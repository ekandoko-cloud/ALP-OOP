import java.util.Stack;

public class MapTraversal {
    private Stack<Location> riwayatArea;

    public MapTraversal() {
        this.riwayatArea = new Stack<>();
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

    public Location kembali() {
        if (riwayatArea.isEmpty()) return null;
        return riwayatArea.pop();
    }

    public Location areaSaatIni() {
        if (riwayatArea.isEmpty()) return null;
        return riwayatArea.peek();
    }
}

