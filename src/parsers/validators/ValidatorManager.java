package parsers.validators;

import java.util.HashMap;

public class ValidatorManager {
    public static HashMap<String, Validator> getFieldAndValidator() {

        HashMap<String, Validator> fieldNames = new HashMap<>();

        fieldNames.put("name", new NameValidator());
        fieldNames.put("coordinates", new CoordinatesValidator());
        fieldNames.put("x", new FirstCoordinateValidator());
        fieldNames.put("y", new SecondCoordinateValidator());
        fieldNames.put("age", new AgeValidator());
        fieldNames.put("color", new ColorValidator());
        fieldNames.put("type", new TypeValidator());
        fieldNames.put("character", new CharacterValidator());
        fieldNames.put("depth", new DepthValidator());

        return fieldNames;
    }
}
