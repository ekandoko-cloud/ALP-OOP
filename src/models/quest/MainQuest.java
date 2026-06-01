package models.quest;

import models.account.AccountProfile;
import models.character.Monster;
import models.item.Item;
import enums.StatusQuest;
import systems.quest.QuestTracker;

import java.util.*;

public class MainQuest extends Quest {
    private int chapterTerbuka;
    private String wilayah;
    private int nomorQuest;
    private String hadiahUtama;
    private ArrayList<String> lineUpMusuh;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_MAGENTA = "\u001B[35m";

    public MainQuest(int idQuest, String namaQuest, String deskripsiQuest, String objectiveMainQuest, int objectiveTarget, int hadiahKoin, int chapterTerbuka) {
        this(idQuest, namaQuest, deskripsiQuest, objectiveMainQuest, objectiveTarget, hadiahKoin, chapterTerbuka, null, 0, null, null);
    }

    public MainQuest(int idQuest, String namaQuest, String deskripsiQuest, String objectiveMainQuest, int objectiveTarget, int hadiahKoin, int chapterTerbuka, String wilayah, int nomorQuest, String hadiahUtama, List<String> lineUpMusuh) {
        super(idQuest, namaQuest, deskripsiQuest, objectiveMainQuest, objectiveTarget, hadiahKoin);
        this.chapterTerbuka = chapterTerbuka;
        this.wilayah = wilayah;
        this.nomorQuest = nomorQuest;
        this.hadiahUtama = hadiahUtama;
        this.lineUpMusuh = new ArrayList<>();
        if (lineUpMusuh != null) {
            this.lineUpMusuh.addAll(lineUpMusuh);
        }
    }

    public int getChapterTerbuka() {
        return chapterTerbuka;
    }

