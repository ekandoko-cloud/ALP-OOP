package systems.shop;
import java.util.*;

import models.item.ConsumableFood;
import models.item.Equipment;
import models.item.Item;
import models.account.AccountProfile;
public class Shop {
    private ArrayList<Item> daftarItem;
    private String shopName;
    private AccountProfile linkedAccount;


    public Shop(ArrayList<Item> daftarItem, String shopName, AccountProfile account) {
        this.daftarItem = daftarItem;
        this.shopName = shopName;
        this.linkedAccount = account;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ArrayList<Item> getDaftarItem() {
        return daftarItem;
    }

    public void setDaftarItem(ArrayList<Item> daftarItem) {
        this.daftarItem = daftarItem;
    }

    public void tampilkanItem() {
        if (daftarItem.isEmpty()) {
            System.out.println("\n=== " + shopName + " ===");
            System.out.println("Toko sedang sepi! Tidak ada item tersedia.");
            return;
        }

        System.out.println("\n=== " + shopName + " ===");
        System.out.println("Selamat datang di " + shopName + "!");
        System.out.println(String.format("%-30s | %-15s | %-10s",
                "Nama Item", "Tipe", "Harga"));
        System.out.println("-".repeat(70));

        int index = 1;
        for (Item item : daftarItem) {
            String tipe = item.getItemType().toString();
            System.out.println(index + ". " + String.format("%-25s | %-15s | %d gold ",
                    item.getNamaItem(), tipe, item.getHargaJual()));
            index++;
        }
    }

    public boolean beliItem(int itemIndex, int jumlah, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("\nProfil pemain tidak tersedia!");
            return false;
        }

        if (itemIndex < 1 || itemIndex > daftarItem.size()) {
            System.out.println("\nIndex item tidak valid!");
            return false;
        }

        if (jumlah <= 0) {
            System.out.println("\nJumlah pembelian harus lebih dari 0!");
            return false;
        }

        Item shopItem = daftarItem.get(itemIndex - 1);
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

        if (playerInventory.size() + jumlah > playerAccount.getMaxInventorySlots()) {
            System.out.println("\nInventory sudah penuh! (Max " + playerAccount.getMaxInventorySlots() + " slot)");
            System.out.println("  Slot yang digunakan: " + playerInventory.size() + "/" + playerAccount.getMaxInventorySlots());
            return false;
        }

        playerAccount.setTotalGold(playerAccount.getTotalGold() - totalPrice);

        for (int i = 0; i < jumlah; i++) {
            playerInventory.add(shopItem);
        }

        System.out.println("\nPembelian berhasil!");
        System.out.println("  Item dibeli: " + shopItem.getNamaItem());
        System.out.println("  Jumlah: " + jumlah);
        System.out.println("  Harga total: " + totalPrice + " gold");
        System.out.println("  Gold tersisa: " + playerAccount.getTotalGold());
        return true;
    }

    public void displayItemDetail(int itemIndex) {
        Item item =  daftarItem.get(itemIndex - 1);

        if (item == null) {
            System.out.println("\nItem tidak tersedia di toko!");
            return;
        }

        System.out.println("\n=== DETAIL ITEM ===");
        System.out.println("Nama: " + item.getNamaItem());
        System.out.println("Harga: " + item.getHargaJual() + " gold");
        System.out.println("Deskripsi: " + item.getDeskripsi());
        System.out.println("Tipe: " + item.getItemType());

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

    public boolean sellItem(int inventoryIndex, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("\nProfil pemain tidak tersedia!");
            return false;
        }

        LinkedList<Item> inventory = playerAccount.getInventory();
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("\nInventory kosong, tidak ada yang bisa dijual.");
            return false;
        }

        if (inventoryIndex < 1 || inventoryIndex > inventory.size()) {
            System.out.println("\nIndex inventory tidak valid!");
            return false;
        }

        Item itemToSell = inventory.get(inventoryIndex - 1);
        if (itemToSell == null) {
            System.out.println("\nItem tidak ditemukan pada index tersebut.");
            return false;
        }

        int sellPrice = itemToSell.getHargaJual();
        inventory.remove(inventoryIndex - 1);
        playerAccount.setTotalGold(playerAccount.getTotalGold() + sellPrice);

        System.out.println("\nPenjualan berhasil!");
        System.out.println("  Item terjual: " + itemToSell.getNamaItem());
        System.out.println("  Harga jual: " + sellPrice + " gold");
        System.out.println("  Gold sekarang: " + playerAccount.getTotalGold());
        return true;
    }

    private Item findItemInShop(String itemName) {
        for (Item item : daftarItem) {
            if (item.getNamaItem().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }


    public int getMaxInventorySlots() {
        if (linkedAccount != null) {
            return linkedAccount.getMaxInventorySlots();
        }
        return 10;
    }

    public void setMaxInventorySlots(int maxInventorySlots) {
        if (linkedAccount != null) {
            linkedAccount.setMaxInventorySlots(maxInventorySlots);
        }
    }

    public void setLinkedAccount(AccountProfile account) {
        this.linkedAccount = account;
    }

    public AccountProfile getLinkedAccount() {
        return linkedAccount;
    }

    public void setCurrentAccount(AccountProfile account) {
        this.linkedAccount = account;
    }
}


