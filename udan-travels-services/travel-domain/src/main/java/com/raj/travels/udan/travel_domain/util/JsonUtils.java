package com.raj.travels.udan.travel_domain.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.io.File;


/**
 * Utility class for JSON operations using Jackson library.
 * Provides methods to read from and write to JSON files and strings.
 *
 * @version 1.0
 * @date 2025-08-23
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private JsonUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Reads a JSON file and maps its content to an object of the specified class.
     *
     * @param filePath  the path to the JSON file
     * @param reference the type reference of the object to map the JSON content to
     * @param <T>       the type of the object
     * @return an object of type T mapped from the JSON file
     */
    @SneakyThrows
    public static <T> T readFromJsonFile(String filePath, TypeReference<T> reference) {
        return objectMapper.readValue(new File(filePath), reference);
    }

    /**
     * Reads a JSON string and maps its content to an object of the specified class.
     *
     * @param jsonString the JSON string to read
     * @param reference  the type reference of the object to map the JSON content to
     * @param <T>        the type of the object
     * @return an object of type T mapped from the JSON string
     */
    @SneakyThrows
    public static <T> T readFromJsonString(String jsonString, TypeReference<T> reference) {
        return objectMapper.readValue(jsonString, reference);
    }

    /**
     * Writes an object to a JSON file with pretty printing.
     *
     * @param filePath the path to the JSON file
     * @param obj      the object to write to the JSON file
     */
    @SneakyThrows
    public static void writeAsJsonToFile(String filePath, Object obj) {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), obj);
    }

    /**
     * Converts an object to a JSON string with pretty printing.
     *
     * @param obj the object to convert to a JSON string
     * @return a JSON string representation of the object
     */
    @SneakyThrows
    public static String writeAsJsonString(Object obj) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
