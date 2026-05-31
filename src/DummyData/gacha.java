package DummyData;

import systems.gacha.itemGacha;
import models.item.Accessory;
import models.item.Armor;
import models.item.Equipment;
import models.item.Weapon;

import java.util.*;

public class gacha {

	private static final itemGacha[] DUMMY_GACHA = new itemGacha[]{
			g(w(1), 50, "Common"),
			g(w(2), 50, "Common"),
			g(a(1), 50, "Common"),
			g(a(2), 50, "Common"),
			g(c(1), 50, "Common"),
			g(c(2), 50, "Common"),
			g(w(3), 50, "Common"),

			g(w(4), 30, "Uncommon"),
			g(w(5), 30, "Uncommon"),
			g(a(4), 30, "Uncommon"),
			g(a(5), 30, "Uncommon"),
			g(c(7), 30, "Uncommon"),
			g(c(8), 30, "Uncommon"),
			g(a(6), 30, "Uncommon"),

			g(w(7), 15, "Rare"),
			g(w(8), 15, "Rare"),
			g(a(7), 15, "Rare"),
			g(a(8), 15, "Rare"),
			g(c(13), 15, "Rare"),
			g(c(14), 15, "Rare"),
			g(w(9), 15, "Rare"),

			g(w(10), 4, "Epic"),
			g(w(11), 4, "Epic"),
			g(a(10), 4, "Epic"),
			g(a(11), 4, "Epic"),
			g(c(19), 4, "Epic"),
			g(c(20), 4, "Epic"),
			g(a(12), 4, "Epic"),

			g(w(12), 1, "Legendary"),
			g(w(13), 1, "Legendary"),
			g(a(13), 1, "Legendary"),
			g(a(14), 1, "Legendary"),
			g(c(24), 1, "Legendary"),
			g(c(25), 1, "Legendary"),
			g(c(27), 1, "Legendary")
	};

	private static itemGacha g(Equipment equipment, int probabilitas, String rarity) {
		if (equipment == null) {
			throw new IllegalStateException("Equipment dummy tidak ditemukan");
		}
		return new itemGacha(equipment, probabilitas, rarity);
	}

	private static Equipment w(int id) {
		return (Equipment) weapon.getDummyWeaponsMap().get(id);
	}

	private static Equipment a(int id) {
		return (Equipment) armor.getDummyArmorsMap().get(id);
	}

	private static Equipment c(int id) {
		return (Equipment) accessory.getDummyAccessoriesMap().get(id);
	}

	private static final HashMap<Integer, itemGacha> GACHA_MAP = initializeMap();

	private static HashMap<Integer, itemGacha> initializeMap() {
		HashMap<Integer, itemGacha> map = new HashMap<>();
		for (int i = 0; i < DUMMY_GACHA.length; i++) {
			map.put(i + 1, DUMMY_GACHA[i]);
		}
		return map;
	}

	public static itemGacha[] getDummyGacha() {
		return DUMMY_GACHA;
	}

	public static List<itemGacha> getDummyGachaList() {
		return List.of(DUMMY_GACHA);
	}

	public static HashMap<Integer, itemGacha> getDummyGachaMap() {
		return GACHA_MAP;
	}
}
