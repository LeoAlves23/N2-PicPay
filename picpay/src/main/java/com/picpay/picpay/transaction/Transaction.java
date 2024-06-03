package com.picpay.picpay.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "TRANSACTIONS")
public record Transaction(
    @Id Long id,
    Long payer,
    Long payee,
    BigDecimal value,
    @CreatedDate LocalDateTime createdAt)
     {
    public Transaction{
        value = value.setScale(2);
    }

    public static Transaction createTransaction(long l, long m, long n, BigDecimal bigDecimal, LocalDateTime now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTransaction'");
    }

}
