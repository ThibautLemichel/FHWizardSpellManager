public class RecoveryState implements SpellState {
    private int recoveryTime;

    public RecoveryState(int recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public void cast() {
        System.out.println("The spell is recovering and cannot be cast again for " + recoveryTime + " ms.");
    }
}
