package models.item;

import enums.ClassType;
import enums.EquipmentType;

public class Armor extends Equipment {
    private int bonusDefense;
    private ClassType requiredClassType;

    public Armor(int idItem, String namaItem, int hargaJual, String deskripsi, int bonusDefense, int levelTempa) {
        this(idItem, namaItem, hargaJual, deskripsi, bonusDefense, levelTempa, ClassType.CLASSLESS);
    }

    public Armor(int idItem, String namaItem, int hargaJual, String deskripsi, int bonusDefense, int levelTempa, ClassType requiredClassType) {
        super(idItem, namaItem, hargaJual, deskripsi, EquipmentType.ARMOR, levelTempa);
        this.bonusDefense = bonusDefense;
        this.requiredClassType = requiredClassType == null ? ClassType.CLASSLESS : requiredClassType;
    }

    public int getBonusKekuatan() {
        return 0;
    }

    public void setBonusKekuatan(int bonusKekuatan) {
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }

    @Override
    public ClassType getRequiredClassType() {
        return requiredClassType;
    }

    public void setRequiredClassType(ClassType requiredClassType) {
        this.requiredClassType = requiredClassType == null ? ClassType.CLASSLESS : requiredClassType;
    }
}
