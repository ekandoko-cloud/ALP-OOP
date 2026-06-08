package systems.encyclopedia;

import java.util.*;
import main.AnsiColors;
import models.character.Monster;
import models.item.Item;
import models.item.Equipment;
import models.location.Location;
import systems.classSystem.ClassNode;
import systems.skill.SkillNode;

public class Encyclopedia {

    private final HashMap<String, Object> indexMonster = new HashMap<>();
    private final HashMap<String, Object> indexIngredientAlam = new HashMap<>();
    private final HashMap<String, Object> indexIngredientMonster = new HashMap<>();
    private final HashMap<String, Object> indexIngredientConsumables = new HashMap<>();
    private final HashMap<String, Object> indexConsumables = new HashMap<>();
    private final HashMap<String, Object> indexWeapon = new HashMap<>();
    private final HashMap<String, Object> indexArmor = new HashMap<>();
    private final HashMap<String, Object> indexAccessory = new HashMap<>();
    private final HashMap<String, Object> indexResep = new HashMap<>();
    private final HashMap<String, Object> indexLokasi = new HashMap<>();
    private final HashMap<String, Object> indexClassTree = new HashMap<>();
    private final HashMap<String, Object> indexSkillTree = new HashMap<>();
    private final HashMap<String, Object> indexUtama = new HashMap<>();
    private ClassNode classTreeRoot;
    private List<SkillNode> skillTreeList;
    public Encyclopedia() {}

    public HashMap<String, Object> getIndexMonster() { return indexMonster; }
    public HashMap<String, Object> getIndexIngredientAlam() { return indexIngredientAlam; }
    public HashMap<String, Object> getIndexIngredientMonster() { return indexIngredientMonster; }
    public HashMap<String, Object> getIndexIngredientConsumables() { return indexIngredientConsumables; }
    public HashMap<String, Object> getIndexConsumables() { return indexConsumables; }
    public HashMap<String, Object> getIndexWeapon() { return indexWeapon; }
    public HashMap<String, Object> getIndexArmor() { return indexArmor; }
    public HashMap<String, Object> getIndexAccessory() { return indexAccessory; }
    public HashMap<String, Object> getIndexResep() { return indexResep; }
    public HashMap<String, Object> getIndexLokasi() { return indexLokasi; }
    public HashMap<String, Object> getIndexClassTree() { return indexClassTree; }
    public HashMap<String, Object> getIndexSkillTree() { return indexSkillTree; }
    public HashMap<String, Object> getIndexUtama() { return indexUtama; }

    public void setClassTreeRoot(ClassNode root) {
        this.classTreeRoot = root;
        traverseAndAddClassNode(root);
    }

    public void setSkillTreeList(List<SkillNode> list) {
        this.skillTreeList = list;
        for (SkillNode sn : list) {
            indexSkillTree.put(sn.getNamaSkill(), sn);
        }
    }

    private void traverseAndAddClassNode(ClassNode node) {
        if (node == null) return;
        indexClassTree.put(node.getNamaClass(), node);
        for (ClassNode child : node.getChildren()) {
            traverseAndAddClassNode(child);
        }
    }

    private void printItemEntry(int i, Item item) {
        System.out.println(AnsiColors.GREEN + "[" + i + "]" + AnsiColors.RESET + " ID: " + item.getIdItem() + " | " + AnsiColors.YELLOW + item.getNamaItem() + AnsiColors.RESET);
        System.out.println("   Harga: " + item.getHargaJual() + " | " + item.getDeskripsi());
    }

    private void printEquipEntry(int i, Item item) {
        String tipe = "";
        if (item instanceof Equipment) tipe = " | Type: " + ((Equipment) item).getTipeEquipment().name();
        System.out.println(AnsiColors.GREEN + "[" + i + "]" + AnsiColors.RESET + " ID: " + item.getIdItem() + " | " + AnsiColors.YELLOW + item.getNamaItem() + AnsiColors.RESET + tipe);
        System.out.println("   Harga: " + item.getHargaJual() + " | " + item.getDeskripsi());
    }

    public void displayMonsterSector() {
        if (indexMonster.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Monster." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.RED + "~~ MONSTER ENCYCLOPEDIA ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexMonster.entrySet()) {
            Monster m = (Monster) entry.getValue();
            System.out.println(AnsiColors.GREEN + "[" + i + "]" + AnsiColors.RESET + " " + AnsiColors.YELLOW + m.getNama() + AnsiColors.RESET);
            System.out.println("   HP: " + m.getMaxHp() + " | STR: " + m.getKekuatan() + " | DEF: " + m.getDefense());
            System.out.println("   " + m.getTriviaPenyakit());
            System.out.println();
            i++;
        }
    }

