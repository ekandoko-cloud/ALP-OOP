package models.item;

import enums.ItemType;
import models.character.GameCharacter;
public class ConsumableFood extends Item implements IConsumable {
    private int healHpAmount;
    private int healMpAmount;
    private int strBuff;
    private int defBuff;
    private String infoGiziSDG;

    public ConsumableFood(int idItem, String namaItem, int hargaJual, String deskripsi, ItemType itemType, int healHpAmount, int healMpAmount, int strBuff, int defBuff, String infoGiziSDG) {
        super(idItem, namaItem, hargaJual, deskripsi, itemType);
        this.healHpAmount = healHpAmount;
        this.healMpAmount = healMpAmount;
        this.strBuff = strBuff;
        this.defBuff = defBuff;
        this.infoGiziSDG = infoGiziSDG;
    }

    public int getHealHpAmount() {
        return healHpAmount;
    }

    public void setHealHpAmount(int healHpAmount) {
        this.healHpAmount = healHpAmount;
    }

    public int getHealMpAmount() {
        return healMpAmount;
    }

    public void setHealMpAmount(int healMpAmount) {
        this.healMpAmount = healMpAmount;
    }

    public int getStrBuff() {
        return strBuff;
    }

    public void setStrBuff(int strBuff) {
        this.strBuff = strBuff;
    }

    public int getDefBuff() {
        return defBuff;
    }

    public void setDefBuff(int defBuff) {
        this.defBuff = defBuff;
    }

    public String getInfoGiziSDG() {
        return infoGiziSDG;
    }

    public void setInfoGiziSDG(String infoGiziSDG) {
        this.infoGiziSDG = infoGiziSDG;
    }


    @Override
    public void consume(GameCharacter target) {
        if (target == null) {
            return;
        }

        target.setCurrentHp(Math.max(0, Math.min(target.getMaxHp(), target.getCurrentHp() + healHpAmount)));
        target.setCurrentMp(Math.max(0, Math.min(target.getMaxMp(), target.getCurrentMp() + healMpAmount)));
        target.setKekuatan(Math.max(0, target.getKekuatan() + strBuff));
        target.setDefense(Math.max(0, target.getDefense() + defBuff));
    }


}


