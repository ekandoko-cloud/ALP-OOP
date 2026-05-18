package main;

import java.io.*;
import java.util.*;
import java.nio.file.Files;

import models.account.AccountProfile;
import models.character.PlayerCharacter;
import models.item.Item;
import systems.save.SaveLoadSystem;

public class App {
    public Scanner inpInt = new Scanner(System.in);
    public Scanner inpStr = new Scanner(System.in);

    // Current logged-in account
    private AccountProfile currentAccount;
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

            try {
                choice = inpInt.nextInt();

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
                    startMenu();
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Silakan masukkan yang sesuai.");
                System.out.println();
                startMenu();
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
            startMenu();
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
                    try {
                        saveload.save(currentAccount);
                    } catch (Exception ex) {
                        System.out.println("Belum ada file save. 4 karakter CLASSLESS baru telah dibuat (gagal autosave: " + ex.getMessage() + ")");
                    }
                }
                // keep global party in sync after login
                party = (currentAccount != null && currentAccount.getParty() != null)
                        ? currentAccount.getParty()
                        : new PlayerCharacter[0];

                mainMenu();
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
            int choice = 0;

            try {
                choice = inpInt.nextInt();

                if (choice == 1) {

                } else if (choice == 2) {

                } else if (choice == 3) {
//                    System.out.println("--- INVENTORY ---");
//                    LinkedList<Item> inventory = currentAccount.getInventory();
//                    if (inventory == null || inventory.isEmpty()) {
//                        System.out.println("Inventory kosong.");
//                    } else {
//                        for (int i = 0; i < inventory.size(); i++) {
//                            Item item = inventory.get(i);
//                            System.out.println((i + 1) + ". " + item.getNamaItem() +
//                                    " | ID: " + item.getIdItem() +
//                                    " | Harga Jual: " + item.getHargaJual() +
//                                    " | Deskripsi: " + item.getDeskripsi());
//                        }
//                    }
                } else if (choice == 4) {

                } else if (choice == 5) {

                } else if (choice == 6) {

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
                    saveload.save(currentAccount);
                    System.out.println("Game saved successfully.");
                } else if (choice == 16) {
                    System.out.println("Logging out...");
                    currentAccount = null;
                    usernameLogin = "";
                    party = new PlayerCharacter[0];
                    startMenu();
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih sesuai dengan index yang tersedia.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Silakan masukkan angka yang sesuai.");
                continue;
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
            System.out.println("Total Playtime : " + currentAccount.getTotalPlaytime() + " menit");
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
                if (choice == 1) {
                    System.out.print("Masukkan username baru: ");
                    String usernameBaru = inpStr.nextLine();
                    usernameBaru = usernameBaru == null ? "" : usernameBaru.trim();
                    if (usernameBaru.isEmpty()) {
                        System.out.println("Username tidak boleh kosong");
                        accProfileMenu();
                    }

                    if (checkUsername(usernameBaru)) {
                        System.out.println("Username sudah terdaftar, silakan pilih username lain");
                        accProfileMenu();
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
                            if (choiceDetail < 1 || choiceDetail > party.length) {
                                System.out.println("Pilihan tidak valid.");
                            } else {
                                PlayerCharacter pc = party[choiceDetail - 1];
                                if (pc == null) {
                                    System.out.println("Pilihan tidak valid.");
                                    accProfileMenu();
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
                            if (choiceEditNamaChara < 1 || choiceEditNamaChara > party.length) {
                                System.out.println("Pilihan tidak valid.");
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
                                    accProfileMenu();
                                }
                                pc.setNama(namaBaru);
                                System.out.println("Nama karakter berhasil diubah menjadi '" + namaBaru + "'");
                            }
                        } catch (Exception e) {
                            System.out.println("Input tidak valid.");
                            continue;
                        }
                    }
                } else if (choice == 4) {
                    System.out.println();
                    mainMenu();
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid.");
                continue;
            }
        }
    }
}

