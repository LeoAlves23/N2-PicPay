package com.picpay.picpay.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Um registro que representa uma transação.
 */
@Table(name = "TRANSACTIONS")
public record Transaction(
    /**
     * O identificador da transação.
     */
    @Id Long id,
    /**
     * O identificador do pagador.
     */
    Long payer,
    /**
     * O identificador do beneficiário.
     */
    Long payee,
    /**
     * O valor da transação.
     */
    BigDecimal value,
    /**
     * A data e hora de criação da transação.
     */
    @CreatedDate LocalDateTime createdAt
) {
    /**
     * Construtor da Transaction.
     * Arredonda o valor da transação para duas casas decimais.
     */
    public Transaction {
        value = value.setScale(2);
    }

    /**
     * Cria uma instância de Transaction com os parâmetros fornecidos.
     * @param id O identificador da transação.
     * @param payer O identificador do pagador.
     * @param payee O identificador do beneficiário.
     * @param value O valor da transação.
     * @param createdAt A data e hora de criação da transação.
     * @return Uma instância de Transaction.
     */
    public static Transaction createTransaction(Long id, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
        return new Transaction(id, payer, payee, value, createdAt);
    }
}
