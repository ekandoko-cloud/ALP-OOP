package systems.gacha;
import java.util.*;
import models.item.Item;
import models.account.AccountProfile;
public class GachaSystem {
    private Item[] poolItem;
    private int[] bobotProbabilitas;

    public GachaSystem(Item[] poolItem, int[] bobotProbabilitas) {
        this.poolItem = poolItem;
        this.bobotProbabilitas = bobotProbabilitas;
    }


    public Item[] getPoolItem() {
        return poolItem;
    }

    public void setPoolItem(Item[] poolItem) {
        this.poolItem = poolItem;
    }

    public int[] getBobotProbabilitas() {
        return bobotProbabilitas;
    }

    public void setBobotProbabilitas(int[] bobotProbabilitas) {
        this.bobotProbabilitas = bobotProbabilitas;
    }

    public Item tarik(AccountProfile profil) {
        return null;
    }

    public Item[] tarikSepuluh(AccountProfile profil) {
        return null;
    }
}


