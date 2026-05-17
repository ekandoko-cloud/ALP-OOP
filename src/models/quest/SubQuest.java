package models.quest;

public class SubQuest extends Quest {
    private int syaratLevel;
    //private int batasWaktu;
//    private String objectiveSubQuest;

    public SubQuest(int idQuest, String namaQuest, String deskripsiQuest, String objectiveSubQuest, int objectiveTarget, int hadiahKoin, int syaratLevel/*, int batasWaktu*/) {
        super(idQuest, namaQuest, deskripsiQuest/*, objectiveSubQuest*/, objectiveTarget, hadiahKoin);
        this.syaratLevel = syaratLevel;
        //this.batasWaktu = batasWaktu;
//        this.objectiveSubQuest = objectiveSubQuest;
    }

//    public SubQuest(int idQuest, String namaQuest, int objectiveTarget, int hadiahKoin, int syaratLevel/*, int batasWaktu*/) {
//        this(idQuest, namaQuest, namaQuest, namaQuest, objectiveTarget, hadiahKoin, syaratLevel/*, batasWaktu*/);
//    }

    public int getSyaratLevel() {
        return syaratLevel;
    }

    public void setSyaratLevel(int syaratLevel) {
        this.syaratLevel = syaratLevel;
    }

//    public int getBatasWaktu() {
//        return batasWaktu;
//    }
//
//    public void setBatasWaktu(int batasWaktu) {
//        this.batasWaktu = batasWaktu;
//    }

//    public String getObjectiveSubQuest() {
//        return objectiveSubQuest;
//    }
//
//    public void setObjectiveSubQuest(String objectiveSubQuest) {
//        this.objectiveSubQuest = objectiveSubQuest;
//        this.objectiveQuest = objectiveSubQuest;
//    }

    public void catatObjectiveSubQuest(int progressTambahan, String catatan) {
        catatObjective(progressTambahan, "[SubQuest] " + catatan);
    }
}
