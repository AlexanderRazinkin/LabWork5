package command;

import collection.DragonCollection;
import dragon.Dragon;

public class RemoveGreater extends Command {

    public void execute(DragonCollection collection) {

        Dragon newDragon = getNewDragon();

        for (int i = 0; i < collection.getDragonArray().size(); i++) {
            if (collection.getDragonArray().get(i).getAge() > newDragon.getAge()) {
                for (int j = collection.getDragonArray().size() - 1; j >= i; j--) {
                    collection.getDragonArray().remove(j);
                }
                return;
            }
        }

    }
}
