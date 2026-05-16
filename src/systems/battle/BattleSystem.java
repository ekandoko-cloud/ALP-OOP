package systems.battle;
import java.util.*;
import models.character.Monster;
import models.character.PlayerCharacter;
public class BattleSystem {
    private PlayerCharacter[] partyPlayer;
    private Monster[] partyEnemy;
    //private int turnSekarang;
    private BattleLog battleLog;

    public BattleSystem(PlayerCharacter[] partyPlayer, Monster[] partyEnemy, BattleLog battleLog) {
        this.partyPlayer = partyPlayer;
        this.partyEnemy = partyEnemy;
        this.battleLog = battleLog;
    }


    public PlayerCharacter[] getPartyPlayer() {
        return partyPlayer;
    }

    public void setPartyPlayer(PlayerCharacter[] partyPlayer) {
        this.partyPlayer = partyPlayer;
    }

    public Monster[] getPartyEnemy() {
        return partyEnemy;
    }

    public void setPartyEnemy(Monster[] partyEnemy) {
        this.partyEnemy = partyEnemy;
    }

    public BattleLog getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(BattleLog battleLog) {
        this.battleLog = battleLog;
    }
}


