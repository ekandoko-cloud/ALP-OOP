package models.item;

import enums.ItemType;
import enums.ClassType;
import enums.EquipmentType;
import models.character.PlayerCharacter;

public abstract class Equipment extends Item implements IEquippable {
    private EquipmentType equipmentType;
    private int levelTempa;

    protected Equipment(int idItem, String namaItem, int hargaJual, String deskripsi, EquipmentType equipmentType, int levelTempa) {
        super(idItem, namaItem, hargaJual, deskripsi, enums.ItemType.EQUIPMENT);
        this.equipmentType = equipmentType;
        this.levelTempa = levelTempa;
    }

    public EquipmentType getTipeEquipment() {
        return equipmentType;
    }

    public void setTipeEquipment(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getLevelTempa() {
        return levelTempa;
    }

    public void setLevelTempa(int levelTempa) {
        this.levelTempa = levelTempa;
    }

    public abstract int getBonusKekuatan();
    public abstract void setBonusKekuatan(int bonusKekuatan);
    public abstract int getBonusDefense();
    public abstract void setBonusDefense(int bonusDefense);
    public ClassType getRequiredClassType() {
        return ClassType.CLASSLESS;
    }

    public void equip(PlayerCharacter target) {
        equip(target, slotName());
    }

    public void equip(PlayerCharacter target, String slot) {
        if (target == null) {
            return;
        }

        String normalizedSlot = normalizeSlot(slot);
        if (normalizedSlot == null) {
            return;
        }

        ClassType requiredClassType = getRequiredClassType();
        if (requiredClassType != ClassType.CLASSLESS && getPlayerClassType(target) != requiredClassType) {
            System.out.println("[Equipment] Class tidak cocok untuk memakai item ini.");
            return;
        }

        Equipment equipped = target.getEquipmentBySlot(normalizedSlot);
        if (equipped != null && equipped.getIdItem() == this.getIdItem()) {
            return;
        }

        if (equipped != null) {
            target.setKekuatan(target.getKekuatan() - equipped.getBonusKekuatan());
            target.setDefense(target.getDefense() - equipped.getBonusDefense());
        }

        target.setEquipmentBySlot(normalizedSlot, this);
        target.setKekuatan(target.getKekuatan() + getBonusKekuatan());
        target.setDefense(target.getDefense() + getBonusDefense());
    }

    public void unequip(PlayerCharacter target) {
        unequip(target, slotName());
    }

    public void unequip(PlayerCharacter target, String slot) {
        if (target == null) {
            return;
        }

        String normalizedSlot = normalizeSlot(slot);
        if (normalizedSlot == null) {
            return;
        }

        Equipment equipped = target.getEquipmentBySlot(normalizedSlot);
        if (equipped == null) {
            return;
        }

        if (equipped.getIdItem() == this.getIdItem()) {
            target.setKekuatan(target.getKekuatan() - getBonusKekuatan());
            target.setDefense(target.getDefense() - getBonusDefense());
            target.setEquipmentBySlot(normalizedSlot, null);
        }
    }

    private String slotName() {
        return equipmentType == null ? "WEAPON" : equipmentType.name();
    }

    private ClassType getPlayerClassType(PlayerCharacter target) {
        if (target == null || target.getNamaClass() == null) {
            return ClassType.CLASSLESS;
        }

        try {
            return ClassType.valueOf(target.getNamaClass().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ClassType.CLASSLESS;
        }
    }

    private String normalizeSlot(String slot) {
        if (slot == null) {
            return null;
        }

        String normalized = slot.trim().toUpperCase();
        if (normalized.isEmpty()) {
            return null;
        }

        if (!normalized.equals("WEAPON") && !normalized.equals("ARMOR") && !normalized.equals("ACCESSORY")) {
            return null;
        }

        return normalized;
    }
}


