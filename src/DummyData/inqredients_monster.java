package DummyData;

import enums.ItemType;
import models.item.Ingredients;
import models.item.Item;

import java.util.*;

public class inqredients_monster {

    private static final Ingredients[] DUMMY_INQREDIENTS_MONSTER = new Ingredients[]{
            i(101, "Starving Zombie Flesh", 410, "Potongan daging busuk dari zombie kelaparan."),
            i(102, "Starving Scavenger Rags", 414, "Kain kotor dari pemulung yang kelaparan."),
            i(103, "Decayed Wolf Fang", 418, "Taring serigala yang sudah membusuk."),
            i(104, "Hollow Toad Wart", 422, "Kutil dari katak berongga penyebar miasma."),
            i(105, "Crimson Mercenary Badge", 426, "Lencana tentara bayaran yang terkena wabah merah."),
            i(106, "Noxious Bat Wing", 430, "Sayap kelelawar beracun."),
            i(107, "Venomous Creeper Vine", 434, "Akar rambat berduri dengan racun mematikan."),
            i(108, "Vile Scavenger Pouch", 438, "Kantong curian dari pemulung keji."),
            i(109, "Skeletal Scavenger Bone", 442, "Tulang kering dari pemulung tengkorak."),
            i(110, "Parasitic Bat Ear", 446, "Telinga kelelawar pembawa parasit."),
            i(111, "Corrupt Vulture Beak", 450, "Paruh tajam dari burung bangkai yang korup."),
            i(112, "Infected Creeper Sap", 454, "Getah terinfeksi dari tanaman merambat."),
            i(113, "Noxious Slime Goo", 458, "Lendir beracun pewaris The Great Blight."),
            i(114, "Infected Rat Tail", 462, "Ekor tikus pembawa penyakit."),
            i(115, "Mutated Bandit Mask", 466, "Topeng dari bandit yang bermutasi."),
            i(116, "Skeletal Beetle Shell", 470, "Cangkang rapuh seperti tulang dari kumbang."),
            i(117, "Skeletal Rat Skull", 474, "Tengkorak kecil tikus."),
            i(118, "Parasitic Root Fiber", 478, "Serat dari akar parasit penyerap kehidupan."),
            i(119, "Vile Ghoul Nail", 482, "Kuku panjang dari ghoul keji."),
            i(120, "Infected Zombie Eye", 486, "Mata terinfeksi dari zombie."),
            i(121, "Venomous Mercenary Blade", 490, "Patahan pedang beracun."),
            i(122, "Crimson Creeper Leaf", 494, "Daun merah pembawa Crimson Plague."),
            i(123, "Infected Toad Tongue", 498, "Lidah katak yang penuh bakteri."),
            i(124, "Noxious Rat Claw", 502, "Cakar tikus beracun."),
            i(125, "Starving Thief Lockpick", 506, "Alat pembobol dari pencuri yang mati kelaparan."),
            i(126, "Rabid Scavenger Scrap", 510, "Besi rongsokan dari pemulung gila."),
            i(127, "Crimson Mercenary Armor", 514, "Pecahan armor bernoda darah."),
            i(128, "Noxious Toad Gland", 518, "Kelenjar racun katak."),
            i(129, "Plague Scavenger Sack", 522, "Karung penuh debu wabah."),
            i(130, "Withered Zombie Bone", 526, "Tulang keropos dari zombie layu."),
            i(131, "Venomous Mercenary Toxin", 530, "Botol racun milik tentara bayaran."),
            i(132, "Crimson Wolf Pelt", 534, "Bulu serigala berwarna merah darah."),
            i(133, "Noxious Vulture Feather", 538, "Bulu beracun pembawa penyakit."),
            i(134, "Vile Scavenger Boot", 542, "Sepatu usang pemulung."),
            i(135, "Decayed Root Bark", 546, "Kulit akar yang membusuk."),
            i(136, "Crimson Zombie Blood", 550, "Darah kental dari zombie merah."),
            i(137, "Rabid Scorpion Stinger", 554, "Sengat kalajengking liar."),
            i(138, "Noxious Mercenary Gauntlet", 558, "Sarung tangan besi beracun."),
            i(139, "Noxious Mercenary Helm", 562, "Helm berkarat dengan lumut beracun."),
            i(140, "Venomous Scorpion Carapace", 566, "Cangkang keras kalajengking racun."),
            i(141, "Blighted Beetle Mandible", 570, "Rahang kumbang yang terkena Blight."),
            i(142, "Starving Mercenary Ration", 574, "Sisa jatah makan yang sudah busuk."),
            i(143, "Plague Shroom Cap", 578, "Tudung jamur pembawa wabah."),
            i(144, "Putrid Wasp Wing", 582, "Sayap tawon yang membusuk."),
            i(145, "Toxic Zombie Ash", 586, "Abu dari zombie beracun."),
            i(146, "Noxious Wolf Claw", 590, "Cakar serigala yang terkontaminasi."),
            i(147, "Parasitic Shroom Spore", 594, "Spora parasit untuk melumpuhkan musuh."),
            i(148, "Skeletal Beetle Horn", 598, "Tanduk kumbang tulang."),
            i(149, "Vile Creeper Thorn", 602, "Duri mematikan dari tanaman menjalar."),
            i(150, "Infected Toad Slime", 606, "Lendir katak infeksi."),
            i(151, "Infected Scavenger Rags", 610, "Kain terkoyak berlumur virus."),
            i(152, "Parasitic Scavenger Pouch", 614, "Kantong tempat parasit bersarang."),
            i(153, "Parasitic Zombie Brain", 618, "Otak zombie yang dikendalikan parasit."),
            i(154, "Venomous Rat Incisor", 622, "Gigi seri tikus beracun."),
            i(155, "Infected Hound Collar", 626, "Kerah anjing terinfeksi."),
            i(156, "Plague Rat Tail", 630, "Ekor tikus wabah."),
            i(157, "Plague Hound Fang", 634, "Taring anjing wabah."),
            i(158, "Noxious Hound Saliva", 638, "Air liur anjing beracun."),
            i(159, "Toxic Crow Beak", 642, "Paruh gagak beracun."),
            i(160, "Rabid Scorpion Pincer", 646, "Capit kalajengking buas."),
            i(161, "Blighted Hound Pelt", 650, "Bulu anjing pembawa hama."),
            i(162, "Hollow Shroom Stem", 654, "Batang jamur berongga."),
            i(163, "Withered Ghoul Heart", 658, "Jantung kering dari ghoul."),
            i(164, "Parasitic Bat Guano", 662, "Kotoran kelelawar parasit penyubur tanah."),
            i(165, "Toxic Creeper Root", 666, "Akar tanaman rambat beracun."),
            i(166, "Skeletal Crow Feather", 670, "Bulu gagak tulang."),
            i(167, "Infected Bandit Dagger", 674, "Belati bandit yang berkarat dan terinfeksi."),
            i(168, "Corrupt Toad Eye", 678, "Mata katak korup."),
            i(169, "Crimson Toad Wart", 682, "Kutil merah pembawa Crimson Plague."),
            i(170, "Corrupt Rat Claw", 686, "Cakar tikus korup."),
            i(171, "Diseased Slime Core", 690, "Inti lendir berpenyakit."),
            i(172, "Rabid Bandit Bandana", 694, "Bandana milik bandit gila."),
            i(173, "Plague Mercenary Shield", 698, "Pecahan perisai wabah."),
            i(174, "Plague Ghoul Tooth", 702, "Gigi ghoul wabah."),
            i(175, "Vile Shroom Mycelium", 706, "Jaringan akar jamur keji."),
            i(176, "Crimson Zombie Flesh", 710, "Daging zombie wabah merah."),
            i(177, "Noxious Toad Acid", 714, "Asam pencernaan katak."),
            i(178, "Plague Thief Cloak", 718, "Jubah pencuri pembawa wabah."),
            i(179, "Hollow Thief Dagger", 722, "Belati kosong tak berjiwa."),
            i(180, "Starving Slime Residue", 726, "Sisa lendir kelaparan."),
            i(181, "Diseased Creeper Bulb", 730, "Bonggol tanaman berpenyakit."),
            i(182, "Venomous Hound Fur", 734, "Bulu anjing beracun penambah Kekuatan."),
            i(183, "Toxic Mercenary Boots", 738, "Sepatu tentara bayaran beracun."),
            i(184, "Mutated Wolf Eye", 742, "Mata serigala mutan."),
            i(185, "Mutated Shroom Cap", 746, "Tudung jamur mutasi yang mematikan."),
            i(186, "Vile Ghoul Dust", 750, "Abu sisa tubuh ghoul keji."),
            i(187, "Starving Beetle Leg", 754, "Kaki kumbang rapuh."),
            i(188, "Blighted Zombie Skull", 758, "Tengkorak zombie pembawa hama."),
            i(189, "Vile Zombie Arm", 762, "Lengan zombie yang patah."),
            i(190, "Putrid Shroom Spore", 766, "Spora pembusuk."),
            i(191, "Infected Ghoul Rib", 770, "Tulang rusuk ghoul terinfeksi."),
            i(192, "Hollow Ghoul Tear", 774, "Air mata ghoul tanpa jiwa."),
            i(193, "Skeletal Toad Bone", 778, "Tulang katak langka."),
            i(194, "Starving Toad Skin", 782, "Kulit katak kelaparan."),
            i(195, "Plague Beetle Carapace", 786, "Cangkang kumbang wabah untuk armor."),
            i(196, "Blighted Wolf Fang", 790, "Taring serigala hama penambah Kekuatan fisik."),
            i(197, "Rabid Scorpion Tail", 794, "Ekor kalajengking gila."),
            i(198, "Parasitic Wolf Pelt", 798, "Bulu serigala dengan parasit inang."),
            i(199, "Toxic Zombie Brain", 802, "Otak zombie beracun."),
            i(200, "Plague Slime Essence", 806, "Esensi wabah murni dari slime tingkat tinggi."),
            i(201, "Blight-Root Core", 850, "Inti dari akar Blight yang menjadi sumber kontaminasi di Valerion. Berdenyut dengan energi kegelapan purba."),
            i(202, "Miasma Gland", 900, "Kelenjar miasma dari Goliath Toad yang menyaring racun rawa. Bahan utama penawar gas beracun."),
            i(203, "Silo Vault Key", 950, "Kunci gudang silo pangan ilegal milik Baron Gluttony. Diperlukan untuk membuka akses ke persediaan pangan darurat."),
            i(204, "Vaccine Formula", 1000, "Formula vaksin asli hasil penelitian Dr. Mortis. Berisi resep lengkap untuk membuat vaksin penawar wabah."),
            i(205, "Core of Purified Earth", 1200, "Inti bumi murni dari Aldoria yang telah menyaring seluruh radiasi. Simbol harapan untuk memulai kehidupan baru."),
    };

    private static final HashMap<Integer, Item> DUMMY_INQREDIENTS_MONSTER_MAP = initializeMap();

    private static HashMap<Integer, Item> initializeMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (Ingredients ingredient : DUMMY_INQREDIENTS_MONSTER) {
            map.put(ingredient.getIdItem(), ingredient);
        }
        return map;
    }

    private static Ingredients i(int id, String nama, int hargaJual, String deskripsi) {
        return new Ingredients(id, nama, hargaJual, deskripsi, ItemType.INQREDIENT);
    }

    public static List<Ingredients> getDummyIngredientsMonster() {
        return List.of(DUMMY_INQREDIENTS_MONSTER);
    }

    public static HashMap<Integer, Item> getDummyIngredientsMonsterMap() {
        return DUMMY_INQREDIENTS_MONSTER_MAP;
    }
}
