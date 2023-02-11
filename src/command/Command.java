package command;

import collection.DragonCollection;
import dragon.*;
import user.UserRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Command {

    private static ArrayList<String> commandList;

    static {
        commandList = new ArrayList<>();

        commandList.add("help");
        commandList.add("info");
        commandList.add("show");
        commandList.add("add");
        commandList.add("remove_by_id");
        commandList.add("clear");
        commandList.add("save");
        commandList.add("exit");
        commandList.add("remove_at");
        commandList.add("add_if_max");
        commandList.add("remove_greater");
        commandList.add("remove_any_by_type");
        commandList.add("sum_of_age");
        commandList.add("print_field_ascending_color");
        commandList.add("execute_script");
    }

    protected static Dragon getNewDragon() {
        ArrayList<Object> characteristics = UserRequest.createNewDragon();

        String name = (String) characteristics.get(0);
        Coordinates coordinates = (Coordinates) characteristics.get(1);
        LocalDate creationDate = (LocalDate) characteristics.get(2);
        Long age = (Long) characteristics.get(3);
        Color color = (Color) characteristics.get(4);
        DragonType type = (DragonType) characteristics.get(5);
        DragonCharacter character = (DragonCharacter) characteristics.get(6);
        DragonCave cave = (DragonCave) characteristics.get(7);

        return new Dragon(name, coordinates, creationDate, age, color, type, character, cave);
    }

    public static ArrayList<String> getcommandList() {
        return commandList;
    }

    public void execute(DragonCollection collection){}
    public void execute(DragonCollection collection, String argument){}

}
