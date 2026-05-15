public class Equipment extends Item implements IEquippable {
    private String tipeEquipment;
    private int bonusKekuatan;
    private int bonusDefense;
    private int levelTempa;

    public Equipment() {
        super();
    }


    public String getTipeEquipment() {
        return tipeEquipment;
    }

    public void setTipeEquipment(String tipeEquipment) {
        this.tipeEquipment = tipeEquipment;
    }

    public int getBonusKekuatan() {
        return bonusKekuatan;
    }

    public void setBonusKekuatan(int bonusKekuatan) {
        this.bonusKekuatan = bonusKekuatan;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }

    public int getLevelTempa() {
        return levelTempa;
    }

    public void setLevelTempa(int levelTempa) {
        this.levelTempa = levelTempa;
    }

    public void equip(PlayerCharacter target) {
    }

    public void unequip(PlayerCharacter target) {
    }
}

