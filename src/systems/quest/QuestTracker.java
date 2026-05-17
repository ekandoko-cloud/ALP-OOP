package systems.quest;
import java.util.*;
import models.quest.MainQuest;
import models.quest.Quest;
import models.quest.SubQuest;
public class QuestTracker {
    private ArrayList<MainQuest> daftarMainQuestAktif;
    private ArrayList<SubQuest> daftarSubQuestAktif;
    private ArrayList<Quest> riwayatMisiSelesai;

    public QuestTracker(ArrayList<MainQuest> daftarMainQuest, ArrayList<SubQuest> daftarSubQuestAktif, ArrayList<Quest> riwayatMisiSelesai) {
        this.daftarMainQuestAktif = daftarMainQuest;
        this.daftarSubQuestAktif = daftarSubQuestAktif;
        this.riwayatMisiSelesai = riwayatMisiSelesai;
    }

    public ArrayList<MainQuest> getDaftarMainQuestAktif() {
        return daftarMainQuestAktif;
    }

    public void setDaftarMainQuestAktif(ArrayList<MainQuest> daftarMainQuest) {
        this.daftarMainQuestAktif = daftarMainQuest;
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

