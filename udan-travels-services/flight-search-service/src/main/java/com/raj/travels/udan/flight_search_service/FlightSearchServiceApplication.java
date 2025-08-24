package com.raj.travels.udan.flight_search_service;

import com.raj.travels.udan.flight_search_service.interceptor.RestCallLoggingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FlightSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightSearchServiceApplication.class, args);
    }

    /**
     * Bean definition for RestTemplate to be used for making REST calls.
     * This RestTemplate can be autowired into other components for making HTTP requests.
     *
     * @return a new instance of RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestCallLoggingInterceptor interceptor) {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.getInterceptors().add(interceptor);
        return restTemplate;
    }
}
