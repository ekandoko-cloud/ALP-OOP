public class SaveLoadSystem {
    private String namaFile;

    public SaveLoadSystem(String namaFile) {
        this.namaFile = namaFile;
    }


    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public void save(AccountProfile profil) {
    }

    public AccountProfile load(String username) {
        return null;
    }
}

