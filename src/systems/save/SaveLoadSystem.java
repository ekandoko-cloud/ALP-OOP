package systems.save;

import java.awt.*;
import java.io.*;
import java.util.*;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import models.item.ConsumableFood;
import models.item.Equipment;
import models.item.Inqredients;
import models.item.Item;
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
                    writer.write("karakter=" + karakter.getNama() + "^" + karakter.getNamaClass() + "^" + karakter.getLevel() + "^" + karakter.getMaxHp() + "^" + karakter.getCurrentHp() + "^" + karakter.getMaxMp() + "^" + karakter.getCurrentMp() + "^" + karakter.getKekuatan() + "^" + karakter.getDefense() + "^" + karakter.getCurrentExp() + "^" + karakter.getMaxExp() + "^" + karakter.isStatusTubuhNirlelah());
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
                    if (item instanceof Inqredients) {
                        writer.write("inqredient=" + item.getIdItem() + "^" + item.getNamaItem() + "^" + item.getDeskripsi() + "^" + item.getHargaJual());
                        writer.newLine();
                    } else if (item instanceof Equipment equipment) {
                        writer.write("equipment=" + equipment.getIdItem() + "^" + equipment.getNamaItem() + "^" + equipment.getDeskripsi() + "^" + equipment.getHargaJual() + "^" + equipment.getTipeEquipment() + "^" + equipment.getBonusKekuatan() + "^" + equipment.getBonusDefense() + "^" + equipment.getLevelTempa());
                        writer.newLine();
                    } else if (item instanceof ConsumableFood consumableFood) {
                        writer.write("consumableFood=" + consumableFood.getIdItem() + "^" + consumableFood.getNamaItem() + "^" + consumableFood.getDeskripsi() + "^" + consumableFood.getHargaJual() + "^" + consumableFood.getHealHpAmount() + "^" + consumableFood.getHealMpAmount() + "^" + consumableFood.getTempStrBuff() + "^" + consumableFood.getTempDefBuff() + "^" + consumableFood.getInfoGiziSDG());
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
                        writer.write("mainQuest=" + mq.getIdQuest() + "^" + mq.getNamaQuest() + "^" + mq.getDeskripsiQuest() + "^" + mq.getObjectiveQuest() + "^" + mq.getObjectiveTarget() + "^" + mq.getObjectiveProgress() + "^" + mq.getHadiahKoin() + "^" + mq.getChapterTerbuka() + "^" + mq.getStatusQuest() + "^" + String.join("~", mq.getRiwayatObjective()));
                        writer.newLine();
                    }
                }

                ArrayList<SubQuest> subQuestAktif = qt.getDaftarSubQuestAktif();
                if (subQuestAktif != null) {
                    for (SubQuest sq : subQuestAktif) {
                        if (sq == null) {
                            continue;
                        }
                        writer.write("subQuest=" + sq.getIdQuest() + "^" + sq.getNamaQuest() + "^" + sq.getDeskripsiQuest() + "^" + sq.getObjectiveQuest() + "^" + sq.getObjectiveTarget() + "^" + sq.getObjectiveProgress() + "^" + sq.getHadiahKoin() + "^" + sq.getSyaratLevel() + "^" + sq.getStatusQuest() + "^" + String.join("~", sq.getRiwayatObjective()));
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

            System.out.println("Game Saved! Progres berhasil disimpan ke \"" + fileName + "\".");

        } catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    /*
    private String safe(String value) {
        return value == null ? "" : value.replace("^", " ").replace(",", " ").replace("\n", " ").replace("\r", " ");
    }
    */

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
        //int totalPlaytime = 0;
        //String areaName = "";

        PlayerCharacter[] partyArray = new PlayerCharacter[4];
        int partyCount = 0;
        LinkedList<Item> inventoryList = new LinkedList<>();
        QuestTracker questTracker = null;

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
                                    data[1], //class
                                    Boolean.parseBoolean(data[11])); //fatigue
                            partyArray[partyCount] = karakter;
                            partyCount++;
                        }
                    }
                } else if (currentSection.equals(inventory)) {
                    if (line.startsWith("inqredient=")) {
                        String[] parts = line.substring("inqredient=".length()).split("\\^");
                        if (parts.length >= 4) {
                            int id = Integer.parseInt(parts[0]);
                            String nama = parts[1];
                            String deskripsi = parts[2];
                            int harga = Integer.parseInt(parts[3]);
                            inventoryList.add(new Inqredients(id, nama, harga, deskripsi));
                        }
                    } else if (line.startsWith("equipment=")) {
                        String[] parts = line.substring("equipment=".length()).split("\\^");
                        if (parts.length >= 8) {
                            int id = Integer.parseInt(parts[0]);
                            String nama = parts[1];
                            String deskripsi = parts[2];
                            int harga = Integer.parseInt(parts[3]);
                            String tipe = parts[4];
                            int bonusStr = Integer.parseInt(parts[5]);
                            int bonusDef = Integer.parseInt(parts[6]);
                            int levelTempa = Integer.parseInt(parts[7]);
                            inventoryList.add(new Equipment(id, nama, harga, deskripsi, tipe, bonusStr, bonusDef, levelTempa));
                        }
                    } else if (line.startsWith("consumableFood=")) {
                        String[] parts = line.substring("consumableFood=".length()).split("\\^");
                        if (parts.length >= 9) {
                            int id = Integer.parseInt(parts[0]);
                            String nama = parts[1];
                            String deskripsi = parts[2];
                            int harga = Integer.parseInt(parts[3]);
                            int healHp = Integer.parseInt(parts[4]);
                            int healMp = Integer.parseInt(parts[5]);
                            int tempStr = Integer.parseInt(parts[6]);
                            int tempDef = Integer.parseInt(parts[7]);
                            String info = parts[8];
                            inventoryList.add(new ConsumableFood(id, nama, harga, deskripsi, healHp, healMp, tempStr, tempDef, info));
                        }
                    }
                }
            }

            if (partyCount == 0) {
                partyArray = null;
            } else if (partyCount < partyArray.length) {
                partyArray = Arrays.copyOf(partyArray, partyCount);
            }

            return new AccountProfile(usernameSave.isEmpty() ? username : usernameSave, "", totalGold, partyArray, inventoryList, questTracker);
        } catch (Exception e) {
            System.out.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}
