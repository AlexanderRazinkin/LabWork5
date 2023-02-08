package command;

import collection.DragonCollection;
import dragon.Dragon;

public class Show extends Command {

    @Override
    public void execute(DragonCollection collection) {
        if (collection.getDragonArray().isEmpty()) {
            System.out.println("Текущая коллекция пуста!");
        } else {
            for (Dragon dragon : collection.getDragonArray()) {
                System.out.println(dragon);
            }
        }
    }
}
