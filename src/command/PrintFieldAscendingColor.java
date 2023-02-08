package command;

import collection.DragonCollection;
import dragon.Color;
import dragon.Dragon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PrintFieldAscendingColor extends Command {

    public void execute(DragonCollection collection) {

        HashMap<Color, Integer> colorCount = new HashMap<>();

        for (Dragon dragon : collection.getDragonArray()) {
            if (colorCount.get(dragon.getColor()) == null)
                colorCount.put(dragon.getColor(), 1);
            else
                colorCount.put(dragon.getColor(), colorCount.get(dragon.getColor()) + 1);
        }
    }
}
