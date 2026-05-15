public class PlayerCharacter extends GameCharacter {
    private int currentExp;
    private int maxExp;
    private String namaEvolusiClass;
    private boolean statusTubuhNirlelah;

    public PlayerCharacter() {
        super();
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

