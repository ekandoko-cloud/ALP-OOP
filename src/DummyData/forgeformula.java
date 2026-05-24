package DummyData;

import java.util.ArrayList;

import systems.craft.forgeFormula;

public class forgeformula {

	private static final ArrayList<forgeFormula> FORGE_FORMULA = new ArrayList<>();

	static {
		FORGE_FORMULA.add(new forgeFormula(1, 3, 5, 2, "Iron Ore"));
		FORGE_FORMULA.add(new forgeFormula(2, 3, 8, 3, "Copper Ore"));
		FORGE_FORMULA.add(new forgeFormula(3, 3, 10, 5, "Tin Ore"));
		FORGE_FORMULA.add(new forgeFormula(4, 3, 12, 7, "Silver Ore"));
		FORGE_FORMULA.add(new forgeFormula(5, 3, 15, 10, "Gold Ore"));
		FORGE_FORMULA.add(new forgeFormula(6, 3, 18, 12, "Mithril Ore"));
		FORGE_FORMULA.add(new forgeFormula(7, 3, 20, 15, "Orichalcum Ore"));
		FORGE_FORMULA.add(new forgeFormula(8, 3, 25, 18, "Adamantite Ore"));
		FORGE_FORMULA.add(new forgeFormula(9, 3, 30, 20, "Celestite Ore"));
		FORGE_FORMULA.add(new forgeFormula(10, 3, 35, 25, "Dragonite Ore"));
	}

	public static ArrayList<forgeFormula> getDummyForgeFormulas() {
		return new ArrayList<>(FORGE_FORMULA);
	}

}
