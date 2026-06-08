package systems.classSystem;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import DummyData.classtree;
import java.util.*;

public class ClassSystem {

    public static ClassNode getClassTreeRoot() {
        return classtree.generateClassTree();
    }

    public static List<ClassNode> getAvailableClassOptions(ClassNode root, PlayerCharacter chosen) {
        List<ClassNode> options = new ArrayList<>();
        if (root == null || chosen == null || root.getChildren() == null) return options;

        for (ClassNode c : root.getChildren()) {
            if (c != null && chosen.getLevel() >= c.getSyaratLevel()) {
                options.add(c);
            }
        }
        return options;
    }

    public static void applyClassToCharacter(ClassNode classNode, PlayerCharacter pc) {
        if (classNode == null || pc == null) return;
        String nama = classNode.getNamaClass();
        pc.setNamaClass(nama);
        switch (nama) {
            case "Warrior":
            case "Swordsman":
            case "Knight":
            case "Berserker":
                pc.setKekuatan(pc.getKekuatan() + 20);
                pc.setDefense(pc.getDefense() + 10);
                pc.setMaxHp(pc.getMaxHp() + 100);
                pc.setCurrentHp(pc.getMaxHp());
                break;
            case "Archer":
            case "Scout":
            case "Marksman":
            case "Ranger":
                pc.setKekuatan(pc.getKekuatan() + 30);
                pc.setDefense(pc.getDefense() + 5);
                pc.setMaxMp(pc.getMaxMp() + 50);
                pc.setCurrentMp(pc.getMaxMp());
                break;
            case "Mage":
            case "Wizard":
            case "Witch":
            case "Archmage":
                pc.setKekuatan(pc.getKekuatan() + 50);
                pc.setMaxMp(pc.getMaxMp() + 100);
                pc.setCurrentMp(pc.getMaxMp());
                break;
            case "Support":
            case "Shieldman":
            case "Angel":
            case "Paladin":
            case "Archangel":
                pc.setDefense(pc.getDefense() + 50);
                pc.setMaxHp(pc.getMaxHp() + 150);
                pc.setCurrentHp(pc.getMaxHp());
                break;
            default:
                break;
        }
    }
}

