package models.quest;

import main.AnsiColors;
import models.account.AccountProfile;
import models.character.Monster;
import models.character.PlayerCharacter;
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

    public String getWilayah() {
        return wilayah;
    }

    public int getNomorQuest() {
        return nomorQuest;
    }

    public String getHadiahUtama() {
        return hadiahUtama;
    }

    public List<String> getLineUpMusuh() {
        return Collections.unmodifiableList(lineUpMusuh);
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

    public void catatObjectiveMainQuest(int progressTambahan, String catatan) {
        catatObjective(progressTambahan, "[MainQuest] " + catatan);
    }

    public void tambahProgress(int progressTambahan, String catatan) {
        catatObjectiveMainQuest(progressTambahan, catatan);
    }

    public static void displayQuestTracker(QuestTracker qt) {
        if (qt == null) {
            System.out.println(AnsiColors.YELLOW + "Belum ada quest tracker." + AnsiColors.RESET);
            return;
        }

        ArrayList<MainQuest> daftar = qt.getDaftarMainQuestAktif();
        boolean adaOngoing = false;
        if (daftar != null && !daftar.isEmpty()) {
            System.out.println();
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║                                                                                    ║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + AnsiColors.BOLD + "    ███╗   ███╗ █████╗ ██╗███╗   ██╗     ██████╗ ██╗   ██╗███████╗███████╗████████╗ " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + AnsiColors.BOLD + "    ████╗ ████║██╔══██╗██║████╗  ██║    ██╔═══██╗██║   ██║██╔════╝██╔════╝╚══██╔══╝ " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + AnsiColors.BOLD + "    ██╔████╔██║███████║██║██╔██╗ ██║    ██║   ██║██║   ██║█████╗  ███████╗   ██║    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.WARM_GOLD  + AnsiColors.BOLD + "    ██║╚██╔╝██║██╔══██║██║██║╚██╗██║    ██║   ██║██║   ██║██╔══╝  ╚════██║   ██║    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.WARM_GOLD  + AnsiColors.BOLD + "    ██║ ╚═╝ ██║██║  ██║██║██║ ╚████║    ╚██████╔╝╚██████╔╝███████╗███████║   ██║    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.WARM_GOLD  + AnsiColors.BOLD + "    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝     ╚═════╝  ╚═════╝ ╚══════╝╚══════╝   ╚═╝    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║                                                                                    ║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + AnsiColors.BOLD + "                        -  M A I N   Q U E S T   A C T I V E  -                     " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
            System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + AnsiColors.RESET);            int no = 1;
            for (MainQuest mq : daftar) {
                if (mq == null) continue;
                if (mq.getStatusQuest() != StatusQuest.ONGOING) continue;
                adaOngoing = true;
                System.out.println(AnsiColors.BOLD + (no++) + ". " + mq.getNamaQuest() + AnsiColors.RESET + " " + AnsiColors.GREEN + "[ONGOING]");
                System.out.println("   Wilayah: " + mq.getWilayah() + " | Quest ke-" + mq.getNomorQuest());
                System.out.println("   Objective: " + mq.getObjectiveQuest());
                System.out.println("   Progress: " + mq.getObjectiveProgress() + "/" + mq.getObjectiveTarget());
                System.out.println("   Hadiah: " + mq.getHadiahKoin() + " Gold + " + mq.getHadiahUtama());
                System.out.println(AnsiColors.CYAN + "   ─────────────────────────────────────────" + AnsiColors.RESET);
            }
        }
        if (!adaOngoing) {
            System.out.println(AnsiColors.YELLOW + "Tidak ada quest aktif. Ambil quest dari Quest Board!" + AnsiColors.RESET);
        }
    }

    public static void displayCompletedQuests(QuestTracker qt) {
        if (qt == null) return;
        ArrayList<Quest> riwayat = qt.getRiwayatMisiSelesai();
        if (riwayat != null && !riwayat.isEmpty()) {
            System.out.println(AnsiColors.GREEN + AnsiColors.BOLD + "\n=== QUEST SELESAI ===" + AnsiColors.RESET);
            for (Quest q : riwayat) {
                if (q instanceof MainQuest) {
                    MainQuest mq = (MainQuest) q;
                    String statusLabel = (mq.getStatusQuest() == StatusQuest.COMPLETED) ? AnsiColors.GREEN + "[Siap Klaim]" : AnsiColors.CYAN + "[Sudah Diklaim]";
                    System.out.println("- " + mq.getNamaQuest() + " (" + mq.getWilayah() + ") " + statusLabel + AnsiColors.RESET);
                }
            }
        }
    }

    public static void displayQuestBoard(QuestTracker qt) {
        if (qt == null) {
            System.out.println(AnsiColors.YELLOW + "Belum ada quest tracker." + AnsiColors.RESET);
            return;
        }

        ArrayList<MainQuest> daftar = qt.getDaftarMainQuestAktif();
        if (daftar == null || daftar.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Tidak ada quest di papan pengumuman." + AnsiColors.RESET);
            return;
        }

        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "\n╔═══════════════════════════════════════════════════════════════╗" + AnsiColors.RESET);
        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.YELLOW + AnsiColors.BOLD + "                 Q U E S T   B O A R D                  " + AnsiColors.CYAN + AnsiColors.BOLD + "║" + AnsiColors.RESET);
        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "╚═══════════════════════════════════════════════════════════════╝" + AnsiColors.RESET);

            String[] wilayahs = {"Valerion", "Asgard", "Grandis", "Lumina", "Aldoria"};
        for (String wil : wilayahs) {
            boolean hasQuests = false;
            StringBuilder sb = new StringBuilder();
            sb.append(AnsiColors.MAGENTA + AnsiColors.BOLD + "\n[" + wil + "]" + AnsiColors.RESET + "\n");

            for (MainQuest mq : daftar) {
                if (mq == null || !mq.getWilayah().equalsIgnoreCase(wil)) continue;
                hasQuests = true;
                String statusIcon;
                if (mq.getStatusQuest() == StatusQuest.REWARDED) statusIcon = AnsiColors.CYAN + "[★]";
                else if (mq.getStatusQuest() == StatusQuest.COMPLETED) statusIcon = AnsiColors.GREEN + "[✓]";
                else if (mq.getStatusQuest() == StatusQuest.ONGOING) statusIcon = AnsiColors.YELLOW + "[▶]";
                else if (mq.getStatusQuest() == StatusQuest.BELUM_DIAMBIL) statusIcon = AnsiColors.RED + "[🔒]";
                else statusIcon = AnsiColors.RED + "[✗]";

                sb.append(String.format("  %s Quest %d: %s%s", statusIcon, mq.getNomorQuest(), mq.getNamaQuest(), AnsiColors.RESET));
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
            System.out.println(AnsiColors.GREEN + "\nTotal main quest selesai: " + count + "/25" + AnsiColors.RESET);
        } else {
            System.out.println(AnsiColors.YELLOW + "\nTotal main quest selesai: 0/25" + AnsiColors.RESET);
        }
    }

    public static void displayQuestBoardForArea(QuestTracker qt, String currentArea, java.util.Scanner scanner) {
        if (qt == null || currentArea == null|| currentArea.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Belum ada quest tracker atau area tidak diketahui." + AnsiColors.RESET);
            return;
        }

        ArrayList<MainQuest> daftar = qt.getDaftarMainQuestAktif();
        if (daftar == null || daftar.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Tidak ada quest di papan pengumuman." + AnsiColors.RESET);
            return;
        }

        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "\n╔═══════════════════════════════════════════════════════════════╗" + AnsiColors.RESET);
        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.YELLOW + AnsiColors.BOLD + "           Q U E S T   B O A R D  -  " + currentArea.toUpperCase() + "           " + AnsiColors.CYAN + AnsiColors.BOLD + "║" + AnsiColors.RESET);
        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "╚═══════════════════════════════════════════════════════════════╝" + AnsiColors.RESET);

        java.util.List<MainQuest> available = new java.util.ArrayList<>();
         for (MainQuest mq : daftar) {
             if (mq == null) continue;
             // Only show quests in current area with BELUM_DIAMBIL status
             if (mq.getWilayah() != null && mq.getWilayah().equalsIgnoreCase(currentArea) && mq.getStatusQuest() == StatusQuest.BELUM_DIAMBIL) {
                 available.add(mq);
             }
         }

        if (available.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + "Tidak ada quest yang tersedia di " + currentArea + "." + AnsiColors.RESET);
            return;
        }

        System.out.println(AnsiColors.GREEN + "Quest tersedia di " + currentArea + ":" + AnsiColors.RESET);
        int idx = 1;
        for (MainQuest mq : available) {
            System.out.println(AnsiColors.BOLD + (idx) + ". " + mq.getNamaQuest() + AnsiColors.RESET);
            System.out.println("   Objective: " + mq.getObjectiveQuest());
            System.out.println("   Hadiah: " + mq.getHadiahKoin() + " Gold + " + mq.getHadiahUtama());
            System.out.println(AnsiColors.CYAN + "   ─────────────────────" + AnsiColors.RESET);
            idx++;
        }

        System.out.print("Pilih quest yang ingin diambil (0 untuk batal): ");
        try {
            int pilihan = Integer.parseInt(scanner.nextLine());
            if (pilihan < 1 || pilihan > available.size()) {
                System.out.println(AnsiColors.YELLOW + "Pembatalan." + AnsiColors.RESET);
                return;
            }
            MainQuest picked = available.get(pilihan - 1);
            picked.setStatusQuest(StatusQuest.ONGOING);
            System.out.println(AnsiColors.GREEN + "Quest \"" + picked.getNamaQuest() + "\" berhasil diambil!" + AnsiColors.RESET);
        } catch (Exception e) {
            System.out.println(AnsiColors.YELLOW + "Input tidak valid." + AnsiColors.RESET);
        }
    }

    public static void berikanHadiah(MainQuest quest, AccountProfile akun, Map<Integer, Item> ingredientAlam, Map<Integer, Item> ingredientMonster, Map<Integer, Item> consumablesMap) {
        if (quest == null || akun == null) {
            System.out.println(AnsiColors.RED + "Error: quest atau akun null." + AnsiColors.RESET);
            return;
        }

        quest.setStatusQuest(StatusQuest.REWARDED);

        System.out.println(AnsiColors.GREEN + AnsiColors.BOLD + "\n╔══════════════════════════════════════╗" + AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.YELLOW + AnsiColors.BOLD + "         QUEST SELESAI! MENDAPATKAN:      " + AnsiColors.GREEN + AnsiColors.BOLD + "║" + AnsiColors.RESET);
        System.out.println(AnsiColors.GREEN + AnsiColors.BOLD + "╚══════════════════════════════════════╝" + AnsiColors.RESET);

        int gold = quest.getHadiahKoin();
        if (gold > 0) {
            akun.setTotalGold(akun.getTotalGold() + gold);
            System.out.println("  " + AnsiColors.YELLOW + gold + " Gold" + AnsiColors.RESET);
        }

        int expReward = 50 + (quest.getChapterTerbuka() * 30);
        PlayerCharacter[] party = akun.getParty();
        if (party != null) {
            for (PlayerCharacter pc : party) {
                if (pc != null) {
                    pc.tambahExp(expReward);
                }
            }
            System.out.println("  " + AnsiColors.MAGENTA + expReward + " EXP (setiap anggota party)" + AnsiColors.RESET);
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
                    System.out.println("  " + AnsiColors.CYAN + qty + "x " + found.getNamaItem() + AnsiColors.RESET);
                } else {
                    System.out.println("  " + AnsiColors.MAGENTA + part + " (hadiah khusus)" + AnsiColors.RESET);
                }
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

        System.out.println(AnsiColors.CYAN + AnsiColors.BOLD + "\n=== MONSTER DI " + wilayah.toUpperCase() + " ===" + AnsiColors.RESET);
        for (String name : monsterNames) {
            boolean found = false;
            for (Monster m : semuaMonster) {
                if (m != null && m.getNama().equalsIgnoreCase(name)) {
                    System.out.println(AnsiColors.YELLOW + "  " + m.getNama() + AnsiColors.RESET + " | HP:" + m.getMaxHp() + " STR:" + m.getKekuatan() + " DEF:" + m.getDefense());
                    System.out.println("  " + m.getTriviaPenyakit());
                    System.out.println(AnsiColors.CYAN + "  ─────────────────────" + AnsiColors.RESET);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("  " + AnsiColors.RED + name + " (data belum tersedia)" + AnsiColors.RESET);
            }
        }
    }

}
