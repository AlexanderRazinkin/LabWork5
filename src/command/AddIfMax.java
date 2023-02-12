package command;

import collection.DragonCollection;
import collection.SortByAge;
import dragon.Dragon;

public class AddIfMax extends Command {

    public AddIfMax() {
        super(false);
    }

    @Override
    public void execute() {
        Dragon dragon = Dragon.getNewDragon();

        if (dragon.getAge() > CommandManager.getDragonCollection().getDragonArray().get(-1).getAge()) {
            CommandManager.getDragonCollection().getDragonArray().add(dragon);
            CommandManager.getDragonCollection().getDragonArray().sort(new SortByAge());
            System.out.println("Элемент успешно добавлен в текущую коллекцию!");
        } else {
            System.out.println("Элемент не добавлен в текущую коллекцию!");
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда add_if_max не имеет аргументов!");
            return false;
        }
    }

}
