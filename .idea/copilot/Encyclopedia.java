import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Encyclopedia {
    private final HashMap<String, Object> indexMonster = new HashMap<>();
    private final HashMap<String, Object> indexMakanan = new HashMap<>();
    private final HashMap<String, Object> indexLokasi = new HashMap<>();
    private final HashMap<String, Object> indexItem = new HashMap<>();
    private final HashMap<String, Object> indexResep = new HashMap<>();
    private final HashMap<String, Object> indexForge = new HashMap<>();
    private final HashMap<String, Object> indexUtama = new HashMap<>();

    private int totalEntri;

    public void tambahEntri(Object entri) {
        String key = keyOf(entri);
        if (key == null) return;
        simpanKeKategori(key, entri);
        indexUtama.put(key, entri);
        totalEntri++;
    }

    public void tambahEntriAlias(String alias, Object entri) {
        String key = normalize(alias);
        if (key != null && entri != null) indexUtama.put(key, entri);
    }

    public String cari(String keyword) {
        String key = normalize(keyword);
        if (key == null) {
            System.out.println("[Ensiklopedia] Masukkan kata kunci terlebih dahulu.");
            return null;
        }

        Object hasil = indexUtama.get(key);
        if (hasil != null) {
            tampilkanDetailFor(hasil);
            return extractNama(hasil);
        }

        ArrayList<String> saran = new ArrayList<>();
        for (String k : indexUtama.keySet()) if (k.contains(key)) saran.add(k);
        tampilkanTidakDitemukan(keyword, saran);
        return null;
    }

    public String cariDiKategori(String keyword, String kategori) {
        String key = normalize(keyword);
        if (key == null) {
            System.out.println("[Ensiklopedia] Masukkan kata kunci terlebih dahulu.");
            return null;
        }

        Object hasil = mapKategori(kategori).get(key);
        if (hasil != null) {
            tampilkanDetailFor(hasil);
            return extractNama(hasil);
        }

        System.out.println("[Ensiklopedia] '" + keyword + "' tidak ditemukan di kategori " + kategori + ".");
        return null;
    }

    public void tampilkanDaftarKategori(String kategori) {
        HashMap<String, Object> target = mapKategori(kategori);
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.printf("║  📚 %-43s║%n", namaKategori(kategori));
        System.out.printf("║  Total entri: %-37d║%n", target.size());
        System.out.println("╠═════════════════════════════════════════════════════╣");
        if (target.isEmpty()) {
            System.out.println("║  (Belum ada entri di kategori ini)                  ║");
        } else {
            int no = 1;
            for (Object entri : target.values()) {
                System.out.printf("║  %3d. %-47s║%n", no++, extractNama(entri));
            }
        }
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public void tampilkanMenuEnsiklopedia() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║            📚  ENSIKLOPEDIA NUTRITALE              ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.printf("║  Total entri tersedia: %-28d║%n", totalEntri);
        System.out.println("╠═════════════════════════════════════════════════════╣");
        ringkasan("👾", indexMonster.size(), "Monster");
        ringkasan("🍎", indexMakanan.size(), "Makanan & Bahan");
        ringkasan("⚔", indexItem.size(), "Equipment");
        ringkasan("🗺", indexLokasi.size(), "Lokasi & Kota");
        ringkasan("📖", indexResep.size(), "Resep Consumable");
        ringkasan("🔨", indexForge.size(), "Formula Tempa");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Cari berdasarkan nama                          ║");
        System.out.println("║  [2] Jelajahi berdasarkan kategori                  ║");
        System.out.println("║  [0] Keluar                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
    }

    public void bukaEnsiklopedia() {
        Scanner sc = new Scanner(System.in);
        boolean aktif = true;
        while (aktif) {
            tampilkanMenuEnsiklopedia();
            System.out.print("  Pilihan: ");
            switch (sc.nextLine().trim()) {
                case "1" -> {
                    System.out.print("  🔍 Kata kunci: ");
                    cari(sc.nextLine().trim());
                }
                case "2" -> bukaKategori(sc);
                case "0" -> aktif = false;
                default -> System.out.println("  ⚠ Pilihan tidak valid.");
            }
        }
    }

    private void bukaKategori(Scanner sc) {
        System.out.println();
        System.out.println("  Pilih kategori: [monster / makanan / item / lokasi / resep / forge / semua]");
        System.out.print("  Kategori: ");
        String kategori = sc.nextLine().trim();
        tampilkanDaftarKategori(kategori);
        System.out.println("  Masukkan nama entri untuk detail (atau Enter untuk kembali):");
        System.out.print("  Nama: ");
        String nama = sc.nextLine().trim();
        if (!nama.isEmpty()) cari(nama);
    }

    private void simpanKeKategori(String key, Object entri) {
        if (entri instanceof models.character.Monster) indexMonster.put(key, entri);
        else if (entri instanceof models.item.Equipment) indexItem.put(key, entri);
        else if (entri instanceof models.item.ConsumableFood || entri instanceof models.item.Inqredients) indexMakanan.put(key, entri);
        else if (entri instanceof systems.craft.craftingRecipe) indexResep.put(key, entri);
        else if (entri instanceof systems.craft.forgeFormula) indexForge.put(key, entri);
        else if (entri instanceof models.location.Location) indexLokasi.put(key, entri);
    }

    private HashMap<String, Object> mapKategori(String kategori) {
        if (kategori == null) return indexUtama;
        return switch (kategori.trim().toUpperCase()) {
            case "MONSTER" -> indexMonster;
            case "MAKANAN", "CONSUMABLE" -> indexMakanan;
            case "LOKASI" -> indexLokasi;
            case "EQUIPMENT", "ITEM" -> indexItem;
            case "RESEP" -> indexResep;
            case "FORGE" -> indexForge;
            case "SEMUA" -> indexUtama;
            default -> indexUtama;
        };
    }

    private String namaKategori(String kategori) {
        if (kategori == null) return "📚 Semua Entri";
        return switch (kategori.trim().toUpperCase()) {
            case "MONSTER" -> "👾 Monster";
            case "MAKANAN", "CONSUMABLE" -> "🍎 Makanan & Bahan";
            case "LOKASI" -> "🗺  Lokasi & Kota";
            case "EQUIPMENT", "ITEM" -> "⚔  Equipment";
            case "RESEP" -> "📖 Resep Consumable";
            case "FORGE" -> "🔨 Formula Tempa";
            default -> "📚 Semua Entri";
        };
    }

    private String keyOf(Object o) {
        if (o instanceof models.character.Monster) return normalize(((models.character.Monster) o).getNama());
        if (o instanceof models.item.Item) return normalize(((models.item.Item) o).getNamaItem());
        if (o instanceof systems.craft.craftingRecipe) return normalize(((systems.craft.craftingRecipe) o).getRecipeName());
        if (o instanceof systems.craft.forgeFormula) {
            systems.craft.forgeFormula f = (systems.craft.forgeFormula) o;
            return normalize("tempa: " + f.getMaterialName() + " lv." + f.getLevel());
        }
        if (o instanceof models.location.Location) return normalize(((models.location.Location) o).getNamaLokasi());
        return null;
    }

    private String normalize(String value) {
        return value == null ? null : value.toLowerCase().trim();
    }

    private void tampilkanDetailFor(Object o) {
        if (o instanceof models.character.Monster) {
            models.character.Monster m = (models.character.Monster) o;
            panel("👾  [MONSTER]  " + m.getNama(), () -> {
                field("ID", m.getIdMonster());
                field("HP", m.getCurrentHp() + "/" + m.getMaxHp());
                field("MP", m.getCurrentMp() + "/" + m.getMaxMp());
                field("ATK", m.getKekuatan());
                field("DEF", m.getDefense());
                field("Level", m.getLevel());
                field("Trivia", m.getTriviaPenyakit());
            });
            return;
        }

        if (o instanceof models.item.Equipment) {
            models.item.Equipment e = (models.item.Equipment) o;
            panel("⚔  [EQUIPMENT]  " + e.getNamaItem(), () -> {
                field("Tipe", e.getTipeEquipment());
                field("ATK Bonus", e.getBonusKekuatan() + " poin");
                field("DEF Bonus", e.getBonusDefense() + " poin");
                field("Level Tempa", e.getLevelTempa() + " (maks)");
                field("Deskripsi", e.getDeskripsi());
            });
            return;
        }

        if (o instanceof models.item.ConsumableFood) {
            models.item.ConsumableFood c = (models.item.ConsumableFood) o;
            panel("🍎  [CONSUMABLE]  " + c.getNamaItem(), () -> {
                field("Pulih HP", "+" + c.getHealHpAmount() + " HP");
                field("Pulih MP", "+" + c.getHealMpAmount() + " MP");
                field("Info Gizi", c.getInfoGiziSDG());
                field("Deskripsi", c.getDeskripsi());
            });
            return;
        }

        if (o instanceof models.item.Inqredients) {
            models.item.Inqredients i = (models.item.Inqredients) o;
            panel("🌿  [INGREDIENT]  " + i.getNamaItem(), () -> {
                field("Deskripsi", i.getDeskripsi());
                field("Harga Jual", i.getHargaJual() + " gold");
            });
            return;
        }

        if (o instanceof systems.craft.craftingRecipe) {
            systems.craft.craftingRecipe r = (systems.craft.craftingRecipe) o;
            panel("📖  [RESEP]  " + r.getRecipeName(), () -> {
                field("Hasil", r.getResultItem() != null ? r.getResultItem().getNamaItem() : "-");
                System.out.println("║  ── Bahan-Bahan: ──────────────────────────────────  ║");
                int no = 1;
                if (r.getRequiredIngredients() != null) {
                    for (systems.craft.craftingRecipe.IngredientReq req : r.getRequiredIngredients()) {
                        String bahan = req.getIngredient().getNamaItem() + " x" + req.getAmount();
                        System.out.printf("║    %d. %-47s║%n", no++, wrap(bahan, 47));
                    }
                }
            });
            return;
        }

        if (o instanceof systems.craft.forgeFormula) {
            systems.craft.forgeFormula f = (systems.craft.forgeFormula) o;
            panel("🔨  [FORGE]  Tempa Lv." + f.getLevel() + " - " + f.getMaterialName(), () -> {
                field("Material", f.getMaterialName());
                field("Jumlah", f.getMaterialAmount());
                field("ATK+", f.getAtkIncrease());
                field("DEF+", f.getDefIncrease());
            });
            return;
        }

        if (o instanceof models.location.Location) {
            models.location.Location l = (models.location.Location) o;
            panel("🗺  [LOKASI]  " + l.getNamaLokasi(), () -> field("Deskripsi", l.getDeskripsiLokasi()));
            return;
        }

        System.out.println(o);
        System.out.println();
    }

    private void panel(String judul, Runnable isi) {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.printf("║  %-49s║%n", judul);
        System.out.println("╠═════════════════════════════════════════════════════╣");
        isi.run();
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void field(String label, Object value) {
        System.out.printf("║  %-18s : %-31s║%n", label, wrap(String.valueOf(value), 31));
    }

    private void tampilkanTidakDitemukan(String keyword, ArrayList<String> saran) {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║  ❌  Entri Tidak Ditemukan                          ║");
        System.out.printf("║  Keyword: %-43s║%n", wrap(keyword, 43));
        if (!saran.isEmpty()) {
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println("║  Mungkin maksud kamu:                               ║");
            for (int i = 0; i < Math.min(saran.size(), 5); i++) {
                System.out.printf("║    • %-47s║%n", extractNama(indexUtama.get(saran.get(i))));
            }
        }
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private String extractNama(Object o) {
        if (o == null) return "-";
        if (o instanceof models.character.Monster) return ((models.character.Monster) o).getNama();
        if (o instanceof models.item.Item) return ((models.item.Item) o).getNamaItem();
        if (o instanceof systems.craft.craftingRecipe) return ((systems.craft.craftingRecipe) o).getRecipeName();
        if (o instanceof systems.craft.forgeFormula) {
            systems.craft.forgeFormula f = (systems.craft.forgeFormula) o;
            return "Tempa: " + f.getMaterialName() + " Lv." + f.getLevel();
        }
        if (o instanceof models.location.Location) return ((models.location.Location) o).getNamaLokasi();
        return o.toString();
    }

    private void ringkasan(String icon, int total, String label) {
        System.out.printf("║  %s  %-3d entri  %-38s║%n", icon, total, label);
    }

    private String wrap(String s, int max) {
        if (s == null) return "-";
        return s.length() <= max ? s : s.substring(0, max - 3) + "...";
    }
}
