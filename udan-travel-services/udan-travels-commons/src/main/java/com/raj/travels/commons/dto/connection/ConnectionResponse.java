package com.raj.travels.commons.dto.connection;

import com.raj.travels.commons.dto.base.BaseDTO;
import com.raj.travels.commons.enums.Pseudocode;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConnectionResponse extends BaseDTO {
    private Pseudocode pseudocode;
    private String securityToken;
    private Long expiresIn;
}
