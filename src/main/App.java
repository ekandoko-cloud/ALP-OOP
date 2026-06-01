package main;

import DummyData.*;

import java.io.*;
import java.util.*;
import java.nio.file.Files;

import minigames.QuizGame;
import minigames.SpaceGame;
import models.account.AccountProfile;
import models.character.Monster;
import models.character.PlayerCharacter;
import models.item.Item;
import models.item.Equipment;
import models.location.Location;
import models.quest.MainQuest;
import models.quest.Quest;
import models.quest.SubQuest;
import systems.battle.AdventureSystem;
import systems.craft.ForgeSystem;
import systems.craft.CraftingSystem;
import systems.encyclopedia.Encyclopedia;
import systems.gacha.GachaSystem;
import systems.gacha.itemGacha;
import systems.inventory.Inventory;
import systems.save.SaveLoadSystem;
import systems.shop.Shop;
import systems.map.MapTraversal;
import systems.map.WaypointSystem;
import systems.quest.QuestTracker;
import systems.skill.SkillSystem;
import systems.classSystem.ClassSystem;

public class App {
    //ANSI COLORS
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RED_BRIGHT = "\u001B[91m";
    private static final String SOFT_TEAL  = "\u001B[38;2;64;200;180m";
    private static final String WARM_GOLD  = "\u001B[38;2;220;180;80m";
    private static final String SOFT_WHITE = "\u001B[38;2;220;230;240m";
    private static final String SOFT_GREEN = "\u001B[38;2;100;200;140m";
    private static final String DIM_GRAY   = "\u001B[38;2;130;145;160m";

    private static final String C_64_196_255   = "\u001B[38;2;64;196;255m";
    private static final String C_32_160_220   = "\u001B[38;2;32;160;220m";
    private static final String C_80_220_160   = "\u001B[38;2;80;220;160m";
    private static final String C_100_230_180  = "\u001B[38;2;100;230;180m";
    private static final String C_200_240_255  = "\u001B[38;2;200;240;255m";
    private static final String C_160_220_240  = "\u001B[38;2;160;220;240m";
    private static final String C_160_230_255  = "\u001B[38;2;160;230;255m";
    private static final String C_80_130_160   = "\u001B[38;2;80;130;160m";

    private static final String C_180_100_255  = "\u001B[38;2;180;100;255m";
    private static final String C_150_80_230   = "\u001B[38;2;150;80;230m";
    private static final String C_220_180_80   = "\u001B[38;2;220;180;80m";
    private static final String C_200_160_60   = "\u001B[38;2;200;160;60m";
    private static final String C_220_220_240  = "\u001B[38;2;220;220;240m";
    private static final String C_180_180_210  = "\u001B[38;2;180;180;210m";
    private static final String C_200_160_255  = "\u001B[38;2;200;160;255m";
    private static final String C_100_80_140   = "\u001B[38;2;100;80;140m";
    private static final String C_220_200_255  = "\u001B[38;2;220;200;255m";

    private static final String C_80_200_80    = "\u001B[38;2;80;200;80m";
    private static final String C_60_170_60    = "\u001B[38;2;60;170;60m";
    private static final String C_180_130_40   = "\u001B[38;2;180;130;40m";
    private static final String C_160_100_40   = "\u001B[38;2;160;100;40m";
    private static final String C_130_80_30    = "\u001B[38;2;130;80;30m";
    private static final String C_180_230_140  = "\u001B[38;2;180;230;140m";
    private static final String C_200_230_150  = "\u001B[38;2;200;230;150m";
    private static final String C_120_210_90   = "\u001B[38;2;120;210;90m";
    private static final String C_80_110_50    = "\u001B[38;2;80;110;50m";

    private static final String C_255_230_80   = "\u001B[38;2;255;230;80m";
    private static final String C_240_200_60   = "\u001B[38;2;240;200;60m";
    private static final String C_100_220_120  = "\u001B[38;2;100;220;120m";
    private static final String C_80_190_100   = "\u001B[38;2;80;190;100m";
    private static final String C_240_255_220  = "\u001B[38;2;240;255;220m";
    private static final String C_200_240_180  = "\u001B[38;2;200;240;180m";
    private static final String C_150_230_130  = "\u001B[38;2;150;230;130m";
    private static final String C_120_130_70   = "\u001B[38;2;120;130;70m";
    private static final String C_255_245_160  = "\u001B[38;2;255;245;160m";
    private static final String C_240_255_180  = "\u001B[38;2;240;255;180m";

    private static final String C_100_180_220  = "\u001B[38;2;100;180;220m";
    private static final String C_70_150_190   = "\u001B[38;2;70;150;190m";
    private static final String C_160_180_200  = "\u001B[38;2;160;180;200m";
    private static final String C_130_150_170  = "\u001B[38;2;130;150;170m";
    private static final String C_80_220_240   = "\u001B[38;2;80;220;240m";
    private static final String C_60_190_210   = "\u001B[38;2;60;190;210m";
    private static final String C_180_220_240  = "\u001B[38;2;180;220;240m";
    private static final String C_100_200_220  = "\u001B[38;2;100;200;220m";
    private static final String C_70_100_120   = "\u001B[38;2;70;100;120m";

    //SCANNER
    public Scanner inpInt = new Scanner(System.in);
    public Scanner inpStr = new Scanner(System.in);

    Inventory inventory;
    //ensiklopedia
    private HashMap<Integer, Item> ingredientAlamCatalog = inqredients_alam.getDummyInqredientsAlamMap();
    private HashMap<Integer, Item> ingredientMonsterCatalog = inqredients_monster.getDummyInqredientsMonsterMap();
    private HashMap<Integer, Item> ingredientConsumablesCatalog = inqredients_consumables.getDummyInqredientsConsumablesMap();
    private HashMap<Integer, Item> consumables = DummyData.consumables.getDummyConsumablesMap();
    private HashMap<Integer, Item> weaponCatalog = DummyData.weapon.getDummyWeaponsMap();
    private HashMap<Integer, Item> armorCatalog = DummyData.armor.getDummyArmorsMap();
    private HashMap<Integer, Item> accessoryCatalog = DummyData.accessory.getDummyAccessoriesMap();
    private HashMap<Integer, systems.craft.craftingRecipe> craftingRecipes = DummyData.craftingRecipe.getDummyRecipesMap();
    private HashMap<Integer, Location> kotaMap = kota.getDummyKotaMap();
    private HashMap<Integer, Monster> monster = DummyData.monster.getDummyMonstersMap();
    private HashMap<Integer, itemGacha> gachaItems = DummyData.gacha.getDummyGachaMap();
    private HashMap<Integer, Encyclopedia> ensiklopedia = new HashMap<>();

    // Current logged-in account
    private AccountProfile currentAccount;
    private ForgeSystem forgeSystem;
    private CraftingSystem craftingSystem;
    private PlayerCharacter[] party = new PlayerCharacter[4];

    //minigame
    SpaceGame SpaceGame = new SpaceGame();
    QuizGame QuizGame = new QuizGame();

    //GACHA
    GachaSystem gachaSystem = new GachaSystem();

    private HashSet<Integer> questRewarded = new HashSet<>();

    //utk nyimpan akun
    String ACCOUNT_FILE = "src/main/accounts.txt";
    String DELIMITER = ":";
    SaveLoadSystem saveload = new SaveLoadSystem();
    String usernameLogin = "";

    // Map traversal instance for the current session
    private MapTraversal mapTraversal;
    private WaypointSystem waypointSystem;

