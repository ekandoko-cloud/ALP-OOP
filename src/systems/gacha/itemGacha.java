package systems.gacha;

import models.item.Equipment;

public class itemGacha {
    private Equipment equipment;
    private int probabilitas;
    private String rarity;

    public itemGacha(Equipment equipment, int probabilitas, String rarity) {
        this.equipment = equipment;
        this.probabilitas = probabilitas;
        this.rarity = rarity;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getProbabilitas() {
        return probabilitas;
    }

    public void setProbabilitas(int probabilitas) {
        this.probabilitas = probabilitas;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}


