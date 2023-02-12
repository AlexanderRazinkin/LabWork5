package dragon;

import user.UserRequest;

import java.time.LocalDate;
import java.util.ArrayList;

public class Dragon {

    private static int objCount = 1;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле не может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonCave cave; //Поле может быть null

    public Dragon(String name, Coordinates coordinates, LocalDate creationDate, Long age, Color color,
                  DragonType type, DragonCharacter character, DragonCave cave) {
        this.id = objCount++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }

    public static Dragon getNewDragon() {
        ArrayList<Object> characteristics = UserRequest.createNewDragonByUser();

        String name = (String) characteristics.get(0);
        Coordinates coordinates = (Coordinates) characteristics.get(1);
        LocalDate creationDate = (LocalDate) characteristics.get(2);
        Long age = (Long) characteristics.get(3);
        Color color = (Color) characteristics.get(4);
        DragonType type = (DragonType) characteristics.get(5);
        DragonCharacter character = (DragonCharacter) characteristics.get(6);
        DragonCave cave = (DragonCave) characteristics.get(7);

        return new Dragon(name, coordinates, creationDate, age, color, type, character, cave);
    }

    @Override
    public String toString() {
        return "Dragon{id='" + id + "', name='" + name + "', coordinates=['" + coordinates.getX() + "'; '" +
                coordinates.getY() + "'], creationDate='" + creationDate.toString() + "', age='" + age + "', " +
                "color='" + color.toString() + "', type='" + type.toString() + "', character='" +
                (character == null ? null : character.toString()) + "', cave='" + (cave == null ? null : cave.getDepth()) + "'}";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getAge() {
        return age;
    }

    public Color getColor() {
        return color;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public DragonCave getCave() {
        return cave;
    }
}