    //FITUR 3.2.2 LOGIN REGISTER
    public void startMenu() {
        while (true) {
            System.out.println();
            System.out.println(SOFT_TEAL + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "       ███╗   ██╗██╗   ██╗████████╗██████╗ ██╗████████╗ █████╗ ██╗     ███████╗     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "       ████╗  ██║██║   ██║╚══██╔══╝██╔══██╗██║╚══██╔══╝██╔══██╗██║     ██╔════╝     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "       ██╔██╗ ██║██║   ██║   ██║   ██████╔╝██║   ██║   ███████║██║     █████╗       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "       ██║╚██╗██║██║   ██║   ██║   ██╔══██╗██║   ██║   ██╔══██║██║     ██╔══╝       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "       ██║ ╚████║╚██████╔╝   ██║   ██║  ██║██║   ██║   ██║  ██║███████╗███████╗     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "       ╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                  *  Learn Nutrition. Grow Strong. End Hunger.  *                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "                          -  S E L A M A T  D A T A N G  -                          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [1] Login                                                                     " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [2] Register                                                                  " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "  >  [3] Keluar                                                                    " + SOFT_TEAL + ANSI_BOLD + " ║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + "                                     Choose an option :                             " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(SOFT_TEAL + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
            System.out.print(SOFT_WHITE + "  >> " + ANSI_RESET);

            int choice = 0;
            String line = null;
            try {
                line = inpStr.nextLine();
                if (line == null || line.trim().isEmpty()) {
                    System.out.println("Pilihan tidak valid. Silakan pilih 1, 2, atau 3.");
                    System.out.println();
                    continue;
                }
                choice = Integer.parseInt(line.trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Input tidak valid. Silakan masukkan yang sesuai.");
                System.out.println();
                continue;
            }

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else if (choice == 3) {
                System.out.println("Thank you for playing our game.");
                System.exit(0);
            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih 1, 2, atau 3.");
                System.out.println();
                continue;
            }
        }
    }

    public void register() {
        while (true) {
            System.out.println();
            System.out.println("--- REGISTER ---");
            System.out.print("Username: ");
            String username = inpStr.nextLine();

            if (username.isEmpty()) {
                System.out.println("Username tidak boleh kosong");
                return;
            }

            System.out.print("Password: ");
            String password = inpStr.nextLine();

            if (password.isEmpty()) {
                System.out.println("Password tidak boleh kosong");
                return;
            }

            if (checkUsername(username)) {
                System.out.println("Username sudah terdaftar, silakan pilih username lain");
                return;
            }

            saveAcc(username, password);
            System.out.println("Registration successful.");
            System.out.println();
            return;
        }
    }

    public void login() {
        while (true) {
            System.out.println("--- LOGIN ---");
            System.out.print("Username: ");
            usernameLogin = inpStr.nextLine();

            if (usernameLogin.isEmpty()) {
                System.out.println("Username tidak boleh kosong");
                return;
            }

            System.out.print("Password: ");
            String password = inpStr.nextLine();

            if (password.isEmpty()) {
                System.out.println("Password tidak boleh kosong");
                return;
            }

            if (verifyLogin(usernameLogin, password)) {
                System.out.println("Login successful. Welcome, " + usernameLogin + "!");

                AccountProfile loadedAccount = saveload.load(usernameLogin);
                if (loadedAccount != null) {
                    loadedAccount.setPassword(password);
                    ensureQuestTrackerCatalog(loadedAccount);
                    this.currentAccount = loadedAccount;
                    this.craftingSystem = null;
                    System.out.println("Save ditemukan. Data akun berhasil di-load otomatis.");
                } else {
                    PlayerCharacter[] newParty = new PlayerCharacter[4];
                    List<String> namePool = new ArrayList<>();
                    File namesFile = new File("src/main/charaNames.txt");
                    if (namesFile.exists()) {
                        try (BufferedReader brNames = new BufferedReader(new FileReader(namesFile))) {
                            String n;
                            while ((n = brNames.readLine()) != null) {
                                n = n.trim();
                                if (!n.isEmpty()) namePool.add(n);
                            }
                        } catch (IOException ex) {
                            System.out.println("Gagal membaca charaNames.txt: " + ex.getMessage());
                        }
                    }
                    Random rand = new Random();
                    for (int i = 0; i < 4; i++) {
                        String pick;
                        if (!namePool.isEmpty()) {
                            int idRandomChara = rand.nextInt(namePool.size());
                            pick = namePool.remove(idRandomChara);
                        } else {
                            pick = "Hero" + (i + 1);
                        }
                        newParty[i] = new PlayerCharacter(pick, 100, 100, 50, 50, 10, 5, 1, 0, 100, "CLASSLESS", false);
                    }
                    this.currentAccount = new AccountProfile(usernameLogin, password, 0, newParty, new LinkedList<>(), null);
                    ensureQuestTrackerCatalog(this.currentAccount);
                    this.craftingSystem = null;
                    try {
                        saveCurrentAccountWithRecipes();
                    } catch (Exception ex) {
                        System.out.println("Belum ada file save. 4 karakter CLASSLESS baru telah dibuat (gagal autosave: " + ex.getMessage() + ")");
                    }

                    if (ingredientAlamCatalog.isEmpty() && ingredientMonsterCatalog.isEmpty() && ingredientConsumablesCatalog.isEmpty()) {
                        System.out.println("Peringatan: data dummy ingredients gagal dimuat.");
                    }
                }

                party = (currentAccount != null && currentAccount.getParty() != null)
                        ? currentAccount.getParty()
                        : new PlayerCharacter[0];

                if (forgeSystem == null) {
                    forgeSystem = new ForgeSystem(currentAccount);
                } else {
                    forgeSystem.setCurrentAccount(currentAccount);
                }

                if (currentAccount != null) currentAccount.startPlaytime();

                if (currentAccount != null) {
                    String savedArea = currentAccount.getAreaName();
                    if (savedArea != null && !savedArea.isEmpty()) {
                        mapTraversal = new MapTraversal(savedArea);
                    } else {
                        mapTraversal = new MapTraversal();
                    }
                    if (mapTraversal.areaSaatIni() != null) {
                        currentAccount.setAreaName(mapTraversal.areaSaatIni().getNamaLokasi());
                    }
                }

                mainMenu();
                return;
            } else {
                System.out.println("Login failed. Incorrect username or password.");
                return;
            }
        }
    }

    public boolean checkUsername(String username) {
        File accFile = new File(ACCOUNT_FILE);

        if (!accFile.exists()) {
            return false;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(accFile))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(DELIMITER, 2);
                if (array.length >= 1 && array[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return false;
    }

    public boolean saveAcc(String username, String password) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))) {
            bufferedWriter.write(username + DELIMITER + password);
            bufferedWriter.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file");
            return false;
        }
    }

    public boolean verifyLogin(String username, String password) {
        String currentLine;
        String array[];

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNT_FILE));

            while ((currentLine = bufferedReader.readLine()) != null) {
                array = currentLine.split(DELIMITER, 2);
                if (array[0].equals(username) && array[1].equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

        return false;

    }

    public boolean changeUsername(String oldUsername, String newUsername) {
        File accFile = new File(ACCOUNT_FILE);
        if (!accFile.exists()) return false;

        List<String> lines = new ArrayList<>();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(accFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER, 2);
                if (parts.length == 2 && oldUsername != null && parts[0].trim().equals(oldUsername.trim())) {
                    lines.add(newUsername + DELIMITER + parts[1]);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            return false;
        }

        if (!found) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(accFile, false))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            return false;
        }

        File oldSaveFile = new File(saveload.SAVE_FOLDER + oldUsername + saveload.extension);
        if (oldSaveFile.exists()) {
            File newSaveFile = new File(saveload.SAVE_FOLDER + newUsername + saveload.extension);
            try (BufferedReader brSave = new BufferedReader(new FileReader(oldSaveFile));
                 BufferedWriter bwSave = new BufferedWriter(new FileWriter(newSaveFile))) {
                String saveLine;
                while ((saveLine = brSave.readLine()) != null) {
                    if (saveLine.startsWith("username=")) {
                        bwSave.write("username=" + newUsername);
                    } else {
                        bwSave.write(saveLine);
                    }
                    bwSave.newLine();

                }
            } catch (IOException e) {
                return false;
            }
            try {
                Files.deleteIfExists(oldSaveFile.toPath());
            } catch (IOException ex) {
                System.err.println("Warning: failed to delete old save file: " + ex.getMessage());
            }
        }

        return true;
    }

//    // sync party from currentAccount (ensures max 4)
//    private void syncPartyFromAccount() {
//        if (currentAccount == null) {
//            party = new PlayerCharacter[0];
//            return;
//        }
//        PlayerCharacter[] acctParty = currentAccount.getParty();
//        if (acctParty == null) {
//            party = new PlayerCharacter[0];
//        } else if (acctParty.length <= 4) {
//            party = acctParty;
//        } else {
//            party = Arrays.copyOf(acctParty, 4);
//            currentAccount.setParty(party);
//        }
//    }


//    // party management helpers: enforce max 4 characters
//    public boolean addToParty(PlayerCharacter pc) {
//        if (pc == null) return false;
//        if (party == null) party = new PlayerCharacter[0];
//        if (party.length >= 4) return false; // max reached
//        PlayerCharacter[] next = Arrays.copyOf(party, party.length + 1);
//        next[party.length] = pc;
//        party = next;
//        if (currentAccount != null) currentAccount.setParty(party);
//        return true;
//    }
//
//    public boolean removeFromParty(int index) {
//        if (party == null || index < 0 || index >= party.length) return false;
//        PlayerCharacter[] next = new PlayerCharacter[Math.max(0, party.length - 1)];
//        for (int i = 0, j = 0; i < party.length; i++) {
//            if (i == index) continue;
//            next[j++] = party[i];
//        }
//        party = next;
//        if (currentAccount != null) currentAccount.setParty(party);
//        return true;
//    }
//
//    // sync party from currentAccount (ensures max 4)
//    private void syncPartyFromAccount() {
//        if (currentAccount == null) {
//            party = new PlayerCharacter[0];
//            return;
//        }
//        PlayerCharacter[] acctParty = currentAccount.getParty();
//        if (acctParty == null) {
//            party = new PlayerCharacter[0];
//        } else if (acctParty.length <= 4) {
//            party = acctParty;
//        } else {
//            party = Arrays.copyOf(acctParty, 4);
//            currentAccount.setParty(party);
//        }
//    }


