import java.util.ArrayList;
import java.util.List;

public class Wizard {
    private List<Command> commandHistory = new ArrayList<>();

    public void castSpell(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoLastSpell() {
        if (!commandHistory.isEmpty()) {
            commandHistory.remove(commandHistory.size() - 1).undo();
        }
    }
}
