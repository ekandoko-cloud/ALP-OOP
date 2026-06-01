package DummyData;

import enums.ItemType;
import models.item.Ingredients;
import models.item.Item;

import java.util.*;

public class inqredients_consumables {

    private static final Ingredients[] DUMMY_INQREDIENTS_CONSUMABLES = new Ingredients[]{
            i(201, "Wortel", 15, "Meningkatkan akurasi dan ketajaman penglihatan (Buff Accuracy)."),
            i(202, "Selada", 10, "Menambah sedikit regenerasi HP saat dikonsumsi dalam salad."),
            i(203, "Dada Ayam", 40, "Sumber protein utama untuk meningkatkan Attack fisik."),
            i(204, "Lada Hitam", 5, "Meningkatkan metabolisme, memberikan sedikit damage tambahan."),
            i(205, "Tomat", 12, "Kaya antioksidan, menetralkan debuff racun ringan."),
            i(206, "Bawang Putih", 8, "Antibiotik alami, mempercepat pemulihan status infeksi."),
            i(207, "Gandum (Oat)", 20, "Sumber energi stabil, mencegah penurunan Kekuatan (Mana)."),
            i(208, "Pisang", 10, "Kaya kalium, mencegah efek Slow atau kelelahan."),
            i(209, "Telur Ayam", 15, "Protein lengkap untuk pemulihan stamina dasar."),
            i(210, "Brokoli", 18, "Detoksifikasi tubuh, mempercepat pemulihan dari status Poison."),
            i(211, "Ikan Salmon", 60, "Omega-3 tinggi, meningkatkan Defense (ketahanan tubuh)."),
            i(212, "Lemon", 10, "Vitamin C tinggi, memperkuat imunitas terhadap penyakit."),
            i(213, "Kacang Almond", 35, "Lemak sehat, meningkatkan durasi buff status fisik."),
            i(214, "Bayam", 12, "Zat besi tinggi, meningkatkan regenerasi HP."),
            i(215, "Ubi Jalar", 15, "Karbohidrat kompleks, memberikan Kekuatan (Mana) jangka panjang."),
            i(216, "Susu Fermentasi", 25, "Probiotik, menjaga kesehatan perut (menangkal debuff makanan)."),
            i(217, "Stroberi", 20, "Antioksidan, mempercepat cooldown skill."),
            i(218, "Blueberry", 25, "Meningkatkan fokus mental (buff kritikal)."),
            i(219, "Kentang", 10, "Karbohidrat dasar untuk pemulihan HP kecil."),
            i(220, "Minyak Jenuh (Minyak Goreng)", 10, "Meningkatkan kalori, tapi berisiko meningkatkan debuff berat badan."),
            i(221, "Garam", 5, "Elektrolit dasar, menyeimbangkan hidrasi tubuh."),
            i(222, "Roti Olahan", 20, "Pengganjal perut dasar, memulihkan sedikit HP."),
            i(223, "Daging Berlemak", 30, "Menambah HP dalam jumlah besar, tapi sedikit mengurangi Speed."),
            i(224, "Keju Imitasi", 25, "Menambah energi instan, tapi berisiko menyebabkan efek Slow."),
            i(225, "Sosis Olahan", 25, "Penambah damage instan, mengandung pengawet tinggi."),
            i(226, "MSG (Penyedap)", 5, "Meningkatkan rasa (buff Attack), tapi mengurangi akurasi (debuff)."),
            i(227, "Gula Pasir", 5, "Energy boost instan, diikuti efek lelah (debuff Speed)."),
            i(228, "Pewarna Buatan", 2, "Penambah estetika, berisiko rendah terhadap racun tubuh."),
            i(229, "Tepung Terigu", 10, "Bahan dasar pembuat makanan, meningkatkan porsi HP."),
            i(230, "Mentega", 15, "Penambah lemak dan energi, tapi berisiko kolesterol."),
            i(231, "Bumbu Instan", 10, "Memberi efek buff serangan cepat, tapi kurang nutrisi."),
            i(232, "Pengawet Makanan", 5, "Menambah durasi simpan item, tapi menurunkan nilai nutrisi."),
            i(233, "Air Kelapa Muda", 20, "Isotonik alami, memulihkan Kekuatan (Mana) dan hidrasi."),
            i(234, "Daun Teh Hijau", 15, "Antioksidan, meningkatkan Kekuatan (Mana) secara regeneratif."),
            i(235, "Jeruk Segar", 12, "Vitamin C, memberikan status Immunity (kebal penyakit)."),
            i(236, "Susu Sapi Segar", 20, "Kalsium, meningkatkan Defense dan kesehatan tulang."),
            i(237, "Jahe", 8, "Memberi efek hangat, menyembuhkan status Freeze atau dingin."),
            i(238, "Madu Alami", 45, "Antibakteri, memulihkan HP dan status luka bakar."),
            i(239, "Apel Merah", 15, "Serat tinggi, menstabilkan Kekuatan (Mana) dalam tubuh."),
            i(240, "Air Murni", 5, "Pemulihan hidrasi dasar untuk menjaga fungsi organ."),
            i(241, "Air Karbonasi", 15, "Memberikan efek Sugar Rush (Speed), diikuti rasa haus."),
            i(242, "Sirup Jagung (HFCS)", 15, "Pemanis kalori kosong, menambah Speed tapi mengurangi Max Kekuatan."),
            i(243, "Kafein Ekstrak", 30, "Penambah fokus (Critical Chance), tapi jantung berdebar (debuff)."),
            i(244, "Taurin", 30, "Penambah stamina fisik, tapi berisiko kerusakan Kekuatan jangka panjang."),
            i(245, "Pemanis Buatan", 5, "Efek rasa manis, namun tidak memiliki nilai nutrisi (kosong)."),
            i(246, "Ekstrak Kopi", 25, "Efek rasa manis, namun tidak memiliki nilai nutrisi (kosong)."),
            i(247, "Krimer Nabati", 15, "Penambah tekstur, memberikan lemak jenuh (resiko berat badan)."),
            i(248, "Gula Aren", 15, "Pemanis alami yang lebih baik, menambah Kekuatan (Mana)."),
            i(249, "Pewarna Karmin Sintetis", 5, "Memberi tampilan menarik pada minuman, tanpa nutrisi."),
            i(250, "Konsentrat Buah", 20, "Pemulihan HP kecil, sering kali rendah serat asli."),
            i(251, "Tepung Tapioka", 12, "Bahan dasar Boba, menambah Kekuatan (Mana) tapi mengurangi Speed."),
            i(252, "Teh Hitam", 10, "Antioksidan lebih pekat, meningkatkan durasi efek buff."),
            i(253, "Susu Kental Manis", 15, "Gula tinggi, energi instan tapi beresiko debuff kesehatan."),
            i(254, "Biji Kopi Fermentasi", 50, "Kopi kualitas tinggi, buff Attack dan fokus tanpa debuff besar."),
    };

    private static final HashMap<Integer, Item> DUMMY_INQREDIENTS_CONSUMABLES_MAP = initializeMap();

    private static HashMap<Integer, Item> initializeMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (Ingredients ingredient : DUMMY_INQREDIENTS_CONSUMABLES) {
            map.put(ingredient.getIdItem(), ingredient);
        }
        return map;
    }

    private static Ingredients i(int id, String nama, int hargaJual, String deskripsi) {
        return new Ingredients(id, nama, hargaJual, deskripsi, ItemType.INQREDIENT);
    }

    public static List<Ingredients> getDummyIngredientsConsumables() {
        return List.of(DUMMY_INQREDIENTS_CONSUMABLES);
    }

    public static HashMap<Integer, Item> getDummyIngredientsConsumablesMap() {
        return DUMMY_INQREDIENTS_CONSUMABLES_MAP;
    }
}
