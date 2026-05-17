package models.quest;

public class MainQuest extends Quest {
    private int chapterTerbuka;
//    private String objectiveMainQuest;

    public MainQuest(int idQuest, String namaQuest, String deskripsiQuest, String objectiveMainQuest, int objectiveTarget, int hadiahKoin, int chapterTerbuka) {
        super(idQuest, namaQuest, deskripsiQuest/*, objectiveMainQuest*/, objectiveTarget, hadiahKoin);
        this.chapterTerbuka = chapterTerbuka;
//        this.objectiveMainQuest = objectiveMainQuest;
    }

//    public MainQuest(int idQuest, String namaQuest, int objectiveTarget, int hadiahKoin) {
//        this(idQuest, namaQuest, namaQuest, namaQuest, objectiveTarget, hadiahKoin, 1);
//    }

    public int getChapterTerbuka() {
        return chapterTerbuka;
    }

    public void setChapterTerbuka(int chapterTerbuka) {
        this.chapterTerbuka = chapterTerbuka;
    }

    /*public String getObjectiveMainQuest() {
        return objectiveMainQuest;
    }

    public void setObjectiveMainQuest(String objectiveMainQuest) {
        this.objectiveMainQuest = objectiveMainQuest;
        this.objectiveQuest = objectiveMainQuest;
    }*/

    public void catatObjectiveMainQuest(int progressTambahan, String catatan) {
        catatObjective(progressTambahan, "[MainQuest] " + catatan);
    }
}
