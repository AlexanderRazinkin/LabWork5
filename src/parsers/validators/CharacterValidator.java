package parsers.validators;

import dragon.DragonCharacter;

import java.util.ArrayList;

public class CharacterValidator extends Validator {

    private ArrayList<String> characterStringList;

    @Override
    public boolean isValid(Object value) {

        initCharacterStringList();

        if (value == null) {
            System.out.println("Проверьте, есть ли в файле поле type!");
            return false;
        } else {
            if (value instanceof String) {
                if (characterStringList.contains((String) value) || ((String) value).equals("")) {
                    return true;
                } else {
                    System.out.println("Поле type может хранить пустую строку \"\" или одно их следующиъ значений:");
                    System.out.println(characterStringList);
                    return false;
                }
            } else {
                System.out.println("Поле type может хранить пустую строку \"\" или одно их следующиъ значений:");
                System.out.println(characterStringList);
                return false;
            }
        }
    }

    private void initCharacterStringList() {

        characterStringList = new ArrayList<>();

        for (DragonCharacter character : DragonCharacter.values()) {
            characterStringList.add(character.toString());
        }
    }

}
