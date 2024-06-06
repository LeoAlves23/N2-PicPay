package com.picpay.picpay.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Um controlador REST para transações.
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Construtor do TransactionController.
     * @param transactionService O serviço de transações.
     */
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    /**
     * Cria uma nova transação.
     * @param transaction A transação a ser criada.
     * @return A transação criada.
     */
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction ){
        return transactionService.create(transaction);
    }

    /**
     * Retorna uma lista de todas as transações.
     * @return Uma lista de transações.
     */
    @GetMapping
    public List<Transaction> list(){
        return transactionService.list();
    }
}
