package models.character;

public class Support extends PlayerCharacter {
	public Support(String nama, int maxHp, int currentHp, int maxMp, int currentMp, int kekuatan, int defense, int level,
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

		int healHp = Math.max(1, (int) Math.round(target.getMaxHp() * 0.30));
		int healMp = Math.max(1, (int) Math.round(target.getMaxMp() * 0.20));
		target.setCurrentHp(Math.min(target.getMaxHp(), target.getCurrentHp() + healHp));
		target.setCurrentMp(Math.min(target.getMaxMp(), target.getCurrentMp() + healMp));
	}

}


