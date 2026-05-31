package models.character;
public abstract class GameCharacter {
    protected String nama;
    protected int maxHp;
    protected int currentHp;
    protected int maxMp;
    protected int currentMp;
    protected int kekuatan;
    protected int defense;
    protected boolean defending;

    protected GameCharacter(String nama, int maxHp, int currentHp, int kekuatan, int defense) {
        this.nama = nama;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.kekuatan = kekuatan;
        this.defense = defense;
        this.defending = false;
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

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public int getXpReward() {
        return 0;
    }

    public int serang(GameCharacter target) {
        if (target == null || !isAlive()) {
            return 0;
        }

        int damage = Math.max(1, this.kekuatan - target.getDefense());
        return target.terimaDamage(damage);
    }

    public int defend() {
        this.defending = true;
        return 0;
    }

    public int terimaDamage(int damage) {
        if (damage < 0) {
            damage = 0;
        }

        int actualDamage = damage;
        if (this.defending) {
            actualDamage = Math.max(1, (int) Math.ceil(actualDamage * 0.5));
            this.defending = false;
        }

        this.currentHp = Math.max(0, this.currentHp - actualDamage);
        return actualDamage;
    }

    public void modifikasiStat(int hp, int mp, int atk, int def) {
        setMaxHp(Math.max(1, this.maxHp + hp));
        setCurrentHp(Math.max(0, Math.min(this.maxHp, this.currentHp + hp)));
        setMaxMp(Math.max(0, this.maxMp + mp));
        setCurrentMp(Math.max(0, Math.min(this.maxMp, this.currentMp + mp)));
        setKekuatan(Math.max(0, this.kekuatan + atk));
        setDefense(Math.max(0, this.defense + def));
    }
}


