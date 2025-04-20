package main.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import main.agnostic_model.Model;

public class LLMProxy implements Model {
    private final Model realModel;
    private final Map<String, String> cache = new HashMap<>();
    private long lastRequestTime = 0;
    private final long rateLimitInterval = TimeUnit.SECONDS.toMillis(1); // 1 request per second
    private final int maxRequestSize = 100; // Max request size in characters

    public LLMProxy(Model realModel) {
        this.realModel = realModel;
    }

    @Override
    public String getName() {
        return realModel.getName();
    }

    @Override
    public String interact(String input) {
        // Check input size
        if (!isRequestSizeValid(input)) {
            return "Request size exceeds the maximum allowed limit of " + maxRequestSize + " characters.";
        }

        // Preprocess the input
        String processedInput = preprocessInput(input);

        // Check rate limiting
        if (!isRequestAllowed()) {
            return "Rate limit exceeded. Please wait before making another request.";
        }

        // Log the request
        logRequest(processedInput);

        // Check the cache
        if (cache.containsKey(processedInput)) {
            return "Cached Response: " + cache.get(processedInput);
        }

        // Forward the request to the real model
        String response = realModel.interact(processedInput);

        // Cache the response
        cache.put(processedInput, response);

        // Log the response
        logResponse(response);

        return response;
    }

    /**
     * Check if the request size is valid
     * @param input
     * @return
     */
    private boolean isRequestSizeValid(String input) {
        return input.length() <= maxRequestSize;
    }

    /**
     * Preprocess the input
     * @param input
     * @return
     */
    private String preprocessInput(String input) {
        return input.trim().toLowerCase();
    }

    /**
     * Check if the request is allowed based on rate limiting
     * @return
     */
    private boolean isRequestAllowed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRequestTime < rateLimitInterval) {
            return false;
        }
        lastRequestTime = currentTime;
        return true;
    }

    /**
     * Print the request
     * @param input
     */
    private void logRequest(String input) {
        System.out.println("Request: " + input);
    }

    /**
     * Print the response
     * @param response
     */
    private void logResponse(String response) {
        System.out.println("Response: " + response);
    }
}