package models.item;

import enums.EquipmentType;

public class Accessory extends Equipment {
    private int bonusKekuatan;
    private int bonusDefense;

    public Accessory(int idItem, String namaItem, int hargaJual, String deskripsi, int bonusKekuatan, int bonusDefense, int levelTempa) {
        super(idItem, namaItem, hargaJual, deskripsi, EquipmentType.ACCESSORY, levelTempa);
        this.bonusKekuatan = bonusKekuatan;
        this.bonusDefense = bonusDefense;
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
}
