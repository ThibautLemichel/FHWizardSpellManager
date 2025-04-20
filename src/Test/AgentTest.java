package Test;

import main.agent.GeminiAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.agnostic_model.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgentTest {
    @Test
    public void testLLMAgentInteraction() {
        Model suggestionAgent = new SpellSuggestionAgent();
        String suggestionResponse = suggestionAgent.interact("attack");
        assertNotNull(suggestionResponse);
        assertTrue(suggestionResponse.contains("Suggested Spell: FireBall"));

        Model descriptionAgent = new SpellDescriptionAgent();
        String descriptionResponse = descriptionAgent.interact("FireBall");
        assertNotNull(descriptionResponse);
        assertTrue(descriptionResponse.contains("A powerful fire spell that deals damage to enemies."));

        Model geminiAgent = new GeminiAgent();
        String geminiResponse = geminiAgent.interact("As a response I just want you to say : Hello world");
        assertNotNull(geminiResponse);
        assertTrue(geminiResponse.contains("Hello world"));
    }
}
