package com.raj.travels.udan.connection_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SabreAuthTokenSingleClass {

    // Nested DTO for parsing the JSON response
    public static class SabreTokenResponse {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("expires_in")
        private int expiresIn;

        public String getAccessToken() {
            return accessToken;
        }

        // This is necessary for RestTemplate to deserialize the JSON
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        // Standard setters... (omitted for brevity)
    }

    public static void main(String[] args) {
        String userId = "V1:ninsd23iizi8aiiv:DEVCENTER:EXT";
        String encodedUserId =  Base64.getEncoder().encodeToString(userId.getBytes(StandardCharsets.UTF_8));
        String password = "1BwbLnJ6";
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        String req = encodedUserId + ":" + encodedPassword;
        String encodedReq = Base64.getEncoder().encodeToString(req.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encoded UserID: " + encodedReq);
    }

    public static String getSabreAuthToken(String userID, String password, String pcc, String domain) {
        RestTemplate restTemplate = new RestTemplate();
        String tokenEndpoint = "https://api.platform.sabre.com/v2/auth/token";

        try {
            // Step 1: Format and encode credentials
            String userIdString = "V1:" + userID + ":" + pcc + ":" + domain;
            String encodedUserId = Base64.getEncoder().encodeToString(userIdString.getBytes());
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            String finalCredentialsString = encodedUserId + ":" + encodedPassword;
            String encodedFinalCredentials = Base64.getEncoder().encodeToString(finalCredentialsString.getBytes());

            // Step 2: Set up HTTP headers for the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Basic " + encodedFinalCredentials);

            // Step 3: Create the request body
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "client_credentials");

            // Step 4: Create the HTTP entity with headers and body
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

            // Step 5: Make the POST request and get the response
            ResponseEntity<SabreTokenResponse> response = restTemplate.exchange(
                    tokenEndpoint,
                    HttpMethod.POST,
                    requestEntity,
                    SabreTokenResponse.class
            );

            // Step 6: Return the access token if the request was successful
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getAccessToken();
            } else {
                System.err.println("Failed to retrieve Sabre token. Response status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
