import java.util.ArrayList;

public class SkillNode {
    private String namaSkill;
    private String deskripsi;
    private int biayaGold;
    private boolean isUnlocked;
    private SkillNode parent;
    private ArrayList<SkillNode> children;

    public void unlock() {
    }

    public boolean isAvailable() {
        return true;
    }
}

