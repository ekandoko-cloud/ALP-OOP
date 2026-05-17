package systems.inventory;

import java.util.*;
import models.item.Item;

public class Inventory {
    private LinkedList<Item> listBarang;

    public Inventory(LinkedList<Item> listBarang) {
        this.listBarang = listBarang;
    }


    public LinkedList<Item> getListBarang() {
        return listBarang;
    }

    public void setListBarang(LinkedList<Item> listBarang) {
        this.listBarang = listBarang;
    }

    public void tambahItem(Item Item) {
    }

    public void hapusItem(Item Item) {
    }

    public Item cariItem(String keyword) {
        return null;
    }

    public void sortAbjad() {
    }
}
