package DummyData;

import enums.ClassType;
import models.item.Item;
import models.item.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class weapon {

    private static final ArrayList<Weapon> DUMMY_WEAPONS = new ArrayList<>();

    static {
        // Warrior
        DUMMY_WEAPONS.add(w(1, "Rusted Iron Sword", 60, "Tier: Common | Pedang tua berkarat yang sudah tumpul dimakan waktu.", 15, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(2, "Blunt Broadsword", 72, "Tier: Common | Pedang berat dengan mata pisau yang tidak lagi tajam.", 18, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(3, "Chipped Wood Axe", 64, "Tier: Common | Kapak kayu yang sedikit retak, biasanya digunakan penebang pohon.", 16, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(4, "Copper Cleaver", 120, "Tier: Uncommon | Golok tembaga tebal yang biasa dipakai oleh para jagal.", 30, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(5, "Purified Iron Blade", 140, "Tier: Uncommon | Pedang besi yang telah diberkati dengan ramuan suci.", 35, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(6, "Bandit's Greatsword", 152, "Tier: Uncommon | Pedang besar rampasan dari pemimpin bandit gunung.", 38, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(7, "Silver Edge", 220, "Tier: Rare | Bilah perak berkilau yang mampu membelah kegelapan.", 55, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(8, "Noxious Battleaxe", 260, "Tier: Rare | Kapak tempur yang dilapisi racun mematikan dari serigala mutasi.", 65, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(9, "Commander's Vanguard Sword", 240, "Tier: Rare | Pedang milik komandan pasukan garda depan yang berwibawa.", 60, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(10, "Obsidian Razor", 380, "Tier: Epic | Senjata tajam dari batuan obsidian purba, memancarkan aura dingin.", 95, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(11, "Blight-Bane Claymore", 420, "Tier: Epic | Pedang raksasa penghancur wabah yang ditempa dari titanium murni.", 105, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(12, "The Genesis Blade", 600, "Tier: Legendary | Pedang suci yang tercipta bersamaan dengan awal mula dunia.", 150, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(13, "Warlord's Crimson Axe", 640, "Tier: Legendary | Kapak merah darah milik sang panglima perang legendaris.", 160, ClassType.WARRIOR));
        DUMMY_WEAPONS.add(w(14, "Excalibur of the Dawn", 1000, "Tier: Mythic | Pedang mitologi legendaris yang membawa fajar kemenangan.", 250, ClassType.WARRIOR));

        // Archer
        DUMMY_WEAPONS.add(w(15, "Frayed Wooden Bow", 48, "Tier: Common | Busur kayu lapuk dengan tali pengikat yang mulai berserabut.", 12, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(16, "Scavenger's Slingshot", 40, "Tier: Common | Ketapel sederhana milik pemulung untuk berburu hewan kecil.", 10, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(17, "Simple Shortbow", 56, "Tier: Common | Busur pendek standar yang mudah digunakan oleh pemula.", 14, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(18, "Iron-tipped Hunter Bow", 112, "Tier: Uncommon | Busur berburu dengan ujung besi untuk menembus kulit binatang.", 28, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(19, "Tainted Crossbow", 140, "Tier: Uncommon | Busur silang yang telah terkontaminasi oleh getah beracun.", 35, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(20, "Plagued Wood Recurve", 128, "Tier: Uncommon | Busur recurve dari kayu hutan terlarang yang terkutuk.", 32, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(21, "Silver String Bow", 208, "Tier: Rare | Busur indah bertali perak yang menghasilkan tembakan senyap.", 52, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(22, "Venomous Piercer", 232, "Tier: Rare | Busur panah penembus jitu yang diresapi taring tikus beracun.", 58, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(23, "Black Owl Heavy Crossbow", 248, "Tier: Rare | Busur silang berat yang biasa digunakan untuk berburu dimalam hari.", 62, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(24, "Lunarite Longbow", 360, "Tier: Epic | Busur panjang bercahaya bulan, ditempa menggunakan batu meteor.", 90, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(25, "Crimson Stalker Arbalest", 400, "Tier: Epic | Busur silang raksasa penghisap darah mangsa dari kegelapan.", 100, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(26, "Bow of the Purified Forest", 580, "Tier: Legendary | Busur magis pembawa berkah kedamaian dari jantung hutan suci.", 145, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(27, "Toxic Chimera String", 620, "Tier: Legendary | Busur mematikan dengan tali penarik dari jaring laba-laba mistis.", 155, ClassType.ARCHER));
        DUMMY_WEAPONS.add(w(28, "Artemis' Starfall", 960, "Tier: Mythic | Busur dewi perburuan yang mampu menjatuhkan bintang dari langit.", 240, ClassType.ARCHER));

        // Mage
        DUMMY_WEAPONS.add(w(29, "Crooked Branch Staff", 40, "Tier: Common | Tongkat sihir dari ranting pohon bengkok, menyalurkan mana kecil.", 10, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(30, "Cracked Orb", 48, "Tier: Common | Bola kristal retak yang masih menyimpan sisa-sisa energi magis.", 12, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(31, "Novice Wand", 44, "Tier: Common | Tongkat kecil berlapis kuarsa, cocok untuk penyihir magang.", 11, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(32, "Copper-Trimmed Grimoire", 100, "Tier: Uncommon | Buku mantra bersampul kulit dengan ukiran tembaga tipis.", 25, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(33, "Blighted Wood Staff", 120, "Tier: Uncommon | Tongkat kayu berjamur yang meradiasikan sihir kegelapan.", 30, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(34, "Toad Eye Wand", 112, "Tier: Uncommon | Tongkat sihir aneh dengan permata berbentuk mata katak rawa.", 28, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(35, "Silver-Inlaid Wand", 200, "Tier: Rare | Tongkat elegan bertatahkan perak dan kecubung pelindung mana.", 50, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(36, "Noxious Slime Orb", 220, "Tier: Rare | Orb magis berlendir yang memancarkan gas beracun korosif.", 55, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(37, "Crimson Cultist Tome", 232, "Tier: Rare | Kitab kuno milik sekte sesat, berisi mantra pemanggil darah.", 58, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(38, "Obsidian Core Staff", 340, "Tier: Epic | Tongkat sihir berat berinti batu obsidian pemadat elemen api.", 85, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(39, "Parasitic Witch Staff", 360, "Tier: Epic | Tongkat kayu hitam yang dapat menyerap mana musuh secara parasit.", 90, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(40, "Staff of the Elements", 540, "Tier: Legendary | Tongkat agung penyatu empat elemen dasar alam semesta.", 135, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(41, "Hexing Skull of Dr. Mortis", 560, "Tier: Legendary | Tengkorak terkutuk wadah ilmu hitam dan kutukan mematikan.", 140, ClassType.MAGE));
        DUMMY_WEAPONS.add(w(42, "Eye of the Cosmos", 880, "Tier: Mythic | Artefak berbentuk mata yang menyimpan rahasia kehancuran galaksi.", 220, ClassType.MAGE));

        // Support weapons - set 1
        DUMMY_WEAPONS.add(w(43, "Wooden Mallet", 32, "Tier: Common | Palu kayu berukuran sedang, biasa dipakai untuk menempa tenda.", 8, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(44, "Rusted Buckler", 20, "Tier: Common | Perisai besi kecil berkarat, perlindungan minimal.", 5, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(45, "Novice Hammer", 36, "Tier: Common | Palu gada standar penegak keadilan bagi para pemula.", 9, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(46, "Iron Mace", 80, "Tier: Uncommon | Gada besi berduri yang cukup tangguh untuk meremukkan zirah.", 20, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(47, "Guard's Tower Shield", 60, "Tier: Uncommon | Perisai menara kokoh standar pertahanan penjaga gerbang kota.", 15, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(48, "Purified Iron Hammer", 88, "Tier: Uncommon | Palu besi yang disucikan untuk menghalau energi negatif.", 22, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(49, "Silver Vanguard Shield", 140, "Tier: Rare | Perisai perak mengkilap, memantulkan berkah perlindungan suci.", 35, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(50, "Blight-Crusher Mace", 180, "Tier: Rare | Gada penghancur monster wabah berbahan cangkang kumbang purba.", 45, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(51, "Aegis Guard Hammer", 160, "Tier: Rare | Palu pelindung berenergi tinggi bertatahkan berlian aegis.", 40, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(52, "Nirlelah Adamantite Bulwark", 260, "Tier: Epic | Dinding zirah adamantit raksasa penahan serangan naga.", 65, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(53, "Crimson Deflector", 240, "Tier: Epic | Perisai merah pemantul serangan sihir jahat.", 60, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(54, "The Golden Aegis", 380, "Tier: Legendary | Perisai emas legendaris, konon tak bisa ditembus senjata apapun.", 95, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(55, "Hammer of the Just", 440, "Tier: Legendary | Palu penghakiman suci, mengalirkan berkah penyembuhan bagi kawan.", 110, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(56, "Shield of the World Tree", 600, "Tier: Mythic | Perisai suci yang dipahat langsung dari akar Pohon Kehidupan.", 150, ClassType.SUPPORT));

        // Support weapons - set 2
        DUMMY_WEAPONS.add(w(57, "Faded Prayer Beads", 24, "Tier: Common | Tasbih doa usang untuk merapalkan mantra penyembuh minor.", 6, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(58, "Wooden Cross", 20, "Tier: Common | Salib kayu sederhana lambang keteguhan iman penjelajah.", 5, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(59, "Withered Relic", 28, "Tier: Common | Artefak kuno layu yang masih menyimpan sedikit berkah ilahi.", 7, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(60, "Copper Chalice", 60, "Tier: Uncommon | Cawan tembaga wadah air suci untuk membasuh luka.", 15, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(61, "Purified Chime", 72, "Tier: Uncommon | Lonceng pembersih aura negatif di sekitar area pertempuran.", 18, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(62, "Silver Bell", 64, "Tier: Uncommon | Lonceng perak penghasil nada penenang jiwa yang terluka.", 16, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(63, "Sanguine Tear Pendant", 120, "Tier: Rare | Kalung air mata darah penambah efektivitas mantra regenerasi.", 30, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(64, "Healing Lotus Censer", 128, "Tier: Rare | Wadah dupa teratai penyebar aroma terapi penyembuh massal.", 32, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(65, "Priest's Silver Codex", 140, "Tier: Rare | Buku panduan pendeta perak berisi tata cara doa pemulihan.", 35, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(66, "Lunarite Scepter", 220, "Tier: Epic | Tongkat keagungan penenang badai dengan kekuatan batuan bulan.", 55, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(67, "Purified Genesis Relic", 240, "Tier: Epic | Relik pemulihan tingkat tinggi penghapus segala jenis kutukan.", 60, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(68, "Staff of Miracles", 340, "Tier: Legendary | Tongkat mukjizat pemberi berkah kebangkitan bagi yang sekarat.", 85, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(69, "Tear of the Goddess", 360, "Tier: Legendary | Kristal air mata dewi pemulih seluruh mana dalam sekejap.", 90, ClassType.SUPPORT));
        DUMMY_WEAPONS.add(w(70, "Halo of the Seraphim", 520, "Tier: Mythic | Lingkaran cahaya malaikat pemberi perlindungan abadi dari kematian.", 130, ClassType.SUPPORT));
    }

    private static Weapon w(int id, String name, int price, String desc, int bonusKekuatan, ClassType requiredClassType) {
        return new Weapon(id, name, price, desc, bonusKekuatan, 0, requiredClassType);
    }

    public static List<Weapon> getDummyWeapons() {
        return new ArrayList<>(DUMMY_WEAPONS);
    }

    public static Weapon[] getDummyWeaponsArray() {
        return DUMMY_WEAPONS.toArray(new Weapon[0]);
    }

    public static HashMap<Integer, Item> getDummyWeaponsMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (Weapon weapon : DUMMY_WEAPONS) {
            map.put(weapon.getIdItem(), weapon);
        }
        return map;
    }
}
