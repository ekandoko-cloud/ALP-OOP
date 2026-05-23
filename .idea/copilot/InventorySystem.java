package copilot;

import java.util.*;
import models.item.*;
import models.account.AccountProfile;

/**
 * InventorySystem - Sistem manajemen inventory pemain
 *
 * Fitur utama:
 * 1. Menampilkan semua item dalam urutan A-Z (menggunakan Double Linked List)
 * 2. Mencari item berdasarkan kata kunci
 * 3. Menampilkan detail item
 * 4. Menggunakan item consumable
 * 5. Menambah dan menghapus item
 *
 * OOP Concepts:
 * - Encapsulation: Private variables dengan getter/setter
 * - Composition: Menggunakan LinkedList untuk menyimpan item
 * - Polymorphism: Bekerja dengan Item base class
 * - Abstraction: Interface IConsumable untuk item yang bisa dikonsumsi
 */
public class InventorySystem {
    private static final int MAX_INVENTORY_SLOTS = 10;
    private AccountProfile playerAccount;
    private LinkedList<Item> sortedInventory;

    public InventorySystem(AccountProfile playerAccount) {
        this.playerAccount = playerAccount;
        this.sortedInventory = new LinkedList<>();
        syncInventory();
        sortedInventory.sort(Comparator.comparing(Item::getNamaItem));
    }

    /**
     * Sinkronisasi inventory dari AccountProfile
     */
    private void syncInventory() {
        if (playerAccount.getInventory() != null) {
            sortedInventory = new LinkedList<>(playerAccount.getInventory());
        }
    }

    /**
     * Menampilkan semua item yang tersortir A-Z
     */
    public void displayInventory() {
        if (sortedInventory.isEmpty()) {
            System.out.println("\n=== INVENTORY ===");
            System.out.println("Inventory masih kosong!");
            return;
        }

        // Sort menggunakan built-in Collections.sort
        LinkedList<Item> sorted = new LinkedList<>(sortedInventory);
        sorted.sort(Comparator.comparing(Item::getNamaItem));

        System.out.println("\n=== INVENTORY (A-Z) ===");
        System.out.println("Slot: " + sortedInventory.size() + "/" + MAX_INVENTORY_SLOTS);
        System.out.println("No.\tNama Item\t\tTipe\t\tHarga");
        System.out.println("-".repeat(60));

        int index = 1;
        for (Item item : sorted) {
            String tipe = getItemType(item);
            System.out.println(index + ".\t" + item.getNamaItem() + "\t\t" + tipe + "\t\t" + item.getHargaJual() + " gold");
            index++;
        }
        System.out.println("Total item: " + sorted.size());
    }


    /**
     * Mencari item berdasarkan kata kunci
     * Traversal: Linear search dari head ke tail
     */
    public void searchItem(String keyword) {
        LinkedList<Item> results = new LinkedList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Item item : sortedInventory) {
            if (item.getNamaItem().toLowerCase().contains(lowerKeyword)) {
                results.add(item);
            }
        }

        if (results.isEmpty()) {
            System.out.println("\n=== HASIL PENCARIAN ===");
            System.out.println("Item dengan kata kunci '" + keyword + "' tidak ditemukan!");
            return;
        }

        System.out.println("\n=== HASIL PENCARIAN: '" + keyword + "' ===");
        System.out.println("No.\tNama Item\t\tTipe\t\tHarga");
        System.out.println("-".repeat(60));

