package main.agent;

public interface LLMAgent {
    String interact(String input);
    String getAgentName();
}
