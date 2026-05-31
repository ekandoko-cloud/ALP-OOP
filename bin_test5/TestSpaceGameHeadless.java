import java.util.LinkedList;
import minigames.SpaceGame;
import models.account.AccountProfile;
public class TestSpaceGameHeadless {
    public static void main(String[] args) {
        AccountProfile profile = new AccountProfile("tester", "pw", 0, null, new LinkedList<>(), null);
        SpaceGame game = new SpaceGame();
        game.setBatasWaktuDetik(1);
        game.startGame(profile);
        System.out.println("COUNT=" + game.getJumlahKetukanSpasi());
        System.out.println("GOLD=" + profile.getTotalGold());
    }
}