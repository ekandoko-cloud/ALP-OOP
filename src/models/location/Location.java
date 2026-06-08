package models.location;
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

    public String getDeskripsiLokasi() {
        return deskripsiLokasi;
    }
}


