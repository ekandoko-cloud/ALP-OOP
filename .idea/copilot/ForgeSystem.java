package copilot;

import java.util.*;
import models.item.*;
import models.account.AccountProfile;

/**
 * ForgeSystem - Sistem upgrade equipment/senjata
 *
 * Fitur utama:
 * 1. Menampilkan semua equipment yang bisa di-upgrade
 * 2. Upgrade equipment dengan material
 * 3. Meningkatkan stats equipment (ATK, DEF)
 * 4. Validasi level upgrade maksimal (+10)
 * 5. Mengurangi material dari inventory
 *
 * OOP Concepts:
 * - Encapsulation: Private class UpgradeFormula untuk formula upgrade
 * - Composition: Menggunakan HashMap untuk menyimpan formula upgrade
 * - Inheritance: Polymorphism dengan Equipment class
 * - Abstraction: Menyembunyikan kompleksitas kalkulasi upgrade
 * - Encapsulation: Private inner class untuk formula upgrade
 */
public class ForgeSystem {
    private static final int MAX_UPGRADE_LEVEL = 10;
    private HashMap<Integer, UpgradeFormula> upgradeFormulas;
    private String blacksmithName;
    private int maxInventorySlots;

    /**
     * Inner class untuk merepresentasikan formula upgrade
     * Encapsulated - tidak bisa diakses langsung dari luar
     */
    private static class UpgradeFormula {
        private int level; // Level yang akan dicapai (1-10)
        private int materialRequirement; // Jumlah material yang dibutuhkan
        private int atkIncrease; // Peningkatan ATK
        private int defIncrease; // Peningkatan DEF
        private String materialName; // Nama material yang dibutuhkan

        public UpgradeFormula(int level, String materialName, int materialReq,
                             int atkInc, int defInc) {
            this.level = level;
            this.materialName = materialName;
            this.materialRequirement = materialReq;
            this.atkIncrease = atkInc;
            this.defIncrease = defInc;
        }

        public int getLevel() { return level; }
        public String getMaterialName() { return materialName; }
        public int getMaterialRequirement() { return materialRequirement; }
        public int getAtkIncrease() { return atkIncrease; }
        public int getDefIncrease() { return defIncrease; }
    }

    public ForgeSystem(String blacksmithName, int maxInventorySlots) {
        this.blacksmithName = blacksmithName;
        this.maxInventorySlots = maxInventorySlots;
        this.upgradeFormulas = new HashMap<>();
        initializeUpgradeFormulas();
    }

    /**
     * Inisialisasi formula upgrade untuk setiap level
     * Semakin tinggi level, semakin besar requirement dan bonus
     */
    private void initializeUpgradeFormulas() {
        upgradeFormulas.put(1, new UpgradeFormula(1, "Iron Ore", 2, 5, 2));
        upgradeFormulas.put(2, new UpgradeFormula(2, "Iron Ore", 3, 8, 3));
        upgradeFormulas.put(3, new UpgradeFormula(3, "Steel Ingot", 2, 10, 5));
        upgradeFormulas.put(4, new UpgradeFormula(4, "Steel Ingot", 3, 12, 7));
        upgradeFormulas.put(5, new UpgradeFormula(5, "Mithril Ore", 2, 15, 10));
        upgradeFormulas.put(6, new UpgradeFormula(6, "Mithril Ore", 3, 18, 12));
        upgradeFormulas.put(7, new UpgradeFormula(7, "Mithril Ingot", 2, 20, 15));
        upgradeFormulas.put(8, new UpgradeFormula(8, "Mithril Ingot", 3, 25, 18));
        upgradeFormulas.put(9, new UpgradeFormula(9, "Orichalcum", 3, 30, 20));
        upgradeFormulas.put(10, new UpgradeFormula(10, "Orichalcum", 5, 35, 25));
    }

