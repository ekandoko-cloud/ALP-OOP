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

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }

    @Override
    public int getBonusKekuatan() {
        return 0;
    }

    @Override
    public void setBonusKekuatan(int bonusKekuatan) {
    }

    @Override
    public ClassType getRequiredClassType() {
        return requiredClassType;
    }
}
