package models.quest;
import java.util.*;
public class MainQuest extends Quest {
    private int chapterTerbuka;

    public MainQuest(String idQuest, String namaQuest, int targetKills, int hadiahKoin, int chapterTerbuka) {
        super(idQuest, namaQuest, targetKills, hadiahKoin);
        this.idQuest = idQuest;
        this.namaQuest = namaQuest;
        this.targetKills = targetKills;
        this.hadiahKoin = hadiahKoin;
        this.chapterTerbuka = chapterTerbuka;
    }


    public int getChapterTerbuka() {
        return chapterTerbuka;
    }

    public void setChapterTerbuka(int chapterTerbuka) {
        this.chapterTerbuka = chapterTerbuka;
    }
}


