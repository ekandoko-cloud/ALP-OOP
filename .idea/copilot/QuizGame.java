import java.util.Collections;
import java.util.Scanner;

/**
 * ============================================================
 *  FITUR 3.2.10 — Mini Game: QUIZ GAME
 *  NutriTale — Kelompok 6
 * ============================================================
 *
 *  Sistem mini game kuis nutrisi dan gizi. Pemain menjawab
 *  5 soal acak dari total 20 soal yang tersedia. Reward
 *  gold diberikan sesuai jumlah jawaban benar.
 *
 *  Inheritance sesuai class diagram:
 *    QuizGame extends MiniGame (abstract)
 *
 *  Atribut class diagram yang digunakan:
 *    (MiniGame) #namaGame, #rewardKoin, +startGame()
 *    (QuizGame) -arraySoalGizi, -arrayJawaban
 * ============================================================
 */


/**
 * MiniGame — abstract base class sesuai class diagram
 * (Hapus dan ganti referensi ke class asli jika sudah ada)
 */
abstract class MiniGame {
    protected String namaGame;
    protected int    rewardKoin;

    public MiniGame(String namaGame, int rewardKoin) {
        this.namaGame   = namaGame;
        this.rewardKoin = rewardKoin;
    }

    public abstract void startGame(PlayerCharacter pemain);

    public String getNamaGame()  { return namaGame; }
    public int    getRewardKoin(){ return rewardKoin; }
}


/**
 * SoalKuis — data satu butir soal kuis
 * (kelas pembantu, bukan di class diagram tapi dibutuhkan)
 */
class SoalKuis {
    private String pertanyaan;
    private String[] pilihanJawaban;   // 4 pilihan: A, B, C, D
    private int indeksBenar;           // 0=A, 1=B, 2=C, 3=D
    private String penjelasan;         // tampil di rangkuman akhir

    public SoalKuis(String pertanyaan, String[] pilihanJawaban,
                    int indeksBenar, String penjelasan) {
        this.pertanyaan    = pertanyaan;
        this.pilihanJawaban = pilihanJawaban;
        this.indeksBenar   = indeksBenar;
        this.penjelasan    = penjelasan;
    }

    public String   getPertanyaan()                { return pertanyaan; }
    public String[] getPilihanJawaban()            { return pilihanJawaban; }
    public int      getIndeksBenar()               { return indeksBenar; }
    public String   getJawabanBenar()              { return pilihanJawaban[indeksBenar]; }
    public String   getPenjelasan()                { return penjelasan; }
    public boolean  cekJawaban(int pilihan)        { return pilihan == indeksBenar; }
}


/**
 * QuizGame — Mini game kuis nutrisi
 * extends MiniGame sesuai class diagram
 */
public class QuizGame extends MiniGame {

    private static final int JUMLAH_SOAL_PER_SESI = 5;
    private static final int JUMLAH_TOTAL_SOAL = 20;

    // Sesuai class diagram
    private String[] arraySoalGizi;    // teks pertanyaan
    private String[] arrayJawaban;     // jawaban benar (untuk referensi)

    // Pool soal lengkap (berisi objek SoalKuis)
    private SoalKuis[] poolSoal;

    // ─────────────────────────────────────────────────────────
    public QuizGame() {
        super("Quiz Nutrisi", 0);   // rewardKoin ditentukan dinamis saat selesai
        this.poolSoal           = new SoalKuis[JUMLAH_TOTAL_SOAL];
        initSoal();
        syncArray();
    }

    // ══════════════════════════════════════════════════════════
    //  INISIALISASI SOAL
    // ══════════════════════════════════════════════════════════

