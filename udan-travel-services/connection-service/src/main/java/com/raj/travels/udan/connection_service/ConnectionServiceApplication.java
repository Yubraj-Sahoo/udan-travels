package com.raj.travels.udan.connection_service;

import com.raj.travels.commons.interceptors.RestTemplateLoggingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the Connection Service Spring Boot application.
 * Starts the application context.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-10-02
 */
@SpringBootApplication
public class ConnectionServiceApplication {

    /**
     * The main method to run the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConnectionServiceApplication.class, args);
    }

    /**
     * Bean definition for RestTemplate to facilitate HTTP requests.
     *
     * @return a new instance of RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        RestTemplateLoggingInterceptor loggingInterceptor = new RestTemplateLoggingInterceptor();
        interceptors.add(loggingInterceptor);
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
