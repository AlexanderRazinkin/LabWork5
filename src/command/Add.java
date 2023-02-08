package command;

import collection.DragonCollection;
import collection.SortByAge;

public class Add extends Command{
    @Override
    public void execute(DragonCollection collection) {
        collection.getDragonArray().add(getNewDragon());
        collection.getDragonArray().sort(new SortByAge());
    }
}
