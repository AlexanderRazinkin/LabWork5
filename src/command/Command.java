package command;

import collection.DragonCollection;

import java.util.HashMap;

public abstract class Command {

    private static DragonCollection dragonCollection;
    private final boolean hasArgument;
    private Object argument;
    private final static HashMap<String, Command> commandMap;

    static {
        commandMap = new HashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("add", new Add());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("exit", new Exit());
        commandMap.put("remove_at", new RemoveAtIndex());
        commandMap.put("add_if_max", new AddIfMax());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("remove_any_by_type", new RemoveAnyByType());
        commandMap.put("sum_of_age", new SumOfAge());
        commandMap.put("print_field_ascending_color", new PrintFieldAscendingColor());
        commandMap.put("execute_script", new ExecuteScript());
    }

    public Command(boolean hasArgument) {
        this.hasArgument = hasArgument;
    }

    public static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    public abstract void execute();

    public abstract boolean checkArgument(Object inputArgument);

    public static void setDragonCollection(DragonCollection dragonCollection) {
        Command.dragonCollection = dragonCollection;
    }

    public static DragonCollection getDragonCollection() {
        return Command.dragonCollection;
    }

    public boolean isHasArgument() {
        return hasArgument;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
