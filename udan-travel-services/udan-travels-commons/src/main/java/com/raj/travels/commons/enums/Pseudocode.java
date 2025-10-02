package com.raj.travels.commons.enums;

import lombok.Getter;

@Getter
public enum Pseudocode {
    UDAN("UDAN", GdsType.AMADEUS);

    Pseudocode(String code, GdsType gdsType) {
        this.code = code;
        this.gdsType = gdsType;
    }

    private String code;
    private GdsType gdsType;
}
