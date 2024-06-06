package com.picpay.picpay.authorization;

/**
 * Um registro que representa uma autorização.
 */
public record Authorization(
    /**
     * A mensagem de autorização.
     */
    String message
) {
    /**
     * Verifica se a autorização está concedida.
     * @return true se a mensagem for "Autorizado", false caso contrário.
     */
    public boolean isAuthorized(){
        return message.equals("Autorizado");
    }
}
