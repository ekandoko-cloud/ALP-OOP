import java.util.ArrayList;

public class QuestTracker {
    private ArrayList<MainQuest> daftarMainQuest;
    private ArrayList<SubQuest> daftarSubQuestAktif;
    private ArrayList<Quest> riwayatMisiSelesai;

    public QuestTracker() {
        this.daftarMainQuest = new ArrayList<>();
        this.daftarSubQuestAktif = new ArrayList<>();
        this.riwayatMisiSelesai = new ArrayList<>();
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
