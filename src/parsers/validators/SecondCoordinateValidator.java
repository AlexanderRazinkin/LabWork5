package parsers.validators;

public class SecondCoordinateValidator extends Validator {

    @Override
    public boolean isValid(Object value) {

        if (value instanceof Long && (Long) value >= -323 && (Long) value <= Integer.MAX_VALUE)
            return true;
        else {
            System.out.println("Первая координата массива coordinates должна хранить значение типа данных int, " +
                    "а также не меньше -323 и не больше " + Integer.MAX_VALUE);
            return false;
        }
    }

}

