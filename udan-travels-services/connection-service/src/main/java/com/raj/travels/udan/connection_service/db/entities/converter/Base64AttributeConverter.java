package com.raj.travels.udan.connection_service.db.entities.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter for Base64 encoding and decoding of String attributes.
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @date 19-6-2025
 */
@Converter
public class Base64AttributeConverter implements AttributeConverter<String, String> {

    /**
     * Converts the entity attribute value to a Base64 encoded string for storage in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the Base64 encoded string, or null if the attribute is null
     */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return Base64Converter.encode(attribute);
    }

    /**
     * Converts the Base64 encoded string from the database back to the original entity attribute value.
     *
     * @param dbData the data from the database column to be converted
     * @return the original string value, or null if the dbData is null
     */
    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Base64Converter.decode(dbData);
    }
}

