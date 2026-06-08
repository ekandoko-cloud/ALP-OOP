package systems.skill;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import java.util.*;

public class SkillSystem {

    public static List<SkillNode> getSkillTree() {
        return DummyData.skilltree.generateSkillTree();
    }

    public static List<SkillNode> getAvailableSkills(List<SkillNode> skills) {
        List<SkillNode> purchasable = new ArrayList<>();
        if (skills == null) return purchasable;

        for (SkillNode s : skills) {
            if (s != null && !s.isUnlocked() && s.isAvailable()) {
                purchasable.add(s);
            }
        }
        return purchasable;
    }

    public static void unlockSkill(AccountProfile account, SkillNode chosen) {
        if (account == null || chosen == null) return;
        if (account.getTotalGold() < chosen.getBiayaGold()) return;

        account.setTotalGold(account.getTotalGold() - chosen.getBiayaGold());
        chosen.setUnlocked(true);
        account.addUnlockedSkillName(chosen.getNamaSkill());
        applySkillEffect(chosen, account);
    }

    public static void applySavedUnlocks(List<SkillNode> skills, List<String> unlockedNames) {
        if (skills == null || unlockedNames == null) return;
        for (SkillNode s : skills) {
            if (s != null && unlockedNames.contains(s.getNamaSkill())) {
                s.setUnlocked(true);
            }
        }
    }

    private static void applySkillEffect(SkillNode node, AccountProfile account) {
        if (node == null || account == null) return;
        PlayerCharacter[] party = account.getParty();
        if (party == null || party.length == 0) return;

        String desc = node.getDeskripsi();
        int value = extractFirstInt(desc);

        if (desc.contains("ATK")) {
            for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(0, 0, value, 0);
        } else if (desc.contains("Max HP")) {
            for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(value, 0, 0, 0);
        } else if (desc.contains("Max MP")) {
            for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(0, value, 0, 0);
        } else if (desc.contains("DEF")) {
            for (PlayerCharacter pc : party) if (pc != null) pc.modifikasiStat(0, 0, 0, value);
        } else if (desc.contains("Slot Inventory")) {
            account.setMaxInventorySlots(account.getMaxInventorySlots() + value);
        }
    }

    private static int extractFirstInt(String s) {
        if (s == null) {
            return 0;
        }
        String digits = s.replaceAll("[^0-9]", "");
        return digits.isEmpty() ? 0 : Integer.parseInt(digits);
    }
}
