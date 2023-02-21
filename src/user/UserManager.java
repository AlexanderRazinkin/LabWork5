package user;

import command.Command;
import command.CommandManager;
import dragon.*;
import parsers.validators.Validator;
import parsers.validators.ValidatorManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserManager {

    private static HashMap<String, Command> commandMap;
    private static Scanner scanner;
    private static boolean isWorking;

    static {
        commandMap = CommandManager.getCommandMap();
        scanner = new Scanner(System.in);
        isWorking = true;
    }

    public static void requestCommand() {
        scanner = new Scanner(System.in);
        System.out.print("Введите команду: ");
        String UserManager = scanner.nextLine().strip();

        while (UserManager.contains("  "))
            UserManager = UserManager.replaceAll("  ", " ");

        String[] commandAndArgument = UserManager.split(" ");
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
            commandMap.get(commandAndArgument[0]).setArgument(argument);
            commandMap.get(commandAndArgument[0]).execute();
        } else {
            System.out.println("Команды " + commandAndArgument[0] + " не существует!" +
                    " Для уточнения команд воспользуйтесь командрй help!");
        }
    }

    public static Map<String, Object> createNewDragonByUser() {

        Map<String, Validator> FieldAndValidator = ValidatorManager.getFieldAndValidator();
        Map<String, Object> dragonCharacteristics = new HashMap<>();
        String userAnswer = "";

        String name = "";
        do {
            try {
                System.out.print("Введите ненулевое имя: ");
                userAnswer = scanner.nextLine().strip();
                if (!isNull(userAnswer))
                    name = userAnswer;
            } catch (NoSuchElementException e) {
                isWorking = false;
            }
        } while (!FieldAndValidator.get("name").isValid(name));
        dragonCharacteristics.put("name", name);

        Float x = null;
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Введите координату x в типе данных float (в качестве разделения целой и дробной" +
                        " частей используйте запятую): ");
                userAnswer = scanner.nextLine().strip();
                if (!checkNull(userAnswer) && !isNull(userAnswer))
                    x = Float.parseFloat(userAnswer);
            } catch (NumberFormatException e) {
            } catch (NoSuchElementException e) {
                isWorking = false;
            }
        } while (x == null || !FieldAndValidator.get("x").isValid(Double.valueOf(x)));
        dragonCharacteristics.put("x", (float) x.doubleValue());

        Integer y = null;
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Введите координату y в типе данных int: ");
                userAnswer = scanner.nextLine().strip();
                if (!checkNull(userAnswer) && !isNull(userAnswer))
                    y = Integer.parseInt(userAnswer);
            } catch (NumberFormatException e) {
            } catch (NoSuchElementException e) {
                isWorking = false;
            }
        } while (y == null || !FieldAndValidator.get("y").isValid(Long.valueOf(y)));
        dragonCharacteristics.put("y", (int) y.longValue());

        Coordinates coordinates = new Coordinates((float) x.doubleValue(), (int) y.longValue());
        dragonCharacteristics.put("coordinates", coordinates);

        LocalDate creationDate = LocalDate.now();
        dragonCharacteristics.put("creationDate", creationDate);

        Long age = null;
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Введите возраст в типе данных long: ");
                userAnswer = scanner.nextLine().strip();
                if (!checkNull(userAnswer) && !isNull(userAnswer))
                    age = Long.parseLong(userAnswer);
            } catch (NumberFormatException e) {
            } catch (NoSuchElementException e) {
                isWorking = false;
            }
        } while (age == null || !FieldAndValidator.get("age").isValid(age));
        dragonCharacteristics.put("age", age);

        Color color;
        do {
            color = (Color) requestEnum(Color.values(), "цвет");
        } while (color == null);
        dragonCharacteristics.put("color", color);

        DragonType type;
        do {
            type = (DragonType) requestEnum(DragonType.values(), "тип");
        } while (type == null);
        dragonCharacteristics.put("type", type);

        DragonCharacter character = null;
        do {
            character = (DragonCharacter) requestEnum(DragonCharacter.values(), "характер");
        } while (type == null);
        dragonCharacteristics.put("character", character == DragonCharacter.NULL ? null : character);


        Integer depth = 0;
        do {
            try {
                scanner = new Scanner(System.in);
                System.out.print("Введите глубину пещеры в типе данных int либо пустую строку: ");
                userAnswer = scanner.nextLine().strip();
                if (isNull(userAnswer))
                    continue;
                if (!userAnswer.equals(""))
                    depth = Integer.parseInt(scanner.nextLine().strip());
                else
                    break;
            } catch (NumberFormatException e) {
            } catch (NoSuchElementException) {
                isWorking = false;
            }
        } while (!FieldAndValidator.get("depth").isValid(Long.valueOf(depth)));
        dragonCharacteristics.put("cave", userAnswer.equals("") ? null : new DragonCave(depth));

        return dragonCharacteristics;
    }

    private static Object requestEnum(Object[] values, String name) {
        scanner = new Scanner(System.in);
        int i = 0;
        boolean isCharacter = values[0] instanceof DragonCharacter;

        System.out.println("Выберите " + name + ":");
        for (Object value : values) {
            System.out.println("\t" + ++i + "-" + value.toString());
        }

        int userAnswer = 0;

        do {
            try {
                System.out.print("Введите целое число от 1 до " + values.length + ": ");
                userAnswer = Integer.parseInt(scanner.nextLine().strip());
            } catch (NumberFormatException e) {
                System.out.println("Требуется ввести целое число!");
            } catch (NoSuchElementException e) {
                isWorking = false;
            }
        } while (userAnswer == 0);

        if (userAnswer >= 1 && userAnswer <= i) {
            return values[userAnswer - 1];
        } else {
            System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
            return null;
        }

    }

    private static boolean checkNull(String userAnswer) {
        if (userAnswer.equals("")) {
            System.out.println("Поле не может быть null!");
            return true;
        } else
            return false;
    }

    private static boolean isNull(String userAnswer) {
        if (userAnswer.equals("null")) {
            System.out.println("Для ввода null-значения введите пустую строку!");
            return true;
        } else
            return false;
    }

    public static void setIsWorking(boolean isWorking) {
        UserManager.isWorking = isWorking;
    }

    public static boolean isWorking() {
        return isWorking;
    }
}

