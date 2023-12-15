package dev.surya.labs.service.impl;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUpdater {

    public static void main(String[] args) {
        // Replace "yourfile.json" with the path to your JSON file
        String jsonFilePath = "yourfile.json";

        try {
            // Step 1: Read the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

            // Step 2: Update the JSON object
            updateJsonObject(rootNode);

            // Step 3: Write the updated JSON back to the file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), rootNode);

            System.out.println("JSON object updated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateJsonObject(JsonNode rootNode) {
        // Modify the JSON object as needed
        // For example, assuming the JSON object has a field named "name"
        // and you want to update its value to "New Name"
        if (rootNode.has("name")) {
            ((ObjectNode) rootNode).put("name", "New Name");
        }
    }
}
