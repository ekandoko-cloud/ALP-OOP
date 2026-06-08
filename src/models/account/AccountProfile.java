package models.account;

import java.util.*;

import models.character.PlayerCharacter;
import models.item.Item;
import models.location.Location;
import systems.quest.QuestTracker;

public class AccountProfile {
    private static final int MAX_PARTY_SIZE = 4;
    public static final int DEFAULT_MAX_INVENTORY_SLOTS = 200;

    private String username;
    private String password;
    private int totalGold;
    private String areaName;
    private PlayerCharacter[] party;
    private LinkedList<Item> inventory;
    private QuestTracker questTracker;
    private int maxInventorySlots = DEFAULT_MAX_INVENTORY_SLOTS;
    private ArrayList<String> unlockedSkillNames = new ArrayList<>();
    private ArrayList<String> statusLokasi = new ArrayList<>();

    public AccountProfile(String username, String password, int totalGold, PlayerCharacter[] party, LinkedList<Item> inventory, QuestTracker questTracker) {
        this.username = username;
        this.password = password;
        this.totalGold = totalGold;
        this.areaName = "";
        this.party = limitPartySize(party);
        this.inventory = null;
        setInventory(inventory);
        this.questTracker = questTracker;
    }


    public PlayerCharacter[] getParty() {
        return party;
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
        trimInventoryToLimit();
    }

    public QuestTracker getQuestTracker() {
        return questTracker;
    }

    public void setQuestTracker(QuestTracker questTracker) {
        this.questTracker = questTracker;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void addItemToInventory(Item item) {
        if (item == null) {
            return;
        }
        if (this.inventory == null) {
            this.inventory = new LinkedList<>();
        }
        if (this.inventory.size() >= this.maxInventorySlots) {
            return;
        }
        this.inventory.add(item);
    }

    public void removeItemFromInventory(Item item) {
        if (this.inventory != null) {
            this.inventory.remove(item);
        }
    }

    public int getMaxInventorySlots() {
        return maxInventorySlots;
    }

    public void setMaxInventorySlots(int maxInventorySlots) {
        this.maxInventorySlots = maxInventorySlots;
        trimInventoryToLimit();
    }

    private void trimInventoryToLimit() {
        if (this.inventory == null) {
            return;
        }

        while (this.inventory.size() > this.maxInventorySlots) {
            this.inventory.removeLast();
        }
    }

    public ArrayList<String> getUnlockedSkillNames() {
        return unlockedSkillNames;
    }

    public void setUnlockedSkillNames(ArrayList<String> unlockedSkillNames) {
        this.unlockedSkillNames = unlockedSkillNames != null ? unlockedSkillNames : new ArrayList<>();
    }

    public void addUnlockedSkillName(String skillName) {
        if (skillName != null && !this.unlockedSkillNames.contains(skillName)) {
            this.unlockedSkillNames.add(skillName);
        }
    }

    public void kunjungiLokasi(String namaLokasi) {
        if (namaLokasi != null) {
            String key = namaLokasi.toLowerCase();
            if (!this.statusLokasi.contains(key)) {
                this.statusLokasi.add(key);
            }
        }
    }

    public ArrayList<String> getVisitedLocationNames() {
        return new ArrayList<>(statusLokasi);
    }
}


