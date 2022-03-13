package lab5.client.commands;

import java.io.IOException;

import lab5.client.exceptions.IncorrectDataOfFileException;
import lab5.client.utility.AskMarine;
import lab5.client.utility.IOManager;
import lab5.client.utility.SpaceMarineCollection;


/**
 * Command 'remove_lower'. Removes elements lower than user entered.
 */
public class RemoveLowerCommand extends Command {
    private final SpaceMarineCollection spaceMarineCollection;
    private final AskMarine asker;
    private final IOManager ioManager;

    public RemoveLowerCommand(SpaceMarineCollection spaceMarineCollection, AskMarine asker, IOManager ioManager) {
        super("remove_lower {element}", "remove all items smaller than the specified one from the collection");
        this.spaceMarineCollection = spaceMarineCollection;
        this.asker = asker;
        this.ioManager = ioManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     * @throws IOException When something with file went wrong.
     * @throws IncorrectDataOfFileException When in file data isn't correct.
     */
    @Override
    public boolean run(String str) throws IOException, IncorrectDataOfFileException {
        if (!str.isEmpty()) {
            ioManager.printerr("Incorrect input. Right: '" + name + "'.");
            return false;
        }
        Integer specifiedHealth = asker.askHealth();
        if (spaceMarineCollection.removeIf(spMar -> spMar.getHealth() < specifiedHealth)) {
            ioManager.println("All items have been successfully deleted.");
        } else {
            ioManager.println("No element has been deleted.");
        }
        return true;
    }
}