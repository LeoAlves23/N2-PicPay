package com.picpay.picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

/**
 * A classe principal para a aplicação Picpay.
 * Esta classe inicializa e executa a aplicação Spring Boot.
 */
@EnableJdbcAuditing
@SpringBootApplication
public class PicpayApplication {

    /**
     * O método principal para iniciar a aplicação Picpay.
     * @param args Os argumentos de linha de comando passados para a aplicação.
     */
	public static void main(String[] args) {
		SpringApplication.run(PicpayApplication.class, args);
	}

}
