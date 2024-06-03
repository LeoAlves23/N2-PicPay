package com.picpay.picpay.transaction;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picpay.picpay.authorization.AuthorizerService;
import com.picpay.picpay.notification.NotificationService;
import com.picpay.picpay.wallet.Wallet;
import com.picpay.picpay.wallet.WalletRepository;
import com.picpay.picpay.wallet.WalletType;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, AuthorizerService authorizerService ,WalletRepository walletRepository, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        // 1 - validar
        validate(transaction);

        // 2 - criar transação
        var newTransaction = transactionRepository.save(transaction);

        // 3- debitar da carteira
        var wallet = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(wallet.debit(transaction.value()));

        // 4- chamar serviços externos
        //Autorização de transações
        authorizerService.authorize(transaction);

        //Notificação
        notificationService.notify(transaction);

        return newTransaction;
    }

    /*
     * -Se pagador tem uma carteira do tipo comum
     * -Se o pagador tem saldo suficiente
     * -Pagador não pode ser o recebedor
     */
    private void validate(Transaction transaction) {

        walletRepository.findById(transaction.payee())
            .map(payee -> walletRepository.findById(transaction.payer())
                .map(
                    payer -> isTransactionValid(transaction, payer) ? true : null)
                    .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s" .formatted(transaction))))
                    .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s" .formatted(transaction)));
      }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() &&
            payer.balance().compareTo(transaction.value()) >= 0 &&
            !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
