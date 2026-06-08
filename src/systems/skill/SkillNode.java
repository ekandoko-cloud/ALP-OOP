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

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getBiayaGold() {
        return biayaGold;
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

    public ArrayList<SkillNode> getChildren() {
        return children;
    }

    public boolean isAvailable() {
        return parent == null || parent.isUnlocked;
    }
}


