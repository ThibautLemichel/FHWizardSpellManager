import java.util.ArrayList;
import java.util.List;

abstract class BaseSpell implements Spell {
    protected SpellState state;
    protected int recoveryTime;
    private List<SpellStatusObserver> observers = new ArrayList<>();

    public BaseSpell(int recoveryTime) {
        this.state = new ChargedState();
        this.recoveryTime = recoveryTime;
    }

    public void setState(SpellState state) {
        this.state = state;
        notifyStateChange();
    }

    public void cast() {
        state.cast();
        activate();
    }

    public void activate() {
        System.out.println("Activating spell!");
    }

    public void addObserver(SpellStatusObserver observer) {
        observers.add(observer);
    }

    public void notifyStateChange() {
        for (SpellStatusObserver observer : observers) {
            observer.onSpellStatusChange(this, state.getClass().getSimpleName());
        }
    }
}