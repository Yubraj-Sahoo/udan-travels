package com.raj.travel.udan.services.connection.converter;

/**
 * Utility class for converting strings to and from Base64 encoding.
 * This class provides methods to encode a string into Base64 format
 * and decode a Base64 encoded string back to its original format.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 29-6-2025
 */
public class Base64Converter {

    private Base64Converter() {
    }

    /**
     * Encodes a given string into Base64 format.
     *
     * @param input the string to encode
     * @return the Base64 encoded string
     */
    public static String encode(String input) {
        return java.util.Base64.getEncoder().encodeToString(input.getBytes());
    }

    /**
     * Decodes a Base64 encoded string back to its original format.
     *
     * @param input the Base64 encoded string to decode
     * @return the decoded string
     */
    public static String decode(String input) {
        return new String(java.util.Base64.getDecoder().decode(input));
    }
}
