package models.item;

import enums.ClassType;
import enums.EquipmentType;

public class Weapon extends Equipment {
    private int bonusKekuatan;
    private ClassType requiredClassType;

    public Weapon(int idItem, String namaItem, int hargaJual, String deskripsi, int bonusKekuatan, int levelTempa) {
        this(idItem, namaItem, hargaJual, deskripsi, bonusKekuatan, levelTempa, ClassType.CLASSLESS);
    }

    public Weapon(int idItem, String namaItem, int hargaJual, String deskripsi, int bonusKekuatan, int levelTempa, ClassType requiredClassType) {
        super(idItem, namaItem, hargaJual, deskripsi, EquipmentType.WEAPON, levelTempa);
        this.bonusKekuatan = bonusKekuatan;
        this.requiredClassType = requiredClassType == null ? ClassType.CLASSLESS : requiredClassType;
    }

    public int getBonusKekuatan() {
        return bonusKekuatan;
    }

    public void setBonusKekuatan(int bonusKekuatan) {
        this.bonusKekuatan = bonusKekuatan;
    }

    @Override
    public ClassType getRequiredClassType() {
        return requiredClassType;
    }

    public void setRequiredClassType(ClassType requiredClassType) {
        this.requiredClassType = requiredClassType == null ? ClassType.CLASSLESS : requiredClassType;
    }

    public int getBonusDefense() {
        return 0;
    }

    public void setBonusDefense(int bonusDefense) {
    }
}
