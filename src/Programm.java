import collection.DragonCollection;
import command.Command;
import json_reader.Json;
import user.UserRequest;

public class Programm {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Требуется ввести один аргумент - ссылку на файл формата json!");
            return;
        }

        DragonCollection collection = new DragonCollection(Json.readJsonFile(args[0]), args[0]);
        Command command = new Command();

        while (UserRequest.isIsWorking()) {
            UserRequest.requestCommand(collection);
        }
    }
}