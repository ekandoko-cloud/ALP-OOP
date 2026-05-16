package systems.quest;
import java.util.*;
import models.quest.MainQuest;
import models.quest.Quest;
import models.quest.SubQuest;
public class QuestTracker {
    private ArrayList<MainQuest> daftarMainQuest;
    private ArrayList<SubQuest> daftarSubQuestAktif;
    private ArrayList<Quest> riwayatMisiSelesai;

    public QuestTracker(ArrayList<MainQuest> daftarMainQuest, ArrayList<SubQuest> daftarSubQuestAktif, ArrayList<Quest> riwayatMisiSelesai) {
        this.daftarMainQuest = daftarMainQuest;
        this.daftarSubQuestAktif = daftarSubQuestAktif;
        this.riwayatMisiSelesai = riwayatMisiSelesai;
    }

    public ArrayList<MainQuest> getDaftarMainQuest() {
        return daftarMainQuest;
    }

    public void setDaftarMainQuest(ArrayList<MainQuest> daftarMainQuest) {
        this.daftarMainQuest = daftarMainQuest;
    }

    public ArrayList<SubQuest> getDaftarSubQuestAktif() {
        return daftarSubQuestAktif;
    }

    public void setDaftarSubQuestAktif(ArrayList<SubQuest> daftarSubQuestAktif) {
        this.daftarSubQuestAktif = daftarSubQuestAktif;
    }

    public ArrayList<Quest> getRiwayatMisiSelesai() {
        return riwayatMisiSelesai;
    }

    public void setRiwayatMisiSelesai(ArrayList<Quest> riwayatMisiSelesai) {
        this.riwayatMisiSelesai = riwayatMisiSelesai;
    }
}

