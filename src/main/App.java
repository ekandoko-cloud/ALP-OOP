package main;

import java.io.*;
import java.util.*;

import models.account.AccountProfile;
import systems.save.SaveLoadSystem;

public class App {
    public Scanner inpInt = new Scanner(System.in);
    public Scanner inpStr = new Scanner(System.in);

    // Current logged-in account
    private AccountProfile currentAccount;

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
                    this.currentAccount = new AccountProfile(usernameLogin, password, 0, null, new LinkedList<>(), null);
                    System.out.println("Belum ada file save. Profil baru dibuat di memori, dan file akan dibuat saat kamu save manual.");
                }

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

    //MAIN MENU NUTRITALE
    public void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║              N U T R I T A L E             ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║  MENU UTAMA                                ║");
        System.out.println("╠══════════════════╦═════════════════════════╣");
        System.out.println("║  [ 1] Play           ║  [ 2] Quest Tracker ║");
        System.out.println("║  [ 3] Inventory      ║  [ 4] Shop          ║");
        System.out.println("║  [ 5] Crafting       ║  [ 6] Forge         ║");
        System.out.println("║  [ 7] Quest Board    ║  [ 8] Mini Game     ║");
        System.out.println("║  [ 9] Encyclopedia   ║  [10] Skill Tree    ║");
        System.out.println("║  [11] Class Tree     ║  [12] Gacha         ║");
        System.out.println("║  [13] Waypoint       ║  [14] Profil Akun   ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║  [15] Save Game                            ║");
        System.out.println("║  [16] Logout                               ║");
        System.out.println("╚════════════════════════════════════════════╝");
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

                } else if (choice == 15) {
                    saveload.save(currentAccount);
                    System.out.println("Game saved successfully.");
                } else if (choice == 16) {
                    System.out.println("Logging out...");
                    currentAccount = null;
                    usernameLogin = "";
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
}

