package command;

import collection.DragonCollection;
import dragon.Dragon;

public class SumOfAge extends Command {
    public void execute(DragonCollection collection) {

        long sum = 0;

        for (Dragon dragon : collection.getDragonArray()) {
            sum += dragon.getAge();
        }

        System.out.println("Суммарный возраст всех элементов текущей коллекции составляет: " + sum);
    }

}
