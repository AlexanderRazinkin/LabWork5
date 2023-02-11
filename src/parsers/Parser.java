package parsers;

import dragon.Dragon;

import java.util.ArrayList;

public interface Parser {

    void write(String filePath, ArrayList<Dragon> dragonList);

    ArrayList<Dragon> read(String filePath);
}
