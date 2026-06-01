package models.account;
import java.util.*;
import models.character.PlayerCharacter;
import models.item.Item;
import models.location.Location;
import systems.quest.QuestTracker;
import enums.StatusLokasi;

public class AccountProfile {
    private static final int MAX_PARTY_SIZE = 4;
    public static final int DEFAULT_MAX_INVENTORY_SLOTS = 200;

    private String username;
    private String password;
    private int totalGold;
    private int totalPlaytime;
    private transient long sessionStartMillis = 0L;
    private String areaName;
    private PlayerCharacter[] party;
    private LinkedList<Item> inventory;
    private QuestTracker questTracker;
    private int maxInventorySlots = DEFAULT_MAX_INVENTORY_SLOTS;
    private Set<String> unlockedSkillNames = new HashSet<>();
    private HashMap<String, StatusLokasi> statusLokasi = new HashMap<>();

    public AccountProfile(String username, String password, int totalGold, PlayerCharacter[] party, LinkedList<Item> inventory, QuestTracker questTracker) {
        this.username = username;
        this.password = password;
        this.totalGold = totalGold;
        this.totalPlaytime = 0;
        this.areaName = "";
        this.party = limitPartySize(party);
        this.inventory = null;
        setInventory(inventory);
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
        trimInventoryToLimit();
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

    public void startPlaytime() {
        if (this.sessionStartMillis == 0L) {
            this.sessionStartMillis = System.currentTimeMillis();
        }
    }

    public int stopPlaytimeAndAccumulate() {
        if (this.sessionStartMillis == 0L) return 0;
        long now = System.currentTimeMillis();
        long elapsedMillis = now - this.sessionStartMillis;
        int addedMinutes = (int) (elapsedMillis / 60000L);
        if (addedMinutes > 0) {
            this.totalPlaytime += addedMinutes;
        }
        this.sessionStartMillis = 0L;
        return addedMinutes;
    }

    public String getTotalPlaytimeFormatted() {
        int minutes = Math.max(0, this.totalPlaytime);
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%d:%02d", hours, mins);
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

    public Set<String> getUnlockedSkillNames() {
        return unlockedSkillNames;
    }

    public void setUnlockedSkillNames(Set<String> unlockedSkillNames) {
        this.unlockedSkillNames = unlockedSkillNames != null ? unlockedSkillNames : new HashSet<>();
    }

    public void addUnlockedSkillName(String skillName) {
        if (skillName != null) {
            this.unlockedSkillNames.add(skillName);
        }
    }

    public boolean isSkillUnlocked(String skillName) {
        return unlockedSkillNames.contains(skillName);
    }

    public HashMap<String, StatusLokasi> getStatusLokasi() {
        return statusLokasi;
    }

    public void setStatusLokasi(HashMap<String, StatusLokasi> statusLokasi) {
        this.statusLokasi = statusLokasi != null ? statusLokasi : new HashMap<>();
    }

    public void kunjungiLokasi(String namaLokasi) {
        if (namaLokasi != null) {
            statusLokasi.put(namaLokasi.toLowerCase(), StatusLokasi.TERBUKA);
        }
    }

    public boolean sudahMengunjungi(String namaLokasi) {
        return namaLokasi != null && statusLokasi.containsKey(namaLokasi.toLowerCase());
    }

    public StatusLokasi getStatusLokasi(String namaLokasi) {
        return statusLokasi.getOrDefault(namaLokasi != null ? namaLokasi.toLowerCase() : "", StatusLokasi.TERKUNCI);
    }

    public ArrayList<String> getVisitedLocationNames() {
        ArrayList<String> visited = new ArrayList<>();
        for (Map.Entry<String, StatusLokasi> entry : statusLokasi.entrySet()) {
            if (entry.getValue() == StatusLokasi.TERBUKA) {
                visited.add(entry.getKey());
            }
        }
        return visited;
    }
}


