package com.raj.travels.commons.enums;

import lombok.Getter;

@Getter
public enum Pseudocode {
    UDAN("UDAN", GdsType.AMADEUS),
    UDT("UDT", GdsType.SABRE);

    Pseudocode(String code, GdsType gdsType) {
        this.code = code;
        this.gdsType = gdsType;
    }

    private final String code;
    private final GdsType gdsType;

    /**
     * This method returns the Pseudocode enum constant corresponding to the given code.
     *
     * @param code the pseudocode.
     * @return the pseudocode.
     */
    public static Pseudocode fromCode(String code) {
        for (Pseudocode pseudocode : Pseudocode.values()) {
            if (pseudocode.getCode().equalsIgnoreCase(code)) {
                return pseudocode;
            }
        }
        return null;
    }
}
