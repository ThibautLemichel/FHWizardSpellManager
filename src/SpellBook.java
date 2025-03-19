import java.util.ArrayList;
import java.util.List;

// Singleton Pattern: Ensures only one instance of SpellBook is created
public class SpellBook {
    private static SpellBook instance;
    private List<Spell> spells = new ArrayList<>();
    private List<SpellStatusObserver> observers = new ArrayList<>();

    private SpellBook() {}

    /**
     * Gets the instance of the spell book (Singleton)
     * @return instance
     */
    public static SpellBook getInstance() {
        if (instance == null) {
            instance = new SpellBook();
        }
        return instance;
    }

    /**
     * Adds a spell to the spell book
     * @param spell
     */
    public void addSpell(Spell spell) {
        spells.add(spell);
        notifyObservers(spell, "added");
    }

    /**
     * Casts all spells in the spell book
     */
    public void castAllSpells() {
        for (Spell spell : spells) {
            spell.cast();
            notifyObservers(spell, "cast");
        }
    }

    /**
     * Gets a spell from the spell book
     * @param spellType
     * @return spell
     */
    public Spell getSpell(String spellType) {
        for (Spell spell : spells) {
            if (spell.getClass().getSimpleName().equalsIgnoreCase(spellType)) {
                return spell;
            }
        }
        return null;
    }

    /**
     * Gets all spells in the spell book
     * @return spells
     */
    public List<Spell> getSpells() {
        return spells;
    }

    /**
     * Adds an observer to the spell book
     * @param observer
     */
    public void addObserver(SpellStatusObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers of a spell status change
     * @param spell
     * @param status
     */
    private void notifyObservers(Spell spell, String status) {
        for (SpellStatusObserver observer : observers) {
            observer.onSpellStatusChange(spell, status);
        }
    }
}
