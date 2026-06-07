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

    public void sinkronisasiChapterTerbuka(int chapterAktif) {
    }

    public ArrayList<String> catatMusuhKalah(String namaMusuh) {
        ArrayList<String> catatan = new ArrayList<>();
        if (namaMusuh == null || daftarMainQuestAktif == null) {
            return catatan;
        }

        for (int i = daftarMainQuestAktif.size() - 1; i >= 0; i--) {
            MainQuest mq = daftarMainQuestAktif.get(i);
            if (mq == null || mq.getStatusQuest() != StatusQuest.ONGOING) {
                continue;
            }

            if (mq.membutuhkanMusuh(namaMusuh)) {
                mq.tambahProgress(1, "Mengalahkan " + namaMusuh);
                catatan.add("Quest naik: " + mq.getNamaQuest() + " = " + mq.getObjectiveProgress() + "/" + mq.getObjectiveTarget());

                if (mq.getStatusQuest() == StatusQuest.COMPLETED) {
                    // remove by index to avoid ConcurrentModificationException
                    daftarMainQuestAktif.remove(i);
                    if (riwayatMisiSelesai == null) {
                        riwayatMisiSelesai = new ArrayList<>();
                    }
                    riwayatMisiSelesai.add(mq);
                    catatan.add("Quest selesai: " + mq.getNamaQuest() + " — Kunjungi Quest Board untuk klaim hadiah!");
                }
            }
        }

        return catatan;
    }
}

