package copilot;

import java.util.*;
import models.item.*;
import models.account.AccountProfile;

/**
 * CraftingSystem - Sistem pembuatan item dari bahan-bahan
 *
 * Fitur utama:
 * 1. Menampilkan daftar resep yang tersedia
 * 2. Membuat item berdasarkan resep
 * 3. Validasi bahan-bahan di inventory
 * 4. Mengurangi bahan dari inventory
 * 5. Menambahkan hasil craft ke inventory
 *
 * OOP Concepts:
 * - Encapsulation: Private class Recipe untuk menyembunyikan detail resep
 * - Composition: Menggunakan HashMap untuk menyimpan resep dan ingredients
 * - Inheritance: Polymorphism dengan Item base class
 * - Abstraction: Menyembunyikan kompleksitas proses crafting
 * - Encapsulation: Private inner class Recipe
 */
public class CraftingSystem {
    private static final int MAX_INVENTORY_SLOTS = 10;
    private HashMap<String, Recipe> recipes;
    private String workshopName;

    /**
     * Inner class untuk merepresentasikan resep crafting
     * Encapsulated - tidak bisa diakses langsung dari luar
     */
    private static class Recipe {
        private String recipeName;
        private Item resultItem;
        private HashMap<String, Integer> requiredIngredients; // nama item -> jumlah
        private int successRate; // 0-100

        public Recipe(String recipeName, Item resultItem,
                     HashMap<String, Integer> requiredIngredients, int successRate) {
            this.recipeName = recipeName;
            this.resultItem = resultItem;
            this.requiredIngredients = new HashMap<>(requiredIngredients);
            this.successRate = successRate;
        }

        public String getRecipeName() { return recipeName; }
        public Item getResultItem() { return resultItem; }
        public HashMap<String, Integer> getRequiredIngredients() { return requiredIngredients; }
        public int getSuccessRate() { return successRate; }
    }

    public CraftingSystem(String workshopName) {
        this.workshopName = workshopName;
        this.recipes = new HashMap<>();
        initializeDummyRecipes();
    }

    /**
     * Inisialisasi dummy recipes untuk testing
     */
    private void initializeDummyRecipes() {
        // Recipe 1: Healing Potion
        HashMap<String, Integer> recipe1 = new HashMap<>();
        recipe1.put("Herb Daun", 2);
        recipe1.put("Mineral Blue", 1);
        ConsumableFood healingPotion = new ConsumableFood(
            101, "Healing Potion", 50, "Minuman untuk menyembuhkan HP",
            30, 0, 0, 0, "Health"
        );
        recipes.put("Healing Potion", new Recipe("Healing Potion", healingPotion, recipe1, 90));

        // Recipe 2: Mana Elixir
        HashMap<String, Integer> recipe2 = new HashMap<>();
        recipe2.put("Crystal Blue", 1);
        recipe2.put("Essence Mana", 2);
        ConsumableFood manaElixir = new ConsumableFood(
            102, "Mana Elixir", 75, "Minuman untuk menyembuhkan MP",
            0, 25, 0, 0, "Energy"
        );
        recipes.put("Mana Elixir", new Recipe("Mana Elixir", manaElixir, recipe2, 85));

        // Recipe 3: Strength Stew
        HashMap<String, Integer> recipe3 = new HashMap<>();
        recipe3.put("Meat Beef", 1);
        recipe3.put("Herb Daun", 1);
        recipe3.put("Mineral Red", 1);
        ConsumableFood strengthStew = new ConsumableFood(
            103, "Strength Stew", 100, "Makanan untuk buff ATK",
            10, 0, 15, 0, "Strength"
        );
        recipes.put("Strength Stew", new Recipe("Strength Stew", strengthStew, recipe3, 88));

        // Recipe 4: Defense Cake
        HashMap<String, Integer> recipe4 = new HashMap<>();
        recipe4.put("Flour Wheat", 2);
        recipe4.put("Butter Cow", 1);
        recipe4.put("Essence Defense", 1);
        ConsumableFood defenseCake = new ConsumableFood(
            104, "Defense Cake", 120, "Makanan untuk buff DEF",
            15, 0, 0, 12, "Defense"
        );
        recipes.put("Defense Cake", new Recipe("Defense Cake", defenseCake, recipe4, 82));
    }

