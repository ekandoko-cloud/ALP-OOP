package models.character;

import java.util.*;

public class Monster extends GameCharacter {
    private String triviaPenyakit;

    public Monster(String nama, int maxHp, int currentHp, int kekuatan, int defense, String triviaPenyakit) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.triviaPenyakit = triviaPenyakit;
    }

    public String getTriviaPenyakit() {
        return triviaPenyakit;
    }

    public void setTriviaPenyakit(String triviaPenyakit) {
        this.triviaPenyakit = triviaPenyakit;
    }
}


