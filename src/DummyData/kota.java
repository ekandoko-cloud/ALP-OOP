package DummyData;

import models.location.Location;

import java.util.*;

public class kota {

    private static final Location[] DUMMY_KOTA = new Location[]{
            k("Valerion", "Kota pelabuhan yang makmur dengan pasar segar penuh hasil laut dan pertanian. Terkenal dengan pedagang yang jujur dan sistem distribusi makanan yang adil kepada seluruh lapisan masyarakat. Menjadi harapan baru dalam memerangi kelaparan."),
            k("Asgard", "Pusat kerajaan dengan istana megah dan perpustakaan luas penuh pengetahuan tentang pertanian dan nutrisi. Para ahli kerajaan bekerja keras mengembangkan benih unggul untuk mengatasi kekurangan pangan global."),
            k("Grandis", "Lembah subur dengan perkebunan dan sawah yang luas. Penduduknya adalah petani tangguh yang telah menjaga tradisi pertanian berkelanjutan. Menjadi supplier utama biji padi dan sayuran bagi daerah sekitar."),
            k("Lumina", "Kota cahaya di tengah hutan yang tersbar dengan komunitas ahli gizi dan apoteker. Mereka mengembangkan resep makanan bergizi seimbang dari bahan lokal untuk mengatasi malnutrisi."),
            k("Aldoria", "Benteng pertahanan di dataran tinggi dengan gudang penyimpanan makanan raksasa. Terkenal dengan sistem irigasi canggih yang memungkinkan bertani sepanjang tahun meski cuaca ekstrem.")
    };

    private static final HashMap<Integer, Location> KOTA_MAP = initializeMap();

    private static HashMap<Integer, Location> initializeMap() {
        HashMap<Integer, Location> map = new HashMap<>();
        for (int i = 0; i < DUMMY_KOTA.length; i++) {
            map.put(i + 1, DUMMY_KOTA[i]);
        }
        return map;
    }

    private static Location k(String namaLokasi, String deskripsiLokasi) {
        return new Location(namaLokasi, deskripsiLokasi);
    }

    public static List<Location> getDummyKota() {
        return List.of(DUMMY_KOTA);
    }

    public static HashMap<Integer, Location> getDummyKotaMap() {
        return KOTA_MAP;
    }
}
