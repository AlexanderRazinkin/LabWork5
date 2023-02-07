import dragon.Dragon;

import java.time.LocalDate;
import java.util.ArrayList;

public class DragonCollection {

    private ArrayList<Dragon> dragonArray;
    private LocalDate creationDate;

    public DragonCollection(ArrayList<Dragon> dragonArray) {
        this.dragonArray = dragonArray;
        creationDate = LocalDate.now();
    }

    public ArrayList<Dragon> getDragonArray() {
        return dragonArray;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
