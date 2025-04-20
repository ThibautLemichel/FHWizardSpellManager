package main.orchestrator;

import main.agent.GeminiAgent;
import main.agent.LLMAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.agnostic_model.Model;
import main.proxy.LLMProxy;

import java.util.HashMap;
import java.util.Map;

public class DefaultPromptOrchestrator  implements PromptOrchestrator {
    private final Map<String, Model> models = new HashMap<>();

    public void registerModel(Model model) {
        models.put(model.getName().toLowerCase(), model);
    }

    @Override
    public String orchestrate(String agentType, String input) {
        Model agent = models.get(agentType.toLowerCase());
        if (agent == null) {
            return "Invalid agent type: " + agentType;
        }
        return agent.interact(input);
    }
}
