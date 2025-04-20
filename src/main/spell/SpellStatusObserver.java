package main.spell;

interface SpellStatusObserver {
    /**
     * Called when the status of a spell changes
     * @param spell
     * @param status
     */
    void onSpellStatusChange(Spell spell, String status);
}