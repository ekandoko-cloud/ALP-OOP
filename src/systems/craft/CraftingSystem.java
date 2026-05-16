package systems.craft;
import java.util.*;
import systems.inventory.Inventory;
public class CraftingSystem {
    private ArrayList<String> daftarResep;

    public CraftingSystem(ArrayList<String> daftarResep) {
        this.daftarResep = daftarResep;
    }


    public ArrayList<String> getDaftarResep() {
        return daftarResep;
    }

    public void setDaftarResep(ArrayList<String> daftarResep) {
        this.daftarResep = daftarResep;
    }

    public void tampilkanResep() {
    }

    public boolean craft(String resep, Inventory inventory) {
        return true;
    }
}


