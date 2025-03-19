// Purpose: Interface for observers of the Spell class.
public interface SpellObserver {

    /**
     * Called when a spell is added
     * @param spell the spell that was added
     */
    void onSpellAdded(Spell spell);
}
