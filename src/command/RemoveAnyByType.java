package command;

import collection.DragonCollection;
import dragon.Dragon;
import dragon.DragonType;

import java.util.ArrayList;

public class RemoveAnyByType extends Command {

    @Override
    public void execute(DragonCollection collection, String argument) {

        ArrayList<String> types = new ArrayList<>();

        for (DragonType type : DragonType.values()) {
            types.add(type.toString());
        }

        if (!types.contains(argument)) {
            System.out.println("Такого типа не существует! Требуется ввести один из следующего списка: ");
            for (String type : types)
                System.out.println(type);
        }

        ArrayList<Dragon> toDelete = new ArrayList<>();
        for (Dragon dragon : collection.getDragonArray()) {
            if (dragon.getType().toString().equals(argument)) {
                toDelete.add(dragon);
            }
        }
        for (Dragon dragon : toDelete) {
            collection.getDragonArray().remove(dragon);
        }
        System.out.println("Было успешно удалено элементов из текущей коллекции: " + toDelete.size());
    }
}
