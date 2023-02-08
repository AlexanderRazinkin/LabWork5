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

    public static void writeJsonFile(String filePath, ArrayList<Dragon> dragonList) {

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

    public static ArrayList<Dragon> readJsonFile(String filePath) {

        ArrayList<Dragon> dragonList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath))) {
            JSONArray dragonArray = (JSONArray) parser.parse(reader);

            for (int i = 0; i < dragonArray.size(); i++) {
                JSONObject obj = (JSONObject) dragonArray.get(i);

                String name = (String) obj.get("name");

                JSONArray coordinatesArray = (JSONArray) obj.get("coordinates");
                Double x = (Double) coordinatesArray.get(0);
                Long y = (Long) coordinatesArray.get(1);

                LocalDate creationDate = LocalDate.parse((String) obj.get("creationDate"));

                Long age = (Long) obj.get("age");

                Color color = Color.valueOf((String) obj.get("color"));

                DragonType type = DragonType.valueOf((String) obj.get("type"));

                DragonCharacter character = DragonCharacter.valueOf((String) obj.get("character"));

                Long depth = (Long) obj.get("depth");

                if (name == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"name\" в файле!");
                    continue;
                } else if (coordinatesArray == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"coordinates\" в файле!");
                    continue;
                } else if (x == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует значение \"x\" в файле!");
                    continue;
                } else if (y == 0) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует значение \"y\" в файле!");
                    continue;
                } else if (creationDate == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"creationDate\" в файле!");
                    continue;
                } else if (age == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"age\" в файле!");
                    continue;
                } else if (color == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"age\" в файле!");
                    continue;
                } else if (type == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"type\" в файле!");
                    continue;
                } else if (character == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"character\" в файле!");
                    continue;
                } else if (depth == null) {
                    System.out.println("У объекта-" + i + 1 + " отсутствует \"depth\" в файле!");
                    continue;
                }

                Dragon dragon = new Dragon(name, new Coordinates((float) x.doubleValue(), (int) y.longValue()),
                        creationDate, age, color, type, character, new DragonCave((int) depth.longValue()));

                dragonList.add(dragon);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файла по указаному пути не существует!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Файл поврежден! Перепроверьте корректность его заполнения!");
        }
        return dragonList;
    }
}
