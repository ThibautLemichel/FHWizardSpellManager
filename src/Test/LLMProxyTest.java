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

        // Second call - should return cached response
        String secondResponse = proxy.interact(input);
        assertEquals("Cached Response: " + expectedResponse, secondResponse);
    }
}