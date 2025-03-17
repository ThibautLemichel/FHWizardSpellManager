public interface Spell {
    void cast();
    void setState(SpellState state);
    void activate();
    void notifyStateChange();
}
