package parsers.validators;

import dragon.Color;

import java.util.ArrayList;

public class ColorValidator extends Validator {

    private ArrayList<String> colorStringList;

    @Override
    public boolean isValid(Object value) {

        initColorStringList();

        if (value == null) {
            System.out.println("Проверьте, есть ли в файле поле color!");
            return false;
        } else {
            if (value instanceof String) {
                if (colorStringList.contains((String) value)) {
                    return true;
                } else {
                    System.out.println("Поле color может хранить только следующие значения:");
                    System.out.println(colorStringList);
                    return false;
                }
            } else {
                System.out.println("Поле color должно хранить одно из следюущих значений:");
                System.out.println(colorStringList);
                return false;
            }
        }
    }

    private void initColorStringList() {

        colorStringList = new ArrayList<>();

        for (Color color : Color.values()) {
            colorStringList.add(color.toString());
        }
    }
}
