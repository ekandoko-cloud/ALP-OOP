package DummyData;

import models.character.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class monster {

    private static final ArrayList<Monster> DUMMY_MONSTERS = new ArrayList<>();

    static {
        DUMMY_MONSTERS.add(m("Starving Zombie", 56, 56, 12, 13, "Menderita Malnutrisi Energi Kronis (MEK) ekstrem yang menyebabkan penyusutan massa otot parah (Marasmus)."));
        DUMMY_MONSTERS.add(m("Starving Scavenger", 75, 75, 21, 6, "Mengalami kelaparan akut (Severe Acute Malnutrition) yang merusak sistem imun dan memicu perilaku agresif demi makanan."));
        DUMMY_MONSTERS.add(m("Decayed Wolf", 77, 77, 16, 8, "Terinfeksi bakteri Mycobacterium ulcerans yang menyebabkan Penyakit Buruli Ulcer, memicu kerusakan kulit dan jaringan lunak."));
        DUMMY_MONSTERS.add(m("Hollow Toad", 61, 61, 12, 13, "Menjadi inang cacing Schistosoma (Skistosomiasis) yang merusak organ dalam dan menyebabkan anemia berat."));
        DUMMY_MONSTERS.add(m("Crimson Mercenary", 50, 50, 21, 11, "Terinfeksi virus Ebola (Ebola Virus Disease) yang memicu demam hemoragik dan pendarahan internal hebat."));
        DUMMY_MONSTERS.add(m("Noxious Bat", 70, 70, 23, 14, "Sayapnya menyebarkan Henipavirus (Virus Nipah) yang dapat memicu ensefalitis (radang otak) akut pada manusia."));
        DUMMY_MONSTERS.add(m("Venomous Creeper", 61, 61, 20, 15, "Membawa neurotoksin dari gigitan ular berbisa (Envenoming), sebuah penyakit tropis terabaikan (NTD) yang mematikan."));
        DUMMY_MONSTERS.add(m("Vile Scavenger", 50, 50, 16, 8, "Menyebarkan bakteri Salmonella typhi penyebab Demam Tifoid akibat sanitasi lingkungan yang buruk."));
        DUMMY_MONSTERS.add(m("Skeletal Scavenger", 51, 51, 14, 13, "Mengalami Osteomalasia akut akibat defisiensi parah vitamin D dan kalsium dampak dari kerawanan pangan jangka panjang."));
        DUMMY_MONSTERS.add(m("Parasitic Bat", 64, 64, 22, 7, "Membawa protozoa Trypanosoma cruzi penyebab Penyakit Chagas yang menyerang fungsi jantung."));
        DUMMY_MONSTERS.add(m("Corrupt Vulture", 45, 45, 25, 9, "Terinfeksi virus Flu Burung (Highly Pathogenic Avian Influenza/H5N1) yang berpotensi memicu pandemi zoofilik."));
        DUMMY_MONSTERS.add(m("Infected Creeper", 80, 80, 18, 6, "Terinfeksi virus Hepatitis B yang menyerang sel hati dan dapat berkembang menjadi sirosis kronis."));
        DUMMY_MONSTERS.add(m("Noxious Slime", 52, 52, 25, 15, "Terkontaminasi limbah B3 (Merkuri/Raksa) yang memicu Penyakit Minamata, merusak sistem saraf pusat."));
        DUMMY_MONSTERS.add(m("Infected Rat", 72, 72, 15, 15, "Pembawa bakteri Leptospira (Leptospirosis) yang menular lewat urin tikus saat banjir atau sanitasi buruk."));
        DUMMY_MONSTERS.add(m("Mutated Bandit", 67, 67, 14, 15, "Mengalami dampak kesehatan mental berat (Depresi Mayor dan Kecemasan) akibat trauma konflik wilayah tak berkesudahan."));
        DUMMY_MONSTERS.add(m("Skeletal Beetle", 57, 57, 15, 13, "Terpapar mikotoksit Aflatoksin pada komoditas pangan yang dapat memicu kanker hati dan gangguan pertumbuhan."));
        DUMMY_MONSTERS.add(m("Skeletal Rat", 43, 43, 16, 10, "Membawa bakteri Yersinia pestis, patogen penyebab Wabah Pes (Plague) yang menyerang kelenjar getah bening."));
        DUMMY_MONSTERS.add(m("Parasitic Root", 46, 46, 20, 7, "Terinfeksi Jamur Karat Batang (Puccinia graminis) yang menghancurkan tanaman pangan pokok dan memicu krisis kelaparan."));
        DUMMY_MONSTERS.add(m("Vile Ghoul", 53, 53, 16, 14, "Terinfeksi bakteri Mycobacterium leprae penyebab Penyakit Kusta (Lepra) yang merusak saraf tepi dan kulit."));
        DUMMY_MONSTERS.add(m("Infected Zombie", 55, 55, 12, 6, "Terinfeksi bakteri Mycobacterium tuberculosis penyebab TBC, penyakit menular mematikan yang menyerang paru-paru."));
        DUMMY_MONSTERS.add(m("Venomous Mercenary", 93, 93, 35, 16, "Menggunakan senjata biologis berbasis racun Botulinum (Botulisme) yang melumpuhkan sistem saraf pernapasan."));
        DUMMY_MONSTERS.add(m("Crimson Creeper", 90, 90, 23, 12, "Terinfeksi virus Dengue (Demam Berdarah Dengue/DBD) stadium lanjut yang memicu kebocoran plasma darah."));
        DUMMY_MONSTERS.add(m("Infected Toad", 88, 88, 29, 17, "Terinfeksi penyakit Frambusia (Yaws), infeksi bakteri kulit kronis yang menyebabkan lesi melepuh di seluruh tubuh."));
        DUMMY_MONSTERS.add(m("Noxious Rat", 99, 99, 28, 11, "Terinfeksi Lassa Virus (Demam Lassa) yang ditularkan melalui kontak dengan makanan yang tercemar kotoran tikus."));
        DUMMY_MONSTERS.add(m("Starving Thief", 84, 84, 28, 11, "Mengalami busung lapar (Kwashiorkor) akibat defisiensi protein parah, ditandai dengan perut membusung akibat edema."));
        DUMMY_MONSTERS.add(m("Rabid Scavenger", 77, 77, 32, 20, "Terinfeksi Virus Rabies (Lyssavirus) stadium hidrofobia, memicu ensefalitis fatal dan agresi ekstrem."));
        DUMMY_MONSTERS.add(m("Crimson Mercenary", 72, 72, 35, 16, "Terinfeksi virus Marburg, menyebabkan demam berdarah parah dengan tingkat fatalitas hingga 88%."));
        DUMMY_MONSTERS.add(m("Noxious Toad", 96, 96, 34, 14, "Mengandung racun dari habitat perairan yang tercemar logam berat Timbal (Lead poisoning), merusak ginjal dan otak."));
        DUMMY_MONSTERS.add(m("Plague Scavenger", 75, 75, 24, 18, "Vektor penyebar kuman Anthrax (Bacillus anthracis) tipe kulit dan pernapasan yang bersumber dari bangkai ternak."));
        DUMMY_MONSTERS.add(m("Withered Zombie", 98, 98, 33, 11, "Mengalami gangguan wasting (tubuh terlalu kurus dibanding tinggi badan) akibat kelaparan berkepanjangan."));
        DUMMY_MONSTERS.add(m("Venomous Mercenary", 95, 95, 22, 19, "Mengidap infeksi sifilis sekunder (Treponema pallidum) yang tidak diobati, memicu luka dan ruam di seluruh tubuh."));
        DUMMY_MONSTERS.add(m("Crimson Wolf", 78, 78, 34, 19, "Terinfeksi Virus Zika yang ditularkan melalui vektor nyamuk, memicu demam dan gangguan saraf."));
        DUMMY_MONSTERS.add(m("Noxious Vulture", 105, 105, 33, 14, "Membawa virus Demam Lembah Rift (RVF), penyakit zoonosis yang menyerang hewan ternak dan manusia."));
        DUMMY_MONSTERS.add(m("Vile Scavenger", 85, 85, 34, 12, "Terinfeksi parasit usus Giardia duodenalis (Giardiasis) akibat mengonsumsi air yang terkontaminasi feses."));
        DUMMY_MONSTERS.add(m("Decayed Root", 81, 81, 35, 13, "Terpapar penyakit Hawar Daun Bakteri (Xanthomonas oryzae) yang merusak ketahanan pangan padi di wilayah agraris."));
        DUMMY_MONSTERS.add(m("Crimson Zombie", 96, 96, 25, 15, "Mengidap infeksi HIV stadium 4 (AIDS) dengan infeksi oportunistik parah karena tidak mendapat akses obat ARV."));
        DUMMY_MONSTERS.add(m("Rabid Scorpion", 108, 108, 31, 19, "Membawa virus Rabies aktif pada kelenjar racunnya, meningkatkan risiko penularan fatal lewat luka tusuk."));
        DUMMY_MONSTERS.add(m("Noxious Mercenary", 99, 99, 26, 19, "Mengalami PPOK (Penyakit Paru Obstruktif Kronis) parah akibat paparan polusi udara industri berbahaya tanpa proteksi."));
        DUMMY_MONSTERS.add(m("Noxious Mercenary", 89, 89, 34, 15, "Mengidap keracunan arsenik kronis (Arsenicosis) dari sumber air minum bawah tanah yang tidak tersaring."));
        DUMMY_MONSTERS.add(m("Venomous Scorpion", 101, 101, 34, 14, "Sengatannya menyuntikkan bisa yang memicu gagal jantung akut, salah satu penyebab kematian tertinggi non-menular."));
        DUMMY_MONSTERS.add(m("Blighted Beetle", 139, 139, 38, 22, "Membawa hama Ulat Grayak Frugiperda yang merusak ketahanan pangan jagung nasional secara masif."));
        DUMMY_MONSTERS.add(m("Starving Mercenary", 109, 109, 36, 18, "Mengalami kelaparan tersembunyi (Hidden Hunger) akibat defisiensi mikronutrien akut seperti zat besi dan yodium."));
        DUMMY_MONSTERS.add(m("Plague Shroom", 115, 115, 45, 19, "Jamur liar beracun jenis Amanita phalloides yang menyebabkan gagal hati total jika tidak sengaja terkonsumsi saat krisis pangan."));
        DUMMY_MONSTERS.add(m("Putrid Wasp", 114, 114, 33, 18, "Air liurnya memicu reaksi Anafilaksis (syok alergi berat) yang dapat menyumbat saluran pernapasan musuh dalam sekejap."));
        DUMMY_MONSTERS.add(m("Toxic Zombie", 129, 129, 33, 16, "Terkontaminasi limbah Kimia Dioksin, polutan organik persisten yang merusak sistem reproduksi dan imun tubuh."));
        DUMMY_MONSTERS.add(m("Noxious Wolf", 122, 122, 39, 25, "Terinfeksi virus Flu Babi (H1N1) mutasi baru yang menyerang organ pernapasan mamalia secara agresif."));
        DUMMY_MONSTERS.add(m("Parasitic Shroom", 109, 109, 37, 22, "Menyebarkan spora jamur Histoplasma capsulatum yang memicu infeksi paru-paru berat (Histoplasmosis) di area lembab."));
        DUMMY_MONSTERS.add(m("Skeletal Beetle", 108, 108, 39, 22, "Mengalami penyakit busuk buah (Erwinia) pada tanaman pangan yang diserangnya, memicu kegagalan panen lokal."));
        DUMMY_MONSTERS.add(m("Vile Creeper", 128, 128, 45, 20, "Kulitnya dipenuhi koloni bakteri Staphylococcus aureus yang kebal obat (MRSA), memicu infeksi bernanah parah."));
        DUMMY_MONSTERS.add(m("Infected Toad", 125, 125, 45, 25, "Membawa parasit Cryptosporidium di kulitnya, menyebabkan diare air akut yang berbahaya bagi anak-anak."));
        DUMMY_MONSTERS.add(m("Infected Scavenger", 103, 103, 32, 24, "Mengidap infeksi filariasis (Kaki Gajah) stadium lanjut akibat gigitan nyamuk Mansonia berulang kali."));
        DUMMY_MONSTERS.add(m("Parasitic Scavenger", 111, 111, 40, 21, "Terinfeksi cacing tambang (Ancylostomiasis) parah yang mengisap darah dan nutrisi dari dinding usus inangnya."));
        DUMMY_MONSTERS.add(m("Parasitic Zombie", 139, 139, 39, 19, "Terinfeksi cacing Guinea (Dracunculiasis) yang tumbuh hingga satu meter di dalam jaringan bawah kulit kaki."));
        DUMMY_MONSTERS.add(m("Venomous Rat", 136, 136, 37, 18, "Membawa virus Hantavirus Pulmonary Syndrome (HPS) yang menular lewat partikel kotoran tikus yang terhirup."));
        DUMMY_MONSTERS.add(m("Infected Hound", 136, 136, 45, 19, "Mengidap penyakit Kudis (Scabies) parah akibat infestasi tungau Sarcoptes scabiei karena kurangnya akses air bersih."));
        DUMMY_MONSTERS.add(m("Plague Rat", 110, 110, 42, 17, "Menjadi vektor utama penyebaran penyakit pes pneumonik, varian pes paling menular lewat udara (droplet)."));
        DUMMY_MONSTERS.add(m("Plague Hound", 136, 136, 45, 22, "Mengidap penyakit Brucellosis, infeksi bakteri menular yang menyerang organ reproduksi mamalia."));
        DUMMY_MONSTERS.add(m("Noxious Hound", 106, 106, 37, 19, "Terinfeksi bakteri Bordetella pertussis (Batuk Rejan) stadium lanjut, memicu serangan batuk parah yang merusak rusuk."));
        DUMMY_MONSTERS.add(m("Toxic Crow", 138, 138, 45, 18, "Membawa Virus West Nile (WNV) yang ditularkan lewat nyamuk ke burung, menyebabkan kegagalan sistem saraf."));
        DUMMY_MONSTERS.add(m("Rabid Scorpion", 118, 118, 37, 20, "Terinfeksi virus rabies laten yang membuat monster ini kehilangan rasa takut dan menyerang membabi buta."));
        DUMMY_MONSTERS.add(m("Blighted Hound", 162, 162, 42, 21, "Mengalami komplikasi Ulkus Diabetik yang membusuk akibat tidak adanya fasilitas pengelolaan penyakit tidak menular (PTM)."));
        DUMMY_MONSTERS.add(m("Hollow Shroom", 152, 152, 48, 25, "Terinfeksi jamur Claviceps purpurea (Ergotisme), racun tanaman pangan yang memicu halusinasi dan gangren pada manusia."));
        DUMMY_MONSTERS.add(m("Withered Ghoul", 158, 158, 51, 28, "Mengalami Dehidrasi Berat akibat komplikasi diare berulang tanpa penanganan oralit atau cairan IV yang memadai."));
        DUMMY_MONSTERS.add(m("Parasitic Bat", 150, 150, 49, 25, "Mengandung plasmodium penyebab Malaria Falciparum yang resisten terhadap obat artemisinin (Krisis Antimikroba)."));
        DUMMY_MONSTERS.add(m("Toxic Creeper", 164, 164, 53, 29, "Terpapar herbisida Parakuat dosis tinggi, racun pertanian berbahaya yang merusak paru-paru secara ireversibel."));
        DUMMY_MONSTERS.add(m("Skeletal Crow", 145, 145, 43, 29, "Membawa virus Flu Burung varian H7N9 yang memiliki tingkat fatalitas tinggi pada manusia yang tertular."));
        DUMMY_MONSTERS.add(m("Infected Bandit", 139, 139, 52, 30, "Mengidap infeksi bakteri Clostridium tetani (Tetanus) pada luka senjata berkarat akibat belum menerima imunisasi."));
        DUMMY_MONSTERS.add(m("Corrupt Toad", 158, 158, 51, 24, "Terkontaminasi limbah industri Kadmium (Penyakit Itai-itai) yang memicu pelunakan tulang dan gagal ginjal parah."));
        DUMMY_MONSTERS.add(m("Crimson Toad", 143, 143, 48, 21, "Kulitnya memancarkan racun dari Cyanobacteria (Algae Blown) akibat eutrofikasi perairan yang tercemar pupuk kimia."));
        DUMMY_MONSTERS.add(m("Corrupt Rat", 165, 165, 44, 27, "Membawa virus pernapasan akut parah (SARS-CoV), memicu sindrom gangguan pernapasan akut di wilayah padat."));
        DUMMY_MONSTERS.add(m("Diseased Slime", 133, 133, 42, 25, "Terbentuk dari akumulasi bakteri Vibrio cholerae penyebab penyakit Kolera epidemik akibat krisis air bersih."));
        DUMMY_MONSTERS.add(m("Rabid Bandit", 160, 160, 49, 28, "Manusia korban gigitan anjing rabies yang tidak mendapatkan Serum Anti Rabies (SAR) tepat waktu, kini di fase fatal."));
        DUMMY_MONSTERS.add(m("Plague Mercenary", 132, 132, 49, 23, "Mengidap Tuberkulosis Resisten Multi-Obat (MDR-TB) yang kebal terhadap antibiotik lini pertama."));
        DUMMY_MONSTERS.add(m("Plague Ghoul", 161, 161, 42, 30, "Terinfeksi virus demam kuning (Yellow Fever) akut yang merusak jaringan hati dan ginjal secara masif."));
        DUMMY_MONSTERS.add(m("Vile Shroom", 163, 163, 45, 22, "Terinfeksi patogen Blast Padi (Magnaporthe oryzae) yang mengancam ketersediaan stok beras dan memicu kelaparan."));
        DUMMY_MONSTERS.add(m("Crimson Zombie", 151, 151, 49, 28, "Mengalami Anemia Defisiensi Besi berat akibat malnutrisi kronis, ditandai dengan pucat dan hilangnya kekuatan otot."));
        DUMMY_MONSTERS.add(m("Noxious Toad", 147, 147, 52, 21, "Terpapar gas belerang dioksida (SO2) konsentrasi tinggi dari polusi industri udara, memicu asma bronkial parah."));
        DUMMY_MONSTERS.add(m("Plague Thief", 137, 137, 43, 26, "Membawa bakteri Shigella dysenteriae (Disentri Basiler) yang menular lewat makanan akibat kebersihan sanitasi buruk."));
        DUMMY_MONSTERS.add(m("Hollow Thief", 140, 140, 44, 22, "Mengalami gangguan kecanduan zat adiktif (Substance use disorder) akibat distribusi obat terlarang ilegal di area kumuh."));
        DUMMY_MONSTERS.add(m("Starving Slime", 153, 153, 44, 21, "Organisme parasit yang menguras nutrisi mikro tanah, menghambat sistem pertanian berkelanjutan."));
        DUMMY_MONSTERS.add(m("Diseased Creeper", 186, 186, 64, 27, "Mengidap infeksi Meningitis Meningokokus yang memicu peradangan akut pada selaput otak dan sumsum tulang belakang."));
        DUMMY_MONSTERS.add(m("Venomous Hound", 169, 169, 56, 31, "Air liurnya mengandung bakteri Pasteurella multocida pasca gigitan, memicu infeksi jaringan lunak yang fatal."));
        DUMMY_MONSTERS.add(m("Toxic Mercenary", 184, 184, 57, 31, "Mengalami asbestosis kronis akibat paparan serat asbes jangka panjang di tempat kerja tanpa regulasi K3 yang sehat."));
        DUMMY_MONSTERS.add(m("Mutated Wolf", 162, 162, 60, 26, "Terinfeksi virus Echinococcus (Kista Hidatid) yang ditularkan dari hewan liar, merusak fungsi organ hati."));
        DUMMY_MONSTERS.add(m("Mutated Shroom", 198, 198, 56, 35, "Terinfeksi penyakit busuk hitam (Alternaria) yang menurunkan produktivitas tanaman hortikultura secara global."));
        DUMMY_MONSTERS.add(m("Vile Ghoul", 197, 197, 52, 29, "Mengidap infeksi Helicobacter pylori kronis yang memicu kanker lambung akibat konsumsi air tercemar bakteri."));
        DUMMY_MONSTERS.add(m("Starving Beetle", 182, 182, 54, 31, "Mengalami kelaparan tersembunyi akibat hilangnya keanekaragaman hayati tanah pendukung nutrisi tanaman pangan."));
        DUMMY_MONSTERS.add(m("Blighted Zombie", 190, 190, 63, 34, "Terinfeksi virus cacar monyet (Mpox) stadium lanjut yang menyebabkan lesi makulopapular nyeri di seluruh tubuh."));
        DUMMY_MONSTERS.add(m("Vile Zombie", 180, 180, 53, 26, "Mengalami Sepsis Neonatal (infeksi darah berat) akibat melahirkan di lingkungan fasilitas kesehatan yang tidak higienis."));
        DUMMY_MONSTERS.add(m("Putrid Shroom", 174, 174, 65, 32, "Mengeluarkan mikotoksin Trichothecene yang sangat beracun bagi pernapasan pekerja pemanen di wilayah minim regulasi."));
        DUMMY_MONSTERS.add(m("Infected Ghoul", 188, 188, 59, 35, "Terinfeksi Schistosoma haematobium yang merusak kandung kemih dan meningkatkan risiko kanker urothelium."));
        DUMMY_MONSTERS.add(m("Hollow Ghoul", 186, 186, 60, 27, "Mengalami sindrom kelelahan kronis akibat infeksi laten virus Epstein-Barr (EBV) yang merusak pertahanan imun."));
        DUMMY_MONSTERS.add(m("Skeletal Toad", 165, 165, 61, 34, "Terpapar polusi suara (Noise pollution) tingkat tinggi di atas 85 dB secara terus-menerus, memicu gangguan kecemasan."));
        DUMMY_MONSTERS.add(m("Starving Toad", 199, 199, 56, 34, "Mengalami Stunting (kerdil) ekstrem akibat kegagalan pemenuhan gizi seimbang pada fase pertumbuhan awal kehidupan."));
        DUMMY_MONSTERS.add(m("Plague Beetle", 185, 185, 52, 26, "Membawa hama Kumbang Bubuk Gabah (Rhyzopertha dominica) yang menghancurkan stok lumbung pangan darurat daerah."));
        DUMMY_MONSTERS.add(m("Blighted Wolf", 175, 175, 52, 32, "Terinfeksi Leishmaniasis Kulit (ditularkan oleh lalat pasir), menyebabkan luka borok terbuka menahun yang sulit sembuh."));
        DUMMY_MONSTERS.add(m("Rabid Scorpion", 186, 186, 57, 35, "Terinfeksi virus rabies galur liar berkat minimnya program vaksinasi massal hewan penular rabies (HPR)."));
        DUMMY_MONSTERS.add(m("Parasitic Wolf", 185, 185, 61, 32, "Mengidap infeksi cacing pita babi (Taenia solium) yang bermigrasi menjadi neurosistiserkosis di dalam otak."));
        DUMMY_MONSTERS.add(m("Toxic Zombie", 176, 176, 61, 26, "Mengalami keracunan pestisida organofosfat akut akibat penggunaan bahan kimia pertanian tanpa standar keamanan WHO."));
        DUMMY_MONSTERS.add(m("Plague Slime", 190, 190, 56, 33, "Terbentuk dari genangan air limbah terbuka, menjadi sarang pembiakan nyamuk Anopheles penyebar Malaria."));

        // === QUEST-SPECIFIC MONSTERS ===

        // Valerion (Chapter 1) monsters
        DUMMY_MONSTERS.add(m("Scavenger Scout", 48, 48, 14, 10, "Pemulung rendah yang menyusup ke pemukiman mencari sisa makanan. Menyebarkan bakteri E. coli akibat kebersihan diri yang buruk."));
        DUMMY_MONSTERS.add(m("Corrupted Crawler", 44, 44, 12, 11, "Serangga raksasa yang terkontaminasi limbah pertanian beracun. Membawa residu pestisida organofosfat yang merusak saraf."));
        DUMMY_MONSTERS.add(m("Blight Spore", 42, 42, 11, 9, "Gumpalan spora terapung dari jamur yang terinfeksi Blight. Menyebarkan mikotoksin aflatoksin pemicu kanker hati."));
        DUMMY_MONSTERS.add(m("Scavenger Hunter", 55, 55, 16, 12, "Pemulung yang lebih agresif dan terorganisir. Terinfeksi bakteri Campylobacter dari daging bangkai yang dikonsumsinya."));

        // Aethelgard (Chapter 2) monsters
        DUMMY_MONSTERS.add(m("Swamplands Leech", 60, 60, 16, 11, "Lintah raksasa dari rawa beracun yang mengisap darah korbannya. Vektor penyakit Leptospirosis dari genangan air kotor."));
        DUMMY_MONSTERS.add(m("Miasma Husk", 68, 68, 18, 13, "Tubuh kering terbungkus kabut miasma beracun. Menyebabkan keracunan hidrogen sulfida akut pada makhluk di sekitarnya."));
        DUMMY_MONSTERS.add(m("Sludge Mutant", 72, 72, 17, 14, "Mutan yang terbentuk dari lumpur limbah industri. Terpapar logam berat merkuri penyebab penyakit Minamata."));

        // Grandis (Chapter 3) monsters
        DUMMY_MONSTERS.add(m("Security Drone", 78, 78, 22, 15, "Drone otomatis pengawal gudang pangan ilegal. Menyebarkan debu logam berat yang memicu penyakit paru okupasional."));
        DUMMY_MONSTERS.add(m("Enath Trooper", 85, 85, 25, 16, "Tentara bayaran bersenjata lengkap yang menjaga distribusi ilegal. Mengidap TBC resisten obat akibat lingkungan kerja tertutup."));
        DUMMY_MONSTERS.add(m("Elite Guard", 90, 90, 26, 18, "Pengawal pribadi Baron yang terlatih dan kejam. Menderita pneumonia kimia akibat sering terpapar gas air mata dan asap."));
        DUMMY_MONSTERS.add(m("Heavy Enath Trooper", 95, 95, 28, 20, "Tentara elit lapis baja dengan perlengkapan berat. Mengalami gangguan pendengaran permanen akibat baku tembak terus-menerus."));

        // Lumina (Chapter 4) monsters
        DUMMY_MONSTERS.add(m("Test Subject X", 105, 105, 28, 18, "Mantan manusia yang dijadikan kelinci percobaan vaksin gagal. Mengidap sindrom cytokine storm akibat over-reaksi imun."));
        DUMMY_MONSTERS.add(m("Alchemist Cultist", 110, 110, 30, 19, "Pengikut aliran sesat yang melakukan eksperimen bioteror. Terpapar patogen aerosol yang menyebabkan pneumonia berat."));
        DUMMY_MONSTERS.add(m("Failed Experiment", 115, 115, 32, 21, "Hasil percobaan genetika yang keluar dari laboratorium. Mengalami nekrosis jaringan akibat senyawa kimia eksperimental."));

        // Aldoria (Chapter 5) monsters
        DUMMY_MONSTERS.add(m("Ash Beast", 145, 145, 36, 24, "Binatang buas yang bertahan di area radioaktif. Mengidap acute radiation syndrome yang merusak sumsum tulang."));
        DUMMY_MONSTERS.add(m("Radiant Sentinel", 155, 155, 38, 26, "Makhluk penjaga yang menyatu dengan kristal radiasi. Menyebabkan kerusakan DNA seluler akibat paparan radiasi pengion."));
        DUMMY_MONSTERS.add(m("Flare Crawler", 150, 150, 40, 25, "Reptil bermutasi yang memancarkan panas ekstrem. Kulitnya mengandung zat radioaktif cesium-137 yang memicu kanker tiroid."));

        // === BOSS MONSTERS ===
        DUMMY_MONSTERS.add(m("Blight-Root", 120, 120, 28, 20, "BOSS: Akar raksasa yang menjadi sumber Blight di Valerion. Menyebabkan Sindrom Uremia Hemolitik melalui toksin yang meracuni sumber air."));
        DUMMY_MONSTERS.add(m("Goliath Toad", 160, 160, 35, 25, "BOSS: Katak raksasa mutan di rawa Aethelgard. Kulitnya mengeluarkan racun batrachotoxin yang memicu fibrilasi jantung fatal."));
        DUMMY_MONSTERS.add(m("Baron Gluttony", 200, 200, 45, 28, "BOSS: Penguasa kartel pangan ilegal Grandis. Menderita obesitas morbid dan diabetes tipe 2 akibat kerakusan tak terkendali."));
        DUMMY_MONSTERS.add(m("Dr. Mortis", 230, 230, 50, 32, "BOSS: Ilmuwan gila di balik vaksin palsu Lumina. Terinfeksi virus laboratorium hasil rekayasanya sendiri yang tak terkendali."));
        DUMMY_MONSTERS.add(m("Crimson Chimera", 280, 280, 60, 38, "BOSS FINAL: Makhluk gabungan hasil mutasi radiasi Aldoria. Menjadi inang segala penyakit yang pernah ada, siap menyebarkan wabah final."));
    }

    private static Monster m(String name, int maxHp, int currentHp, int kekuatan, int defense, String trivia) {
        int xp = Math.max(5, (maxHp / 10) + kekuatan * 2);
        return new Monster(name, maxHp, currentHp, kekuatan, defense, trivia, xp);
    }

    public static List<Monster> getDummyMonsters() {
        return new ArrayList<>(DUMMY_MONSTERS);
    }

    public static Monster[] getDummyMonstersArray() {
        return DUMMY_MONSTERS.toArray(new Monster[0]);
    }

    public static HashMap<Integer, Monster> getDummyMonstersMap() {
        HashMap<Integer, Monster> map = new HashMap<>();
        for (int i = 0; i < DUMMY_MONSTERS.size(); i++) {
            map.put(i + 1, DUMMY_MONSTERS.get(i));
        }
        return map;
    }
}
