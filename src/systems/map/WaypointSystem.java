package systems.map;
import java.util.*;
import models.location.Location;
import java.util.Scanner;

/**
 * Waypoint System:
 * Menyimpan daftar area yang pernah dikunjungi pemain.
 * Memungkinkan pemain untuk teleport ke area yang sudah dibuka.
 * ArrayList digunakan untuk penyimpanan dinamis dengan akses O(1) via indeks.
 */
public class WaypointSystem {
    private ArrayList<Location> lokasiTerbuka;
    private Location lokasiSaatIni;

    public WaypointSystem() {
        this.lokasiTerbuka = new ArrayList<>();
        this.lokasiSaatIni = null;
    }

    public WaypointSystem(ArrayList<Location> lokasiTerbuka, Location lokasiSaatIni) {
        this.lokasiTerbuka = lokasiTerbuka != null ? lokasiTerbuka : new ArrayList<>();
        this.lokasiSaatIni = lokasiSaatIni;
    }

    public ArrayList<Location> getLokasiTerbuka() {
        return lokasiTerbuka;
    }

    public void setLokasiTerbuka(ArrayList<Location> lokasiTerbuka) {
        this.lokasiTerbuka = lokasiTerbuka != null ? lokasiTerbuka : new ArrayList<>();
    }

    public Location getLokasiSaatIni() {
        return lokasiSaatIni;
    }

    public void setLokasiSaatIni(Location lokasiSaatIni) {
        this.lokasiSaatIni = lokasiSaatIni;
    }

    /**
     * Menambahkan area baru ke daftar waypoint jika belum pernah dikunjungi.
     * @param loc - Area yang akan ditambahkan
     */
    public void tambahLokasi(Location loc) {
        if (loc == null) return;

        // Cek apakah area sudah ada di daftar
        for (Location existing : lokasiTerbuka) {
            if (existing != null && existing.getNamaLokasi().equalsIgnoreCase(loc.getNamaLokasi())) {
                return; // Area sudah ada, tidak perlu ditambahkan
            }
        }

        // Tambahkan area baru
        lokasiTerbuka.add(loc);
    }

    /**
     * Menampilkan daftar area yang telah dibuka (visited areas).
     */
    public void tampilkanDaftar() {
        if (lokasiTerbuka.isEmpty()) {
            System.out.println("Belum ada area yang dikunjungi.");
            return;
        }

        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║           DAFTAR WAYPOINT (Area Terbuka)           ║");
        System.out.println("╠════════════════════════════════════════════════════╣");

        for (int i = 0; i < lokasiTerbuka.size(); i++) {
            Location loc = lokasiTerbuka.get(i);
            if (loc != null) {
                String status = (loc.getNamaLokasi().equals(lokasiSaatIni != null ? lokasiSaatIni.getNamaLokasi() : ""))
                    ? " [CURRENT]" : "";
                System.out.println("║ [" + (i + 1) + "] " + String.format("%-44s", loc.getNamaLokasi() + status) + "║");
            }
        }

        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    /**
     * Teleport ke area (waypoint).
     * @param loc - Area tujuan
     */
    public void teleport(Location loc) {
        if (loc == null) return;

        // Validasi bahwa area ada di daftar waypoint
        for (Location wp : lokasiTerbuka) {
            if (wp != null && wp.getNamaLokasi().equalsIgnoreCase(loc.getNamaLokasi())) {
                this.lokasiSaatIni = loc;
                System.out.println("✓ Teleport berhasil! Anda sekarang berada di " + loc.getNamaLokasi());
                return;
            }
        }

        System.out.println("✗ Area tidak tersedia di waypoint Anda.");
    }
}


