package models.character;
import java.util.*;
public class NPC extends GameCharacter {
    private String pangkat;
    private String unitTaktis;
    private String[] arrayDialog;

    public NPC(String nama, int maxHp, int currentHp, int kekuatan, int defense,
               String pangkat, String unitTaktis, String[] arrayDialog) {
        super(nama, maxHp, currentHp, kekuatan, defense);
        this.pangkat = pangkat;
        this.unitTaktis = unitTaktis;
        this.arrayDialog = arrayDialog;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getUnitTaktis() {
        return unitTaktis;
    }

    public void setUnitTaktis(String unitTaktis) {
        this.unitTaktis = unitTaktis;
    }

    public String[] getArrayDialog() {
        return arrayDialog;
    }

    public void setArrayDialog(String[] arrayDialog) {
        this.arrayDialog = arrayDialog;
    }
}


