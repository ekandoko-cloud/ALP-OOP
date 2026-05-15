public class SubQuest extends Quest {
    private int syaratLevel;
    private int batasWaktu;

    public SubQuest(String idQuest, String namaQuest, int targetKills, int hadiahKoin, int syaratLevel, int batasWaktu) {
        super(idQuest, namaQuest, targetKills, hadiahKoin);
        this.idQuest = idQuest;
        this.namaQuest = namaQuest;
        this.targetKills = targetKills;
        this.hadiahKoin = hadiahKoin;
        this.syaratLevel = syaratLevel;
        this.batasWaktu = batasWaktu;
    }


    public int getSyaratLevel() {
        return syaratLevel;
    }

    public void setSyaratLevel(int syaratLevel) {
        this.syaratLevel = syaratLevel;
    }

    public int getBatasWaktu() {
        return batasWaktu;
    }

    public void setBatasWaktu(int batasWaktu) {
        this.batasWaktu = batasWaktu;
    }
}