//    //VALERION (KOTA 1)
//    public void displayMenuValerion() {
//        System.out.println();
//        System.out.println(C_64_196_255 + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_64_196_255 + "      ##     ##    ###    ##       ######## ########  ####  #######  ##    ##       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_32_160_220 + "      ##     ##   ## ##   ##       ##       ##     ##  ##  ##     ## ###   ##       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "      ##     ##  ##   ##  ##       ##       ##     ##  ##  ##     ## ####  ##       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_100_230_180 + "      ##     ## ##     ## ##       ######   ########   ##  ##     ## ## ## ##       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_240_255 + "       ##   ##  ######### ##       ##       ##   ##    ##  ##     ## ##  ####       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_160_220_240 + "        ## ##   ##     ## ##       ##       ##    ##   ##  ##     ## ##   ###       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_160_220_240 + "         ###    ##     ## ######## ######## ##     ## ####  #######  ##    ##       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_160_230_255 + "  Kota pelabuhan yang makmur dengan pasar segar penuh hasil laut dan pertanian.     " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_160_230_255 + "  Terkenal dengan pedagang yang jujur dan sistem distribusi makanan yang adil       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_160_230_255 + "  kepada seluruh lapisan masyarakat. Menjadi harapan baru dalam memerangi           " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_160_230_255 + "  kelaparan.                                                                        " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_240_255 + ANSI_BOLD + "                              -  M A I N   M E N U  -                               " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 1] Play                           " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 2] Quest Tracker                   " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 3] Inventory                      " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 4] Shop                            " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 5] Crafting                       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 6] Forge                           " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 7] Quest Board                    " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 8] Mini Game                       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [ 9] Encyclopedia                   " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [10] Skill Tree                      " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [11] Class Tree                     " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [12] Gacha                           " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [13] Waypoint                       " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_160 + "  >  [14] Profil Akun                     " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_64_196_255 + "                         [15] Save Game        [16] Logout                          " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET + C_80_130_160 + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + C_64_196_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_64_196_255 + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
//        System.out.println();
//        while (true) {
//            displayMainMenu();
//            System.out.print("Choose an option: ");
//            int choice;
//
//            try {
//                choice = inpInt.nextInt();
//
//                if (choice == 1) {
//                    mapTraversalMenu();
//                } else if (choice == 2) {
//                    if (currentAccount != null) {
//                        MainQuest.displayQuestTracker(currentAccount.getQuestTracker());
//                    } else {
//                        System.out.println("Belum login.");
//                    }
//                } else if (choice == 3) {
//                    inventoryMenu();
//                } else if (choice == 4) {
//                    shop1();
//                } else if (choice == 5) {
//                    craftingMenu();
//                } else if (choice == 6) {
//                    forgeMenu();
//                } else if (choice == 7) {
//                    if (currentAccount != null) {
//                        MainQuest.displayQuestBoard(currentAccount.getQuestTracker());
//                    } else {
//                        System.out.println("Belum login.");
//                    }
//                } else if (choice == 8) {
//                    miniGameMenu();
//                } else if (choice == 9) {
//                    ensiklopediaMenu();
//                } else if (choice == 10) {
//                    System.out.println("Skill Tree belum tersedia.");
//                } else if (choice == 11) {
//                    System.out.println("Class Tree belum tersedia.");
//                } else if (choice == 12) {
//                    gachaMenu();
//                } else if (choice == 13) {
//                    waypointMenu();
//                } else if (choice == 14) {
//                    accProfileMenu();
//                } else if (choice == 15) {
//                    if (currentAccount != null) {
//                        currentAccount.stopPlaytimeAndAccumulate();
//                        saveCurrentAccountWithRecipes();
//                        System.out.println("Game saved successfully. (playtime updated: " + currentAccount.getTotalPlaytimeFormatted() + ")");
//                        currentAccount.startPlaytime();
//                    } else {
//                        System.out.println("No account loaded to save.");
//                    }
//                } else if (choice == 16) {
//                    if (currentAccount != null) {
//                        currentAccount.stopPlaytimeAndAccumulate();
//                        saveCurrentAccountWithRecipes();
//                        System.out.println("Logging out... Playtime saved: " + currentAccount.getTotalPlaytimeFormatted());
//                    } else {
//                        System.out.println("Logging out...");
//                    }
//                    currentAccount = null;
//                    usernameLogin = "";
//                    resepUser = null;
//                    craftingSystem = null;
//                    party = new PlayerCharacter[0];
//                    if (forgeSystem != null) forgeSystem.setCurrentAccount(null);
//                    startMenu();
//                } else {
//                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
//                    continue;
//                }
//            } catch (Exception e) {
//                System.out.println("Invalid Input");
//                continue;
//            }
//        }
//    }
//
//    //ASGARD (KOTA 2)
//    public void displayMenuAsgard() {
//        System.out.println();
//        System.out.println(C_180_100_255 + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_180_100_255 + "                ###     ######   ######      ###    ########  ########              " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_150_80_230 + "               ## ##   ##    ## ##    ##    ## ##   ##     ## ##     ##             " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_180_80 + "              ##   ##  ##       ##         ##   ##  ##     ## ##     ##             " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_60 + "             ##     ##  ######  ##   #### ##     ## ########  ##     ##             " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_220_240 + "             #########       ## ##    ##  ######### ##   ##   ##     ##             " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_180_180_210 + "             ##     ## ##    ## ##    ##  ##     ## ##    ##  ##     ##             " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_180_180_210 + "             ##     ##  ######   ######   ##     ## ##     ## ########              " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_200_255 + "  Pusat kerajaan dengan istana megah dan perpustakaan luas penuh pengetahuan        " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_200_255 + "  tentang pertanian dan nutrisi. Para ahli kerajaan bekerja keras mengembangkan     " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_200_255 + "  benih unggul untuk mengatasi kekurangan pangan global.                            " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_220_240 + ANSI_BOLD + "                              -  M A I N   M E N U  -                               " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 1] Play                           " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 2] Quest Tracker                   " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 3] Inventory                      " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 4] Shop                            " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 5] Crafting                       " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 6] Forge                           " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 7] Quest Board                    " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 8] Mini Game                       " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [ 9] Encyclopedia                   " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [10] Skill Tree                      " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [11] Class Tree                     " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [12] Gacha                           " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [13] Waypoint                       " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_255 + "  >  [14] Profil Akun                     " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_220_180_80 + "                         [15] Save Game        [16] Logout                          " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET + C_100_80_140 + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + C_180_100_255 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_180_100_255 + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
//        System.out.println();
//    }
//
//
//    //GRANDIS (KOTA 3)
//    public void displayMenuGrandis() {
//        System.out.println();
//        System.out.println(C_80_200_80 + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_80_200_80 + "            ######   ########     ###    ##    ## ########  ####  ######            " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_60_170_60 + "           ##    ##  ##     ##   ## ##   ###   ## ##     ##  ##  ##    ##           " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_60 + "              ##        ##     ##  ##   ##  ####  ## ##     ##  ##  ##              " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_180_130_40 + "           ##   #### ########  ##     ## ## ## ## ##     ##  ##   ######            " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_160_100_40 + "           ##    ##  ##   ##   ######### ##  #### ##     ##  ##        ##           " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_130_80_30 + "           ##    ##  ##    ##  ##     ## ##   ### ##     ##  ##  ##    ##           " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_130_80_30 + "            ######   ##     ## ##     ## ##    ## ########  ####  ######            " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println("\u001B[38;2;80;200;80m" + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_180_230_140 + "  Lembah subur dengan perkebunan dan sawah yang luas. Penduduknya adalah petani     " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_180_230_140 + "  tangguh yang telah menjaga tradisi pertanian berkelanjutan. Menjadi supplier      " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_180_230_140 + "  utama biji padi dan sayuran bagi daerah sekitar.                                  " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_200_230_150 + ANSI_BOLD + "                              -  M A I N   M E N U  -                               " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 1] Play                           " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 2] Quest Tracker                   " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 3] Inventory                      " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 4] Shop                            " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 5] Crafting                       " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 6] Forge                           " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 7] Quest Board                    " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 8] Mini Game                       " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [ 9] Encyclopedia                   " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [10] Skill Tree                      " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [11] Class Tree                     " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [12] Gacha                           " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [13] Waypoint                       " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_210_90 + "  >  [14] Profil Akun                     " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_200_160_60 + "                         [15] Save Game        [16] Logout                          " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET + C_80_110_50 + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + C_80_200_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_80_200_80 + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
//        System.out.println();
//    }
//
//    //LUMINA (KOTA 4)
//    public void displayMenuLumina() {
//        System.out.println();
//        System.out.println(C_255_230_80 + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_255_230_80 + "                 ##       ##     ## ##     ## #### ##    ##    ###                  " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_240_200_60 + "                 ##       ##     ## ###   ###  ##  ###   ##   ## ##                 " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_100_220_120 + "                ##       ##     ## #### ####  ##  ####  ##  ##   ##                 " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_80_190_100 + "                ##       ##     ## ## ### ##  ##  ## ## ## ##     ##                " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_240_255_220 + "                ##       ##     ## ##     ##  ##  ##   ### ##     ##                " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_200_240_180 + "                ##       ##     ## ##     ##  ##  ##   ### ##     ##                " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_200_240_180 + "                ########  #######  ##     ## #### ##    ## ##     ##                " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println("\u001B[38;2;255;230;80m" + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_255_245_160 + "  Kota cahaya di tengah hutan yang tersebar dengan komunitas ahli gizi dan          " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_255_245_160 + "  apoteker. Mereka mengembangkan resep makanan bergizi seimbang dari bahan lokal    " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_255_245_160 + "  untuk mengatasi malnutrisi.                                                       " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_240_255_180 + ANSI_BOLD + "                              -  M A I N   M E N U  -                               " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 1] Play                           " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 2] Quest Tracker                   " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 3] Inventory                      " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 4] Shop                            " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 5] Crafting                       " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 6] Forge                           " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 7] Quest Board                    " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 8] Mini Game                       " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [ 9] Encyclopedia                   " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [10] Skill Tree                      " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [11] Class Tree                     " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [12] Gacha                           " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [13] Waypoint                       " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_150_230_130 + "  >  [14] Profil Akun                     " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_255_230_80 + "                         [15] Save Game        [16] Logout                          " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET + C_120_130_70 + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + C_255_230_80 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_255_230_80 + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
//        System.out.println();
//    }
//
//    //ALDORIA (KOTA 5)
//    public void displayMenuAldoria() {
//        System.out.println();
//        System.out.println(C_100_180_220 + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_180_220 + "               ###    ##       ########   #######  ########  ####    ###            " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_70_150_190 + "             ## ##   ##       ##     ## ##     ## ##     ##  ##    ## ##            " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_160_180_200 + "            ##   ##  ##       ##     ## ##     ## ##     ##  ##   ##   ##           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//       frcd System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_130_150_170 + "          ##     ## ##       ##     ## ##     ## ########   ##  ##     ##           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_240 + "          ######### ##       ##     ## ##     ## ##   ##    ##  #########           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_60_190_210 + "          ##     ## ##       ##     ## ##     ## ##    ##   ##  ##     ##           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_60_190_210 + "          ##     ## ######## ########   #######  ##     ## #### ##     ##           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_180_220_240 + "  Benteng pertahanan di dataran tinggi dengan gudang penyimpanan makanan raksasa.   " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_180_220_240 + "  Terkenal dengan sistem irigasi canggih yang memungkinkan bertani sepanjang tahun  " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_180_220_240 + "  meski cuaca ekstrem.                                                              " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_160_220_240 + ANSI_BOLD + "                              -  M A I N   M E N U  -                               " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 1] Play                           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 2] Quest Tracker                   " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println("\u001B[38;2;100;180;220m" + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 3] Inventory                      " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 4] Shop                            " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 5] Crafting                       " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 6] Forge                           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 7] Quest Board                    " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 8] Mini Game                       " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 9] Encyclopedia                   " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [10] Skill Tree                      " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [11] Class Tree                     " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [12] Gacha                           " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [13] Waypoint                       " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_100_200_220 + "  >  [ 14] Profil Akun                     " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_80_220_240 + "                         [15] Save Game        [16] Logout                          " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET + C_70_100_120 + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + C_100_180_220 + ANSI_BOLD + "║" + ANSI_RESET);
//        System.out.println(C_100_180_220 + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
//        System.out.println();
//    }


    // MAIN MENU NUTRITALE
    public void displayMainMenuValerion() {
        System.out.println();
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ███╗   ██╗██╗   ██╗████████╗██████╗ ██╗████████╗ █████╗ ██╗     ███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ████╗  ██║██║   ██║╚══██╔══╝██╔══██╗██║╚══██╔══╝██╔══██╗██║     ██╔════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██╔██╗ ██║██║   ██║   ██║   ██████╔╝██║   ██║   ███████║██║     █████╗            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██║╚██╗██║██║   ██║   ██║   ██╔══██╗██║   ██║   ██╔══██║██║     ██╔══╝            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ██║ ╚████║╚██████╔╝   ██║   ██║  ██║██║   ██║   ██║  ██║███████╗███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                  *  Learn Nutrition. Grow Strong. End Hunger.  *                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + ANSI_BOLD + "                              -  V A L E R I O N  -                               " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 1] Play                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 2] Quest Tracker                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 3] Inventory                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 4] Shop                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 5] Crafting                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 6] Forge                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 7] Quest Board                    " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 8] Mini Game                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 9] Encyclopedia                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [10] Skill Tree                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [11] Class Tree                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [12] Gacha                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [13] Waypoint                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [14] Profil Akun                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                         [15] Save Game        [16] Logout                          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + DIM_GRAY   + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();

        while (true) {
            displayMainMenuValerion();
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = inpInt.nextInt();

                if (choice == 1) {
                    mapTraversalMenu();
                } else if (choice == 2) {
                    if (currentAccount != null) {
                        MainQuest.displayQuestTracker(currentAccount.getQuestTracker());
                        SubQuest.displayQuestTracker(currentAccount.getQuestTracker());
                    } else {
                        System.out.println("Belum login.");
                    }
                } else if (choice == 3) {
                    inventoryMenu();
                } else if (choice == 4) {
                    shop1();
                } else if (choice == 5) {
                    craftingMenu();
                } else if (choice == 6) {
                    forgeMenu();
                } else if (choice == 7) {
                    if (currentAccount != null) {
                        questBoardMenu();
                    } else {
                        System.out.println("Belum login.");
                    }
                } else if (choice == 8) {
                    miniGameMenu();
                } else if (choice == 9) {
                    ensiklopediaMenu();
                } else if (choice == 10) {
                    SkillSystem.skillTreeMenu(currentAccount, inpInt, inpStr);
                } else if (choice == 11) {
                    ClassSystem.classTreeMenu(currentAccount, inpInt, inpStr);
                } else if (choice == 12) {
                    gachaMenu();
                } else if (choice == 13) {
                    waypointMenu();
                } else if (choice == 14) {
                    accProfileMenu();
                } else if (choice == 15) {
                    if (currentAccount != null) {
                        currentAccount.stopPlaytimeAndAccumulate();
                        saveCurrentAccountWithRecipes();
                        System.out.println("Game saved successfully. (playtime updated: " + currentAccount.getTotalPlaytimeFormatted() + ")");
                        currentAccount.startPlaytime();
                    } else {
                        System.out.println("No account loaded to save.");
                    }
                } else if (choice == 16) {
                    if (currentAccount != null) {
                        currentAccount.stopPlaytimeAndAccumulate();
                        saveCurrentAccountWithRecipes();
                        System.out.println("Logging out... Playtime saved: " + currentAccount.getTotalPlaytimeFormatted());
                    } else {
                        System.out.println("Logging out...");
                    }
                    currentAccount = null;
                    usernameLogin = "";
                    craftingSystem = null;
                    party = new PlayerCharacter[0];
                    if (forgeSystem != null) forgeSystem.setCurrentAccount(null);
                    startMenu();
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
                continue;
            }
        }
    }

    //class tree menu
    public void classTreeMenu(){

    }

    //skill tree menu
    public void skillTreeMenu(){

    }

    public void displayMenuAsgard(){
        System.out.println();
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ███╗   ██╗██╗   ██╗████████╗██████╗ ██╗████████╗ █████╗ ██╗     ███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ████╗  ██║██║   ██║╚══██╔══╝██╔══██╗██║╚══██╔══╝██╔══██╗██║     ██╔════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██╔██╗ ██║██║   ██║   ██║   ██████╔╝██║   ██║   ███████║██║     █████╗            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██║╚██╗██║██║   ██║   ██║   ██╔══██╗██║   ██║   ██╔══██║██║     ██╔══╝            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ██║ ╚████║╚██████╔╝   ██║   ██║  ██║██║   ██║   ██║  ██║███████╗███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                  *  Learn Nutrition. Grow Strong. End Hunger.  *                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + ANSI_RED + ANSI_BOLD + "                              -  A S G A R D  -                               " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 1] Play                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 2] Quest Tracker                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 3] Inventory                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 4] Shop                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 5] Crafting                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 6] Forge                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 7] Quest Board                    " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 8] Mini Game                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 9] Encyclopedia                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [10] Skill Tree                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [11] Class Tree                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [12] Gacha                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [13] Waypoint                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [14] Profil Akun                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                         [15] Save Game        [16] Logout                          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + DIM_GRAY   + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();
    }

    public void displayMenuGrandis(){
        System.out.println();
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ███╗   ██╗██╗   ██╗████████╗██████╗ ██╗████████╗ █████╗ ██╗     ███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ████╗  ██║██║   ██║╚══██╔══╝██╔══██╗██║╚══██╔══╝██╔══██╗██║     ██╔════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██╔██╗ ██║██║   ██║   ██║   ██████╔╝██║   ██║   ███████║██║     █████╗            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██║╚██╗██║██║   ██║   ██║   ██╔══██╗██║   ██║   ██╔══██║██║     ██╔══╝            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ██║ ╚████║╚██████╔╝   ██║   ██║  ██║██║   ██║   ██║  ██║███████╗███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                  *  Learn Nutrition. Grow Strong. End Hunger.  *                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + ANSI_BOLD + "                              -  G R A N D I S  -                               " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 1] Play                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 2] Quest Tracker                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 3] Inventory                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 4] Shop                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 5] Crafting                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 6] Forge                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 7] Quest Board                    " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 8] Mini Game                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 9] Encyclopedia                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [10] Skill Tree                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [11] Class Tree                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [12] Gacha                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [13] Waypoint                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [14] Profil Akun                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                         [15] Save Game        [16] Logout                          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + DIM_GRAY   + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();
    }

    public void displayMenuLumina(){
        System.out.println();
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ███╗   ██╗██╗   ██╗████████╗██████╗ ██╗████████╗ █████╗ ██╗     ███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ████╗  ██║██║   ██║╚══██╔══╝██╔══██╗██║╚══██╔══╝██╔══██╗██║     ██╔════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██╔██╗ ██║██║   ██║   ██║   ██████╔╝██║   ██║   ███████║██║     █████╗            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██║╚██╗██║██║   ██║   ██║   ██╔══██╗██║   ██║   ██╔══██║██║     ██╔══╝            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ██║ ╚████║╚██████╔╝   ██║   ██║  ██║██║   ██║   ██║  ██║███████╗███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                  *  Learn Nutrition. Grow Strong. End Hunger.  *                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + ANSI_CYAN + ANSI_BOLD + "                              -  L U M I N A  -                               " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 1] Play                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 2] Quest Tracker                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 3] Inventory                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 4] Shop                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 5] Crafting                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 6] Forge                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 7] Quest Board                    " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 8] Mini Game                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 9] Encyclopedia                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [10] Skill Tree                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [11] Class Tree                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [12] Gacha                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [13] Waypoint                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [14] Profil Akun                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                         [15] Save Game        [16] Logout                          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + DIM_GRAY   + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();
    }

    public void displayMenuAldoria(){
        System.out.println();
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ███╗   ██╗██╗   ██╗████████╗██████╗ ██╗████████╗ █████╗ ██╗     ███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + ANSI_BOLD + "  ████╗  ██║██║   ██║╚══██╔══╝██╔══██╗██║╚══██╔══╝██╔══██╗██║     ██╔════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██╔██╗ ██║██║   ██║   ██║   ██████╔╝██║   ██║   ███████║██║     █████╗            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + ANSI_BOLD + "  ██║╚██╗██║██║   ██║   ██║   ██╔══██╗██║   ██║   ██╔══██║██║     ██╔══╝            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ██║ ╚████║╚██████╔╝   ██║   ██║  ██║██║   ██║   ██║  ██║███████╗███████╗          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "  ╚═╝  ╚═══╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚══════╝          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                  *  Learn Nutrition. Grow Strong. End Hunger.  *                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                                                                    ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_WHITE + ANSI_BOLD + "                              -  A L D O R I A  -                               " + SOFT_TEAL + ANSI_BOLD + "  ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╦══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 1] Play                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 2] Quest Tracker                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 3] Inventory                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 4] Shop                            " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 5] Crafting                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 6] Forge                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 7] Quest Board                    " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 8] Mini Game                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [ 9] Encyclopedia                   " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [10] Skill Tree                      " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [11] Class Tree                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [12] Gacha                           " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [13] Waypoint                       " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + SOFT_GREEN + "  >  [14] Profil Akun                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║                                         ║                                          ║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠═════════════════════════════════════════╩══════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + WARM_GOLD  + "                         [15] Save Game        [16] Logout                          " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET + DIM_GRAY   + "                    SDG 2: Zero Hunger  —  Eat Smart, Live Well                     " + SOFT_TEAL + ANSI_BOLD + "║" + ANSI_RESET);
        System.out.println(SOFT_TEAL + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();
    }

    public static final String INVALID_INPUT_BOX =
            "\n" +
                    ANSI_RED + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════╗" + ANSI_RESET + "\n" +
                    ANSI_RED + ANSI_BOLD + "║                ✖  I N V A L I D   I N P U T  ✖                 ║" + ANSI_RESET + "\n" +
                    ANSI_RED + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════╣" + ANSI_RESET + "\n" +
                    ANSI_RED + ANSI_BOLD + "║" + ANSI_RESET + ANSI_RED_BRIGHT + "            Masukkan input yang tersedia pada menu!             " + ANSI_RED + ANSI_BOLD + "║" + ANSI_RESET + "\n" +
                    ANSI_RED + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════╝" + ANSI_RESET + "\n";


    public void mainMenu() {
        while (true) {
            displayMainMenuValerion();
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = inpInt.nextInt();

                if (choice == 1) {
                    mapTraversalMenu();
                } else if (choice == 2) {
                    if (currentAccount != null) {
                        MainQuest.displayQuestTracker(currentAccount.getQuestTracker());
                        SubQuest.displayQuestTracker(currentAccount.getQuestTracker());
                    } else {
                        System.out.println("Belum login.");
                    }
                } else if (choice == 3) {
                    inventoryMenu();
                } else if (choice == 4) {
                    shop1();
                } else if (choice == 5) {
                    craftingMenu();
                } else if (choice == 6) {
                    forgeMenu();
                } else if (choice == 7) {
                    if (currentAccount != null) {
                        questBoardMenu();
                    } else {
                        System.out.println("Belum login.");
                    }
                } else if (choice == 8) {
                    miniGameMenu();
                } else if (choice == 9) {
                    ensiklopediaMenu();
                } else if (choice == 10) {
                    if (currentAccount != null) {
                        SkillSystem.skillTreeMenu(currentAccount, inpInt, inpStr);
                    } else {
                        System.out.println("Belum login.");
                    }
                } else if (choice == 11) {
                    if (currentAccount != null) {
                        ClassSystem.classTreeMenu(currentAccount, inpInt, inpStr);
                    } else {
                        System.out.println("Belum login.");
                    }
                } else if (choice == 12) {
                    gachaMenu();
                } else if (choice == 13) {
                    waypointMenu();
                } else if (choice == 14) {
                    accProfileMenu();
                } else if (choice == 15) {
                    if (currentAccount != null) {
                        currentAccount.stopPlaytimeAndAccumulate();
                        saveCurrentAccountWithRecipes();
                        System.out.println("Game saved successfully. (playtime updated: " + currentAccount.getTotalPlaytimeFormatted() + ")");
                        currentAccount.startPlaytime();
                    } else {
                        System.out.println("No account loaded to save.");
                    }
                } else if (choice == 16) {
                    if (currentAccount != null) {
                        currentAccount.stopPlaytimeAndAccumulate();
                        saveCurrentAccountWithRecipes();
                        System.out.println("Logging out... Playtime saved: " + currentAccount.getTotalPlaytimeFormatted());
                    } else {
                        System.out.println("Logging out...");
                    }
                    currentAccount = null;
                    usernameLogin = "";
                    craftingSystem = null;
                    party = new PlayerCharacter[0];
                    if (forgeSystem != null) forgeSystem.setCurrentAccount(null);
                    startMenu();
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
                continue;
            }
        }
    }

    //ensiklopedia
    private Encyclopedia ensiklopediaInstance;

    public void ensiklopediaInit() {
        if (ensiklopediaInstance == null) {
            ensiklopediaInstance = new Encyclopedia();

            for (Map.Entry<Integer, Monster> entry : monster.entrySet()) {
                String k = entry.getValue().getNama();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexMonster().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : ingredientAlamCatalog.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexIngredientAlam().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : ingredientMonsterCatalog.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexIngredientMonster().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : ingredientConsumablesCatalog.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexIngredientConsumables().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : consumables.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexConsumables().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : weaponCatalog.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexWeapon().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : armorCatalog.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexArmor().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Item> entry : accessoryCatalog.entrySet()) {
                String k = entry.getValue().getNamaItem();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexAccessory().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, Location> entry : kotaMap.entrySet()) {
                String k = entry.getValue().getNamaLokasi();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexLokasi().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            for (Map.Entry<Integer, systems.craft.craftingRecipe> entry : craftingRecipes.entrySet()) {
                String k = entry.getValue().getRecipeName();
                Object v = entry.getValue();
                ensiklopediaInstance.getIndexResep().put(k, v);
                ensiklopediaInstance.getIndexUtama().put(k, v);
            }
            ensiklopedia.put(1, ensiklopediaInstance);
        }
    }

    public void ensiklopediaMenu() {
        ensiklopediaInit();
        Encyclopedia e = ensiklopediaInstance;

        while (true) {
            System.out.println();
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + "                          E N C Y C L O P E D I A                           " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + "  _____ _   _ _______   _______  _     ___________ ___________ ___________ " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + " |  ___| \\ | /  __ \\ \\ / /  __ \\| |   |  _  | ___ \\  ___|  _  \\_   _|/ _ \\  " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + " | |__ |  \\| | /  \\/\\ V /| /  \\/| |   | | | | |_/ / |__ | | | | | | / /_\\ \\ " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + " |  __|| . ` | |     \\ / | |    | |   | | | |  __/|  __|| | | | | | |  _  | " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + " | |___| |\\  | \\__/\\ | | | \\__/\\| |___\\ \\_/ / |   | |___| |/ / _| |_| | | | " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + " \\____/\\_| \\_/\\____/ \\_/  \\____/\\_____/\\___/\\_|   \\____/|___/  \\___/\\_| |_/ " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[1] Monster                          " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[2] Ingredient Alam                   " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[3] Ingredient Monster               " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[4] Ingredient Consumables            " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[5] Consumables                      " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[6] Weapon                            " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[7] Armor                            " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[8] Accessory                         " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[9] Location                         " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[10] Crafting Recipes                 " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[11] Search Keyword                  " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "[12] Back to Main Menu                " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
            System.out.println();
            System.out.print("Choose an option: ");

            try {
                int choice = inpInt.nextInt();
                if (choice == 1) {
                    e.displayMonsterSector();
                    encyclopediaSubMenu(e, e.getIndexMonster());
                } else if (choice == 2) {
                    e.displayIngredientAlamSector();
                    encyclopediaSubMenu(e, e.getIndexIngredientAlam());
                } else if (choice == 3) {
                    e.displayIngredientMonsterSector();
                    encyclopediaSubMenu(e, e.getIndexIngredientMonster());
                } else if (choice == 4) {
                    e.displayIngredientConsumablesSector();
                    encyclopediaSubMenu(e, e.getIndexIngredientConsumables());
                } else if (choice == 5) {
                    e.displayConsumablesSector();
                    encyclopediaSubMenu(e, e.getIndexConsumables());
                } else if (choice == 6) {
                    e.displayWeaponSector();
                    encyclopediaSubMenu(e, e.getIndexWeapon());
                } else if (choice == 7) {
                    e.displayArmorSector();
                    encyclopediaSubMenu(e, e.getIndexArmor());
                } else if (choice == 8) {
                    e.displayAccessorySector();
                    encyclopediaSubMenu(e, e.getIndexAccessory());
                } else if (choice == 9) {
                    e.displayLocationSector();
                    encyclopediaSubMenu(e, e.getIndexLokasi());
                } else if (choice == 10) {
                    e.displayRecipeSector();
                    encyclopediaSubMenu(e, e.getIndexResep());
                } else if (choice == 11) {
                    e.searchEncyclopedia(inpStr);
                } else if (choice == 12) {
                    return;
                } else {
                    System.out.println(INVALID_INPUT_BOX);
                }
            } catch (Exception ex) {
                System.out.println(INVALID_INPUT_BOX);
            }
        }
    }

    private HashMap<Integer, Object> convertToIntegerKeyedMap(HashMap<String, Object> stringKeyedMap) {
        HashMap<Integer, Object> integerKeyedMap = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, Object> entry : stringKeyedMap.entrySet()) {
            integerKeyedMap.put(index, entry.getValue());
            index++;
        }
        return integerKeyedMap;
    }

    public void encyclopediaSubMenu(Encyclopedia e, HashMap<String, Object> sectorIndex) {
        HashMap<Integer, Object> indexedMap = convertToIntegerKeyedMap(sectorIndex);

        while (true) {
            System.out.println();
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + "                      DETAIL ITEM MENU                           " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "  [1] Check Details                                         " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "  [2] Back to Encyclopedia                                       " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
            System.out.print("Pilih: ");

            try {
                int pilih = inpInt.nextInt();

                if (pilih == 1) {
                    System.out.print("Masukkan Nomor: ");
                    int itemIndex = inpInt.nextInt();

                    Object found = indexedMap.get(itemIndex);

                    if (found != null) {
                        System.out.println();
                        e.displayDetail(found);
                        System.out.println();
                    } else {
                        System.out.println(INVALID_INPUT_BOX);
                    }
                } else if (pilih == 2) {
                    return;
                } else {
                    System.out.println(INVALID_INPUT_BOX);
                }
            } catch (Exception ex) {
                System.out.println(INVALID_INPUT_BOX);
            }
        }
    }

    //GACHA MENU
    public void gachaMenu() {
        while (true) {
            System.out.println();
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╔════════════════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + "            ✦ ✦ ✦  S E L A M A T  D A T A N G  ✦ ✦ ✦            " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + "                 ____    _    ____ _   _    _                   " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + "                / ___|  / \\  / ___| | | |  / \\                  " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + "               | |  _  / _ \\| |   | |_| | / _ \\                 " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + "               | |_| |/ ___ \\ |___|  _  |/ ___ \\                " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_MAGENTA + "                \\____/_/   \\_\\____|_| |_/_/   \\_\\               " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "                     [ 1] Cek Daftar Hadiah                     " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "                     [ 2] Pull 1x   (50 Gold)                   " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_GREEN + "                     [ 3] Pull 10x  (500 Gold)                  " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_YELLOW + "                     [ 4] Back to Main Menu                     " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╠════════════════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET + ANSI_CYAN + "                      Choose an option :                        " + ANSI_CYAN + ANSI_BOLD + "║" + ANSI_RESET);
            System.out.println(ANSI_CYAN + ANSI_BOLD + "╚════════════════════════════════════════════════════════════════╝" + ANSI_RESET);
            System.out.println();

            try {
                int choice = inpInt.nextInt();

                if (choice == 1) {
                    gachaSystem.tampilkanDaftarHadiah();
                } else if (choice == 2) {
                    Item hasil = gachaSystem.pull(currentAccount);
                    if (hasil != null) {
                        System.out.println(ANSI_GREEN + "Kamu mendapatkan: " + hasil.getNamaItem() + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_RED_BRIGHT + "Gacha gagal. Gold tidak cukup, inventory penuh, atau data hadiah kosong." + ANSI_RESET);
                    }
                } else if (choice == 3) {
                    Item[] hasil = gachaSystem.pullTen(currentAccount);
                    if (hasil != null) {
                        System.out.println(ANSI_GREEN + "Hasil Pull 10x:");
                        for (int i = 0; i < hasil.length; i++) {
                            System.out.println((i + 1) + ". " + (hasil[i] == null ? "-" : hasil[i].getNamaItem()));
                        }
                    } else {
                        System.out.println(ANSI_RED_BRIGHT + "Gacha gagal. Gold tidak cukup, inventory penuh, atau data hadiah kosong." + ANSI_RESET);
                    }
                } else if (choice == 4) {
                    mainMenu();
                }
            } catch (Exception e) {
                System.out.print(INVALID_INPUT_BOX);
            }
        }
    }

    //MINI GAME MENU
    public void miniGameMenu() {
        while (true) {
            System.out.println();
            System.out.println("=== MINI GAME ===");
            System.out.println("1. Quiz Game");
            System.out.println("2. Spasi Game");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = inpInt.nextInt();

                if (choice == 1) {
                    inpInt.nextLine();
                    QuizGame.startGame(currentAccount);
                } else if (choice == 2) {
                    inpInt.nextLine();
                    SpaceGame.startGame(currentAccount);
                } else if (choice == 3) {
                    return;
                } else {
                    System.out.println(INVALID_INPUT_BOX);
                    miniGameMenu();
                }
            } catch (Exception e) {
                System.out.println(INVALID_INPUT_BOX);
                miniGameMenu();
            }
        }

    }

    // Simple map traversal menu (uses MapTraversal and Waypoint)
    public void mapTraversalMenu() {
        if (mapTraversal == null) {
            if (currentAccount != null && currentAccount.getAreaName() != null && !currentAccount.getAreaName().isEmpty()) {
                mapTraversal = new MapTraversal(currentAccount.getAreaName());
            } else {
                mapTraversal = new MapTraversal();
            }
            if (currentAccount != null && mapTraversal.areaSaatIni() != null) {
                currentAccount.setAreaName(mapTraversal.areaSaatIni().getNamaLokasi());
            }
        }

        // Initialize waypoint system if needed
        if (waypointSystem == null) {
            waypointSystem = new WaypointSystem();
        }
        
        // Add current area to waypoint
        Location currentLoc = mapTraversal.areaSaatIni();
        if (currentLoc != null) {
            waypointSystem.tambahLokasi(currentLoc);
            waypointSystem.setLokasiSaatIni(currentLoc);
        }

        while (true) {
            System.out.println();
            currentLoc = mapTraversal.areaSaatIni();
            System.out.println("Current area: " + (currentLoc == null ? "Unknown" : currentLoc.getNamaLokasi()));
            if (currentLoc != null && currentLoc.getDeskripsiLokasi() != null && !currentLoc.getDeskripsiLokasi().isEmpty()) {
                System.out.println(currentLoc.getDeskripsiLokasi());
            }
            System.out.println("1. Go to Next Area");
            System.out.println("2. Go back to previous area");
            System.out.println("3. Explore Current Area");
            System.out.println("4. Show Visited Path");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = inpInt.nextInt();
                inpInt.nextLine();
                
                if (choice == 1) {
                    if (currentAccount != null && currentAccount.getQuestTracker() != null) {
                        QuestTracker qt = currentAccount.getQuestTracker();
                        ArrayList<Quest> completed = qt.getRiwayatMisiSelesai();
                        int[] range = mapTraversal.getQuestIdRangeForCurrentArea();

                        if (range != null && !MapTraversal.areAllQuestsInRangeCompleted(completed, range[0], range[1])) {
                            int done = MapTraversal.countCompletedQuestsInRange(completed, range[0], range[1]);
                            System.out.println("Selesaikan semua main quest di " + currentLoc.getNamaLokasi() + " sebelum lanjut.");
                            System.out.println("  Main quest selesai: " + done + "/5");
                            continue;
                        }
                    }
                    
                    boolean moved = mapTraversal.goToNext();
                    if (moved) {
                        Location nextArea = mapTraversal.areaSaatIni();
                        String now = nextArea.getNamaLokasi();
                        if (currentAccount != null) currentAccount.setAreaName(now);
                        
                        if (nextArea != null) {
                            waypointSystem.tambahLokasi(nextArea);
                            waypointSystem.setLokasiSaatIni(nextArea);
                        }
                        
                        System.out.println("Teleported to: " + now);
                    } else {
                        System.out.println("Cannot teleport forward. No next area.");
                    }
                    
                } else if (choice == 2) {
                    if (currentLoc != null && currentLoc.getNamaLokasi().equalsIgnoreCase("Valerion")) {
                        System.out.println("Tidak bisa kembali ke area sebelumnya.");
                    } else {
                        Location prevArea = mapTraversal.kembali();
                        if (prevArea != null) {
                            String prevName = prevArea.getNamaLokasi();
                            if (currentAccount != null) currentAccount.setAreaName(prevName);
                            
                            if (waypointSystem != null) {
                                waypointSystem.setLokasiSaatIni(prevArea);
                            }
                            
                            System.out.println("Moved back to: " + prevName);
                        } else {
                            System.out.println("Tidak ada area sebelumnya untuk kembali.");
                        }
                    }
                    
                } else if (choice == 3) {
                    if (currentAccount == null) {
                        System.out.println("Belum ada akun aktif.");
                    } else {
                        Location curLoc = mapTraversal.areaSaatIni();
                        if (curLoc != null) {
                            List<Monster> allMonsters = DummyData.monster.getDummyMonsters();
                            MainQuest.tampilkanMusuhWilayah(curLoc.getNamaLokasi(), allMonsters);
                        }
                        System.out.println();
                        System.out.println("Mulai menjelajah...");
                        AdventureSystem adventureSystem = new AdventureSystem();
                        boolean lanjutExplore = adventureSystem.jalankanEksplorasi(currentAccount, mapTraversal, inpInt);
                        berikanHadiahQuestSelesai();
                        if (!lanjutExplore) {
                            return;
                        }
                    }
                    
                } else if (choice == 4) {
                    Stack<Location> history = mapTraversal.getRiwayatArea();
                    System.out.println("Visited path (oldest -> newest):");
                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + ". " + history.get(i).getNamaLokasi());
                    }
                    
                } else if (choice == 5) {
                    return;
                    
                } else {
                    System.out.println(INVALID_INPUT_BOX);
                }
            } catch (Exception e) {
                System.out.println(INVALID_INPUT_BOX);
                inpInt.nextLine();
            }
        }
    }


    // WAYPOINT MENU
    public void waypointMenu() {
        if (waypointSystem == null) {
            waypointSystem = new WaypointSystem();
            if (mapTraversal != null && mapTraversal.areaSaatIni() != null) {
                Location currentLoc = mapTraversal.areaSaatIni();
                waypointSystem.tambahLokasi(currentLoc);
                waypointSystem.setLokasiSaatIni(currentLoc);
            }
        }

        while (true) {
            System.out.println();
            System.out.println("1. Lihat daftar waypoint");
            System.out.println("2. Teleport");
            System.out.println("3. Back to main menu");
            System.out.print("Pilih: ");

            try {
                int choice = inpInt.nextInt();

                if (choice == 1) {
                    waypointSystem.tampilkanDaftar();

                } else if (choice == 2) {
                    if (waypointSystem.getLokasiTerbuka().isEmpty()) {
                        System.out.println("Belum ada area yang dapat di-teleport.");
                        continue;
                    }

                    waypointSystem.tampilkanDaftar();
                    System.out.print("Masukkan nomor area untuk di-teleport (0 untuk batal): ");

                    try {
                        int pilihan = inpInt.nextInt();
                        inpInt.nextLine();

                        if (pilihan == 0) {
                            System.out.println("Pembatalan waypoint.");
                            continue;
                        }

                        if (pilihan < 1 || pilihan > waypointSystem.getLokasiTerbuka().size()) {
                            System.out.println("Pilihan tidak valid!");
                            continue;
                        }

                        Location tujuan = waypointSystem.getLokasiTerbuka().get(pilihan - 1);
                        if (tujuan != null) {
                            boolean canTeleport = false;
                            for (Location wp : waypointSystem.getLokasiTerbuka()) {
                                if (wp != null && wp.getNamaLokasi().equalsIgnoreCase(tujuan.getNamaLokasi())) {
                                    canTeleport = true;
                                    break;
                                }
                            }

                            if (canTeleport) {
                                waypointSystem.setLokasiSaatIni(tujuan);
                                System.out.println("Teleport berhasil! Anda sekarang berada di " + tujuan.getNamaLokasi());

                                if (mapTraversal != null) {
                                    mapTraversal.goTo(tujuan.getNamaLokasi());
                                }
                                if (currentAccount != null) {
                                    currentAccount.setAreaName(tujuan.getNamaLokasi());
                                }
                            } else {
                                System.out.println("✗ Area tidak tersedia di waypoint Anda.");
                            }
                        }
                    } catch (NumberFormatException | InputMismatchException e) {
                        System.out.println("Input tidak valid!");
                        inpInt.nextLine();
                    }

                } else if (choice == 3) {
                    mainMenu();

                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih 1-3.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Input tidak valid!");
                inpInt.nextLine();
            }
        }
    }


    //FITUR CRAFTING
    public void craftingMenu() {
        if (craftingSystem == null) {
            craftingSystem = new CraftingSystem(new ArrayList<>(Arrays.asList(DummyData.craftingRecipe.getDummyRecipesArray())));
        }

        currentAccount.addItemToInventory(getIngredientById(30));
        currentAccount.addItemToInventory(getIngredientById(30));
        currentAccount.addItemToInventory(getIngredientById(32));
        currentAccount.addItemToInventory(getIngredientById(32));

        while (true) {
            craftingSystem.tampilkanResep();
            System.out.println("======================================");
            System.out.println("1. Craft Item");
            System.out.println("2. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = inpInt.nextInt();

                if (choice == 1) {
                    System.out.print("Masukkan index resep yang mau dicraft: ");
                    int index = inpInt.nextInt();
                    craftingSystem.craft(index, currentAccount);
                } else if (choice == 2) {
                    return;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }

        }
    }

    //FITUR FORGE
    public void forgeMenu() {
        if (forgeSystem == null) {
            forgeSystem = new ForgeSystem(currentAccount);
        } else {
            forgeSystem.setCurrentAccount(currentAccount);
        }

        currentAccount.addItemToInventory(weaponCatalog.get(1));
        currentAccount.addItemToInventory(getIngredientById(1));
        currentAccount.addItemToInventory(getIngredientById(1));
        currentAccount.addItemToInventory(getIngredientById(1));

        while (true) {
            forgeSystem.tampilkanEquipment(currentAccount);
            System.out.println("======================================");
            System.out.println("1. Upgrade Equipment");
            System.out.println("2. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = inpInt.nextInt();

                if (choice == 1) {
                    System.out.print("Masukkan index equipment yang mau diupgrade: ");
                    int index = inpInt.nextInt();
                    forgeSystem.upgrade(index, currentAccount);
                } else if (choice == 2) {
                    return;
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid.");
            }
        }
    }

    //FITUR SHOP
    private Shop shop1;
    private ArrayList<Item> shop1Items;

    private void shop1() {
        if (shop1 == null) {
            shop1Items = new ArrayList<>();
            shop1 = new Shop(shop1Items, "Shop 1", currentAccount);
            shop1Items.add(consumables.get(1));
            shop1Items.add(consumables.get(2));
            shop1Items.add(consumables.get(3));
            shop1Items.add(consumables.get(4));
            shop1Items.add(consumables.get(5));
            shop1Items.add(consumables.get(6));
            shop1Items.add(consumables.get(7));
            shop1Items.add(consumables.get(8));
        } else {
            shop1.setCurrentAccount(currentAccount);
        }
        shopMenu1();
    }

    private Item getIngredientById(int id) {
        if (id <= 100) {
            return ingredientAlamCatalog.get(id);
        }
        if (id <= 200) {
            return ingredientMonsterCatalog.get(id);
        }
        return ingredientConsumablesCatalog.get(id);
    }

    public void shopMenu1() {
        while (true) {
            shop1.tampilkanItem();
            System.out.println("======================================================");
            System.out.println("1. Buy Items");
            System.out.println("2. Sell Items");
            System.out.println("3. Display Item Details");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int choice = inpInt.nextInt();


                if (choice == 1) {
                    System.out.print("Enter the index of the item you want to buy: ");
                    int itemIndex = inpInt.nextInt();

                    System.out.print("Enter the amount: ");
                    int itemAmount = inpInt.nextInt();

                    shop1.beliItem(itemIndex, itemAmount, currentAccount);
                } else if (choice == 2) {
                    try {
                        if (currentAccount == null) {
                            System.out.println("Error: Account tidak tersedia.");
                            continue;
                        }

                        LinkedList<Item> inventory = currentAccount.getInventory();
                        if (inventory == null || inventory.isEmpty()) {
                            System.out.println("Inventory Anda kosong! Tidak ada item untuk dijual.");
                            continue;
                        }

                        Inventory inventoryPlayer = new Inventory(currentAccount);
                        inventoryPlayer.displayInventory();
                        System.out.println();
                        System.out.print("Enter the index of the item you want to sell: ");

                        if (!inpInt.hasNextInt()) {
                            System.out.println("Input tidak valid. Masukkan angka!");
                            inpInt.nextLine();
                            continue;
                        }

                        int itemIndex = inpInt.nextInt();
                        inpInt.nextLine();

                        if (itemIndex < 1 || itemIndex > inventory.size()) {
                            System.out.println("Index tidak valid! (Valid: 1 - " + inventory.size() + ")");
                            continue;
                        }

                        shop1.sellItem(itemIndex, currentAccount);
                    } catch (NullPointerException e) {
                        System.out.println("Error: Data tidak tersedia untuk penjualan!");
                    } catch (Exception e) {
                        System.out.println("Error saat menjual item: " + e.getMessage());
                    }
                } else if (choice == 3) {
                    System.out.print("Enter the index of the item you want to see the details of: ");
                    int itemIndex = inpInt.nextInt();
                    shop1.displayItemDetail(itemIndex);
                } else if (choice == 4) {
                    mainMenu();
                } else {
                    System.out.println("Invalid Input. Please pick according to the index");
                }
            } catch (Exception ignored) {
                System.out.println("Invalid Input");
            }
        }
    }

    //FITUR INVENTORY
    public void inventoryMenu() {
        currentAccount.addItemToInventory(getIngredientById(10));
        currentAccount.addItemToInventory(getIngredientById(100));
        currentAccount.addItemToInventory(consumables.get(44));
        currentAccount.addItemToInventory(armorCatalog.get(25));

        while (true) {
            Inventory inventoryView = new Inventory(currentAccount);
            inventoryView.displayInventory();
            System.out.println();
            System.out.println("=== INVENTORY MENU===");
            System.out.println("1. Search Item");
            System.out.println("2. View Item Details");
            System.out.println("3. Use Items");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            try {
                int invChoice = inpInt.nextInt();

                if (invChoice == 1) {
                    System.out.print("Masukkan kata kunci untuk mencari item: ");
                    String keyword = inpStr.nextLine();
                    inventoryView.cariItem(keyword);
                } else if (invChoice == 2) {
                    System.out.print("Masukkan index item: ");
                    int index = inpInt.nextInt();

                    inventoryView.displayItemDetail(currentAccount.getInventory().get(index - 1).getNamaItem());
                } else if (invChoice == 3) {
                    System.out.print("Masukkan index item yang ingin dipakai: ");
                    String itemLine = inpStr.nextLine();
                    int itemIndex;
                    try {
                        itemIndex = Integer.parseInt(itemLine.trim());
                    } catch (Exception ex) {
                        System.out.println("Input tidak valid. Kembali ke menu inventory.");
                        continue;
                    }

                    System.out.println("Pilih member yang ingin digunakan itemnya:");
                    PlayerCharacter[] acctParty = currentAccount == null ? null : currentAccount.getParty();
                    if (acctParty == null || acctParty.length == 0) {
                        System.out.println("Party kosong.");
                    } else {
                        for (int i = 0; i < acctParty.length; i++) {
                            PlayerCharacter pc = acctParty[i];
                            if (pc == null) continue;
                            System.out.println((i + 1) + ". " + pc.getNama() +
                                    " | Class: " + pc.getNamaClass() +
                                    " | Level: " + pc.getLevel() +
                                    " | HP: " + pc.getCurrentHp() + "/" + pc.getMaxHp() +
                                    " | MP: " + pc.getCurrentMp() + "/" + pc.getMaxMp() +
                                    " | STR: " + pc.getKekuatan() +
                                    " | DEF: " + pc.getDefense() +
                                    " | EXP: " + pc.getCurrentExp() + "/" + pc.getMaxExp() +
                                    " | Nirlelah: " + pc.isStatusTubuhNirlelah() +
                                    " | Weapon: " + (pc.getCurrentWeapon() == null ? "None" : pc.getCurrentWeapon().getNamaItem()) +
                                    " | Armor: " + (pc.getCurrentArmor() == null ? "None" : pc.getCurrentArmor().getNamaItem()) +
                                    " | Accessory: " + (pc.getCurrentAccessory() == null ? "None" : pc.getCurrentAccessory().getNamaItem()));
                        }
                    }

                    int targetIndex = -1;
                    while (true) {
                        System.out.print("Masukkan index member (0 untuk batal): ");
                        String targetLine = inpStr.nextLine();
                        if (targetLine == null || targetLine.trim().isEmpty()) {
                            System.out.println("Input tidak valid.");
                            continue;
                        }
                        try {
                            targetIndex = Integer.parseInt(targetLine.trim());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Input tidak valid.");
                            continue;
                        }
                        if (targetIndex == 0) {
                            System.out.println("Batal menggunakan item.");
                            break;
                        }
                        if (targetIndex < 1 || targetIndex > acctParty.length || acctParty[targetIndex - 1] == null) {
                            System.out.println("Pilihan tidak valid.");
                            continue;
                        }
                        break;
                    }

                    if (targetIndex == 0) continue;
                    inventoryView.useItem(itemIndex, targetIndex);
                } else if (invChoice == 4) {
                    mainMenu();
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                }
            } catch (Exception e) {
                try {
                } catch (Exception ignored) {
                }
                System.out.println("Invalid input");
            }
        }
    }


    private void questBoardMenu() {
        if (currentAccount == null) {
            System.out.println("Belum login.");
            return;
        }

        String areaNow = mapTraversal != null && mapTraversal.areaSaatIni() != null ? mapTraversal.areaSaatIni().getNamaLokasi() : "";
        QuestTracker qt = currentAccount.getQuestTracker();
        
        while (true) {
            System.out.println();
            System.out.println("═══════════════════════════════════════");
            System.out.println("          QUEST BOARD MENU");
            System.out.println("═══════════════════════════════════════");
            System.out.println("[1] Main Quest Available");
            System.out.println("[2] Sub Quest Available");
            System.out.println("[3] Accept Reward");
            System.out.println("[4] Back to Main Menu");
            System.out.print("Pilihan: ");
            
            try {
                int choice = inpInt.nextInt();
                
                if (choice == 1) {
                    // Display main quest board
                    MainQuest.displayQuestBoardForArea(qt, areaNow, inpStr);
                } else if (choice == 2) {
                    // Display sub quest board
                    SubQuest.displayQuestBoardForArea(qt, areaNow, inpStr);
                } else if (choice == 3) {
                    // Accept rewards for completed quests
                    acceptQuestRewards();
                } else if (choice == 4) {
                    // Back to main menu
                    break;
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid.");
                inpStr.nextLine(); // consume invalid input
            }
        }
    }

    private void acceptQuestRewards() {
        if (currentAccount == null) {
            System.out.println("Belum login.");
            return;
        }

        QuestTracker qt = currentAccount.getQuestTracker();
        if (qt == null) {
            System.out.println("\u001B[33mBelum ada quest tracker.\u001B[0m");
            return;
        }

        ArrayList<Quest> riwayat = qt.getRiwayatMisiSelesai();
        if (riwayat == null) {
            System.out.println("\u001B[33mTidak ada quest yang selesai.\u001B[0m");
            return;
        }

        // Find completed quests that haven't been rewarded yet
        ArrayList<Quest> completedQuests = new ArrayList<>();
        for (Quest q : riwayat) {
            if (q != null && q.getStatusQuest() == enums.StatusQuest.COMPLETED) {
                completedQuests.add(q);
            }
        }

        if (completedQuests.isEmpty()) {
            System.out.println("\u001B[33mTidak ada hadiah yang siap diambil.\u001B[0m");
            return;
        }

        System.out.println("\u001B[36m\u001B[1m\n╔═══════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[36m\u001B[1m║\u001B[0m         QUEST REWARDS AVAILABLE         \u001B[36m\u001B[1m║\u001B[0m");
        System.out.println("\u001B[36m\u001B[1m╚═══════════════════════════════════════╝\u001B[0m");

        for (int i = 0; i < completedQuests.size(); i++) {
            Quest q = completedQuests.get(i);
            System.out.println((i + 1) + ". " + q.getNamaQuest() + " - Status: " + q.getStatusQuest());
        }

        System.out.print("Pilih quest yang ingin ambil hadiahnya (0 untuk batal): ");
        try {
            int choice = inpInt.nextInt();
            if (choice < 1 || choice > completedQuests.size()) {
                System.out.println("Pembatalan.");
                return;
            }

            Quest picked = completedQuests.get(choice - 1);
            
            // Give reward based on quest type
            if (picked instanceof MainQuest) {
                MainQuest mq = (MainQuest) picked;
                MainQuest.berikanHadiah(mq, currentAccount, ingredientAlamCatalog, ingredientMonsterCatalog, consumables);
            } else if (picked instanceof SubQuest) {
                SubQuest sq = (SubQuest) picked;
                // Give basic reward for subquest
                int gold = sq.getHadiahKoin();
                if (gold > 0) {
                    currentAccount.setTotalGold(currentAccount.getTotalGold() + gold);
                }
                System.out.println("\u001B[32m\u001B[1m╔══════════════════════════════════════╗\u001B[0m");
                System.out.println("\u001B[32m\u001B[1m║\u001B[0m\u001B[33m\u001B[1m         QUEST SELESAI! MENDAPATKAN:      \u001B[32m\u001B[1m║\u001B[0m");
                System.out.println("\u001B[32m\u001B[1m╚══════════════════════════════════════╝\u001B[0m");
                if (gold > 0) {
                    System.out.println("  \u001B[33m" + gold + " Gold\u001B[0m");
                }
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid.");
            inpStr.nextLine();
        }
    }

    private void berikanHadiahQuestSelesai() {
        if (currentAccount == null) return;
        QuestTracker qt = currentAccount.getQuestTracker();
        if (qt == null) return;

        ArrayList<Quest> riwayat = qt.getRiwayatMisiSelesai();
        if (riwayat == null) return;

        for (Quest q : riwayat) {
            if (q instanceof MainQuest) {
                MainQuest mq = (MainQuest) q;
                if (!questRewarded.contains(mq.getIdQuest())) {
                    MainQuest.berikanHadiah(mq, currentAccount, ingredientAlamCatalog, ingredientMonsterCatalog, consumables);
                    questRewarded.add(mq.getIdQuest());
                }
            }
        }
    }

    private void ensureQuestTrackerCatalog(AccountProfile account) {
        if (account == null) {
            return;
        }

        QuestTracker qt = account.getQuestTracker();
        if (qt == null) {
            // For new accounts: do NOT pre-populate subquests. Players must take subquests from the Quest Board.
            qt = new QuestTracker(new ArrayList<>(mainquest.getDummyMainQuest()), new ArrayList<SubQuest>(), new ArrayList<Quest>());
            account.setQuestTracker(qt);
            return;
        }

        if (qt.getDaftarMainQuestAktif() == null || qt.getDaftarMainQuestAktif().isEmpty()) {
            qt.setDaftarMainQuestAktif(new ArrayList<>(mainquest.getDummyMainQuest()));
        }
        // Ensure subquest list exists but do not populate it automatically. Leave empty until player accepts from Quest Board.
        if (qt.getDaftarSubQuestAktif() == null) {
            qt.setDaftarSubQuestAktif(new ArrayList<SubQuest>());
        }
        if (qt.getRiwayatMisiSelesai() == null) {
            qt.setRiwayatMisiSelesai(new ArrayList<Quest>());
        }
    }

    private int countQuestsByStatus(ArrayList<? extends Quest> quests, enums.StatusQuest targetStatus) {
        if (quests == null || targetStatus == null) {
            return 0;
        }

        int count = 0;
        for (Quest quest : quests) {
            if (quest != null && quest.getStatusQuest() == targetStatus) {
                count++;
            }
        }
        return count;
    }

    //Fitur 3.2.5 Profil Akun
    public void accProfileMenu() {
        while (true) {
            party = (currentAccount != null && currentAccount.getParty() != null) ? currentAccount.getParty() : new PlayerCharacter[0];
            System.out.println();
            System.out.println("========== PROFIL AKUN ==========");
            System.out.println("Username    : " + currentAccount.getUsername());
            System.out.println("Total Gold  : " + currentAccount.getTotalGold());
            System.out.println("Total Playtime : " + currentAccount.getTotalPlaytimeFormatted() + " (H:MM)");
            System.out.println("Area Name   : " + (currentAccount.getAreaName() == null || currentAccount.getAreaName().isEmpty() ? "Belum menjelajah" : currentAccount.getAreaName()));
            System.out.println("=================================");
            System.out.println();

            System.out.println("--- PARTY ---");
            if (currentAccount == null || party == null || party.length == 0) {
                System.out.println("Party is empty.");
            } else {
                for (int i = 0; i < party.length; i++) {
                    PlayerCharacter pc = party[i];
                    if (pc == null) {
                        continue;
                    }
                    System.out.println((i + 1) + ". " + pc.getNama() +
                            " | Class: " + pc.getNamaClass() +
                            " | Level: " + pc.getLevel() +
                            " | HP: " + pc.getCurrentHp() + "/" + pc.getMaxHp() +
                            " | MP: " + pc.getCurrentMp() + "/" + pc.getMaxMp() +
                            " | STR: " + pc.getKekuatan() +
                            " | DEF: " + pc.getDefense() +
                            " | EXP: " + pc.getCurrentExp() + "/" + pc.getMaxExp() +
                            " | Nirlelah: " + pc.isStatusTubuhNirlelah() +
                            " | Weapon: " + (pc.getCurrentWeapon() == null ? "None" : pc.getCurrentWeapon().getNamaItem()) +
                            " | Armor: " + (pc.getCurrentArmor() == null ? "None" : pc.getCurrentArmor().getNamaItem()) +
                            " | Accessory: " + (pc.getCurrentAccessory() == null ? "None" : pc.getCurrentAccessory().getNamaItem()));
                }
            }

            System.out.println();
            System.out.println("--- INVENTORY ---");
            LinkedList<Item> inventory = currentAccount.getInventory();
            if (inventory == null || inventory.isEmpty()) {
                System.out.println("Inventory kosong.");
            } else {
                for (int i = 0; i < inventory.size(); i++) {
                    Item item = inventory.get(i);
                    System.out.println((i + 1) + ". " + item.getNamaItem() +
                            " | ID: " + item.getIdItem() +
                            " | Harga Jual: " + item.getHargaJual() +
                            " | Deskripsi: " + item.getDeskripsi());
                }
            }

            System.out.println();
            System.out.println("--- QUEST TRACKER ---");
            QuestTracker qt = currentAccount.getQuestTracker();
            if (qt == null) {
                System.out.println("Belum ada quest tracker.");
            } else {
                System.out.println("Main Quest Aktif : " + countQuestsByStatus(qt.getDaftarMainQuestAktif(), enums.StatusQuest.ONGOING));
                System.out.println("Sub Quest Aktif  : " + countQuestsByStatus(qt.getDaftarSubQuestAktif(), enums.StatusQuest.ONGOING));
                System.out.println("Riwayat Quest    : " + (qt.getRiwayatMisiSelesai() == null ? 0 : qt.getRiwayatMisiSelesai().size()));
            }

            System.out.println();
            System.out.println("[1] Edit username");
            System.out.println("[2] Lihat detail karakter");
            System.out.println("[3] Edit nama karakter");
            System.out.println("[4] Kembali");
            System.out.print("Pilihan: ");
            try {
                int choice = inpInt.nextInt();

                try {

                } catch (Exception ignored) {
                }
                if (choice == 1) {
                    System.out.print("Masukkan username baru: ");
                    String usernameBaru = inpStr.nextLine();
                    usernameBaru = usernameBaru == null ? "" : usernameBaru.trim();
                    if (usernameBaru.isEmpty()) {
                        System.out.println("Username tidak boleh kosong");
                        continue;
                    }

                    if (checkUsername(usernameBaru)) {
                        System.out.println("Username sudah terdaftar, silakan pilih username lain");
                        continue;
                    }

                    String usernameOld = currentAccount != null ? currentAccount.getUsername() : usernameLogin;

                    if (changeUsername(usernameOld, usernameBaru)) {
                        currentAccount.setUsername(usernameBaru);
                        usernameLogin = usernameBaru;
                        System.out.println("Username berhasil diubah menjadi '" + usernameBaru + "'");
                    } else {
                        System.out.println("Gagal mengubah username.");
                    }
                } else if (choice == 2) {
                    if (party == null || party.length == 0) {
                        System.out.println("Party is empty.");
                    } else {
                        for (int i = 0; i < party.length; i++) {
                            if (party[i] == null) continue;
                            System.out.println((i + 1) + ". " + party[i].getNama() + " | Class: " + party[i].getNamaClass());
                        }
                        System.out.print("Pilihan: ");
                        try {
                            int choiceDetail = inpInt.nextInt();

                            try {

                            } catch (Exception ignored) {
                            }
                            if (choiceDetail < 1 || choiceDetail > party.length) {
                                System.out.println("Pilihan tidak valid.");
                                continue;
                            } else {
                                PlayerCharacter pc = party[choiceDetail - 1];
                                if (pc == null) {
                                    System.out.println("Pilihan tidak valid.");
                                    continue;
                                }
                                System.out.println("\n--- Detail Karakter ---");
                                System.out.println("Nama       : " + pc.getNama());
                                System.out.println("Class      : " + pc.getNamaClass());
                                System.out.println("Level      : " + pc.getLevel());
                                System.out.println("HP         : " + pc.getCurrentHp() + "/" + pc.getMaxHp());
                                System.out.println("MP         : " + pc.getCurrentMp() + "/" + pc.getMaxMp());
                                System.out.println("STR        : " + pc.getKekuatan());
                                System.out.println("DEF        : " + pc.getDefense());
                                System.out.println("EXP        : " + pc.getCurrentExp() + "/" + pc.getMaxExp());
                                System.out.println("Nirlelah   : " + (pc.isStatusTubuhNirlelah() ? "Ya" : "Tidak"));
                                Equipment weapon = pc.getCurrentWeapon();
                                Equipment armor = pc.getCurrentArmor();
                                Equipment accessory = pc.getCurrentAccessory();
                                System.out.println("Weapon     : " + (weapon == null ? "Tidak ada" : weapon.getNamaItem() + " (ID: " + weapon.getIdItem() + ")"));
                                System.out.println("Armor      : " + (armor == null ? "Tidak ada" : armor.getNamaItem() + " (ID: " + armor.getIdItem() + ")"));
                                System.out.println("Accessory  : " + (accessory == null ? "Tidak ada" : accessory.getNamaItem() + " (ID: " + accessory.getIdItem() + ")"));
                            }
                        } catch (Exception e) {
                            try {

                            } catch (Exception ignored) {
                            }
                            System.out.println("Input tidak valid.");
                            continue;
                        }
                    }
                } else if (choice == 3) {
                    if (party == null || party.length == 0) {
                        System.out.println("Party is empty.");
                    } else {
                        for (int i = 0; i < party.length; i++) {
                            if (party[i] == null) continue;
                            System.out.println((i + 1) + ". " + party[i].getNama() + " | Class: " + party[i].getNamaClass());
                        }
                        System.out.print("Pilihan: ");
                        try {
                            int choiceEditNamaChara = inpInt.nextInt();

                            try {

                            } catch (Exception ignored) {
                            }
                            if (choiceEditNamaChara < 1 || choiceEditNamaChara > party.length) {
                                System.out.println("Pilihan tidak valid.");
                                continue;
                            } else {
                                PlayerCharacter pc = party[choiceEditNamaChara - 1];
                                if (pc == null) {
                                    System.out.println("Pilihan tidak valid.");
                                    continue;
                                }
                                System.out.print("Masukkan nama baru untuk " + pc.getNama() + ": ");
                                String namaBaru = inpStr.nextLine();
                                namaBaru = namaBaru == null ? "" : namaBaru.trim();
                                if (namaBaru.isEmpty()) {
                                    System.out.println("Nama karakter tidak boleh kosong");
                                    continue;
                                }
                                pc.setNama(namaBaru);
                                System.out.println("Nama karakter berhasil diubah menjadi '" + namaBaru + "'");
                            }
                        } catch (Exception e) {
                            try {

                            } catch (Exception ignored) {
                            }
                            System.out.println("Input tidak valid.");
                            continue;
                        }
                    }
                } else if (choice == 4) {
                    System.out.println();
                    return;
                } else {
                    System.out.println("Pilihan tidak valid.");
                    continue;
                }
            } catch (Exception e) {
                try {

                } catch (Exception ignored) {
                }
                System.out.println("Input tidak valid.");
                continue;
            }
        }
    }

    private void saveCurrentAccountWithRecipes() {
        if (currentAccount == null) {
            return;
        }
        ensureQuestTrackerCatalog(currentAccount);
        saveload.save(currentAccount);
    }
}

