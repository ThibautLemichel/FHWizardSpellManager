package Test;

import main.agent.GeminiAgent;
import main.agent.LLMAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.orchestrator.DefaultPromptOrchestrator;
import main.orchestrator.PromptOrchestrator;
import main.spell.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    private SpellBook spellBook;
    private Wizard wizard;

    @BeforeEach
    public void setUp() {
        spellBook = SpellBook.getInstance();
        spellBook.clear(); // Ensure the spell book is empty before each test
        wizard = new Wizard();
    }

    @Test
    public void testAddSpell() {
        Spell fireBall = SpellFactory.createSpell("FireBall");
        spellBook.addSpell(fireBall);

        List<Spell> spells = spellBook.getSpells();
        assertEquals(1, spells.size());
        assertEquals("FireBallSpell", spells.get(0).getClass().getSimpleName());
    }

    @Test
    public void testCastSpell() {
        Spell fireBall = SpellFactory.createSpell("FireBall");
        spellBook.addSpell(fireBall);

        Command castCommand = new CastSpellCommand(fireBall);
        wizard.castSpell(castCommand);

        assertTrue(wizard.getSpellHistory().contains(castCommand));
    }

    @Test
    public void testUndoLastSpell() {
        Spell fireBall = SpellFactory.createSpell("FireBall");
        spellBook.addSpell(fireBall);

        Command castCommand = new CastSpellCommand(fireBall);
        wizard.castSpell(castCommand);

        wizard.undoLastSpell();
        assertTrue(wizard.getSpellHistory().isEmpty());
    }

    @Test
    public void testUndoAllSpells() {
        Spell fireBall = SpellFactory.createSpell("FireBall");
        Spell necromancer = SpellFactory.createSpell("Necromancer");
        spellBook.addSpell(fireBall);
        spellBook.addSpell(necromancer);

        Command fireBallCommand = new CastSpellCommand(fireBall);
        Command necromancerCommand = new CastSpellCommand(necromancer);
        wizard.castSpell(fireBallCommand);
        wizard.castSpell(necromancerCommand);

        wizard.undoAllSpell();
        assertTrue(wizard.getSpellHistory().isEmpty());
    }

    @Test
    public void testListSpells() {
        Spell fireBall = SpellFactory.createSpell("FireBall");
        Spell necromancer = SpellFactory.createSpell("Necromancer");
        spellBook.addSpell(fireBall);
        spellBook.addSpell(necromancer);

        List<Spell> spells = spellBook.getSpells();
        assertEquals(2, spells.size());
        assertEquals("FireBallSpell", spells.get(0).getClass().getSimpleName());
        assertEquals("NecromancerSpell", spells.get(1).getClass().getSimpleName());
    }

    @Test
    public void testLLMAgentInteraction() {
        LLMAgent suggestionAgent = new SpellSuggestionAgent();
        String suggestionResponse = suggestionAgent.interact("attack");
        assertNotNull(suggestionResponse);
        assertTrue(suggestionResponse.contains("Suggested Spell: FireBall"));

        LLMAgent descriptionAgent = new SpellDescriptionAgent();
        String descriptionResponse = descriptionAgent.interact("FireBall");
        assertNotNull(descriptionResponse);
        assertTrue(descriptionResponse.contains("A powerful fire spell that deals damage to enemies."));

        LLMAgent geminiAgent = new GeminiAgent();
        String geminiResponse = geminiAgent.interact("As a response I just want you to say : Hello world");
        assertNotNull(geminiResponse);
        assertTrue(geminiResponse.contains("Hello world"));
    }

    @Test
    public void testPromptOrchestrator() {
        PromptOrchestrator orchestrator = new DefaultPromptOrchestrator();

        String suggestionResponse = orchestrator.orchestrate("Suggestion", "attack");
        assertNotNull(suggestionResponse);
        assertTrue(suggestionResponse.contains("Suggested Spell: FireBall"));

        String descriptionResponse = orchestrator.orchestrate("Description", "FireBall");
        assertNotNull(descriptionResponse);
        assertTrue(descriptionResponse.contains("A powerful fire spell that deals damage to enemies."));

        String geminiResponse = orchestrator.orchestrate("Gemini", "As a response I just want you to say : Hello world");
        assertNotNull(geminiResponse);
        assertTrue(geminiResponse.contains("Hello world"));
    }
}
