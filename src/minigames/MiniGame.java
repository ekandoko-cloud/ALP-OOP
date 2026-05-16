package minigames;
import java.util.*;
public abstract class MiniGame {
    protected String namaGame;
    protected int rewardKoin;

    protected MiniGame(String namaGame, int rewardKoin) {
        this.namaGame = namaGame;
        this.rewardKoin = rewardKoin;
    }


    public String getNamaGame() {
        return namaGame;
    }

    public void setNamaGame(String namaGame) {
        this.namaGame = namaGame;
    }

    public int getRewardKoin() {
        return rewardKoin;
    }

    public void setRewardKoin(int rewardKoin) {
        this.rewardKoin = rewardKoin;
    }

    public void startGame() {
    }
}


