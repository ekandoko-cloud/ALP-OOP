package models.character;
public class PlayerCharacter extends GameCharacter {
    private int currentExp;
    private int maxExp;
    private String namaClass;
    private boolean statusTubuhNirlelah;

    public PlayerCharacter(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
                           int currentExp, int maxExp, String namaClass, boolean statusTubuhNirlelah) {
        super(nama, maxHp, currentHp, kekuatan, defense, level);
        this.maxMp = maxMp;
        this.currentMp = currentMp;
        this.currentExp = currentExp;
        this.maxExp = maxExp;
        this.namaClass = namaClass;
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

    public String getNamaClass() {
        return namaClass;
    }

    public void setNamaClass(String namaEvolusiClass) {
        this.namaClass = namaEvolusiClass;
    }

    public boolean isStatusTubuhNirlelah() {
        return statusTubuhNirlelah;
    }

    public void setStatusTubuhNirlelah(boolean statusTubuhNirlelah) {
        this.statusTubuhNirlelah = statusTubuhNirlelah;
    }
}


