package com.raj.travels.udan.travel_domain.dto.connection;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationErrorResponse {
    private String status;
    private String errorMessage;
}
