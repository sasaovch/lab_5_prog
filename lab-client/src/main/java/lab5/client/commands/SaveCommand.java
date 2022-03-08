package lab5.client.commands;

import java.io.File;
import java.io.IOException;

import lab5.client.utility.IOManager;
import lab5.client.utility.ParsingJSON;
import lab5.client.utility.SpaceMarineCollection;


/**
 * Command 'save'. Saves the collection to str file.
 */
public class SaveCommand extends Command {
    private final SpaceMarineCollection spaceMarineCollection;
    private final ParsingJSON pars;
    private final File file;
    private final IOManager ioManager;

    public SaveCommand(SpaceMarineCollection spaceMarineCollection, ParsingJSON pars, File file, IOManager ioManager) {
        super("save", "save collection in file");
        this.spaceMarineCollection = spaceMarineCollection;
        this.pars = pars;
        this.file = file;
        this.ioManager = ioManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     * @throws IOException
     */
    @Override
    public boolean run(String str) throws IOException {
        if (!str.isEmpty()) {
            ioManager.printerr("Incorrect input. Right: '" + name + "'.");
            return false;
        }
        if (!file.exists()) {
            ioManager.printerr("File isn't exist.");
            return false;
        }
        if (pars.serialize(spaceMarineCollection, file)) {
            ioManager.println("The collection has been saved.");
        } else {
            ioManager.println("The collection hasn't been saved.");
        }
        return true;
    }
}