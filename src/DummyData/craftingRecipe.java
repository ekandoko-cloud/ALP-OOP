package DummyData;

import java.util.*;
import models.item.Inqredients;

public class craftingRecipe {

    private static final systems.craft.craftingRecipe[] DUMMY_RECIPES = new systems.craft.craftingRecipe[] {
        r("Salad Wortel Segar", consumables.getDummyConsumablesMap().get(1), ing(201, 1), ing(202, 1)),
        r("Dada Ayam Panggang", consumables.getDummyConsumablesMap().get(2), ing(203, 1), ing(204, 1)),
        r("Sup Tomat Merah", consumables.getDummyConsumablesMap().get(3), ing(205, 1), ing(206, 1)),
        r("Oatmeal Pisang", consumables.getDummyConsumablesMap().get(4), ing(207, 1), ing(208, 1)),
        r("Telur Rebus Sempurna", consumables.getDummyConsumablesMap().get(5), ing(209, 2)),
        r("Brokoli Kukus", consumables.getDummyConsumablesMap().get(6), ing(210, 2)),
        r("Steak Ikan Salmon", consumables.getDummyConsumablesMap().get(7), ing(211, 1), ing(212, 1)),
        r("Kacang Almond Panggang", consumables.getDummyConsumablesMap().get(8), ing(213, 2)),
        r("Tumis Bayam Bawang Putih", consumables.getDummyConsumablesMap().get(9), ing(214, 1), ing(206, 1)),
        r("Ubi Jalar Rebus", consumables.getDummyConsumablesMap().get(10), ing(215, 2)),
        r("Yogurt Buah Beri", consumables.getDummyConsumablesMap().get(11), ing(216, 1), ing(217, 1), ing(218, 1)),
        r("Lalapan Wortel Renyah", consumables.getDummyConsumablesMap().get(12), ing(201, 1), ing(202, 1)),
        r("Tumis Ayam Lada Hitam Sehat", consumables.getDummyConsumablesMap().get(13), ing(203, 1), ing(204, 1)),
        r("Saus Tomat Alami Murni", consumables.getDummyConsumablesMap().get(14), ing(205, 1), ing(206, 1)),
        r("Bubur Gandum Pisang Serat", consumables.getDummyConsumablesMap().get(15), ing(207, 1), ing(208, 1)),
        r("Telur Setengah Matang Bernutrisi", consumables.getDummyConsumablesMap().get(16), ing(209, 2)),
        r("Cah Brokoli Bening", consumables.getDummyConsumablesMap().get(17), ing(210, 2)),
        r("Salmon Panggang Air Lemon", consumables.getDummyConsumablesMap().get(18), ing(211, 1), ing(212, 1)),
        r("Susu Almond Murni Tanpa Gula", consumables.getDummyConsumablesMap().get(19), ing(213, 2)),
        r("Sayur Bening Bayam Bawang", consumables.getDummyConsumablesMap().get(20), ing(214, 1), ing(206, 1)),
        r("Ubi Cilembu Panggang Alami", consumables.getDummyConsumablesMap().get(21), ing(215, 2)),
        r("Smoothie Beri Probiotik", consumables.getDummyConsumablesMap().get(22), ing(216, 1), ing(217, 1), ing(218, 1)),
        r("Wrap Sayur Wortel Segar", consumables.getDummyConsumablesMap().get(23), ing(201, 1), ing(202, 1)),
        r("Dada Ayam Suwir Pedas Hangat", consumables.getDummyConsumablesMap().get(24), ing(203, 1), ing(204, 1)),
        r("Sup Krim Tomat Bawang", consumables.getDummyConsumablesMap().get(25), ing(205, 1), ing(206, 1)),
        r("Bar Energi Oat Pisang", consumables.getDummyConsumablesMap().get(26), ing(207, 1), ing(208, 1)),
        r("Telur Dadar Kukus Sehat", consumables.getDummyConsumablesMap().get(27), ing(209, 2)),
        r("Sup Brokoli Halus", consumables.getDummyConsumablesMap().get(28), ing(210, 2)),
        r("Fillet Salmon Saus Lemon", consumables.getDummyConsumablesMap().get(29), ing(211, 1), ing(212, 1)),
        r("Granola Kacang Almond", consumables.getDummyConsumablesMap().get(30), ing(213, 2)),
        r("Salad Bayam Siram Bawang", consumables.getDummyConsumablesMap().get(31), ing(214, 1), ing(206, 1)),
        r("Puree Ubi Jalar Lembut", consumables.getDummyConsumablesMap().get(32), ing(215, 2)),
        r("Parfait Yogurt Buah Beri", consumables.getDummyConsumablesMap().get(33), ing(216, 1), ing(217, 1), ing(218, 1)),
        r("Gado-Gado Wortel Mentah", consumables.getDummyConsumablesMap().get(34), ing(201, 1), ing(202, 1)),
        r("Steak Dada Ayam Lada Hitam", consumables.getDummyConsumablesMap().get(35), ing(203, 1), ing(204, 1)),

        r("Kentang Goreng Berbumbu", consumables.getDummyConsumablesMap().get(36), ing(219, 2), ing(220, 1), ing(221, 1)),
        r("Burger Berlapis Keju Cair", consumables.getDummyConsumablesMap().get(37), ing(222, 1), ing(223, 1), ing(224, 1)),
        r("Sosis Goreng MSG", consumables.getDummyConsumablesMap().get(38), ing(225, 1), ing(226, 1), ing(220, 1)),
        r("Permen Gula Kapas", consumables.getDummyConsumablesMap().get(39), ing(227, 2), ing(228, 1)),
        r("Donat Gula Kental", consumables.getDummyConsumablesMap().get(40), ing(229, 2), ing(227, 1), ing(230, 1)),
        r("Mie Instan Kuah Asin", consumables.getDummyConsumablesMap().get(41), ing(229, 2), ing(232, 1), ing(226, 1), ing(221, 1)),
        r("Ayam Goreng Tepung Ekstra", consumables.getDummyConsumablesMap().get(42), ing(203, 1), ing(229, 1), ing(220, 1)),
        r("Camilan Keripik Asin", consumables.getDummyConsumablesMap().get(43), ing(219, 2), ing(221, 1), ing(232, 1)),
        r("Potato Wedges Deep-Fry", consumables.getDummyConsumablesMap().get(44), ing(219, 2), ing(220, 1), ing(221, 1)),
        r("Double Cheeseburger Junk Food", consumables.getDummyConsumablesMap().get(45), ing(222, 1), ing(223, 1), ing(224, 1)),
        r("Sosis Bakar Gurih Murahan", consumables.getDummyConsumablesMap().get(46), ing(225, 1), ing(226, 1), ing(220, 1)),
        r("Permen Gulali Merah Muda", consumables.getDummyConsumablesMap().get(47), ing(227, 2), ing(228, 1)),
        r("Donat Glaze Gula Pekat", consumables.getDummyConsumablesMap().get(48), ing(229, 2), ing(227, 1), ing(230, 1)),
        r("Mie Instan Kuah Pedas Natrium", consumables.getDummyConsumablesMap().get(49), ing(229, 2), ing(232, 1), ing(226, 1), ing(221, 1)),
        r("Ayam Goreng Krispi Kulit Tebal", consumables.getDummyConsumablesMap().get(50), ing(203, 1), ing(229, 1), ing(220, 1)),
        r("Keripik Kentang Kemasan Asin", consumables.getDummyConsumablesMap().get(51), ing(219, 2), ing(221, 1), ing(232, 1)),
        r("Keripik Kentang Balado Minyak", consumables.getDummyConsumablesMap().get(52), ing(219, 2), ing(220, 1), ing(221, 1)),
        r("Burger Jumbo Keju Meleleh", consumables.getDummyConsumablesMap().get(53), ing(222, 1), ing(223, 1), ing(224, 1)),
        r("Sate Sosis Saus Mayones Berlemak", consumables.getDummyConsumablesMap().get(54), ing(225, 1), ing(226, 1), ing(220, 1)),
        r("Permen Jeli Warna-Warni", consumables.getDummyConsumablesMap().get(55), ing(227, 2), ing(228, 1)),
        r("Donat Tabur Gula Bubuk", consumables.getDummyConsumablesMap().get(56), ing(229, 2), ing(227, 1), ing(230, 1)),
        r("Mie Instan Goreng Bumbu Pekat", consumables.getDummyConsumablesMap().get(57), ing(229, 2), ing(232, 1), ing(226, 1), ing(221, 1)),
        r("Ayam Pop Goreng Minyak Jenuh", consumables.getDummyConsumablesMap().get(58), ing(203, 1), ing(229, 1), ing(220, 1)),
        r("Stik Kentang Asin Kemasan", consumables.getDummyConsumablesMap().get(59), ing(219, 2), ing(221, 1), ing(232, 1)),
        r("Kentang Goreng Krispi Saus Keju", consumables.getDummyConsumablesMap().get(60), ing(219, 2), ing(220, 1), ing(221, 1), ing(224, 1)),
        r("Triple Patty Burger Berminyak", consumables.getDummyConsumablesMap().get(61), ing(222, 1), ing(223, 1), ing(224, 1)),
        r("Sosis Goreng Tepung (Corn Dog)", consumables.getDummyConsumablesMap().get(62), ing(225, 1), ing(229, 1), ing(220, 1), ing(226, 1)),
        r("Kembang Gula Pelangi", consumables.getDummyConsumablesMap().get(63), ing(227, 2), ing(228, 1)),
        r("Donat Cokelat Meses Klasik", consumables.getDummyConsumablesMap().get(64), ing(229, 2), ing(227, 1), ing(230, 1)),
        r("Mie Instan Cup Praktis", consumables.getDummyConsumablesMap().get(65), ing(229, 2), ing(232, 1), ing(226, 1), ing(221, 1)),
        r("Sayap Ayam Goreng Tepung Madu", consumables.getDummyConsumablesMap().get(66), ing(203, 1), ing(229, 1), ing(220, 1), ing(238, 1)),
        r("Keripik Kentang Panggang Asin Ekstra", consumables.getDummyConsumablesMap().get(67), ing(219, 2), ing(221, 1), ing(232, 1)),
        r("Kentang Goreng Ulir Mentega", consumables.getDummyConsumablesMap().get(68), ing(219, 2), ing(220, 1), ing(230, 1), ing(221, 1)),
        r("Slidder Burger Keju Asin", consumables.getDummyConsumablesMap().get(69), ing(222, 1), ing(223, 1), ing(224, 1)),
        r("Sosis Gulung Mie Goreng", consumables.getDummyConsumablesMap().get(70), ing(225, 1), ing(229, 1), ing(226, 1), ing(220, 1)),

        r("Jus Wortel Murni", consumables.getDummyConsumablesMap().get(71), ing(201, 1), ing(240, 1)),
        r("Air Kelapa Segar", consumables.getDummyConsumablesMap().get(72), ing(233, 1)),
        r("Teh Hijau Hangat", consumables.getDummyConsumablesMap().get(73), ing(234, 1), ing(240, 1)),
        r("Jus Jeruk Peras", consumables.getDummyConsumablesMap().get(74), ing(235, 1)),
        r("Susu Sapi Murni", consumables.getDummyConsumablesMap().get(75), ing(236, 1)),
        r("Seduhan Jahe Madu", consumables.getDummyConsumablesMap().get(76), ing(237, 1), ing(238, 1), ing(240, 1)),
        r("Jus Apel Merah", consumables.getDummyConsumablesMap().get(77), ing(239, 1)),
        r("Air Mineral Pegunungan", consumables.getDummyConsumablesMap().get(78), ing(240, 1)),
        r("Sari Wortel Dingin Murni", consumables.getDummyConsumablesMap().get(79), ing(201, 1), ing(240, 1)),
        r("Air Kelapa Hijau Alami", consumables.getDummyConsumablesMap().get(80), ing(233, 1)),
        r("Seduhan Matcha Tanpa Gula", consumables.getDummyConsumablesMap().get(81), ing(234, 1), ing(240, 1)),
        r("Jus Jeruk Peras Alami Murni", consumables.getDummyConsumablesMap().get(82), ing(235, 1)),
        r("Susu Sapi Pasteurisasi Murni", consumables.getDummyConsumablesMap().get(83), ing(236, 1)),
        r("Wedang Jahe Madu Hangat", consumables.getDummyConsumablesMap().get(84), ing(237, 1), ing(238, 1), ing(240, 1)),
        r("Sari Apel Merah Peras", consumables.getDummyConsumablesMap().get(85), ing(239, 1)),
        r("Air Alkali Pegunungan Alami", consumables.getDummyConsumablesMap().get(86), ing(240, 1)),
        r("Smoothie Wortel Madu Sehat", consumables.getDummyConsumablesMap().get(87), ing(201, 1), ing(238, 1), ing(240, 1)),
        r("Es Air Kelapa Muda Murni", consumables.getDummyConsumablesMap().get(88), ing(233, 1)),
        r("Teh Hijau Seduh Daun Utuh", consumables.getDummyConsumablesMap().get(89), ing(234, 1), ing(240, 1)),
        r("Sari Jeruk Nipis Hangat", consumables.getDummyConsumablesMap().get(90), ing(235, 1), ing(240, 1)),
        r("Susu Sapi UHT Plain", consumables.getDummyConsumablesMap().get(91), ing(236, 1)),
        r("Teh Jahe Madu Rempah", consumables.getDummyConsumablesMap().get(92), ing(237, 1), ing(238, 1), ing(240, 1)),
        r("Cold-Pressed Jus Apel", consumables.getDummyConsumablesMap().get(93), ing(239, 1)),
        r("Air Mineral Kemasan Steril", consumables.getDummyConsumablesMap().get(94), ing(240, 1)),
        r("Nektar Wortel Segar", consumables.getDummyConsumablesMap().get(95), ing(201, 1), ing(240, 1)),
        r("Air Kelapa Pemulih Ion", consumables.getDummyConsumablesMap().get(96), ing(233, 1)),
        r("Infusi Teh Hijau Melati", consumables.getDummyConsumablesMap().get(97), ing(234, 1), ing(240, 1)),

        r("Minuman Bersoda Manis", consumables.getDummyConsumablesMap().get(106), ing(241, 1), ing(242, 1), ing(228, 1)),
        r("Minuman Berenergi Sintetis", consumables.getDummyConsumablesMap().get(107), ing(243, 1), ing(244, 1), ing(245, 1)),
        r("Es Kopi Gula Aren Ganda", consumables.getDummyConsumablesMap().get(108), ing(246, 1), ing(247, 1), ing(248, 1)),
        r("Sirup Merah Buatan", consumables.getDummyConsumablesMap().get(109), ing(240, 1), ing(249, 1), ing(245, 1)),
        r("Jus Kotak Kemasan Manis", consumables.getDummyConsumablesMap().get(110), ing(250, 1), ing(227, 1)),
        r("Minuman Boba Ekstra Manis", consumables.getDummyConsumablesMap().get(111), ing(251, 1), ing(252, 1), ing(253, 1)),
        r("Alkohol Sulingan Kasar", consumables.getDummyConsumablesMap().get(112), ing(216, 1), ing(207, 1)),
        r("Cola Bersoda Hitam Pekat", consumables.getDummyConsumablesMap().get(113), ing(241, 1), ing(242, 1), ing(249, 1)),
        r("Minuman Stamina Berenergi Tinggi", consumables.getDummyConsumablesMap().get(114), ing(243, 1), ing(244, 1), ing(245, 1)),
        r("Kopi Susu Gula Aren Kekinian", consumables.getDummyConsumablesMap().get(115), ing(246, 1), ing(247, 1), ing(248, 1)),
        r("Sirup Rasa Stroberi Buatan", consumables.getDummyConsumablesMap().get(116), ing(240, 1), ing(249, 1), ing(245, 1)),
        r("Jus Buah Kemasan Kotak Manis", consumables.getDummyConsumablesMap().get(117), ing(250, 1), ing(227, 1)),
        r("Bubble Tea Boba Gula Aren", consumables.getDummyConsumablesMap().get(118), ing(251, 1), ing(252, 1), ing(253, 1)),
        r("Alkohol Oplosan Kadar Tinggi", consumables.getDummyConsumablesMap().get(119), ing(216, 1), ing(207, 1)),
        r("Soda Rasa Buah Sintetis", consumables.getDummyConsumablesMap().get(120), ing(241, 1), ing(242, 1), ing(228, 1)),
        r("Minuman Booster Energi Instan", consumables.getDummyConsumablesMap().get(121), ing(243, 1), ing(244, 1), ing(245, 1)),
        r("Es Kopi Krimer Gula Ekstra", consumables.getDummyConsumablesMap().get(122), ing(246, 1), ing(247, 1), ing(248, 1)),
        r("Sirup Merah Delima Buatan", consumables.getDummyConsumablesMap().get(123), ing(240, 1), ing(249, 1), ing(245, 1)),
        r("Minuman Rasa Buah Kemasan", consumables.getDummyConsumablesMap().get(124), ing(250, 1), ing(227, 1)),
        r("Minuman Boba Susu Karamel", consumables.getDummyConsumablesMap().get(125), ing(251, 1), ing(252, 1), ing(253, 1)),
        r("Arak Sulingan Tradisional Kasar", consumables.getDummyConsumablesMap().get(126), ing(216, 1), ing(207, 1)),
        r("Soda Bening Karbonasi Manis", consumables.getDummyConsumablesMap().get(127), ing(241, 1), ing(242, 1), ing(228, 1)),
        r("Minuman Kaleng Berkafein Tinggi", consumables.getDummyConsumablesMap().get(128), ing(243, 1), ing(244, 1), ing(245, 1)),
        r("Kopi Instan 3-in-1 Super Manis", consumables.getDummyConsumablesMap().get(129), ing(246, 1), ing(247, 1), ing(248, 1)),
        r("Minuman Sirup Es Lilin Pewarna", consumables.getDummyConsumablesMap().get(130), ing(240, 1), ing(249, 1), ing(245, 1)),
        r("Nektar Buah Kemasan Manis", consumables.getDummyConsumablesMap().get(131), ing(250, 1), ing(227, 1)),
        r("Boba Milk Tea Velvet", consumables.getDummyConsumablesMap().get(132), ing(251, 1), ing(252, 1), ing(253, 1)),
        r("Minuman Keras Sulingan Ilegal", consumables.getDummyConsumablesMap().get(133), ing(216, 1), ing(207, 1)),
        r("Air Soda Beraroma Manis", consumables.getDummyConsumablesMap().get(134), ing(241, 1), ing(242, 1), ing(228, 1)),
        r("Shot Energi Sintetis Ekstrem", consumables.getDummyConsumablesMap().get(135), ing(243, 1), ing(244, 1), ing(245, 1)),
        r("Kopi Latte Instan Manis Pekat", consumables.getDummyConsumablesMap().get(136), ing(246, 1), ing(247, 1), ing(248, 1)),
        r("Sirup Koktail Buah Buatan", consumables.getDummyConsumablesMap().get(137), ing(240, 1), ing(249, 1), ing(245, 1)),
        r("Jus Jeruk Kotak Pengawet", consumables.getDummyConsumablesMap().get(138), ing(250, 1), ing(227, 1)),
        r("Es Boba Matcha Manis Pekat", consumables.getDummyConsumablesMap().get(139), ing(251, 1), ing(252, 1), ing(253, 1)),
        r("Alkohol Fermentasi Murahan", consumables.getDummyConsumablesMap().get(140), ing(216, 1), ing(207, 1)),
    };

