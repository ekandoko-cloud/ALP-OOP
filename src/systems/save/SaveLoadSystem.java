package systems.save;

import java.io.*;
import java.util.*;

import enums.ItemType;
import enums.ClassType;
import enums.EquipmentType;
import enums.StatusLokasi;
import models.account.AccountProfile;
import models.character.PlayerCharacter;
import models.item.ConsumableFood;
import models.item.Accessory;
import models.item.Armor;
import models.item.Equipment;
import models.item.Ingredients;
import models.item.Item;
import models.item.Weapon;
import models.quest.MainQuest;
import models.quest.Quest;
import models.quest.SubQuest;
import systems.quest.QuestTracker;

public class SaveLoadSystem {
    public String SAVE_FOLDER = "src/saves/";
    public String extension = ".txt";
    public String basicInfo = "[BASIC INFO]";
    public String party = "[PARTY INFO]";
    public String inventory = "[INVENTORY INFO]";
    public String quest = "[QUEST INFO]";
    public String skillSection = "[SKILL INFO]";
    public String locationSection = "[LOCATION INFO]";

    public void save(AccountProfile profile) {
        File saveFolder = new File(SAVE_FOLDER);
        if (!saveFolder.exists()) {
            if (!saveFolder.mkdirs()) {
                System.out.println("Warning: gagal membuat folder save " + SAVE_FOLDER);
            }
        }

        String fileName = SAVE_FOLDER + profile.getUsername() + extension;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //basicInfo
            writer.write(basicInfo);
            writer.newLine();
            writer.write("username=" + profile.getUsername());
            writer.newLine();
            writer.write("totalGold=" + profile.getTotalGold());
            writer.newLine();
            writer.write("maxInventorySlots=" + profile.getMaxInventorySlots());
            writer.newLine();
            writer.write("areaName=" + profile.getAreaName());
            writer.newLine();
            writer.newLine();

            //party
            writer.write(party);
            writer.newLine();
            PlayerCharacter[] party = profile.getParty();
            if (party != null) {
                for (PlayerCharacter karakter : party) {
                    if (karakter == null) {
                        continue;
                    }
                    writer.write("karakter=" + karakter.getNama() + "^" + karakter.getNamaClass() + "^" + karakter.getLevel() + "^" + karakter.getMaxHp() + "^" + karakter.getCurrentHp() + "^" + karakter.getMaxMp() + "^" + karakter.getCurrentMp() + "^" + karakter.getKekuatan() + "^" + karakter.getDefense() + "^" + karakter.getCurrentExp() + "^" + karakter.getMaxExp() + "^" + (karakter.getCurrentWeapon() != null ? karakter.getCurrentWeapon().getIdItem() : 0) + "^" + (karakter.getCurrentArmor() != null ? karakter.getCurrentArmor().getIdItem() : 0) + "^" + (karakter.getCurrentAccessory() != null ? karakter.getCurrentAccessory().getIdItem() : 0));
                    writer.newLine();
                }
            }
            writer.newLine();

            //inven
            writer.write(inventory);
            writer.newLine();
            LinkedList<Item> inven = profile.getInventory();
            if (inven != null) {
                for (Item item : inven) {
                    if (item instanceof Ingredients) {
                        writer.write("inqredient=" + item.getIdItem() + "^" + item.getNamaItem() + "^" + item.getDeskripsi() + "^" + item.getHargaJual());
                        writer.newLine();
                    } else if (item instanceof Equipment equipment) {
                        writer.write("equipment=" + equipment.getIdItem() + "^" + equipment.getNamaItem() + "^" + equipment.getDeskripsi() + "^" + equipment.getHargaJual() + "^" + equipment.getTipeEquipment().name() + "^" + equipment.getBonusKekuatan() + "^" + equipment.getBonusDefense() + "^" + equipment.getLevelTempa() + "^" + equipment.getRequiredClassType().name());
                        writer.newLine();
                    } else if (item instanceof ConsumableFood consumableFood) {
                        writer.write("consumableFood=" + consumableFood.getIdItem() + "^" + consumableFood.getNamaItem() + "^" + consumableFood.getDeskripsi() + "^" + consumableFood.getHargaJual() + "^" + consumableFood.getHealHpAmount() + "^" + consumableFood.getHealMpAmount() + "^" + consumableFood.getStrBuff() + "^" + consumableFood.getDefBuff() + "^" + consumableFood.getInfoGiziSDG());
                        writer.newLine();
                    }
                }
            }
            writer.newLine();

            //quest
            writer.write(quest);
            writer.newLine();
            QuestTracker qt = profile.getQuestTracker();
            if (qt != null) {
                ArrayList<MainQuest> mainQuestAktif = qt.getDaftarMainQuestAktif();
                if (mainQuestAktif != null) {
                    for (MainQuest mq : mainQuestAktif) {
                        if (mq == null) {
                            continue;
                        }
                        writer.write("mainQuest=" + mq.getIdQuest() + "^" + mq.getNamaQuest() + "^" + mq.getDeskripsiQuest() + "^" + mq.getObjectiveQuest() + "^" + mq.getObjectiveTarget() + "^" + mq.getObjectiveProgress() + "^" + mq.getHadiahKoin() + "^" + mq.getChapterTerbuka() + "^" + mq.getStatusQuest() + "^" + String.join("~", mq.getRiwayatObjective()) + "^" + (mq.getWilayah() == null ? "" : mq.getWilayah()) + "^" + mq.getNomorQuest() + "^" + (mq.getHadiahUtama() == null ? "" : mq.getHadiahUtama()) + "^" + String.join("~", mq.getLineUpMusuh()));
                        writer.newLine();
                    }
                }

                ArrayList<SubQuest> subQuestAktif = qt.getDaftarSubQuestAktif();
                if (subQuestAktif != null) {
                    for (SubQuest sq : subQuestAktif) {
                        if (sq == null) {
                            continue;
                        }
                        writer.write("subQuest=" + sq.getIdQuest() + "^" + sq.getNamaQuest() + "^" + sq.getDeskripsiQuest() + "^" + sq.getObjectiveQuest() + "^" + sq.getObjectiveTarget() + "^" + sq.getObjectiveProgress() + "^" + sq.getHadiahKoin() + "^" + sq.getStatusQuest() + "^" + String.join("~", sq.getRiwayatObjective()) + "^" + (sq.getWilayah() == null ? "" : sq.getWilayah()));
                        writer.newLine();
                    }
                }

                ArrayList<Quest> riwayatMisiSelesai = qt.getRiwayatMisiSelesai();
                if (riwayatMisiSelesai != null) {
                    for (Quest q : riwayatMisiSelesai) {
                        if (q == null) {
                            continue;
                        }
                        writer.write("questHistory=" + q.getIdQuest() + "^" + q.getNamaQuest() + "^" + q.getDeskripsiQuest() + "^" + q.getObjectiveQuest() + "^" + q.getObjectiveTarget() + "^" + q.getObjectiveProgress() + "^" + q.getHadiahKoin() + "^" + q.getStatusQuest() + "^" + String.join("~", q.getRiwayatObjective()));
                        writer.newLine();
                    }
                }
            } else {
                writer.write("questTracker=null");
                writer.newLine();
            }
            writer.newLine();

            //skill
            writer.write(skillSection);
            writer.newLine();
            ArrayList<String> unlockedSkills = profile.getUnlockedSkillNames();
            if (unlockedSkills != null && !unlockedSkills.isEmpty()) {
                writer.write("unlockedSkills=" + String.join(",", unlockedSkills));
                writer.newLine();
            }
            writer.newLine();

            //location
            writer.write(locationSection);
            writer.newLine();
            ArrayList<String> visited = profile.getVisitedLocationNames();
            if (visited != null && !visited.isEmpty()) {
                writer.write("visitedLocations=" + String.join(",", visited));
                writer.newLine();
            }
            writer.newLine();

            System.out.println("Game Saved! Progres berhasil disimpan ke \"" + fileName + "\".");

        } catch (Exception e) {
            System.err.println("Gagal menyimpan game: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            System.out.println("Gagal menyimpan progres. Silakan coba lagi.");
        }
    }

