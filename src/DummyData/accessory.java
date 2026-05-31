package DummyData;

import models.item.Accessory;
import models.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class accessory {

    private static final ArrayList<Accessory> DUMMY_ACCESSORIES = new ArrayList<>();

    static {
        DUMMY_ACCESSORIES.add(a(1, "Rusted Iron Ring", 25, "Tier: Common | Cincin besi berkarat tua yang ditemukan di reruntuhan parit pertahanan.", 0, 5));
        DUMMY_ACCESSORIES.add(a(2, "Copper Bangle", 20, "Tier: Common | Gelang tembaga sederhana yang biasa dipakai oleh para petualang pemula.", 3, 0));
        DUMMY_ACCESSORIES.add(a(3, "Wooden Bead Necklace", 30, "Tier: Common | Kalung dari untaian manik-manik kayu purba yang dipercaya membawa kebugaran.", 0, 0));
        DUMMY_ACCESSORIES.add(a(4, "Dull Silver Earring", 35, "Tier: Common | Anting perak kusam yang sudah kehilangan kilaunya namun menyimpan sedikit magis.", 2, 2));
        DUMMY_ACCESSORIES.add(a(5, "Crude Leather Wristband", 22, "Tier: Common | Gelang kulit kasar berkancing besi untuk memperkokoh pergelangan tangan.", 0, 4));
        DUMMY_ACCESSORIES.add(a(6, "Quartz Pendant", 35, "Tier: Common | Kalung liontin batu kuarsa mentah yang terasa dingin saat menyentuh kulit.", 0, 0));
        DUMMY_ACCESSORIES.add(a(7, "Polished Jade Ring", 110, "Tier: Uncommon | Cincin batu giok halus yang memancarkan hawa ketenangan dan fokus bertarung.", 5, 15));
        DUMMY_ACCESSORIES.add(a(8, "Sturdy Iron Bangle", 95, "Tier: Uncommon | Gelang besi tebal yang dirancang untuk membantu menahan guncangan serangan.", 0, 20));
        DUMMY_ACCESSORIES.add(a(9, "Bitter Herb Amulet", 100, "Tier: Uncommon | Azimat kantung berisi tanaman obat pahit yang aromanya menyegarkan tubuh.", 0, 0));
        DUMMY_ACCESSORIES.add(a(10, "Bandit's Lucky Coin", 85, "Tier: Uncommon | Koin emas keberuntungan milik gembong penyamun yang telah dilubangi tengahnya.", 8, 0));
        DUMMY_ACCESSORIES.add(a(11, "Luminous Fiber Choker", 120, "Tier: Uncommon | Kalung choker dari rajutan serat hutan ajaib yang bersinar redup di kegelapan.", 5, 0));
        DUMMY_ACCESSORIES.add(a(12, "Silver Chain Necklace", 90, "Tier: Uncommon | Kalung rantai perak murni yang elegan dan ringan saat dikenakan.", 12, 0));
        DUMMY_ACCESSORIES.add(a(13, "Sanguine Ring", 260, "Tier: Rare | Cincin bermata permata merah darah yang berdenyut selaras dengan detak jantung.", 15, 0));
        DUMMY_ACCESSORIES.add(a(14, "Blighted Root Brooch", 215, "Tier: Rare | Bros dari potongan akar pohon terkutuk yang mengeras seperti baja hitam.", 0, 30));
        DUMMY_ACCESSORIES.add(a(15, "Amethyst Earrings", 280, "Tier: Rare | Anting batu kecubung ungu yang konon dapat meningkatkan kekuatan otot penggunanya.", 25, 0));
        DUMMY_ACCESSORIES.add(a(16, "Obsidian Band", 250, "Tier: Rare | Ikat cincin dari pecahan obsidian tajam, memancarkan aura ofensif yang pekat.", 25, 10));
        DUMMY_ACCESSORIES.add(a(17, "Commander's Signet Ring", 270, "Tier: Rare | Cincin stempel resmi milik komandan militer, lambang ketangguhan tak tergoyahkan.", 20, 20));
        DUMMY_ACCESSORIES.add(a(18, "Pure Spring Amulet", 230, "Tier: Rare | Jimat gelembung air suci dari mata air pegunungan yang tak pernah surut.", 0, 0));
        DUMMY_ACCESSORIES.add(a(19, "Aegis Diamond Ring", 510, "Tier: Epic | Cincin berlian Aegis raksasa yang menciptakan lapisan pelindung tak kasat mata.", 0, 80));
        DUMMY_ACCESSORIES.add(a(20, "Black Owl Insignia Talisman", 420, "Tier: Epic | Jimat lambang burung hantu hitam peninggalan organisasi pembunuh bayaran legendaris.", 45, 0));
        DUMMY_ACCESSORIES.add(a(21, "Titanium Bangle", 460, "Tier: Epic | Gelang padat berbahan titanium murni, sangat sulit untuk digores atau dihancurkan.", 0, 50));
        DUMMY_ACCESSORIES.add(a(22, "Poison Stinger Earring", 400, "Tier: Epic | Anting hiasan yang dibuat dari jarum penyengat monster kalajengking gurun.", 40, 0));
        DUMMY_ACCESSORIES.add(a(23, "Heart of the Mountain Pendant", 550, "Tier: Epic | Liontin batu inti bumi yang menyimpan bobot dan ketahanan sebuah gunung.", 0, 0));
        DUMMY_ACCESSORIES.add(a(24, "Ring of Gluttonous Hoard", 720, "Tier: Legendary | Cincin kerakusan yang menarik energi kehidupan dari sisa jarahan sekitar.", 0, 100));
        DUMMY_ACCESSORIES.add(a(25, "Mortis' Toxic Needle Brooch", 690, "Tier: Legendary | Bros jarum beracun ciptaan Dr. Mortis yang mematikan dan sangat ditakuti.", 80, 0));
        DUMMY_ACCESSORIES.add(a(26, "Genesis Seed Necklace", 750, "Tier: Legendary | Kalung berisi benih murni dari pohon awal mula kehidupan, kaya akan vitalitas.", 0, 30));
        DUMMY_ACCESSORIES.add(a(27, "Chimera's Heart Amulet", 800, "Tier: Legendary | Azimat mengerikan yang dikristalisasi dari jantung binatang buas Chimera.", 120, 80));
        DUMMY_ACCESSORIES.add(a(28, "Loop of the Infinite Cosmos", 950, "Tier: Mythic | Cincin tanpa ujung yang berputar membawa fragmen energi galaksi tak terbatas.", 200, 0));
        DUMMY_ACCESSORIES.add(a(29, "Seraphim's Eternal Blessing", 920, "Tier: Mythic | Kalung suci yang ditempa oleh tangan malaikat, membawa berkah perlindungan abadi.", 0, 50));
        DUMMY_ACCESSORIES.add(a(30, "Ring of Zero Famine", 1000, "Tier: Mythic | Cincin mitologi pembawa kemakmuran mutlak, melenyapkan segala kelemahan penggunanya.", 150, 150));
    }

    private static Accessory a(int id, String name, int price, String desc, int bonusKekuatan, int bonusDefense) {
        return new Accessory(id, name, price, desc, bonusKekuatan, bonusDefense, 0);
    }

    public static List<Accessory> generateDummyAccessories() {
        return new ArrayList<>(DUMMY_ACCESSORIES);
    }

    public static List<Accessory> getDummyAccessories() {
        return generateDummyAccessories();
    }

    public static Accessory[] getDummyAccessoriesArray() {
        return DUMMY_ACCESSORIES.toArray(new Accessory[0]);
    }

    public static HashMap<Integer, Item> getDummyAccessoriesMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (Accessory accessory : DUMMY_ACCESSORIES) {
            map.put(accessory.getIdItem(), accessory);
        }
        return map;
    }
}
