package systems.inventory;

import java.util.*;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import models.item.ConsumableFood;
import models.item.Equipment;
import models.item.Item;

public class Inventory {
    private int MAX_INVENTORY_SLOTS = models.account.AccountProfile.DEFAULT_MAX_INVENTORY_SLOTS;
    private AccountProfile currentAccount;
    private LinkedList<Item> listBarang;

    public Inventory(AccountProfile currentAccount) {
        this.currentAccount = currentAccount;
        this.listBarang = new LinkedList<>();
        syncInventory();
    }

    public void syncInventory() {
        if (currentAccount.getInventory() != null) {
            this.listBarang = new LinkedList<>(currentAccount.getInventory());
        }
    }

    public LinkedList<Item> getSortedInventory() {
        LinkedList<Item> sorted = new LinkedList<>(listBarang);
        sortByName(sorted);
        return sorted;
    }

    public void displayInventory() {
        LinkedList<Item> sorted = getSortedInventory();

        if (sorted.isEmpty()) {
            System.out.println("\n=== INVENTORY ===");
            System.out.println("Inventory masih kosong!");
            return;
        }

        System.out.println("\n=== INVENTORY ===");
        System.out.printf("%-4s %-30s %-15s %s%n", "No.", "Nama Item", "Tipe", "Harga");
        System.out.println("-".repeat(65));

        int index = 1;
        for (Item item : sorted) {
            String tipe = item.getItemType().toString();
            System.out.printf("%-4s %-30.30s %-15s %s%n", index + ".", item.getNamaItem(), tipe, item.getHargaJual() + " gold");
            index++;
        }
        System.out.println("Total item: " + sorted.size());
    }

    public void cariItem(String keyword) {
        LinkedList<Item> hasil = new LinkedList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Item item : listBarang) {
            if (item.getNamaItem().toLowerCase().contains(lowerKeyword)) {
                hasil.add(item);
            }
        }

        if (hasil.isEmpty()) {
            System.out.println("\n=== HASIL PENCARIAN ===");
            System.out.println("Item dengan kata kunci '" + keyword + "' tidak ditemukan!");
            return;
        }

        sortByName(hasil);

        System.out.println("\n=== HASIL PENCARIAN: '" + keyword + "' ===");
        System.out.printf("%-4s %-30s %-15s %s%n", "No.", "Nama Item", "Tipe", "Harga");
        System.out.println("-".repeat(65));

        int index = 1;
        for (Item item : hasil) {
            String tipe = item.getItemType().toString();
            System.out.printf("%-4s %-30.30s %-15s %s%n", index + ".", item.getNamaItem(), tipe, item.getHargaJual() + " gold");
            index++;
        }
    }

    private Item itemNameSearch(String name) {
        for (Item item : listBarang) {
            if (item.getNamaItem().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


    public boolean useItem(int itemIndex, int targetIndex) {
        try {
            LinkedList<Item> sortedInventory = getSortedInventory();

            if (itemIndex < 1 || itemIndex > sortedInventory.size()) {
                System.out.println("Index item tidak valid. (Valid: 1 - " + sortedInventory.size() + ")");
                return false;
            }

            Item item = sortedInventory.get(itemIndex - 1);
            if (!(item instanceof ConsumableFood food)) {
                System.out.println("Item tersebut bukan consumable.");
                return false;
            }

            if (currentAccount == null || currentAccount.getParty() == null) {
                System.out.println("Party tidak tersedia.");
                return false;
            }

            List<PlayerCharacter> members = new ArrayList<>();
            for (PlayerCharacter pc : currentAccount.getParty()) {
                if (pc != null) {
                    members.add(pc);
                }
            }

            if (members.isEmpty()) {
                System.out.println("Party kosong.");
                return false;
            }

            if (targetIndex < 1 || targetIndex > members.size()) {
                System.out.println("Index member tidak valid.");
                return false;
            }

            PlayerCharacter target = members.get(targetIndex - 1);
            food.useItem(target);

            listBarang.remove(item);
            if (currentAccount.getInventory() != null) {
                currentAccount.getInventory().remove(item);
            }

            System.out.println("Item consumable digunakan pada " + target.getNama() + ".");
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index tidak valid!");
            return false;
        } catch (Exception e) {
            System.out.println("Error menggunakan item: " + e.getMessage());
            return false;
        }
    }

    public void displayItemDetail(String itemName) {
        Item item = itemNameSearch(itemName);

        if (item == null) {
            System.out.println("\nItem '" + itemName + "' tidak ditemukan!");
            return;
        }

        System.out.println("\n=== DETAIL ITEM ===");
        System.out.println("Nama: " + item.getNamaItem());
        System.out.println("Deskripsi: " + item.getDeskripsi());
        System.out.println("Harga Jual: " + item.getHargaJual() + " gold");
        System.out.println("Tipe: " + item.getItemType());

        if (item instanceof Equipment equipment) {
            System.out.println("\n--- Detail Equipment ---");
            System.out.println("Tipe Equipment: " + equipment.getTipeEquipment());
            System.out.println("Bonus Kekuatan: +" + equipment.getBonusKekuatan());
            System.out.println("Bonus Defense: +" + equipment.getBonusDefense());
            System.out.println("Level Upgrade: +" + equipment.getLevelTempa());
        } else if (item instanceof ConsumableFood food) {
            System.out.println("\n--- Detail Consumable ---");
            System.out.println("Penyembuhan HP: " + food.getHealHpAmount());
            System.out.println("Penyembuhan MP: " + food.getHealMpAmount());
            System.out.println("Buff Kekuatan Permanen: " + food.getStrBuff());
            System.out.println("Buff Defense Permanen: " + food.getDefBuff());
            System.out.println("Info Gizi SDG: " + food.getInfoGiziSDG());
        }
    }

    public LinkedList<Item> getListBarang() {
        return listBarang;
    }

    public void setListBarang(LinkedList<Item> listBarang) {
        this.listBarang = listBarang;
    }

    private void sortByName(List<Item> items) {
        items.sort(Comparator.comparing(Item::getNamaItem));
    }


    public int getMAX_INVENTORY_SLOTS() {
        if (currentAccount != null) {
            return currentAccount.getMaxInventorySlots();
        }
        return MAX_INVENTORY_SLOTS; // fallback to local default
    }

    public void setMAX_INVENTORY_SLOTS(int maxSlots) {
        if (currentAccount != null) {
            currentAccount.setMaxInventorySlots(maxSlots);
        }
        this.MAX_INVENTORY_SLOTS = maxSlots;
    }
}
