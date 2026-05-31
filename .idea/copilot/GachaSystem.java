import java.util.Random;
import DummyData.gacha;
import models.account.AccountProfile;
import models.item.Equipment;
import models.item.Item;
import systems.gacha.itemGacha;

/**
 * GachaSystem.java
 * Fitur 3.2.14 - Fitur Gacha (NutriTale - Kelompok 6)
 *
 * Sistem gacha menggunakan weighted random selection berdasarkan rarity.
 * Pool item disimpan dalam array itemGacha yang mencakup equipment, probabilitas, dan rarity.
 */
public class GachaSystem {

    // -------------------------------------------------------------------------
    // KONSTANTA
    // -------------------------------------------------------------------------

    private static final int BIAYA_GACHA_1X  = 50;
    private static final int BIAYA_GACHA_10X = 500; // 50 x 10

    // -------------------------------------------------------------------------
    // POOL ITEM
    // Menggunakan itemGacha yang menggabungkan equipment, probabilitas, dan rarity
    // -------------------------------------------------------------------------

    /** Pool item gacha dari dummy data */
    private itemGacha[] poolItem;

    // -------------------------------------------------------------------------
    // DEPENDENSI
    // -------------------------------------------------------------------------

    private Random random = new Random();

    // -------------------------------------------------------------------------
    // KONSTRUKTOR
    // -------------------------------------------------------------------------

    /**
     * Inisialisasi GachaSystem dengan data dari DummyData.
     * Mengambil pool item dan bobot dari gacha dummy data.
     */
    public GachaSystem() {
        // Ambil pool item dari dummy data
        this.poolItem = gacha.getDummyGacha();
    }

    /**
     * Inisialisasi GachaSystem dengan pool item dan bobot custom.
     *
     * @param poolItem array itemGacha yang berisi equipment dan metadata
     */
    public GachaSystem(itemGacha[] poolItem) {
        if (poolItem == null || poolItem.length == 0) {
            throw new IllegalStateException(
                    "[GachaSystem] Pool item tidak boleh kosong!"
            );
        }
        this.poolItem = poolItem;
    }

    // -------------------------------------------------------------------------
    // METODE PUBLIK UTAMA
    // -------------------------------------------------------------------------

    /**
     * Melakukan gacha 1x.
     * Mengurangi gold pemain sebesar BIAYA_GACHA_1X jika mencukupi,
     * lalu mengembalikan 1 item hasil weighted random selection.
     *
     * @param profil AccountProfile pemain
     * @return Equipment yang didapat, atau null jika gold tidak cukup
     */
    public Equipment pull(AccountProfile profil) {
        if (!bayar(profil, BIAYA_GACHA_1X)) {
            return null;
        }

        itemGacha terpilih = poolItem[pilihIndex()];
        Equipment hasil = salinEquipment(terpilih.getEquipment());
        profil.addItemToInventory(hasil);

        return hasil;
    }

    /**
     * Melakukan gacha 10x.
     * Mengurangi gold pemain sebesar BIAYA_GACHA_10X jika mencukupi,
     * lalu mengembalikan array 10 item hasil weighted random selection.
     *
     * @param profil AccountProfile pemain
     * @return array 10 Equipment yang didapat, atau null jika gold tidak cukup
     */
    public Equipment[] tarikSepuluh(AccountProfile profil) {
        if (!bayar(profil, BIAYA_GACHA_10X)) {
            return null;
        }

        Equipment[] hasil = new Equipment[10];

        // Melakukan weighted random selection 10x dan menambahkan ke inventory
        for (int i = 0; i < hasil.length; i++) {
            itemGacha terpilih = poolItem[pilihIndex()];
            hasil[i] = salinEquipment(terpilih.getEquipment());
            profil.addItemToInventory(hasil[i]);
        }

        return hasil;
    }

    /**
     * Menampilkan pool item yang tersedia beserta rarity dan persentase peluang.
     */
    public void tampilkanPoolItem() {
        System.out.println("\n========================================");
        System.out.println("         POOL ITEM GACHA               ");
        System.out.println("========================================");
        System.out.printf("%-25s %-12s %s%n", "Nama Item", "Rarity", "Peluang");
        System.out.println("----------------------------------------");

        int totalBobot = hitungTotalBobot();

        for (int i = 0; i < poolItem.length; i++) {
            double persen = (poolItem[i].getProbabilitas() * 100.0) / totalBobot;
            System.out.printf("%-25s %-12s %.1f%%%n",
                    poolItem[i].getEquipment().getNamaItem(),
                    poolItem[i].getRarity(),
                    persen);
        }

        System.out.println("========================================");
        System.out.println("Biaya Gacha 1x  : " + BIAYA_GACHA_1X + " gold");
        System.out.println("Biaya Gacha 10x : " + BIAYA_GACHA_10X + " gold");
        System.out.println("========================================\n");
    }

    public itemGacha[] getPoolItem() {
        return poolItem;
    }

    public void setPoolItem(itemGacha[] poolItem) {
        this.poolItem = poolItem;
    }

    public int getBiayaGacha1x() {
        return BIAYA_GACHA_1X;
    }

    public int getBiayaGacha10x() {
        return BIAYA_GACHA_10X;
    }

    // -------------------------------------------------------------------------
    // METODE PRIVAT - LOGIKA INTI
    // -------------------------------------------------------------------------

    /**
     * Mengecek apakah gold pemain mencukupi, lalu menguranginya.
     *
     * @param profil  AccountProfile pemain
     * @param biaya   jumlah gold yang dibutuhkan
     * @return true jika gold cukup dan berhasil dikurangi, false jika tidak cukup
     */
    private boolean bayar(AccountProfile profil, int biaya) {
        if (profil == null || profil.getTotalGold() < biaya) {
            return false;
        }
        profil.setTotalGold(profil.getTotalGold() - biaya);
        return true;
    }

    /**
     * Melakukan weighted random selection untuk memilih index item dari pool.
     * Menggunakan metode cumulative weight.
     *
     * @return indeks item yang terpilih dari poolItem
     */
    private int pilihIndex() {
        int totalBobot = hitungTotalBobot();
        int angkaAcak  = random.nextInt(totalBobot); // 0 s.d. totalBobot-1

        // Cumulative weight selection
        int kumulatif = 0;
        for (int i = 0; i < poolItem.length; i++) {
            kumulatif += poolItem[i].getProbabilitas();
            if (angkaAcak < kumulatif) {
                return i;
            }
        }

        // Fallback (seharusnya tidak pernah tercapai)
        return poolItem.length - 1;
    }

    /**
     * Menghitung total semua bobot probabilitas.
     *
     * @return total dari semua bobot
     */
    private int hitungTotalBobot() {
        int total = 0;
        for (itemGacha item : poolItem) {
            total += item.getProbabilitas();
        }
        return total;
    }

    /**
     * Membuat salinan Equipment baru dengan property yang sama.
     * Diperlukan untuk menghindari reference sharing.
     *
     * @param original Equipment original dari pool
     * @return salinan Equipment dengan property yang sama
     */
    private Equipment salinEquipment(Equipment original) {
        Equipment salinan = new Equipment(
                original.getIdItem(),
                original.getNamaItem(),
                original.getHargaJual(),
                original.getDeskripsi(),
                original.getItemType(),
                original.getBonusKekuatan(),
                original.getBonusDefense(),
                original.getLevelTempa()
        );
        salinan.setTipeEquipment(original.getTipeEquipment());
        return salinan;
    }


}