package DummyData;

import java.util.*;
import models.item.Inqredients;

public class craftingRecipe {

    private static final systems.craft.craftingRecipe[] DUMMY_RECIPES = new systems.craft.craftingRecipe[] {
        r("Daging Orc Goreng Lemak", consumables.getDummyConsumablesMap().get(1), ing(30, 2), ing(32, 2)),
        r("Roti Gandum Berjamur", consumables.getDummyConsumablesMap().get(2), ing(19, 2), ing(34, 1)),
        r("Burger Lendir Goblin", consumables.getDummyConsumablesMap().get(3), ing(19, 1), ing(5, 2)),
        r("Sate Daging Tikus", consumables.getDummyConsumablesMap().get(4), ing(14, 3), ing(17, 1)),
        r("Sup Tulang Berkarat", consumables.getDummyConsumablesMap().get(5), ing(9, 2), ing(7, 1)),
        r("Keripik Cangkang Kumbang", consumables.getDummyConsumablesMap().get(6), ing(66, 3), ing(41, 1)),
        r("Tumis Jamur Halusinasi", consumables.getDummyConsumablesMap().get(7), ing(34, 4), ing(55, 1)),
        r("Dendeng Kelelawar Asam", consumables.getDummyConsumablesMap().get(8), ing(47, 2), ing(24, 2)),
        r("Pie Daging Misterius", consumables.getDummyConsumablesMap().get(9), ing(44, 3), ing(16, 1)),
        r("Ransum Darurat Kadaluarsa", consumables.getDummyConsumablesMap().get(10), ing(19, 2), ing(30, 2)),
        r("Gulai Taring Serigala", consumables.getDummyConsumablesMap().get(11), ing(26, 3), ing(2, 1)),
        r("Kue Lumpur Rawa", consumables.getDummyConsumablesMap().get(12), ing(57, 2), ing(16, 2)),
        r("Sosis Darah Vampir", consumables.getDummyConsumablesMap().get(13), ing(89, 1), ing(27, 2)),
        r("Omelet Telur Laba-Laba", consumables.getDummyConsumablesMap().get(14), ing(36, 3), ing(41, 2)),
        r("Sate Kalajengking Berbisa", consumables.getDummyConsumablesMap().get(15), ing(45, 2), ing(3, 1)),
        r("Daging Beruang Gosong", consumables.getDummyConsumablesMap().get(16), ing(52, 2), ing(107, 1)),
        r("Sup Lendir Asam", consumables.getDummyConsumablesMap().get(17), ing(57, 4), ing(2, 1)),
        r("Roti Lapis Mangan", consumables.getDummyConsumablesMap().get(18), ing(19, 2), ing(75, 1)),
        r("Steak Monster Gagal", consumables.getDummyConsumablesMap().get(19), ing(44, 3), ing(152, 2)),
        r("Bakar Cakar Gargoyle", consumables.getDummyConsumablesMap().get(20), ing(82, 2), ing(49, 1)),
        r("Keripik Sisik Naga", consumables.getDummyConsumablesMap().get(21), ing(86, 1), ing(32, 3)),
        r("Kaldu Tengkorak", consumables.getDummyConsumablesMap().get(22), ing(30, 2), ing(2, 2)),
        r("Bubur Padi Besi", consumables.getDummyConsumablesMap().get(23), ing(29, 4), ing(41, 1)),
        r("Manisan Mata Harpy", consumables.getDummyConsumablesMap().get(24), ing(95, 2), ing(83, 2)),
        r("Sate Usus Troll", consumables.getDummyConsumablesMap().get(25), ing(27, 3), ing(90, 1)),
        r("Daging Ogre Cincang Mentah", consumables.getDummyConsumablesMap().get(26), ing(61, 3), ing(20, 1)),
        r("Kue Spora Kematian", consumables.getDummyConsumablesMap().get(27), ing(34, 4), ing(94, 1)),
        r("Gorengan Minyak Hitam", consumables.getDummyConsumablesMap().get(28), ing(19, 2), ing(55, 2)),
        r("Stew Ikan Mutan", consumables.getDummyConsumablesMap().get(29), ing(67, 2), ing(2, 2)),
        r("Pesta Daging Enath", consumables.getDummyConsumablesMap().get(30), ing(44, 5), ing(101, 2)),
        r("Salad Gandum Murni", consumables.getDummyConsumablesMap().get(31), ing(1, 3), ing(59, 1)),
        r("Sup Daun Obat Steril", consumables.getDummyConsumablesMap().get(32), ing(10, 3), ing(59, 2)),
        r("Daging Asap Daun Cemara", consumables.getDummyConsumablesMap().get(33), ing(44, 2), ing(13, 2)),
        r("Bubur Sari Pati", consumables.getDummyConsumablesMap().get(34), ing(63, 3), ing(59, 1)),
        r("Roti Teratai Salju", consumables.getDummyConsumablesMap().get(35), ing(1, 2), ing(156, 1)),
        r("Ikan Panggang Steril", consumables.getDummyConsumablesMap().get(36), ing(67, 2), ing(41, 1)),
        r("Sup Jamur Bercahaya", consumables.getDummyConsumablesMap().get(37), ing(48, 3), ing(59, 2)),
        r("Puding Lidah Buaya", consumables.getDummyConsumablesMap().get(38), ing(43, 3), ing(83, 1)),
        r("Salad Sayur Vallesia", consumables.getDummyConsumablesMap().get(39), ing(156, 4), ing(55, 1)),
        r("Biskuit Energi Black Owl", consumables.getDummyConsumablesMap().get(40), ing(1, 2), ing(97, 1)),
        r("Ransum Komando Commander", consumables.getDummyConsumablesMap().get(41), ing(44, 3), ing(156, 2)),
        r("Sate Madu Hutan", consumables.getDummyConsumablesMap().get(42), ing(44, 2), ing(83, 1)),
        r("Kentang Panggang Api", consumables.getDummyConsumablesMap().get(43), ing(167, 3), ing(107, 1)),
        r("Stew Akar Ginseng", consumables.getDummyConsumablesMap().get(44), ing(167, 1), ing(59, 2)),
        r("Bubur Kaldu Murni", consumables.getDummyConsumablesMap().get(45), ing(9, 2), ing(59, 3)),
        r("Nasi Tim Orichalcum (Kiasan)", consumables.getDummyConsumablesMap().get(46), ing(29, 3), ing(63, 1)),
        r("Kue Kismis Bintang", consumables.getDummyConsumablesMap().get(47), ing(1, 2), ing(78, 1)),
        r("Tumis Teratai Ginseng", consumables.getDummyConsumablesMap().get(48), ing(156, 1), ing(167, 1)),
        r("Daging Bakar Batu Apung", consumables.getDummyConsumablesMap().get(49), ing(44, 2), ing(160, 1)),
        r("Salad Buah Surya", consumables.getDummyConsumablesMap().get(50), ing(78, 4), ing(83, 1)),
        r("Sereal Gandum Pagi", consumables.getDummyConsumablesMap().get(51), ing(1, 3), ing(59, 1)),
        r("Sup Kuarsa Bening", consumables.getDummyConsumablesMap().get(52), ing(98, 1), ing(59, 3)),
        r("Roti Lapis Ekstrak Biologis", consumables.getDummyConsumablesMap().get(53), ing(1, 2), ing(193, 1)),
        r("Daging Asap Rempah Enath", consumables.getDummyConsumablesMap().get(54), ing(116, 2), ing(75, 1)),
        r("Pie Buah Nymphadora", consumables.getDummyConsumablesMap().get(55), ing(167, 3), ing(1, 1)),
        r("Biskuit Tubuh Nirlelah", consumables.getDummyConsumablesMap().get(56), ing(195, 1), ing(1, 2)),
        r("Ransum Taktis Black Owl", consumables.getDummyConsumablesMap().get(57), ing(44, 2), ing(78, 2)),
        r("Sup Ikan Laut Eldoria", consumables.getDummyConsumablesMap().get(58), ing(67, 3), ing(41, 1)),
        r("Puding Sutra", consumables.getDummyConsumablesMap().get(59), ing(59, 2), ing(83, 1)),
        r("Pesta Panen Vallesia", consumables.getDummyConsumablesMap().get(60), ing(156, 5), ing(116, 5)),
    };

    private static systems.craft.craftingRecipe r(String name, models.item.Item result, systems.craft.craftingRecipe.IngredientReq... reqs) {
        return new systems.craft.craftingRecipe(name, result, new ArrayList<>(Arrays.asList(reqs)));
    }

    private static systems.craft.craftingRecipe.IngredientReq ing(int ingredientId, int qty) {
        return new systems.craft.craftingRecipe.IngredientReq((Inqredients) inqredients.getDummyIngredientsMap().get(ingredientId), qty);
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