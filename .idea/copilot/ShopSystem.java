package copilot;

import java.util.*;
import models.item.*;
import models.account.AccountProfile;

/**
 * ShopSystem - Sistem toko untuk membeli item
 *
 * Fitur utama:
 * 1. Menampilkan daftar item yang dijual
 * 2. Membeli item dengan gold pemain
 * 3. Validasi gold pemain
 * 4. Menambahkan item ke inventory setelah pembelian
 *
 * OOP Concepts:
 * - Encapsulation: Private variables untuk shop inventory dan player account
 * - Composition: Menggunakan ArrayList<Item> untuk daftar item toko
 * - Inheritance: Item dan subclass-nya (Equipment, ConsumableFood, Inqredients)
 * - Polymorphism: Bekerja dengan Item base class untuk berbagai tipe item
 * - Abstraction: Menyembunyikan kompleksitas proses transaksi
 */
public class ShopSystem {
    private ArrayList<Item> shopInventory;
    private String shopName;
    private int maxInventorySlots;

    public ShopSystem(String shopName, ArrayList<Item> initialItems, int maxInventorySlots) {
        this.shopName = shopName;
        this.shopInventory = new ArrayList<>(initialItems);
        this.maxInventorySlots = maxInventorySlots;
    }

    /**
     * Menampilkan semua item yang tersedia di toko
     */
    public void displayShop() {
        if (shopInventory.isEmpty()) {
            System.out.println("\n=== " + shopName + " ===");
            System.out.println("Toko sedang sepi! Tidak ada item tersedia.");
            return;
        }

        System.out.println("\n=== " + shopName + " ===");
        System.out.println("Selamat datang di " + shopName + "!");
        System.out.println(String.format("%-30s | %-15s | %-10s | %-8s",
            "Nama Item", "Tipe", "Harga", "Stok"));
        System.out.println("-".repeat(70));

        int index = 1;
        for (Item item : shopInventory) {
            String tipe = getItemType(item);
            System.out.println(index + ". " + String.format("%-25s | %-15s | %d gold | Terbatas",
                item.getNamaItem(), tipe, item.getHargaJual()));
            index++;
        }
    }

    /**
     * Membeli item dari toko berdasarkan index dan jumlah.
     * Alur: user pilih item, lalu masukkan jumlah.
     */
    public boolean buyItem(int itemIndex, int jumlah, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("\n Profil pemain tidak tersedia!");
            return false;
        }

        if (itemIndex < 1 || itemIndex > shopInventory.size()) {
            System.out.println("\n Index item tidak valid!");
            return false;
        }

        if (jumlah <= 0) {
            System.out.println("\n Jumlah pembelian harus lebih dari 0!");
            return false;
        }

        Item shopItem = shopInventory.get(itemIndex - 1);
        int totalPrice = shopItem.getHargaJual() * jumlah;

        if (playerAccount.getTotalGold() < totalPrice) {
            System.out.println("\n✗ Gold tidak mencukupi!");
            System.out.println("  Gold Anda: " + playerAccount.getTotalGold());
            System.out.println("  Harga Total: " + totalPrice);
            System.out.println("  Kurang: " + (totalPrice - playerAccount.getTotalGold()) + " gold");
            return false;
        }

        LinkedList<Item> playerInventory = playerAccount.getInventory();
        if (playerInventory == null) {
            playerInventory = new LinkedList<>();
            playerAccount.setInventory(playerInventory);
        }

        if (playerInventory.size() + jumlah > maxInventorySlots) {
            System.out.println("\n✗ Inventory sudah penuh! (Max " + maxInventorySlots + " slot)");
            System.out.println("  Slot yang digunakan: " + playerInventory.size() + "/" + maxInventorySlots);
            return false;
        }

        playerAccount.setTotalGold(playerAccount.getTotalGold() - totalPrice);

        for (int i = 0; i < jumlah; i++) {
            playerInventory.add(shopItem);
        }

