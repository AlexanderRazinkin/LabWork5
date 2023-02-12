package command;

import collection.DragonCollection;
import dragon.Dragon;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            Dragon newDragon = Dragon.getNewDragon();

            for (int i = 0; i < getDragonCollection().getDragonArray().size(); i++) {
                if (getDragonCollection().getDragonArray().get(i).getAge() > newDragon.getAge()) {
                    for (int j = getDragonCollection().getDragonArray().size() - 1; j >= i; j--) {
                        getDragonCollection().getDragonArray().remove(j);
                    }
                    return;
                }
            }
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда remove_greater не имеет аргументов!");
            return false;
        }
    }

}
