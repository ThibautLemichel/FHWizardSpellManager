package main;

// Observer Pattern: Observes changes in main.SpellBook and logs them
public class SpellLogger implements SpellStatusObserver {
    public void onSpellStatusChange(Spell spell, String status) {
        System.out.println("main.Spell status changed: " + spell.getClass().getSimpleName() + " : " + status);
    }
}
