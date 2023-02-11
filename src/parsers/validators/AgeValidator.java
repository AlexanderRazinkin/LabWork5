package parsers.validators;

import org.json.simple.JSONArray;

public class AgeValidator extends Validator {

    @Override
    public boolean isValid(Object value) {

        if (value == null) {
            System.out.println("Проверьте, есть ли в файле поле age!");
            return false;
        } else {
            if (value instanceof Long) {
                if (((Long) value) > 0) {
                    return true;
                } else {
                    System.out.println("Значение поля age должно быть больше 0");
                    return false;
                }
            } else {
                System.out.println("Поле age должно хранить значения типа данных long!");
                return false;
            }
        }

    }
}
