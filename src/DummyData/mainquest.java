package DummyData;

import models.quest.MainQuest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class mainquest {

	private static final ArrayList<MainQuest> DUMMY_MAIN_QUEST = new ArrayList<>();
	private static final HashMap<Integer, MainQuest> DUMMY_MAIN_QUEST_MAP = new HashMap<>();

	static {
		add(q(1, "Valerion", 1, "Investigasi Tanah Mati", "3x Infected Rat, 1x Scavenger Scout", 4, 100, "5x Copper", 1,
				"Infected Rat", "Infected Rat", "Infected Rat", "Scavenger Scout"));
		add(q(2, "Valerion", 2, "Melacak Akar Parasit", "2x Corrupted Crawler, 2x Blight Spore", 4, 150, "5x Bitter Herb", 1,
				"Corrupted Crawler", "Corrupted Crawler", "Blight Spore", "Blight Spore"));
		add(q(3, "Valerion", 3, "Mengamankan Air Bersih", "1x Scavenger Hunter, 3x Infected Rat", 4, 200, "1x Filtered Water", 1,
				"Scavenger Hunter", "Infected Rat", "Infected Rat", "Infected Rat"));
		add(q(4, "Valerion", 4, "Pemetaan Area Blight", "2x Blight Spore, 2x Corrupted Crawler", 4, 0, "5x Iron + 5x Withered Herb", 1,
				"Blight Spore", "Blight Spore", "Corrupted Crawler", "Corrupted Crawler"));
		add(q(5, "Valerion", 5, "Melawan Blight-Root", "2x Blight Spore, 2x Corrupted Crawler, 1x Boss: Blight-Root", 5, 300, "Blight-Root Core", 1,
				"Blight Spore", "Blight Spore", "Corrupted Crawler", "Corrupted Crawler", "Blight-Root"));

		add(q(6, "Aethelgard", 1, "Analisis Miasma", "3x Swamplands Leech, 1x Miasma Husk", 4, 250, "5x Murky Sludge", 2,
				"Swamplands Leech", "Swamplands Leech", "Swamplands Leech", "Miasma Husk"));
		add(q(7, "Aethelgard", 2, "Mencari Filter Crystal", "2x Sludge Mutant, 2x Swamplands Leech", 4, 300, "2x Crystal Shard", 2,
				"Sludge Mutant", "Sludge Mutant", "Swamplands Leech", "Swamplands Leech"));
		add(q(8, "Aethelgard", 3, "Menyelamatkan Penduduk", "1x Miasma Husk, 3x Sludge Mutant", 4, 400, "5x Sanguine Herb", 2,
				"Miasma Husk", "Sludge Mutant", "Sludge Mutant", "Sludge Mutant"));
		add(q(9, "Aethelgard", 4, "Melumpuhkan Sumber Limbah", "2x Miasma Husk, 2x Sludge Mutant", 4, 0, "10x Titanium + 5x Purified Sap", 2,
				"Miasma Husk", "Miasma Husk", "Sludge Mutant", "Sludge Mutant"));
		add(q(10, "Aethelgard", 5, "Melawan Goliath Toad", "2x Swamplands Leech, 2x Sludge Mutant, 1x Boss: Goliath Toad", 5, 500, "Miasma Gland", 2,
				"Swamplands Leech", "Swamplands Leech", "Sludge Mutant", "Sludge Mutant", "Goliath Toad"));

		add(q(11, "Grandis", 1, "Menyusup ke Sektor Luar", "3x Security Drone, 1x Enath Trooper", 4, 400, "10x Iron", 3,
				"Security Drone", "Security Drone", "Security Drone", "Enath Trooper"));
		add(q(12, "Grandis", 2, "Membuka Gerbang Gudang", "2x Elite Guard, 2x Security Drone", 4, 500, "10x Bread", 3,
				"Elite Guard", "Elite Guard", "Security Drone", "Security Drone"));
		add(q(13, "Grandis", 3, "Memutus Distribusi Ilegal", "1x Enath Trooper, 3x Elite Guard", 4, 600, "5x Silver", 3,
				"Enath Trooper", "Elite Guard", "Elite Guard", "Elite Guard"));
		add(q(14, "Grandis", 4, "Melawan Pengawal Baron", "2x Elite Guard, 2x Heavy Enath Trooper", 4, 800, "5x Tough Leather", 3,
				"Elite Guard", "Elite Guard", "Heavy Enath Trooper", "Heavy Enath Trooper"));
		add(q(15, "Grandis", 5, "Melawan Baron Gluttony", "2x Security Drone, 2x Elite Guard, 1x Boss: Baron Gluttony", 5, 1000, "Silo Vault Key", 3,
				"Security Drone", "Security Drone", "Elite Guard", "Elite Guard", "Baron Gluttony"));

		add(q(16, "Lumina", 1, "Mencari Data Vaksin", "3x Test Subject X, 1x Alchemist Cultist", 4, 700, "10x Quartz", 4,
				"Test Subject X", "Test Subject X", "Test Subject X", "Alchemist Cultist"));
		add(q(17, "Lumina", 2, "Menguji Sampel Klinis", "2x Failed Experiment, 2x Test Subject X", 4, 800, "5x Purified Herb", 4,
				"Failed Experiment", "Failed Experiment", "Test Subject X", "Test Subject X"));
		add(q(18, "Lumina", 3, "Pembebasan Tahanan", "1x Alchemist Cultist, 3x Failed Experiment", 4, 900, "5x Mana Petal", 4,
				"Alchemist Cultist", "Failed Experiment", "Failed Experiment", "Failed Experiment"));
		add(q(19, "Lumina", 4, "Menembus Lab Utama", "2x Alchemist Cultist, 2x Failed Experiment", 4, 1200, "5x Adamantite", 4,
				"Alchemist Cultist", "Alchemist Cultist", "Failed Experiment", "Failed Experiment"));
		add(q(20, "Lumina", 5, "Melawan Dr. Mortis", "2x Test Subject X, 2x Alchemist Cultist, 1x Boss: Dr. Mortis", 5, 1500, "Vaccine Formula", 4,
				"Test Subject X", "Test Subject X", "Alchemist Cultist", "Alchemist Cultist", "Dr. Mortis"));

		add(q(21, "Aldoria", 1, "Menstabilkan Radiasi", "3x Ash Beast, 1x Radiant Sentinel", 4, 1500, "10x Obsidian", 5,
				"Ash Beast", "Ash Beast", "Ash Beast", "Radiant Sentinel"));
		add(q(22, "Aldoria", 2, "Mengaktifkan Seed", "2x Flare Crawler, 2x Ash Beast", 4, 2000, "10x Genesis Bloom", 5,
				"Flare Crawler", "Flare Crawler", "Ash Beast", "Ash Beast"));
		add(q(23, "Aldoria", 3, "Menggabungkan Formula", "1x Radiant Sentinel, 3x Flare Crawler", 4, 2500, "5x Mithril", 5,
				"Radiant Sentinel", "Flare Crawler", "Flare Crawler", "Flare Crawler"));
		add(q(24, "Aldoria", 4, "Penghancur Pelindung", "2x Radiant Sentinel, 2x Flare Crawler", 4, 3000, "10x Meteorite Dust", 5,
				"Radiant Sentinel", "Radiant Sentinel", "Flare Crawler", "Flare Crawler"));
		add(q(25, "Aldoria", 5, "Melawan Chimera", "2x Ash Beast, 2x Flare Crawler, 1x Boss: Crimson Chimera", 5, 5000, "Core of Purified Earth", 5,
				"Ash Beast", "Ash Beast", "Flare Crawler", "Flare Crawler", "Crimson Chimera"));
	}

	private static void add(MainQuest quest) {
		DUMMY_MAIN_QUEST.add(quest);
		DUMMY_MAIN_QUEST_MAP.put(quest.getIdQuest(), quest);
	}

	private static MainQuest q(int id, String wilayah, int nomorQuest, String namaQuest, String objectiveQuest, int objectiveTarget, int hadiahKoin, String hadiahUtama, int chapterTerbuka, String... lineUpMusuh) {
		return new MainQuest(
				id,
				namaQuest,
				"Main Quest " + wilayah + " - Quest " + nomorQuest + ".",
				objectiveQuest,
				objectiveTarget,
				hadiahKoin,
				chapterTerbuka,
				wilayah,
				nomorQuest,
				hadiahUtama,
				Arrays.asList(lineUpMusuh)
		);
	}

	public static List<MainQuest> getDummyMainQuest() {
		return new ArrayList<>(DUMMY_MAIN_QUEST);
	}

	public static MainQuest[] getDummyMainQuestArray() {
		return DUMMY_MAIN_QUEST.toArray(new MainQuest[0]);
	}

	public static HashMap<Integer, MainQuest> getDummyMainQuestMap() {
		return new HashMap<>(DUMMY_MAIN_QUEST_MAP);
	}

	public static MainQuest getMainQuestById(int idQuest) {
		return DUMMY_MAIN_QUEST_MAP.get(idQuest);
	}

	public static List<MainQuest> getDummyMainQuestByWilayah(String wilayah) {
		ArrayList<MainQuest> hasil = new ArrayList<>();
		for (MainQuest quest : DUMMY_MAIN_QUEST) {
			if (quest.getWilayah() != null && quest.getWilayah().equalsIgnoreCase(wilayah)) {
				hasil.add(quest);
			}
		}
		return hasil;
	}

	public static List<MainQuest> getDummyMainQuestByChapter(int chapterTerbuka) {
		ArrayList<MainQuest> hasil = new ArrayList<>();
		for (MainQuest quest : DUMMY_MAIN_QUEST) {
			if (quest.getChapterTerbuka() == chapterTerbuka) {
				hasil.add(quest);
			}
		}
		return hasil;
	}
}
