package user;

import collection.DragonCollection;
import command.*;
import dragon.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserRequest {

    private static HashMap<String, Command> commandMap;
    private static Scanner scanner;
    private static boolean isWorking;

    static {
        commandMap = Command.getCommandMap();
        scanner = new Scanner(System.in);
        isWorking = true;
    }

    public static void requestCommand() {
        System.out.print("Введите команду: ");
        String userRequest = scanner.nextLine().strip();

        while (userRequest.contains("  "))
            userRequest = userRequest.replaceAll("  ", " ");

        String[] commandAndArgument = userRequest.split(" ");
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

    public static ArrayList<Object> createNewDragonByUser() {

        ArrayList<Object> characteristics = new ArrayList<>();

        String name;

        do {
            System.out.print("Введите ненулевое имя: ");
            name = scanner.nextLine().strip();
        } while (name.equals(""));
        characteristics.add(name);

        Float x;
        while (true) {
            try {
                System.out.print("Введите координату x в типе данных float: ");
                x = scanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных float!");
                scanner.next();
            }
        }
        Integer y;
        while (true) {
            try {
                System.out.print("Введите координату y в типе данных int: ");
                y = scanner.nextInt();
                if (y > -323)
                    break;
                else
                    System.out.println("Координата y должна быть больше -323");
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных int!");
                scanner.next();
            }
        }
        Coordinates coordinates = new Coordinates(x, y);
        characteristics.add(coordinates);

        LocalDate creationDate = LocalDate.now();
        characteristics.add(creationDate);

        Long age;
        while (true) {
            try {
                System.out.print("Введите возраст в типе данных long: ");
                age = scanner.nextLong();
                if (age > 0) {
                    break;
                } else {
                    System.out.println("Возраст должен быть больше 0!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных long!");
                scanner.next();
            }
        }
        characteristics.add(age);

        Color color;
        do {
            color = (Color) requestEnum(Color.values(), "цвет");
        } while (color == null);
        characteristics.add(color);

        DragonType type;
        do {
            type = (DragonType) requestEnum(DragonType.values(), "тип");
        } while (type == null);
        characteristics.add(type);

        DragonCharacter character;

        while (true) {
            scanner = new Scanner(System.in);
            System.out.println("Сделать характер null?");
            System.out.print("Введите ответ да/нет: ");
            String userAnswer = scanner.nextLine().strip();
            if (userAnswer.equals("да")) {
                character = null;
                break;
            } else if (userAnswer.equals(("нет"))) {
                character = (DragonCharacter) requestEnum(DragonCharacter.values(), "характер");
                break;
            } else {
                System.out.println("Требуется ввести да/нет!");
            }
        }

        characteristics.add(character);

        scanner = new Scanner(System.in);
        String depth;
        DragonCave cave;
        while (true) {
            System.out.print("Введите глубину пещеры в типе данных int: ");
            depth = scanner.nextLine().strip();
            if (depth.equals("")) {
                cave = null;
                break;
            } else {
                try {
                    cave = new DragonCave(Integer.parseInt(depth));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Требуется ввести целое число!");
                }
            }
        }

        characteristics.add(cave);
        return characteristics;
    }

    private static Object requestEnum(Object[] values, String name) {

        int i = 0;

        if (values[0] instanceof DragonCharacter)

            System.out.println("Выберите " + name + ":");
        for (Object value : values) {
            System.out.println("\t" + ++i + "-" + value.toString());
        }
        System.out.print("Введите целое число от 1 до " + values.length + ": ");
        int userAnswer;

        try {
            userAnswer = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Требуется ввести целое число!");
            return null;
        }

        if (userAnswer >= 1 && userAnswer <= i) {
            return values[userAnswer - 1];
        } else {
            System.out.println("Требуется ввести целое число от 1 до " + values.length + "!");
            return null;
        }

    }

    public static void setIsWorking(boolean isWorking) {
        UserRequest.isWorking = isWorking;
    }

    public static boolean isWorking() {
        return isWorking;
    }
}

