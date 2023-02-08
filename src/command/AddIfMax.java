package command;

import collection.DragonCollection;
import collection.SortByAge;
import dragon.Dragon;

public class AddIfMax extends Command {

    public void execute(DragonCollection collection) {

        Dragon dragon = getNewDragon();

        if (dragon.getAge() > collection.getDragonArray().get(collection.getDragonArray().size() - 1).getAge()) {
            collection.getDragonArray().add(dragon);
            collection.getDragonArray().sort(new SortByAge());
            System.out.println("Элемент успешно добавлен в текущую коллекцию!");
        } else {
            System.out.println("Элемент не добавлен в текущую коллекцию!");
        }
    }
}
