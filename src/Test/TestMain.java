package Test;

import main.agent.GeminiAgent;
import main.agent.LLMAgent;
import main.agent.SpellDescriptionAgent;
import main.agent.SpellSuggestionAgent;
import main.agnostic_model.Model;
import main.orchestrator.DefaultPromptOrchestrator;
import main.orchestrator.PromptOrchestrator;
import main.proxy.LLMProxy;
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
}
