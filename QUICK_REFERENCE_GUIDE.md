# QUICK REFERENCE GUIDE - CONTOH PENGGUNAAN

## Table of Contents
- [1. Setup Sistem](#1-setup-sistem)
- [2. Contoh Inventory](#2-inventory-examples)
- [3. Contoh Shop](#3-shop-examples)
- [4. Contoh Crafting](#4-crafting-examples)
- [5. Contoh Forge](#5-forge-examples)
- [6. Integrasi Lengkap](#6-integrasi-lengkap)

---

## 1. Setup Sistem

### Langkah 1: Import Classes
```java
import java.util.*;
import models.item.*;
import models.character.PlayerCharacter;
import models.account.AccountProfile;
import copilot.*;
```

### Langkah 2: Buat Data Pemain
```java
// Buat inventory awal
LinkedList<Item> startingInventory = new LinkedList<>();
startingInventory.add(new Inqredients(1, "Herb Daun", 20, "Daun herbal hijau"));
startingInventory.add(new Inqredients(2, "Mineral Blue", 30, "Kristal mineral biru"));
startingInventory.add(new Inqredients(3, "Iron Ore", 50, "Biji besi mentah"));

// Buat character utama
PlayerCharacter heroCharacter = new PlayerCharacter(
    "Hero Legendary",           // nama
    100,                         // maxHp
    100,                         // currentHp
    50,                          // maxMp
    50,                          // currentMp
    20,                          // kekuatan
    10,                          // defense
    1,                           // level
    0,                           // currentExp
    1000,                        // maxExp
    "Warrior",                   // namaClass
    false                        // statusTubuhNirlelah
);

// Buat account pemain
AccountProfile myAccount = new AccountProfile(
    "player1",
    "password123",
    5000,                        // totalGold
    new PlayerCharacter[]{heroCharacter},
    startingInventory,
    null                         // questTracker
);

System.out.println("=== Setup Selesai ===");
System.out.println("Username: " + myAccount.getUsername());
System.out.println("Gold: " + myAccount.getTotalGold());
System.out.println("Character: " + heroCharacter.getNama());
System.out.println("Inventory Items: " + startingInventory.size());
```

---

## 2. Inventory Examples

### Contoh 2.1: Menampilkan Inventory Terurut
```java
System.out.println("\n=== CONTOH 2.1: DISPLAY INVENTORY ===");

InventorySystem inventorySystem = new InventorySystem(myAccount);
inventorySystem.displayInventory();

// Output:
// === INVENTORY (A-Z) ===
// Nama Item                 | Tipe            | Harga
// ────────────────────────────────────────────────────
// 1. Herb Daun              | Bahan           | 20 gold
// 2. Iron Ore               | Bahan           | 50 gold
// 3. Mineral Blue            | Bahan           | 30 gold
```

### Contoh 2.2: Mencari Item
```java
System.out.println("\n=== CONTOH 2.2: SEARCH ITEM ===");

inventorySystem.searchItem("Ore");
// Output:
// === HASIL PENCARIAN: 'Ore' ===
// ...
// 1. Iron Ore               | Bahan           | 50 gold
```

### Contoh 2.3: Melihat Detail Item
```java
System.out.println("\n=== CONTOH 2.3: DETAIL ITEM ===");

inventorySystem.displayItemDetail("Herb Daun");
// Output:
// === DETAIL ITEM ===
// Nama: Herb Daun
// Deskripsi: Daun herbal hijau
// Harga Jual: 20 gold
// Tipe: Bahan
```

### Contoh 2.4: Menambah dan Menghapus Item
```java
System.out.println("\n=== CONTOH 2.4: ADD & REMOVE ITEM ===");

// Tambah item
ConsumableFood newPotion = new ConsumableFood(
    5, "Healing Potion", 50, "Minuman penyembuh HP",
    30, 0, 0, 0, "Health"
);
inventorySystem.addItem(newPotion);
System.out.println("\nSetelah menambah item:");
inventorySystem.displayInventory();

// Hapus item
inventorySystem.removeItem("Herb Daun");
System.out.println("\nSetelah menghapus Herb Daun:");
inventorySystem.displayInventory();
```

### Contoh 2.5: Menggunakan Consumable Item
```java
System.out.println("\n=== CONTOH 2.5: USE CONSUMABLE ===");

// Tambah consumable item
ConsumableFood manaPotion = new ConsumableFood(
    6, "Mana Potion", 75, "Minuman penyembuh MP",
    0, 25, 0, 0, "Energy"
);
inventorySystem.addItem(manaPotion);

// Gunakan item
if (inventorySystem.useConsumableItem("Mana Potion")) {
    System.out.println("Mana Potion digunakan!");
    inventorySystem.displayInventory();
}
```

---

## 3. Shop Examples

### Contoh 3.1: Setup Toko
```java
System.out.println("\n=== CONTOH 3.1: SETUP SHOP ===");

// Siapkan item toko
ArrayList<Item> shopItems = new ArrayList<>();

// Equipment
shopItems.add(new Equipment(
    101, "Iron Sword", 500, "Pedang besi berkualitas",
    "Sword", 15, 5, 0
));
shopItems.add(new Equipment(
    102, "Steel Shield", 450, "Perisai baja kokoh",
    "Shield", 5, 20, 0
));

// Consumables
shopItems.add(new ConsumableFood(
    201, "Healing Potion", 50, "Minuman penyembuh HP",
    30, 0, 0, 0, "Health"
));
shopItems.add(new ConsumableFood(
    202, "Mana Elixir", 75, "Minuman penyembuh MP",
    0, 25, 0, 0, "Energy"
));

// Ingredients
shopItems.add(new Inqredients(
    301, "Steel Ingot", 100, "Besi tempa berkualitas"
));

// Buat toko
ShopSystem shopSystem = new ShopSystem("Toko Prajurit", shopItems, 20);
```

### Contoh 3.2: Menampilkan Toko
```java
System.out.println("\n=== CONTOH 3.2: DISPLAY SHOP ===");

shopSystem.displayShop();
// Output:
// === Toko Prajurit ===
// Selamat datang di Toko Prajurit!
// Nama Item                 | Tipe            | Harga    | Stok
// ──────────────────────────────────────────────────────────────
// 1. Healing Potion         | Makanan         | 50 gold  | Terbatas
// 2. Iron Sword             | Equipment       | 500 gold | Terbatas
// ... dst
```

### Contoh 3.3: Membeli Item Tunggal
```java
System.out.println("\n=== CONTOH 3.3: BUY SINGLE ITEM ===");

System.out.println("Gold sebelum: " + myAccount.getTotalGold());

if (shopSystem.buyItem("Healing Potion", myAccount)) {
    System.out.println("Pembelian berhasil!");
} else {
    System.out.println("Pembelian gagal!");
}

System.out.println("Gold sesudah: " + myAccount.getTotalGold());
System.out.println("Items in inventory: " + myAccount.getInventory().size());
```

### Contoh 3.4: Membeli Multiple Items
```java
System.out.println("\n=== CONTOH 3.4: BUY MULTIPLE ITEMS ===");

String[] itemsToBuy = {
    "Healing Potion",
    "Mana Elixir",
    "Steel Ingot"
};

System.out.println("Gold sebelum: " + myAccount.getTotalGold());
shopSystem.buyMultipleItems(itemsToBuy, myAccount);
System.out.println("Gold sesudah: " + myAccount.getTotalGold());
```

### Contoh 3.5: Lihat Detail Item Toko
```java
System.out.println("\n=== CONTOH 3.5: DETAIL ITEM TOKO ===");

shopSystem.displayItemDetail("Iron Sword");
// Output:
// === DETAIL ITEM ===
// Nama: Iron Sword
// Harga: 500 gold
// Deskripsi: Pedang besi berkualitas
// Tipe: Equipment
//
// --- Detail Equipment ---
// Tipe: Sword
// Bonus ATK: +15
// Bonus DEF: +5
```

### Contoh 3.6: Restock Item Toko
```java
System.out.println("\n=== CONTOH 3.6: RESTOCK ITEM ===");

ConsumableFood newItem = new ConsumableFood(
    203, "Super Potion", 150, "Minuman penyembuh super",
    60, 20, 5, 5, "Premium"
);

shopSystem.restockItem(newItem);
shopSystem.displayShop();
```

---

## 4. Crafting Examples

### Contoh 4.1: Lihat Resep Tersedia
```java
System.out.println("\n=== CONTOH 4.1: DISPLAY RECIPES ===");

CraftingSystem craftingSystem = new CraftingSystem("Workshop Pertamina");
craftingSystem.displayRecipes();
// Output:
// === DAFTAR RESEP (Workshop Pertamina) ===
// Nama Resep              | Hasil Item         | Success Rate
// ─────────────────────────────────────────────────────────
// 1. Healing Potion       | Healing Potion     | 90%
// 2. Mana Elixir          | Mana Elixir        | 85%
// ... dst
```

### Contoh 4.2: Detail Resep
```java
System.out.println("\n=== CONTOH 4.2: DETAIL RECIPE ===");

craftingSystem.displayRecipeDetail("Healing Potion");
// Output:
// === DETAIL RESEP: Healing Potion ===
// Hasil Item: Healing Potion
// Deskripsi: Minuman untuk menyembuhkan HP
// Success Rate: 90%
//
// Bahan yang dibutuhkan:
//   - Herb Daun x2
//   - Mineral Blue x1
```

### Contoh 4.3: Craft Item (Sukses)
```java
System.out.println("\n=== CONTOH 4.3: CRAFT ITEM (SUKSES) ===");

// Siapkan bahan
myAccount.getInventory().add(new Inqredients(
    10, "Herb Daun", 20, "Daun herbal"
));
myAccount.getInventory().add(new Inqredients(
    10, "Herb Daun", 20, "Daun herbal"
));
myAccount.getInventory().add(new Inqredients(
    11, "Mineral Blue", 30, "Kristal biru"
));

// Craft
System.out.println("Items sebelum craft: " + myAccount.getInventory().size());
if (craftingSystem.craftItem("Healing Potion", myAccount)) {
    System.out.println("Craft SUKSES!");
}
System.out.println("Items sesudah craft: " + myAccount.getInventory().size());

// Lihat hasil craft
InventorySystem invSystem = new InventorySystem(myAccount);
invSystem.displayInventory();
```

### Contoh 4.4: Craft Item (Gagal - Bahan Kurang)
```java
System.out.println("\n=== CONTOH 4.4: CRAFT ITEM (GAGAL) ===");

// Clear inventory
myAccount.getInventory().clear();

// Craft tanpa bahan yang cukup
if (!craftingSystem.craftItem("Strength Stew", myAccount)) {
    System.out.println("Craft gagal karena bahan kurang!");
}
```

### Contoh 4.5: Tambah Resep Custom
```java
System.out.println("\n=== CONTOH 4.5: ADD CUSTOM RECIPE ===");

HashMap<String, Integer> customIngredients = new HashMap<>();
customIngredients.put("Gold Dust", 2);
customIngredients.put("Crystal", 1);

ConsumableFood magicPotion = new ConsumableFood(
    210, "Magic Potion", 200, "Potion ajaib kuat",
    50, 50, 20, 20, "Magic Supreme"
);

craftingSystem.addRecipe("Magic Potion", magicPotion, customIngredients, 75);

craftingSystem.displayRecipes();
// Akan menampilkan resep baru "Magic Potion"
```

---

## 5. Forge Examples

### Contoh 5.1: Setup Equipment untuk Upgrade
```java
System.out.println("\n=== CONTOH 5.1: SETUP EQUIPMENT ===");

// Tambahkan equipment yang bisa di-upgrade
Equipment sword = new Equipment(
    500, "Iron Sword", 500, "Pedang besi",
    "Sword", 15, 5, 0
);
myAccount.getInventory().add(sword);

// Tambahkan material untuk upgrade
for (int i = 0; i < 3; i++) {
    myAccount.getInventory().add(new Inqredients(
        601, "Iron Ore", 50, "Biji besi"
    ));
}

System.out.println("Equipment dan material siap untuk upgrade!");
```

### Contoh 5.2: Lihat Equipment yang Bisa Di-upgrade
```java
System.out.println("\n=== CONTOH 5.2: DISPLAY UPGRADABLE EQUIPMENT ===");

ForgeSystem forgeSystem = new ForgeSystem("Blacksmith Tua", 20);
forgeSystem.displayUpgradableEquipment(myAccount);
// Output:
// === FORGE (Blacksmith Tua) ===
// Equipment yang dapat di-upgrade:
// Nama Equipment            | Tipe            | Level  | ATK/DEF
// ──────────────────────────────────────────────────────────────
// 1. Iron Sword             | Sword           | +0     | 15/5
```

### Contoh 5.3: Detail Upgrade Equipment
```java
System.out.println("\n=== CONTOH 5.3: DETAIL UPGRADE ===");

forgeSystem.displayEquipmentUpgradeDetail("Iron Sword", myAccount);
// Output:
// === DETAIL UPGRADE: Iron Sword ===
// Nama: Iron Sword
// Tipe: Sword
// Level Saat Ini: +0
// ATK: 15
// DEF: 5
//
// --- Requirement Upgrade ke Level +1 ---
// Material: Iron Ore x2
// ATK Increase: +5
// DEF Increase: +2
// Material tersedia: 3/2
```

### Contoh 5.4: Upgrade Equipment (Sukses)
```java
System.out.println("\n=== CONTOH 5.4: UPGRADE EQUIPMENT (SUKSES) ===");

System.out.println("Sebelum Upgrade:");
forgeSystem.displayEquipmentUpgradeDetail("Iron Sword", myAccount);

if (forgeSystem.upgradeEquipment("Iron Sword", myAccount)) {
    System.out.println("\nSetelah Upgrade:");
    forgeSystem.displayEquipmentUpgradeDetail("Iron Sword", myAccount);
    
    System.out.println("\nInventory:");
    InventorySystem invSystem = new InventorySystem(myAccount);
    invSystem.displayInventory();
}
```

### Contoh 5.5: Lihat Upgrade Chart
```java
System.out.println("\n=== CONTOH 5.5: UPGRADE CHART ===");

forgeSystem.displayUpgradeChart();
// Output:
// === UPGRADE CHART ===
// Level  | Material         | Jumlah          | ATK+     | DEF+
// ──────────────────────────────────────────────────────────────
// +1     | Iron Ore         | 2               | 5        | 2
// +2     | Iron Ore         | 3               | 8        | 3
// ... dst sampai +10
```

### Contoh 5.6: Multiple Upgrade Levels
```java
System.out.println("\n=== CONTOH 5.6: MULTIPLE UPGRADE ===");

// Siapkan banyak material
for (int i = 0; i < 10; i++) {
    myAccount.getInventory().add(new Inqredients(
        601, "Iron Ore", 50, "Biji besi"
    ));
    myAccount.getInventory().add(new Inqredients(
        602, "Steel Ingot", 100, "Besi tempa"
    ));
}

// Lakukan multiple upgrade
for (int i = 0; i < 5; i++) {
    System.out.println("\n--- Upgrade " + (i+1) + " ---");
    if (forgeSystem.upgradeEquipment("Iron Sword", myAccount)) {
        Equipment sword = findEquipmentInInventory("Iron Sword");
        System.out.println("Sword sekarang: +" + sword.getLevelTempa() + 
                           " (ATK: " + sword.getBonusKekuatan() + 
                           ", DEF: " + sword.getBonusDefense() + ")");
    } else {
        System.out.println("Upgrade gagal!");
        break;
    }
}
```

---

## 6. Integrasi Lengkap

### Contoh Lengkap: Game Loop Sederhana
```java
import java.util.*;
import models.item.*;
import models.character.PlayerCharacter;
import models.account.AccountProfile;
import copilot.*;

public class GameDemoIntegrasi {
    
    private static InventorySystem inventorySystem;
    private static ShopSystem shopSystem;
    private static CraftingSystem craftingSystem;
    private static ForgeSystem forgeSystem;
    private static AccountProfile playerAccount;
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    GAME SYSTEM INTEGRATION DEMO         ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        // 1. Setup
        setupGame();
        
        // 2. Run Demo
        runDemo();
    }
    
    private static void setupGame() {
        System.out.println("\n[SETUP] Inisialisasi sistem game...\n");
        
        // Create inventory
        LinkedList<Item> inventory = new LinkedList<>();
        inventory.add(new Inqredients(1, "Herb Daun", 20, "Daun herbal"));
        inventory.add(new Inqredients(2, "Mineral Blue", 30, "Kristal biru"));
        
        // Create character
        PlayerCharacter mainChar = new PlayerCharacter(
            "Hero", 100, 100, 50, 50, 20, 10, 1,
            0, 1000, "Warrior", false
        );
        
        // Create account
        playerAccount = new AccountProfile(
            "player1", "password123", 2000,
            new PlayerCharacter[]{mainChar},
            inventory, null
        );
        
        // Initialize systems
        inventorySystem = new InventorySystem(playerAccount);
        
        ArrayList<Item> shopItems = new ArrayList<>();
        shopItems.add(new ConsumableFood(
            101, "Healing Potion", 50, "Minuman penyembuh",
            30, 0, 0, 0, "Health"
        ));
        shopItems.add(new Equipment(
            102, "Iron Sword", 500, "Pedang besi",
            "Sword", 15, 5, 0
        ));
        shopItems.add(new Inqredients(
            103, "Steel Ingot", 100, "Besi tempa"
        ));
        shopSystem = new ShopSystem("Toko Prajurit", shopItems, 20);
        
        craftingSystem = new CraftingSystem("Workshop");
        forgeSystem = new ForgeSystem("Forge", 20);
        
        System.out.println("✓ Setup selesai!");
        System.out.println("  Username: " + playerAccount.getUsername());
        System.out.println("  Gold: " + playerAccount.getTotalGold());
        System.out.println("  Inventory: " + inventory.size() + " items");
    }
    
    private static void runDemo() {
        System.out.println("\n[DEMO 1] Inventory Management");
        System.out.println("─".repeat(40));
        inventorySystem.displayInventory();
        
        System.out.println("\n[DEMO 2] Shop System");
        System.out.println("─".repeat(40));
        shopSystem.displayShop();
        
        System.out.println("\n[DEMO 3] Buy Item");
        System.out.println("─".repeat(40));
        if (shopSystem.buyItem("Healing Potion", playerAccount)) {
            System.out.println("Pembelian berhasil!");
        }
        
        System.out.println("\nInventory setelah pembelian:");
        inventorySystem.displayInventory();
        
        System.out.println("\n[DEMO 4] Crafting");
        System.out.println("─".repeat(40));
        craftingSystem.displayRecipes();
        
        System.out.println("\n[DEMO 5] Achievement Summary");
        System.out.println("─".repeat(40));
        System.out.println("✓ Inventory Sorted A-Z");
        System.out.println("✓ Item Purchased");
        System.out.println("✓ Recipes Displayed");
        System.out.println("✓ Upgrade System Ready");
        System.out.println("\nAll systems integrated successfully!");
    }
}
```

### Output yang Diharapkan:
```
╔════════════════════════════════════════╗
║    GAME SYSTEM INTEGRATION DEMO         ║
╚════════════════════════════════════════╝

[SETUP] Inisialisasi sistem game...

✓ Setup selesai!
  Username: player1
  Gold: 2000
  Inventory: 2 items

[DEMO 1] Inventory Management
────────────────────────────────────────
=== INVENTORY (A-Z) ===
Nama Item                 | Tipe            | Harga
────────────────────────────────────────────────────
1. Herb Daun              | Bahan           | 20 gold
2. Mineral Blue           | Bahan           | 30 gold
Total item: 2

... (output lengkap demo berikutnya)
```

---

## Cheatsheet

### Frequently Used Methods

**InventorySystem:**
```java
inventorySystem.displayInventory();
inventorySystem.searchItem("keyword");
inventorySystem.displayItemDetail("itemName");
inventorySystem.useConsumableItem("itemName");
inventorySystem.addItem(item);
```

**ShopSystem:**
```java
shopSystem.displayShop();
shopSystem.buyItem("itemName", playerAccount);
shopSystem.displayItemDetail("itemName");
```

**CraftingSystem:**
```java
craftingSystem.displayRecipes();
craftingSystem.displayRecipeDetail("recipeName");
craftingSystem.craftItem("recipeName", playerAccount);
```

**ForgeSystem:**
```java
forgeSystem.displayUpgradableEquipment(playerAccount);
forgeSystem.displayEquipmentUpgradeDetail("equipName", playerAccount);
forgeSystem.upgradeEquipment("equipName", playerAccount);
forgeSystem.displayUpgradeChart();
```

---

## Troubleshooting

### Issue: "Item tidak ditemukan"
**Solution:** Pastikan nama item exact match (case-sensitive untuk comparison)

### Issue: "Gold tidak mencukupi"
**Solution:** Cek `playerAccount.getTotalGold()` sebelum membeli

### Issue: "Inventory penuh"
**Solution:** Tingkatkan `maxInventorySlots` atau hapus item

### Issue: "Bahan tidak cukup"
**Solution:** Pastikan semua bahan ada di inventory sebelum craft

---

End of Quick Reference Guide

