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
        String[] inputArguments = scanner.nextLine().split(" ");
        
        Command command;
        String commandArgument;
        
        if (!Command.getcommandList().contains(inputArguments[0]) || inputArguments.length > 2) {
            System.out.println("Такой команды не существует! Вводите команду без лишних пробелов в начале!" +
                    " А также учтите, что между словами ставится ОДИН пробел!");
            return;
        } else {
            switch (inputArguments[0]) {
                case "help":
                    checkArguments(collection, inputArguments, new Help(), 1);
                    break;
                case "info":
                    checkArguments(collection, inputArguments, new Info(), 1);
                    break;
                case "show":
                    checkArguments(collection, inputArguments, new Show(), 1);
                    break;
                case "add":
                    checkArguments(collection, inputArguments, new Add(), 1);
                    break;
                case "exit":
                    checkArguments(collection, inputArguments, new Exit(), 1);
                    isWorking = false;
                    break;
                case "clear":
                    checkArguments(collection, inputArguments, new Clear(), 1);
                    break;
                case "save":
                    checkArguments(collection, inputArguments, new Save(), 1);
                    break;
                case "add_if_max":
                    checkArguments(collection, inputArguments, new AddIfMax(), 1);
                    break;
                case "remove_greater":
                    checkArguments(collection, inputArguments, new RemoveGreater(), 1);
                    break;
                case "sum_of_age":
                    checkArguments(collection, inputArguments, new SumOfAge(), 1);
                    break;
                case "print_field_ascending_color":
                    checkArguments(collection, inputArguments, new PrintFieldAscendingColor(), 1);
                    break;
                case "remove_by_id":
                    checkArguments(collection, inputArguments, new RemoveById(), 2);
                    break;
                case "remove_at":
                    checkArguments(collection, inputArguments, new RemoveAtIndex(), 2);
                    break;
                case "remove_any_by_type":
                    checkArguments(collection, inputArguments, new RemoveAnyByType(), 2);
            }
        }
    }

    public static ArrayList<Object> createNewDragon() {

        ArrayList<Object> characteristics = new ArrayList<>();

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
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
                break;
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
                break;
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
        do {
            character = (DragonCharacter) requestEnum(DragonCharacter.values(), "характер");
        } while (character == null);
        characteristics.add(character);

        Integer depth;
        while (true) {
            try {
                System.out.print("Введите глубину пещеры в типе данных int: ");
                depth = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Требуется ввести число в типе данных int!");
                scanner.next();
            }
        }
        DragonCave cave = new DragonCave(depth);
        characteristics.add(cave);
        return characteristics;
    }

    private static Object requestEnum(Object[] values, String name) {

        int i = 0;

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
            System.out.println("Трубуется ввести целое число от 1 до" + values.length + "!");
            return null;
        }
    }

    public static boolean isIsWorking() {
        return isWorking;
    }

    private static void checkArguments(DragonCollection collection, String[] inputArguments, Command command, 
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
