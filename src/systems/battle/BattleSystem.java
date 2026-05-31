package systems.battle;

import models.character.GameCharacter;
import models.character.PlayerCharacter;
import models.character.Skill;
import models.character.Support;
import models.item.ConsumableFood;
import models.item.Item;
import systems.quest.QuestTracker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    private PlayerCharacter[] partyPlayer;
    private GameCharacter[] partyEnemy;
    private BattleLog battleLog;
    private final Random random = new Random();

    public BattleSystem(PlayerCharacter[] partyPlayer, GameCharacter[] partyEnemy, BattleLog battleLog) {
        this.partyPlayer = partyPlayer;
        this.partyEnemy = partyEnemy;
        this.battleLog = battleLog == null ? new BattleLog(new ArrayList<>()) : battleLog;
    }

    public PlayerCharacter[] getPartyPlayer() {
        return partyPlayer;
    }

    public void setPartyPlayer(PlayerCharacter[] partyPlayer) {
        this.partyPlayer = partyPlayer;
    }

    public GameCharacter[] getPartyEnemy() {
        return partyEnemy;
    }

    public void setPartyEnemy(GameCharacter[] partyEnemy) {
        this.partyEnemy = partyEnemy;
    }

    public BattleLog getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(BattleLog battleLog) {
        this.battleLog = battleLog;
    }

    public BattleResult mulaiPertarungan(Scanner scanner, LinkedList<Item> inventory, QuestTracker questTracker) {
        Scanner input = scanner == null ? new Scanner(System.in) : scanner;
        if (battleLog == null) {
            battleLog = new BattleLog(new ArrayList<>());
        }

        battleLog.bersihkan();
        System.out.println("\n=== Battle mulai ===");

        int turn = 1;
        while (true) {
            if (semuaMusuhDikalahkan()) {
                berikanXpHadiah();
                return BattleResult.VICTORY;
            }
            if (semuaPartyDikalahkan()) {
                battleLog.tambahEntri("Semua tim pemain kalah.");
                return BattleResult.DEFEAT;
            }

            for (GameCharacter gc : partyPlayer) {
                if (gc != null) gc.setDefending(false);
            }
            for (GameCharacter gc : partyEnemy) {
                if (gc != null) gc.setDefending(false);
            }

            battleLog.nextTurn(turn);
            System.out.println("\n--- TURN " + turn + " ---");
            tampilkanStatusPertarungan();

            for (int i = 0; i < partyPlayer.length; i++) {
                PlayerCharacter currentCharacter = partyPlayer[i];
                if (currentCharacter == null || !currentCharacter.isAlive()) {
                    continue;
                }

                if (semuaMusuhDikalahkan()) {
                    break;
                }

                System.out.println("\nGiliran: " + currentCharacter.getNama() + " (HP " + currentCharacter.getCurrentHp() + "/" + currentCharacter.getMaxHp() + ", MP " + currentCharacter.getCurrentMp() + "/" + currentCharacter.getMaxMp() + ")");
                boolean aksiSelesai = false;
                while (!aksiSelesai) {
                    tampilkanOpsiAksi(currentCharacter, inventory);
                    int choice = bacaPilihan(input, 1, 7);

                    if (choice == 1) {
                        int targetIndex = pilihTargetMusuh(input);
                        if (targetIndex == -1) {
                            System.out.println("Tidak ada target musuh.");
                            aksiSelesai = true;
                        } else {
                            GameCharacter target = partyEnemy[targetIndex];
                            int damage = currentCharacter.serang(target);
                            String log = currentCharacter.getNama() + " menyerang " + target.getNama() + " \u2192 " + damage + " damage";
                            System.out.println(log);
                            battleLog.tambahEntri(log);
                            cekMusuhKalah(targetIndex, questTracker);
                            aksiSelesai = true;
                        }
                    } else if (choice == 2) {
                        currentCharacter.defend();
                            String log = currentCharacter.getNama() + " melakukan defend";
                        System.out.println(log);
                        battleLog.tambahEntri(log);
                        aksiSelesai = true;
                    } else if (choice == 3) {
                            if (!(currentCharacter instanceof Skill)) {
                            String log = currentCharacter.getNama() + " tidak punya skill.";
                            System.out.println(log);
                            battleLog.tambahEntri(log);
                            aksiSelesai = true;
                        } else if (currentCharacter instanceof models.character.Mage && currentCharacter.getCurrentMp() < 10) {
                            String log = currentCharacter.getNama() + " MP kurang.";
                            System.out.println(log);
                            battleLog.tambahEntri(log);
                            aksiSelesai = true;
                        } else if (currentCharacter instanceof Support) {
                            int allyIndex = pilihTargetParty(input, true);
                            if (allyIndex == -1) {
                                System.out.println("Tidak ada target ally.");
                                aksiSelesai = true;
                            } else {
                                GameCharacter ally = partyPlayer[allyIndex];
                                int beforeHp = ally.getCurrentHp();
                                ((Skill) currentCharacter).gunakanSkillUnik(ally);
                                int healed = ally.getCurrentHp() - beforeHp;
                                String log = currentCharacter.getNama() + " menyembuhkan " + ally.getNama() + " \u2192 +" + Math.max(0, healed) + " HP";
                                System.out.println(log);
                                battleLog.tambahEntri(log);
                                aksiSelesai = true;
                            }
                        } else {
                            int targetIndex = pilihTargetMusuh(input);
                            if (targetIndex == -1) {
                                System.out.println("Tidak ada target musuh.");
                                aksiSelesai = true;
                            } else {
                                GameCharacter target = partyEnemy[targetIndex];
                                int beforeHp = target.getCurrentHp();
                                ((Skill) currentCharacter).gunakanSkillUnik(target);
                                int damage = beforeHp - target.getCurrentHp();
                                String log = currentCharacter.getNama() + " menggunakan skill \"Skill Unik\" \u2192 " + Math.max(0, damage) + " damage ke " + target.getNama();
                                System.out.println(log);
                                battleLog.tambahEntri(log);
                                cekMusuhKalah(targetIndex, questTracker);
                                aksiSelesai = true;
                            }
                        }
                    } else if (choice == 4) {
                        if (inventory == null || inventory.isEmpty()) {
                            String log = "Inventory kosong.";
                            System.out.println(log);
                            battleLog.tambahEntri(log);
                            aksiSelesai = true;
                        } else {
                            int consumableIndex = pilihConsumable(input, inventory);
                            if (consumableIndex == -1) {
                                System.out.println("Tidak ada item yang bisa dipakai.");
                                aksiSelesai = true;
                            } else {
                                int allyIndex = pilihTargetParty(input, true);
                                if (allyIndex == -1) {
                                    System.out.println("Tidak ada target teman.");
                                    aksiSelesai = true;
                                } else {
                                    Item item = inventory.get(consumableIndex);
                                    if (!(item instanceof ConsumableFood)) {
                                        String log = "Itu bukan item pakai.";
                                        System.out.println(log);
                                        battleLog.tambahEntri(log);
                                        aksiSelesai = true;
                                    } else {
                                        ConsumableFood consumable = (ConsumableFood) item;
                                        GameCharacter ally = partyPlayer[allyIndex];
                                        consumable.consume(ally);
                                        inventory.remove(consumableIndex);
                                        String log = currentCharacter.getNama() + " menggunakan " + consumable.getNamaItem() + " \u2192 +" + consumable.getHealHpAmount() + " HP";
                                        System.out.println(log);
                                        battleLog.tambahEntri(log);
                                        aksiSelesai = true;
                                    }
                                }
                            }
                        }
                    } else if (choice == 5) {
                        String log = currentCharacter.getNama() + " melewati giliran";
                        System.out.println(log);
                        battleLog.tambahEntri(log);
                        aksiSelesai = true;
                    } else if (choice == 6) {
                        System.out.println("\n=== Battle Log ===");
                        battleLog.tampilkanLog();
                        aksiSelesai = true;
                    } else if (choice == 7) {
                            String log = currentCharacter.getNama() + " melarikan diri";
                        System.out.println(log);
                        battleLog.tambahEntri(log);
                        return BattleResult.FLED;
                    } else {
                        System.out.println("Pilihan salah.");
                    }
                }
            }

            if (semuaMusuhDikalahkan()) {
                berikanXpHadiah();
                return BattleResult.VICTORY;
            }
            if (semuaPartyDikalahkan()) {
                battleLog.tambahEntri("Semua tim pemain kalah.");
                return BattleResult.DEFEAT;
            }

            System.out.println("\n-- Giliran musuh --");
            for (int i = 0; i < partyEnemy.length; i++) {
                GameCharacter enemy = partyEnemy[i];
                if (enemy == null || !enemy.isAlive()) {
                    continue;
                }

                if (semuaPartyDikalahkan()) {
                    break;
                }

                boolean defendOrAttack = random.nextBoolean();
                if (defendOrAttack) {
                    enemy.defend();
                    String log = enemy.getNama() + " melakukan defend";
                    System.out.println(log);
                    battleLog.tambahEntri(log);
                } else {
                    int targetIndex = pilihTargetPartyAcak();
                    if (targetIndex == -1) {
                        break;
                    }
                    GameCharacter target = partyPlayer[targetIndex];
                    int damage = enemy.serang(target);
                    String log = enemy.getNama() + " menyerang " + target.getNama() + " \u2192 " + damage + " damage";
                    System.out.println(log);
                    battleLog.tambahEntri(log);
                }
            }

            turn++;
        }
    }

    private void tampilkanStatusPertarungan() {
        System.out.println("Tim pemain:");
        for (int i = 0; i < partyPlayer.length; i++) {
            PlayerCharacter pc = partyPlayer[i];
            if (pc == null) {
                continue;
            }
            System.out.println((i + 1) + ". " + pc.getNama() + " | HP " + pc.getCurrentHp() + "/" + pc.getMaxHp() + " | MP " + pc.getCurrentMp() + "/" + pc.getMaxMp() + (pc.isDefending() ? " | DEFEND" : ""));
        }

        System.out.println("Lawan:");
        for (int i = 0; i < partyEnemy.length; i++) {
            GameCharacter enemy = partyEnemy[i];
            if (enemy == null) {
                continue;
            }
            if (enemy.isAlive()) {
                System.out.println((i + 1) + ". " + enemy.getNama() + " | HP " + enemy.getCurrentHp() + "/" + enemy.getMaxHp() + (enemy.isDefending() ? " | DEFEND" : ""));
            } else {
                System.out.println((i + 1) + ". " + enemy.getNama() + " | KO");
            }
        }
    }

    private void tampilkanOpsiAksi(PlayerCharacter currentCharacter, LinkedList<Item> inventory) {
        System.out.println("1. Serang");
        System.out.println("2. Bertahan");
        System.out.println("3. Skill");
        System.out.println("4. Pakai Item");
        System.out.println("5. Lewati");
        System.out.println("6. Lihat Log");
        System.out.println("7. Kabur");
        if (!(currentCharacter instanceof Skill)) {
            System.out.println("(Karakter ini tidak punya skill)");
        } else if (currentCharacter instanceof models.character.Mage && currentCharacter.getCurrentMp() < 10) {
            System.out.println("(MP kurang untuk skill)");
        }
        if (inventory == null || inventory.isEmpty()) {
            System.out.println("(Inventory kosong)");
        }
        System.out.print("Pilih aksi: ");
    }

    private int pilihTargetMusuh(Scanner scanner) {
        List<Integer> aliveIndices = new ArrayList<>();
        System.out.println("Pilih target musuh:");
        for (int i = 0; i < partyEnemy.length; i++) {
            GameCharacter enemy = partyEnemy[i];
            if (enemy != null && enemy.isAlive()) {
                aliveIndices.add(i);
                System.out.println((aliveIndices.size()) + ". " + enemy.getNama() + " (HP " + enemy.getCurrentHp() + "/" + enemy.getMaxHp() + ")");
            }
        }

        if (aliveIndices.isEmpty()) {
            return -1;
        }

        int pilihan = bacaPilihan(scanner, 1, aliveIndices.size());
        return aliveIndices.get(pilihan - 1);
    }

    private int pilihTargetParty(Scanner scanner, boolean onlyAlive) {
        List<Integer> available = new ArrayList<>();
        System.out.println("Pilih target party:");
        for (int i = 0; i < partyPlayer.length; i++) {
            PlayerCharacter pc = partyPlayer[i];
            if (pc == null) {
                continue;
            }
            if (onlyAlive && !pc.isAlive()) {
                continue;
            }
            available.add(i);
            System.out.println((available.size()) + ". " + pc.getNama() + " (HP " + pc.getCurrentHp() + "/" + pc.getMaxHp() + ")");
        }

        if (available.isEmpty()) {
            return -1;
        }

        int pilihan = bacaPilihan(scanner, 1, available.size());
        return available.get(pilihan - 1);
    }

    private int pilihConsumable(Scanner scanner, LinkedList<Item> inventory) {
        List<Integer> available = new ArrayList<>();
        System.out.println("Pilih consumable:");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof ConsumableFood) {
                available.add(i);
                System.out.println((available.size()) + ". " + item.getNamaItem() + " | " + item.getDeskripsi());
            }
        }

        if (available.isEmpty()) {
            return -1;
        }

        int pilihan = bacaPilihan(scanner, 1, available.size());
        return available.get(pilihan - 1);
    }

    private int bacaPilihan(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String line = scanner.nextLine();
                int pilihan = Integer.parseInt(line.trim());
                if (pilihan >= min && pilihan <= max) {
                    return pilihan;
                }
            } catch (Exception ignored) {
            }
            System.out.print("Input tidak valid. Masukkan angka " + min + " - " + max + ": ");
        }
    }

    private int pilihTargetPartyAcak() {
        ArrayList<Integer> alive = new ArrayList<>();
        for (int i = 0; i < partyPlayer.length; i++) {
            if (partyPlayer[i] != null && partyPlayer[i].isAlive()) {
                alive.add(i);
            }
        }

        if (alive.isEmpty()) {
            return -1;
        }

        return alive.get(random.nextInt(alive.size()));
    }

    private boolean semuaMusuhDikalahkan() {
        for (GameCharacter enemy : partyEnemy) {
            if (enemy != null && enemy.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private boolean semuaPartyDikalahkan() {
        for (PlayerCharacter pc : partyPlayer) {
            if (pc != null && pc.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private void cekMusuhKalah(int enemyIndex, QuestTracker questTracker) {
        if (enemyIndex < 0 || enemyIndex >= partyEnemy.length) {
            return;
        }

        GameCharacter enemy = partyEnemy[enemyIndex];
        if (enemy == null || enemy.isAlive()) {
            return;
        }

        String log = enemy.getNama() + " kalah.";
        System.out.println(log);
        battleLog.tambahEntri(log);

        if (questTracker != null) {
            ArrayList<String> progressLog = questTracker.catatMusuhKalah(enemy.getNama());
            for (String line : progressLog) {
                System.out.println(line);
                battleLog.tambahEntri(line);
            }
        }
    }

    private void catatLog(String teks) {
        System.out.println(teks);
        battleLog.tambahEntri(teks);
    }

    private void berikanXpHadiah() {
        int totalXp = 0;
        for (GameCharacter enemy : partyEnemy) {
            if (enemy != null && !enemy.isAlive()) {
                totalXp += enemy.getXpReward();
            }
        }
        if (totalXp <= 0) return;
        String log = "Party mendapatkan " + totalXp + " XP!";
        System.out.println(log);
        battleLog.tambahEntri(log);
        for (PlayerCharacter pc : partyPlayer) {
            if (pc != null && pc.isAlive()) {
                pc.tambahExp(totalXp);
            }
        }
    }
}

