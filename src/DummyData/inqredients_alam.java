package DummyData;

import enums.itemType;
import models.item.Inqredients;
import models.item.Item;

import java.util.*;

public class inqredients_alam {

    private static final Inqredients[] DUMMY_INQREDIENTS_ALAM = new Inqredients[]{
            i(1, "Copper", 10, "Logam dasar kemerahan yang sering ditemukan di permukaan bumi."),
            i(2, "Iron", 14, "Logam keras yang menjadi tulang punggung persenjataan dasar."),
            i(3, "Silver", 18, "Logam berkilau yang memiliki konduktivitas sihir ringan."),
            i(4, "Gold", 22, "Logam mulia yang sangat baik untuk menyalurkan energi sihir."),
            i(5, "Platinum", 26, "Logam langka yang sangat keras dan tahan korosi."),
            i(6, "Titanium", 30, "Logam tangguh yang sangat ringan cocok untuk pergerakan cepat."),
            i(7, "Mithril", 34, "Logam mistis seringan bulu namun sekeras naga."),
            i(8, "Adamantite", 38, "Logam terkeras di dunia yang mustahil untuk dibengkokkan."),
            i(9, "Orichalcum", 42, "Logam legendaris penyalur murni untuk meningkatkan Kekuatan."),
            i(10, "Obsidian", 46, "Kaca vulkanik hitam pekat dengan ketajaman mematikan."),
            i(11, "Meteorite", 50, "Batu angkasa yang jatuh di Kawah Episentrum."),
            i(12, "Voidstone", 54, "Batu misterius yang menyerap cahaya dan suara di sekitarnya."),
            i(13, "Starfall", 58, "Pecahan bintang dengan energi kosmik yang berdenyut."),
            i(14, "Lunarite", 62, "Mineral pucat yang bersinar lembut di bawah cahaya bulan."),
            i(15, "Solarite", 66, "Mineral hangat yang menyimpan panas matahari siang."),
            i(16, "Abyssal Rock", 70, "Batuan dari dasar lautan terdalam yang menahan tekanan luar biasa."),
            i(17, "Pumice", 74, "Batu vulkanik berongga yang sangat ringan."),
            i(18, "Brimstone", 78, "Batu belerang dengan aroma menyengat yang mudah terbakar."),
            i(19, "Basalt", 82, "Batuan beku gelap yang sangat padat."),
            i(20, "Granite", 86, "Batu keras berbintik yang sering digunakan untuk fondasi."),
            i(21, "Marble", 90, "Batu putih halus yang elegan dan memantulkan sihir."),
            i(22, "Quartz", 94, "Kristal bening serbaguna penyimpan energi dasar."),
            i(23, "Amethyst", 98, "Kristal ungu pelindung dari gangguan pikiran."),
            i(24, "Sapphire", 102, "Kristal biru penyimpan elemen air dan es."),
            i(25, "Ruby", 106, "Kristal merah menyala peningkat vitalitas."),
            i(26, "Emerald", 110, "Kristal hijau zamrud yang beresonansi dengan alam."),
            i(27, "Diamond", 114, "Kristal terkeras yang membiaskan semua spektrum sihir."),
            i(28, "Topaz", 118, "Kristal kuning penyimpan elemen petir."),
            i(29, "Onyx", 122, "Kristal hitam legam pengikat energi negatif."),
            i(30, "Jade", 126, "Batu giok penyembuh yang menenangkan jiwa."),
            i(31, "Withered Herb", 130, "Tanaman herbal yang hampir mati terkena wabah The Great Blight."),
            i(32, "Bitter Herb", 134, "Herbal pahit untuk obat penawar penyakit dasar."),
            i(33, "Sanguine Herb", 138, "Herbal merah beracun dari Rawa Sanguin."),
            i(34, "Healing Leaf", 142, "Daun dengan khasiat penyembuhan luka fisik."),
            i(35, "Mana Petal", 146, "Kelopak bunga pemulih energi spiritual."),
            i(36, "Purified Herb", 150, "Herbal murni tanpa kontaminasi wabah."),
            i(37, "Toxic Spore", 154, "Spora beracun dari tanaman yang bermutasi."),
            i(38, "Crimson Lotus", 158, "Teratai merah pembawa panas tubuh yang ekstrem."),
            i(39, "Blighted Root", 162, "Akar busuk dari Lembah Asing Tandu."),
            i(40, "Genesis Bloom", 166, "Bunga langka dari inti kawah pembawa kehidupan baru."),
            i(41, "Moonflower", 170, "Bunga mekar di malam hari yang memancarkan cahaya perak."),
            i(42, "Sunweed", 174, "Gulma tangguh yang menyerap energi matahari langsung."),
            i(43, "Ash Blossom", 178, "Bunga yang tumbuh di atas abu sisa pembakaran hutan."),
            i(44, "Bloodcap Mushroom", 182, "Jamur berlendir merah yang tumbuh di mayat makhluk hidup."),
            i(45, "Glowshroom", 186, "Jamur bercahaya biru yang menerangi gua gelap."),
            i(46, "Plague Spore", 190, "Spora pekat yang menyebarkan The Crimson Plague."),
            i(47, "Vitality Seed", 194, "Biji kuno penambah stamina dan daya tahan."),
            i(48, "Miracle Sprout", 198, "Tunas ajaib yang tumbuh di tanah paling tandus sekalipun."),
            i(49, "Deathcap", 202, "Jamur hitam beracun yang seketika melumpuhkan sistem saraf."),
            i(50, "Ironweed", 206, "Rumput keras yang seratnya setajam silet."),
            i(51, "Ash Wood", 210, "Kayu rapuh dari pohon yang terbakar habis."),
            i(52, "Sturdy Log", 214, "Gelondongan kayu kokoh dari hutan pedalaman."),
            i(53, "Ironwood", 218, "Kayu langka yang memiliki tingkat kekerasan setara besi."),
            i(54, "Crimson Bark", 222, "Kulit kayu merah dari pohon yang terinfeksi wabah darah."),
            i(55, "Petrified Wood", 226, "Kayu yang telah membatu selama ribuan tahun."),
            i(56, "Genesis Timber", 230, "Kayu suci berkilau pembentuk dunia baru."),
            i(57, "Hollow Branch", 234, "Cabang kayu berongga yang biasa dijadikan alat tiup gaib."),
            i(58, "Willow Weep", 238, "Ranting lentur dari pohon willow yang selalu meneteskan air."),
            i(59, "Ebony Wood", 242, "Kayu hitam pekat yang anti rayap dan sihir gelap."),
            i(60, "Rosewood", 246, "Kayu kemerahan dengan aroma bunga mawar yang kuat."),
            i(61, "Blightwood", 250, "Kayu membusuk yang memancarkan aura Great Blight."),
            i(62, "Spirit Timber", 254, "Kayu transparan tempat roh-roh hutan bersarang."),
            i(63, "Sanguine Sap", 258, "Getah merah pekat yang berbau seperti tembaga."),
            i(64, "Purified Sap", 262, "Getah murni suci peningkat Kekuatan."),
            i(65, "Tainted Water", 266, "Air kotor berwarna kecokelatan penyebar wabah hama."),
            i(66, "Filtered Water", 270, "Air hasil saringan kasar penawar haus sederhana."),
            i(67, "Pure Spring Water", 274, "Air mata air murni penghilang status negatif tubuh."),
            i(68, "Crimson Dew", 278, "Embun merah mematikan pembawa The Crimson Plague."),
            i(69, "Volatile Acid", 282, "Asam alami berbahaya pelebur semua jenis logam."),
            i(70, "Murky Sludge", 286, "Lumpur beracun tebal dari Rawa Sanguin."),
            i(71, "Purified Soil", 290, "Tanah subur gembur yang bebas dari hama dan kutukan."),
            i(72, "Blighted Earth", 294, "Tanah abu-abu tandus pembawa kematian flora."),
            i(73, "Volcanic Ash", 298, "Abu panas sisa letusan gunung api."),
            i(74, "Miasma Vapor", 302, "Gas ungu beracun dari pembusukan massal."),
            i(75, "Aether Gas", 306, "Gas magis tak kasat mata di dataran tinggi."),
            i(76, "Soul Dew", 310, "Embun kristal sisa perpindahan jiwa makhluk."),
            i(77, "Life Extract", 314, "Sari pati kehidupan kental peningkat limit selular."),
            i(78, "Cursed Sludge", 318, "Lumpur hitam yang menarik energi kehidupan sekitarnya."),
            i(79, "Genesis Clay", 322, "Tanah liat purba yang bisa dibentuk menjadi apapun."),
            i(80, "Terran Mud", 326, "Lumpur bumi alami untuk penutup luka darurat."),
            i(81, "Natural Silk", 330, "Sutra alam sangat kuat dari ulat sutra liar."),
            i(82, "Cotton Fabric", 334, "Kain katun dasar yang sejuk dan menyerap keringat."),
            i(83, "Linen Cloth", 338, "Kain rami kasar namun sangat tahan lama."),
            i(84, "Tough Leather", 342, "Kulit hewan buas tebal untuk armor kulit sedang."),
            i(85, "Beast Bone", 346, "Tulang utuh dari predator puncak di alam liar."),
            i(86, "Wild Fang", 350, "Taring panjang melengkung dari hewan buas."),
            i(87, "Feather Plume", 354, "Bulu unggas besar pengatur keseimbangan anak panah."),
            i(88, "Mystic Ashes", 358, "Abu berkilau sisa pembakaran api magis murni."),
            i(89, "Coral Fragment", 362, "Pecahan karang laut dalam yang keras."),
            i(90, "Sea Glass", 366, "Kaca laut yang terkikis ombak menjadi halus."),
            i(91, "Pearl", 370, "Mutiara bulat sempurna penetral racun alami."),
            i(92, "Amber", 374, "Getah pohon fosil dengan serangga kuno di dalamnya."),
            i(93, "Fossilized Shell", 378, "Cangkang kerang zaman purba yang membatu."),
            i(94, "Ivory", 382, "Gading gajah liar berwarna putih gading."),
            i(95, "Beeswax", 386, "Lilin madu alami untuk pelapis kedap air."),
            i(96, "Crystalized Magma", 390, "Magma bersuhu sangat tinggi yang terjebak dalam bentuk padat."),
            i(97, "Frozen Tear", 394, "Bongkahan es abadi sekecil air mata yang tidak bisa mencair."),
            i(98, "Thunderstone", 398, "Batu bersudut tajam penyimpan aliran listrik statis yang besar."),
            i(99, "Wind Chime Stone", 402, "Batu ringan yang beresonansi dan bernyanyi saat tertiup angin."),
            i(100, "Miracle Sprout", 406, "Tunas ajaib yang tumbuh di tanah paling tandus sekalipun."),
            i(101, "Crystal Shard", 120, "Pecahan kristal murni yang memantulkan cahaya. Digunakan sebagai filter alami untuk menjernihkan air dan udara."),
            i(102, "Meteorite Dust", 160, "Debu halus sisa hancurnya meteorit. Partikelnya mengandung mineral langka dari luar angkasa yang sangat reaktif."),
    };

    private static final HashMap<Integer, Item> DUMMY_INQREDIENTS_ALAM_MAP = initializeMap();

    private static HashMap<Integer, Item> initializeMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (Inqredients ingredient : DUMMY_INQREDIENTS_ALAM) {
            map.put(ingredient.getIdItem(), ingredient);
        }
        return map;
    }

    private static Inqredients i(int id, String nama, int hargaJual, String deskripsi) {
        return new Inqredients(id, nama, hargaJual, deskripsi, itemType.INQREDIENT);
    }

    public static List<Inqredients> getDummyInqredientsAlam() {
        return List.of(DUMMY_INQREDIENTS_ALAM);
    }

    public static HashMap<Integer, Item> getDummyInqredientsAlamMap() {
        return DUMMY_INQREDIENTS_ALAM_MAP;
    }
}
