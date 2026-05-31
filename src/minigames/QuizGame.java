package minigames;

import models.account.AccountProfile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizGame extends MiniGame {
    private static final int JUMLAH_SOAL = 5;
    private static final Quiz[] BANK_SOAL = {
            soal("Masalah stunting pada anak sering kali disebabkan oleh kurangnya asupan nutrisi tertentu dalam jangka panjang. Nutrisi apakah yang paling krusial untuk pertumbuhan tulang dan tinggi badan anak?",
                    "Protein dan Kalsium", "Lemak jenuh dan Gula", "Karbohidrat kompleks dan Lemak",
                    "Protein dan Kalsium",
                    "Protein dan Kalsium adalah bahan dasar pembentukan jaringan tubuh dan kepadatan tulang yang sangat penting untuk pertumbuhan tinggi badan yang optimal."),
            soal("Zat besi merupakan nutrisi penting untuk mencegah anemia. Mengapa anemia pada ibu hamil dapat meningkatkan risiko stunting pada bayi?",
                    "Karena anemia membuat ibu hamil cepat merasa lelah dan mengantuk.", "Karena anemia menyebabkan nafsu makan ibu hamil menurun drastis.", "Karena anemia menghambat suplai oksigen dan nutrisi ke janin, sehingga menghambat perkembangan fisik dan otaknya.",
                    "Karena anemia menghambat suplai oksigen dan nutrisi ke janin, sehingga menghambat perkembangan fisik dan otaknya.",
                    "Anemia menyebabkan suplai oksigen dan nutrisi ke janin berkurang, sehingga menghambat perkembangan fisik dan otaknya."),
            soal("Protein hewani seperti telur, ikan, dan daging dinilai sangat efektif untuk mencegah stunting dibandingkan protein nabati. Apa alasannya?",
                    "Protein hewani memiliki profil asam amino yang lebih lengkap", "Protein hewani lebih murah harganya", "Protein hewani tidak mengandung lemak",
                    "Protein hewani memiliki profil asam amino yang lebih lengkap",
                    "Protein hewani memiliki asam amino esensial yang lebih lengkap dan lebih mudah dimanfaatkan tubuh."),
            soal("Pola makan dengan gizi tidak seimbang yang hanya mengandalkan karbohidrat (seperti nasi saja) disebut dengan istilah...",
                    "Kelaparan tersembunyi (Hidden Hunger)", "Gizi lebih", "Malnutrisi akut",
                    "Kelaparan tersembunyi (Hidden Hunger)",
                    "Terjadi ketika seseorang kenyang karbohidrat, namun kekurangan mikronutrien penting seperti vitamin dan mineral."),
            soal("Mengapa pemberian ASI Eksklusif selama 6 bulan pertama sangat disarankan dalam program pencegahan stunting?",
                    "Karena ASI mengandung antibodi dan nutrisi lengkap", "Karena ASI gratis dan mudah didapat", "Karena bayi tidak butuh nutrisi lain selain ASI",
                    "Karena ASI mengandung antibodi dan nutrisi lengkap",
                    "ASI memberikan kekebalan alami dan nutrisi yang sangat pas untuk sistem pencernaan bayi."),
            soal("Zinc atau seng merupakan mineral yang penting untuk fungsi kekebalan tubuh anak. Kekurangan Zinc dapat berdampak pada...",
                    "Meningkatnya risiko infeksi dan diare", "Anak menjadi terlalu aktif", "Kerusakan gigi",
                    "Meningkatnya risiko infeksi dan diare",
                    "Zinc berperan dalam sistem imun; kekurangannya membuat anak mudah terkena infeksi penyakit yang dapat memicu stunting."),
            soal("Apa dampak jangka panjang dari stunting pada kemampuan kognitif anak di masa depan?",
                    "Penurunan kemampuan belajar dan prestasi", "Anak menjadi lebih mahir dalam olahraga", "Kemampuan berbahasa meningkat",
                    "Penurunan kemampuan belajar dan prestasi",
                    "Stunting pada masa emas perkembangan otak dapat mengakibatkan hambatan kognitif permanen."),
            soal("Sanitasi lingkungan yang buruk berhubungan dengan stunting karena...",
                    "Menyebabkan anak sering terkena infeksi pencernaan", "Membuat udara menjadi terlalu dingin", "Mengurangi asupan oksigen",
                    "Menyebabkan anak sering terkena infeksi pencernaan",
                    "Penyakit seperti diare yang berulang akan menghambat penyerapan nutrisi dalam tubuh anak."),
            soal("Sesuai dengan konsep gizi seimbang, berapa proporsi makanan pokok yang disarankan dalam sekali makan?",
                    "Sepertiga dari isi piring", "Seluruh piring harus nasi", "Sepertiga isi piring",
                    "Sepertiga dari isi piring",
                    "Dalam panduan 'Isi Piringku', 1/3 piring diisi oleh makanan pokok, 1/3 sayuran, 1/6 lauk-pauk, dan 1/6 buah-buahan."),
            soal("Nutrisi manakah yang berperan paling dominan dalam pembentukan sel-sel otak pada masa janin dan balita?",
                    "Asam lemak Omega-3", "Gula pasir", "Pewarna makanan",
                    "Asam lemak Omega-3",
                    "Asam lemak Omega-3 (seperti DHA dan EPA) adalah komponen struktural utama membran sel otak."),
            soal("Mengapa MPASI harus dimulai tepat saat bayi berusia 6 bulan?",
                    "Kebutuhan energi/nutrisi melampaui apa yang bisa diberikan ASI", "Bayi sudah mulai bosan dengan ASI", "Agar bayi bisa makan makanan orang dewasa",
                    "Kebutuhan energi/nutrisi melampaui apa yang bisa diberikan ASI",
                    "Setelah usia 6 bulan, kebutuhan bayi akan energi dan nutrisi spesifik mulai meningkat melebihi ASI saja."),
            soal("Apa dampak kekurangan vitamin A pada kesehatan mata anak?",
                    "Meningkatkan risiko kebutaan malam", "Membuat mata sering berair", "Mengubah warna iris mata",
                    "Meningkatkan risiko kebutaan malam",
                    "Vitamin A sangat penting untuk kesehatan kornea dan penglihatan pada kondisi minim cahaya."),
            soal("Konsumsi jajanan sekolah yang tinggi MSG namun rendah protein dapat menyebabkan...",
                    "Anak kenyang palsu sehingga kurang nafsu makan bergizi", "Kecerdasan anak meningkat", "Tubuh menjadi lebih kuat karena banyak bumbu",
                    "Anak kenyang palsu sehingga kurang nafsu makan bergizi",
                    "Jajanan gurih memberikan rasa kenyang sesaat tetapi tidak memberikan nutrisi untuk pertumbuhan."),
            soal("Pemberian tablet tambah darah pada remaja putri bertujuan untuk...",
                    "Mencegah anemia untuk calon ibu di masa depan", "Membuat kulit wajah lebih cerah", "Meningkatkan nafsu makan saja",
                    "Mencegah anemia untuk calon ibu di masa depan",
                    "Remaja putri yang bebas anemia akan menjadi calon ibu yang sehat, mengurangi risiko melahirkan bayi stunting."),
            soal("Apa itu stunting?",
                    "Kondisi gagal tumbuh pada anak akibat gizi kronis", "Kondisi anak yang berat badannya turun drastis", "Penyakit turunan yang tidak bisa dicegah",
                    "Kondisi gagal tumbuh pada anak akibat gizi kronis",
                    "Stunting adalah kondisi gagal tumbuh pada anak akibat kekurangan gizi kronis dalam waktu lama."),
            soal("Mengapa sayuran hijau sangat penting dalam diet gizi seimbang?",
                    "Kaya akan mikronutrien seperti vitamin dan mineral", "Sayuran hijau pengganti nasi", "Sayuran hijau mengandung semua jenis lemak",
                    "Kaya akan mikronutrien seperti vitamin dan mineral",
                    "Sayuran hijau adalah sumber utama vitamin, mineral, dan serat."),
            soal("Apa yang dimaksud dengan '1000 Hari Pertama Kehidupan'?",
                    "Periode kehamilan hingga anak berusia dua tahun", "Periode saat anak duduk di bangku SD", "Periode baru lahir sampai usia satu tahun",
                    "Periode kehamilan hingga anak berusia dua tahun",
                    "Masa kritis di mana pertumbuhan otak dan fisik anak paling pesat."),
            soal("Mengapa air minum yang tercemar dapat menyebabkan stunting?",
                    "Karena membawa patogen yang mengganggu penyerapan nutrisi", "Karena air tercemar rasanya tidak enak", "Karena air tercemar terlalu banyak mengandung mineral",
                    "Karena membawa patogen yang mengganggu penyerapan nutrisi",
                    "Pencernaan yang terganggu oleh kuman membuat nutrisi makanan tidak bisa diserap dengan baik."),
            soal("Pola makan tinggi gula namun rendah mikronutrisi seringkali menyebabkan...",
                    "Resiko diabetes dan obesitas", "Tubuh menjadi sangat kurus", "Meningkatnya tinggi badan secara drastis",
                    "Resiko diabetes dan obesitas",
                    "Konsumsi gula berlebih terus-menerus merusak metabolisme tubuh."),
            soal("Manakah kelompok makanan berikut yang merupakan sumber utama protein hewani?",
                    "Jagung", "telur", "Singkong",
                    "telur",
                    "Telur merupakan sumber protein hewani yang kaya akan asam amino, vitamin, dan mineral.")
    };

    public QuizGame() {
        super("Quiz Nutrisi", 0);
    }

    @Override
    public void startGame(AccountProfile currentProfile) {
        Scanner sc = new Scanner(System.in);
        Quiz[] sesi = ambilSoalAcak();
        int benar = 0;

        System.out.println();
        System.out.println("=== QUIZ NUTRISI ===");
        if (currentProfile != null) {
            System.out.println("Pemain: " + currentProfile.getUsername());
        }

        for (int i = 0; i < sesi.length; i++) {
            Quiz soal = sesi[i];
            tampilkanSoal(i + 1, soal);
            String jawaban = sc.nextLine().trim();
            String[] opsi = soal.getAnswerChoices();
            boolean benarJawab =
                    jawaban.equalsIgnoreCase(soal.getCorrectAnswer()) ||
                    (jawaban.equalsIgnoreCase("a") && opsi.length > 0 && opsi[0].equalsIgnoreCase(soal.getCorrectAnswer())) ||
                    (jawaban.equalsIgnoreCase("b") && opsi.length > 1 && opsi[1].equalsIgnoreCase(soal.getCorrectAnswer())) ||
                    (jawaban.equalsIgnoreCase("c") && opsi.length > 2 && opsi[2].equalsIgnoreCase(soal.getCorrectAnswer()));

            if (benarJawab) {
                benar++;
                System.out.println("Benar!");
            } else {
                System.out.println("Salah. Jawaban benar: " + soal.getCorrectAnswer());
            }
        }

        int rewardGold = benar * 10;
        if (currentProfile != null) {
            currentProfile.setTotalGold(currentProfile.getTotalGold() + rewardGold);
        }

        System.out.println();
        System.out.println("Skor akhir: " + benar + "/" + sesi.length);
        System.out.println("Hadiah: " + rewardGold + " gold");
    }

    private void tampilkanSoal(int nomor, Quiz soal) {
        System.out.println();
        System.out.println(nomor + ". \"" + soal.getQuestion() + "\"");
        char label = 'a';
        for (String opsi : soal.getAnswerChoices()) {
            System.out.println((label++) + ". " + opsi);
        }
        System.out.print("Jawabanmu: ");
    }

    private Quiz[] ambilSoalAcak() {
        List<Quiz> daftar = Arrays.asList(BANK_SOAL.clone());
        Collections.shuffle(daftar);

        Quiz[] sesi = new Quiz[JUMLAH_SOAL];
        for (int i = 0; i < JUMLAH_SOAL; i++) {
            sesi[i] = daftar.get(i);
        }
        return sesi;
    }

    private static Quiz soal(String question, String answer1, String answer2, String answer3, String correctAnswer, String explanation) {
        return new Quiz(question, new String[]{answer1, answer2, answer3}, correctAnswer, explanation);
    }
}
