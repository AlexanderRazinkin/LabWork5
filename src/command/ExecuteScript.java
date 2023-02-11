package command;

import collection.DragonCollection;
import user.UserRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExecuteScript extends Command {

    @Override
    public void execute(DragonCollection collection, String argument) {

        ArrayList<String> commandList = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(argument))) {

            String command = "";
            int c;
            while (true) {

                c = reader.read();

                if (c != 10 && c != -1)
                    command += (Character.valueOf((char) c)).toString();
                else if (c == 10) {
                    commandList.add(command);
                    command = "";
                } else
                    break;
            }
            commandList.add(command);
        } catch (
                FileNotFoundException e) {
            System.out.println("Файла по указаному пути не существует!");
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        for (String command : commandList) {
            String[] inputArguments = command.strip().split(" ");

            if (!Command.getcommandList().contains(inputArguments[0]) || inputArguments.length > 2) {
                System.out.printf("Команды %s не существует! Для ознакомления с командами используйте команду help!\n", command);
                return;
            } else {
                switch (inputArguments[0]) {
                    case "help":
                        UserRequest.checkArguments(collection, inputArguments, new Help(), 1);
                        break;
                    case "info":
                        UserRequest.checkArguments(collection, inputArguments, new Info(), 1);
                        break;
                    case "show":
                        UserRequest.checkArguments(collection, inputArguments, new Show(), 1);
                        break;
                    case "add":
                        UserRequest.checkArguments(collection, inputArguments, new Add(), 1);
                        break;
                    case "exit":
                        UserRequest.checkArguments(collection, inputArguments, new Exit(), 1);
                        UserRequest.setIsWorking(false);
                        break;
                    case "clear":
                        UserRequest.checkArguments(collection, inputArguments, new Clear(), 1);
                        break;
                    case "save":
                        UserRequest.checkArguments(collection, inputArguments, new Save(), 1);
                        break;
                    case "add_if_max":
                        UserRequest.checkArguments(collection, inputArguments, new AddIfMax(), 1);
                        break;
                    case "remove_greater":
                        UserRequest.checkArguments(collection, inputArguments, new RemoveGreater(), 1);
                        break;
                    case "sum_of_age":
                        UserRequest.checkArguments(collection, inputArguments, new SumOfAge(), 1);
                        break;
                    case "print_field_ascending_color":
                        UserRequest.checkArguments(collection, inputArguments, new PrintFieldAscendingColor(), 1);
                        break;
                    case "remove_by_id":
                        UserRequest.checkArguments(collection, inputArguments, new RemoveById(), 2);
                        break;
                    case "remove_at":
                        UserRequest.checkArguments(collection, inputArguments, new RemoveAtIndex(), 2);
                        break;
                    case "remove_any_by_type":
                        UserRequest.checkArguments(collection, inputArguments, new RemoveAnyByType(), 2);
                        break;
                    case "execute_script":
                        if (checkRecursion(argument, inputArguments[1]))
                            UserRequest.checkArguments(collection, inputArguments, new ExecuteScript(), 2);
                        else
                            System.out.println("Команда " + inputArguments[0] + " не выполняется, чтобы не " +
                                    "допустить рекурсию!") ;
                }
            }
        }
    }

    private boolean checkRecursion(String filePath, String inputFilePath) {
        if (filePath.equals(inputFilePath))
            return false;
        else
            return true;
    }


}
