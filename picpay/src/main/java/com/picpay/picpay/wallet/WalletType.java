package com.picpay.picpay.wallet;

/**
 * Um enum que representa o tipo de carteira.
 */
public enum WalletType {
    
    /**
     * Representa o tipo de carteira comum.
     */
    COMUM(1),
    
    /**
     * Representa o tipo de carteira para logistas.
     */
    LOGISTA(2);

    private int value;

    /**
     * Construtor do enum WalletType.
     * @param value O valor associado ao tipo de carteira.
     */
    private WalletType(int value){
        this.value = value;
    }

    /**
     * Obtém o valor associado ao tipo de carteira.
     * @return O valor associado ao tipo de carteira.
     */
    public int getValue(){
        return value;
    }
}
