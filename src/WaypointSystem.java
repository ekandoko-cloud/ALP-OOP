import java.util.ArrayList;

public class WaypointSystem {
    private ArrayList<Location> lokasiTerbuka;
    private Location lokasiSaatIni;

    public WaypointSystem() {
        this.lokasiTerbuka = new ArrayList<>();
        this.lokasiSaatIni = null;
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

