// Observer Pattern: Observes changes in SpellBook and logs them
public class SpellLogger implements SpellStatusObserver {
    public void onSpellStatusChange(Spell spell, String status) {
        System.out.println("Spell status changed: " + spell.getClass().getSimpleName() + " : " + status);
    }
}
