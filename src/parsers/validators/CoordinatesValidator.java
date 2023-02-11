package parsers.validators;

import org.json.simple.JSONArray;


public class CoordinatesValidator extends Validator {

    @Override
    public boolean isValid(Object value) {

        if (value == null) {
            System.out.println("Проверьте, есть ли в файле массив coordinates!");
            return false;
        } else {
            if (value instanceof JSONArray) {
                if (((JSONArray) value).size() == 2) {
                    return true;
                } else {
                    System.out.println("Количество элементов массива coordinates должно равняться 2!");
                    return false;
                }
            } else {
                System.out.println("coordinates должно быть массивом!");
                return false;
            }

        }

    }
}
