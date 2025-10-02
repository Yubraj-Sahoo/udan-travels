package com.raj.travels.commons.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Interceptor for logging outgoing HTTP requests and incoming HTTP responses
 * made using RestTemplate.
 */
@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        String exchangeId = UUID.randomUUID().toString();

        logRequest(request, body, exchangeId);

        ClientHttpResponse response = execution.execute(request, body);
        BufferingClientHttpResponseWrapper bufferedResponse = new BufferingClientHttpResponseWrapper(response);

        logResponse(bufferedResponse, exchangeId);

        return bufferedResponse;
    }

    private void logRequest(HttpRequest request, byte[] body, String exchangeId) {
        String requestBody = new String(body, StandardCharsets.UTF_8);

        String requestLog = "\n========== OUTGOING REQUEST ==========\n" +
                "Exchange ID: " + exchangeId + "\n" +
                "URI: " + request.getURI() + "\n" +
                "Method: " + request.getMethod() + "\n" +
                "Headers: " + request.getHeaders() + "\n" +
                "Body: " + requestBody + "\n" +
                "======================================";
        log.info(requestLog);
    }

    private void logResponse(ClientHttpResponse response, String exchangeId) throws IOException {
        String responseBody = new BufferedReader(
                new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        String responseLog = "\n========== INCOMING RESPONSE =========\n" +
                "Exchange ID: " + exchangeId + "\n" +
                "Status Code: " + response.getStatusCode() + "\n" +
                "Headers: " + response.getHeaders() + "\n" +
                "Body: " + responseBody + "\n" +
                "======================================";
        log.info(responseLog);
    }

    private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse response;
        private final byte[] body;

        BufferingClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
            this.response = response;
            this.body = response.getBody().readAllBytes();
        }

        @Override
        public InputStream getBody() {
            return new ByteArrayInputStream(body);
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            response.close();
        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }
    }
}
