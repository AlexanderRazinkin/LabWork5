package command;

import collection.DragonCollection;
import collection.SortByAge;
import dragon.Dragon;

public class Add extends Command{

    public Add() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            getDragonCollection().getDragonArray().add(Dragon.getNewDragon());
            getDragonCollection().getDragonArray().sort(new SortByAge());
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда add не имеет аргументов!");
            return false;
        }
    }

}
