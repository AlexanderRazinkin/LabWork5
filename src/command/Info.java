package command;

import collection.DragonCollection;

public class Info extends Command {

    public void execute(DragonCollection collection) {
        System.out.println("Тип коллекции: " + collection.getDragonArray().getClass().toString());
        System.out.println("Дата инициализации: " + collection.getCreationDate().toString());
        System.out.println("Количество элементов: " + collection.getDragonArray().size());
    }

}
