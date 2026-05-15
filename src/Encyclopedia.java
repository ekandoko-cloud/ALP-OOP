import java.util.HashMap;

public class Encyclopedia {
    private HashMap<String, Object> indexMonster;
    private HashMap<String, Object> indexMakanan;
    private HashMap<String, Object> indexLokasi;

    public Encyclopedia(HashMap<String, Object> indexMonster, HashMap<String, Object> indexMakanan, HashMap<String, Object> indexLokasi) {
        this.indexMonster = indexMonster;
        this.indexMakanan = indexMakanan;
        this.indexLokasi = indexLokasi;
    }


    public HashMap<String, Object> getIndexMonster() {
        return indexMonster;
    }

    public void setIndexMonster(HashMap<String, Object> indexMonster) {
        this.indexMonster = indexMonster;
    }

    public HashMap<String, Object> getIndexMakanan() {
        return indexMakanan;
    }

    public void setIndexMakanan(HashMap<String, Object> indexMakanan) {
        this.indexMakanan = indexMakanan;
    }

    public HashMap<String, Object> getIndexLokasi() {
        return indexLokasi;
    }

    public void setIndexLokasi(HashMap<String, Object> indexLokasi) {
        this.indexLokasi = indexLokasi;
    }

    public String cari(String keyword) {
        return null;
    }

    public void tambahEntri(String key, String value) {
    }
}

