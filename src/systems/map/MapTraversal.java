package systems.map;

import java.util.*;
import models.location.Location;
import models.quest.MainQuest;
import models.quest.Quest;
import DummyData.kota;

public class MapTraversal {
    private Stack<Location> riwayatArea;

    private static final List<Location> LINEAR_LOCATIONS = kota.getDummyKota();

    private static final int[][] AREA_QUEST_ID_RANGES = {
        {1, 5},
        {6, 10},
        {11, 15},
        {16, 20},
        {21, 25}
    };


    public MapTraversal() {
        this.riwayatArea = new Stack<>();
        this.riwayatArea.push(LINEAR_LOCATIONS.getFirst());
    }

    public MapTraversal(String startArea) {
        this.riwayatArea = new Stack<>();
        initializeFromAreaName(startArea);
    }

    public void initializeFromAreaName(String startArea) {
        if (startArea == null) startArea = LINEAR_LOCATIONS.getFirst().getNamaLokasi();
        for (Location loc : LINEAR_LOCATIONS) {
            this.riwayatArea.push(loc);
            if (loc.getNamaLokasi().equalsIgnoreCase(startArea)) {
                return;
            }
        }
        this.riwayatArea.clear();
        this.riwayatArea.push(LINEAR_LOCATIONS.getFirst());
    }

    public Stack<Location> getRiwayatArea() {
        return riwayatArea;
    }

    public boolean goToNext() {
        Location current = areaSaatIni();
        if (current == null) return false;
        int idx = indexOf(current.getNamaLokasi());
        if (idx >= 0 && idx < LINEAR_LOCATIONS.size() - 1) {
            Location nextLoc = LINEAR_LOCATIONS.get(idx + 1);
            riwayatArea.push(nextLoc);
            return true;
        }
        return false;
    }

    public boolean goTo(String areaName) {
        if (areaName == null) return false;
        Location current = areaSaatIni();
        String curName = current == null ? null : current.getNamaLokasi();
        if (areaName.equalsIgnoreCase(curName)) return false;

        int targetIdx = indexOf(areaName);
        int curIdx = indexOf(curName);
        if (targetIdx == -1) return false;

        if (targetIdx == curIdx + 1) {
            riwayatArea.push(LINEAR_LOCATIONS.get(targetIdx));
            return true;
        } else if (targetIdx < curIdx) {
            while (!riwayatArea.isEmpty() && !areaSaatIni().getNamaLokasi().equalsIgnoreCase(areaName)) {
                riwayatArea.pop();
            }
            return true;
        }
        return false;
    }

    public Location kembali() {
        if (riwayatArea.isEmpty()) return null;
        if (riwayatArea.size() == 1) return riwayatArea.peek();
        return riwayatArea.pop();
    }

    public Location areaSaatIni() {
        if (riwayatArea.isEmpty()) return null;
        return riwayatArea.peek();
    }

    private int indexOf(String name) {
        if (name == null) return -1;
        for (int i = 0; i < LINEAR_LOCATIONS.size(); i++) {
            if (LINEAR_LOCATIONS.get(i).getNamaLokasi().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    public static int[] getQuestIdRangeForArea(String areaName) {
        if (areaName == null) return null;
        for (int i = 0; i < LINEAR_LOCATIONS.size(); i++) {
            if (LINEAR_LOCATIONS.get(i).getNamaLokasi().equalsIgnoreCase(areaName)) {
                return AREA_QUEST_ID_RANGES[i];
            }
        }
        return null;
    }

    public int[] getQuestIdRangeForCurrentArea() {
        Location current = areaSaatIni();
        if (current == null) return null;
        return getQuestIdRangeForArea(current.getNamaLokasi());
    }


    public static int countCompletedQuestsInRange(List<Quest> completedQuests, int startId, int endId) {
        if (completedQuests == null) return 0;
        int count = 0;
        for (Quest q : completedQuests) {
            if (q instanceof MainQuest) {
                int id = q.getIdQuest();
                if (id >= startId && id <= endId) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean areAllQuestsInRangeCompleted(List<Quest> completedQuests, int startId, int endId) {
        return countCompletedQuestsInRange(completedQuests, startId, endId) >= (endId - startId + 1);
    }
}


