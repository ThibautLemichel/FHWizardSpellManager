package main;

import java.util.Timer;
import java.util.TimerTask;

public class FireBallSpell extends BaseSpell implements TimedSpell{
    public FireBallSpell() {
        super(5000);
    }

    /**
     * Activates the spell
     */
    public void activate() {
        System.out.println("Fire spell is active for 5 seconds...");
        startTimer();
    }

    /**
     * Starts the timer for the spell
     */
    public void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("Fire spell has expired!");
                setState(new RecoveryState(recoveryTime));
            }
        }, 5000);
    }
}
