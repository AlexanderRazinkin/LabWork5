package json_reader;

import dragon.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Json {

    public void writeJsonFile(String filePath, ArrayList<Dragon> dragonList) {

        JSONArray dragonArray = new JSONArray();

        for (Dragon dragon : dragonList) {
            JSONObject obj = new JSONObject();

            obj.put("name", dragon.getName());

            JSONArray coordinatesArray = new JSONArray();
            coordinatesArray.add(dragon.getCoordinates().getX());
            coordinatesArray.add(dragon.getCoordinates().getY());
            obj.put("coordinates", coordinatesArray);

            obj.put("creationDate", dragon.getCreationDate().toString());

            obj.put("age", dragon.getAge());

            obj.put("color", dragon.getColor().toString());

            obj.put("type", dragon.getType().toString());

            obj.put("character", dragon.getCharacter().toString());

            obj.put("depth", dragon.getCave().getDepth());

            dragonArray.add(obj);
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath))) {
            for (char c : dragonArray.toJSONString().toCharArray()) {
                writer.write((byte) c);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Dragon> readJsonFile(String filePath) {

        ArrayList<Dragon> dragonList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath))) {
            JSONArray dragonArray = (JSONArray) parser.parse(reader);

            for (int i = 0; i < dragonArray.size(); i++) {
                JSONObject obj = (JSONObject) dragonArray.get(i);

                String name = (String) obj.get("name");

                JSONArray coordinatesArray = (JSONArray) obj.get("coordinates");
                double x = (Double) coordinatesArray.get(0);
                Long y = (Long) coordinatesArray.get(1);
                Coordinates coordinates = new Coordinates((float) x, (int) y.longValue());

                LocalDate creationDate = LocalDate.parse((String) obj.get("creationDate"));

                Long age = (Long) obj.get("age");

                Color color = Color.valueOf((String) obj.get("color"));

                DragonType type = DragonType.valueOf((String) obj.get("type"));

                DragonCharacter character = DragonCharacter.valueOf((String) obj.get("character"));

                Long depth = (Long) obj.get("depth");
                DragonCave cave = new DragonCave((int) depth.longValue());

                Dragon dragon = new Dragon(i + 1, name, coordinates, creationDate, age, color, type,
                        character, cave);

                dragonList.add(dragon);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return dragonList;
    }
}
