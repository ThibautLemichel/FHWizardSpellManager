package main.agent;

import main.agnostic_model.Model;

public class SpellSuggestionAgent implements Model {
    public static final String AGENT_NAME = "suggestion";

    @Override
    public String interact(String input) {
        // Simulate interaction with an LLM (Yes Really dump)
        if (input.toLowerCase().contains("attack")) {
            return "Suggested Spell: FireBall";
        } else if (input.toLowerCase().contains("defense")) {
            return "Suggested Spell: Necromancer";
        } else {
            return "No suitable spell found for the given context.";
        }
    }

    @Override
    public String getName() {
        return AGENT_NAME;
    }
}
