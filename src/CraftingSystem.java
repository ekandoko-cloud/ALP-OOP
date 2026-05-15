import java.util.ArrayList;

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

