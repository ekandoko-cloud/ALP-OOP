package models.quest;

import enums.StatusQuest;
import systems.quest.QuestTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubQuest extends Quest {
    private final String wilayah;

    public SubQuest(int idQuest, String namaQuest, String deskripsiQuest, String objectiveSubQuest, int objectiveTarget, int hadiahKoin, String wilayah) {
        super(idQuest, namaQuest, deskripsiQuest, objectiveSubQuest, objectiveTarget, hadiahKoin);
        this.wilayah = wilayah;
    }

    public String getWilayah() {
        return wilayah;
    }


    public boolean bisaDiambilPadaArea(String areaSekarang) {
        if (areaSekarang == null || areaSekarang.trim().isEmpty()) {
            return false;
        }
        if (wilayah == null || !wilayah.equalsIgnoreCase(areaSekarang.trim())) {
            return false;
        }
        return getStatusQuest() == StatusQuest.BELUM_DIAMBIL;
    }

    public static void displayQuestTracker(QuestTracker qt) {
        if (qt == null) {
            System.out.println("\u001B[33mBelum ada quest tracker.\u001B[0m");
            return;
        }

        List<SubQuest> daftar = qt.getDaftarSubQuestAktif();
        if (daftar == null || daftar.isEmpty()) {
            System.out.println("\u001B[33mBelum ada subquest tersedia.\u001B[0m");
            return;
        }

        System.out.println("\u001B[36m\u001B[1m\n=== SUB QUEST ===\u001B[0m");
        int no = 1;
        for (SubQuest sq : daftar) {
            if (sq == null) {
                continue;
            }
            String area = sq.getWilayah() == null ? "-" : sq.getWilayah();
            System.out.println(no++ + ". " + sq.getNamaQuest() + " [" + area + "] - " + sq.getStatusQuest());
            System.out.println("   Objective: " + sq.getObjectiveQuest());
            System.out.println("   Progress : " + sq.getObjectiveProgress() + "/" + sq.getObjectiveTarget());
        }
    }

    public static void displayQuestBoardForArea(QuestTracker qt, String currentArea, Scanner scanner) {
        if (qt == null) {
            System.out.println("\u001B[33mBelum ada quest tracker.\u001B[0m");
            return;
        }

        String area = currentArea == null ? "" : currentArea.trim();
        if (area.isEmpty()) {
            System.out.println("\u001B[33mArea saat ini belum diketahui.\u001B[0m");
            return;
        }

        // Build available list from the global DummyData catalog so new accounts can see/accept subquests
        List<SubQuest> available = new ArrayList<>();
        try {
            List<SubQuest> catalog = DummyData.subquest.getDummySubQuestByWilayah(area);
            if (catalog != null) {
                for (SubQuest sq : catalog) {
                    if (sq != null && sq.getStatusQuest() == enums.StatusQuest.BELUM_DIAMBIL) {
                        available.add(sq);
                    }
                }
            }
        } catch (Throwable t) {
            available = getAvailableSubQuests(qt, area);
        }

        if (available == null || available.isEmpty()) {
            System.out.println("\u001B[33mTidak ada subquest yang tersedia di area ini.\u001B[0m");
            return;
        }

        System.out.println("\u001B[36m\u001B[1m\n=== SUB QUEST BOARD - " + area.toUpperCase() + " ===\u001B[0m");
        for (int i = 0; i < available.size(); i++) {
            SubQuest sq = available.get(i);
            System.out.println((i + 1) + ". " + sq.getNamaQuest());
            System.out.println("   Deskripsi : " + sq.getDeskripsiQuest());
            System.out.println("   Objective : " + sq.getObjectiveQuest());
            System.out.println("   Hadiah    : " + sq.getHadiahKoin() + " Gold");
            System.out.println("   ─────────────────────────────────────────");
        }

        if (scanner == null) {
            return;
        }

        System.out.print("Pilih subquest yang ingin diambil (0 untuk batal): ");
        try {
            String input = scanner.nextLine().trim();
            int pilihan = Integer.parseInt(input);
            if (pilihan < 1 || pilihan > available.size()) {
                System.out.println("Pembatalan.");
                return;
            }

            SubQuest picked = available.get(pilihan - 1);
            if (picked.getStatusQuest() != StatusQuest.BELUM_DIAMBIL) {
                System.out.println("Subquest ini sudah diambil atau sudah selesai.");
                return;
            }

            // Do not mutate the global DummyData instance. Create a player-specific copy
            SubQuest playerQuest = new SubQuest(
                    picked.getIdQuest(),
                    picked.getNamaQuest(),
                    picked.getDeskripsiQuest(),
                    picked.getObjectiveQuest(),
                    picked.getObjectiveTarget(),
                    picked.getHadiahKoin(),
                    picked.getWilayah()
            );
            playerQuest.setStatusQuest(StatusQuest.ONGOING);
            if (qt.getDaftarSubQuestAktif() == null) {
                qt.setDaftarSubQuestAktif(new ArrayList<>());
            }
            if (!containsQuestId(qt.getDaftarSubQuestAktif(), playerQuest.getIdQuest())) {
                qt.getDaftarSubQuestAktif().add(playerQuest);
            }

            System.out.println("Subquest \"" + picked.getNamaQuest() + "\" berhasil diambil!");
        } catch (Exception e) {
            System.out.println("Input tidak valid.");
        }
    }

    public static List<SubQuest> getAvailableSubQuests(QuestTracker qt, String currentArea) {
        ArrayList<SubQuest> hasil = new ArrayList<>();
        if (qt == null || qt.getDaftarSubQuestAktif() == null) {
            return hasil;
        }

        for (SubQuest sq : qt.getDaftarSubQuestAktif()) {
            if (sq != null && sq.bisaDiambilPadaArea(currentArea)) {
                hasil.add(sq);
            }
        }
        return hasil;
    }


    private static boolean containsQuestId(List<SubQuest> daftar, int idQuest) {
        if (daftar == null) {
            return false;
        }
        for (SubQuest sq : daftar) {
            if (sq != null && sq.getIdQuest() == idQuest) {
                return true;
            }
        }
        return false;
    }
}
