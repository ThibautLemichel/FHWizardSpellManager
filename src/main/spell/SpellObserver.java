package main.spell;

// Purpose: Interface for observers of the main.spell.Spell class.
public interface SpellObserver {

    /**
     * Called when a spell is added
     * @param spell the spell that was added
     */
    void onSpellAdded(Spell spell);
}
