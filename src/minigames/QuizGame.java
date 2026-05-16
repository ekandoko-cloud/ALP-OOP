package minigames;
import java.util.*;
public class QuizGame extends MiniGame {
    private String[] arraySoalGizi;
    private String[] arrayJawaban;

    public QuizGame(String namaGame, int rewardKoin, String[] arraySoalGizi, String[] arrayJawaban) {
        super(namaGame, rewardKoin);
        this.namaGame = namaGame;
        this.rewardKoin = rewardKoin;
        this.arraySoalGizi = arraySoalGizi;
        this.arrayJawaban = arrayJawaban;
    }


    public String[] getArraySoalGizi() {
        return arraySoalGizi;
    }

    public void setArraySoalGizi(String[] arraySoalGizi) {
        this.arraySoalGizi = arraySoalGizi;
    }

    public String[] getArrayJawaban() {
        return arrayJawaban;
    }

    public void setArrayJawaban(String[] arrayJawaban) {
        this.arrayJawaban = arrayJawaban;
    }
}


