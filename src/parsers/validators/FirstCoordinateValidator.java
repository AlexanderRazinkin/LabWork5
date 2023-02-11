package parsers.validators;

public class FirstCoordinateValidator extends Validator {

    @Override
    public boolean isValid(Object value) {

        if (value instanceof Double && (Double) value >= Float.MIN_VALUE && (Double) value <= Float.MAX_VALUE) {
            return true;
        } else {
            System.out.println("Первая координата массива coordinates должна хранить значение типа данных float, " +
                    "а также не меньше " + Float.MIN_VALUE + " и не больше " + Float.MAX_VALUE);
            return false;
        }
    }

}
