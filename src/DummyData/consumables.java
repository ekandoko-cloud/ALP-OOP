package DummyData;

import enums.itemType;
import models.character.GameCharacter;
import models.item.ConsumableFood;
import models.item.Item;

import java.util.*;

public class consumables {

    private static final ConsumableFood[] DUMMY_CONSUMABLES = new ConsumableFood[]{
            c(1, "Daging Orc Goreng Lemak", 120, "Resep: 2x Tulang Belulang Orc dan 2x Minyak Lemak Hewan. Hidangan berat dengan cita rasa gurih. Efek: HP +180, STR -6, DEF -2.", 180, 0, -6, -2, "Tidak Sehat - Tinggi lemak jenuh, risiko obesitas."),
            c(2, "Roti Gandum Berjamur", 80, "Resep: 2x Sisa Roti Keras dan 1x Spora Jamur Gua. Roti lama dengan aroma tajam. Efek: HP +120, DEF -4.", 120, 0, 0, -4, "Tidak Sehat - Kontaminasi mikotoksin berbahaya."),
            c(3, "Burger Lendir Goblin", 150, "Resep: 1x Sisa Roti Keras dan 2x Lendir Slime Biasa. Burger aneh dengan tekstur licin. Efek: HP +20, DEF -3.", 20, 0, 0, -3, "Tidak Sehat - Bahan baku tidak higienis dan korosif."),
            c(4, "Sate Daging Tikus", 95, "Resep: 3x Kulit Tikus Selokan dan 1x Paku Bengkok. Sate ekstrem dengan rasa liar. Efek: STR +90, DEF -3.", 0, 0, 90, -3, "Tidak Sehat - Risiko tinggi pembawa vektor penyakit pes."),
            c(5, "Sup Tulang Berkarat", 110, "Resep: 2x Tulang Rapuh dan 1x Besi Karatan. Sup asin beraroma logam. Efek: HP +150, STR -2.", 150, 0, -2, 0, "Tidak Sehat - Kontaminasi logam berat berbahaya."),
            c(6, "Keripik Cangkang Kumbang", 135, "Resep: 3x Cangkang Kumbang dan 1x Kristal Garam Kotor. Camilan renyah dengan rasa kasar. Efek: STR +60, DEF -4.", 0, 0, 60, -4, "Tidak Sehat - Kadar natrium kotor merusak tekanan darah."),
            c(7, "Tumis Jamur Halusinasi", 190, "Resep: 4x Spora Jamur Gua dan 1x Minyak Pelumas Taktis. Tumisan pekat dengan aroma tajam. Efek: STR -4, DEF +78.", 0, 0, -4, 78, "Tidak Sehat - Mengandung zat psikotropika yang merusak saraf."),
            c(8, "Dendeng Kelelawar Asam", 140, "Resep: 2x Gigi Kelelawar dan 2x Air Hujan Asam. Dendeng asin dengan rasa menusuk. Efek: HP +20, DEF -2.", 20, 0, 0, -2, "Tidak Sehat - Kadar asam ekstrem merusak lambung."),
            c(9, "Pie Daging Misterius", 210, "Resep: 3x Darah Monster dan 1x Tanah Liat. Pie gelap dengan isi tak jelas. Efek: HP +200, STR -4.", 200, 0, -4, 0, "Tidak Sehat - Sumber protein tidak jelas, memicu malnutrisi."),
            c(10, "Ransum Darurat Kadaluarsa", 75, "Resep: 2x Sisa Roti Keras dan 2x Tulang Belulang Orc. Ransum seadanya yang bertahan lama. Efek: STR -4, DEF +100.", 0, 0, -4, 100, "Tidak Sehat - Makanan basi melanggar standar keamanan pangan."),
            c(11, "Gulai Taring Serigala", 165, "Resep: 3x Cakar Serigala Liar dan 1x Air Keruh. Gulai liar dengan rasa tajam. Efek: STR +75.", 0, 0, 75, 0, "Tidak Sehat - Bahan tajam memicu pendarahan internal."),
            c(12, "Kue Lumpur Rawa", 160, "Resep: 2x Lendir Asam dan 2x Tanah Liat. Kue lembek dengan cita rasa rawa. Efek: HP +130, DEF -2.", 130, 0, 0, -2, "Tidak Sehat - Mengandung mikroba patogen tanah rawa."),
            c(13, "Sosis Darah Vampir", 300, "Resep: 1x Darah Vampir dan 2x Tali Usus. Sosis gelap dengan aroma tajam. Efek: STR +85, DEF -2.", 0, 0, 85, -2, "Tidak Sehat - Infeksi parasit darah purba merusak sel imun."),
            c(14, "Omelet Telur Laba-Laba", 185, "Resep: 3x Jaring Laba Laba dan 2x Kristal Garam Kotor. Omelet tipis dengan tekstur unik. Efek: HP +15, DEF -2.", 15, 0, 0, -2, "Tidak Sehat - Toksin laba-laba memperlambat sistem motorik."),
            c(15, "Sate Kalajengking Berbisa", 225, "Resep: 2x Ekor Kalajengking dan 1x Kayu Lapuk. Sate liar dengan sensasi pedas. Efek: DEF +45.", 0, 0, 0, 45, "Tidak Sehat - Racun sitotoksik merusak dinding usus."),
            c(16, "Daging Beruang Gosong", 240, "Resep: 2x Kulit Beruang Coklat dan 1x Batu Bara Neraka. Daging panggang dengan aroma asap pekat. Efek: HP +160, STR -3.", 160, 0, -3, 0, "Tidak Sehat - Senyawa karsinogenik tinggi akibat karbonisasi."),
            c(17, "Sup Lendir Asam", 260, "Resep: 4x Lendir Asam dan 1x Air Keruh. Sup pekat dengan rasa asam kuat. Efek: DEF +67.", 0, 0, 0, 67, "Tidak Sehat - Asam kuat melarutkan lapisan kalsium tubuh."),
            c(18, "Roti Lapis Mangan", 280, "Resep: 2x Sisa Roti Keras dan 1x Serbuk Mangan. Roti lapis padat dengan rasa mineral. Efek: STR +65.", 0, 0, 65, 0, "Tidak Sehat - Keracunan logam mangan mengganggu fungsi hati."),
            c(19, "Steak Monster Gagal", 310, "Resep: 3x Darah Monster dan 2x Getah Pohon Beracun. Steak gelap dengan aroma tajam. Efek: HP +100, MP +50, STR -6, DEF -3.", 100, 50, -6, -3, "Tidak Sehat - Gagal netralisasi kimiawi, merusak metabolisme."),
            c(20, "Bakar Cakar Gargoyle", 295, "Resep: 2x Cakar Gargoyle dan 1x Belerang Mentah. Bakar keras dengan tekstur mineral. Efek: HP +10, STR -3, DEF +90.", 10, 0, -3, 90, "Tidak Sehat - Kepadatan material mineral menyumbat arteri."),
            c(21, "Keripik Sisik Naga", 500, "Resep: 1x Sisik Naga Tanah dan 3x Minyak Lemak Hewan. Keripik keras dengan rasa gurih tebal. Efek: HP +20, STR +5, DEF -4.", 20, 0, 5, -4, "Tidak Sehat - Kolesterol kental menyumbat energi kardio."),
            c(22, "Kaldu Tengkorak", 140, "Resep: 2x Tulang Belulang Orc dan 2x Air Keruh. Kaldu gelap dengan rasa pekat. Efek: MP +110, STR -2.", 0, 110, -2, 0, "Tidak Sehat - Residu nekrotik memicu trauma psikologis."),
            c(23, "Bubur Padi Besi", 270, "Resep: 4x Batang Padi Besi dan 1x Kristal Garam Kotor. Bubur padat dengan tekstur berat. Efek: STR +40, DEF -3.", 0, 0, 40, -3, "Tidak Sehat - Serat logam merusak mikrobioma lambung."),
            c(24, "Manisan Mata Harpy", 420, "Resep: 2x Mata Harpy Ratu dan 2x Madu Hutan Tersihir. Manisan manis dengan rasa tajam. Efek: MP +80, DEF -4.", 0, 80, 0, -4, "Tidak Sehat - Kadar gula buatan merusak retina jangka pendek."),
            c(25, "Sate Usus Troll", 480, "Resep: 3x Tali Usus dan 1x Kulit Troll Regenerasi. Sate liat dengan aroma tajam. Efek: HP +140, STR -4, DEF -2.", 140, 0, -4, -2, "Tidak Sehat - Bakteri pembusuk usus memicu syok anafilaktik."),
            c(26, "Daging Ogre Cincang Mentah", 480, "Resep: 3x Taring Ogre dan 1x Tumbukan Arang. Daging cincang kasar dengan rasa liar. Efek: HP +170, STR -4.", 170, 0, -4, 0, "Tidak Sehat - Bakteri mentah melumpuhkan sistem limfatik."),
            c(27, "Kue Spora Kematian", 500, "Resep: 4x Spora Jamur Gua dan 1x Venom Gland Murni. Kue gelap dengan aroma menyengat. Efek: MP +20, STR -3, DEF -3.", 0, 20, -3, -3, "Tidak Sehat - Toksin mematikan penghancur sistem saraf pusat."),
            c(28, "Gorengan Minyak Hitam", 160, "Resep: 2x Sisa Roti Keras dan 2x Minyak Pelumas Taktis. Gorengan gelap dengan rasa tebal. Efek: DEF +28.", 0, 0, 0, 28, "Tidak Sehat - Radikal bebas hidrokarbon meracuni organ dalam."),
            c(29, "Stew Ikan Mutan", 380, "Resep: 2x Sisik Ikan Mutan dan 2x Air Keruh. Stew laut dengan rasa berat. Efek: MP +60, STR -2, DEF -2.", 0, 60, -2, -2, "Tidak Sehat - Kontaminasi merkuri tinggi mengubah daya hantar tubuh."),
            c(30, "Pesta Daging Enath", 500, "Resep: 5x Darah Monster dan 2x Pelat Paduan Enath. Hidangan militer dengan rasa padat. Efek: STR +95, DEF -4.", 0, 0, 95, -4, "Tidak Sehat - Pengawet kimiawi militer merusak fungsi motorik."),
            c(31, "Salad Gandum Murni", 100, "Resep: 3x Serat Gandum Kering dan 1x Kristal Air Murni. Hidangan ringan dan menyegarkan. Efek: HP +70.", 70, 0, 0, 0, "Mendukung SDG 3: Kaya serat alami untuk pencernaan sehat."),
            c(32, "Sup Daun Obat Steril", 150, "Resep: 3x Daun Obat Kering dan 2x Kristal Air Murni. Sup herbal hangat dan bersih. Efek: HP +120.", 120, 0, 0, 0, "Mendukung SDG 3: Senyawa herbal meningkatkan imun tubuh."),
            c(33, "Daging Asap Daun Cemara", 200, "Resep: 2x Darah Monster dan 2x Getah Pohon Cemara. Daging asap dengan aroma hutan. Efek: STR +20.", 0, 0, 20, 0, "Mendukung SDG 2: Protein hewani bersih bebas zat kimia."),
            c(34, "Bubur Sari Pati", 130, "Resep: 3x Sari Pati Tumbuhan dan 1x Kristal Air Murni. Bubur lembut bernutrisi. Efek: HP +60.", 60, 0, 0, 0, "Mendukung SDG 3: Nutrisi mikro nabati cepat diserap tubuh."),
            c(35, "Roti Teratai Salju", 280, "Resep: 2x Serat Gandum Kering dan 1x Daun Teratai Salju. Roti dingin dengan rasa halus. Efek: HP +100.", 100, 0, 0, 0, "Mendukung SDG 3: Agen pendingin seluler alami anti inflamasi."),
            c(36, "Ikan Panggang Steril", 170, "Resep: 2x Sisik Ikan Mutan dan 1x Kristal Garam Kotor. Ikan panggang dengan rasa sederhana. Efek: HP +90.", 90, 0, 0, 0, "Mendukung SDG 3: Asam lemak esensial mengoptimalkan fungsi otak."),
            c(37, "Sup Jamur Bercahaya", 220, "Resep: 3x Lumut Bercahaya dan 2x Kristal Air Murni. Sup hangat dengan cahaya lembut. Efek: MP +50.", 0, 50, 0, 0, "Mendukung SDG 3: Fosfor organik alami aman bagi metabolisme."),
            c(38, "Puding Lidah Buaya", 195, "Resep: 3x Ekstrak Lidah Buaya dan 1x Madu Hutan Tersihir. Puding lembut dan segar. Efek: HP +80.", 80, 0, 0, 0, "Mendukung SDG 3: Menjaga hidrasi dan kesehatan mukosa lambung."),
            c(39, "Salad Sayur Vallesia", 250, "Resep: 4x Daun Teratai Salju dan 1x Minyak Pelumas Taktis. Salad segar dari bahan kebun. Efek: STR +15, DEF +25.", 0, 0, 15, 25, "Mendukung SDG 2: Produk pertanian organik berkelanjutan."),
            c(40, "Biskuit Energi Black Owl", 340, "Resep: 2x Serat Gandum Kering dan 1x Ekstrak Adrenalin. Biskuit padat untuk aktivitas cepat. Efek: STR +45.", 0, 0, 45, 0, "Mendukung SDG 3: Energi instan dari karbohidrat kompleks."),
            c(41, "Ransum Komando Commander", 400, "Resep: 3x Darah Monster dan 2x Daun Teratai Salju. Ransum praktis untuk perjalanan. Efek: HP +150.", 150, 0, 0, 0, "Mendukung SDG 2: Gizi seimbang lengkap makronutrien."),
            c(42, "Sate Madu Hutan", 230, "Resep: 2x Darah Monster dan 1x Madu Hutan Tersihir. Sate manis dengan aroma alami. Efek: HP +75.", 75, 0, 0, 0, "Mendukung SDG 3: Antioksidan alami dari madu murni hutan."),
            c(43, "Kentang Panggang Api", 145, "Resep: 3x Akar Ginseng Emas dan 1x Batu Bara Neraka. Kentang panggang hangat. Efek: HP +65.", 65, 0, 0, 0, "Mendukung SDG 2: Kalori bersih bebas lemak jenuh buatan."),
            c(44, "Stew Akar Ginseng", 500, "Resep: 1x Akar Ginseng Emas dan 2x Kristal Air Murni. Stew herbal yang menghangatkan. Efek: HP +180, MP +100.", 180, 100, 0, 0, "Mendukung SDG 3: Saponin ginseng meningkatkan imunitas sel."),
            c(45, "Bubur Kaldu Murni", 210, "Resep: 2x Tulang Rapuh dan 3x Kristal Air Murni. Bubur kaldu lembut dan hangat. Efek: HP +110, DEF +50.", 110, 0, 0, 50, "Mendukung SDG 3: Sumber kolagen alami baik untuk sendi tubuh."),
            c(46, "Nasi Tim Orichalcum (Kiasan)", 290, "Resep: 3x Batang Padi Besi dan 1x Sari Pati Tumbuhan. Nasi tim padat dan stabil. Efek: STR +35.", 0, 0, 35, 0, "Mendukung SDG 2: Beras kualitas super bebas residu pestisida."),
            c(47, "Kue Kismis Bintang", 460, "Resep: 2x Serat Gandum Kering dan 1x Serpihan Bintang. Kue manis dengan taburan cerah. Efek: MP +90.", 0, 90, 0, 0, "Mendukung SDG 3: Glukosa murni alami meningkatkan fokus kognitif."),
            c(48, "Tumis Teratai Ginseng", 580, "Resep: 1x Daun Teratai Salju dan 1x Akar Ginseng Emas. Tumisan ringan bernuansa herbal. Efek: DEF +60.", 0, 0, 0, 60, "Mendukung SDG 3: Efek detoksifikasi radikal bebas tingkat sel."),
            c(49, "Daging Bakar Batu Apung", 330, "Resep: 2x Darah Monster dan 1x Batu Apung Vulkanik. Daging bakar dengan rasa asap lembut. Efek: DEF +40.", 0, 0, 0, 40, "Mendukung SDG 3: Proses pemanggangan sehat tanpa arang hitam."),
            c(50, "Salad Buah Surya", 390, "Resep: 4x Serpihan Bintang dan 1x Madu Hutan Tersihir. Salad buah bercahaya. Efek: HP +130.", 130, 0, 0, 0, "Mendukung SDG 3: Kaya akan vitamin C dosis tinggi penyegar imun."),
            c(51, "Sereal Gandum Pagi", 160, "Resep: 3x Serat Gandum Kering dan 1x Kristal Air Murni. Sarapan sederhana dan ringan. Efek: HP +70.", 70, 0, 0, 0, "Mendukung SDG 3: Sarapan kaya kalsium penjaga kepadatan tulang."),
            c(52, "Sup Kuarsa Bening", 520, "Resep: 1x Batu Kuarsa Safir dan 3x Kristal Air Murni. Sup jernih dengan mineral halus. Efek: MP +120.", 0, 120, 0, 0, "Mendukung SDG 3: Elemen mineral murni penyeimbang cairan tubuh."),
            c(53, "Roti Lapis Ekstrak Biologis", 500, "Resep: 2x Serat Gandum Kering dan 1x Esensi Biologis Mutakhir. Roti lapis modern bernutrisi. Efek: HP +160, MP +100.", 160, 100, 0, 0, "Mendukung SDG 3: Senyawa biologis aktif pembasmi patogen ringan."),
            c(54, "Daging Asap Rempah Enath", 450, "Resep: 2x Darah Orc Warlord dan 1x Serbuk Mangan. Daging asap dengan rempah kuat. Efek: STR +55.", 0, 0, 55, 0, "Mendukung SDG 3: Rempah antimikroba alami melancarkan sirkulasi darah."),
            c(55, "Pie Buah Nymphadora", 490, "Resep: 3x Akar Ginseng Emas dan 1x Serat Gandum Kering. Pie buah yang lembut. Efek: STR +25.", 0, 0, 25, 0, "Mendukung SDG 3: Kandungan flavonoid tinggi pelindung sel jantung."),
            c(56, "Biskuit Tubuh Nirlelah", 500, "Resep: 1x Fragmen Tubuh Nirlelah dan 2x Serat Gandum Kering. Biskuit padat untuk daya tahan tinggi. Efek: HP +200, MP +150.", 200, 150, 0, 0, "Mendukung SDG 3: Bio regenerasi instan tingkat sel tubuh luar biasa."),
            c(57, "Ransum Taktis Black Owl", 620, "Resep: 2x Darah Monster dan 2x Serpihan Bintang. Ransum ringkas untuk operasi cepat. Efek: STR +30.", 0, 0, 30, 0, "Mendukung SDG 2: Suplemen energi penunjang fokus mental."),
            c(58, "Sup Ikan Laut Eldoria", 570, "Resep: 3x Sisik Ikan Mutan dan 1x Kristal Garam Kotor. Sup laut dengan rasa ringan. Efek: MP +80.", 0, 80, 0, 0, "Mendukung SDG 3: Pemanfaatan sumber daya laut lestari yang bergizi."),
            c(59, "Puding Sutra", 380, "Resep: 2x Kristal Air Murni dan 1x Madu Hutan Tersihir. Puding lembut yang menenangkan. Efek: MP +70.", 0, 70, 0, 0, "Mendukung SDG 3: Kudapan rendah kalori, penenang kecemasan saraf."),
            c(60, "Pesta Panen Vallesia", 500, "Resep: 5x Daun Teratai Salju dan 5x Darah Orc Warlord. Hidangan besar dari panen terbaik. Efek: HP +190, MP +180, STR +100, DEF +100.", 190, 180, 100, 100, "Mendukung SDG 2 & 3: Ketahanan pangan regional dengan nutrisi paripurna."),
    };

    private static final HashMap<Integer, Item> CONSUMABLE_MAP = initializeMap();

    private static HashMap<Integer, Item> initializeMap() {
        HashMap<Integer, Item> map = new HashMap<>();
        for (ConsumableFood food : DUMMY_CONSUMABLES) {
            map.put(food.getIdItem(), food);
        }
        return map;
    }

    private static ConsumableFood c(int id, String nama, int hargaJual, String deskripsi,
                                     int healHpAmount, int healMpAmount, int strBuff, int defBuff, String infoGiziSDG) {
        return new ConsumableFood(id, nama, hargaJual, deskripsi, itemType.CONSUMABLE, healHpAmount, healMpAmount, strBuff, defBuff, infoGiziSDG) {
            @Override
            public void useItem(GameCharacter target) {
                super.useItem(target);
            }
        };
    }

    public static List<ConsumableFood> getDummyConsumables() {
        return List.of(DUMMY_CONSUMABLES);
    }

    public static HashMap<Integer, Item> getDummyConsumablesMap() {
        return CONSUMABLE_MAP;
    }
}
