package Test;

import main.agent.GeminiAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.orchestrator.DefaultPromptOrchestrator;
import main.proxy.LLMProxy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrchestratorTest {
    @Test
    public void testPromptOrchestrator() {
        DefaultPromptOrchestrator orchestrator = new DefaultPromptOrchestrator();
        orchestrator.registerModel(new LLMProxy(new SpellSuggestionAgent()));
        orchestrator.registerModel(new LLMProxy(new SpellDescriptionAgent()));
        orchestrator.registerModel(new LLMProxy(new GeminiAgent()));

        String suggestionResponse = orchestrator.orchestrate("suggestion", "attack");
        assertNotNull(suggestionResponse);
        System.out.println("Suggestion Response: " + suggestionResponse);
        assertTrue(suggestionResponse.contains("Suggested Spell: FireBall"));

        String descriptionResponse = orchestrator.orchestrate("description", "FireBall");
        assertNotNull(descriptionResponse);
        assertTrue(descriptionResponse.contains("A powerful fire spell that deals damage to enemies."));

        String geminiResponse = orchestrator.orchestrate("gemini", "As a response I just want you to say : hello world");
        assertNotNull(geminiResponse);
        assertTrue(geminiResponse.contains("hello world"));
    }
}