    public AccountProfile load(String username) {
        String fileName = SAVE_FOLDER + username + extension;
        File saveFile = new File(fileName);

        if (!saveFile.exists()) {
            System.out.println("File save untuk username \"" + username + "\" tidak ditemukan.");
            return null;
        }

        //temp file
        String usernameSave = "";
        int totalGold = 0;
        int maxInventorySlots = -1;
        String areaName = "";

        PlayerCharacter[] partyArray = new PlayerCharacter[4];
        int partyCount = 0;
        LinkedList<Item> inventoryList = new LinkedList<>();
        ArrayList<MainQuest> mainQuestAktif = new ArrayList<>();
        ArrayList<SubQuest> subQuestAktif = new ArrayList<>();
        ArrayList<Quest> riwayatMisiSelesai = new ArrayList<>();
        boolean explicitQuestTrackerNull = false;
        ArrayList<String> unlockedSkills = new ArrayList<>();
        ArrayList<String> visitedLocations = new ArrayList<>();

        String currentSection = "";

        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("[")) {
                    currentSection = line;
                    continue;
                }

                if (currentSection.equals(basicInfo)) {
                    if (line.startsWith("username=")) {
                        usernameSave = line.substring("username=".length());
                    } else if (line.startsWith("totalGold=")) {
                        totalGold = Integer.parseInt(line.substring("totalGold=".length()));
                    } else if (line.startsWith("maxInventorySlots=")) {
                        try {
                            maxInventorySlots = Integer.parseInt(line.substring("maxInventorySlots=".length()));
                        } catch (NumberFormatException ignored) {}
                    } else if (line.startsWith("areaName=")) {
                        areaName = line.substring("areaName=".length());
                    }
                } else if (currentSection.equals(party)) {
                    if (line.startsWith("karakter=")) {
                        String[] data = line.substring("karakter=".length()).split("\\^");
                        if (data.length >= 12 && partyCount < partyArray.length) {
                            PlayerCharacter karakter = new PlayerCharacter(
                                    data[0], //nama
                                    Integer.parseInt(data[3]),  // maxHp
                                    Integer.parseInt(data[4]),  // currentHp
                                    Integer.parseInt(data[5]),  // maxMp
                                    Integer.parseInt(data[6]),  // currentMp
                                    Integer.parseInt(data[7]),  // str
                                    Integer.parseInt(data[8]),  // def
                                    Integer.parseInt(data[2]),  // level
                                    Integer.parseInt(data[9]),  // currentExp
                                    Integer.parseInt(data[10]), // maxExp
                                    data[1]); //class
                            if (data.length >= 13) {
                                trySetEquipmentSlot(karakter, data[12], "WEAPON");
                            }
                            if (data.length >= 14) {
                                trySetEquipmentSlot(karakter, data[13], "ARMOR");
                            }
                            if (data.length >= 15) {
                                trySetEquipmentSlot(karakter, data[14], "ACCESSORY");
                            }
                            partyArray[partyCount] = karakter;
                            partyCount++;
                        }
                    }
                } else if (currentSection.equals(inventory)) {
                    if (line.startsWith("inqredient=")) {
                        String[] data = line.substring("inqredient=".length()).split("\\^");
                        if (data.length >= 4) {
                            int id = Integer.parseInt(data[0]);
                            String nama = data[1];
                            String deskripsi = data[2];
                            int harga = Integer.parseInt(data[3]);
                            inventoryList.add(new Ingredients(id, nama, harga, deskripsi, ItemType.INQREDIENT));
                        }
                    } else if (line.startsWith("equipment=")) {
                        String[] data = line.substring("equipment=".length()).split("\\^");
                        if (data.length >= 8) {
                            int id = Integer.parseInt(data[0]);
                            String nama = data[1];
                            String deskripsi = data[2];
                            int harga = Integer.parseInt(data[3]);
                            int bonusStr = Integer.parseInt(data[5]);
                            int bonusDef = Integer.parseInt(data[6]);
                            int levelTempa = Integer.parseInt(data[7]);
                            EquipmentType slot = parseSlot(data[4], bonusStr, bonusDef);
                            ClassType requiredClassType = data.length >= 9 ? parseClassType(data[8]) : ClassType.CLASSLESS;
                            inventoryList.add(createEquipment(id, nama, harga, deskripsi, slot, bonusStr, bonusDef, levelTempa, requiredClassType));
                        }
                    } else if (line.startsWith("consumableFood=")) {
                        String[] data = line.substring("consumableFood=".length()).split("\\^");
                        if (data.length >= 9) {
                            int id = Integer.parseInt(data[0]);
                            String nama = data[1];
                            String deskripsi = data[2];
                            int harga = Integer.parseInt(data[3]);
                            int healHp = Integer.parseInt(data[4]);
                            int healMp = Integer.parseInt(data[5]);
                            int strBuff = Integer.parseInt(data[6]);
                            int defBuff = Integer.parseInt(data[7]);
                            String info = data[8];
                            inventoryList.add(new ConsumableFood(id, nama, harga, deskripsi, ItemType.CONSUMABLE, healHp, healMp, strBuff, defBuff, info));
                        }
                    }
                } else if (currentSection.equals(quest)) {
                    if (line.startsWith("mainQuest=")) {
                        String[] data = line.substring("mainQuest=".length()).split("\\^");
                        if (data.length >= 10) {
                            int id = Integer.parseInt(data[0]);
                            String nama = data[1];
                            String deskripsi = data[2];
                            String objective = data[3];
                            int target = Integer.parseInt(data[4]);
                            int progress = Integer.parseInt(data[5]);
                            int hadiah = Integer.parseInt(data[6]);
                            int chapter = Integer.parseInt(data[7]);
                            enums.StatusQuest status = enums.StatusQuest.valueOf(data[8]);
                            ArrayList<String> riwayat = new ArrayList<>();
                            if (!data[9].isEmpty()) {
                                riwayat.addAll(Arrays.asList(data[9].split("~")));
                            }
                            String wilayah = data.length >= 11 ? data[10] : null;
                            int nomorQuest = data.length >= 12 && !data[11].isEmpty() ? Integer.parseInt(data[11]) : 0;
                            String hadiahUtama = data.length >= 13 ? data[12] : null;
                            ArrayList<String> lineUpMusuh = new ArrayList<>();
                            if (data.length >= 14 && !data[13].isEmpty()) {
                                lineUpMusuh.addAll(Arrays.asList(data[13].split("~")));
                            }
                            MainQuest mq = new MainQuest(id, nama, deskripsi, objective, target, hadiah, chapter, wilayah, nomorQuest, hadiahUtama, lineUpMusuh);
                            mq.setObjectiveProgress(progress);
                            mq.setStatusQuest(status);
                            mq.setRiwayatObjective(riwayat);
                            mainQuestAktif.add(mq);
                        }
                    } else if (line.startsWith("subQuest=")) {
                        String[] data = line.substring("subQuest=".length()).split("\\^");
                        if (data.length >= 10) {
                            int id = Integer.parseInt(data[0]);
                            String nama = data[1];
                            String deskripsi = data[2];
                            String objective = data[3];
                            int target = Integer.parseInt(data[4]);
                            int progress = Integer.parseInt(data[5]);
                            int hadiah = Integer.parseInt(data[6]);
                            ArrayList<String> riwayat = new ArrayList<>();
                            enums.StatusQuest status = enums.StatusQuest.valueOf(data[7]);
                            if (!data[8].isEmpty()) {
                                riwayat.addAll(Arrays.asList(data[8].split("~")));
                            }
                            String wilayah = data[9];
                            SubQuest sq = new SubQuest(id, nama, deskripsi, objective, target, hadiah, wilayah.isEmpty() ? null : wilayah);
                            sq.setObjectiveProgress(progress);
                            sq.setStatusQuest(status);
                            sq.setRiwayatObjective(riwayat);
                            subQuestAktif.add(sq);
                        }
                    } else if (line.startsWith("questHistory=")) {
                        String[] data = line.substring("questHistory=".length()).split("\\^");
                        if (data.length >= 9) {
                            int id = Integer.parseInt(data[0]);
                            String nama = data[1];
                            String deskripsi = data[2];
                            String objective = data[3];
                            int target = Integer.parseInt(data[4]);
                            int progress = Integer.parseInt(data[5]);
                            int hadiah = Integer.parseInt(data[6]);
                            enums.StatusQuest status = enums.StatusQuest.valueOf(data[7]);
                            ArrayList<String> riwayat = new ArrayList<>();
                            if (!data[8].isEmpty()) {
                                riwayat.addAll(Arrays.asList(data[8].split("~")));
                            }
                            Quest q = new Quest(id, nama, deskripsi, objective, target, hadiah) {};
                            q.setObjectiveProgress(progress);
                            q.setStatusQuest(status);
                            q.setRiwayatObjective(riwayat);
                            riwayatMisiSelesai.add(q);
                        }
                    } else if (line.startsWith("questTracker=null")) {
                        explicitQuestTrackerNull = true;
                        mainQuestAktif.clear();
                        subQuestAktif.clear();
                        riwayatMisiSelesai.clear();
                    }
                } else if (currentSection.equals(skillSection)) {
                    if (line.startsWith("unlockedSkills=")) {
                        String val = line.substring("unlockedSkills=".length());
                        if (!val.isEmpty()) {
                            String[] names = val.split(",");
                            for (String n : names) {
                                if (!n.trim().isEmpty()) unlockedSkills.add(n.trim());
                            }
                        }
                    }
                } else if (currentSection.equals(locationSection)) {
                    if (line.startsWith("visitedLocations=")) {
                        String val = line.substring("visitedLocations=".length());
                        if (!val.isEmpty()) {
                            String[] locs = val.split(",");
                            for (String l : locs) {
                                if (!l.trim().isEmpty()) visitedLocations.add(l.trim().toLowerCase());
                            }
                        }
                    }
                }
            }

            if (partyCount == 0) {
                partyArray = null;
            } else if (partyCount < partyArray.length) {
                partyArray = Arrays.copyOf(partyArray, partyCount);
            }

            QuestTracker questTracker = (!explicitQuestTrackerNull && (!mainQuestAktif.isEmpty() || !subQuestAktif.isEmpty() || !riwayatMisiSelesai.isEmpty()))
                    ? new QuestTracker(mainQuestAktif, subQuestAktif, riwayatMisiSelesai)
                    : null;

            AccountProfile profile = new AccountProfile(usernameSave.isEmpty() ? username : usernameSave, "", totalGold, partyArray, inventoryList, questTracker);
            // apply loaded maxInventorySlots if present (will re-apply trimming inside setMaxInventorySlots)
            if (maxInventorySlots > 0) profile.setMaxInventorySlots(maxInventorySlots);
            profile.setAreaName(areaName);
            profile.setUnlockedSkillNames(unlockedSkills);
            for (String loc : visitedLocations) {
                profile.kunjungiLokasi(loc);
            }
            return profile;
        } catch (Exception e) {
            System.err.println("[SaveLoadSystem.load] Gagal memuat game: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            System.out.println("Gagal memuat progres. File save mungkin rusak.");
            return null;
        }
    }

    private void trySetEquipmentSlot(PlayerCharacter karakter, String rawId, String slot) {
        try {
            int equipmentId = Integer.parseInt(rawId);
            if (equipmentId <= 0) {
                return;
            }

            Item equipmentItem = getEquipmentBySlotAndId(slot, equipmentId);
            if (equipmentItem instanceof Equipment loadedEquipment) {
                karakter.setEquipmentBySlot(slot, loadedEquipment);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private Item getEquipmentBySlotAndId(String slot, int id) {
        if (slot == null) {
            return null;
        }

        switch (slot.trim().toUpperCase()) {
            case "WEAPON":
                return DummyData.weapon.getDummyWeaponsMap().get(id);
            case "ARMOR":
                return DummyData.armor.getDummyArmorsMap().get(id);
            case "ACCESSORY":
                return DummyData.accessory.getDummyAccessoriesMap().get(id);
            default:
                return null;
        }
    }

    private EquipmentType parseSlot(String rawSlot, int bonusStr, int bonusDef) {
        if (rawSlot != null) {
            try {
                return EquipmentType.valueOf(rawSlot.trim().toUpperCase());
            } catch (IllegalArgumentException ignored) {
            }
        }

        if (bonusStr > 0 && bonusDef == 0) {
            return EquipmentType.WEAPON;
        }
        if (bonusDef > 0 && bonusStr == 0) {
            return EquipmentType.ARMOR;
        }
        return EquipmentType.ACCESSORY;
    }

    private ClassType parseClassType(String rawClassType) {
        if (rawClassType == null || rawClassType.isBlank()) {
            return ClassType.CLASSLESS;
        }

        try {
            return ClassType.valueOf(rawClassType.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ClassType.CLASSLESS;
        }
    }

    private Equipment createEquipment(int id, String nama, int harga, String deskripsi, EquipmentType slot, int bonusStr, int bonusDef, int levelTempa, ClassType requiredClassType) {
        switch (slot) {
            case ARMOR:
                return new Armor(id, nama, harga, deskripsi, bonusDef, levelTempa, requiredClassType);
            case ACCESSORY:
                return new Accessory(id, nama, harga, deskripsi, bonusStr, bonusDef, levelTempa);
            case WEAPON:
            default:
                return new Weapon(id, nama, harga, deskripsi, bonusStr, levelTempa, requiredClassType);
        }
    }
}
