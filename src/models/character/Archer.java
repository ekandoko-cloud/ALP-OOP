package models.character;
import java.util.*;
public class Archer extends PlayerCharacter {
	public Archer(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
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

		int damage = Math.max(1, (int) Math.round(getKekuatan() * 1.6) - (int) Math.round(target.getDefense() * 0.25));
		target.terimaDamage(damage);
	}

}