        System.out.println("\n✓ Pembelian berhasil!");
        System.out.println("  Item dibeli: " + shopItem.getNamaItem());
        System.out.println("  Jumlah: " + jumlah);
        System.out.println("  Harga total: " + totalPrice + " gold");
        System.out.println("  Gold tersisa: " + playerAccount.getTotalGold());
        return true;
    }

    /**
     * Menampilkan detail item yang dijual di toko
     */
    public void displayItemDetail(String itemName) {
        Item item = findItemInShop(itemName);

        if (item == null) {
            System.out.println("\nItem '" + itemName + "' tidak tersedia di toko!");
            return;
        }

        System.out.println("\n=== DETAIL ITEM ===");
        System.out.println("Nama: " + item.getNamaItem());
        System.out.println("Harga: " + item.getHargaJual() + " gold");
        System.out.println("Deskripsi: " + item.getDeskripsi());
        System.out.println("Tipe: " + getItemType(item));

        // Detail spesifik berdasarkan tipe
        if (item instanceof Equipment) {
            Equipment equip = (Equipment) item;
            System.out.println("\n--- Detail Equipment ---");
            System.out.println("Tipe: " + equip.getTipeEquipment());
            System.out.println("Bonus ATK: +" + equip.getBonusKekuatan());
            System.out.println("Bonus DEF: +" + equip.getBonusDefense());
        } else if (item instanceof ConsumableFood) {
            ConsumableFood food = (ConsumableFood) item;
            System.out.println("\n--- Detail Makanan ---");
            System.out.println("Heal HP: " + food.getHealHpAmount());
            System.out.println("Heal MP: " + food.getHealMpAmount());
            System.out.println("Buff ATK: +" + food.getStrBuff());
            System.out.println("Buff DEF: +" + food.getDefBuff());
        }
    }

    /**
     * Mencari item di toko dengan nama
     */
    private Item findItemInShop(String itemName) {
        for (Item item : shopInventory) {
            if (item.getNamaItem().equalsIgnoreCase(itemName)) {
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
     * Menambahkan item baru ke toko (untuk restock)
     */
    public void restockItem(Item item) {
        if (item == null) return;
        shopInventory.add(item);
        System.out.println("✓ Item '" + item.getNamaItem() + "' ditambahkan ke toko.");
    }

    /**
     * Menghapus item dari toko (discontinued)
     */
    public void removeItem(String itemName) {
        Item item = findItemInShop(itemName);
        if (item != null) {
            shopInventory.remove(item);
            System.out.println("✓ Item '" + itemName + "' dihapus dari toko.");
        }
    }

    /**
     * Menjual item dari inventory pemain ke toko (player -> shop)
     * Harga beli kembali adalah 50% dari harga jual item (dibulatkan ke bawah).
     * Tidak otomatis menambahkan kembali item ke shopInventory (anda bisa tambahkan jika ingin restock).
     *
     * @param inventoryIndex 1-based index dari inventory pemain
     * @param playerAccount akun pemain yang menjual item
     * @return true jika penjualan sukses
     */
    public boolean sellItemByIndex(int inventoryIndex, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("\n Profil pemain tidak tersedia!");
            return false;
        }

        LinkedList<Item> inventory = playerAccount.getInventory();
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("\n Inventory kosong, tidak ada yang bisa dijual.");
            return false;
        }

        if (inventoryIndex < 1 || inventoryIndex > inventory.size()) {
            System.out.println("\n Index inventory tidak valid!");
            return false;
        }

        Item itemToSell = inventory.get(inventoryIndex - 1);
        if (itemToSell == null) {
            System.out.println("\n Item tidak ditemukan pada index tersebut.");
            return false;
        }

        int sellPrice = itemToSell.getHargaJual();
        inventory.remove(inventoryIndex - 1);
        playerAccount.setTotalGold(playerAccount.getTotalGold() + sellPrice);

        System.out.println("\n Penjualan berhasil!");
        System.out.println("  Item terjual: " + itemToSell.getNamaItem());
        System.out.println("  Harga jual: " + sellPrice + " gold");
        System.out.println("  Gold sekarang: " + playerAccount.getTotalGold());
        return true;
    }

    /**
     * Menjual item berdasarkan nama (menjual item pertama yang cocok di inventory pemain)
     */
    public boolean sellItemByName(String itemName, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("\n✗ Profil pemain tidak tersedia!");
            return false;
        }

        LinkedList<Item> inventory = playerAccount.getInventory();
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("\n✗ Inventory kosong, tidak ada yang bisa dijual.");
            return false;
        }

        for (int i = 0; i < inventory.size(); i++) {
            Item it = inventory.get(i);
            if (it != null && it.getNamaItem().equalsIgnoreCase(itemName)) {
                return sellItemByIndex(i + 1, playerAccount);
            }
        }

        System.out.println("\n✗ Item '" + itemName + "' tidak ditemukan di inventory.");
        return false;
    }

    /**
     * Getter dan Setter
     */
    public ArrayList<Item> getShopInventory() {
        return shopInventory;
    }

    public String getShopName() {
        return shopName;
    }

    public int getMaxInventorySlots() {
        return maxInventorySlots;
    }
}

