package models.character;
import java.util.*;
public class Monster extends GameCharacter {
    private String idMonster;
    private String triviaPenyakit;

    public Monster(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
                   String idMonster, String triviaPenyakit) {
        super(nama, maxHp, currentHp, maxMp, currentMp, kekuatan, defense, level);
        this.nama = nama;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.maxMp = maxMp;
        this.currentMp = currentMp;
        this.kekuatan = kekuatan;
        this.defense = defense;
        this.level = level;
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


