package command;

import collection.DragonCollection;
import json_reader.Json;

public class Save extends Command {

    public void execute(DragonCollection collection) {
        Json.writeJsonFile(collection.getStartFilePath(), collection.getDragonArray());
    }

}