    public void setChapterTerbuka(int chapterTerbuka) {
        this.chapterTerbuka = chapterTerbuka;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public int getNomorQuest() {
        return nomorQuest;
    }

    public void setNomorQuest(int nomorQuest) {
        this.nomorQuest = nomorQuest;
    }

    public String getHadiahUtama() {
        return hadiahUtama;
    }

    public void setHadiahUtama(String hadiahUtama) {
        this.hadiahUtama = hadiahUtama;
    }

    public List<String> getLineUpMusuh() {
        return Collections.unmodifiableList(lineUpMusuh);
    }

    public void setLineUpMusuh(List<String> lineUpMusuh) {
        this.lineUpMusuh.clear();
        if (lineUpMusuh != null) {
            this.lineUpMusuh.addAll(lineUpMusuh);
        }
    }

    public String getLineUpMusuhRingkas() {
        return String.join(", ", lineUpMusuh);
    }

    public boolean membutuhkanMusuh(String namaMusuh) {
        if (namaMusuh == null) {
            return false;
        }

        for (String musuh : lineUpMusuh) {
            if (musuh != null && musuh.equalsIgnoreCase(namaMusuh.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean butuhMusuh(String namaMusuh) {
        return membutuhkanMusuh(namaMusuh);
    }

    public boolean bisaDiambilPadaChapter(int chapterAktif) {
        return chapterAktif >= chapterTerbuka;
    }

    public boolean siapDipakai(int chapterAktif) {
        return bisaDiambilPadaChapter(chapterAktif);
    }

    public void catatObjectiveMainQuest(int progressTambahan, String catatan) {
        catatObjective(progressTambahan, "[MainQuest] " + catatan);
    }

    public void tambahProgress(int progressTambahan, String catatan) {
        catatObjectiveMainQuest(progressTambahan, catatan);
    }

    // ================= STATIC METHODS FOR APP =================

    public static void displayQuestTracker(QuestTracker qt) {
        if (qt == null) {
            System.out.println(ANSI_YELLOW + "Belum ada quest tracker." + ANSI_RESET);
            return;
        }

        ArrayList<MainQuest> daftar = qt.getDaftarMainQuestAktif();
        boolean adaOngoing = false;
        if (daftar != null && !daftar.isEmpty()) {
            System.out.println(ANSI_CYAN + ANSI_BOLD + "\n=== MAIN QUEST AKTIF ===" + ANSI_RESET);
            int no = 1;
            for (MainQuest mq : daftar) {
                if (mq == null) continue;
                if (mq.getStatusQuest() != StatusQuest.ONGOING) continue;
                adaOngoing = true;
                System.out.println(ANSI_BOLD + (no++) + ". " + mq.getNamaQuest() + ANSI_RESET + " " + ANSI_GREEN + "[ONGOING]");
                System.out.println("   Wilayah: " + mq.getWilayah() + " | Quest ke-" + mq.getNomorQuest());
                System.out.println("   Objective: " + mq.getObjectiveQuest());
                System.out.println("   Progress: " + mq.getObjectiveProgress() + "/" + mq.getObjectiveTarget());
                System.out.println("   Hadiah: " + mq.getHadiahKoin() + " Gold + " + mq.getHadiahUtama());
                System.out.println(ANSI_CYAN + "   ─────────────────────────────────────────" + ANSI_RESET);
            }
        }
        if (!adaOngoing) {
            System.out.println(ANSI_YELLOW + "Tidak ada quest aktif. Ambil quest dari Quest Board!" + ANSI_RESET);
        }

        ArrayList<Quest> riwayat = qt.getRiwayatMisiSelesai();
        if (riwayat != null && !riwayat.isEmpty()) {
            System.out.println(ANSI_GREEN + ANSI_BOLD + "\n=== QUEST SELESAI ===" + ANSI_RESET);
            for (Quest q : riwayat) {
                if (q instanceof MainQuest) {
                    MainQuest mq = (MainQuest) q;
                    System.out.println("- " + mq.getNamaQuest() + " (" + mq.getWilayah() + ")");
                }
            }
        }
    }

    public static void displayQuestBoard(QuestTracker qt) {
        if (qt == null) {
            System.out.println(ANSI_YELLOW + "Belum ada quest tracker." + ANSI_RESET);
            return;
        }

        ArrayList<MainQuest> daftar = qt.getDaftarMainQuestAktif();
        if (daftar == null || daftar.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Tidak ada quest di papan pengumuman." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n╔═══════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + ANSI_BOLD + "                 Q U E S T   B O A R D                  " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + "╚═══════════════════════════════════════════════════════════════╝" + ANSI_RESET);

            String[] wilayahs = {"Valerion", "Asgard", "Grandis", "Lumina", "Aldoria"};
        for (String wil : wilayahs) {
            boolean hasQuests = false;
            StringBuilder sb = new StringBuilder();
            sb.append(ANSI_MAGENTA + ANSI_BOLD + "\n[" + wil + "]" + ANSI_RESET + "\n");

            for (MainQuest mq : daftar) {
                if (mq == null || !mq.getWilayah().equalsIgnoreCase(wil)) continue;
                hasQuests = true;
                String statusIcon;
                if (mq.getStatusQuest() == StatusQuest.COMPLETED) statusIcon = ANSI_GREEN + "[✓]";
                else if (mq.getStatusQuest() == StatusQuest.ONGOING) statusIcon = ANSI_YELLOW + "[▶]";
                else if (mq.getStatusQuest() == StatusQuest.BELUM_DIAMBIL) statusIcon = ANSI_RED + "[🔒]";
                else statusIcon = ANSI_RED + "[✗]";

                sb.append(String.format("  %s Quest %d: %s%s", statusIcon, mq.getNomorQuest(), mq.getNamaQuest(), ANSI_RESET));
                sb.append(String.format(" (%d/%d)\n", mq.getObjectiveProgress(), mq.getObjectiveTarget()));
            }

            if (hasQuests) {
                System.out.print(sb.toString());
            }
        }

        ArrayList<Quest> riwayat = qt.getRiwayatMisiSelesai();
        if (riwayat != null && !riwayat.isEmpty()) {
            int count = 0;
            for (Quest q : riwayat) {
                if (q instanceof MainQuest) count++;
            }
            System.out.println(ANSI_GREEN + "\nTotal main quest selesai: " + count + "/25" + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + "\nTotal main quest selesai: 0/25" + ANSI_RESET);
        }
    }

    public static void displayQuestBoardForArea(QuestTracker qt, String currentArea, java.util.Scanner scanner) {
        if (qt == null || currentArea == null || currentArea.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Belum ada quest tracker atau area tidak diketahui." + ANSI_RESET);
            return;
        }

        ArrayList<MainQuest> daftar = qt.getDaftarMainQuestAktif();
        if (daftar == null || daftar.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Tidak ada quest di papan pengumuman." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n╔═══════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + ANSI_BOLD + "           Q U E S T   B O A R D  -  " + currentArea.toUpperCase() + "           " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + "╚═══════════════════════════════════════════════════════════════╝" + ANSI_RESET);

        java.util.List<MainQuest> available = new java.util.ArrayList<>();
         for (MainQuest mq : daftar) {
             if (mq == null) continue;
             // Only show quests in current area with BELUM_DIAMBIL status
             if (mq.getWilayah() != null && mq.getWilayah().equalsIgnoreCase(currentArea) && mq.getStatusQuest() == StatusQuest.BELUM_DIAMBIL) {
                 available.add(mq);
             }
         }

        if (available.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Tidak ada quest yang tersedia di " + currentArea + "." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_GREEN + "Quest tersedia di " + currentArea + ":" + ANSI_RESET);
        int idx = 1;
        for (MainQuest mq : available) {
            System.out.println(ANSI_BOLD + (idx) + ". " + mq.getNamaQuest() + ANSI_RESET);
            System.out.println("   Objective: " + mq.getObjectiveQuest());
            System.out.println("   Hadiah: " + mq.getHadiahKoin() + " Gold + " + mq.getHadiahUtama());
            System.out.println(ANSI_CYAN + "   ─────────────────────" + ANSI_RESET);
            idx++;
        }

        System.out.print("Pilih quest yang ingin diambil (0 untuk batal): ");
        try {
            int pilihan = Integer.parseInt(scanner.nextLine());
            if (pilihan < 1 || pilihan > available.size()) {
                System.out.println(ANSI_YELLOW + "Pembatalan." + ANSI_RESET);
                return;
            }
            MainQuest picked = available.get(pilihan - 1);
            picked.setStatusQuest(StatusQuest.ONGOING);
            System.out.println(ANSI_GREEN + "Quest \"" + picked.getNamaQuest() + "\" berhasil diambil!" + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_YELLOW + "Input tidak valid." + ANSI_RESET);
        }
    }

    public static void berikanHadiah(MainQuest quest, AccountProfile akun, Map<Integer, Item> ingredientAlam, Map<Integer, Item> ingredientMonster, Map<Integer, Item> consumablesMap) {
        if (quest == null || akun == null) {
            System.out.println(ANSI_RED + "Error: quest atau akun null." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_GREEN + ANSI_BOLD + "\n╔══════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + ANSI_BOLD + "         QUEST SELESAI! MENDAPATKAN:      " + ANSI_GREEN + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(ANSI_GREEN + ANSI_BOLD + "╚══════════════════════════════════════╝" + ANSI_RESET);

        int gold = quest.getHadiahKoin();
        if (gold > 0) {
            akun.setTotalGold(akun.getTotalGold() + gold);
            System.out.println("  " + ANSI_YELLOW + gold + " Gold" + ANSI_RESET);
        }

        String hadiahStr = quest.getHadiahUtama();
        if (hadiahStr != null && !hadiahStr.isEmpty()) {
            String[] parts = hadiahStr.split(" \\+ ");
            for (String part : parts) {
                part = part.trim();
                if (part.isEmpty()) continue;

                int qty = 1;
                String itemName = part;

                if (part.matches("\\d+x\\s.*")) {
                    int spaceIdx = part.indexOf("x");
                    if (spaceIdx > 0) {
                        try {
                            qty = Integer.parseInt(part.substring(0, spaceIdx).trim());
                        } catch (NumberFormatException e) {
                            qty = 1;
                        }
                        itemName = part.substring(spaceIdx + 1).trim();
                    }
                }

                Item found = cariItem(itemName, ingredientAlam, ingredientMonster, consumablesMap);
                if (found != null) {
                    for (int i = 0; i < qty; i++) {
                        akun.addItemToInventory(found);
                    }
                    System.out.println("  " + ANSI_CYAN + qty + "x " + found.getNamaItem() + ANSI_RESET);
                } else {
                    System.out.println("  " + ANSI_MAGENTA + part + " (hadiah khusus)" + ANSI_RESET);
                }
            }
        }

        QuestTracker qt = akun.getQuestTracker();
        if (qt != null) {
            ArrayList<Quest> riwayat = qt.getRiwayatMisiSelesai();
            if (riwayat == null) {
                riwayat = new ArrayList<>();
                qt.setRiwayatMisiSelesai(riwayat);
            }
            if (!riwayat.contains(quest)) {
                riwayat.add(quest);
            }
        }
    }

    //varargs -- ... maps --> bisa menerima banyak map
    private static Item cariItem(String nama, Map<Integer, Item>... maps) {
        for (Map<Integer, Item> map : maps) {
            if (map == null) continue;
            for (Item item : map.values()) {
                if (item != null && item.getNamaItem().equalsIgnoreCase(nama.trim())) {
                    return item;
                }
            }
        }
        return null;
    }

    public static void tampilkanMusuhWilayah(String wilayah, List<Monster> semuaMonster) {
        String[] monsterNames;
        switch (wilayah.toLowerCase()) {
            case "valerion":
                monsterNames = new String[]{"Infected Rat", "Scavenger Scout", "Corrupted Crawler", "Blight Spore", "Scavenger Hunter", "Blight-Root"};
                break;
                  case "asgard":
                monsterNames = new String[]{"Swamplands Leech", "Miasma Husk", "Sludge Mutant", "Goliath Toad"};
                break;
            case "grandis":
                monsterNames = new String[]{"Security Drone", "Enath Trooper", "Elite Guard", "Heavy Enath Trooper", "Baron Gluttony"};
                break;
            case "lumina":
                monsterNames = new String[]{"Test Subject X", "Alchemist Cultist", "Failed Experiment", "Dr. Mortis"};
                break;
            case "aldoria":
                monsterNames = new String[]{"Ash Beast", "Radiant Sentinel", "Flare Crawler", "Crimson Chimera"};
                break;
            default:
                System.out.println("Wilayah tidak dikenal.");
                return;
        }

        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n=== MONSTER DI " + wilayah.toUpperCase() + " ===" + ANSI_RESET);
        for (String name : monsterNames) {
            boolean found = false;
            for (Monster m : semuaMonster) {
                if (m != null && m.getNama().equalsIgnoreCase(name)) {
                    System.out.println(ANSI_YELLOW + "  " + m.getNama() + ANSI_RESET + " | HP:" + m.getMaxHp() + " STR:" + m.getKekuatan() + " DEF:" + m.getDefense());
                    System.out.println("  " + m.getTriviaPenyakit());
                    System.out.println(ANSI_CYAN + "  ─────────────────────" + ANSI_RESET);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("  " + ANSI_RED + name + " (data belum tersedia)" + ANSI_RESET);
            }
        }
    }

    public static List<String> getNamaMusuhWilayah(String wilayah) {
        switch (wilayah.toLowerCase()) {
            case "valerion":
                return Arrays.asList("Infected Rat", "Scavenger Scout", "Corrupted Crawler", "Blight Spore", "Scavenger Hunter", "Blight-Root");
                  case "asgard":
                return Arrays.asList("Swamplands Leech", "Miasma Husk", "Sludge Mutant", "Goliath Toad");
            case "grandis":
                return Arrays.asList("Security Drone", "Enath Trooper", "Elite Guard", "Heavy Enath Trooper", "Baron Gluttony");
            case "lumina":
                return Arrays.asList("Test Subject X", "Alchemist Cultist", "Failed Experiment", "Dr. Mortis");
            case "aldoria":
                return Arrays.asList("Ash Beast", "Radiant Sentinel", "Flare Crawler", "Crimson Chimera");
            default:
                return new ArrayList<>();
        }
    }

    public static void tampilkanSemuaQuest(List<MainQuest> daftar) {
        System.out.println(ANSI_CYAN + ANSI_BOLD + "\n╔═══════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + ANSI_BOLD + "            D A F T A R   S E M U A   Q U E S T             " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(ANSI_CYAN + ANSI_BOLD + "╚═══════════════════════════════════════════════════════════════╝" + ANSI_RESET);

            String[] wilayahs = {"Valerion", "Asgard", "Grandis", "Lumina", "Aldoria"};
        for (String wil : wilayahs) {
            boolean hasQuests = false;
            System.out.println(ANSI_MAGENTA + ANSI_BOLD + "\n[" + wil + "]" + ANSI_RESET);

            for (MainQuest mq : daftar) {
                if (mq == null || !mq.getWilayah().equalsIgnoreCase(wil)) continue;
                hasQuests = true;
                System.out.println("  Quest " + mq.getNomorQuest() + ": " + mq.getNamaQuest());
                System.out.println("    Objective: " + mq.getObjectiveQuest());
                System.out.println("    Hadiah: " + mq.getHadiahKoin() + " Gold + " + mq.getHadiahUtama());
                System.out.println(ANSI_CYAN + "    ─────────────────────" + ANSI_RESET);
            }

            if (!hasQuests) {
                System.out.println("  (Tidak ada quest)");
            }
        }
    }
}
