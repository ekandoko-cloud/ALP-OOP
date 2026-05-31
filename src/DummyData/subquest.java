package DummyData;

import models.quest.SubQuest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class subquest {

	private static final ArrayList<SubQuest> DUMMY_SUB_QUEST = new ArrayList<>();
	private static final HashMap<Integer, SubQuest> DUMMY_SUB_QUEST_MAP = new HashMap<>();

	static {
		add(q(1, "Valerion", "Bersihkan Dermaga Tua", "Kalahkan 3x Infected Rat", 3, 60));
		add(q(2, "Valerion", "Cari Hasil Laut Aman", "Kumpulkan 4x bahan dari area pantai", 4, 80));
		add(q(3, "Valerion", "Lindungi Gudang Pasar", "Kalahkan 2x Scavenger Scout", 2, 100));

		add(q(4, "Aethelgard", "Baca Catatan Perpustakaan", "Kumpulkan 3x informasi dari area kota", 3, 90));
		add(q(5, "Aethelgard", "Amankan Jalur Kebun", "Kalahkan 3x Swamplands Leech", 3, 110));
		add(q(6, "Aethelgard", "Ambil Sampel Murni", "Kumpulkan 4x bahan penelitian", 4, 130));

		add(q(7, "Grandis", "Perbaiki Lumbung", "Kumpulkan 4x bahan bangunan", 4, 120));
		add(q(8, "Grandis", "Usir Hama Ladang", "Kalahkan 3x Security Drone", 3, 140));
		add(q(9, "Grandis", "Panen Cepat", "Kumpulkan 5x hasil panen", 5, 170));

		add(q(10, "Lumina", "Racik Ramuan Ringan", "Kumpulkan 3x bahan herbal", 3, 150));
		add(q(11, "Lumina", "Jaga Laboratorium", "Kalahkan 2x Failed Experiment", 2, 180));
		add(q(12, "Lumina", "Distribusi Obat", "Antarkan 4x paket obat", 4, 220));

		add(q(13, "Aldoria", "Perkuat Benteng", "Kumpulkan 5x material pertahanan", 5, 220));
		add(q(14, "Aldoria", "Tahan Serangan Abu", "Kalahkan 3x Ash Beast", 3, 260));
		add(q(15, "Aldoria", "Sterilkan Gudang Utama", "Kumpulkan 4x material pemurnian", 4, 320));
	}

	private static void add(SubQuest quest) {
		DUMMY_SUB_QUEST.add(quest);
		DUMMY_SUB_QUEST_MAP.put(quest.getIdQuest(), quest);
	}

	private static SubQuest q(int id, String wilayah, String namaQuest, String objectiveQuest, int objectiveTarget, int hadiahKoin) {
		return new SubQuest(
				id,
				namaQuest,
				"Sub Quest " + wilayah + ".",
				objectiveQuest,
				objectiveTarget,
				hadiahKoin,
				wilayah
		);
	}

	public static List<SubQuest> getDummySubQuest() {
		return new ArrayList<>(DUMMY_SUB_QUEST);
	}

	public static SubQuest[] getDummySubQuestArray() {
		return DUMMY_SUB_QUEST.toArray(new SubQuest[0]);
	}

	public static HashMap<Integer, SubQuest> getDummySubQuestMap() {
		return new HashMap<>(DUMMY_SUB_QUEST_MAP);
	}

	public static SubQuest getSubQuestById(int idQuest) {
		return DUMMY_SUB_QUEST_MAP.get(idQuest);
	}

	public static List<SubQuest> getDummySubQuestByWilayah(String wilayah) {
		ArrayList<SubQuest> hasil = new ArrayList<>();
		for (SubQuest quest : DUMMY_SUB_QUEST) {
			if (quest.getWilayah() != null && quest.getWilayah().equalsIgnoreCase(wilayah)) {
				hasil.add(quest);
			}
		}
		return hasil;
	}

}
