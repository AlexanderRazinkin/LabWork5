package parsers.validators;

import dragon.Color;
import dragon.DragonType;

import java.util.ArrayList;

public class TypeValidator extends Validator {

    private ArrayList<String> typeStringList;

    @Override
    public boolean isValid(Object value) {

        initTypeStringList();

        if (value == null) {
            System.out.println("Проверьте, есть ли в файле поле type!");
            return false;
        } else {
            if (value instanceof String) {
                if (typeStringList.contains((String) value)) {
                    return true;
                } else {
                    System.out.println("Поле type может хранить одно их следующиъ значений:");
                    System.out.println(typeStringList);
                    return false;
                }
            } else {
                System.out.println("Поле type может хранить одно их следующиъ значений:");
                System.out.println(typeStringList);
                return false;
            }
        }
    }

    private void initTypeStringList() {

        typeStringList = new ArrayList<>();

        for (DragonType type : DragonType.values()) {
            typeStringList.add(type.toString());
        }
    }
}
