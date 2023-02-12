package command;

import collection.DragonCollection;

public class Info extends Command {

    public Info() {
        super(false);
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.out.println("Тип коллекции: " + getDragonCollection().getDragonArray().getClass().toString());
            System.out.println("Дата инициализации: " + getDragonCollection().getCreationDate().toString());
            System.out.println("Количество элементов: " + getDragonCollection().getDragonArray().size());
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Команда info не имеет аргументов!");
            return false;
        }
    }

}
