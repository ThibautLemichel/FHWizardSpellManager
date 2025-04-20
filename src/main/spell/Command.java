package main.spell;

public interface Command {
    /**
     * Executes the command
     */
    void execute();

    /**
     * Undoes the command
     */
    void undo();
}
