package systems.quest;
import java.util.*;
import models.quest.MainQuest;
import models.quest.Quest;
import models.quest.SubQuest;
import enums.StatusQuest;
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

    public ArrayList<String> catatMusuhKalah(String namaMusuh) {
        ArrayList<String> log = new ArrayList<>();
        if (namaMusuh == null || daftarMainQuestAktif == null) {
            return log;
        }
        for (MainQuest mq : daftarMainQuestAktif) {
            if (mq == null) continue;
            if (mq.getStatusQuest() != StatusQuest.ONGOING) continue;
            if (mq.membutuhkanMusuh(namaMusuh)) {
                mq.tambahProgress(1, "Kalahkan " + namaMusuh);
                log.add("Progress: " + mq.getNamaQuest() + " (" + mq.getObjectiveProgress() + "/" + mq.getObjectiveTarget() + ")");
                if (mq.getStatusQuest() == StatusQuest.COMPLETED) {
                    if (riwayatMisiSelesai == null) {
                        riwayatMisiSelesai = new ArrayList<>();
                    }
                    boolean sudahAdaDiRiwayat = false;
                    for (Quest q : riwayatMisiSelesai) {
                        if (q != null && q.getIdQuest() == mq.getIdQuest()) {
                            sudahAdaDiRiwayat = true;
                            break;
                        }
                    }
                    if (!sudahAdaDiRiwayat) {
                        riwayatMisiSelesai.add(mq);
                        log.add("Quest selesai: " + mq.getNamaQuest() + " - ambil hadiah di Quest Board!");
                    }
                }
            }
        }
        return log;
    }
}

