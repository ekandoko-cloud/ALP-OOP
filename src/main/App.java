package main;

import java.io.*;
import java.util.*;

public class App {
    Scanner inpInt = new Scanner(System.in);
    Scanner inpStr = new Scanner(System.in);
    String ACCOUNT_FILE = "src/main/accounts.txt";
    String DELIMITER = ":";

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

    public void register(){
        while (true){
            System.out.println();
            System.out.println("--- REGISTER ---");
            System.out.print("Username: ");
            String username = inpStr.nextLine();

            if(username.isEmpty()){
                System.out.println("Username tidak boleh kosong");
                return;
            }

            System.out.print("Password: ");
            String password = inpStr.nextLine();

            if(password.isEmpty()){
                System.out.println("Password tidak boleh kosong");
                return;
            }

            if(checkUsername(username)){
                System.out.println("Username sudah terdaftar, silakan pilih username lain");
                return;
            }

            saveAcc(username, password);
            System.out.println("Registration successful.");
            System.out.println();
            startMenu();
        }
    }

    public void login(){
        while (true){
            System.out.println("--- LOGIN ---");
            System.out.print("Username: ");
            String username = inpStr.nextLine();

            if(username.isEmpty()){
                System.out.println("Username tidak boleh kosong");
                return;
            }

            System.out.print("Password: ");
            String password = inpStr.nextLine();

            if(password.isEmpty()){
                System.out.println("Password tidak boleh kosong");
                return;
            }

            if(verifyLogin(username, password)){
                System.out.println("Login successful. Welcome, " + username + "!");
            } else {
                System.out.println("Login failed. Incorrect username or password.");
                return;
            }
        }
    }

    public boolean checkUsername(String username){
        File accFile = new File(ACCOUNT_FILE);

        if(!accFile.exists()){
            return false;
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(accFile))){
            String line;

            while((line = bufferedReader.readLine()) != null){
                String[] array = line.split(DELIMITER, 2);
                if(array.length >= 1 &&  array[0].equals(username)){
                    return true;
                }
            }
        }catch(IOException e){
            System.out.println("Error reading file");
        }

        return false;
    }

    public boolean saveAcc(String username, String password){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))){
            bufferedWriter.write(username + DELIMITER + password);
            bufferedWriter.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file");
            return false;
        }
    }

    public boolean verifyLogin(String username, String password){
        String currentLine;
        String array[];

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNT_FILE));

            while ((currentLine = bufferedReader.readLine())!= null){
                array = currentLine.split(DELIMITER,2);
                if(array[0].equals(username) && array[1].equals(password)){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

        return false;

    }
}

