package models.quest;

import java.util.ArrayList;

import enums.StatusQuest;

public abstract class Quest {
    protected int idQuest;
    protected String namaQuest;
    protected String deskripsiQuest;

    protected String objectiveQuest;
    protected int objectiveTarget;
    protected int objectiveProgress;

    protected int hadiahKoin;
    protected StatusQuest statusQuest;
    protected ArrayList<String> riwayatObjective;

    protected Quest(int idQuest, String namaQuest, String deskripsiQuest, String objectiveQuest, int objectiveTarget, int hadiahKoin) {
        this.idQuest = idQuest;
        this.namaQuest = namaQuest;
        this.deskripsiQuest = deskripsiQuest;
        this.objectiveQuest = objectiveQuest;
        this.objectiveTarget = objectiveTarget;
        this.objectiveProgress = 0;
        this.hadiahKoin = hadiahKoin;
        this.statusQuest = StatusQuest.BELUM_DIAMBIL;
        this.riwayatObjective = new ArrayList<>();
    }

    protected Quest(int idQuest, String namaQuest, String deskripsiQuest, int objectiveTarget, int hadiahKoin) {
        this(idQuest, namaQuest, deskripsiQuest, deskripsiQuest, objectiveTarget, hadiahKoin);
    }

    public int getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(int idQuest) {
        this.idQuest = idQuest;
    }

    public String getNamaQuest() {
        return namaQuest;
    }

    public void setNamaQuest(String namaQuest) {
        this.namaQuest = namaQuest;
    }

    public String getDeskripsiQuest() {
        return deskripsiQuest;
    }

    public void setDeskripsiQuest(String deskripsiQuest) {
        this.deskripsiQuest = deskripsiQuest;
    }

    public String getObjectiveQuest() {
        return objectiveQuest;
    }

    public void setObjectiveQuest(String objectiveQuest) {
        this.objectiveQuest = objectiveQuest;
    }

    public int getObjectiveTarget() {
        return objectiveTarget;
    }

    public void setObjectiveTarget(int objectiveTarget) {
        this.objectiveTarget = objectiveTarget;
    }

    public int getObjectiveProgress() {
        return objectiveProgress;
    }

    public void setObjectiveProgress(int objectiveProgress) {
        this.objectiveProgress = objectiveProgress;
        cekStatusPenyelesaian();
    }

    public int getHadiahKoin() {
        return hadiahKoin;
    }

    public void setHadiahKoin(int hadiahKoin) {
        this.hadiahKoin = hadiahKoin;
    }

    public StatusQuest getStatusQuest() {
        return statusQuest;
    }

    public void setStatusQuest(StatusQuest statusQuest) {
        this.statusQuest = statusQuest;
    }

    public ArrayList<String> getRiwayatObjective() {
        return riwayatObjective;
    }

    public void setRiwayatObjective(ArrayList<String> riwayatObjective) {
        this.riwayatObjective = riwayatObjective;
    }

    public void catatObjective(int progressTambahan, String catatan) {
        this.objectiveProgress += progressTambahan;

        if (this.objectiveProgress < 0) {
            this.objectiveProgress = 0;
        }

        if (this.objectiveProgress > this.objectiveTarget) {
            this.objectiveProgress = this.objectiveTarget;
        }

        this.riwayatObjective.add(catatan + " (" + this.objectiveProgress + "/" + this.objectiveTarget + ")");
        cekStatusPenyelesaian();
    }

    public void cekStatusPenyelesaian() {
        if (this.objectiveProgress >= this.objectiveTarget) {
            this.statusQuest = StatusQuest.COMPLETED;
        }
    }
}
