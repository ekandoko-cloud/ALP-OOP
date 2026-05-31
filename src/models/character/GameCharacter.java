package models.character;
public abstract class GameCharacter {
    protected String nama;
    protected int maxHp;
    protected int currentHp;
    protected int maxMp;
    protected int currentMp;
    protected int kekuatan;
    protected int defense;

    protected GameCharacter(String nama, int maxHp, int currentHp, int kekuatan, int defense) {
        this.nama = nama;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.kekuatan = kekuatan;
        this.defense = defense;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getCurrentMp() {
        return currentMp;
    }

    public void setCurrentMp(int currentMp) {
        this.currentMp = currentMp;
    }

    public int getKekuatan() {
        return kekuatan;
    }

    public void setKekuatan(int kekuatan) {
        this.kekuatan = kekuatan;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void serang(GameCharacter target) {
    }

    public void modifikasiStat(int hp, int mp, int atk, int def) {
    }
}


