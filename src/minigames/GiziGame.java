package minigames;

import models.account.AccountProfile;
import java.util.*;

public class GiziGame extends MiniGame {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RED_BRIGHT = "\u001B[91m";
    private static final String SOFT_TEAL  = "\u001B[38;2;64;200;180m";
    private static final String WARM_GOLD  = "\u001B[38;2;220;180;80m";
    private static final String SOFT_WHITE = "\u001B[38;2;220;230;240m";
    private static final String SOFT_GREEN = "\u001B[38;2;100;200;140m";
    private static final String DIM_GRAY   = "\u001B[38;2;130;145;160m";

    private final int budgetBantuan = 1000;
    private final int targetKenyang = 100;
    private final int targetGizi = 100;

    private final int hargaGandum = 100;
    private final int poinKenyangGandum = 30;
    private final int poinGiziGandum = 0;

    private final int hargaDaging = 300;
    private final int poinKenyangDaging = 10;
    private final int poinGiziDaging = 40;

    private final int hargaSayur = 150;
    private final int poinKenyangSayur = 5;
    private final int poinGiziSayur = 25;

    public GiziGame() {
        super("Investasi Gizi Desa (SDG 2)", 500); // 500 adalah rewardKoin
    }

    @Override
    public void startGame(AccountProfile currentProfile) {
        Scanner scanner = new Scanner(System.in);
        boolean isSelesai = false;

        System.out.println();
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╔═════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "   ███╗   ███╗██╗███╗   ██╗██╗      ██████╗  █████╗ ███╗   ███╗███████╗    " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "   ████╗ ████║██║████╗  ██║██║     ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "   ██╔████╔██║██║██╔██╗ ██║██║     ██║  ███╗███████║██╔████╔██║█████╗      " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "   ██║╚██╔╝██║██║██║╚██╗██║██║     ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "   ██║ ╚═╝ ██║██║██║ ╚████║██║     ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "   ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝    " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD + ANSI_BOLD + "                    " + this.getNamaGame().toUpperCase() + "                                               ".substring(this.getNamaGame().length()) + SOFT_TEAL + ANSI_BOLD + "          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + "   Desa di Distrik 7 sedang mengalami krisis pangan!                        " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + String.format("   Dana Hibah    : " + WARM_GOLD + "%-57s", this.budgetBantuan + " Koin") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + String.format("   Target Kenyang: " + WARM_GOLD + "%-57s", ">= " + this.targetKenyang + " Poin") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + String.format("   Target Gizi   : " + WARM_GOLD + "%-57s", ">= " + this.targetGizi + " Poin") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "   DAFTAR HARGA PASAR                                                       " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + String.format("   >  [1] Gandum (Karbo)  : " + WARM_GOLD + "%-49s", hargaGandum + " Koin | +" + poinKenyangGandum + " Kenyang,+" + poinGiziGandum + " Gizi") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + String.format("   >  [2] Daging (Protein): " + WARM_GOLD + "%-49s", hargaDaging + " Koin | +" + poinKenyangDaging + " Kenyang,+" + poinGiziDaging + " Gizi") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + String.format("   >  [3] Sayur  (Vitamin): " + WARM_GOLD + "%-49s", hargaSayur + " Koin | +" + poinKenyangSayur + " Kenyang,+" + poinGiziSayur + " Gizi") + ANSI_RESET + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + "                                                                            " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╚═════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();

        while (!isSelesai) {
            System.out.print("\nBerapa karung Gandum yang dibeli? : ");
            int qtyGandum = scanner.nextInt();

            System.out.print("Berapa paket Daging yang dibeli?  : ");
            int qtyDaging = scanner.nextInt();

            System.out.print("Berapa paket Sayur yang dibeli?   : ");
            int qtySayur = scanner.nextInt();

            int totalBiaya = (qtyGandum * hargaGandum) + (qtyDaging * hargaDaging) + (qtySayur * hargaSayur);

            if (totalBiaya > budgetBantuan) {
                System.out.println("\n[GAGAL] Total biaya (" + totalBiaya + " Koin) melebihi batas budget " + budgetBantuan + " Koin!");
                System.out.println("Silakan atur ulang strategi belanja Anda.");
            } else {
                System.out.println("\n[TRANSAKSI BERHASIL]");
                System.out.println("Total Biaya: " + totalBiaya + " Koin. Sisa Hibah: " + (budgetBantuan - totalBiaya) + " Koin.");

                int totalKenyang = (qtyGandum * poinKenyangGandum) + (qtyDaging * poinKenyangDaging) + (qtySayur * poinKenyangSayur);
                int totalGizi = (qtyGandum * poinGiziGandum) + (qtyDaging * poinGiziDaging) + (qtySayur * poinGiziSayur);

                System.out.println("-------------------------------------------------------------");
                System.out.println("HASIL EVALUASI GIZI DESA:");
                System.out.println("Poin Kenyang : " + totalKenyang + " / " + targetKenyang);
                System.out.println("Poin Gizi    : " + totalGizi + " / " + targetGizi);
                System.out.println("-------------------------------------------------------------");

                if (totalKenyang >= targetKenyang && totalGizi >= targetGizi) {
                    System.out.println("[MISI BERHASIL - ZERO HUNGER TERCAPAI!]");
                    System.out.println("Gizi seimbang tercapai. Anda mendapatkan reward: " + this.getRewardKoin() + " Koin.");

                    int koinSekarang = currentProfile.getTotalGold();
                    currentProfile.setTotalGold(koinSekarang + this.getRewardKoin());

                } else if (totalKenyang >= targetKenyang && totalGizi < targetGizi) {
                    System.out.println("[MISI GAGAL - KRISIS MALNUTRISI]");
                    System.out.println("Penduduk kenyang namun kekurangan gizi mikro (Stunting/Anemia).");
                } else if (totalKenyang < targetKenyang && totalGizi >= targetGizi) {
                    System.out.println("[MISI GAGAL - KELAPARAN]");
                    System.out.println("Makanan bergizi, namun porsi terlalu sedikit. Desa masih kelaparan!");
                } else {
                    System.out.println("[MISI GAGAL - KRISIS TOTAL]");
                    System.out.println("Desa kekurangan porsi makanan dan gizi.");
                }

                isSelesai = true;
            }
        }
    }
}