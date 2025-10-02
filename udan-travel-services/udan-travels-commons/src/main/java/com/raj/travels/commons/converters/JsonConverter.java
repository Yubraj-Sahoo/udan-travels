package com.raj.travels.commons.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.io.File;

/**
 * Utility class for converting between JSON strings and Java objects,
 * as well as reading from and writing to JSON files.
 *
 * <p>This class uses the Jackson {@link ObjectMapper} for serialization
 * and deserialization.</p>
 *
 * @author Yubraj Sahoo
 * @version 1.0
 * @since 2025-10-02
 */
public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    private JsonConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a JSON string into a Java object of the specified type.
     *
     * @param jsonString the JSON string
     * @param clazz      the class of the target object
     * @param <T>        the type of the object
     * @return the deserialized Java object
     */
    @SneakyThrows
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * Converts a Java object into its JSON string representation.
     *
     * @param object the Java object
     * @return the JSON string
     */
    @SneakyThrows
    public static String toJsonString(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Reads a JSON file and converts its contents into a Java object of the specified type.
     *
     * @param filePath the path to the JSON file
     * @param clazz    the class of the target object
     * @param <T>      the type of the object
     * @return the deserialized Java object
     */
    @SneakyThrows
    public static <T> T fromJsonFile(String filePath, Class<T> clazz) {
        return objectMapper.readValue(new File(filePath), clazz);
    }

    /**
     * Writes a Java object into a JSON file in a pretty-printed format.
     *
     * @param filePath the path of the output JSON file
     * @param object   the Java object to serialize
     */
    @SneakyThrows
    public static void toJsonFile(String filePath, Object object) {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), object);
    }
}
