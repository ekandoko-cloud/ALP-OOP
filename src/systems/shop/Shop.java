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

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_ITALIC = "\u001b[3m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RED_BRIGHT = "\u001B[91m";
    private static final String SOFT_TEAL = "\u001B[38;2;64;200;180m";
    private static final String WARM_GOLD = "\u001B[38;2;220;180;80m";
    private static final String SOFT_WHITE = "\u001B[38;2;220;230;240m";
    private static final String SOFT_GREEN = "\u001B[38;2;100;200;140m";
    private static final String DIM_GRAY = "\u001B[38;2;130;145;160m";


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
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + String.format("   %-73s", shopName) + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + String.format("   %-73s", "Toko sedang sepi! Tidak ada item tersedia.") + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            return;
        }

        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD + ANSI_BOLD + String.format("   %-73s", "Selamat datang di " + shopName + "!") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + String.format("   %-28s | %-15s | %-10s%s", "Nama Item", "Tipe", "Harga", "              ") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "   " + "-".repeat(71) + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);

        int index = 1;
        for (Item item : daftarItem) {
            String tipe = item.getItemType().toString();
            String baris = String.format("   %d. %-24s | %-15s | %d gold", index, item.getNamaItem(), tipe, item.getHargaJual());
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + String.format("%-76s", baris) + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
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
            System.out.println("\nGold tidak mencukupi!");
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
            playerAccount.addItemToInventory(shopItem);
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

        LinkedList<Item> sortedInventory = new LinkedList<>(inventory);
        sortedInventory.sort(Comparator.comparing(Item::getNamaItem));

        if (inventoryIndex < 1 || inventoryIndex > sortedInventory.size()) {
            System.out.println("\nIndex inventory tidak valid! (Valid: 1 - " + sortedInventory.size() + ")");
            return false;
        }

        Item itemToSell = sortedInventory.get(inventoryIndex - 1);
        if (itemToSell == null) {
            System.out.println("\nItem tidak ditemukan pada index tersebut.");
            return false;
        }

        int sellPrice = itemToSell.getHargaJual();

        inventory.remove(itemToSell);
        playerAccount.setTotalGold(playerAccount.getTotalGold() + sellPrice);

        System.out.println("\nPenjualan berhasil!");
        System.out.println("  Item terjual: " + itemToSell.getNamaItem());
        System.out.println("  Harga jual: " + sellPrice + " gold");
        System.out.println("  Gold sekarang: " + playerAccount.getTotalGold());
        return true;
    }

    public int getMaxInventorySlots() {
        if (linkedAccount != null) {
            return linkedAccount.getMaxInventorySlots();
        }
        return models.account.AccountProfile.DEFAULT_MAX_INVENTORY_SLOTS;
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
        setLinkedAccount(account);
    }
}