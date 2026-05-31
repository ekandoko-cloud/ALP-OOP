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

	@Override
	public void gunakanSkillUnik(GameCharacter target) {
		if (target == null) {
			return;
		}

		int damage = Math.max(1, (int) Math.round(getKekuatan() * 1.9) - (target.getDefense() / 2));
		target.terimaDamage(damage);
	}

}


