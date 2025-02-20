import java.util.ArrayList;
import java.util.List;

public class SpellBook {
    private static SpellBook instance;
    private List<Spell> spells = new ArrayList<>();
    private List<SpellObserver> observers = new ArrayList<>();

    private SpellBook() {}

    public static SpellBook getInstance() {
        if (instance == null) {
            instance = new SpellBook();
        }
        return instance;
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
        notifyObservers(spell);
    }

    public void castAllSpells() {
        for (Spell spell : spells) {
            spell.cast();
        }
    }

    public void addObserver(SpellObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Spell spell) {
        for (SpellObserver observer : observers) {
            observer.onSpellAdded(spell);
        }
    }
}
