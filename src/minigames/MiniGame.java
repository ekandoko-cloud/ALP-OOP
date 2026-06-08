package minigames;
import models.account.AccountProfile;

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

    public int getRewardKoin() {
        return rewardKoin;
    }

    public void startGame(AccountProfile currentProfile) {
    }
}


