package systems.craft;

import java.util.*;
import models.account.AccountProfile;
import models.item.Item;
public class CraftingSystem {
    private ArrayList<craftingRecipe> daftarResep;

    public CraftingSystem(ArrayList<craftingRecipe> daftarResep) {
        if (daftarResep != null && !daftarResep.isEmpty()) {
            this.daftarResep = daftarResep;
        } else {
            systems.craft.craftingRecipe[] defaults = DummyData.craftingRecipe.getDummyRecipesArray();
            this.daftarResep = (defaults != null) ? new ArrayList<>(Arrays.asList(defaults)) : new ArrayList<>();
        }
    }

    public void tampilkanResep() {
        if (daftarResep == null || daftarResep.isEmpty()) {
            System.out.println("\n=== CRAFTING ===");
            System.out.println("Belum ada resep tersedia.");
            return;
        }

        System.out.println("\n=== CRAFTING MENU ===");
        System.out.printf("%-4s %-30s %s%n", "No.", "Nama Resep", "Bahan yang dibutuhkan");
        System.out.println("-".repeat(70));

        int idx = 1;
        for (craftingRecipe r : daftarResep) {
            System.out.printf("%-4s %-30.30s%n", idx + ".", r.getRecipeName());
            if (r.getRequiredIngredients() != null && !r.getRequiredIngredients().isEmpty()) {
                int i = 1;
                for (craftingRecipe.IngredientReq req : r.getRequiredIngredients()) {
                    System.out.printf("    %d. %s x%d%n", i, req.getIngredient().getNamaItem(), req.getAmount());
                    i++;
                }
            } else {
                System.out.println("    Tidak membutuhkan bahan");
            }
            idx++;
        }
    }

    public boolean craft(int index, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("Profil pemain tidak tersedia.");
            return false;
        }

        if (daftarResep == null || daftarResep.isEmpty()) {
            System.out.println("Belum ada resep yang terdaftar.");
            return false;
        }

        if (index < 1 || index > daftarResep.size()) {
            System.out.println("Index resep tidak valid.");
            return false;
        }

        LinkedList<Item> inv = playerAccount.getInventory();
        if (inv == null) {
            System.out.println("Inventory tidak tersedia.");
            return false;
        }

        craftingRecipe chosen = daftarResep.get(index - 1);

        ArrayList<Item> temp = new ArrayList<>(inv);
        List<String> missing = new ArrayList<>();
        if (chosen.getRequiredIngredients() != null) {
            for (craftingRecipe.IngredientReq req : chosen.getRequiredIngredients()) {
                String name = req.getIngredient().getNamaItem();
                int amount = req.getAmount();
                for (int k = 0; k < amount; k++) {
                    boolean removed = false;
                    for (int i = 0; i < temp.size(); i++) {
                        if (temp.get(i).getNamaItem().equalsIgnoreCase(name)) {
                            temp.remove(i);
                            removed = true;
                            break;
                        }
                    }
                    if (!removed) {
                        missing.add(name + " (Kurang)");
                        break;
                    }
                }
            }
        }

        if (!missing.isEmpty()) {
            System.out.println("\nCrafting gagal! Bahan kurang:");
            for (String s : missing) System.out.println("  - " + s);
            return false;
        }

        Item result = chosen.getResultItem();
        int resultCount = (result == null) ? 0 : 1;
        int finalSize = temp.size() + resultCount;
        if (finalSize > playerAccount.getMaxInventorySlots()) {
            System.out.println("\nCrafting gagal! Inventory penuh. (Max " + playerAccount.getMaxInventorySlots() + " slot)");
            return false;
        }

        inv.clear();
        inv.addAll(temp);
        if (result != null) inv.add(result);

        System.out.println("\nCrafting '" + chosen.getRecipeName() + "' berhasil! ");
        if (result != null) System.out.println("Item '" + result.getNamaItem() + "' ditambahkan ke inventory.");
        return true;
    }

    public ArrayList<craftingRecipe> getDaftarResep() {
        return daftarResep;
    }

    public void setDaftarResep(ArrayList<craftingRecipe> daftarResep) {
        this.daftarResep = daftarResep;
    }
}


