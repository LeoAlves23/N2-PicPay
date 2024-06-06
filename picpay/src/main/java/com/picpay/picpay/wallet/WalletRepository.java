package com.picpay.picpay.wallet;

import org.springframework.data.repository.CrudRepository;

/**
 * Uma interface de repositório para operações de CRUD (Create, Read, Update, Delete) em carteiras.
 */
public interface WalletRepository extends CrudRepository<Wallet, Long>{
    
}
