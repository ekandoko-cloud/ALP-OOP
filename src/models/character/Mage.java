package models.character;
import java.util.*;
public class Mage extends PlayerCharacter {
	public Mage(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
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

		if (getCurrentMp() < 10) {
			return;
		}

		setCurrentMp(getCurrentMp() - 10);
		int damage = Math.max(1, (int) Math.round(getKekuatan() * 2.1) - (target.getDefense() / 3));
		target.terimaDamage(damage);
	}

}


