package systems.skill;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import java.util.*;

public class SkillSystem {

    public static void skillTreeMenu(AccountProfile account, Scanner inpInt, Scanner inpStr) {
        if (account == null) {
            System.out.println("Belum login.");
            return;
        }

        List<SkillNode> all = DummyData.skilltree.generateSkillTree();
        if (all == null || all.isEmpty()) {
            System.out.println("Tidak ada skill tree.");
            return;
        }

        SkillNode root = null;
        for (SkillNode n : all) if (n.getParent() == null) { root = n; break; }
        if (root == null) root = all.get(0);

        while (true) {
            System.out.println("\n===== SKILL TREE =====");
            // list unlocked
            System.out.println("Unlocked skills:");
            int idx = 1;
            List<SkillNode> available = new ArrayList<>();
            for (SkillNode n : all) {
                if (n == null) continue;
                String mark = n.isUnlocked() ? "[UNLOCKED]" : (n.isAvailable() ? "[AVAILABLE]" : "[LOCKED]");
                System.out.println(idx + ". " + n.getNamaSkill() + " " + mark + " - " + n.getDeskripsi() + " (" + n.getBiayaGold() + "G)");
                if (!n.isUnlocked() && n.isAvailable()) {
                    available.add(n);
                }
                idx++;
            }

            System.out.println("\n[P] Purchase / Unlock  [B] Back");
            System.out.print("Pilihan: ");
            String choice = inpStr.nextLine().trim();
            if (choice.equalsIgnoreCase("B")) return;
            if (choice.equalsIgnoreCase("P")) {
                if (available.isEmpty()) {
                    System.out.println("Tidak ada skill yang tersedia untuk dibeli.");
                    continue;
                }
                System.out.println("Pilih skill available untuk dibeli:");
                for (int i = 0; i < available.size(); i++) {
                    SkillNode n = available.get(i);
                    System.out.println((i+1) + ". " + n.getNamaSkill() + " - " + n.getDeskripsi() + " (" + n.getBiayaGold() + "G)");
                }
                System.out.print("Nomor (0 batal): ");
                try {
                    int pick = Integer.parseInt(inpStr.nextLine().trim());
                    if (pick == 0) continue;
                    if (pick < 1 || pick > available.size()) { System.out.println("Pilihan tidak valid."); continue; }
                    SkillNode chosen = available.get(pick-1);
                    if (account.getTotalGold() < chosen.getBiayaGold()) {
                        System.out.println("Gold tidak mencukupi.");
                        continue;
                    }
                    // deduct
                    account.setTotalGold(account.getTotalGold() - chosen.getBiayaGold());
                    chosen.unlock();
                    applySkillEffect(chosen, account);
                    System.out.println("Skill " + chosen.getNamaSkill() + " berhasil di-unlock!");
                } catch (Exception e) {
                    System.out.println("Input tidak valid.");
                }
            }
        }
    }

    private static void applySkillEffect(SkillNode node, AccountProfile account) {
        if (node == null || account == null) return;
        PlayerCharacter[] party = account.getParty();
        if (party == null || party.length == 0) return;

        String desc = node.getDeskripsi();
        // parse simple patterns: +X ATK, +X Max HP, +X Max MP, +X DEF, +X Slot Inventory
        try {
            if (desc.contains("ATK")) {
                int val = extractLastInt(desc);
                for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(0,0,val,0);
            } else if (desc.contains("Max MP")) {
                int val = extractLastInt(desc);
                for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(0,val,0,0);
            } else if (desc.contains("Max HP")) {
                int val = extractLastInt(desc);
                for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(val,0,0,0);
            } else if (desc.contains("DEF")) {
                int val = extractLastInt(desc);
                for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(0,0,0,val);
            } else if (desc.contains("Slot Inventory")) {
                int val = extractLastInt(desc);
                account.setMaxInventorySlots(account.getMaxInventorySlots() + val);
            }
        } catch (Exception ignored) {}
    }

    private static int extractLastInt(String s) {
        if (s == null) return 0;
        String[] parts = s.split("\\+" );
        if (parts.length == 0) return 0;
        String last = parts[parts.length-1].trim();
        // last might contain number like "5" or "5" after plus
        try {
            String num = last.replaceAll("[^0-9-]", "");
            if (num.isEmpty()) return 0;
            return Integer.parseInt(num);
        } catch (Exception e) { return 0; }
    }
}

