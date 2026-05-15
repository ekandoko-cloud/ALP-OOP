public class StoryManager {
    private int chapterSaatIni;
    private String unitTaktisAktif;

    public StoryManager(int chapterSaatIni, String unitTaktisAktif) {
        this.chapterSaatIni = chapterSaatIni;
        this.unitTaktisAktif = unitTaktisAktif;
    }


    public int getChapterSaatIni() {
        return chapterSaatIni;
    }

    public void setChapterSaatIni(int chapterSaatIni) {
        this.chapterSaatIni = chapterSaatIni;
    }

    public String getUnitTaktisAktif() {
        return unitTaktisAktif;
    }

    public void setUnitTaktisAktif(String unitTaktisAktif) {
        this.unitTaktisAktif = unitTaktisAktif;
    }
}

