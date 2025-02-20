public class main {
    public static void main(String[] args) {
        SpellBook spellBook = SpellBook.getInstance();
        Wizard wizard = new Wizard();

        SpellLogger logger = new SpellLogger();
        spellBook.addObserver(logger);

        Spell fireSpell = SpellFactory.createSpell("FireBall");
        Spell necromancerSpell = SpellFactory.createSpell("Necromancer");

        spellBook.addSpell(fireSpell);
        spellBook.addSpell(necromancerSpell);

        Command fireCommand = new CastSpellCommand(fireSpell);
        Command necromancerCommand = new CastSpellCommand(necromancerSpell);

        wizard.castSpell(fireCommand);
        wizard.castSpell(necromancerCommand);

        wizard.undoLastSpell();
    }
}
