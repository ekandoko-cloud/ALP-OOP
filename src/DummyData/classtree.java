package DummyData;

import systems.classSystem.ClassNode;
import enums.ClassType;
import java.util.ArrayList;

public class classtree {

    public static ClassNode generateClassTree() {
        ClassNode root = new ClassNode("Classless", "Root class", 0, true, ClassType.CLASSLESS, null, new ArrayList<>());

        ClassNode warrior = new ClassNode("Warrior", "Basic melee class", 5, false, ClassType.WARRIOR, root, new ArrayList<>());
        ClassNode archer = new ClassNode("Archer", "Basic ranged class", 5, false, ClassType.ARCHER, root, new ArrayList<>());
        ClassNode mage = new ClassNode("Mage", "Basic magic class", 5, false, ClassType.MAGE, root, new ArrayList<>());
        ClassNode support = new ClassNode("Support", "Basic support class", 5, false, ClassType.SUPPORT, root, new ArrayList<>());

        root.getChildren().add(warrior);
        root.getChildren().add(archer);
        root.getChildren().add(mage);
        root.getChildren().add(support);

        // Level 10 evolution
        ClassNode knight = new ClassNode("Knight", "Warrior evolution", 10, false, ClassType.WARRIOR, warrior, new ArrayList<>());
        ClassNode scout = new ClassNode("Scout", "Archer evolution", 10, false, ClassType.ARCHER, archer, new ArrayList<>());
        ClassNode wizard = new ClassNode("Wizard", "Mage evolution", 10, false, ClassType.MAGE, mage, new ArrayList<>());
        ClassNode shieldman = new ClassNode("Shieldman", "Support tank branch", 10, false, ClassType.SUPPORT, support, new ArrayList<>());
        ClassNode angel = new ClassNode("Angel", "Support healer branch", 10, false, ClassType.SUPPORT, support, new ArrayList<>());

        warrior.getChildren().add(knight);
        archer.getChildren().add(scout);
        mage.getChildren().add(wizard);
        support.getChildren().add(shieldman);
        support.getChildren().add(angel);

        // Level 20 branch choices
        ClassNode swordsman = new ClassNode("Swordsman", "Balanced sword fighter", 20, false, ClassType.WARRIOR, knight, new ArrayList<>());
        ClassNode berserker = new ClassNode("Berserker", "High offense, low defense", 20, false, ClassType.WARRIOR, knight, new ArrayList<>());
        knight.getChildren().add(swordsman);
        knight.getChildren().add(berserker);

        ClassNode ranger = new ClassNode("Ranger", "Area control archer", 20, false, ClassType.ARCHER, scout, new ArrayList<>());
        ClassNode marksman = new ClassNode("Marksman", "High critical damage", 20, false, ClassType.ARCHER, scout, new ArrayList<>());
        scout.getChildren().add(ranger);
        scout.getChildren().add(marksman);

        ClassNode archmage = new ClassNode("Archmage", "High AoE damage", 20, false, ClassType.MAGE, wizard, new ArrayList<>());
        ClassNode sorcerer = new ClassNode("Sorcerer", "Advanced dark magic", 20, false, ClassType.MAGE, wizard, new ArrayList<>());
        wizard.getChildren().add(archmage);
        wizard.getChildren().add(sorcerer);

        ClassNode paladin = new ClassNode("Paladin", "Holy tank", 20, false, ClassType.SUPPORT, shieldman, new ArrayList<>());
        ClassNode archangel = new ClassNode("Archangel", "Reviver and strong healer", 20, false, ClassType.SUPPORT, angel, new ArrayList<>());
        shieldman.getChildren().add(paladin);
        angel.getChildren().add(archangel);

        return root;
    }
}
