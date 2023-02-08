package collection;

import dragon.Dragon;

import java.time.LocalDate;
import java.util.ArrayList;

public class DragonCollection {

    private ArrayList<Dragon> dragonArray;
    private LocalDate creationDate;
    private String startFilePath;


    public DragonCollection(ArrayList<Dragon> dragonArray, String startFilePath) {
        this.dragonArray = dragonArray;
        this.startFilePath = startFilePath;
        creationDate = LocalDate.now();
    }


    public ArrayList<Dragon> getDragonArray() {
        return dragonArray;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getStartFilePath() {
        return startFilePath;
    }

}
