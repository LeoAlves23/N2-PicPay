package com.picpay.picpay.transaction;

import org.springframework.data.repository.ListCrudRepository;

/**
 * Uma interface de repositório para operações de CRUD (Create, Read, Update, Delete) em transações.
 */
public interface TransactionRepository extends ListCrudRepository<Transaction, Long>{
}
