public class ConsumableFood extends Item implements IConsumable {
    private int healHpAmount;
    private int healMpAmount;
    private int tempStrBuff;
    private int tempDefBuff;
    private String infoGiziSDG;

    public ConsumableFood(int healHpAmount, int healMpAmount, int tempStrBuff, int tempDefBuff, String infoGiziSDG) {
        this.healHpAmount = healHpAmount;
        this.healMpAmount = healMpAmount;
        this.tempStrBuff = tempStrBuff;
        this.tempDefBuff = tempDefBuff;
        this.infoGiziSDG = infoGiziSDG;
    }

    public void consume(GameCharacter target) {
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

    public int getTempStrBuff() {
        return tempStrBuff;
    }

    public void setTempStrBuff(int tempStrBuff) {
        this.tempStrBuff = tempStrBuff;
    }

    public int getTempDefBuff() {
        return tempDefBuff;
    }

    public void setTempDefBuff(int tempDefBuff) {
        this.tempDefBuff = tempDefBuff;
    }

    public String getInfoGiziSDG() {
        return infoGiziSDG;
    }

    public void setInfoGiziSDG(String infoGiziSDG) {
        this.infoGiziSDG = infoGiziSDG;
    }
}

