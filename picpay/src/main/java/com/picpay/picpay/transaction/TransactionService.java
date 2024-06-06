package com.picpay.picpay.transaction;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picpay.picpay.authorization.AuthorizerService;
import com.picpay.picpay.notification.NotificationService;
import com.picpay.picpay.wallet.Wallet;
import com.picpay.picpay.wallet.WalletRepository;
import com.picpay.picpay.wallet.WalletType;

/**
 * Um serviço para operações relacionadas a transações.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    /**
     * Construtor do TransactionService.
     * @param transactionRepository O repositório de transações.
     * @param authorizerService O serviço de autorização de transações.
     * @param walletRepository O repositório de carteiras.
     * @param notificationService O serviço de notificações.
     */
    public TransactionService(TransactionRepository transactionRepository, AuthorizerService authorizerService, WalletRepository walletRepository, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    /**
     * Cria uma nova transação.
     * @param transaction A transação a ser criada.
     * @return A transação criada.
     */
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
        // Autorização de transações
        authorizerService.authorize(transaction);

        // Notificação
        notificationService.notify(transaction);

        return newTransaction;
    }

    /*
     * - Se pagador tem uma carteira do tipo comum
     * - Se o pagador tem saldo suficiente
     * - Pagador não pode ser o recebedor
     */
    private void validate(Transaction transaction) {

        walletRepository.findById(transaction.payee())
            .map(payee -> walletRepository.findById(transaction.payer())
                .map(
                    payer -> isTransactionValid(transaction, payer) ? true : null)
                    .orElseThrow(() -> new InvalidTransactionException("Transação inválida - %s".formatted(transaction))))
                    .orElseThrow(() -> new InvalidTransactionException("Transação inválida - %s".formatted(transaction)));
    }

    private boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getValue() &&
            payer.balance().compareTo(transaction.value()) >= 0 &&
            !payer.id().equals(transaction.payee());
    }

    /**
     * Retorna uma lista de todas as transações.
     * @return Uma lista de transações.
     */
    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
