package systems.battle;

import DummyData.mainquest;
import models.character.BossMonster;
import models.character.GameCharacter;
import models.character.Monster;
import models.quest.MainQuest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleEnemyFactory {
    private static final int MIN_CHAPTER = 1;
    private static final int MAX_CHAPTER = 5;

    private BattleEnemyFactory() {
    }

    public static GameCharacter[] createPartyFromQuest(MainQuest quest, int chapter) {
        if (quest == null) {
            return new GameCharacter[0];
        }
        return createPartyFromNames(quest.getLineUpMusuh(), chapter);
    }

    public static GameCharacter[] createRandomPartyForChapter(int chapter, Random random) {
        Random rng = random == null ? new Random() : random;
        List<MainQuest> available = mainquest.getDummyMainQuestByChapter(chapter);
        if (available.isEmpty()) {
            return new GameCharacter[0];
        }

        MainQuest picked = available.get(rng.nextInt(available.size()));
        return createPartyFromQuest(picked, chapter);
    }

    public static GameCharacter[] createPartyFromNames(List<String> enemyNames, int chapter) {
        if (enemyNames == null || enemyNames.isEmpty()) {
            return new GameCharacter[0];
        }

        ArrayList<GameCharacter> enemies = new ArrayList<>();
        for (String enemyName : enemyNames) {
            GameCharacter enemy = createEnemy(enemyName, chapter);
            if (enemy != null) {
                enemies.add(enemy);
            }
        }
        return enemies.toArray(new GameCharacter[0]);
    }

    public static GameCharacter createEnemy(String enemyName, int chapter) {
        if (enemyName == null || enemyName.trim().isEmpty()) {
            return null;
        }

        String name = enemyName.trim();
        int tier = Math.max(MIN_CHAPTER, Math.min(MAX_CHAPTER, chapter));
        int variation = Math.abs(name.toLowerCase().hashCode()) % 7;

        int hp = 36 + (tier * 18) + (variation * 3);
        int atk = 8 + (tier * 4) + (variation % 4);
        int def = 2 + (tier * 2) + (variation % 3);
        int xp = Math.max(5, (hp / 10) + atk * 3);

        if (isBossEnemy(name)) {
            hp = (int) Math.round(hp * 1.8);
            atk = (int) Math.round(atk * 1.5);
            def = (int) Math.round(def * 1.4);
            xp = Math.max(20, (hp / 10) + atk * 3);
            BossMonster boss = new BossMonster(name, hp, hp, atk, def, triviaFor(name));
            boss.setXpDiberikan(xp);
            return boss;
        }

        Monster monster = new Monster(name, hp, hp, atk, def, triviaFor(name));
        monster.setXpDiberikan(xp);
        return monster;
    }

    public static boolean isBossEnemy(String enemyName) {
        if (enemyName == null) {
            return false;
        }
        String lower = enemyName.trim().toLowerCase();
        return lower.contains("boss")
                || lower.contains("blight-root")
                || lower.contains("goliath toad")
                || lower.contains("baron gluttony")
                || lower.contains("dr. mortis")
                || lower.contains("crimson chimera");
    }

    private static String triviaFor(String enemyName) {
        return "Encounter monster: " + enemyName + ".";
    }
}

