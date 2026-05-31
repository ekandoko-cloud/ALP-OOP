package minigames;

import models.account.AccountProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CountDownLatch;

public class SpaceGame extends MiniGame {
    private int batasWaktuDetik = 10;
    private int jumlahKetukanSpasi = 0;

    public SpaceGame() {
        super("Spasi Game", 0);
    }

    public int getBatasWaktuDetik() {
        return batasWaktuDetik;
    }

    public void setBatasWaktuDetik(int batasWaktuDetik) {
        if (batasWaktuDetik > 0) {
            this.batasWaktuDetik = batasWaktuDetik;
        }
    }

    @Override
    public void startGame(AccountProfile currentProfile) {
        jumlahKetukanSpasi = 0;
        System.out.println("=== SPACE SPAM ===");
        System.out.println("Tekan SPASI sebanyak mungkin selama " + batasWaktuDetik + " detik.");

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Mode ini butuh window desktop. Jalankan dari IDE/Windows desktop.");
            return;
        }
        CountDownLatch finished = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space Spam");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(360, 160);
            frame.setLocationRelativeTo(null);

            JLabel timerLabel = new JLabel("Sisa waktu: " + batasWaktuDetik + " detik", SwingConstants.CENTER);
            JLabel countLabel = new JLabel("Total spasi: 0", SwingConstants.CENTER);
            JLabel help = new JLabel("Klik jendela lalu tekan SPACE. Tidak perlu ENTER.", SwingConstants.CENTER);

            JPanel panel = new JPanel(new GridLayout(3, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(timerLabel);
            panel.add(countLabel);
            panel.add(help);

            final int[] total = {0};
            final boolean[] ended = {false};
            final int[] remaining = {batasWaktuDetik};

            frame.add(panel);
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (!ended[0] && e.getKeyCode() == KeyEvent.VK_SPACE) {
                        total[0]++;
                        countLabel.setText("Total spasi: " + total[0]);
                    }
                }
            });

            Timer timer = new Timer(1000, ev -> {
                remaining[0]--;
                if (remaining[0] > 0) {
                    timerLabel.setText("Sisa waktu: " + remaining[0] + " detik");
                } else {
                    ended[0] = true;
                    jumlahKetukanSpasi = total[0];
                    if (frame.isDisplayable()) frame.dispose();
                    finished.countDown();
                }
            });

            frame.setVisible(true);
            frame.requestFocus();
            timer.start();
        });

        try {
            finished.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // jumlahKetukanSpasi already set in EDT
        System.out.println("WAKTU HABIS! Total spasi: " + jumlahKetukanSpasi);
        reward(currentProfile);
        System.out.println("=== PROFIL PEMAIN ===");
        System.out.println("Nama: " + currentProfile.getUsername());
        System.out.println("Gold: " + currentProfile.getTotalGold());
    }

    private void reward(AccountProfile currentProfile) {
        int gold;
        if (jumlahKetukanSpasi < 20) {
            gold = 0;
        } else if (jumlahKetukanSpasi < 40) {
            gold = 50;
        } else if (jumlahKetukanSpasi < 60) {
            gold = 75;
        } else if (jumlahKetukanSpasi < 80) {
            gold = 100;
        } else {
            gold = 200;
        }

        if (gold > 0) {
            currentProfile.setTotalGold(currentProfile.getTotalGold() + gold);
            System.out.println("(Reward: " + gold + " gold)");
        } else {
            System.out.println("No Reward! Better Luck Next Time!");
        }
        System.out.println();
    }

    public int getJumlahKetukanSpasi() {
        return jumlahKetukanSpasi;
    }

    public void setJumlahKetukanSpasi(int jumlahKetukanSpasi) {
        this.jumlahKetukanSpasi = jumlahKetukanSpasi;
    }
}
