package models.account;
import java.util.*;
import models.character.PlayerCharacter;
public class AccountProfile {
    private String username;
    private String password;
    private int totalGold;
    //    private int totalWaktuMain;
    private PlayerCharacter[] party;

    public AccountProfile(String username, String password, int totalGold, PlayerCharacter[] party) {
        this.username = username;
        this.password = password;
        this.totalGold = totalGold;
        this.party = party;
    }


    public PlayerCharacter[] getParty() {
        return party;
    }

    public void setParty(PlayerCharacter[] party) {
        this.party = party;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }
}


