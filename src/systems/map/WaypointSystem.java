package systems.map;
import java.util.*;
import models.location.Location;

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

    public Location getLokasiSaatIni() {
        return lokasiSaatIni;
    }

    public void setLokasiSaatIni(Location lokasiSaatIni) {
        this.lokasiSaatIni = lokasiSaatIni;
    }

    public void tambahLokasi(Location loc) {
        if (loc == null) return;

        for (Location existing : lokasiTerbuka) {
            if (existing != null && existing.getNamaLokasi().equalsIgnoreCase(loc.getNamaLokasi())) {
                return;
            }
        }

        lokasiTerbuka.add(loc);
    }

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
}


