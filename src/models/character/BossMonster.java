package models.character;

public class BossMonster extends GameCharacter implements Skill {
    private String triviaPenyakit;
    private int xpDiberikan;

    public BossMonster(String nama, int maxHp, int currentHp, int kekuatan, int defense, String triviaPenyakit) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.triviaPenyakit = triviaPenyakit;
        this.xpDiberikan = 50;
    }

    public BossMonster(String nama, int maxHp, int currentHp, int kekuatan, int defense, String triviaPenyakit, int xpDiberikan) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.triviaPenyakit = triviaPenyakit;
        this.xpDiberikan = xpDiberikan;
    }

    public String getTriviaPenyakit() {
        return triviaPenyakit;
    }

    public void setTriviaPenyakit(String triviaPenyakit) {
        this.triviaPenyakit = triviaPenyakit;
    }

    public int getXpDiberikan() {
        return xpDiberikan;
    }

    public void setXpDiberikan(int xpDiberikan) {
        this.xpDiberikan = xpDiberikan;
    }

    @Override
    public int getXpReward() {
        return xpDiberikan;
    }

    @Override
    public void gunakanSkill(GameCharacter source, GameCharacter target) {
        if (target == null) {
            return;
        }

        int damage = Math.max(1, source.getKekuatan() + (source.getDefense() / 2) - target.getDefense());
        target.setCurrentHp(Math.max(0, target.getCurrentHp() - damage));
    }
}
