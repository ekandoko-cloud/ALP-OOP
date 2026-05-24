package systems.craft;

public class forgeFormula {
    private int level;
    private int materialAmount;
    private int atkIncrease;
    private int defIncrease;
    private String materialName;


    public forgeFormula(int level, int materialAmount, int atkIncrease, int defIncrease, String materialName) {
        this.level = level;
        this.materialAmount = materialAmount;
        this.atkIncrease = atkIncrease;
        this.defIncrease = defIncrease;
        this.materialName = materialName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(int materialAmount) {
        this.materialAmount = materialAmount;
    }

    public int getAtkIncrease() {
        return atkIncrease;
    }

    public void setAtkIncrease(int atkIncrease) {
        this.atkIncrease = atkIncrease;
    }

    public int getDefIncrease() {
        return defIncrease;
    }

    public void setDefIncrease(int defIncrease) {
        this.defIncrease = defIncrease;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
