package main;

import DummyData.*;

import java.io.*;
import java.util.*;
import java.nio.file.Files;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import models.item.Item;
import systems.craft.ForgeSystem;
import systems.craft.CraftingSystem;
import systems.inventory.Inventory;
import systems.save.SaveLoadSystem;
import systems.shop.Shop;

public class App {
    public Scanner inpInt = new Scanner(System.in);
    public Scanner inpStr = new Scanner(System.in);

    Inventory inventory;
    //ensiklopedia
    private HashMap<Integer, Item> ingredientCatalog = inqredients.getDummyIngredientsMap();
    private HashMap<Integer, Item> consumables = DummyData.consumables.getDummyConsumablesMap();
    private HashMap<Integer, Item> equipment = DummyData.equipment.getEquipmentMap();
    private HashMap<Integer, systems.craft.craftingRecipe> craftingRecipes = DummyData.craftingRecipe.getDummyRecipesMap();
    private ArrayList<systems.craft.craftingRecipe> resepUser;
    private static final String CRAFTING_SECTION = "[CRAFTING INFO]";
    private static final int STARTER_RECIPE_COUNT = 11;
    // Current logged-in account
    private AccountProfile currentAccount;
    private ForgeSystem forgeSystem;
    private CraftingSystem craftingSystem;
    private PlayerCharacter[] party = new PlayerCharacter[4];

    //utk nyimpan akun
    String ACCOUNT_FILE = "src/main/accounts.txt";
    String DELIMITER = ":";
    SaveLoadSystem saveload = new SaveLoadSystem();
    String usernameLogin = "";

