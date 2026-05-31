package systems.battle;

import java.util.ArrayList;

public class BattleLog {
    private ArrayList<ArrayList<String>> turnEntries;
    private int currentTurn;

    public BattleLog(ArrayList<String> historyLog) {
        this.turnEntries = new ArrayList<>();
        this.currentTurn = 1;
        this.turnEntries.add(new ArrayList<>());
    }

    public void nextTurn(int turn) {
        this.currentTurn = turn;
        while (this.turnEntries.size() < turn) {
            this.turnEntries.add(new ArrayList<>());
        }
    }

    public void tambahEntri(String teks) {
        if (teks != null) {
            while (this.turnEntries.size() < currentTurn) {
                this.turnEntries.add(new ArrayList<>());
            }
            this.turnEntries.get(currentTurn - 1).add(teks);
        }
    }

    public void tampilkanLog() {
        if (turnEntries.isEmpty() || (turnEntries.size() == 1 && turnEntries.get(0).isEmpty())) {
            System.out.println("(Log masih kosong)");
            return;
        }

        for (int t = 0; t < turnEntries.size(); t++) {
            ArrayList<String> entries = turnEntries.get(t);
            if (entries.isEmpty()) continue;
            System.out.println("=====================================================");
            System.out.println("Turn " + (t + 1));
            for (String entry : entries) {
                System.out.println("- " + entry);
            }
        }
        System.out.println("=====================================================");
    }

    public void bersihkan() {
        turnEntries.clear();
        turnEntries.add(new ArrayList<>());
        currentTurn = 1;
    }

    public ArrayList<String> getHistoryLog() {
        ArrayList<String> flat = new ArrayList<>();
        for (ArrayList<String> turn : turnEntries) {
            flat.addAll(turn);
        }
        return flat;
    }

    public void setHistoryLog(ArrayList<String> historyLog) {
        this.turnEntries.clear();
        this.turnEntries.add(new ArrayList<>());
        this.currentTurn = 1;
        if (historyLog != null) {
            this.turnEntries.get(0).addAll(historyLog);
        }
    }
}
