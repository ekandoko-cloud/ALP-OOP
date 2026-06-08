package minigames;

import main.AnsiColors;
import models.account.AccountProfile;
import java.util.*;

public class GiziGame extends MiniGame {

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
        super("Investasi Gizi Desa (SDG 2)", 500);
    }

    @Override
    public void startGame(AccountProfile currentProfile) {
        Scanner scanner = new Scanner(System.in);
        boolean isSelesai = false;

        System.out.println();
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╔═════════════════════════════════════════════════════════════════════════════╗" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + AnsiColors.BOLD + "   ███╗   ███╗██╗███╗   ██╗██╗      ██████╗  █████╗ ███╗   ███╗███████╗    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "  ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + AnsiColors.BOLD + "   ████╗ ████║██║████╗  ██║██║     ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "  ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.WARM_GOLD  + AnsiColors.BOLD + "   ██╔████╔██║██║██╔██╗ ██║██║     ██║  ███╗███████║██╔████╔██║█████╗      " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "  ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.WARM_GOLD  + AnsiColors.BOLD + "   ██║╚██╔╝██║██║██║╚██╗██║██║     ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "  ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + AnsiColors.BOLD + "   ██║ ╚═╝ ██║██║██║ ╚████║██║     ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "  ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + AnsiColors.BOLD + "   ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝    " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "  ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.WARM_GOLD + AnsiColors.BOLD + "                    " + this.getNamaGame().toUpperCase() + "                                               ".substring(this.getNamaGame().length()) + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "          ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╠═════════════════════════════════════════════════════════════════════════════╣" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + "   Desa di Distrik 7 sedang mengalami krisis pangan!                        " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + String.format("   Dana Hibah    : " + AnsiColors.WARM_GOLD + "%-57s", this.budgetBantuan + " Koin") + AnsiColors.RESET + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + String.format("   Target Kenyang: " + AnsiColors.WARM_GOLD + "%-57s", ">= " + this.targetKenyang + " Poin") + AnsiColors.RESET + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + String.format("   Target Gizi   : " + AnsiColors.WARM_GOLD + "%-57s", ">= " + this.targetGizi + " Poin") + AnsiColors.RESET + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╠═════════════════════════════════════════════════════════════════════════════╣" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_WHITE + AnsiColors.BOLD + "   DAFTAR HARGA PASAR                                                       " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╠═════════════════════════════════════════════════════════════════════════════╣" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + String.format("   >  [1] Gandum (Karbo)  : " + AnsiColors.WARM_GOLD + "%-49s", hargaGandum + " Koin | +" + poinKenyangGandum + " Kenyang,+" + poinGiziGandum + " Gizi") + AnsiColors.RESET + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + String.format("   >  [2] Daging (Protein): " + AnsiColors.WARM_GOLD + "%-49s", hargaDaging + " Koin | +" + poinKenyangDaging + " Kenyang,+" + poinGiziDaging + " Gizi") + AnsiColors.RESET + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + AnsiColors.SOFT_GREEN + String.format("   >  [3] Sayur  (Vitamin): " + AnsiColors.WARM_GOLD + "%-49s", hargaSayur + " Koin | +" + poinKenyangSayur + " Kenyang,+" + poinGiziSayur + " Gizi") + AnsiColors.RESET + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "║" + AnsiColors.RESET + "                                                                            " + AnsiColors.SOFT_TEAL + AnsiColors.BOLD + " ║" + AnsiColors.RESET);
        System.out.println(AnsiColors.SOFT_TEAL + AnsiColors.BOLD + "╚═════════════════════════════════════════════════════════════════════════════╝" + AnsiColors.RESET);
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