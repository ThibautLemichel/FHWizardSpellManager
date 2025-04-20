package main.agent;

import main.agnostic_model.Model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GeminiAgent implements Model {
    private static final String AGENT_NAME = "gemini";

    private static final String API_KEY = loadApiKey();

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;

    @Override
    public String getName() {
        return AGENT_NAME;
    }

    @Override
    public String interact(String input) {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "{"
                + "\"contents\": [{"
                +     "\"parts\": [{\"text\": \"" + escapeJson(input) + "\"}]"
                + "}]"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return extractText(response.body());
            } else {
                return "Error: " + response.statusCode() + " - " + response.body();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Escapes JSON special characters in the input string.
     * @param input the input string to escape
     * @return
     */
    private String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\"", "\\\"");
    }

    private String extractText(String json) {
        String textKey = "\"text\":";
        int textStart = json.indexOf(textKey) + textKey.length();
        if (textStart == -1) {
            return "No text found.";
        }
        int textEnd = json.indexOf("}", textStart);
        if (textEnd == -1) {
            return "No text found.";
        }
        return json.substring(textStart, textEnd).replaceAll("[\"{}]", "").trim();
    }

    private static String loadApiKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("API_KEY=")) {
                    return line.substring("API_KEY=".length()).trim();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API key from .env file", e);
        }
        throw new RuntimeException("API_KEY not found in .env file");
    }
}