        int index = 1;
        for (Item item : results) {
            String tipe = getItemType(item);
            System.out.println(index + ".\t" + item.getNamaItem() + "\t\t" + tipe + "\t\t" + item.getHargaJual() + " gold");
            index++;
        }
    }

    /**
     * Menampilkan detail item tertentu
     */
    public void displayItemDetail(String itemName) {
        Item item = searchItemByName(itemName);

        if (item == null) {
            System.out.println("\nItem '" + itemName + "' tidak ditemukan!");
            return;
        }

        System.out.println("\n=== DETAIL ITEM ===");
        System.out.println("Nama: " + item.getNamaItem());
        System.out.println("Deskripsi: " + item.getDeskripsi());
        System.out.println("Harga Jual: " + item.getHargaJual() + " gold");
        System.out.println("Tipe: " + getItemType(item));

        // Tampilkan detail spesifik berdasarkan tipe
        if (item instanceof Equipment) {
            displayEquipmentDetail((Equipment) item);
        } else if (item instanceof ConsumableFood) {
            displayConsumableFoodDetail((ConsumableFood) item);
        } else if (item instanceof Inqredients) {
            System.out.println("Tipe: Bahan/Ingredients");
        }
    }

    /**
     * Menampilkan detail Equipment
     */
    private void displayEquipmentDetail(Equipment equipment) {
        System.out.println("\n--- Detail Equipment ---");
        System.out.println("Tipe Equipment: " + equipment.getTipeEquipment());
        System.out.println("Bonus Kekuatan: +" + equipment.getBonusKekuatan());
        System.out.println("Bonus Defense: +" + equipment.getBonusDefense());
        System.out.println("Level Upgrade: +" + equipment.getLevelTempa());
    }

    /**
     * Menampilkan detail ConsumableFood
     */
    private void displayConsumableFoodDetail(ConsumableFood food) {
        System.out.println("\n--- Detail Makanan ---");
        System.out.println("Penyembuhan HP: " + food.getHealHpAmount());
        System.out.println("Penyembuhan MP: " + food.getHealMpAmount());
        System.out.println("Buff Kekuatan Temp: " + food.getTempStrBuff());
        System.out.println("Buff Defense Temp: " + food.getTempDefBuff());
        System.out.println("Info Gizi SDG: " + food.getInfoGiziSDG());
    }

    /**
     * Menggunakan item consumable
     */
    public boolean useConsumableItem(String itemName) {
        Item item = searchItemByName(itemName);

        if (item == null) {
            System.out.println("Item '" + itemName + "' tidak ditemukan!");
            return false;
        }

        if (!(item instanceof IConsumable)) {
            System.out.println("Item '" + itemName + "' tidak bisa dikonsumsi!");
            return false;
        }

        // Konsumsi item dan hapus dari inventory
        IConsumable consumable = (IConsumable) item;
        // Dalam implementasi penuh, konsumsi akan mempengaruhi character stats
        // consumable.consume(playerCharacter);

        sortedInventory.remove(item);
        playerAccount.getInventory().remove(item);

        System.out.println("\n✓ Item '" + itemName + "' berhasil dikonsumsi!");
        System.out.println("Item telah dihapus dari inventory.");

        return true;
    }

    /**
     * Menambahkan item ke inventory
     */
    public boolean addItem(Item newItem) {
        if (newItem == null) {
            System.out.println("Item tidak valid!");
            return false;
        }

        // Check inventory sudah penuh
        if (sortedInventory.size() >= MAX_INVENTORY_SLOTS) {
            System.out.println("\n✗ Inventory penuh! (Max " + MAX_INVENTORY_SLOTS + " slot)");
            System.out.println("Slot yang digunakan: " + sortedInventory.size() + "/" + MAX_INVENTORY_SLOTS);
            return false;
        }

        sortedInventory.add(newItem);
        playerAccount.getInventory().add(newItem);

        System.out.println("\n✓ Item '" + newItem.getNamaItem() + "' ditambahkan ke inventory!");
        System.out.println("Slot tersisa: " + (MAX_INVENTORY_SLOTS - sortedInventory.size()) + "/" + MAX_INVENTORY_SLOTS);
        return true;
    }

    /**
     * Menghapus item dari inventory
     */
    public boolean removeItem(String itemName) {
        Item item = searchItemByName(itemName);

        if (item == null) {
            System.out.println("Item '" + itemName + "' tidak ditemukan!");
            return false;
        }

        sortedInventory.remove(item);
        playerAccount.getInventory().remove(item);

        System.out.println("\n✓ Item '" + itemName + "' dihapus dari inventory!");
        return true;
    }

    /**
     * Helper: Mencari item berdasarkan nama
     */
    private Item searchItemByName(String name) {
        for (Item item : sortedInventory) {
            if (item.getNamaItem().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Helper: Mendapatkan tipe item sebagai string
     */
    private String getItemType(Item item) {
        if (item instanceof Equipment) {
            return "Equipment";
        } else if (item instanceof ConsumableFood) {
            return "Makanan";
        } else if (item instanceof Inqredients) {
            return "Bahan";
        }
        return "Item";
    }

    /**
     * Getter untuk inventory yang sudah disortir
     */
    public LinkedList<Item> getSortedInventory() {
        LinkedList<Item> sorted = new LinkedList<>(sortedInventory);
        Collections.sort(sorted, Comparator.comparing(Item::getNamaItem));
        return sorted;
    }

    /**
     * Mendapatkan jumlah item dalam inventory
     */
    public int getItemCount() {
        return sortedInventory.size();
    }

    /**
     * Mendapatkan slot inventory maksimal
     */
    public int getMaxSlots() {
        return MAX_INVENTORY_SLOTS;
    }

    /**
     * Mendapatkan slot inventory yang tersisa
     */
    public int getAvailableSlots() {
        return MAX_INVENTORY_SLOTS - sortedInventory.size();
    }

    /**
     * Check apakah inventory penuh
     */
    public boolean isInventoryFull() {
        return sortedInventory.size() >= MAX_INVENTORY_SLOTS;
    }
}

