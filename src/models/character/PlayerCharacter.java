package models.character;

import models.item.Equipment;

public class PlayerCharacter extends GameCharacter {
    private int currentExp;
    private int maxExp;
    private String namaClass;
    private boolean statusTubuhNirlelah;
    protected int level;
    private Skill skill;
    private Equipment currentWeapon;
    private Equipment currentArmor;
    private Equipment currentAccessory;

    public PlayerCharacter(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
                           int currentExp, int maxExp, String namaClass, boolean statusTubuhNirlelah) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.level = level;
        this.maxMp = maxMp;
        this.currentMp = currentMp;
        this.currentExp = currentExp;
        this.maxExp = maxExp;
        this.namaClass = namaClass;
        this.statusTubuhNirlelah = statusTubuhNirlelah;
        this.currentWeapon = null;
        this.currentArmor = null;
        this.currentAccessory = null;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Equipment getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Equipment currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Equipment getCurrentArmor() {
        return currentArmor;
    }

    public void setCurrentArmor(Equipment currentArmor) {
        this.currentArmor = currentArmor;
    }

    public Equipment getCurrentAccessory() {
        return currentAccessory;
    }

    public void setCurrentAccessory(Equipment currentAccessory) {
        this.currentAccessory = currentAccessory;
    }

    public Equipment getEquipmentBySlot(String slot) {
        String normalizedSlot = normalizeSlot(slot);
        if (normalizedSlot == null) {
            return null;
        }

        switch (normalizedSlot) {
            case "WEAPON":
                return currentWeapon;
            case "ARMOR":
                return currentArmor;
            case "ACCESSORY":
                return currentAccessory;
            default:
                return null;
        }
    }

    public void setEquipmentBySlot(String slot, Equipment equipment) {
        String normalizedSlot = normalizeSlot(slot);
        if (normalizedSlot == null) {
            return;
        }

        switch (normalizedSlot) {
            case "WEAPON":
                currentWeapon = equipment;
                break;
            case "ARMOR":
                currentArmor = equipment;
                break;
            case "ACCESSORY":
                currentAccessory = equipment;
                break;
            default:
                break;
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
        return normalized;
    }

    public void tambahExp(int exp) {
        if (exp <= 0) return;
        this.currentExp += exp;
        while (this.currentExp >= this.maxExp) {
            this.currentExp -= this.maxExp;
            levelUp();
        }
    }

    public void levelUp() {
        this.level++;
        this.maxExp = this.level * 100;
        this.maxHp++;
        this.currentHp = this.maxHp;
        this.maxMp++;
        this.currentMp = this.maxMp;
        this.kekuatan++;
        this.defense++;
        System.out.println("LEVEL UP! " + this.nama + " sekarang level " + this.level + "!");
    }

    public void gunakanSkillUnik(GameCharacter target) {
        if (target == null) return;
        if (skill != null) {
            skill.gunakanSkill(this, target);
            return;
        }
        int damage = Math.max(1, (int) Math.round(getKekuatan() * 1.5) - target.getDefense());
        target.terimaDamage(damage);
    }
}