    /**
     * Menampilkan semua equipment yang bisa di-upgrade
     */
    public void displayUpgradableEquipment(AccountProfile playerAccount) {
        LinkedList<Item> inventory = playerAccount.getInventory();
        List<Equipment> upgradableEquips = new ArrayList<>();

        for (Item item : inventory) {
            if (item instanceof Equipment) {
                upgradableEquips.add((Equipment) item);
            }
        }

        if (upgradableEquips.isEmpty()) {
            System.out.println("\n=== " + blacksmithName + " ===");
            System.out.println("Anda tidak memiliki equipment untuk di-upgrade!");
            return;
        }

        System.out.println("\n=== FORGE (" + blacksmithName + ") ===");
        System.out.println("Equipment yang dapat di-upgrade:");
        System.out.println(String.format("%-30s | %-15s | %-8s | %-8s",
            "Nama Equipment", "Tipe", "Level", "ATK/DEF"));
        System.out.println("-".repeat(70));

        int index = 1;
        for (Equipment equip : upgradableEquips) {
            String levelDisplay = "+" + equip.getLevelTempa();
            String status = equip.getLevelTempa() >= MAX_UPGRADE_LEVEL ? "MAX" : levelDisplay;

            System.out.println(index + ". " + String.format("%-25s | %-15s | %s | %d/%d",
                equip.getNamaItem(),
                equip.getTipeEquipment(),
                status,
                equip.getBonusKekuatan(),
                equip.getBonusDefense()));
            index++;
        }
    }

    /**
     * Menampilkan detail equipment dan requirement upgrade berikutnya
     */
    public void displayEquipmentUpgradeDetail(String equipmentName, AccountProfile playerAccount) {
        Equipment equipment = findEquipmentInInventory(equipmentName, playerAccount);

        if (equipment == null) {
            System.out.println("\n✗ Equipment '" + equipmentName + "' tidak ditemukan!");
            return;
        }

        int currentLevel = equipment.getLevelTempa();

        System.out.println("\n=== DETAIL UPGRADE: " + equipmentName + " ===");
        System.out.println("Nama: " + equipment.getNamaItem());
        System.out.println("Tipe: " + equipment.getTipeEquipment());
        System.out.println("Level Saat Ini: +" + currentLevel);
        System.out.println("ATK: " + equipment.getBonusKekuatan());
        System.out.println("DEF: " + equipment.getBonusDefense());

        if (currentLevel >= MAX_UPGRADE_LEVEL) {
            System.out.println("\n⭐ Equipment sudah mencapai level maksimal (+10)!");
            return;
        }

        // Tampilkan requirement untuk level berikutnya
        int nextLevel = currentLevel + 1;
        UpgradeFormula formula = upgradeFormulas.get(nextLevel);

        if (formula != null) {
            System.out.println("\n--- Requirement Upgrade ke Level +" + nextLevel + " ---");
            System.out.println("Material: " + formula.getMaterialName() + " x" + formula.getMaterialRequirement());
            System.out.println("ATK Increase: +" + formula.getAtkIncrease());
            System.out.println("DEF Increase: +" + formula.getDefIncrease());

            // Cek apakah material tersedia
            int availableMaterial = countItemInInventory(playerAccount.getInventory(), formula.getMaterialName());
            System.out.println("Material tersedia: " + availableMaterial + "/" + formula.getMaterialRequirement());
        }
    }

