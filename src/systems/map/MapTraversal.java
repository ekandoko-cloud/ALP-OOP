package systems.map;

import java.util.*;
import models.location.Location;
import DummyData.kota;

/**
 * Stack-based map traversal system.
 *
 * Linear world path: Valerion -> Asgard -> Grandis -> LUmina -> Aldoria
 * The stack stores the sequence of areas the player has visited (bottom -> top).
 * New players start at Valerion. Moving forward pushes the next area onto the stack.
 * Moving back pops the stack to return to the previous area.
 */
public class MapTraversal {
    private Stack<Location> riwayatArea;

    private static final List<Location> LINEAR_LOCATIONS = kota.getDummyKota();

    public MapTraversal() {
        this.riwayatArea = new Stack<>();
        this.riwayatArea.push(LINEAR_LOCATIONS.get(0));
    }

    public MapTraversal(String startArea) {
        this.riwayatArea = new Stack<>();
        initializeFromAreaName(startArea);
    }

    public void initializeFromAreaName(String startArea) {
        if (startArea == null) startArea = LINEAR_LOCATIONS.get(0).getNamaLokasi();
        for (Location loc : LINEAR_LOCATIONS) {
            this.riwayatArea.push(loc);
            if (loc.getNamaLokasi().equalsIgnoreCase(startArea)) {
                return;
            }
        }
        this.riwayatArea.clear();
        this.riwayatArea.push(LINEAR_LOCATIONS.get(0));
    }

    public Stack<Location> getRiwayatArea() {
        return riwayatArea;
    }

    public void setRiwayatArea(Stack<Location> riwayatArea) {
        this.riwayatArea = riwayatArea;
    }

    public void pindahArea(Location area) {
        if (area != null) {
            riwayatArea.push(area);
        }
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

    // Move to a named area respecting the linear structure.
    // - If target is the next area: push it.
    // - If target is a previous area: pop until it becomes current.
    // - Otherwise (non-adjacent forward jump) returns false.
    public boolean goTo(String areaName) {
        if (areaName == null) return false;
        Location current = areaSaatIni();
        String curName = current == null ? null : current.getNamaLokasi();
        if (areaName.equalsIgnoreCase(curName)) return false;

        int targetIdx = indexOf(areaName);
        int curIdx = indexOf(curName);
        if (targetIdx == -1) return false;

        if (targetIdx == curIdx + 1) { // forward one step
            riwayatArea.push(LINEAR_LOCATIONS.get(targetIdx));
            return true;
        } else if (targetIdx < curIdx) { // go back: pop until target
            while (!riwayatArea.isEmpty() && !areaSaatIni().getNamaLokasi().equalsIgnoreCase(areaName)) {
                riwayatArea.pop();
            }
            return true;
        }
        // disallow multi-step forward jumps in this linear traversal implementation
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
}