    public void displayIngredientAlamSector() {
        if (indexIngredientAlam.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Ingredient Alam." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.GREEN + "~~ INGREDIENT ALAM ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexIngredientAlam.entrySet()) {
            printItemEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayIngredientMonsterSector() {
        if (indexIngredientMonster.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Ingredient Monster." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.RED + "~~ INGREDIENT MONSTER ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexIngredientMonster.entrySet()) {
            printItemEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayIngredientConsumablesSector() {
        if (indexIngredientConsumables.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Ingredient Consumables." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.YELLOW + "~~ INGREDIENT CONSUMABLES ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexIngredientConsumables.entrySet()) {
            printItemEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayConsumablesSector() {
        if (indexConsumables.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Consumables." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.GREEN + "~~ CONSUMABLES ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexConsumables.entrySet()) {
            printItemEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayWeaponSector() {
        if (indexWeapon.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Weapon." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.CYAN + "~~ WEAPON ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexWeapon.entrySet()) {
            printEquipEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayArmorSector() {
        if (indexArmor.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Armor." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.CYAN + "~~ ARMOR ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexArmor.entrySet()) {
            printEquipEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayAccessorySector() {
        if (indexAccessory.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Accessory." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.MAGENTA + "~~ ACCESSORY ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexAccessory.entrySet()) {
            printEquipEntry(i, (Item) entry.getValue());
            System.out.println();
            i++;
        }
    }

    public void displayLocationSector() {
        if (indexLokasi.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Location." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.MAGENTA + "~~ LOCATION ENCYCLOPEDIA ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexLokasi.entrySet()) {
            Location l = (Location) entry.getValue();
            System.out.println(AnsiColors.GREEN + "[" + i + "]" + AnsiColors.RESET + " " + AnsiColors.YELLOW + l.getNamaLokasi() + AnsiColors.RESET);
            System.out.println("   " + l.getDeskripsiLokasi());
            System.out.println();
            i++;
        }
    }

    public void displayRecipeSector() {
        if (indexResep.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Crafting Recipes." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.YELLOW + "~~ CRAFTING RECIPE ENCYCLOPEDIA ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexResep.entrySet()) {
            systems.craft.craftingRecipe r = (systems.craft.craftingRecipe) entry.getValue();
            System.out.println(AnsiColors.GREEN + "[" + i + "]" + AnsiColors.RESET + " " + AnsiColors.YELLOW + r.getRecipeName() + AnsiColors.RESET);
            String hasil = r.getResultItem() != null ? r.getResultItem().getNamaItem() : "?";
            System.out.println("   Hasil: " + hasil);
            ArrayList<systems.craft.craftingRecipe.IngredientReq> reqs = r.getRequiredIngredients();
            if (reqs != null && !reqs.isEmpty()) {
                System.out.print("   Bahan: ");
                for (int j = 0; j < reqs.size(); j++) {
                    systems.craft.craftingRecipe.IngredientReq req = reqs.get(j);
                    if (j > 0) System.out.print(", ");
                    System.out.print((req.getIngredient() != null ? req.getIngredient().getNamaItem() : "?") + " x" + req.getAmount());
                }
                System.out.println();
            }
            System.out.println();
            i++;
        }
    }

    public void displayClassTreeSector() {
        if (classTreeRoot == null || indexClassTree.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Class Tree." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.CYAN + "~~ CLASS TREE ENCYCLOPEDIA ~~" + AnsiColors.RESET + "\n");
        printClassTreeRecursive(classTreeRoot, 0);
    }

    private void printClassTreeRecursive(ClassNode node, int depth) {
        String indent = "  ".repeat(depth);
        String status = node.isUnlocked() ? AnsiColors.GREEN + "[UNLOCKED]" + AnsiColors.RESET : AnsiColors.RED + "[LOCKED]" + AnsiColors.RESET;
        System.out.println(indent + "- " + AnsiColors.YELLOW + node.getNamaClass() + AnsiColors.RESET + " (Level " + node.getSyaratLevel() + ") " + status);
        System.out.println(indent + "  " + node.getDeskripsi());
        for (ClassNode child : node.getChildren()) {
            printClassTreeRecursive(child, depth + 1);
        }
    }

    public void displaySkillTreeSector() {
        if (indexSkillTree.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada data Skill Tree." + AnsiColors.RESET);
            return;
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.GREEN + "~~ SKILL TREE ENCYCLOPEDIA ~~" + AnsiColors.RESET + "\n");
        int i = 1;
        for (Map.Entry<String, Object> entry : indexSkillTree.entrySet()) {
            SkillNode s = (SkillNode) entry.getValue();
            String status = s.isUnlocked() ? AnsiColors.GREEN + "[UNLOCKED]" + AnsiColors.RESET : AnsiColors.RED + "[LOCKED]" + AnsiColors.RESET;
            System.out.println(AnsiColors.GREEN + "[" + i + "]" + AnsiColors.RESET + " " + AnsiColors.YELLOW + s.getNamaSkill() + AnsiColors.RESET + " " + status);
            System.out.println("   " + s.getDeskripsi());
            System.out.println("   Biaya: " + s.getBiayaGold() + " gold");
            if (s.getParent() != null) {
                System.out.println("   Prasyarat: " + s.getParent().getNamaSkill());
            }
            System.out.println();
            i++;
        }
    }

    public void displayDetail(Object obj) {
        if (obj == null) {
            System.out.println(AnsiColors.RED + "Data tidak ditemukan." + AnsiColors.RESET);
            return;
        }

        if (obj instanceof Monster m) {
            System.out.println(AnsiColors.BOLD + AnsiColors.RED + "=== DETAIL MONSTER ===" + AnsiColors.RESET);
            System.out.println("Nama              : " + m.getNama());
            System.out.println("HP                : " + m.getCurrentHp() + "/" + m.getMaxHp());
            System.out.println("MP                : " + m.getCurrentMp() + "/" + m.getMaxMp());
            System.out.println("Kekuatan          : " + m.getKekuatan());
            System.out.println("Defense           : " + m.getDefense());
            System.out.println("Trivia Penyakit   : " + m.getTriviaPenyakit());
        } else if (obj instanceof Location l) {
            System.out.println(AnsiColors.BOLD + AnsiColors.MAGENTA + "=== DETAIL LOCATION ===" + AnsiColors.RESET);
            System.out.println("Nama Lokasi       : " + l.getNamaLokasi());
            System.out.println("Deskripsi         : " + l.getDeskripsiLokasi());
        } else if (obj instanceof systems.craft.craftingRecipe r) {
            System.out.println(AnsiColors.BOLD + AnsiColors.YELLOW + "=== DETAIL CRAFTING RECIPE ===" + AnsiColors.RESET);
            System.out.println("Nama Resep        : " + r.getRecipeName());
            System.out.println("Hasil             : " + (r.getResultItem() != null ? r.getResultItem().getNamaItem() : "?"));
            System.out.println("Detail Hasil      :");
            if (r.getResultItem() != null) {
                displayDetail(r.getResultItem());
            } else {
                System.out.println("   - Tidak ada hasil item.");
            }
            ArrayList<systems.craft.craftingRecipe.IngredientReq> reqs = r.getRequiredIngredients();
            System.out.println("Bahan             :");
            if (reqs == null || reqs.isEmpty()) {
                System.out.println("   - Tidak ada bahan.");
            } else {
                for (int i = 0; i < reqs.size(); i++) {
                    systems.craft.craftingRecipe.IngredientReq req = reqs.get(i);
                    String namaBahan = (req.getIngredient() != null ? req.getIngredient().getNamaItem() : "?");
                    System.out.println("   " + (i + 1) + ". " + namaBahan + " x" + req.getAmount());
                }
            }
        } else if (obj instanceof ClassNode cn) {
            System.out.println(AnsiColors.BOLD + AnsiColors.CYAN + "=== DETAIL CLASS ===" + AnsiColors.RESET);
            System.out.println("Nama Class       : " + cn.getNamaClass());
            System.out.println("Deskripsi        : " + cn.getDeskripsi());
            System.out.println("Syarat Level     : " + cn.getSyaratLevel());
            System.out.println("Tipe Class       : " + cn.getTipeClass());
            System.out.println("Status           : " + (cn.isUnlocked() ? "Unlocked" : "Locked"));
            if (cn.getParent() != null) {
                System.out.println("Parent Class     : " + cn.getParent().getNamaClass());
            }
            if (cn.getChildren() != null && !cn.getChildren().isEmpty()) {
                System.out.print("Evolusi ke       : ");
                for (int i = 0; i < cn.getChildren().size(); i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(cn.getChildren().get(i).getNamaClass());
                }
                System.out.println();
            }
        } else if (obj instanceof SkillNode sn) {
            System.out.println(AnsiColors.BOLD + AnsiColors.GREEN + "=== DETAIL SKILL ===" + AnsiColors.RESET);
            System.out.println("Nama Skill       : " + sn.getNamaSkill());
            System.out.println("Deskripsi        : " + sn.getDeskripsi());
            System.out.println("Biaya Gold       : " + sn.getBiayaGold());
            System.out.println("Status           : " + (sn.isUnlocked() ? "Unlocked" : "Locked"));
            if (sn.getParent() != null) {
                System.out.println("Prasyarat        : " + sn.getParent().getNamaSkill());
            }
            if (sn.getChildren() != null && !sn.getChildren().isEmpty()) {
                System.out.print("Skill Turunan    : ");
                for (int i = 0; i < sn.getChildren().size(); i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print(sn.getChildren().get(i).getNamaSkill());
                }
                System.out.println();
            }
        } else if (obj instanceof Item item) {
            System.out.println(AnsiColors.BOLD + AnsiColors.GREEN + "=== DETAIL ITEM ===" + AnsiColors.RESET);
            System.out.println("ID                : " + item.getIdItem());
            System.out.println("Nama              : " + item.getNamaItem());
            System.out.println("Harga Jual        : " + item.getHargaJual() + " gold");
            System.out.println("Tipe              : " + item.getItemType());
            System.out.println("Deskripsi         : " + item.getDeskripsi());

            if (item instanceof Equipment eq) {
                System.out.println();
                System.out.println("--- DETAIL EQUIPMENT ---");
                System.out.println("Type Equipment    : " + eq.getTipeEquipment());
                System.out.println("Level Tempa       : +" + eq.getLevelTempa());
                System.out.println("Bonus Kekuatan    : +" + eq.getBonusKekuatan());
                System.out.println("Bonus Defense     : +" + eq.getBonusDefense());
                System.out.println("Required Class    : " + eq.getRequiredClassType());

                if (item instanceof models.item.Weapon weapon) {
                    System.out.println("Subtipe           : Weapon");
                    System.out.println("Bonus ATK         : +" + weapon.getBonusKekuatan());
                } else if (item instanceof models.item.Armor armor) {
                    System.out.println("Subtipe           : Armor");
                    System.out.println("Bonus DEF         : +" + armor.getBonusDefense());
                } else if (item instanceof models.item.Accessory accessory) {
                    System.out.println("Subtipe           : Accessory");
                    System.out.println("Bonus ATK         : +" + accessory.getBonusKekuatan());
                    System.out.println("Bonus DEF         : +" + accessory.getBonusDefense());
                }
            } else if (item instanceof models.item.ConsumableFood food) {
                System.out.println();
                System.out.println("--- DETAIL CONSUMABLE ---");
                System.out.println("Heal HP           : " + food.getHealHpAmount());
                System.out.println("Heal MP           : " + food.getHealMpAmount());
                System.out.println("Buff Kekuatan     : +" + food.getStrBuff());
                System.out.println("Buff Defense      : +" + food.getDefBuff());
                System.out.println("Info Gizi SDG     : " + food.getInfoGiziSDG());
            }
        } else {
            System.out.println(AnsiColors.YELLOW + "Tipe data: " + obj.getClass().getSimpleName() + AnsiColors.RESET);
        }
    }

    public LinkedHashMap<String, Object> searchEncyclopedia(Scanner inpStr) {
        if (indexUtama.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Encyclopedia kosong." + AnsiColors.RESET);
            return new LinkedHashMap<>();
        }
        System.out.print("Masukkan keyword: ");
        String keyword = inpStr.nextLine().trim().toLowerCase();
        if (keyword.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Keyword tidak boleh kosong." + AnsiColors.RESET);
            return new LinkedHashMap<>();
        }
        System.out.println("\n" + AnsiColors.BOLD + AnsiColors.MAGENTA + "HASIL PENCARIAN: " + keyword + AnsiColors.RESET + "\n");
        int found = 0;
        LinkedHashMap<String, Object> searchResults = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : indexUtama.entrySet()) {
            if (entry.getKey().toLowerCase().contains(keyword)) {
                found++;
                System.out.println(AnsiColors.GREEN + "[" + found + "]" + AnsiColors.RESET + " " + AnsiColors.YELLOW + entry.getKey() + AnsiColors.RESET);
                displayDetail(entry.getValue());
                System.out.println();
                searchResults.put(entry.getKey(), entry.getValue());
            }
        }

        if (found == 0) {
            System.out.println(AnsiColors.RED + "Tidak ada hasil yang ditemukan untuk '" + keyword + "'." + AnsiColors.RESET);
        } else {
            System.out.println(AnsiColors.CYAN + "Ditemukan " + found + " hasil." + AnsiColors.RESET);
        }

        return searchResults;
    }
}
