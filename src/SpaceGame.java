public class SpaceGame extends MiniGame {
    private int batasWaktuDetik;
    private int jumlahKetukanSpasi;

    public SpaceGame(String namaGame, int rewardKoin, int batasWaktuDetik, int jumlahKetukanSpasi) {
        super(namaGame, rewardKoin);
        this.namaGame = namaGame;
        this.rewardKoin = rewardKoin;
        this.batasWaktuDetik = batasWaktuDetik;
        this.jumlahKetukanSpasi = jumlahKetukanSpasi;
    }


    public int getBatasWaktuDetik() {
        return batasWaktuDetik;
    }

    public void setBatasWaktuDetik(int batasWaktuDetik) {
        this.batasWaktuDetik = batasWaktuDetik;
    }

    public int getJumlahKetukanSpasi() {
        return jumlahKetukanSpasi;
    }

    public void setJumlahKetukanSpasi(int jumlahKetukanSpasi) {
        this.jumlahKetukanSpasi = jumlahKetukanSpasi;
    }
}

