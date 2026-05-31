import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SpaceGame — versi sederhana dan rapi
 *
 * Fitur:
 *  - Pemain menekan SPASI sebanyak-banyaknya dalam durasi tertentu (default 10 detik).
 *  - Ada mode simulasi (untuk IDE) dan mode terminal nyata.
 *  - Hadiah berupa gold berdasarkan performance.
 *
 * Cara pakai:
 *  - Jalankan dari terminal (atau IDE). Program akan menunggu ENTER untuk mulai.
 *  - Setelah ENTER ditekan, mulai tekan SPASI sebanyak mungkin selama durasi.
 *  - Untuk testing di IDE, aktifkan simulasi dengan setModeSimulasi(true).
 */
public class SpaceGame extends MiniGame {
    private int durationSec = 10;      // durasi permainan dalam detik
    private int count = 0;             // jumlah ketukan SPASI yang terhitung
    // no manual sim flag; mode will be detected automatically at runtime

    public SpaceGame() {
        super("Spasi Spam", 0);
    }

    /** Ubah durasi permainan (detik) */
    public void setDuration(int sec) {
        if (sec > 0) this.durationSec = sec;
    }

    @Override
    public void startGame(PlayerCharacter pemain) {
        count = 0;
        System.out.println("=== SPACE SPAM ===");
        System.out.println("Tekan SPASI sebanyak mungkin dalam " + durationSec + " detik.");
        System.out.println("Tekan ENTER untuk mulai...");

        // Tunggu ENTER agar player siap. Gunakan BufferedReader untuk
        // menghindari masalah buffering jika aplikasi utama juga memakai Scanner.
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
        } catch (IOException ignored) {}

        boolean terminalMode = System.console() != null;
        if (!terminalMode) {
            // fallback: likely running inside IDE / no console -> simulate
            System.out.println("[INFO] Mode otomatis: simulasi (console tidak terdeteksi).");
        } else {
            System.out.println("[INFO] Mode otomatis: terminal.");
        }

        if (!terminalMode) {
            // Mode simulasi: tambahkan ketukan acak setiap 200ms
            long end = System.currentTimeMillis() + durationSec * 1000L;
            while (System.currentTimeMillis() < end) {
                if (Math.random() < 0.2) count++;
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
            }
        } else {
            System.out.println("Mulai! Tekan SPASI...");
            long end = System.currentTimeMillis() + durationSec * 1000L;

            // blocking reader thread untuk menghindari busy-wait
            java.util.concurrent.atomic.AtomicBoolean running = new java.util.concurrent.atomic.AtomicBoolean(true);
            java.util.concurrent.atomic.AtomicInteger counter = new java.util.concurrent.atomic.AtomicInteger(0);

            Thread reader = new Thread(() -> {
                try {
                    while (running.get()) {
                        int k = System.in.read();
                        if (k == -1) break;
                        if (k == 32) counter.incrementAndGet();
                    }
                } catch (IOException ignored) {}
            }, "space-reader");
            reader.setDaemon(true);
            reader.start();

            try {
                while (System.currentTimeMillis() < end) {
                    long now = System.currentTimeMillis();
                    long sisa = Math.max(0, (end - now + 999) / 1000);
                    System.out.printf("\r  ⏱ Waktu tersisa: %2d detik | Spasi: %d    ", sisa, counter.get());
                    System.out.flush();
                    try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                }
            } finally {
                running.set(false);
                reader.interrupt();
                try { reader.join(100); } catch (InterruptedException ignored) {}
                count = counter.get();
            }
        }

        System.out.println();
        System.out.println("WAKTU HABIS! Total spasi: " + count);
        beriReward(pemain);
        pemain.tampilkanStatus();
    }

    /** Hadiah gold berdasarkan jumlah ketukan */
    private void beriReward(PlayerCharacter pemain) {
        int gold;
        if (count < 20) gold = 0;
        else if (count < 40) gold = 75;
        else if (count < 60) gold = 175;
        else if (count < 80) gold = 300;
        else gold = 500;

        if (gold > 0) {
            pemain.tambahKoin(gold);
            System.out.println("(Reward: " + gold + " gold)");
            if (gold >= 500) System.out.println("🏆 BONUS JACKPOT!");
        } else {
            System.out.println("No Reward! Better Luck Next Time!");
        }
        System.out.println();
    }

    // Demo / contoh pemakaian
    public static void main(String[] args) {
        SpaceGame game = new SpaceGame();
        PlayerCharacter pemain = new PlayerCharacter("Aria", 500);

        // Demo: kita deteksi mode otomatis (console tersedia -> terminal mode,
        // jika tidak -> simulasi). Tidak perlu setter manual.
        if (System.console() == null) {
            System.out.println("[DEMO] Console not detected — running simulation.");
        } else {
            System.out.println("[DEMO] Console detected — running terminal mode.");
        }

        game.startGame(pemain);
    }
}