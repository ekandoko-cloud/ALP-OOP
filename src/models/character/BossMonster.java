package models.character;

public class BossMonster extends GameCharacter implements Skill {
    private String triviaPenyakit;

    public BossMonster(String nama, int maxHp, int currentHp, int kekuatan, int defense, String triviaPenyakit) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.triviaPenyakit = triviaPenyakit;
    }

    public String getTriviaPenyakit() {
        return triviaPenyakit;
    }

    public void setTriviaPenyakit(String triviaPenyakit) {
        this.triviaPenyakit = triviaPenyakit;
    }

    @Override
    public void gunakanSkillUnik(GameCharacter target) {
        if (target == null) {
            return;
        }

        int damage = Math.max(1, getKekuatan() + (getDefense() / 2) - target.getDefense());
        target.setCurrentHp(Math.max(0, target.getCurrentHp() - damage));
    }
}
