package models.character;
import java.util.*;
public class Warrior extends PlayerCharacter {
	public Warrior(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
				   int currentExp, int maxExp, String namaEvolusiClass, boolean statusTubuhNirlelah) {
		super(nama, maxHp, currentHp, maxMp, currentMp, kekuatan, defense, level, currentExp, maxExp, namaEvolusiClass, statusTubuhNirlelah);
		this.nama = nama;
		this.maxHp = maxHp;
		this.currentHp = currentHp;
		this.maxMp = maxMp;
		this.currentMp = currentMp;
		this.kekuatan = kekuatan;
		this.defense = defense;
		this.level = level;
		this.setCurrentExp(currentExp);
		this.setMaxExp(maxExp);
		this.setNamaClass(namaEvolusiClass);
		this.setStatusTubuhNirlelah(statusTubuhNirlelah);
	}

}


