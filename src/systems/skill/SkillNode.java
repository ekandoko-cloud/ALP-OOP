package systems.skill;
import java.util.*;
public class SkillNode {
    private String namaSkill;
    private String deskripsi;
    private int biayaGold;
    private boolean isUnlocked;
    private SkillNode parent;
    private ArrayList<SkillNode> children;

    public SkillNode(String namaSkill, String deskripsi, int biayaGold, boolean isUnlocked, SkillNode parent, ArrayList<SkillNode> children) {
        this.namaSkill = namaSkill;
        this.deskripsi = deskripsi;
        this.biayaGold = biayaGold;
        this.isUnlocked = isUnlocked;
        this.parent = parent;
        this.children = children;
    }


    public String getNamaSkill() {
        return namaSkill;
    }

    public void setNamaSkill(String namaSkill) {
        this.namaSkill = namaSkill;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getBiayaGold() {
        return biayaGold;
    }

    public void setBiayaGold(int biayaGold) {
        this.biayaGold = biayaGold;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public SkillNode getParent() {
        return parent;
    }

    public void setParent(SkillNode parent) {
        this.parent = parent;
    }

    public ArrayList<SkillNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SkillNode> children) {
        this.children = children;
    }

    public void unlock() {
        this.isUnlocked = true;
    }

    public boolean isAvailable() {
        return parent == null || parent.isUnlocked;
    }
}


