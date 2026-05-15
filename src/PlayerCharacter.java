public class PlayerCharacter extends GameCharacter {
    private int currentExp;
    private int maxExp;
    private String namaEvolusiClass;
    private boolean statusTubuhNirlelah;

    public PlayerCharacter(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
                           int currentExp, int maxExp, String namaEvolusiClass, boolean statusTubuhNirlelah) {
        super(nama, maxHp, currentHp, maxMp, currentMp, kekuatan, defense, level);
        this.nama = nama;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.maxMp = maxMp;
        this.currentMp = currentMp;
        this.kekuatan = kekuatan;
        this.defense = defense;
        this.level = level;
        this.currentExp = currentExp;
        this.maxExp = maxExp;
        this.namaEvolusiClass = namaEvolusiClass;
        this.statusTubuhNirlelah = statusTubuhNirlelah;
    }


    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public String getNamaEvolusiClass() {
        return namaEvolusiClass;
    }

    public void setNamaEvolusiClass(String namaEvolusiClass) {
        this.namaEvolusiClass = namaEvolusiClass;
    }

    public boolean isStatusTubuhNirlelah() {
        return statusTubuhNirlelah;
    }

    public void setStatusTubuhNirlelah(boolean statusTubuhNirlelah) {
        this.statusTubuhNirlelah = statusTubuhNirlelah;
    }
}

