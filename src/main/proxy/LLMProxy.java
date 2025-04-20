package main.proxy;
import java.util.HashMap;
import java.util.Map;

import main.agent.LLMAgent;
import main.agnostic_model.Model;

public class LLMProxy implements Model {
    private final Model realModel;
    private final Map<String, String> cache = new HashMap<>();

    public LLMProxy(Model realAgent) {
        this.realModel = realAgent;
    }

    @Override
    public String getName() {
        return realModel.getName();
    }

    @Override
    public String interact(String input) {
        if (cache.containsKey(input)) {
            return "Cached Response: " + cache.get(input);
        }
        String response = realModel.interact(input);
        cache.put(input, response);
        return response;
    }
}