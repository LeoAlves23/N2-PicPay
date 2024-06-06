package com.picpay.picpay.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

/**
 * Um registro que representa uma carteira.
 */
public record Wallet(
    /**
     * O identificador da carteira.
     */
    @Id Long id,
    /**
     * O nome completo associado à carteira.
     */
    String fullName,
    /**
     * O CPF associado à carteira.
     */
    Long cpf,
    /**
     * O email associado à carteira.
     */
    String email,
    /**
     * A senha associada à carteira.
     */
    String password,
    /**
     * O tipo de carteira.
     */
    int type,
    /**
     * O saldo da carteira.
     */
    BigDecimal balance
) {
    /**
     * Debita um valor da carteira, subtraindo-o do saldo atual.
     * @param value O valor a ser debitado.
     * @return Uma nova instância de Wallet com o saldo atualizado.
     */
    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.subtract(value));
    }

}
