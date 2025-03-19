// Factory Pattern: Creates Spell objects based on input
public class SpellFactory {
    public static Spell createSpell(String spellType) {
        switch (spellType) {
            case "FireBall": return new FireBallSpell();
            case "Necromancer": return new NecromancerSpell();
            default: throw new IllegalArgumentException("Unknown spell type");
        }
    }
}
