package command;

import collection.DragonCollection;
import dragon.Dragon;

public class RemoveById extends Command {

    @Override
    public void execute(DragonCollection collection, String argument) {

        int id = 0;

        try {
            id = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("В качестве аргумента данная команда принимает целое число!");
            return;
        }

        for (Dragon dragon : collection.getDragonArray()) {
            if (dragon.getId() == id) {
                collection.getDragonArray().remove(dragon);
                System.out.println("Элемент с id=" + id + " успешно удален из текущей коллекции!");
                return;
            }
        }
        System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
    }
}
