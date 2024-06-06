package com.picpay.picpay.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.picpay.picpay.transaction.Transaction;

/**
 * Um serviço para autorização de transações.
 */
@Service
public class AuthorizerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizerService.class);
    private RestClient restClient;

    /**
     * Construtor do AuthorizerService.
     * @param builder O construtor para o RestClient.
     */
    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
            "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
    }

    /**
     * Autoriza uma transação.
     * @param transaction A transação a ser autorizada.
     * @throws UnauthorizedTransactionException Se a transação não for autorizada.
     */
    public void authorize(Transaction transaction) {
        LOGGER.info("Autorizando transação {}...", transaction);

        var response = restClient.get().retrieve().toEntity(Authorization.class);
        if (response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
            throw new UnauthorizedTransactionException("Transação não autorizada!");
        }
    }
}

/**
 * Um registro que representa uma autorização.
 */
record Authorization(String message) {
    /**
     * Verifica se a autorização está concedida.
     * @return true se a mensagem for "Autorizado", false caso contrário.
     */
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
