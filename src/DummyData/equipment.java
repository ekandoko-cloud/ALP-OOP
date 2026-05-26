package DummyData;

import enums.itemType;
import models.item.Equipment;
import models.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class equipment {

	private static final ArrayList<Equipment> DUMMY_EQUIPMENT = new ArrayList<>();

	static {
		// Warrior
		DUMMY_EQUIPMENT.add(e(1, "Pedang Besar Vanguard", 500, "[Warrior] Pedang dua tangan berat khusus untuk mendobrak lini depan.", "Warrior", 45, 5, 0));
		DUMMY_EQUIPMENT.add(e(2, "Zirah Pelat Baja Berat", 650, "[Warrior] Pelindung dada tebal yang memberikan pertahanan fisik mutlak.", "Warrior", 0, 50, 0));
		DUMMY_EQUIPMENT.add(e(3, "Kapak Tempur Berserker", 480, "[Warrior] Kapak dua bilah yang meningkatkan daya rusak secara brutal.", "Warrior", 50, 0, 0));
		DUMMY_EQUIPMENT.add(e(4, "Perisai Menara Besi", 400, "[Warrior] Perisai besar penutup seluruh tubuh ksatria pelindung.", "Warrior", 5, 45, 0));
		DUMMY_EQUIPMENT.add(e(5, "Helm Tanduk Ogre", 250, "[Warrior] Helm besi dengan dekorasi tanduk penambah kesan intimidasi.", "Warrior", 15, 20, 0));
		DUMMY_EQUIPMENT.add(e(6, "Gada Penghancur Armor", 380, "[Warrior] Senjata tumpul berat untuk meremukkan zirah pelat musuh.", "Warrior", 38, 0, 0));
		DUMMY_EQUIPMENT.add(e(7, "Sepatu Boot Pelat Besi", 180, "[Warrior] Boot berat yang kokoh, membuat pemakainya sulit dijatuhkan.", "Warrior", 5, 15, 0));
		DUMMY_EQUIPMENT.add(e(8, "Sarung Tangan Ksatria Singa", 210, "[Warrior] Gauntlet pelindung tangan yang memperkuat cengkeraman senjata.", "Warrior", 12, 12, 0));
		DUMMY_EQUIPMENT.add(e(9, "Tombak Berkuda Kerajaan", 420, "[Warrior] Tombak panjang berujung baja runcing untuk serangan menusuk.", "Warrior", 35, 5, 0));
		DUMMY_EQUIPMENT.add(e(10, "Perisai Duri Perunggu", 300, "[Warrior] Perisai sedang dilengkapi duri untuk membalas serangan dekat.", "Warrior", 15, 25, 0));
		DUMMY_EQUIPMENT.add(e(11, "Zirah Rantai Kawat", 350, "[Warrior] Chainmail fleksibel yang nyaman namun tetap protektif.", "Warrior", 0, 35, 0));
		DUMMY_EQUIPMENT.add(e(12, "Claymore Berkarat", 150, "[Warrior] Pedang besar tua yang masih menyimpan daya tebas mematikan.", "Warrior", 25, 2, 0));
		DUMMY_EQUIPMENT.add(e(13, "Sabuk Kulit Raksasa", 190, "[Warrior] Ikat pinggang penahan beban yang meningkatkan stamina otot.", "Warrior", 20, 8, 0));

		// Archer
		DUMMY_EQUIPMENT.add(e(14, "Busur Panjang Kayu Elven", 450, "[Archer] Busur ringan dari kayu mistis dengan akurasi jarak jauh.", "Archer", 42, 0, 0));
		DUMMY_EQUIPMENT.add(e(15, "Jubah Kulit Penjejak", 320, "[Archer] Pakaian berburu yang ringan untuk mobilitas tinggi.", "Archer", 10, 22, 0));
		DUMMY_EQUIPMENT.add(e(16, "Busur Silang Berbisa", 520, "[Archer] Crossbow mekanis yang meluncurkan baut berdaya rusak tinggi.", "Archer", 48, 0, 0));
		DUMMY_EQUIPMENT.add(e(17, "Sepatu Boot Ringan Angin", 160, "[Archer] Boot kain tipis yang dirancang agar bisa melangkah tanpa suara.", "Archer", 8, 10, 0));
		DUMMY_EQUIPMENT.add(e(18, "Sarung Tangan Pemburu Elang", 180, "[Archer] Sarung tangan kulit pelindung jari saat menarik tali busur.", "Archer", 18, 5, 0));
		DUMMY_EQUIPMENT.add(e(19, "Penutup Kepala Kamuflase", 200, "[Archer] Hood penyamar keberadaan di dalam vegetasi hutan.", "Archer", 12, 15, 0));
		DUMMY_EQUIPMENT.add(e(20, "Tas Anak Panah Elang", 240, "[Archer] Quiver magis yang meringankan bobot anak panah yang dibawa.", "Archer", 20, 4, 0));
		DUMMY_EQUIPMENT.add(e(21, "Belati Berkembar Siluman", 310, "[Archer] Sepasang belati cadangan jika musuh berhasil mendekat.", "Archer", 32, 2, 0));
		DUMMY_EQUIPMENT.add(e(22, "Rompi Sisik Reptil Rawa", 340, "[Archer] Armor ringan tahan air yang tidak membebani pergerakan.", "Archer", 5, 28, 0));
		DUMMY_EQUIPMENT.add(e(23, "Busur Pendek Komposit", 280, "[Archer] Busur lincah yang sangat efektif untuk gaya tempur bergerak.", "Archer", 30, 0, 0));
		DUMMY_EQUIPMENT.add(e(24, "Cincin Fokus Penglihatan", 290, "[Archer] Cincin yang mempertajam insting membidik titik vital.", "Archer", 25, 0, 0));
		DUMMY_EQUIPMENT.add(e(25, "Kacamata Pembidik Mekanis", 360, "[Archer] Lensa pembesar khusus pemantau pergerakan angin.", "Archer", 22, 8, 0));

		// Mage
		DUMMY_EQUIPMENT.add(e(26, "Tongkat Kristal Mana", 550, "[Mage] Tongkat pengalir sihir dengan ujung batu permata murni.", "Mage", 46, 2, 0));
		DUMMY_EQUIPMENT.add(e(27, "Jubah Sutra Arcane", 400, "[Mage] Jubah tenunan benang mana yang menahan abrasi sihir musuh.", "Mage", 12, 24, 0));
		DUMMY_EQUIPMENT.add(e(28, "Kitab Mantra Kuno", 460, "[Mage] Grimoire berisi tulisan kuno pengganda daya hancur sihir.", "Mage", 40, 0, 0));
		DUMMY_EQUIPMENT.add(e(29, "Topi Kerucut Astrologi", 220, "[Mage] Topi penyihir klasik yang memfokuskan energi astral langit.", "Mage", 15, 12, 0));
		DUMMY_EQUIPMENT.add(e(30, "Tongkat Kayu Penatua", 260, "[Mage] Tongkat sederhana dari pohon purba beresonansi magis stabil.", "Mage", 28, 5, 0));
		DUMMY_EQUIPMENT.add(e(31, "Cincin Delima Api", 300, "[Mage] Aksesoris yang meningkatkan intensitas sihir elemen panas.", "Mage", 35, 0, 0));
		DUMMY_EQUIPMENT.add(e(32, "Sepatu Kain Berwujud", 150, "[Mage] Sepatu ringan penunjang meditasi saat berjalan.", "Mage", 5, 11, 0));
		DUMMY_EQUIPMENT.add(e(33, "Gelang Esensi Energi", 210, "[Mage] Gelang penghantar riak sihir agar tidak melukai pengguna.", "Mage", 20, 8, 0));
		DUMMY_EQUIPMENT.add(e(34, "Kalung Jimat Safir", 320, "[Mage] Amulet yang menstabilkan pasokan sihir dalam tubuh.", "Mage", 25, 10, 0));
		DUMMY_EQUIPMENT.add(e(35, "Orbe Kaca Kegelapan", 490, "[Mage] Bola kaca hitam yang memancarkan aura kehancuran tinggi.", "Mage", 44, 0, 0));
		DUMMY_EQUIPMENT.add(e(36, "Mantel Bulu Phoenix", 410, "[Mage] Jubah merah tebal berdaya tahan panas tinggi.", "Mage", 15, 26, 0));
		DUMMY_EQUIPMENT.add(e(37, "Sarung Tangan Rajutan Mantra", 190, "[Mage] Memudahkan perapalan segel sihir menggunakan tangan.", "Mage", 18, 6, 0));
		DUMMY_EQUIPMENT.add(e(38, "Tiara Perak Pengendali", 330, "[Mage] Mahkota kecil penyeimbang konsentrasi pikiran.", "Mage", 22, 14, 0));

		// Support
		DUMMY_EQUIPMENT.add(e(39, "Tongkat Suci Pemberi Berkat", 480, "[Support] Tongkat upacara ritual, meningkatkan efektivitas pemulihan.", "Support", 15, 28, 0));
		DUMMY_EQUIPMENT.add(e(40, "Jubah Pelindung Guardian", 600, "[Support] Jubah tebal berlapis pelindung aura bagi penjaga tim.", "Support", 4, 48, 0));
		DUMMY_EQUIPMENT.add(e(41, "Lonceng Ritual Penyembuh", 370, "[Support] Dentang loncengnya memberikan rasa tenang dan perlindungan.", "Support", 10, 32, 0));
		DUMMY_EQUIPMENT.add(e(42, "Tameng Cahaya Kebajikan", 520, "[Support] Perisai suci pemecah kutukan dan pelindung rekan.", "Support", 0, 46, 0));
		DUMMY_EQUIPMENT.add(e(43, "Mahkota Daun Kehidupan", 290, "[Support] Hiasan kepala herbal penangkal racun dan aura negatif.", "Support", 12, 24, 0));
		DUMMY_EQUIPMENT.add(e(44, "Sepatu Boot Pengembara Baik", 190, "[Support] Boot awet penjelajah untuk menolong wilayah tertinggal.", "Support", 6, 20, 0));
		DUMMY_EQUIPMENT.add(e(45, "Kalung Relik Keabadian", 410, "[Support] Jimat kuno penyimpan energi cadangan keselamatan.", "Support", 5, 38, 0));
		DUMMY_EQUIPMENT.add(e(46, "Lambang Suci Perak", 250, "[Support] Simbol keagamaan yang memancarkan aura pertahanan pasif.", "Support", 14, 22, 0));
		DUMMY_EQUIPMENT.add(e(47, "Buku Doa Agung", 310, "[Support] Manuskrip doa pelindung barisan pertahanan kawan.", "Support", 18, 25, 0));
		DUMMY_EQUIPMENT.add(e(48, "Cincin Aura Kebal", 380, "[Support] Membentuk pelindung kasat mata tipis di sekitar pengguna.", "Support", 0, 40, 0));
		DUMMY_EQUIPMENT.add(e(49, "Sarung Tangan Sutra Berkat", 170, "[Support] Sarung tangan higienis untuk menyalurkan energi vital.", "Support", 8, 16, 0));
		DUMMY_EQUIPMENT.add(e(50, "Sabuk Penjaga Kedamaian", 220, "[Support] Ikat pinggang tempat menyimpan ramuan obat darurat.", "Support", 5, 30, 0));
	}

	private static Equipment e(int id, String name, int price, String desc, String tipe, int atk, int def, int level) {
		Equipment equip = new Equipment(id, name, price, desc, itemType.EQUIPMENT, atk, def, level);
		equip.setTipeEquipment(tipe);
		return equip;
	}

	public static List<Equipment> getEquipment() {
		return new ArrayList<>(DUMMY_EQUIPMENT);
	}

	public static HashMap<Integer, Item> getEquipmentMap() {
		HashMap<Integer, Item> equipmentMap = new HashMap<>();
		for (Equipment equipment : DUMMY_EQUIPMENT) {
			equipmentMap.put(equipment.getIdItem(), equipment);
		}
		return equipmentMap;
	}
}
