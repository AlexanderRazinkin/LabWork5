package command;

import collection.DragonCollection;

public class Clear extends Command {
    public void execute(DragonCollection collection) {
        collection.getDragonArray().clear();
        System.out.println("Коллекция успешно очищена!");
    }
}
