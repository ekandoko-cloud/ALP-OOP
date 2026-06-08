package models.character;

public class Monster extends GameCharacter {
    private String triviaPenyakit;
    private int xpDiberikan;

    public Monster(String nama, int maxHp, int currentHp, int kekuatan, int defense, String triviaPenyakit) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.triviaPenyakit = triviaPenyakit;
        this.xpDiberikan = 10;
    }

    public Monster(String nama, int maxHp, int currentHp, int kekuatan, int defense, String triviaPenyakit, int xpDiberikan) {
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
}


