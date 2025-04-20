package main;

import main.agent.GeminiAgent;
import main.agent.LLMAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.agnostic_model.Model;
import main.orchestrator.DefaultPromptOrchestrator;
import main.orchestrator.PromptOrchestrator;
import main.spell.*;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        SpellBook spellBook = SpellBook.getInstance();
        Wizard wizard = new Wizard();
        Scanner scanner = new Scanner(System.in);

        SpellLogger logger = new SpellLogger();
        spellBook.addObserver(logger);

        String agentType;
        Model agent;
        String context;
        String spellName;

        PromptOrchestrator orchestrator = new DefaultPromptOrchestrator();

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add main.spell.Spell");
            System.out.println("2. Cast main.spell.Spell");
            System.out.println("3. Undo Last main.spell.Spell");
            System.out.println("4. Undo All Spells");
            System.out.println("5. List Spells");
            System.out.println("6. Exit");
            System.out.println("7. Interact with LLM Agent");
            System.out.println("8. Prompt Orchestrator");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter spell type (FireBall/Necromancer):");
                    String spellType = scanner.nextLine();
                    Spell spell = SpellFactory.createSpell(spellType);
                    spellBook.addSpell(spell);
                    System.out.println(spellType + " spell added.");
                    break;
                case 2:
                    if(spellBook.getSpells().isEmpty()) {
                        System.out.println("No spells available.");
                        break;
                    }
                    System.out.println("Available spells:");
                    for (Spell s : spellBook.getSpells()) {
                        System.out.println(s.getClass().getSimpleName());
                    }
                    System.out.println("Enter spell type to cast (FireBallSpellNecromancerSpell):");
                    String castSpellType = scanner.nextLine();
                    Spell castSpell = spellBook.getSpell(castSpellType);
                    if (castSpell != null) {
                        Command castCommand = new CastSpellCommand(castSpell);
                        wizard.castSpell(castCommand);
                        System.out.println(castSpellType + " spell cast.");
                    } else {
                        System.out.println("Spell not found.");
                    }
                    break;
                case 3:
                    wizard.undoLastSpell();
                    System.out.println("Last spell undone.");
                    break;
                case 4:
                    wizard.undoAllSpell();
                    System.out.println("All spells undone.");
                    break;
                case 5:
                    System.out.println("List of spells:");
                    for (Spell s : spellBook.getSpells()) {
                        System.out.println(s.getClass().getSimpleName());
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                case 7:
                    System.out.println("Choose LLM Agent (Suggestion/Description/Gemini):");
                    agentType = scanner.nextLine();
                    if ("Suggestion".equalsIgnoreCase(agentType)) {
                        System.out.println("Enter context (e.g., attack, defense):");
                        context = scanner.nextLine();
                        agent = new SpellSuggestionAgent();
                        System.out.println(agent.interact(context));
                    } else if ("Description".equalsIgnoreCase(agentType)) {
                        System.out.println("Enter spell name (e.g., FireBall, Necromancer):");
                        spellName = scanner.nextLine();
                        agent = new SpellDescriptionAgent();
                        System.out.println(agent.interact(spellName));
                    } else if ("Gemini".equalsIgnoreCase(agentType)) {
                        agent = new GeminiAgent();
                        System.out.println("Enter input for Gemini:");
                        String geminiInput = scanner.nextLine();
                        System.out.println(agent.interact(geminiInput));
                    } else {
                        System.out.println("Invalid agent type.");
                    }
                    break;
                case 8:
                    System.out.println("\nPrompt Ochestrator: Suggestion/Description/Gemini");
                    agentType = scanner.nextLine();

                    System.out.println("Enter input for the agent:");
                    String input = scanner.nextLine();

                    String response = orchestrator.orchestrate(agentType, input);
                    System.out.println("Agent Response: " + response);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
