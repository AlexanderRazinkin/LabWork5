package parsers.validators;

public class NameValidator extends Validator {

    @Override
    public boolean isValid(Object value) {

        if (value == null) {
            System.out.println("Проверьте, есть ли файле поле name!");
            return false;
        } else {
            if (value instanceof String) {
                if (!(((String) value).equals(""))) {
                    return true;
                } else {
                    System.out.println("Поле name не может быть пустой строкой - присвойте ему значение!");
                    return false;
                }
            } else {
                System.out.println("Поле name должно хранить значение типа данных String!");
                return false;
            }
        }

    }
}
