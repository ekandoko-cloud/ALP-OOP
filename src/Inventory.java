import java.util.LinkedList;

public class Inventory {
    private LinkedList<Item> listBarang;

    public Inventory() {
        this.listBarang = new LinkedList<>();
    }


    public LinkedList<Item> getListBarang() {
        return listBarang;
    }

    public void setListBarang(LinkedList<Item> listBarang) {
        this.listBarang = listBarang;
    }

    public void tambahItem(Item item) {
    }

    public void hapusItem(Item item) {
    }

    public Item cariItem(String keyword) {
        return null;
    }

    public void sortAbjad() {
    }
}

