package systems.classSystem;

import systems.classSystem.ClassNode;
import enums.*;
import java.util.*;
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


    public String getNamaClass() {
        return namaClass;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getSyaratLevel() {
        return syaratLevel;
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

    public ClassNode getParent() {
        return parent;
    }

    public ArrayList<ClassNode> getChildren() {
        return children;
    }
}


