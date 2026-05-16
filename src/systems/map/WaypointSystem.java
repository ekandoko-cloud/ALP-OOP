package systems.map;
import java.util.*;
import models.location.Location;
public class WaypointSystem {
    private ArrayList<Location> lokasiTerbuka;
    private Location lokasiSaatIni;

    public WaypointSystem(ArrayList<Location> lokasiTerbuka, Location lokasiSaatIni) {
        this.lokasiTerbuka = lokasiTerbuka;
        this.lokasiSaatIni = lokasiSaatIni;
    }


    public ArrayList<Location> getLokasiTerbuka() {
        return lokasiTerbuka;
    }

    public void setLokasiTerbuka(ArrayList<Location> lokasiTerbuka) {
        this.lokasiTerbuka = lokasiTerbuka;
    }

    public Location getLokasiSaatIni() {
        return lokasiSaatIni;
    }

    public void setLokasiSaatIni(Location lokasiSaatIni) {
        this.lokasiSaatIni = lokasiSaatIni;
    }

    public void tambahLokasi(Location loc) {
    }

    public void teleport(Location loc) {
    }

    public void tampilkanDaftar() {
    }
}