    /**
     * Menampilkan semua resep yang tersedia
     */
    public void displayRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("\n=== " + workshopName + " ===");
            System.out.println("Tidak ada resep tersedia!");
            return;
        }

        System.out.println("\n=== DAFTAR RESEP (" + workshopName + ") ===");
        System.out.println(String.format("%-25s | %-15s | %-15s", "Nama Resep", "Hasil Item", "Success Rate"));
        System.out.println("-".repeat(60));

        int index = 1;
        for (Recipe recipe : recipes.values()) {
            System.out.println(index + ". " + String.format("%-20s | %-15s | %d%%",
                recipe.getRecipeName(),
                recipe.getResultItem().getNamaItem(),
                recipe.getSuccessRate()));
            index++;
        }
    }

    /**
     * Menampilkan detail resep tertentu beserta bahan-bahannya
     */
    public void displayRecipeDetail(String recipeName) {
        Recipe recipe = recipes.get(recipeName);

        if (recipe == null) {
            System.out.println("\nResep '" + recipeName + "' tidak ditemukan!");
            return;
        }

        Item result = recipe.getResultItem();

        System.out.println("\n=== DETAIL RESEP: " + recipeName + " ===");
        System.out.println("Hasil Item: " + result.getNamaItem());
        System.out.println("Deskripsi: " + result.getDeskripsi());
        System.out.println("Success Rate: " + recipe.getSuccessRate() + "%");
        System.out.println("\nBahan yang dibutuhkan:");

        for (Map.Entry<String, Integer> ingredient : recipe.getRequiredIngredients().entrySet()) {
            System.out.println("  - " + ingredient.getKey() + " x" + ingredient.getValue());
        }
    }

    /**
     * Membuat item berdasarkan resep
     *
     * Proses:
     * 1. Validasi resep ada
     * 2. Validasi inventory tidak penuh
     * 3. Validasi semua bahan tersedia dalam jumlah cukup
     * 4. Kurangi bahan dari inventory
     * 5. Cek success rate
     * 6. Jika sukses, tambahkan hasil ke inventory
     */
    public boolean craftItem(String recipeName, AccountProfile playerAccount) {
        Recipe recipe = recipes.get(recipeName);

        // Validasi 1: Cek resep ada
        if (recipe == null) {
            System.out.println("\n✗ Resep '" + recipeName + "' tidak ditemukan!");
            return false;
        }

        LinkedList<Item> playerInventory = playerAccount.getInventory();
        
        // Validasi 2: Cek inventory tidak penuh
        if (playerInventory.size() >= MAX_INVENTORY_SLOTS) {
            System.out.println("\n✗ Crafting gagal! Inventory penuh! (Max " + MAX_INVENTORY_SLOTS + " slot)");
            System.out.println("Slot yang digunakan: " + playerInventory.size() + "/" + MAX_INVENTORY_SLOTS);
            return false;
        }

        HashMap<String, Integer> requiredItems = recipe.getRequiredIngredients();

        // Validasi 3: Cek semua bahan tersedia
        List<String> missingItems = checkMissingIngredients(playerInventory, requiredItems);
        if (!missingItems.isEmpty()) {
            System.out.println("\n✗ Crafting gagal! Bahan kurang:");
            for (String missing : missingItems) {
                System.out.println("  - " + missing);
            }
            return false;
        }

        System.out.println("\n=== PROSES CRAFTING ===");
        System.out.println("Crafting: " + recipeName);

        // Kurangi bahan dari inventory
        for (Map.Entry<String, Integer> ingredient : requiredItems.entrySet()) {
            removeIngredientsFromInventory(playerInventory, ingredient.getKey(), ingredient.getValue());
            System.out.println("  ✓ Bahan '" + ingredient.getKey() + "' x" + ingredient.getValue() + " digunakan");
        }

        // Cek success rate
        int successRate = recipe.getSuccessRate();
        int randomNumber = new Random().nextInt(100);
        boolean success = randomNumber < successRate;

        System.out.println("  Roll: " + randomNumber + "/" + successRate + " (Success Rate)");

        if (!success) {
            System.out.println("\n✗ Crafting gagal! Bahan habis tetapi hasil tidak diperoleh.");
            return false;
        }

        // Tambahkan hasil ke inventory (buat copy dari template)
        Item resultTemplate = recipe.getResultItem();
        Item resultItem = createCraftResultCopy(resultTemplate);
        playerInventory.add(resultItem);

        System.out.println("\n✓ Crafting berhasil!");
        System.out.println("  Item '" + resultItem.getNamaItem() + "' ditambahkan ke inventory!");
        System.out.println("  Slot tersisa: " + (MAX_INVENTORY_SLOTS - playerInventory.size()) + "/" + MAX_INVENTORY_SLOTS);

        return true;
    }

    /**
     * Mencari bahan yang hilang/kurang dari inventory
     */
    private List<String> checkMissingIngredients(LinkedList<Item> inventory,
                                                  HashMap<String, Integer> required) {
        List<String> missing = new ArrayList<>();

        for (Map.Entry<String, Integer> requirement : required.entrySet()) {
            String itemName = requirement.getKey();
            int neededAmount = requirement.getValue();
            int availableAmount = countItemInInventory(inventory, itemName);

            if (availableAmount < neededAmount) {
                missing.add(itemName + " (Tersedia: " + availableAmount + ", Butuh: " + neededAmount + ")");
            }
        }

        return missing;
    }

    /**
     * Menghitung jumlah item tertentu dalam inventory
     */
    private int countItemInInventory(LinkedList<Item> inventory, String itemName) {
        int count = 0;
        for (Item item : inventory) {
            if (item.getNamaItem().equalsIgnoreCase(itemName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Menghapus item dari inventory (menggunakan bahan)
     */
    private void removeIngredientsFromInventory(LinkedList<Item> inventory, String itemName, int amount) {
        int removed = 0;
        ListIterator<Item> iterator = inventory.listIterator();

        while (iterator.hasNext() && removed < amount) {
            Item item = iterator.next();
            if (item.getNamaItem().equalsIgnoreCase(itemName)) {
                iterator.remove();
                removed++;
            }
        }
    }

    /**
     * Membuat copy dari hasil crafting
     */
    private Item createCraftResultCopy(Item template) {
        if (template instanceof ConsumableFood) {
            ConsumableFood food = (ConsumableFood) template;
            return new ConsumableFood(
                food.getIdItem(), food.getNamaItem(), food.getHargaJual(),
                food.getDeskripsi(), food.getHealHpAmount(), food.getHealMpAmount(),
                food.getTempStrBuff(), food.getTempDefBuff(), food.getInfoGiziSDG()
            );
        }
        return null;
    }

    /**
     * Menambahkan resep baru ke sistem crafting
     */
    public void addRecipe(String recipeName, Item resultItem,
                         HashMap<String, Integer> ingredients, int successRate) {
        Recipe newRecipe = new Recipe(recipeName, resultItem, ingredients, successRate);
        recipes.put(recipeName, newRecipe);
        System.out.println("✓ Resep '" + recipeName + "' ditambahkan ke workshop.");
    }

    /**
     * Getter
     */
    public String getWorkshopName() {
        return workshopName;
    }

    public int getRecipeCount() {
        return recipes.size();
    }

    public HashMap<String, Recipe> getRecipes() {
        return recipes;
    }
}

