package systems.skill;
import java.util.*;
public class SkillTree {
    private SkillNode root;
    private int poinSkillTersedia;

    public SkillTree(SkillNode root, int poinSkillTersedia) {
        this.root = root;
        this.poinSkillTersedia = poinSkillTersedia;
    }


    public SkillNode getRoot() {
        return root;
    }

    public void setRoot(SkillNode root) {
        this.root = root;
    }

    public int getPoinSkillTersedia() {
        return poinSkillTersedia;
    }

    public void setPoinSkillTersedia(int poinSkillTersedia) {
        this.poinSkillTersedia = poinSkillTersedia;
    }

    public void unlockSkill(SkillNode node) {
    }

    public void traverse() {
    }
}


