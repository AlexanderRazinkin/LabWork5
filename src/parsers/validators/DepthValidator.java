package parsers.validators;

public class DepthValidator extends Validator {

    @Override
    public boolean isValid(Object value) {

        if (value == null) {
            System.out.println("Проверьте, есть ли в файле поле depth!");
            return false;
        } else {
            if (value instanceof String) {
                if (((String) value).equals("")) {
                    return true;
                } else {
                    System.out.println("Если вы собираетесь создать null-объект класса Cave - впишите" +
                            " пустую строку \"\"!");
                    return false;
                }
            }
            else if (value instanceof Long) {
                if ((Long) value > 0 && (Long) value <= Integer.MAX_VALUE) {
                    return true;
                } else {
                    System.out.println("Значение поля depth должно быть больше 0 и не больше " + Integer.MAX_VALUE);
                    return false;
                }
            } else {
                System.out.println("Поле depth должно хранить значения типа данных int!");
                return false;
            }
        }

    }

}

