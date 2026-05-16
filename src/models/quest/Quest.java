package models.quest;
import java.util.*;
public abstract class Quest {
    protected String idQuest;
    protected String namaQuest;
    protected int targetKills;
    protected int hadiahKoin;

    protected Quest(String idQuest, String namaQuest, int targetKills, int hadiahKoin) {
        this.idQuest = idQuest;
        this.namaQuest = namaQuest;
        this.targetKills = targetKills;
        this.hadiahKoin = hadiahKoin;
    }


    public String getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(String idQuest) {
        this.idQuest = idQuest;
    }

    public String getNamaQuest() {
        return namaQuest;
    }

    public void setNamaQuest(String namaQuest) {
        this.namaQuest = namaQuest;
    }

    public int getTargetKills() {
        return targetKills;
    }

    public void setTargetKills(int targetKills) {
        this.targetKills = targetKills;
    }

    public int getHadiahKoin() {
        return hadiahKoin;
    }

    public void setHadiahKoin(int hadiahKoin) {
        this.hadiahKoin = hadiahKoin;
    }

    public void cekStatusPenyelesaian() {
    }
}


