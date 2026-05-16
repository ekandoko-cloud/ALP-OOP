package models.location;
import java.util.*;
public class Location {
    private String namaLokasi;
    private String deskripsiLokasi;

    public Location(String namaLokasi, String deskripsiLokasi) {
        this.namaLokasi = namaLokasi;
        this.deskripsiLokasi = deskripsiLokasi;
    }


    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public String getDeskripsiLokasi() {
        return deskripsiLokasi;
    }

    public void setDeskripsiLokasi(String deskripsiLokasi) {
        this.deskripsiLokasi = deskripsiLokasi;
    }
}


