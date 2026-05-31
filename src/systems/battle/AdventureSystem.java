package systems.battle;

import DummyData.mainquest;
import models.account.AccountProfile;
import models.character.GameCharacter;
import models.location.Location;
import models.quest.MainQuest;
import systems.map.MapTraversal;
import systems.quest.QuestTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AdventureSystem {
    private static final int CHANCE_BATTLE = 55;
    private static final int CHANCE_TREASURE = 80;

    private final Random random = new Random();

    public boolean jalankanEksplorasi(AccountProfile account, MapTraversal mapTraversal, Scanner input) {
        if (account == null || mapTraversal == null) {
            return false;
        }

        Scanner scanner = input == null ? new Scanner(System.in) : input;
        Location currentLocation = mapTraversal.areaSaatIni();
        int chapter = chapterDariArea(currentLocation);

        QuestTracker questTracker = account.getQuestTracker();
        if (questTracker != null) {
            questTracker.sinkronisasiChapterTerbuka(chapter);
        }

        int roll = random.nextInt(100);
        if (roll < CHANCE_BATTLE) {
            jalankanBattleRandom(account, chapter, scanner);
        } else if (roll < CHANCE_TREASURE) {
            jalankanTreasure(account, chapter);
        } else {
            jalankanPuzzle(account, chapter, scanner);
        }

        System.out.println();
        System.out.println("1. Lanjut explore");
        System.out.println("2. Balik ke kota");
        System.out.print("Pilihan: ");
        int pilihan = bacaPilihan(scanner, 2);
        return pilihan == 1;
    }

    private void jalankanBattleRandom(AccountProfile account, int chapter, Scanner scanner) {
        List<MainQuest> kandidatQuest = mainquest.getDummyMainQuestByChapter(chapter);
        if (kandidatQuest.isEmpty()) {
            System.out.println("Tidak ada musuh di area ini.");
            return;
        }

        MainQuest seedQuest = kandidatQuest.get(random.nextInt(kandidatQuest.size()));
        GameCharacter[] partyEnemy = BattleEnemyFactory.createPartyFromQuest(seedQuest, chapter);
        BattleSystem battleSystem = new BattleSystem(account.getParty(), partyEnemy, new BattleLog(new ArrayList<>()));
        BattleResult result = battleSystem.mulaiPertarungan(scanner, account.getInventory(), account.getQuestTracker());

        if (result == BattleResult.VICTORY) {
            int bonusGold = 40 + (chapter * 30) + random.nextInt(40);
            account.setTotalGold(account.getTotalGold() + bonusGold);
            System.out.println("Kamu menang dan mendapatkan " + bonusGold + " Gold.");
        } else if (result == BattleResult.DEFEAT) {
            System.out.println("Party kamu kalah. Cobalah lagi setelah memperkuat karakter.");
        } else {
            System.out.println("Kamu kabur dari battle.");
        }
    }

    private void jalankanTreasure(AccountProfile account, int chapter) {
        int gold = hadiahGold(60, 35, 50, chapter);
        account.setTotalGold(account.getTotalGold() + gold);
        System.out.println("\nKamu menemukan harta karun!");
        System.out.println("Isi peti: " + gold + " Gold.");
    }

    private void jalankanPuzzle(AccountProfile account, int chapter, Scanner scanner) {
        PuzzleData puzzle = puzzleForChapter(chapter);
        System.out.println("\nKamu menemukan puzzle kuno!");
        System.out.println(puzzle.question);
        for (int i = 0; i < puzzle.options.length; i++) {
            System.out.println((i + 1) + ". " + puzzle.options[i]);
        }
        System.out.print("Jawaban: ");
        int answer = bacaPilihan(scanner, puzzle.options.length);

        if (answer == puzzle.correctAnswer) {
            int reward = hadiahGold(70, 25, 0, chapter);
            account.setTotalGold(account.getTotalGold() + reward);
            System.out.println("Benar! Kamu mendapatkan " + reward + " Gold.");
        } else {
            System.out.println("Salah. Puzzle tertutup kembali tanpa reward.");
        }
    }

    private PuzzleData puzzleForChapter(int chapter) {
        int chapterTerbuka = Math.max(1, Math.min(5, chapter));
        if (chapterTerbuka == 1) {
            return new PuzzleData(
                    "Puzzle: Mana langkah paling aman saat wilayah mulai terkontaminasi?",
                    new String[]{"Langsung menyerang semua musuh", "Mencari sumber kontaminasi", "Menambah beban inventory", "Meninggalkan party"},
                    2
            );
        }
        if (chapterTerbuka == 2) {
            return new PuzzleData(
                    "Puzzle: Apa fokus utama saat mengolah sumber daya rawa?",
                    new String[]{"Mempercepat gerak tanpa tujuan", "Memisahkan racun dari bahan berguna", "Mengabaikan peta", "Menyerah"},
                    2
            );
        }
        if (chapterTerbuka == 3) {
            return new PuzzleData(
                    "Puzzle: Apa kunci untuk menembus kota kubah?",
                    new String[]{"Mencuri tanpa rencana", "Menyusup dan mengamati patroli", "Berteriak di gerbang", "Membakar gudang"},
                    2
            );
        }
        if (chapterTerbuka == 4) {
            return new PuzzleData(
                    "Puzzle: Bagaimana cara menghadapi eksperimen berbahaya?",
                    new String[]{"Langsung mendekat tanpa persiapan", "Menganalisis pola dan kelemahannya", "Menutup mata", "Membuang semua item"},
                    2
            );
        }
        return new PuzzleData(
                "Puzzle: Apa prioritas di wilayah episentrum?",
                new String[]{"Mengabaikan radiasi", "Menstabilkan area lalu menyerang hati-hati", "Menyerang acak", "Membawa party ke bahaya"},
                2
        );
    }

    private int chapterDariArea(Location location) {
        if (location == null || location.getNamaLokasi() == null) {
            return 1;
        }

        List<Location> daftarLokasi = DummyData.kota.getDummyKota();
        for (int i = 0; i < daftarLokasi.size(); i++) {
            Location kota = daftarLokasi.get(i);
            if (kota != null && kota.getNamaLokasi() != null && kota.getNamaLokasi().equalsIgnoreCase(location.getNamaLokasi())) {
                return i + 1;
            }
        }
        return 1;
    }

    private int hadiahGold(int goldDasar, int perChapter, int bonusAcak, int chapter) {
        return goldDasar + (chapter * perChapter) + (bonusAcak > 0 ? random.nextInt(bonusAcak) : 0);
    }

    private int bacaPilihan(Scanner scanner, int max) {
        while (true) {
            try {
                String line = scanner.nextLine();
                int pilihan = Integer.parseInt(line.trim());
                if (pilihan >= 1 && pilihan <= max) {
                    return pilihan;
                }
            } catch (Exception ignored) {
            }
            System.out.print("Input tidak valid. Masukkan angka 1 - " + max + ": ");
        }
    }

    private static class PuzzleData {
        final String question;
        final String[] options;
        final int correctAnswer;

        PuzzleData(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
}



