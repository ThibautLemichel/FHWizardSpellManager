public class NecromancerSpell extends BaseSpell {
    public NecromancerSpell() {
        super(10000);
    }

    public void activate() {
        System.out.println("Necromancer spell is active indefinitely until undone.");
        setState(new RecoveryState(recoveryTime));
    }
}
