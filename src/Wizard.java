import java.util.ArrayList;
import java.util.List;

public class Wizard {
    private List<Command> commandHistory = new ArrayList<>();

    /**
     * Casts a spell and adds it to the command history
     * @param command the spell to cast
     */
    public void castSpell(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    /**
     * Undoes the last spell cast
     */
    public void undoLastSpell() {
        if (!commandHistory.isEmpty()) {
            commandHistory.remove(commandHistory.size() - 1).undo();
        }
    }

    /**
     * Undoes all spells cast
     */
    public void undoAllSpell() {
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            commandHistory.get(i).undo();
        }
    }
}
