package main;

public class SpellDescriptionAgent implements LLMAgent{
    private static final String AGENT_NAME = "DumpDescriptionAgent";

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
    public String getAgentName() {
        return AGENT_NAME;
    }
}
