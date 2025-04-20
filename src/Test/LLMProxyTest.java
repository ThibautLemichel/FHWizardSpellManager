package Test;

import main.agent.SpellSuggestionAgent;
import main.proxy.LLMProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LLMProxyTest {

    private SpellSuggestionAgent realAgent;
    private LLMProxy proxy;

    @BeforeEach
    public void setUp() {
        realAgent = new SpellSuggestionAgent();
        proxy = new LLMProxy(realAgent);
    }

    @Test
    public void testProxyDelegatesToRealAgent() {
        String input = "attack";
        String expectedResponse = "Suggested Spell: FireBall"; // Adjust based on actual implementation

        String response = proxy.interact(input);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testProxyCachesResponse() {
        String input = "attack";
        String expectedResponse = "Suggested Spell: FireBall";

        // First call - should delegate to the real agent
        String firstResponse = proxy.interact(input);
        assertEquals(expectedResponse, firstResponse);

        // Simulate a delay to ensure that we are out of the minimal time between requests
        try {
            Thread.sleep(1000); // Sleep for 1 second
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Second call - should return cached response
        String secondResponse = proxy.interact(input);
        System.out.println(secondResponse);
        assertEquals("Cached Response: " + expectedResponse, secondResponse);
    }

    @Test
    public void testProxyRejectsOversizedRequest() {
        String oversizedInput = "a".repeat(101); // Input exceeds the max size of 100 characters
        String expectedResponse = "Request size exceeds the maximum allowed limit of 100 characters.";

        String response = proxy.interact(oversizedInput);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testProxyRejectsRateLimitExceeded() {
        String input = "attack";
        String expectedResponse = "Rate limit exceeded. Please wait before making another request.";

        // Simulate rate limit by calling the method multiple times in quick succession
        for (int i = 0; i < 5; i++) {
            proxy.interact(input);
        }

        // The next call should be rate limited
        String response = proxy.interact(input);

        assertEquals(expectedResponse, response);
    }
}