package user;

import collection.DragonCollection;
import command.*;
import dragon.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserRequest {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isWorking = true;


    public static void requestCommand(DragonCollection collection) {
        scanner = new Scanner(System.in);
        System.out.print("Введите команду: ");
        String userAnswer = scanner.nextLine().strip();

        while (userAnswer.contains("  "))
            userAnswer = userAnswer.replaceAll("  ", " ");

        String[] inputArguments = userAnswer.split(" ");
        Command command;
        String commandArgument;

        if (!Command.getcommandList().contains(inputArguments[0]) || inputArguments.length > 2) {
            System.out.println("Такой команды не существует! Для ознакомления с командами используйте команду help!");
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
                    isWorking = false;
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
                    UserRequest.checkArguments(collection, inputArguments, new ExecuteScript(), 2);
            }
        }
    }

    public static ArrayList<Object> createNewDragon() {

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

    public static void checkArguments(DragonCollection collection, String[] inputArguments, Command command,
                                       int argumentCount) {
        if (inputArguments.length == argumentCount)
            if (argumentCount == 1)
                command.execute(collection);
            else if (argumentCount == 2)
                command.execute(collection, inputArguments[1]);
            else {
                if (argumentCount == 1)
                    System.out.println("Данная команда не имеет аргументов!");
                else if (argumentCount == 2)
                    System.out.println("Данная команда имеет один аргумент!");
            }
    }

}
