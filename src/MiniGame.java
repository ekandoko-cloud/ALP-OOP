public abstract class MiniGame {
    protected String namaGame;
    protected int rewardKoin;

    protected MiniGame() {
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