    /**
     * Mengisi pool soal dengan pertanyaan nutrisi dan gizi.
     * Tambahkan lebih banyak soal di sini sesuai kebutuhan game.
     */
    private void initSoal() {
        poolSoal[0] = new SoalKuis(
                "Vitamin apa yang banyak terdapat pada wortel?",
                new String[]{"Vitamin C", "Vitamin A", "Vitamin D", "Vitamin B12"},
                1,
                "Wortel kaya akan beta-karoten yang diubah tubuh menjadi Vitamin A."
        );
        poolSoal[1] = new SoalKuis(
                "Mineral apa yang paling banyak dibutuhkan tulang?",
                new String[]{"Zat besi", "Magnesium", "Kalsium", "Kalium"},
                2,
                "Kalsium adalah mineral utama pembentuk dan penguat tulang."
        );
        poolSoal[2] = new SoalKuis(
                "Makronutrien mana yang memberikan energi terbanyak per gram?",
                new String[]{"Karbohidrat", "Protein", "Lemak", "Serat"},
                2,
                "Lemak menghasilkan 9 kkal/gram, dua kali lebih banyak dari karbohidrat/protein."
        );
        poolSoal[3] = new SoalKuis(
                "Berapa porsi sayuran yang dianjurkan dalam 'Isi Piringku' per hari?",
                new String[]{"1 porsi", "2 porsi", "3 porsi", "4 porsi"},
                2,
                "Pedoman 'Isi Piringku' menganjurkan 3 porsi sayuran per hari."
        );
        poolSoal[4] = new SoalKuis(
                "Kekurangan vitamin D dapat menyebabkan penyakit apa?",
                new String[]{"Anemia", "Skorbut", "Rakhitis", "Beri-beri"},
                2,
                "Rakhitis adalah pelunakan tulang akibat kekurangan vitamin D."
        );
        poolSoal[5] = new SoalKuis(
                "Protein berfungsi utama untuk apa di dalam tubuh?",
                new String[]{"Sumber energi utama", "Membangun dan memperbaiki jaringan", "Membuat tubuh haus", "Menggantikan vitamin"},
                1,
                "Protein berperan penting dalam membangun dan memperbaiki jaringan tubuh."
        );
        poolSoal[6] = new SoalKuis(
                "Zat gizi yang paling cepat menjadi sumber energi adalah?",
                new String[]{"Karbohidrat", "Vitamin", "Mineral", "Air"},
                0,
                "Karbohidrat adalah sumber energi utama dan paling cepat digunakan tubuh."
        );
        poolSoal[7] = new SoalKuis(
                "Makanan yang merupakan sumber lemak sehat adalah?",
                new String[]{"Alpukat", "Permen", "Soda", "Roti tawar"},
                0,
                "Alpukat mengandung lemak tak jenuh yang baik untuk tubuh."
        );
        poolSoal[8] = new SoalKuis(
                "Buah apa yang terkenal kaya vitamin C?",
                new String[]{"Pisang", "Jeruk", "Mangga", "Pepaya"},
                1,
                "Jeruk merupakan salah satu buah yang kaya vitamin C."
        );
        poolSoal[9] = new SoalKuis(
                "Zat besi paling banyak dibutuhkan untuk pembentukan apa?",
                new String[]{"Darah", "Tulang", "Kulit", "Rambut"},
                0,
                "Zat besi membantu pembentukan hemoglobin dalam darah."
        );
        poolSoal[10] = new SoalKuis(
                "Sumber kalsium terbaik untuk kesehatan tulang adalah?",
                new String[]{"Susu", "Minuman bersoda", "Keripik", "Permen"},
                0,
                "Susu kaya kalsium dan baik untuk menjaga kesehatan tulang."
        );
        poolSoal[11] = new SoalKuis(
                "Sayuran hijau umumnya kaya akan zat gizi apa?",
                new String[]{"Vitamin dan mineral", "Gula", "Kafein", "Kolesterol"},
                0,
                "Sayuran hijau umumnya kaya vitamin, mineral, dan serat."
        );
        poolSoal[12] = new SoalKuis(
                "Makanan berserat tinggi bermanfaat untuk apa?",
                new String[]{"Melancarkan pencernaan", "Menaikkan suhu tubuh", "Membuat haus", "Mengurangi tidur"},
                0,
                "Serat membantu melancarkan sistem pencernaan."
        );
        poolSoal[13] = new SoalKuis(
                "Vitamin A bermanfaat utama untuk apa?",
                new String[]{"Penglihatan", "Meningkatkan gula darah", "Membuat otot besar", "Mengurangi air tubuh"},
                0,
                "Vitamin A penting untuk kesehatan mata dan penglihatan."
        );
        poolSoal[14] = new SoalKuis(
                "Makanan pokok di Indonesia yang paling umum adalah?",
                new String[]{"Nasi", "Apel", "Roti manis", "Cokelat"},
                0,
                "Nasi merupakan makanan pokok yang paling umum dikonsumsi di Indonesia."
        );
        poolSoal[15] = new SoalKuis(
                "Kekurangan zat besi dapat menyebabkan?",
                new String[]{"Anemia", "Skorbut", "Obesitas", "Batu ginjal"},
                0,
                "Kekurangan zat besi dapat menyebabkan anemia defisiensi besi."
        );
        poolSoal[16] = new SoalKuis(
                "Makanan berikut yang termasuk protein hewani adalah?",
                new String[]{"Tempe", "Tahu", "Telur", "Kacang tanah"},
                2,
                "Telur merupakan salah satu sumber protein hewani."
        );
        poolSoal[17] = new SoalKuis(
                "Air putih penting untuk apa?",
                new String[]{"Membantu hidrasi tubuh", "Membuat tubuh lapar", "Menambah kolesterol", "Menghambat pernapasan"},
                0,
                "Air putih membantu menjaga hidrasi dan fungsi organ tubuh."
        );
        poolSoal[18] = new SoalKuis(
                "Makanan olahan yang terlalu manis sebaiknya?",
                new String[]{"Dikonsumsi berlebihan", "Dibatasi", "Dijadikan menu utama", "Tidak perlu diatur"},
                1,
                "Makanan tinggi gula sebaiknya dibatasi agar kesehatan tetap terjaga."
        );
        poolSoal[19] = new SoalKuis(
                "Pedoman makan seimbang di Indonesia dikenal sebagai?",
                new String[]{"Isi Piringku", "4 sehat 5 sempurna", "Makanan cepat saji", "Diet ekstrem"},
                0,
                "Isi Piringku adalah pedoman makan seimbang yang dianjurkan saat ini."
        );
    }

