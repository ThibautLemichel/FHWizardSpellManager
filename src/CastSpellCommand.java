public class CastSpellCommand implements Command {

    private Spell spell;

    public CastSpellCommand(Spell spell) {
        this.spell = spell;
    }

    public void execute() {
        spell.cast();
    }

    public void undo() {
        System.out.println("Undoing spell... ("+ spell.getClass().getSimpleName()+ ")");
        spell.setState(new ChargedState());
        System.out.println("Spell is now inactive!");
    }
}
