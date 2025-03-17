import java.util.ArrayList;
import java.util.List;

public class SpellBook {
    private static SpellBook instance;
    private List<Spell> spells = new ArrayList<>();
    private List<SpellStatusObserver> observers = new ArrayList<>();

    private SpellBook() {}

    public static SpellBook getInstance() {
        if (instance == null) {
            instance = new SpellBook();
        }
        return instance;
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
        notifyObservers(spell, "added");
    }

    public void castAllSpells() {
        for (Spell spell : spells) {
            spell.cast();
            notifyObservers(spell, "cast");
        }
    }

    public Spell getSpell(String spellType) {
        for (Spell spell : spells) {
            if (spell.getClass().getSimpleName().equalsIgnoreCase(spellType)) {
                return spell;
            }
        }
        return null;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void addObserver(SpellStatusObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Spell spell, String status) {
        for (SpellStatusObserver observer : observers) {
            observer.onSpellStatusChange(spell, status);
        }
    }
}
