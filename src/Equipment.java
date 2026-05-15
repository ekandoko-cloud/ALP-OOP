public class Equipment extends Item implements IEquippable {
    private String tipeEquipment;
    private int bonusKekuatan;
    private int bonusDefense;
    private int levelTempa;

    public Equipment(String idItem, String namaItem, int hargaJual, String deskripsi,
                     String tipeEquipment, int bonusKekuatan, int bonusDefense, int levelTempa) {
        super(idItem, namaItem, hargaJual, deskripsi);
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.hargaJual = hargaJual;
        this.deskripsi = deskripsi;
        this.tipeEquipment = tipeEquipment;
        this.bonusKekuatan = bonusKekuatan;
        this.bonusDefense = bonusDefense;
        this.levelTempa = levelTempa;
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

