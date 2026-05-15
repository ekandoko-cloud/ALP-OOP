public abstract class GameCharacter {
    protected String nama;
    protected int maxHp;
    protected int currentHp;
    protected int maxMp;
    protected int currentMp;
    protected int kekuatan;
    protected int defense;
    protected int level;

    public void serang(GameCharacter target) {
    }

    public void gunakanSkillUnik(GameCharacter target) {
    }

    public void modifikasiStat(int hp, int mp, int atk, int def) {
    }
}

