import collection.DragonCollection;
import command.Command;
import command.CommandManager;
import parsers.JsonParser;
import user.UserRequest;

public class Programm {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Требуется ввести один аргумент - ссылку на файл формата json!");
            return;
        }

        JsonParser jsonParser = new JsonParser();
        DragonCollection collection = new DragonCollection(jsonParser.read(args[0]), args[0]);
        CommandManager.setDragonCollection(collection);

        while (UserRequest.isWorking()) {
            UserRequest.requestCommand();
        }


    }
}