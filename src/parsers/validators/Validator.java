package parsers.validators;

/**
 * Для проверки корректности ввода значений полей объектов созданы валидаторы
 */
public abstract class Validator {
    public abstract boolean isValid(Object value);
}
