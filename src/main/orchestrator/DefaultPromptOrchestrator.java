package main.orchestrator;

import main.agent.GeminiAgent;
import main.agent.LLMAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.proxy.LLMProxy;

import java.util.HashMap;
import java.util.Map;

public class DefaultPromptOrchestrator  implements PromptOrchestrator {
    private final Map<String, LLMAgent> agents = new HashMap<>();

    public DefaultPromptOrchestrator() {
        // Register agents
        agents.put("description", new LLMProxy(new SpellDescriptionAgent()));
        agents.put("suggestion", new LLMProxy(new SpellSuggestionAgent()));
        agents.put("gemini", new LLMProxy(new GeminiAgent()));
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
