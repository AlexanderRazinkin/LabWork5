package command;

import collection.DragonCollection;

public class Exit extends Command {

    @Override
    public void execute(DragonCollection collection) {
        System.out.println("Завершение работы программы!");
    }
}
