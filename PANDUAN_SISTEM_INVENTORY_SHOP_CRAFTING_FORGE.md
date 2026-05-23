# PANDUAN LENGKAP SISTEM INVENTORY, SHOP, CRAFTING, DAN FORGE

## Daftar Isi
1. [Pengenalan Umum](#pengenalan-umum)
2. [InventorySystem](#inventorysystem)
3. [ShopSystem](#shopsystem)
4. [CraftingSystem](#craftingsystem)
5. [ForgeSystem](#forgesystem)
6. [Contoh Implementasi](#contoh-implementasi)
7. [OOP Concepts yang Digunakan](#oop-concepts-yang-digunakan)

---

## Pengenalan Umum

Keempat sistem ini dirancang untuk mengelola item, transaksi pembelian, pembuatan item, dan upgrade equipment dalam game RPG. Setiap sistem dibangun dengan prinsip Object-Oriented Programming (OOP) yang solid.

### Struktur File
```
src/copilot/
├── InventorySystem.java     (Sistem manajemen item pemain)
├── ShopSystem.java          (Sistem toko untuk membeli item)
├── CraftingSystem.java      (Sistem membuat item dari bahan)
└── ForgeSystem.java         (Sistem upgrade equipment)
```

### Dependencies
- `LinkedList<Item>` dari `java.util`
- `ArrayList<Item>` dari `java.util`
- `HashMap<String, Integer>` dari `java.util`
- Model classes: `Item`, `Equipment`, `ConsumableFood`, `Inqredients`
- Model classes: `PlayerCharacter`, `AccountProfile`

---

## InventorySystem

### Deskripsi
InventorySystem mengelola item yang dimiliki pemain dengan fitur:
- Menampilkan inventory terurut A-Z
- Mencari item berdasarkan kata kunci
- Melihat detail item
- Menggunakan item consumable
- Menambah/menghapus item

### Constructor
```java
public InventorySystem(AccountProfile playerAccount)
```

**Parameter:**
- `playerAccount`: Profil akun pemain yang berisi inventory

### Method Utama

#### 1. `displayInventory()`
Menampilkan semua item yang tersortir secara alfabetis A-Z.

**Output Contoh:**
```
=== INVENTORY (A-Z) ===
Nama Item                 | Tipe            | Harga
────────────────────────────────────────────────────────
1. Defense Cake           | Makanan         | 120 gold
2. Healing Potion         | Makanan         | 50 gold
3. Mana Elixir            | Makanan         | 75 gold
Total item: 3
```

**Algoritma Sorting:**
- Menggunakan Bubble Sort pada Double Linked List
- Complexity: O(n²) namun optimal untuk data kecil
- Traversal: Dari head ke tail, menukar posisi jika perlu

#### 2. `searchItem(String keyword)`
Mencari item berdasarkan kata kunci dengan linear search.

**Parameter:**
- `keyword`: Kata kunci pencarian (case-insensitive)

**Contoh Penggunaan:**
```java
inventorySystem.searchItem("Potion");
// Output: Menampilkan semua item dengan nama mengandung "Potion"
```

#### 3. `displayItemDetail(String itemName)`
Menampilkan detail lengkap item tertentu.

**Parameter:**
- `itemName`: Nama item

**Output Contoh (Equipment):**
```
=== DETAIL ITEM ===
Nama: Iron Sword
Deskripsi: Pedang besi berkualitas
Harga Jual: 500 gold
Tipe: Equipment

--- Detail Equipment ---
Tipe Equipment: Sword
Bonus Kekuatan: +15
Bonus Defense: +5
Level Upgrade: +3
```

#### 4. `useConsumableItem(String itemName)`
Menggunakan item consumable (menghapus dari inventory).

**Parameter:**
- `itemName`: Nama item yang ingin digunakan

**Return:** `boolean` - true jika berhasil

**Contoh:**
```java
if (inventorySystem.useConsumableItem("Healing Potion")) {
    // Item berhasil dikonsumsi
}
```

#### 5. `addItem(Item newItem)`
Menambahkan item baru ke inventory.

**Parameter:**
- `newItem`: Item object yang akan ditambahkan

**Contoh:**
```java
ConsumableFood potion = new ConsumableFood(
    101, "Mana Elixir", 75, "Minuman penyembuh mana",
    0, 25, 0, 0, "Energy"
);
inventorySystem.addItem(potion);
```

#### 6. `removeItem(String itemName)`
Menghapus item dari inventory.

**Parameter:**
- `itemName`: Nama item yang akan dihapus

**Return:** `boolean` - true jika berhasil

#### 7. `getSortedInventory()`
Mendapatkan inventory yang sudah tersortir A-Z.

**Return:** `LinkedList<Item>` - List item yang terurut

### OOP Concepts dalam InventorySystem

1. **Encapsulation**
   - Private variables: `playerAccount`, `sortedInventory`
   - Private methods: `sortItemsAlphabetically()`, `getItemType()`

2. **Polymorphism**
   - Bekerja dengan Item base class
   - Handling berbagai subclass: Equipment, ConsumableFood, Inqredients

3. **Composition**
   - Menggunakan `LinkedList<Item>` untuk menyimpan inventory
   - Menggunakan `AccountProfile` untuk mengakses data pemain

---

## ShopSystem

### Deskripsi
ShopSystem mengelola toko dengan fitur:
- Menampilkan item yang dijual
- Membeli item dengan validasi gold
- Menampilkan detail item
- Batch buying

### Constructor
```java
public ShopSystem(String shopName, ArrayList<Item> initialItems, int maxInventorySlots)
```

**Parameter:**
- `shopName`: Nama toko
- `initialItems`: Daftar item yang dijual
- `maxInventorySlots`: Maksimal slot inventory pemain

### Method Utama

#### 1. `displayShop()`
Menampilkan semua item yang tersedia di toko.

**Output Contoh:**
```
=== Toko Prajurit ===
Selamat datang di Toko Prajurit!
Nama Item                 | Tipe            | Harga    | Stok
──────────────────────────────────────────────────────────────
1. Iron Sword             | Equipment       | 500 gold | Terbatas
2. Healing Potion         | Makanan         | 50 gold  | Terbatas
3. Steel Ingot            | Bahan           | 100 gold | Terbatas
```

#### 2. `buyItem(String itemName, AccountProfile playerAccount)`
Membeli item dengan validasi lengkap.

**Parameter:**
- `itemName`: Nama item yang dibeli
- `playerAccount`: Profil pemain

**Return:** `boolean` - true jika pembelian berhasil

**Validasi yang Dilakukan:**
1. Item tersedia di toko
2. Gold pemain mencukupi
3. Slot inventory tersedia
4. Jika lulus semua validasi → transaksi berhasil

**Contoh:**
```java
if (shopSystem.buyItem("Healing Potion", playerAccount)) {
    System.out.println("Pembelian berhasil!");
} else {
    System.out.println("Pembelian gagal!");
}
```

**Output Contoh:**
```
✓ Pembelian berhasil!
  Item dibeli: Healing Potion
  Harga: 50 gold
  Gold tersisa: 450
```

#### 3. `buyMultipleItems(String[] itemNames, AccountProfile playerAccount)`
Membeli beberapa item sekaligus.

**Parameter:**
- `itemNames`: Array nama item
- `playerAccount`: Profil pemain

**Contoh:**
```java
String[] itemsToBuy = {"Healing Potion", "Mana Elixir", "Iron Sword"};
shopSystem.buyMultipleItems(itemsToBuy, playerAccount);
```

#### 4. `displayItemDetail(String itemName)`
Menampilkan detail item yang dijual di toko.

**Parameter:**
- `itemName`: Nama item

#### 5. `restockItem(Item item)`
Menambahkan item baru untuk restock.

**Parameter:**
- `item`: Item yang akan ditambahkan

#### 6. `removeItem(String itemName)`
Menghapus item dari toko (discontinued).

**Parameter:**
- `itemName`: Nama item yang dihapus

### Proses Transaksi Pembelian (Detail)

```
START
  ↓
[1] Cek Item Tersedia? 
    ├─ YA → [2]
    └─ TIDAK → Fail: Item tidak ada
  ↓
[2] Cek Gold Cukup?
    ├─ YA → [3]
    └─ TIDAK → Fail: Gold tidak cukup
  ↓
[3] Cek Slot Inventory Kosong?
    ├─ YA → [4]
    └─ TIDAK → Fail: Inventory penuh
  ↓
[4] Proses Transaksi
    ├─ Kurangi Gold Pemain
    ├─ Tambahkan Item ke Inventory
    └─ Tampilkan Notifikasi Sukses
  ↓
END
```

---

## CraftingSystem

### Deskripsi
CraftingSystem mengelola pembuatan item dari resep dengan fitur:
- Menampilkan daftar resep
- Detail resep dan bahan
- Membuat item dengan validasi bahan
- Success rate untuk craft

### Constructor
```java
public CraftingSystem(String workshopName)
```

**Parameter:**
- `workshopName`: Nama workshop/lokasi crafting

### Dummy Recipes (Default)

| Resep | Hasil Item | Bahan | Success |
|-------|-----------|-------|---------|
| Healing Potion | Healing Potion | Herb Daun x2, Mineral Blue x1 | 90% |
| Mana Elixir | Mana Elixir | Crystal Blue x1, Essence Mana x2 | 85% |
| Strength Stew | Strength Stew | Meat Beef x1, Herb Daun x1, Mineral Red x1 | 88% |
| Defense Cake | Defense Cake | Flour Wheat x2, Butter Cow x1, Essence Defense x1 | 82% |

### Method Utama

#### 1. `displayRecipes()`
Menampilkan semua resep yang tersedia.

**Output Contoh:**
```
=== DAFTAR RESEP (Workshop Pertamina) ===
Nama Resep              | Hasil Item         | Success Rate
─────────────────────────────────────────────────────────────
1. Healing Potion       | Healing Potion     | 90%
2. Mana Elixir          | Mana Elixir        | 85%
3. Strength Stew        | Strength Stew      | 88%
4. Defense Cake         | Defense Cake       | 82%
```

#### 2. `displayRecipeDetail(String recipeName)`
Menampilkan detail resep termasuk bahan-bahan.

**Parameter:**
- `recipeName`: Nama resep

**Output Contoh:**
```
=== DETAIL RESEP: Healing Potion ===
Hasil Item: Healing Potion
Deskripsi: Minuman untuk menyembuhkan HP
Success Rate: 90%

Bahan yang dibutuhkan:
  - Herb Daun x2
  - Mineral Blue x1
```

#### 3. `craftItem(String recipeName, AccountProfile playerAccount)`
Membuat item dari resep dengan validasi bahan lengkap.

**Parameter:**
- `recipeName`: Nama resep yang akan dibuat
- `playerAccount`: Profil pemain

**Return:** `boolean` - true jika crafting berhasil

**Proses Crafting:**
1. Validasi resep ada
2. Cek semua bahan tersedia dengan jumlah cukup
3. Jika ada yang kurang → Fail dan tampilkan bahan kurang
4. Kurangi bahan dari inventory
5. Roll success rate (0-100)
6. Jika success → Tambahkan hasil ke inventory
7. Jika fail → Bahan hilang tapi item tidak diperoleh

**Contoh:**
```java
if (craftingSystem.craftItem("Healing Potion", playerAccount)) {
    System.out.println("Crafting berhasil!");
}
```

**Output Contoh (Sukses):**
```
=== PROSES CRAFTING ===
Crafting: Healing Potion
  ✓ Bahan 'Herb Daun' x2 digunakan
  ✓ Bahan 'Mineral Blue' x1 digunakan
  Roll: 75/90 (Success Rate)

✓ Crafting berhasil!
  Item 'Healing Potion' ditambahkan ke inventory!
```

**Output Contoh (Gagal - Bahan Kurang):**
```
✗ Crafting gagal! Bahan kurang:
  - Herb Daun (Tersedia: 1, Butuh: 2)
  - Mineral Blue (Tersedia: 0, Butuh: 1)
```

#### 4. `addRecipe(String recipeName, Item resultItem, HashMap<String, Integer> ingredients, int successRate)`
Menambahkan resep baru ke workshop.

**Parameter:**
- `recipeName`: Nama resep
- `resultItem`: Item yang dihasilkan
- `ingredients`: HashMap bahan yang dibutuhkan
- `successRate`: Persentase kesuksesan (0-100)

**Contoh:**
```java
HashMap<String, Integer> bahanCustom = new HashMap<>();
bahanCustom.put("Gold Dust", 1);
bahanCustom.put("Crystal", 1);

ConsumableFood magicPotion = new ConsumableFood(
    105, "Magic Potion", 200, "Potion ajaib",
    20, 30, 10, 10, "Magic"
);

craftingSystem.addRecipe("Magic Potion", magicPotion, bahanCustom, 75);
```

### Inner Class: Recipe
```java
private static class Recipe {
    private String recipeName;
    private Item resultItem;
    private HashMap<String, Integer> requiredIngredients;
    private int successRate;
    // ... getter methods
}
```

**Keuntungan Inner Class:**
- Encapsulation penuh terhadap struktur resep
- Tidak bisa di-instantiate dari luar
- Lebih aman dan terorganisir

### OOP Concepts dalam CraftingSystem

1. **Encapsulation**
   - Recipe sebagai inner class private
   - Private HashMap untuk menyimpan resep
   - Private methods untuk helper functions

2. **Abstraction**
   - User hanya tahu interface public
   - Complexity calculation tersembunyi

3. **Composition**
   - HashMap<String, Recipe> untuk menyimpan resep
   - Item composition untuk result template

---

## ForgeSystem

### Deskripsi
ForgeSystem mengelola upgrade equipment dengan fitur:
- Menampilkan equipment yang bisa di-upgrade
- Menampilkan detail upgrade dan requirement
- Upgrade equipment dengan formula
- Validasi level max +10
- Peningkatan stats otomatis

### Constructor
```java
public ForgeSystem(String blacksmithName, int maxInventorySlots)
```

**Parameter:**
- `blacksmithName`: Nama NPC blacksmith/tempat upgrade
- `maxInventorySlots`: Maksimal slot inventory

### Upgrade Formula (Level 1-10)

| Level | Material | Jumlah | ATK+ | DEF+ |
|-------|----------|--------|------|------|
| +1 | Iron Ore | 2 | 5 | 2 |
| +2 | Iron Ore | 3 | 8 | 3 |
| +3 | Steel Ingot | 2 | 10 | 5 |
| +4 | Steel Ingot | 3 | 12 | 7 |
| +5 | Mithril Ore | 2 | 15 | 10 |
| +6 | Mithril Ore | 3 | 18 | 12 |
| +7 | Mithril Ingot | 2 | 20 | 15 |
| +8 | Mithril Ingot | 3 | 25 | 18 |
| +9 | Orichalcum | 3 | 30 | 20 |
| +10 | Orichalcum | 5 | 35 | 25 |

### Method Utama

#### 1. `displayUpgradableEquipment(AccountProfile playerAccount)`
Menampilkan semua equipment yang bisa di-upgrade.

**Parameter:**
- `playerAccount`: Profil pemain

**Output Contoh:**
```
=== FORGE (Blacksmith Tua) ===
Equipment yang dapat di-upgrade:
Nama Equipment            | Tipe            | Level  | ATK/DEF
──────────────────────────────────────────────────────────────
1. Iron Sword             | Sword           | +3     | 20/10
2. Steel Shield           | Shield          | +1     | 5/15
3. Leather Armor          | Armor           | MAX    | 10/20
```

#### 2. `displayEquipmentUpgradeDetail(String equipmentName, AccountProfile playerAccount)`
Menampilkan detail upgrade termasuk requirement level berikutnya.

**Parameter:**
- `equipmentName`: Nama equipment
- `playerAccount`: Profil pemain

**Output Contoh:**
```
=== DETAIL UPGRADE: Iron Sword ===
Nama: Iron Sword
Tipe: Sword
Level Saat Ini: +3
ATK: 20
DEF: 10

--- Requirement Upgrade ke Level +4 ---
Material: Steel Ingot x3
ATK Increase: +12
DEF Increase: +7
Material tersedia: 1/3
```

#### 3. `upgradeEquipment(String equipmentName, AccountProfile playerAccount)`
Melakukan upgrade pada equipment.

**Parameter:**
- `equipmentName`: Nama equipment yang di-upgrade
- `playerAccount`: Profil pemain

**Return:** `boolean` - true jika upgrade berhasil

**Proses Upgrade:**
1. Validasi equipment ada
2. Validasi level belum maksimal (+10)
3. Ambil formula untuk level berikutnya
4. Validasi material tersedia dengan jumlah cukup
5. Jika material kurang → Fail
6. Kurangi material dari inventory
7. Tingkatkan level equipment
8. Tambahkan stats sesuai formula
9. Tampilkan notifikasi sukses

**Contoh:**
```java
if (forgeSystem.upgradeEquipment("Iron Sword", playerAccount)) {
    System.out.println("Upgrade berhasil!");
}
```

**Output Contoh (Sukses):**
```
=== PROSES UPGRADE ===
Equipment: Iron Sword
Level: +3 → +4
✓ Material 'Steel Ingot' x3 digunakan
✓ Stats Equipment ditingkatkan:
  ATK: 32 (+12)
  DEF: 17 (+7)

✓✓ Upgrade berhasil!
  Iron Sword sekarang Level +4
```

**Output Contoh (Gagal - Material Kurang):**
```
✗ Material tidak cukup!
  Material: Steel Ingot
  Tersedia: 1
  Butuh: 3
```

#### 4. `displayUpgradeChart()`
Menampilkan tabel formula upgrade untuk semua level.

**Output Contoh:**
```
=== UPGRADE CHART ===
Level  | Material         | Jumlah          | ATK+     | DEF+
──────────────────────────────────────────────────────────────
+1     | Iron Ore         | 2               | 5        | 2
+2     | Iron Ore         | 3               | 8        | 3
+3     | Steel Ingot      | 2               | 10       | 5
... (dst sampai level +10)
```

### Inner Class: UpgradeFormula
```java
private static class UpgradeFormula {
    private int level;
    private String materialName;
    private int materialRequirement;
    private int atkIncrease;
    private int defIncrease;
    // ... getter methods
}
```

### OOP Concepts dalam ForgeSystem

1. **Encapsulation**
   - UpgradeFormula sebagai inner class private
   - Private HashMap untuk menyimpan formula
   - Constant untuk MAX_UPGRADE_LEVEL

2. **Composition**
   - HashMap<Integer, UpgradeFormula> untuk formula
   - Bekerja dengan Equipment class

3. **Abstraction**
   - User fokus pada upgrade
   - Detail formula tersembunyi

---

## Contoh Implementasi

### Setup Awal
```java
// Import yang diperlukan
import java.util.*;
import models.item.*;
import models.account.AccountProfile;
import models.character.PlayerCharacter;
import copilot.*;

public class GameMain {
    public static void main(String[] args) {
        // 1. Buat inventory pemain
        LinkedList<Item> playerInventory = new LinkedList<>();
        playerInventory.add(new Inqredients(1, "Herb Daun", 20, "Daun herbal"));
        playerInventory.add(new Inqredients(2, "Iron Ore", 50, "Biji besi"));
        
        // 2. Buat character dan account
        PlayerCharacter mainChar = new PlayerCharacter(
            "Hero", 100, 100, 50, 50, 20, 10, 1,
            0, 1000, "Warrior", false
        );
        
        AccountProfile playerAccount = new AccountProfile(
            "player1", "password123", 1000, new PlayerCharacter[]{mainChar},
            playerInventory, null
        );
        
        // 3. Inisialisasi sistem
        InventorySystem inventorySystem = new InventorySystem(playerAccount);
        
        // 4. Tabel item toko
        ArrayList<Item> shopItems = new ArrayList<>();
        shopItems.add(new Equipment(101, "Iron Sword", 500, 
            "Pedang besi berkualitas", "Sword", 15, 5, 0));
        shopItems.add(new ConsumableFood(102, "Healing Potion", 50,
            "Minuman penyembuh HP", 30, 0, 0, 0, "Health"));
        
        ShopSystem shopSystem = new ShopSystem("Toko Prajurit", shopItems, 20);
        
        CraftingSystem craftingSystem = new CraftingSystem("Workshop Pertamina");
        ForgeSystem forgeSystem = new ForgeSystem("Blacksmith Tua", 20);
    }
}
```

### Skenario 1: Membeli Item dan Melihat Inventory
```java
// Tampilkan inventory awal
inventorySystem.displayInventory();

// Beli item dari toko
if (shopSystem.buyItem("Healing Potion", playerAccount)) {
    System.out.println("\n--- Inventory setelah pembelian ---");
    inventorySystem.displayInventory();
}
```

### Skenario 2: Craft Item
```java
// Tampilkan resep yang tersedia
craftingSystem.displayRecipes();

// Tampilkan detail resep
craftingSystem.displayRecipeDetail("Healing Potion");

// Coba craft item
if (craftingSystem.craftItem("Healing Potion", playerAccount)) {
    System.out.println("Craft berhasil!");
    inventorySystem.displayInventory();
} else {
    System.out.println("Craft gagal - bahan tidak cukup");
}
```

### Skenario 3: Upgrade Equipment
```java
// Tambahkan equipment ke inventory pemain
Equipment sword = new Equipment(101, "Iron Sword", 500,
    "Pedang besi", "Sword", 15, 5, 0);
playerInventory.add(sword);

// Tambahkan material upgrade ke inventory
for (int i = 0; i < 3; i++) {
    playerInventory.add(new Inqredients(201, "Steel Ingot", 100, "Besi tempa"));
}

// Tampilkan equipment yang bisa di-upgrade
forgeSystem.displayUpgradableEquipment(playerAccount);

// Tampilkan detail upgrade
forgeSystem.displayEquipmentUpgradeDetail("Iron Sword", playerAccount);

// Lakukan upgrade
if (forgeSystem.upgradeEquipment("Iron Sword", playerAccount)) {
    System.out.println("Upgrade sukses!");
    forgeSystem.displayEquipmentUpgradeDetail("Iron Sword", playerAccount);
}
```

---

## OOP Concepts yang Digunakan

### 1. Encapsulation (Enkapsulasi)
Menyembunyikan kompleksitas internal dan hanya expose interface yang diperlukan.

**Contoh:**
```java
// Private variables
private HashMap<String, Recipe> recipes;
private String workshopName;

// Public methods untuk akses yang terkontrol
public void displayRecipes() { ... }
public boolean craftItem(String recipeName, ...) { ... }
```

### 2. Inheritance (Pewarisan)
Reuse code dari class parent.

**Hierarki Classes:**
```
Item (base class)
├── Equipment (extends Item)
├── ConsumableFood (extends Item)
└── Inqredients (extends Item)
```

### 3. Polymorphism (Polimorfisme)
Satu interface, multiple implementations.

**Contoh:**
```java
// Bekerja dengan base class Item
for (Item item : inventory) {
    if (item instanceof Equipment) {
        handleEquipment((Equipment) item);
    } else if (item instanceof ConsumableFood) {
        handleFood((ConsumableFood) item);
    }
}
```

### 4. Abstraction (Abstraksi)
Menyembunyikan detail teknis, fokus pada functionality.

**Contoh:**
```java
// User tidak perlu tahu detail sorting
public void displayInventory() {
    LinkedList<Item> sorted = sortItemsAlphabetically();
    // Print sorted items
}
```

### 5. Composition (Komposisi)
Membangun class yang kompleks dari class yang lebih sederhana.

**Contoh:**
```java
public class InventorySystem {
    private AccountProfile playerAccount;  // Composition
    private LinkedList<Item> sortedInventory;  // Composition
}
```

### 6. Inner Classes (Private)
Mengorganisir code dengan inner class untuk encapsulation lebih kuat.

**Contoh:**
```java
private static class Recipe {
    // Tidak bisa diakses dari luar
    // Dapat diakses hanya oleh CraftingSystem
}
```

---

## Best Practices

1. **Null Checking**
   ```java
   if (item == null) {
       System.out.println("Item tidak valid!");
       return;
   }
   ```

2. **Input Validation**
   ```java
   if (playerAccount.getTotalGold() < totalPrice) {
       System.out.println("Gold tidak mencukupi!");
       return false;
   }
   ```

3. **Clear Error Messages**
   ```java
   System.out.println("✗ Gold tidak mencukupi!");
   System.out.println("  Gold Anda: " + playerAccount.getTotalGold());
   System.out.println("  Harga Item: " + totalPrice);
   ```

4. **Meaningful Method Names**
   ```java
   displayInventory()      // Jelas apa yang dilakukan
   findEquipmentInInventory()  // Deskriptif
   calculateUpgradeStats()     // Spesifik
   ```

---

## Kesimpulan

Keempat sistem ini dirancang dengan prinsip OOP yang solid:
- ✅ Encapsulation untuk proteksi data
- ✅ Polymorphism untuk fleksibilitas
- ✅ Composition untuk reusability
- ✅ Abstraction untuk clarity

Setiap sistem dapat diintegrasikan dengan game engine utama dan mudah di-extend untuk fitur tambahan.

