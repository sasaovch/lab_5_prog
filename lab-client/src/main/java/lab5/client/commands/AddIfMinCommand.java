package lab5.client.commands;

import java.io.IOException;

import lab5.client.data.SpaceMarine;
import lab5.client.exceptions.IncorrectData;
import lab5.client.exceptions.IncorrectDataOfFileException;
import lab5.client.utility.AskMarine;
import lab5.client.utility.IOManager;
import lab5.client.utility.SpaceMarineCollection;


/**
 * Command 'add_if_min'. Adds a new element to collection if it's less than the minimal one.
 */
public class AddIfMinCommand extends Command {
    private final SpaceMarineCollection spaceMarineCollection;
    private final AskMarine asker;
    private final IOManager ioManager;

    public AddIfMinCommand(SpaceMarineCollection spaceMarineCollection, AskMarine asker, IOManager ioManager) {
        super("add_if_min {element}", "add element if its value is less than minimal value in collection (value is health)"); //replace it's with its
        this.spaceMarineCollection = spaceMarineCollection;
        this.asker = asker;
        this.ioManager = ioManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     * @throws IOException When something with file went wrong.
     * @throws IncorrectDataOfFileException When in file data isn't correct.
     * @throws IncorrectData
     */
    @Override
    public boolean run(String str) throws IOException, IncorrectDataOfFileException, IncorrectData {
        if (!str.isEmpty()) {
            ioManager.printerr("Incorrect input. Right: '" + name + "'.");
            return false;
        }
        SpaceMarine newSpaceMarine = asker.askMarine();
        SpaceMarine minSpaceMarine = null;
        if (spaceMarineCollection.getCollection().size() == 0) {
            spaceMarineCollection.addElement(newSpaceMarine);
            ioManager.println(newSpaceMarine.getName() + "has been successfully added.");
            return true;
        }
        for (SpaceMarine thatSpaceMarine : spaceMarineCollection.getCollection()) {
            if (minSpaceMarine == null) {
                minSpaceMarine = thatSpaceMarine;
                continue;
            }
            if (thatSpaceMarine.compareTo(minSpaceMarine) < 0) {
                minSpaceMarine = thatSpaceMarine;
            }
        }
        if (newSpaceMarine.compareTo(minSpaceMarine) < 0) {
            spaceMarineCollection.addElement(newSpaceMarine);
            ioManager.println(newSpaceMarine.getName() + "has been successfully added.");
        } else {
            ioManager.println("Element is bigger than minimal.");
        }
        return true;
    }
}