    private static systems.craft.craftingRecipe r(String name, models.item.Item result, systems.craft.craftingRecipe.IngredientReq... reqs) {
        return new systems.craft.craftingRecipe(name, result, new ArrayList<>(Arrays.asList(reqs)));
    }

    private static systems.craft.craftingRecipe.IngredientReq ing(int ingredientId, int qty) {
        Inqredients ingredient = ingredientId <= 100
                ? (Inqredients) inqredients_alam.getDummyInqredientsAlamMap().get(ingredientId)
                : ingredientId <= 200
                ? (Inqredients) inqredients_monster.getDummyInqredientsMonsterMap().get(ingredientId)
                : (Inqredients) inqredients_consumables.getDummyInqredientsConsumablesMap().get(ingredientId);
        return new systems.craft.craftingRecipe.IngredientReq(ingredient, qty);
    }

    private static final HashMap<Integer, systems.craft.craftingRecipe> RECIPE_MAP = initializeMap();

    private static HashMap<Integer, systems.craft.craftingRecipe> initializeMap() {
        HashMap<Integer, systems.craft.craftingRecipe> map = new HashMap<>();
        for (int i = 0; i < DUMMY_RECIPES.length; i++) {
            map.put(DUMMY_RECIPES[i].getResultItem().getIdItem(), DUMMY_RECIPES[i]);
        }
        return map;
    }

    public static systems.craft.craftingRecipe[] getDummyRecipesArray() {
        return DUMMY_RECIPES;
    }

    public static HashMap<Integer, systems.craft.craftingRecipe> getDummyRecipesMap() {
        return RECIPE_MAP;
    }

}
