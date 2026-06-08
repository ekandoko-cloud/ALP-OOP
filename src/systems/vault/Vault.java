package systems.vault;

import java.util.ArrayList;
import models.account.AccountProfile;
import models.item.Item;

public class Vault {

    private ArrayList<Item> items;

    public Vault() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean deposit(AccountProfile account, Item item) {
        if (account == null || item == null) {
            return false;
        }
        if (account.getInventory() == null || !account.getInventory().contains(item)) {
            return false;
        }

        account.removeItemFromInventory(item);
        items.add(item);
        return true;
    }

    public boolean withdraw(AccountProfile account, Item item) {
        if (account == null || item == null) {
            return false;
        }
        if (!items.contains(item)) {
            return false;
        }
        if (account.getInventory() == null) {
            account.setInventory(new java.util.LinkedList<>());
        }
        if (account.getInventory().size() >= account.getMaxInventorySlots()) {
            return false;
        }
        items.remove(item);
        account.addItemToInventory(item);
        return true;
    }
}
