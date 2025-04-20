package main.proxy;
import java.util.HashMap;
import java.util.Map;

import main.agent.LLMAgent;

public class LLMProxy implements LLMAgent {
    private final LLMAgent realAgent;
    private final Map<String, String> cache = new HashMap<>();

    public LLMProxy(LLMAgent realAgent) {
        this.realAgent = realAgent;
    }

    @Override
    public String getAgentName() {
        return realAgent.getAgentName();
    }

    @Override
    public String interact(String input) {
        if (cache.containsKey(input)) {
            return "Cached Response: " + cache.get(input);
        }
        String response = realAgent.interact(input);
        cache.put(input, response);
        return response;
    }
}