    //FITUR 3.2.2 LOGIN REGISTER
    public void startMenu() {
        while (true) {
            System.out.println("\n=============================");
            System.out.println("       NUTRI TALE            ");
            System.out.println("=============================");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Keluar");
            System.out.print("Pilihan: ");

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
                    this.currentAccount = loadedAccount;
                    this.resepUser = loadResepUserFromSave(usernameLogin);
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
                    this.resepUser = createStarterResepUser();
                    this.craftingSystem = null;
                    try {
                        saveCurrentAccountWithRecipes();
                    } catch (Exception ex) {
                        System.out.println("Belum ada file save. 4 karakter CLASSLESS baru telah dibuat (gagal autosave: " + ex.getMessage() + ")");
                    }

                    if (ingredientCatalog.isEmpty()) {
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

    //MAIN MENU NUTRITALE
    public void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║                  N U T R I T A L E                 ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║                  M A I N   M E N U                 ║");
        System.out.println("╠═══════════════════════════╦════════════════════════╣");
        System.out.println("║ [ 1] Play                 ║ [ 2] Quest Tracker     ║");
        System.out.println("║ [ 3] Inventory            ║ [ 4] Shop              ║");
        System.out.println("║ [ 5] Crafting             ║ [ 6] Forge             ║");
        System.out.println("║ [ 7] Quest Board          ║ [ 8] Mini Game         ║");
        System.out.println("║ [ 9] Encyclopedia         ║ [10] Skill Tree        ║");
        System.out.println("║ [11] Class Tree           ║ [12] Gacha             ║");
        System.out.println("║ [13] Waypoint             ║ [14] Profil Akun       ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║ [15] Save Game                                     ║");
        System.out.println("║ [16] Logout                                        ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

    public void mainMenu() {
        while (true) {
            displayMainMenu();
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = inpInt.nextInt();

                if (choice == 1) {

                } else if (choice == 2) {

                } else if (choice == 3) {
                    inventoryMenu();
                } else if (choice == 4) {
                    shop1();
                } else if (choice == 5) {
                    craftingMenu();
                } else if (choice == 6) {
                    forgeMenu();
                } else if (choice == 7) {

                } else if (choice == 8) {

                } else if (choice == 9) {

                } else if (choice == 10) {

                } else if (choice == 11) {

                } else if (choice == 12) {

                } else if (choice == 13) {

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
                    resepUser = null;
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



    //FITUR CRAFTING
    public void craftingMenu(){
        if (craftingSystem == null) {
            craftingSystem = new CraftingSystem(resepUser != null ? resepUser : createStarterResepUser());
        }

        currentAccount.getInventory().add(ingredientCatalog.get(30));
        currentAccount.getInventory().add(ingredientCatalog.get(30));
        currentAccount.getInventory().add(ingredientCatalog.get(32));
        currentAccount.getInventory().add(ingredientCatalog.get(32));

        while (true){
            craftingSystem.tampilkanResep();
            System.out.println("======================================");
            System.out.println("1. Craft Item");
            System.out.println("2. Back to Main Menu");
            System.out.print("Choose an option: ");

            try{
                int choice = inpInt.nextInt();

                if(choice == 1) {
                    System.out.print("Masukkan index resep yang mau dicraft: ");
                    int index = inpInt.nextInt();
                    craftingSystem.craft(index, currentAccount);
                }else if(choice == 2){
                    return;
                }else {
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                }
            }catch (Exception e){
                System.out.println("Invalid Input");
            }

        }
    }

    //FITUR FORGE
    public void forgeMenu(){
        if (forgeSystem == null) {
            forgeSystem = new ForgeSystem(currentAccount);
        } else {
            forgeSystem.setCurrentAccount(currentAccount);
        }

        currentAccount.getInventory().add(equipment.get(1));
        currentAccount.getInventory().add(ingredientCatalog.get(201));
        currentAccount.getInventory().add(ingredientCatalog.get(201));
        currentAccount.getInventory().add(ingredientCatalog.get(201));

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

    public void shopMenu1(){
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


                if(choice == 1) {
                    System.out.print("Enter the index of the item you want to buy: ");
                    int itemIndex = inpInt.nextInt();

                    System.out.print("Enter the amount: ");
                    int itemAmount = inpInt.nextInt();

                    shop1.beliItem(itemIndex, itemAmount, currentAccount);
                }else if (choice == 2) {
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
                }else if (choice == 3) {
                    System.out.print("Enter the index of the item you want to see the details of: ");
                    int  itemIndex = inpInt.nextInt();
                    shop1.displayItemDetail(itemIndex);
                }else if (choice == 4) {
                    mainMenu();
                } else {
                    System.out.println("Invalid Input. Please pick according to the index");
                }
            }catch (Exception ignored) {
                System.out.println("Invalid Input");
            }
        }
    }

    //FITUR INVENTORY
    public void inventoryMenu(){
        currentAccount.getInventory().add(ingredientCatalog.get(10));
        currentAccount.getInventory().add(ingredientCatalog.get(100));
        currentAccount.getInventory().add(consumables.get(44));
        currentAccount.getInventory().add(equipment.get(25));

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

                if(invChoice == 1) {
                    System.out.print("Masukkan kata kunci untuk mencari item: ");
                    String keyword = inpStr.nextLine();
                    inventoryView.cariItem(keyword);
                }else if(invChoice == 2){
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
                        continue;
                    }
                    for (int i = 0; i < acctParty.length; i++) {
                        PlayerCharacter pc = acctParty[i];
                        if (pc == null) continue;
                        System.out.println((i + 1) + ". " + pc.getNama() + " | HP: " + pc.getCurrentHp() + "/" + pc.getMaxHp() + " | MP: " + pc.getCurrentMp() + "/" + pc.getMaxMp());
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
                }else if(invChoice == 4){
                    mainMenu();
                }else{
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                }
            } catch (Exception e) {
                try {  } catch (Exception ignored) {}
                System.out.println("Invalid input");
            }
        }
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

//            System.out.println("--- PARTY ---");
//            if (currentAccount == null || party == null || party.length == 0) {
//                System.out.println("Party is empty.");
//            } else {
//                for (int i = 0; i < party.length; i++) {
//                    PlayerCharacter pc = party[i];
//                    if (pc == null) {
//                        continue;
//                    }
//                    System.out.println((i + 1) + ". " + pc.getNama() +
//                            " | Class: " + pc.getNamaClass() +
//                            " | Level: " + pc.getLevel() +
//                            " | HP: " + pc.getCurrentHp() + "/" + pc.getMaxHp() +
//                            " | MP: " + pc.getCurrentMp() + "/" + pc.getMaxMp() +
//                            " | STR: " + pc.getKekuatan() +
//                            " | DEF: " + pc.getDefense() +
//                            " | EXP: " + pc.getCurrentExp() + "/" + pc.getMaxExp() +
//                            " | Nirlelah: " + pc.isStatusTubuhNirlelah());
//                }
//            }

//            System.out.println();
//            System.out.println("--- INVENTORY ---");
//            LinkedList<Item> inventory = currentAccount.getInventory();
//            if (inventory == null || inventory.isEmpty()) {
//                System.out.println("Inventory kosong.");
//            } else {
//                for (int i = 0; i < inventory.size(); i++) {
//                    Item item = inventory.get(i);
//                    System.out.println((i + 1) + ". " + item.getNamaItem() +
//                            " | ID: " + item.getIdItem() +
//                            " | Harga Jual: " + item.getHargaJual() +
//                            " | Deskripsi: " + item.getDeskripsi());
//                }
//            }

//            System.out.println();
//            System.out.println("--- QUEST TRACKER ---");
//            QuestTracker qt = currentAccount.getQuestTracker();
//            if (qt == null) {
//                System.out.println("Belum ada quest tracker.");
//            } else {
//                System.out.println("Main Quest Aktif : " + (qt.getDaftarMainQuestAktif() == null ? 0 : qt.getDaftarMainQuestAktif().size()));
//                System.out.println("Sub Quest Aktif  : " + (qt.getDaftarSubQuestAktif() == null ? 0 : qt.getDaftarSubQuestAktif().size()));
//                System.out.println("Riwayat Quest    : " + (qt.getRiwayatMisiSelesai() == null ? 0 : qt.getRiwayatMisiSelesai().size()));
//            }

//            System.out.println();
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

    private ArrayList<systems.craft.craftingRecipe> createStarterResepUser() {
        ArrayList<systems.craft.craftingRecipe> starter = new ArrayList<>();
        systems.craft.craftingRecipe[] defaults = DummyData.craftingRecipe.getDummyRecipesArray();
        if (defaults == null) {
            return starter;
        }

        for (int i = 0; i < defaults.length && i < STARTER_RECIPE_COUNT; i++) {
            if (defaults[i] != null) {
                starter.add(defaults[i]);
            }
        }
        return starter;
    }

    private ArrayList<systems.craft.craftingRecipe> loadResepUserFromSave(String username) {
        ArrayList<systems.craft.craftingRecipe> loaded = new ArrayList<>();
        File saveFile = new File(saveload.SAVE_FOLDER + username + saveload.extension);
        if (!saveFile.exists()) {
            return createStarterResepUser();
        }

        boolean inCraftSection = false;
        try (BufferedReader br = new BufferedReader(new FileReader(saveFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                if (line.equals(CRAFTING_SECTION)) {
                    inCraftSection = true;
                    continue;
                }
                if (line.startsWith("[") && !line.equals(CRAFTING_SECTION)) {
                    inCraftSection = false;
                    continue;
                }
                if (inCraftSection && line.startsWith("resep=")) {
                    try {
                        int recipeId = Integer.parseInt(line.substring("resep=".length()).trim());
                        systems.craft.craftingRecipe recipe = craftingRecipes.get(recipeId);
                        if (recipe != null) {
                            loaded.add(recipe);
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca crafting save: " + e.getMessage());
        }

        return loaded.isEmpty() ? createStarterResepUser() : loaded;
    }

    private void saveResepUserToFile() {
        if (currentAccount == null || resepUser == null) {
            return;
        }

        File saveFile = new File(saveload.SAVE_FOLDER + currentAccount.getUsername() + saveload.extension);
        if (!saveFile.exists()) {
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile, true))) {
            bw.newLine();
            bw.write(CRAFTING_SECTION);
            bw.newLine();
            for (systems.craft.craftingRecipe recipe : resepUser) {
                if (recipe != null && recipe.getResultItem() != null) {
                    bw.write("resep=" + recipe.getResultItem().getIdItem());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan crafting save: " + e.getMessage());
        }
    }

    private void saveCurrentAccountWithRecipes() {
        if (currentAccount == null) {
            return;
        }
        saveload.save(currentAccount);
        saveResepUserToFile();
    }
}