    /**
     * Upgrade equipment
     *
     * Proses:
     * 1. Validasi equipment ada dan belum level max
     * 2. Cek material tersedia dalam jumlah cukup
     * 3. Kurangi material dari inventory
     * 4. Tingkatkan level dan stats
     * 5. Tampilkan notifikasi sukses
     */
    public boolean upgradeEquipment(String equipmentName, AccountProfile playerAccount) {
        Equipment equipment = findEquipmentInInventory(equipmentName, playerAccount);

        // Validasi 1: Equipment ada
        if (equipment == null) {
            System.out.println("\n✗ Equipment '" + equipmentName + "' tidak ditemukan!");
            return false;
        }

        int currentLevel = equipment.getLevelTempa();

        // Validasi 2: Level belum maksimal
        if (currentLevel >= MAX_UPGRADE_LEVEL) {
            System.out.println("\n✗ Equipment '" + equipmentName + "' sudah Level Maksimal (+10)!");
            return false;
        }

        // Dapatkan formula upgrade berikutnya
        int nextLevel = currentLevel + 1;
        UpgradeFormula formula = upgradeFormulas.get(nextLevel);

        if (formula == null) {
            System.out.println("\n✗ Formula upgrade tidak ditemukan!");
            return false;
        }

        LinkedList<Item> inventory = playerAccount.getInventory();
        int availableMaterial = countItemInInventory(inventory, formula.getMaterialName());

        // Validasi 3: Material tersedia dalam jumlah cukup
        if (availableMaterial < formula.getMaterialRequirement()) {
            System.out.println("\n✗ Material tidak cukup!");
            System.out.println("  Material: " + formula.getMaterialName());
            System.out.println("  Tersedia: " + availableMaterial);
            System.out.println("  Butuh: " + formula.getMaterialRequirement());
            return false;
        }

        // Proses upgrade
        System.out.println("\n=== PROSES UPGRADE ===");
        System.out.println("Equipment: " + equipmentName);
        System.out.println("Level: +" + currentLevel + " → +" + nextLevel);

        // Kurangi material dari inventory
        removeMaterialFromInventory(inventory, formula.getMaterialName(), formula.getMaterialRequirement());
        System.out.println("✓ Material '" + formula.getMaterialName() + "' x" + formula.getMaterialRequirement() + " digunakan");

        // Tingkatkan level equipment
        equipment.setLevelTempa(nextLevel);

        // Tingkatkan stats (cumulative)
        int newAtk = equipment.getBonusKekuatan() + formula.getAtkIncrease();
        int newDef = equipment.getBonusDefense() + formula.getDefIncrease();

        equipment.setBonusKekuatan(newAtk);
        equipment.setBonusDefense(newDef);

        System.out.println("✓ Stats Equipment ditingkatkan:");
        System.out.println("  ATK: " + equipment.getBonusKekuatan() + " (+" + formula.getAtkIncrease() + ")");
        System.out.println("  DEF: " + equipment.getBonusDefense() + " (+" + formula.getDefIncrease() + ")");

        System.out.println("\n✓✓ Upgrade berhasil!");
        System.out.println("  " + equipmentName + " sekarang Level +" + nextLevel);

        return true;
    }

    /**
     * Mencari equipment dalam inventory berdasarkan nama
     */
    private Equipment findEquipmentInInventory(String equipmentName, AccountProfile playerAccount) {
        LinkedList<Item> inventory = playerAccount.getInventory();

        for (Item item : inventory) {
            if (item instanceof Equipment && item.getNamaItem().equalsIgnoreCase(equipmentName)) {
                return (Equipment) item;
            }
        }
        return null;
    }

    /**
     * Menghitung jumlah material tertentu dalam inventory
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
     * Menghapus material dari inventory (menggunakan untuk upgrade)
     */
    private void removeMaterialFromInventory(LinkedList<Item> inventory, String materialName, int amount) {
        int removed = 0;
        ListIterator<Item> iterator = inventory.listIterator();

        while (iterator.hasNext() && removed < amount) {
            Item item = iterator.next();
            if (item.getNamaItem().equalsIgnoreCase(materialName)) {
                iterator.remove();
                removed++;
            }
        }
    }

    /**
     * Menampilkan statistics upgrade untuk semua level
     */
    public void displayUpgradeChart() {
        System.out.println("\n=== UPGRADE CHART ===");
        System.out.println(String.format("%-8s | %-15s | %-15s | %-8s | %-8s",
            "Level", "Material", "Jumlah", "ATK+", "DEF+"));
        System.out.println("-".repeat(65));

        for (int i = 1; i <= MAX_UPGRADE_LEVEL; i++) {
            UpgradeFormula formula = upgradeFormulas.get(i);
            if (formula != null) {
                System.out.println(String.format("%-8s | %-15s | %-15d | %-8d | %-8d",
                    "+" + i,
                    formula.getMaterialName(),
                    formula.getMaterialRequirement(),
                    formula.getAtkIncrease(),
                    formula.getDefIncrease()));
            }
        }
    }

    /**
     * Getter
     */
    public String getBlacksmithName() {
        return blacksmithName;
    }

    public static int getMaxUpgradeLevel() {
        return MAX_UPGRADE_LEVEL;
    }
}

