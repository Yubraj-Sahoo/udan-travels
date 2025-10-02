package com.raj.travels.commons.dto;

import com.raj.travels.commons.enums.Pseudocode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionResponse {
    private Pseudocode pseudocode;
    private String securityToken;
    private Long expiresIn;
}