    /**
     * Menyinkronkan arraySoalGizi & arrayJawaban (atribut class diagram)
     * dari poolSoal agar sesuai struktur yang didefinisikan.
     */
    private void syncArray() {
        arraySoalGizi = new String[poolSoal.length];
        arrayJawaban  = new String[poolSoal.length];
        for (int i = 0; i < poolSoal.length; i++) {
            arraySoalGizi[i] = poolSoal[i].getPertanyaan();
            arrayJawaban[i]  = poolSoal[i].getJawabanBenar();
        }
    }

    // ══════════════════════════════════════════════════════════
    //  AMBIL 5 SOAL ACAK
    // ══════════════════════════════════════════════════════════

    private SoalKuis[] ambilSoalAcak() {
        SoalKuis[] salinan = new SoalKuis[poolSoal.length];
        System.arraycopy(poolSoal, 0, salinan, 0, poolSoal.length);
        Collections.shuffle(java.util.Arrays.asList(salinan));

        SoalKuis[] sesi = new SoalKuis[JUMLAH_SOAL_PER_SESI];
        System.arraycopy(salinan, 0, sesi, 0, JUMLAH_SOAL_PER_SESI);
        return sesi;
    }

    // ══════════════════════════════════════════════════════════
    //  START GAME  (implementasi method abstract MiniGame)
    // ══════════════════════════════════════════════════════════

