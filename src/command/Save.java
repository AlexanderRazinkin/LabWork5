package command;

import collection.DragonCollection;
import parsers.JsonParser;

public class Save extends Command {

    public Save() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            JsonParser json = new JsonParser();
            json.write(CommandManager.getDragonCollection().getStartFilePath(), CommandManager.getDragonCollection().getDragonArray());
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда save не имеет аргументов!");
            return false;
        }
    }

}
