package command;

import collection.DragonCollection;
import parsers.JsonParser;

public class Save extends Command {

    public void execute(DragonCollection collection) {
        JsonParser json = new JsonParser();
        json.write(collection.getStartFilePath(), collection.getDragonArray());
    }

}
