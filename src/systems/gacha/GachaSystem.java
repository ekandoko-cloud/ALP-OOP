package systems.gacha;

import java.util.Random;

import DummyData.gacha;
import models.account.AccountProfile;
import models.item.Item;

public class GachaSystem {
    private static final int BIAYA_GACHA_1X = 50;
    private static final int BIAYA_GACHA_10X = 500;
    private static final int NAMA_HADIAH_MAX = 24;

    private itemGacha[] poolItem;
    private Random random = new Random();

    public GachaSystem() {
        this(gacha.getDummyGacha());
    }

    public GachaSystem(itemGacha[] poolItem) {
        this.poolItem = poolItem;
    }

    public itemGacha[] getPoolItem() {
        return poolItem;
    }

    public void setPoolItem(itemGacha[] poolItem) {
        this.poolItem = poolItem;
    }

    public void tampilkanDaftarHadiah() {
        System.out.println();
        if (poolItem == null || poolItem.length == 0) {
            System.out.println("=============== DAFTAR HADIAH GACHA ===============");
            System.out.println("Daftar hadiah kosong.");
            System.out.println();
            return;
        }

        System.out.println("=============== DAFTAR HADIAH GACHA ===============");
        System.out.printf("%-3s %-24s %-12s %s%n", "No", "Hadiah", "Rarity", "Prob");
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < poolItem.length; i++) {
            itemGacha item = poolItem[i];
            String nama = (item == null || item.getEquipment() == null || item.getEquipment().getNamaItem() == null)
                    ? "-"
                    : item.getEquipment().getNamaItem();
            if (nama.length() > NAMA_HADIAH_MAX) {
                nama = nama.substring(0, NAMA_HADIAH_MAX - 3) + "...";
            }
            String rarity = (item == null || item.getRarity() == null) ? "-" : item.getRarity();
            int prob = item == null ? 0 : item.getProbabilitas();
            System.out.printf("%-3d %-24s %-12s %d%n", (i + 1), nama, rarity, prob);
        }

        System.out.println("---------------------------------------------------");
        System.out.println();
    }

    public Item pull(AccountProfile profil) {
        if (profil == null || poolItem == null || poolItem.length == 0) {
            return null;
        }
        int currentInven = profil.getInventory() == null ? 0 : profil.getInventory().size();
        if (currentInven >= profil.getMaxInventorySlots()) {
            return null;
        }
        if (profil.getTotalGold() < BIAYA_GACHA_1X) {
            return null;
        }

        int index = pilihIndex();
        Item hasil = poolItem[index] == null ? null : poolItem[index].getEquipment();
        if (hasil == null) {
            return null;
        }

        profil.setTotalGold(profil.getTotalGold() - BIAYA_GACHA_1X);
        profil.addItemToInventory(hasil);
        return hasil;
    }

    public Item[] pullTen(AccountProfile profil) {
        if (profil == null || poolItem == null || poolItem.length == 0) {
            return null;
        }
        int currentInven = profil.getInventory() == null ? 0 : profil.getInventory().size();
        if (currentInven + 10 > profil.getMaxInventorySlots()) {
            return null;
        }
        if (profil.getTotalGold() < BIAYA_GACHA_10X) {
            return null;
        }

        Item[] hasil = new Item[10];
        for (int i = 0; i < hasil.length; i++) {
            int index = pilihIndex();
            hasil[i] = poolItem[index] == null ? null : poolItem[index].getEquipment();
            if (hasil[i] == null) {
                return null;
            }
        }

        profil.setTotalGold(profil.getTotalGold() - BIAYA_GACHA_10X);
        for (int i = 0; i < hasil.length; i++) {
            profil.addItemToInventory(hasil[i]);
        }
        return hasil;
    }


    private int pilihIndex() {
        if (poolItem == null || poolItem.length == 0) {
            return 0;
        }
        int total = 0;
        for (itemGacha item : poolItem) {
            if (item != null && item.getProbabilitas() > 0) {
                total += item.getProbabilitas();
            }
        }

        if (total <= 0) {
            return 0;
        }

        int acak = random.nextInt(total);
        int kumulatif = 0;
        for (int i = 0; i < poolItem.length; i++) {
            itemGacha item = poolItem[i];
            if (item == null || item.getProbabilitas() <= 0) {
                continue;
            }
            kumulatif += item.getProbabilitas();
            if (acak < kumulatif) {
                return i;
            }
        }
        return poolItem.length - 1;
    }


}


