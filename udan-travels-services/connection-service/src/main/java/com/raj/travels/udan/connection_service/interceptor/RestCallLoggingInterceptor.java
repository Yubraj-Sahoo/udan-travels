package com.raj.travels.udan.connection_service.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Interceptor for logging REST calls made by RestTemplate.
 * This interceptor logs the request and response details including headers, body, and execution time.
 * It formats JSON bodies for better readability.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 2-7-2025
 */
@Slf4j
@Service
public class RestCallLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectWriter PRETTY_PRINTER = OBJECT_MAPPER.writerWithDefaultPrettyPrinter();

    /**
     * Intercepts the HTTP request and logs the request and response details.
     *
     * @param request the HTTP request
     * @param body    the request body
     * @param execution the execution context
     * @return the HTTP response
     * @throws IOException if an I/O error occurs
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        String exchangeId = UUID.randomUUID().toString();

        logRequest(request, body,exchangeId);
        long startTime = System.currentTimeMillis();
        try (ClientHttpResponse response = execution.execute(request, body)) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            return logResponse(response, elapsedTime, exchangeId);
        }
    }

    /**
     * Logs the request details including URI, method, headers, and body.
     *
     * @param request the HTTP request
     * @param body    the request body
     * @param exchangeId a unique identifier for the exchange
     */
    private void logRequest(HttpRequest request, byte[] body, String exchangeId) {
        String logMessage = "\n" +
                "===================================== [RestTemplate Request Print Start] ====================================" +
                "\n" +
                "URI         : " + request.getURI() +
                "\n" +
                "Method      : " + request.getMethod() +
                "\n" +
                "Exchange-Id" + exchangeId +
                "\n" +
                "Headers     : " + request.getHeaders() +
                "\n" +
                "Request body: " +
                "\n" +
                formatJsonOrPlain(new String(body, StandardCharsets.UTF_8)) +
                "\n" +
                "===================================== [RestTemplate Request Print End] ====================================";

        log.info(logMessage);
    }

    /**
     * Logs the response details including status code, headers, body, and execution time.
     *
     * @param response the HTTP response
     * @param elapsedTime the time taken to execute the request
     * @param exchangeId a unique identifier for the exchange
     * @return the HTTP response
     * @throws IOException if an I/O error occurs
     */
    private ClientHttpResponse logResponse(ClientHttpResponse response, long elapsedTime, String exchangeId) throws IOException {
        String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        String logMessage = "\n" +
                "===================================== [RestTemplate Response Print Start] ====================================" +
                "\n" +
                "Status code  : " + response.getStatusCode() +
                "\n" +
                "Status text  : " + response.getStatusText() +
                "\n" +
                "Exchange-Id" + exchangeId +
                "\n" +
                "Headers      : " + response.getHeaders() +
                "\n" +
                "Response time : " + elapsedTime + " ms" +
                "\n" +
                "Response body: " +
                "\n" +
                formatJsonOrPlain(responseBody) +
                "\n" +
                "===================================== [RestTemplate Response Print End] ====================================";
        log.info(logMessage);
        return response;
    }

    /**
     * Formats the JSON body for better readability or returns it as plain text if not valid JSON.
     *
     * @param body the request or response body
     * @return formatted JSON string or plain text
     */
    private String formatJsonOrPlain(String body) {
        try {
            Object json = OBJECT_MAPPER.readValue(body, Object.class);
            return PRETTY_PRINTER.writeValueAsString(json);
        } catch (Exception e) {
            return body;
        }
    }
}