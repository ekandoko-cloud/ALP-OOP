package DummyData;

import java.util.ArrayList;

import systems.craft.forgeFormula;

public class forgeformula {

	private static final ArrayList<forgeFormula> FORGE_FORMULA = new ArrayList<>();

	static {
		FORGE_FORMULA.add(new forgeFormula(1, 3, 10, 5, "Copper"));
		FORGE_FORMULA.add(new forgeFormula(2, 3, 14, 6, "Iron"));
		FORGE_FORMULA.add(new forgeFormula(3, 3, 18, 7, "Silver"));
		FORGE_FORMULA.add(new forgeFormula(4, 3, 22, 8, "Gold"));
		FORGE_FORMULA.add(new forgeFormula(5, 3, 26, 9, "Platinum"));
		FORGE_FORMULA.add(new forgeFormula(6, 3, 30, 10, "Titanium"));
		FORGE_FORMULA.add(new forgeFormula(7, 3, 34, 11, "Mithril"));
		FORGE_FORMULA.add(new forgeFormula(8, 3, 38, 12, "Adamantite"));
		FORGE_FORMULA.add(new forgeFormula(9, 3, 42, 13, "Orichalcum"));
		FORGE_FORMULA.add(new forgeFormula(10, 3, 46, 14, "Obsidian"));
	}

	public static ArrayList<forgeFormula> getDummyForgeFormulas() {
		return new ArrayList<>(FORGE_FORMULA);
	}

}
