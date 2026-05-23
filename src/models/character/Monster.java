package models.character;

import java.util.*;

public class Monster extends GameCharacter {
    private String idMonster;
    private String triviaPenyakit;

    public Monster(String nama, int maxHp, int currentHp, int kekuatan, int defense, int level, String idMonster, String triviaPenyakit) {
        super(nama, maxHp, currentHp, kekuatan, defense, level);
        this.idMonster = idMonster;
        this.triviaPenyakit = triviaPenyakit;
    }

    public String getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(String idMonster) {
        this.idMonster = idMonster;
    }

    public String getTriviaPenyakit() {
        return triviaPenyakit;
    }

    public void setTriviaPenyakit(String triviaPenyakit) {
        this.triviaPenyakit = triviaPenyakit;
    }
}


