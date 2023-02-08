package collection;

import dragon.Dragon;

import java.util.Comparator;

public class SortByAge implements Comparator<Dragon> {

    @Override
    public int compare(Dragon o1, Dragon o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() == o2.getAge()) {
            return 0;
        } else {
            return -1;
        }
    }
}
