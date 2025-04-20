package main;

import java.util.ArrayList;
import java.util.List;

//
abstract class BaseSpell implements Spell {
    protected SpellState state;
    protected int recoveryTime;
    private List<SpellStatusObserver> observers = new ArrayList<>();

    /**
     * Constructor for main.BaseSpell
     * @param recoveryTime
     */
    public BaseSpell(int recoveryTime) {
        this.state = new ChargedState();
        this.recoveryTime = recoveryTime;
    }

    /**
     * Gets the state of the spell
     * @param state
     */
    public void setState(SpellState state) {
        this.state = state;
        notifyStateChange();
    }

    /**
     * Casts the spell
     */
    public void cast() {
        state.cast();
        activate();
    }

    /**
     * Activates the spell
     */
    public void activate() {
        System.out.println("Activating spell!");
    }

    /**
     * Adds an observer to the spell
     * @param observer
     */
    public void addObserver(SpellStatusObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the spell
     */
    public void notifyStateChange() {
        for (SpellStatusObserver observer : observers) {
            observer.onSpellStatusChange(this, state.getClass().getSimpleName());
        }
    }
}