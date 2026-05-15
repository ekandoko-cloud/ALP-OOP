import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> daftarItem;

    public Shop() {
        this.daftarItem = new ArrayList<>();
    }


    public ArrayList<Item> getDaftarItem() {
        return daftarItem;
    }

    public void setDaftarItem(ArrayList<Item> daftarItem) {
        this.daftarItem = daftarItem;
    }

    public void tampilkanItem() {
    }

    public boolean beliItem(Item item, int jumlah, AccountProfile profil) {
        return true;
    }
}

