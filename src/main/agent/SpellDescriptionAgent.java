package main.agent;

import main.agnostic_model.Model;

public class SpellDescriptionAgent implements Model {
    private static final String AGENT_NAME = "description";

    @Override
    public String interact(String input) {
        // Simulate interaction with an LLM
        switch (input.toLowerCase()) {
            case "fireball":
                return "A powerful fire spell that deals damage to enemies.";
            case "necromancer":
                return "A dark spell that raises the dead to fight for you.";
            default:
                return "No description available for the given spell.";
        }
    }

    @Override
    public String getName() {
        return AGENT_NAME;
    }
}
