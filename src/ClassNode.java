import java.util.ArrayList;

public class ClassNode {
    private String namaClass;
    private String deskripsi;
    private int syaratLevel;
    private boolean isUnlocked;
    private ClassType tipeClass;
    private ClassNode parent;
    private ArrayList<ClassNode> children;

    public ClassNode(String namaClass, String deskripsi, int syaratLevel, boolean isUnlocked, ClassType tipeClass, ClassNode parent, ArrayList<ClassNode> children) {
        this.namaClass = namaClass;
        this.deskripsi = deskripsi;
        this.syaratLevel = syaratLevel;
        this.isUnlocked = isUnlocked;
        this.tipeClass = tipeClass;
        this.parent = parent;
        this.children = children;
    }


    public void unlock() {
    }

    public boolean isAvailable() {
        return true;
    }

    public String getNamaClass() {
        return namaClass;
    }

    public void setNamaClass(String namaClass) {
        this.namaClass = namaClass;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getSyaratLevel() {
        return syaratLevel;
    }

    public void setSyaratLevel(int syaratLevel) {
        this.syaratLevel = syaratLevel;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public ClassType getTipeClass() {
        return tipeClass;
    }

    public void setTipeClass(ClassType tipeClass) {
        this.tipeClass = tipeClass;
    }

    public ClassNode getParent() {
        return parent;
    }

    public void setParent(ClassNode parent) {
        this.parent = parent;
    }

    public ArrayList<ClassNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ClassNode> children) {
        this.children = children;
    }
}

