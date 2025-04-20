package main.spell;

// Observer Pattern: Observes changes in main.spell.SpellBook and logs them
public class SpellLogger implements SpellStatusObserver {
    public void onSpellStatusChange(Spell spell, String status) {
        System.out.println("main.spell.Spell status changed: " + spell.getClass().getSimpleName() + " : " + status);
    }
}
