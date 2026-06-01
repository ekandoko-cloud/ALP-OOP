package systems.classSystem;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import DummyData.classtree;
import java.util.*;

public class ClassSystem {

    public static void classTreeMenu(AccountProfile account, Scanner inpInt, Scanner inpStr) {
        if (account == null) {
            System.out.println("Belum login.");
            return;
        }

        PlayerCharacter[] party = account.getParty();
        if (party == null || party.length == 0) {
            System.out.println("Tidak ada karakter pada party.");
            return;
        }

        ClassNode root = classtree.generateClassTree();
        if (root == null) {
            System.out.println("Tidak ada class tree.");
            return;
        }

        while (true) {
            System.out.println("\n===== CLASS TREE MENU =====");
            // show party
            System.out.println("Pilih karakter (0 untuk kembali):");
            for (int i = 0; i < party.length; i++) {
                PlayerCharacter pc = party[i];
                System.out.println((i+1) + ". " + (pc == null ? "(empty)" : pc.getNama() + " - Lvl " + pc.getLevel() + " - Class: " + pc.getNamaClass()));
            }
            System.out.print("Nomor karakter: ");
            try {
                int pick = Integer.parseInt(inpStr.nextLine().trim());
                if (pick == 0) return;
                if (pick < 1 || pick > party.length) { System.out.println("Pilihan tidak valid."); continue; }
                PlayerCharacter chosen = party[pick-1];
                if (chosen == null) { System.out.println("Slot kosong."); continue; }

                // display available classes from root children based on level
                System.out.println("Available class choices for " + chosen.getNama() + ":");
                List<ClassNode> options = new ArrayList<>();
                for (ClassNode c : root.getChildren()) {
                    if (c == null) continue;
                    if (chosen.getLevel() >= c.getSyaratLevel()) {
                        options.add(c);
                    }
                }
                if (options.isEmpty()) {
                    System.out.println("Tidak ada class yang tersedia saat ini.");
                    continue;
                }
                for (int j = 0; j < options.size(); j++) {
                    ClassNode c = options.get(j);
                    System.out.println((j+1) + ". " + c.getNamaClass() + " - " + c.getDeskripsi() + " (requires lvl " + c.getSyaratLevel() + ")");
                }
                System.out.print("Pilih class (0 batal): ");
                int pickClass = Integer.parseInt(inpStr.nextLine().trim());
                if (pickClass == 0) continue;
                if (pickClass < 1 || pickClass > options.size()) { System.out.println("Pilihan tidak valid."); continue; }
                ClassNode chosenClass = options.get(pickClass-1);
                if (chosen.getLevel() < chosenClass.getSyaratLevel()) { System.out.println("Level tidak cukup."); continue; }

                // apply class: change name and redistribute stats (simple predefined distributions)
                applyClassToCharacter(chosenClass, chosen);
                System.out.println(chosen.getNama() + " sekarang menjadi class: " + chosenClass.getNamaClass());

            } catch (Exception e) {
                System.out.println("Input tidak valid.");
            }
        }
    }

    private static void applyClassToCharacter(ClassNode classNode, PlayerCharacter pc) {
        if (classNode == null || pc == null) return;
        String nama = classNode.getNamaClass();
        pc.setNamaClass(nama);
        // redistribute stats: these are example distributions
        switch (nama) {
            case "Warrior":
            case "Swordsman":
            case "Knight":
            case "Berserker":
                // focus ATK and HP
                pc.setKekuatan(pc.getKekuatan() + 3);
                pc.setDefense(pc.getDefense() + 1);
                pc.setMaxHp(pc.getMaxHp() + 5);
                pc.setCurrentHp(pc.getMaxHp());
                break;
            case "Archer":
            case "Scout":
            case "Marksman":
            case "Ranger":
                pc.setKekuatan(pc.getKekuatan() + 2);
                pc.setDefense(pc.getDefense() + 1);
                pc.setMaxMp(pc.getMaxMp() + 2);
                pc.setCurrentMp(pc.getMaxMp());
                break;
            case "Mage":
            case "Wizard":
            case "Witch":
            case "Archmage":
                pc.setKekuatan(pc.getKekuatan() + 1);
                pc.setMaxMp(pc.getMaxMp() + 5);
                pc.setCurrentMp(pc.getMaxMp());
                break;
            case "Support":
            case "Shieldman":
            case "Angel":
            case "Paladin":
            case "Archangel":
                pc.setDefense(pc.getDefense() + 3);
                pc.setMaxHp(pc.getMaxHp() + 3);
                pc.setCurrentHp(pc.getMaxHp());
                break;
            default:
                break;
        }
    }
}

