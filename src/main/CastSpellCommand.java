package main;

public class CastSpellCommand implements Command {

    private Spell spell;

    /**
     * Constructor for main.CastSpellCommand
     * @param spell
     */
    public CastSpellCommand(Spell spell) {
        this.spell = spell;
    }

    /**
     * Executes the command
     */
    public void execute() {
        spell.cast();
    }

    /**
     * Undoes the command
     */
    public void undo() {
        System.out.println("Undoing spell... ("+ spell.getClass().getSimpleName()+ ")");
        spell.setState(new ChargedState());
        System.out.println("main.Spell is now inactive!");
    }
}
