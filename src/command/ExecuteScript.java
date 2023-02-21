package command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ExecuteScript extends Command {

    private HashMap<String, Command> commandMap;
    private ArrayList<String> filePaths;


    public ExecuteScript() {
        super(true);
        this.commandMap = CommandManager.getCommandMap();
        this.filePaths = new ArrayList<>();
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            filePaths.add((String) getArgument());
            ArrayList<String> operationList = new ArrayList<>();

            try (InputStreamReader reader = new InputStreamReader(new FileInputStream((String) getArgument()))) {

                String command = "";
                int c;
                while (true) {

                    c = reader.read();

                    if (c != 10 && c != -1)
                        command += (Character.valueOf((char) c)).toString();
                    else if (c == 10) {
                        operationList.add(command);
                        command = "";
                    } else
                        break;
                }
                operationList.add(command);
            } catch (
                    FileNotFoundException e) {
                System.out.println("Файла по указанному пути не существует или отсутствуют права на чтение!");
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            for (String operation : operationList) {
                while (operation.contains("  "))
                    operation = operation.replaceAll("  ", " ");

                String[] commandAndArgument = operation.split(" ");
                String command = commandAndArgument[0];
                String argument;

                if (commandAndArgument.length == 1)
                    argument = null;
                else if (commandAndArgument.length == 2)
                    argument = commandAndArgument[1];
                else {
                    System.out.println("Требуется ввести *команда* *аргумент* (при его наличии)!");
                    return;
                }

                if (commandMap.containsKey(commandAndArgument[0])) {
                    if (commandAndArgument[0].equals("execute_script")) {
                        if (filePaths.contains(commandAndArgument[1])) {
                            System.out.println("Команда execute_script не выполняется, чтобы не допустить рекурсию!");
                            continue;
                        }
                    }
                    commandMap.get(commandAndArgument[0]).setArgument(argument);
                    commandMap.get(commandAndArgument[0]).execute();
                } else {
                    System.out.println("Команды " + commandAndArgument[0] + " не существует!" +
                            " Для уточнения команд воспользуйтесь командой help!");
                }
            }
            filePaths.remove(getArgument());
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда execute_script имеет аргумент - ссылку на файл!");
            return false;
        } else if (inputArgument instanceof String) {
            return true;
        }
        return false;
    }

}
