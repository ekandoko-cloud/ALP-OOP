package models.account;
import java.util.*;
import models.character.PlayerCharacter;
import models.item.Item;
import systems.quest.QuestTracker;

public class AccountProfile {
    private static final int MAX_PARTY_SIZE = 4;

    private String username;
    private String password;
    private int totalGold;
    private int totalPlaytime;
    private String areaName;
    private PlayerCharacter[] party;
    private LinkedList<Item> inventory;
    private QuestTracker questTracker;

    public AccountProfile(String username, String password, int totalGold, PlayerCharacter[] party, LinkedList<Item> inventory, QuestTracker questTracker) {
        this.username = username;
        this.password = password;
        this.totalGold = totalGold;
        this.totalPlaytime = 0;
        this.areaName = "";
        this.party = limitPartySize(party);
        this.inventory = inventory;
        this.questTracker = questTracker;
    }


    public PlayerCharacter[] getParty() {
        return party;
    }

    public void setParty(PlayerCharacter[] party) {
        this.party = limitPartySize(party);
    }

    private PlayerCharacter[] limitPartySize(PlayerCharacter[] party) {
        if (party == null) {
            return null;
        }

        if (party.length <= MAX_PARTY_SIZE) {
            return party;
        }

        return Arrays.copyOf(party, MAX_PARTY_SIZE);
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

    public LinkedList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(LinkedList<Item> inventory) {
        this.inventory = inventory;
    }

    public QuestTracker getQuestTracker() {
        return questTracker;
    }

    public void setQuestTracker(QuestTracker questTracker) {
        this.questTracker = questTracker;
    }

    public int getTotalPlaytime() {
        return totalPlaytime;
    }

    public void setTotalPlaytime(int totalPlaytime) {
        this.totalPlaytime = totalPlaytime;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}


