package command;

import collection.DragonCollection;

public class RemoveAtIndex extends Command {

    @Override
    public void execute(DragonCollection collection, String argument) {

        int index = 0;

        try {
            index = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("В качестве аргумента данная команда принимает целое число!");
            return;
        }

        if (index >= 0 && index < collection.getDragonArray().size()) {
            collection.getDragonArray().remove(index);
            System.out.println("Элемент с индексом=" + index + " успешно удален из текущей коллекции!");
        } else {
            System.out.println("Элемента с таким индексом нет в текущей коллекции");
        }
    }
}
