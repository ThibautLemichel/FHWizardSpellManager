package main;

import java.util.HashMap;
import java.util.Map;

public class DefaultPromptOrchestrator  implements PromptOrchestrator {
    private final Map<String, LLMAgent> agents = new HashMap<>();

    public DefaultPromptOrchestrator() {
        // Register agents
        agents.put("description", new SpellDescriptionAgent());
        agents.put("suggestion", new SpellSuggestionAgent());
        agents.put("gemini", new GeminiAgent());
    }

    @Override
    public String orchestrate(String agentType, String input) {
        LLMAgent agent = agents.get(agentType.toLowerCase());
        if (agent == null) {
            return "Invalid agent type: " + agentType;
        }
        return agent.interact(input);
    }
}
