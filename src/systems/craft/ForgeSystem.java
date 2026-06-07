package systems.craft;
import java.util.*;

import DummyData.forgeformula;
import models.account.AccountProfile;
import models.item.Equipment;
import models.item.Item;
public class ForgeSystem {
    private static final int DEFAULT_MAX_UPGRADE_LEVEL = 10;
    private AccountProfile currentAccount;
    private ArrayList<forgeFormula> daftarFormula;

    public ForgeSystem(AccountProfile currentAccount) {
        this(currentAccount, forgeformula.getDummyForgeFormulas());
    }

    public ForgeSystem(AccountProfile currentAccount, ArrayList<forgeFormula> daftarFormula) {
        this.currentAccount = currentAccount;
        this.daftarFormula = (daftarFormula != null && !daftarFormula.isEmpty())
                ? daftarFormula
                : forgeformula.getDummyForgeFormulas();
    }

    public void setCurrentAccount(AccountProfile currentAccount) {
        this.currentAccount = currentAccount;
    }

    public AccountProfile getCurrentAccount() {
        return currentAccount;
    }

    public void tampilkanEquipment(AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("Profil pemain tidak tersedia!");
            return;
        }

        setCurrentAccount(playerAccount);
        List<Equipment> upgradableEquips = collectEquipment(playerAccount.getInventory());

        if (upgradableEquips.isEmpty()) {
            System.out.println("Anda tidak memiliki equipment untuk di-upgrade!");
            return;
        }

        System.out.printf("%-30s | %-8s | %-6s | %-6s%n", "Nama Equipment", "Level", "ATK", "DEF");
        System.out.println("-".repeat(65));

        int index = 1;
        for (Equipment equip : upgradableEquips) {
            String levelDisplay = "+" + equip.getLevelTempa();
            String status = equip.getLevelTempa() >= DEFAULT_MAX_UPGRADE_LEVEL ? "MAX" : levelDisplay;

            System.out.println(index + ". " + String.format("%-25s | %-8s | %-6d | %-6d",
                    equip.getNamaItem(),
                    status,
                    equip.getBonusKekuatan(),
                    equip.getBonusDefense()));
            index++;
        }
    }
    
    public boolean upgrade(int index, AccountProfile playerAccount) {
        if (playerAccount == null) {
            System.out.println("\nProfil pemain tidak tersedia!");
            return false;
        }

        LinkedList<Item> inventory = playerAccount.getInventory();
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("\nInventory kosong!");
            return false;
        }

        List<Equipment> equipmentList = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Equipment) {
                equipmentList.add((Equipment) item);
            }
        }

        if (index < 1 || index > equipmentList.size()) {
            System.out.println("\nIndex equipment tidak valid!");
            return false;
        }

        Equipment equipment = equipmentList.get(index - 1);
        int currentLevel = equipment.getLevelTempa();

        if (currentLevel >= DEFAULT_MAX_UPGRADE_LEVEL) {
            System.out.println("\nEquipment '" + equipment.getNamaItem() + "' sudah Level Maksimal (+10)!");
            return false;
        }

        int nextLevel = currentLevel + 1;
        forgeFormula formula = findFormulaForLevel(nextLevel);

        if (formula == null) {
            System.out.println("\nFormula upgrade untuk level +" + nextLevel + " tidak ditemukan!");
            return false;
        }

        int availableMaterial = countItemInInventory(inventory, formula.getMaterialName());

        if (availableMaterial < formula.getMaterialAmount()) {
            System.out.println("\nMaterial tidak cukup!");
            System.out.println("  Material: " + formula.getMaterialName());
            System.out.println("  Tersedia: " + availableMaterial);
            System.out.println("  Butuh: " + formula.getMaterialAmount());
            return false;
        }

        System.out.println("\n=== UPGRADE ===");
        System.out.println("Equipment: " + equipment.getNamaItem());
        System.out.println("Level: +" + currentLevel + " → +" + nextLevel);

        removeMaterials(inventory, formula.getMaterialName(), formula.getMaterialAmount());
        System.out.println(" Material '" + formula.getMaterialName() + "' x" + formula.getMaterialAmount() + " digunakan");

        equipment.setLevelTempa(nextLevel);

        int appliedAtk = applyAttackBonus(equipment, formula);
        int appliedDef = applyDefenseBonus(equipment, formula);

        System.out.println("Stats Equipment ditingkatkan:");
        System.out.println("ATK: " + equipment.getBonusKekuatan() + " (+" + appliedAtk + ")");
        System.out.println("DEF: " + equipment.getBonusDefense() + " (+" + appliedDef + ")");

        System.out.println("\nUpgrade berhasil!");
        System.out.println(equipment.getNamaItem() + " sekarang Level +" + nextLevel);

        return true;
    }
    
    private int countItemInInventory(LinkedList<Item> inventory, String itemName) {
        int count = 0;
        for (Item item : inventory) {
            if (item != null && item.getNamaItem().equalsIgnoreCase(itemName)) {
                count++;
            }
        }
        return count;
    }

    private List<Equipment> collectEquipment(LinkedList<Item> inventory) {
        List<Equipment> equipmentList = new ArrayList<>();
        if (inventory == null) {
            return equipmentList;
        }

        for (Item item : inventory) {
            if (item instanceof Equipment) {
                equipmentList.add((Equipment) item);
            }
        }
        return equipmentList;
    }

    private forgeFormula findFormulaForLevel(int level) {
        if (daftarFormula == null || daftarFormula.isEmpty()) {
            return null;
        }

        for (forgeFormula formula : daftarFormula) {
            if (formula.getLevel() == level) {
                return formula;
            }
        }
        return null;
    }

    private void removeMaterials(LinkedList<Item> inventory, String materialName, int amount) {
        int removed = 0;
        for (int i = 0; i < inventory.size() && removed < amount; i++) {
            if (inventory.get(i).getNamaItem().equalsIgnoreCase(materialName)) {
                inventory.remove(i);
                removed++;
                i--;
            }
        }
    }

    private int applyAttackBonus(Equipment equipment, forgeFormula formula) {
        if (!(equipment instanceof models.item.Weapon || equipment instanceof models.item.Accessory)) {
            return 0;
        }

        int appliedAtk = formula.getAtkIncrease();
        equipment.setBonusKekuatan(equipment.getBonusKekuatan() + appliedAtk);
        return appliedAtk;
    }

    private int applyDefenseBonus(Equipment equipment, forgeFormula formula) {
        if (!(equipment instanceof models.item.Armor || equipment instanceof models.item.Accessory)) {
            return 0;
        }

        int appliedDef = formula.getDefIncrease();
        equipment.setBonusDefense(equipment.getBonusDefense() + appliedDef);
        return appliedDef;
    }


    public int getLevelMaks() {
        return DEFAULT_MAX_UPGRADE_LEVEL;
    }

    public ArrayList<forgeFormula> getDaftarFormula() {
        return daftarFormula;
    }

    public void setDaftarFormula(ArrayList<forgeFormula> daftarFormula) {
        this.daftarFormula = daftarFormula;
    }
}


