package systems.shop;
import java.util.*;
import models.item.Item;
import models.account.AccountProfile;
public class Shop {
    private ArrayList<Item> daftarItem;

    public Shop(ArrayList<Item> daftarItem) {
        this.daftarItem = daftarItem;
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


