import collection.*;
import json_reader.Json;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Dragon> dragons = new ArrayList<>();
        Dragon dragon1 = new Dragon(1, "1", new Coordinates(1, 1), LocalDate.now(), 10l,
                Color.BLACK, DragonType.AIR, DragonCharacter.WISE, new DragonCave(10));
        Dragon dragon2 = new Dragon(1, "1", new Coordinates(1, 1), LocalDate.now(), 10l,
                Color.BLACK, DragonType.AIR, DragonCharacter.WISE, new DragonCave(10));

        dragons.add(dragon1);
        dragons.add(dragon2);

        Json json = new Json();

        json.writeJsonFile("./src/test.json", dragons);

        System.out.println(json.readJsonFile("./src/test.json"));


    }
}