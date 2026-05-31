package DummyData;

import enums.ClassType;
import models.item.Armor;
import models.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class armor {

    private static final ArrayList<Armor> DUMMY_ARMORS = new ArrayList<>();

    static {
        // Warrior
        DUMMY_ARMORS.add(a(1, "Rusted Iron Plate", 50, "Tier: Common | Pelat besi tua berkarat yang menawarkan perlindungan seadanya.", 10, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(2, "Heavy Chainmail", 60, "Tier: Common | Jalinan rantai besi berat yang cukup meredam sabetan senjata tajam.", 12, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(3, "Scale Mail", 55, "Tier: Common | Pelindung dari sisik besi murah yang dirangkai di atas lapisan kulit hewan.", 11, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(4, "Copper Breastplate", 120, "Tier: Uncommon | Pelindung dada dari tembaga murni, ringan namun cukup kokoh.", 22, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(5, "Purified Iron Mail", 140, "Tier: Uncommon | Zirah besi yang diberkati, memberikan rasa aman dan hangat bagi penggunanya.", 25, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(6, "Bandit's Carapace", 160, "Tier: Uncommon | Pelindung keras terbuat dari cangkang monster rawa, favorit para perampok.", 28, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(7, "Silver Platemail", 250, "Tier: Rare | Set zirah perak berkilau yang mampu memantulkan cahaya matahari.", 45, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(8, "Noxious Wolf Hide", 290, "Tier: Rare | Jubah dari kulit serigala beracun, sangat tebal dan kebal terhadap robekan.", 52, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(9, "Commander's Vanguard Armor", 270, "Tier: Rare | Baju zirah resmi komandan garis depan, tangguh di segala medan perang.", 48, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(10, "Obsidian Greatplate", 415, "Tier: Epic | Pelat legendaris dari batu obsidian pekat yang sangat keras dan tahan api.", 75, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(11, "Blight-Bane Mail", 455, "Tier: Epic | Zirah titanium anti-wabah yang dirancang untuk menahan korosi zat asam monster.", 82, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(12, "Armor of Genesis", 665, "Tier: Legendary | Zirah keramat yang ditempa dari sisa-sisa energi penciptaan dunia.", 120, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(13, "Crimson Warlord Plate", 635, "Tier: Legendary | Pelat baja milik sang panglima perang penakluk naga merah.", 115, ClassType.WARRIOR));
        DUMMY_ARMORS.add(a(14, "Aegis of the Undying Dawn", 1000, "Tier: Mythic | Pelindung suci pembawa fajar keabadian yang konon tidak bisa hancur.", 180, ClassType.WARRIOR));

        // Archer
        DUMMY_ARMORS.add(a(15, "Frayed Leather Vest", 30, "Tier: Common | Rompi kulit usang dengan beberapa jahitan yang sudah lepas.", 6, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(16, "Scavenger's Cloak", 25, "Tier: Common | Jubah tipis dari kain linen bekas, cocok untuk menyamar di tumpukan sampah.", 5, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(17, "Simple Jerkin", 35, "Tier: Common | Pakaian kulit sederhana yang sangat fleksibel untuk bergerak bebas.", 7, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(18, "Iron-Reinforced Vest", 80, "Tier: Uncommon | Rompi kulit yang diperkuat bilah besi tipis di bagian vital.", 15, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(19, "Tainted Leather Coat", 100, "Tier: Uncommon | Mantel kulit yang berbau aneh akibat cipratan getah beracun hutan belantara.", 18, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(20, "Plagued Wood Garb", 90, "Tier: Uncommon | Pakaian berburu dari anyaman serat kayu terkutuk yang sangat ulet.", 16, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(21, "Silver Lining Cloak", 180, "Tier: Rare | Jubah indah berserat perak yang mempermudah pergerakan senyap dimalam hari.", 32, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(22, "Venomous Hunter Jacket", 210, "Tier: Rare | Jaket berburu tahan air yang dilapisi minyak taring hewan berbisa.", 38, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(23, "Black Owl Tactical Vest", 195, "Tier: Rare | Rompi taktis berwarna gelap, dirancang khusus untuk misi pengintaian malam.", 35, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(24, "Lunarite Ranger Tunic", 330, "Tier: Epic | Tunik pelindung berbahan lunarite yang memancarkan pendar redup cahaya bintang.", 60, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(25, "Crimson Stalker Garb", 360, "Tier: Epic | Pakaian berburu merah darah yang menyerap hawa keberadaan penggunanya.", 65, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(26, "Garb of the Purified Forest", 525, "Tier: Legendary | Pakaian suci titipan roh penjaga hutan purba yang penuh berkah kelincahan.", 95, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(27, "Toxic Chimera Carapace", 550, "Tier: Legendary | Pelindung dada dari cangkang keras Chimera beracun.", 100, ClassType.ARCHER));
        DUMMY_ARMORS.add(a(28, "Starfall Cloak of Artemis", 830, "Tier: Mythic | Jubah legendaris milik sang dewi perburuan, ditenun dari debu kosmis.", 150, ClassType.ARCHER));

        // Mage
        DUMMY_ARMORS.add(a(29, "Novice Robe", 20, "Tier: Common | Jubah katun standar bagi para pelajar sihir pemula.", 4, ClassType.MAGE));
        DUMMY_ARMORS.add(a(30, "Frayed Tunic", 25, "Tier: Common | Tunik lusuh bertambal yang sudah kehilangan sebagian besar serat kainnya.", 5, ClassType.MAGE));
        DUMMY_ARMORS.add(a(31, "Patchwork Cloak", 20, "Tier: Common | Jubah rajutan dari berbagai macam potong kain sisa lokakarya.", 4, ClassType.MAGE));
        DUMMY_ARMORS.add(a(32, "Copper-Stitched Vestment", 65, "Tier: Uncommon | Jubah sembahyang penyihir dengan sulaman benang tembaga penstabil mana.", 12, ClassType.MAGE));
        DUMMY_ARMORS.add(a(33, "Blighted Silk Robe", 80, "Tier: Uncommon | Jubah sutra gelap yang memancarkan aura magis misterius.", 15, ClassType.MAGE));
        DUMMY_ARMORS.add(a(34, "Toad Skin Mantle", 75, "Tier: Uncommon | Mantel ganjil bertekstur kasar dari kulit katak rawa raksasa.", 14, ClassType.MAGE));
        DUMMY_ARMORS.add(a(35, "Silver-Threaded Robe", 155, "Tier: Rare | Jubah anggun bersulam benang perak murni penambah fokus meditasi.", 28, ClassType.MAGE));
        DUMMY_ARMORS.add(a(36, "Noxious Slime Vestment", 175, "Tier: Rare | Jubah magis berlapis lendir asam yang tahan terhadap hantaman mantra elemen.", 32, ClassType.MAGE));
        DUMMY_ARMORS.add(a(37, "Crimson Cultist Robe", 165, "Tier: Rare | Jubah merah gelap milik pengikut sekte terlarang, memancarkan hawa mistis.", 30, ClassType.MAGE));
        DUMMY_ARMORS.add(a(38, "Obsidian Core Robe", 305, "Tier: Epic | Jubah tebal berinti batu obsidian cair, melindungi dari suhu ekstrem.", 55, ClassType.MAGE));
        DUMMY_ARMORS.add(a(39, "Parasitic Cursed Robe", 320, "Tier: Epic | Jubah terkutuk yang secara pasif memakan sisa energi magis di sekitar pengguna.", 58, ClassType.MAGE));
        DUMMY_ARMORS.add(a(40, "Robe of Elemental Mastery", 470, "Tier: Legendary | Jubah agung penakluk elemen, ditenun dari serat meteorit purba.", 85, ClassType.MAGE));
        DUMMY_ARMORS.add(a(41, "Hexing Shroud of Dr. Mortis", 485, "Tier: Legendary | Kain kafan kelam milik dokter wabah, kebal terhadap segala jenis racun.", 88, ClassType.MAGE));
        DUMMY_ARMORS.add(a(42, "Vestment of the Cosmic Eye", 720, "Tier: Mythic | Jubah suci bertatahkan pecahan galaksi yang dapat melipatgandakan sirkulasi mana.", 130, ClassType.MAGE));

        // Support - set 1
        DUMMY_ARMORS.add(a(43, "Wooden Plated Vest", 60, "Tier: Common | Rompi berpelat kayu keras, perlindungan dasar bagi garda penolong.", 12, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(44, "Rusted Guardian Mail", 70, "Tier: Common | Zirah pelindung tua peninggalan ksatria kuil masa lalu.", 14, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(45, "Novice Plate", 65, "Tier: Common | Pelat besi standar yang biasa digunakan oleh para pelindung pemula.", 13, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(46, "Iron Bulwark Coat", 155, "Tier: Uncommon | Mantel zirah besi kokoh pembendung serangan garis depan.", 28, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(47, "Guard's Heavy Mail", 165, "Tier: Uncommon | Baju rantai berat berlapis kulit tebal milik penjaga benteng kota.", 30, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(48, "Purified Iron Bulwark", 175, "Tier: Uncommon | Pelindung dada besi yang telah disucikan dari pengaruh energi jahat.", 32, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(49, "Silver Bastion Plate", 305, "Tier: Rare | Baju pelat perak murni bercahaya yang meningkatkan moral bertarung kawan.", 55, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(50, "Blight-Crusher Carapace", 345, "Tier: Rare | Zirah pelindung dari cangkang kumbang raksasa pemakan bangkai.", 62, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(51, "Aegis Guard Plate", 320, "Tier: Rare | Pelat pelindung kokoh yang tertanam berlian pelindung energi mikro.", 58, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(52, "Nirlelah Adamantite Armor", 490, "Tier: Epic | Zirah pertahanan adamantit maha tebal pembendung amukan raksasa.", 88, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(53, "Crimson Deflector Plate", 470, "Tier: Epic | Pelat merah magis pembias hantaman panah dan sihir jarak jauh.", 85, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(54, "The Golden Aegis Mail", 720, "Tier: Legendary | Zirah emas keramat yang memancarkan barrier pelindung suci bagi sekutu.", 130, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(55, "Breastplate of the Just", 750, "Tier: Legendary | Pelindung dada lambang keadilan sejati yang meningkatkan ketahanan fisik grup.", 135, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(56, "World Tree Bulwark", 1000, "Tier: Mythic | Armor legendaris dari kayu Pohon Dunia, menyembuhkan penggunanya secara konstan.", 200, ClassType.SUPPORT));

        // Support - set 2
        DUMMY_ARMORS.add(a(57, "Faded Prayer Robe", 25, "Tier: Common | Jubah biarawan pudar tempat merapalkan doa-doa kesembuhan kecil.", 5, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(58, "Novice Shroud", 30, "Tier: Common | Kain penutup sederhana berlapis linen pelindung dari debu.", 6, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(59, "Withered Garment", 25, "Tier: Common | Pakaian usang beraroma tanaman obat kering penolak bala.", 5, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(60, "Copper Chalice Vestment", 75, "Tier: Uncommon | Jubah upacara beraksen tembaga untuk para pembawa air suci.", 14, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(61, "Purified White Robe", 90, "Tier: Uncommon | Jubah putih bersih bebas noda penangkal energi negatif.", 16, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(62, "Silver Bell Shroud", 85, "Tier: Uncommon | Kain pelindung bertabur lonceng perak kecil pembersih jiwa.", 15, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(63, "Sanguine Tear Vestment", 165, "Tier: Rare | Pakaian pendeta dengan kristal merah penambah daya pulih mantra regenerasi.", 30, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(64, "Healing Lotus Robe", 190, "Tier: Rare | Jubah beraroma bunga teratai penyebar hawa ketenangan dan pemulihan luka.", 34, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(65, "Priest's Silver Vestment", 175, "Tier: Rare | Jubah resmi pendeta kuil agung berlapis sutra perak pelindung.", 32, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(66, "Lunarite Arch Robe", 320, "Tier: Epic | Jubah uskup agung dari benang batu bulan penenang amarah roh liar.", 58, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(67, "Purified Genesis Shroud", 345, "Tier: Epic | Kain kafan suci pembasmi segala jenis wabah penyakit dan kutukan.", 62, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(68, "Shroud of Miracles", 500, "Tier: Legendary | Jubah penuh mukjizat yang mampu menahan roh agar tidak lepas dari raga.", 90, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(69, "Garb of the Goddess", 525, "Tier: Legendary | Pakaian berkah dewi pelindung total dari kehancuran mana.", 95, ClassType.SUPPORT));
        DUMMY_ARMORS.add(a(70, "Seraphim's Halo Robe", 775, "Tier: Mythic | Jubah malaikat bersayap cahaya, memberikan kekebalan absolut dari kematian.", 140, ClassType.SUPPORT));
    }

    private static Armor a(int id, String name, int price, String desc, int bonusDefense, ClassType requiredClassType) {
        return new Armor(id, name, price, desc, bonusDefense, 0, requiredClassType);
    }

    public static List<Armor> generateDummyArmors() {
        return new ArrayList<>(DUMMY_ARMORS);
    }

    public static List<Armor> getDummyArmors() {
        return generateDummyArmors();
    }

    public static Armor[] getDummyArmorsArray() {
        return DUMMY_ARMORS.toArray(new Armor[0]);
    }

    public static HashMap<Integer, Item> getDummyArmorsMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (Armor armor : DUMMY_ARMORS) {
            map.put(armor.getIdItem(), armor);
        }
        return map;
    }
}