    @Override
    public void startGame(PlayerCharacter pemain) {
        Scanner sc = new Scanner(System.in);

        tampilkanHeader();

        SoalKuis[] sesiSoal = ambilSoalAcak();
        int[] hasilPemain = new int[JUMLAH_SOAL_PER_SESI]; // jawaban yg dipilih
        boolean[] benar   = new boolean[JUMLAH_SOAL_PER_SESI];

        // ── Loop soal ──
        for (int i = 0; i < sesiSoal.length; i++) {
            SoalKuis soal = sesiSoal[i];
            tampilkanSoal(i + 1, soal);

            int pilihan = -1;
            while (pilihan < 0 || pilihan > 3) {
                System.out.print("  Jawaban kamu (1-4): ");
                try {
                    pilihan = Integer.parseInt(sc.nextLine().trim()) - 1;
                    if (pilihan < 0 || pilihan > 3) {
                        System.out.println("  ⚠ Masukkan angka 1–4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ⚠ Input tidak valid, masukkan angka 1–4.");
                    pilihan = -1;
                }
            }

            hasilPemain[i] = pilihan;
            benar[i]       = soal.cekJawaban(pilihan);

            if (benar[i]) {
                System.out.println("  ✅ Benar!\n");
            } else {
                System.out.println("  ❌ Salah! Jawaban benar: "
                        + (char)('A' + soal.getIndeksBenar()) + ". " + soal.getJawabanBenar() + "\n");
            }
        }

        // ── Hitung skor ──
        int skor = 0;
        for (boolean b : benar) if (b) skor++;

        tampilkanRangkuman(sesiSoal, hasilPemain, benar, skor);
        beriReward(pemain, skor);
        pemain.tampilkanStatus();
    }

    // ══════════════════════════════════════════════════════════
    //  TAMPILAN
    // ══════════════════════════════════════════════════════════

    private void tampilkanHeader() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║          🧠  MINI GAME — QUIZ NUTRISI           ║");
        System.out.println("║  Jawab 5 pertanyaan seputar nutrisi dan gizi!   ║");
        System.out.println("║  Reward semakin besar jika semakin banyak benar ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void tampilkanSoal(int nomor, SoalKuis soal) {
        System.out.println("─────────────────────────────────────────────────");
        System.out.printf("  Soal %d/%d: %s%n", nomor, JUMLAH_SOAL_PER_SESI, soal.getPertanyaan());
        System.out.println();
        String[] pilihan = soal.getPilihanJawaban();
        char[] huruf = {'A','B','C','D'};
        for (int i = 0; i < pilihan.length; i++) {
            System.out.printf("    %d. %c. %s%n", i + 1, huruf[i], pilihan[i]);
        }
        System.out.println();
    }

    private void tampilkanRangkuman(SoalKuis[] soalSesi,
                                    int[] hasilPemain, boolean[] benar, int skor) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.printf( "║  📋 RANGKUMAN QUIZ  —  Skor: %d/%d              ║%n",
                skor, JUMLAH_SOAL_PER_SESI);
        System.out.println("╠══════════════════════════════════════════════════╣");
        for (int i = 0; i < soalSesi.length; i++) {
            SoalKuis s   = soalSesi[i];
            String ikon  = benar[i] ? "✅" : "❌";
            // Potong pertanyaan agar muat di tabel
            String q = s.getPertanyaan().length() > 38
                    ? s.getPertanyaan().substring(0, 35) + "..."
                    : s.getPertanyaan();
            System.out.printf("║  %s Soal %d: %-38s║%n", ikon, i + 1, q);
            if (!benar[i]) {
                System.out.printf("║       Benar: %-38s║%n",
                        (char)('A' + s.getIndeksBenar()) + ". " + s.getJawabanBenar());
                System.out.printf("║       Info : %-38s║%n",
                        s.getPenjelasan().length() > 38
                                ? s.getPenjelasan().substring(0, 35) + "..."
                                : s.getPenjelasan());
            }
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    // ══════════════════════════════════════════════════════════
    //  REWARD
    // ══════════════════════════════════════════════════════════

    /**
     * Reward tier:
     *   0 benar → tidak ada reward
     *   1 benar → 10 gold
     *   2 benar → 20 gold
     *   3 benar → 30 gold
     *   4 benar → 40 gold
     *   5 benar → 100 gold
     */
    private void beriReward(PlayerCharacter pemain, int skor) {
        System.out.println("🎁 REWARD:");
        if (skor <= 0) {
            System.out.println("  Tidak ada reward. Coba lagi ya!");
        } else {
            int goldReward;
            if (skor >= JUMLAH_SOAL_PER_SESI) {
                goldReward = 100;
            } else {
                goldReward = skor * 10;
            }

            pemain.tambahKoin(goldReward);
            System.out.println("  (Reward: " + goldReward + " gold)");
        }
        System.out.println();
    }

    // ══════════════════════════════════════════════════════════
    //  GETTER
    // ══════════════════════════════════════════════════════════

    public String[] getArraySoalGizi() { return arraySoalGizi; }
    public String[] getArrayJawaban()  { return arrayJawaban; }
    public int      getJumlahSoal()    { return poolSoal.length; }

    // ══════════════════════════════════════════════════════════
    //  DEMO / ENTRY POINT
    // ══════════════════════════════════════════════════════════

    public static void main(String[] args) {
        QuizGame quiz = new QuizGame();
        PlayerCharacter pemain = new PlayerCharacter("Aria", 500);

        quiz.startGame(pemain);
    }
}