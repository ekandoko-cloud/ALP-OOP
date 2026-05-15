import java.util.ArrayList;

public class BattleLog {
    private ArrayList<String> historyLog = new ArrayList<>();

    public void tambahEntri(String teks) {
        historyLog.add(teks);
    }

    public void tampilkanLog() {
        for (int i = 0; i < historyLog.size(); i++) {
            System.out.println(historyLog.get(i));
        }
    }

    public void bersihkan() {
        historyLog.clear();
    }

    public ArrayList<String> getHistoryLog() {
        return historyLog;
    }

    public void setHistoryLog(ArrayList<String> historyLog) {
        this.historyLog = historyLog;
    }
}

