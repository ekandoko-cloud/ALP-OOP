package DummyData;

import systems.classSystem.ClassNode;
import enums.ClassType;
import java.util.ArrayList;
import java.util.List;

public class classtree {

    public static ClassNode generateClassTree() {
        // root: Classless
        ClassNode root = new ClassNode("Classless", "Root class", 0, true, ClassType.CLASSLESS, null, new ArrayList<>());

        // basic classes unlocked at level 5
        ClassNode warrior = new ClassNode("Warrior", "Basic melee class", 5, false, ClassType.WARRIOR, root, new ArrayList<>());
        ClassNode archer = new ClassNode("Archer", "Basic ranged class", 5, false, ClassType.ARCHER, root, new ArrayList<>());
        ClassNode mage = new ClassNode("Mage", "Basic magic class", 5, false, ClassType.MAGE, root, new ArrayList<>());
        ClassNode support = new ClassNode("Support", "Basic support class", 5, false, ClassType.SUPPORT, root, new ArrayList<>());
        root.getChildren().add(warrior);
        root.getChildren().add(archer);
        root.getChildren().add(mage);
        root.getChildren().add(support);

        // Warrior branch
        ClassNode swordsman = new ClassNode("Swordsman", "Balanced sword fighter", 10, false, ClassType.WARRIOR, warrior, new ArrayList<>());
        ClassNode berserker = new ClassNode("Berserker", "High offense, low defense", 10, false, ClassType.WARRIOR, warrior, new ArrayList<>());
        ClassNode knight = new ClassNode("Knight", "Defensive sword master", 15, false, ClassType.WARRIOR, swordsman, new ArrayList<>());
        swordsman.getChildren().add(knight);
        warrior.getChildren().add(swordsman);
        warrior.getChildren().add(berserker);

        // Archer branch
        ClassNode scout = new ClassNode("Scout", "Mobile archer", 10, false, ClassType.ARCHER, archer, new ArrayList<>());
        ClassNode marksman = new ClassNode("Marksman", "High critical damage", 10, false, ClassType.ARCHER, archer, new ArrayList<>());
        ClassNode ranger = new ClassNode("Ranger", "Area control archer", 15, false, ClassType.ARCHER, scout, new ArrayList<>());
        scout.getChildren().add(ranger);
        archer.getChildren().add(scout);
        archer.getChildren().add(marksman);

        // Mage branch
        ClassNode wizard = new ClassNode("Wizard", "Elemental caster", 10, false, ClassType.MAGE, mage, new ArrayList<>());
        ClassNode witch = new ClassNode("Witch", "Curse and drain magic", 10, false, ClassType.MAGE, mage, new ArrayList<>());
        ClassNode archmage = new ClassNode("Archmage", "High AoE damage", 15, false, ClassType.MAGE, wizard, new ArrayList<>());
        wizard.getChildren().add(archmage);
        mage.getChildren().add(wizard);
        mage.getChildren().add(witch);

        // Support branch
        ClassNode shieldman = new ClassNode("Shieldman", "Tank with passive heals", 10, false, ClassType.SUPPORT, support, new ArrayList<>());
        ClassNode angel = new ClassNode("Angel", "Healer evolution", 10, false, ClassType.SUPPORT, support, new ArrayList<>());
        ClassNode paladin = new ClassNode("Paladin", "Holy tank", 15, false, ClassType.SUPPORT, shieldman, new ArrayList<>());
        ClassNode archangel = new ClassNode("Archangel", "Reviver and strong healer", 15, false, ClassType.SUPPORT, angel, new ArrayList<>());
        shieldman.getChildren().add(paladin);
        angel.getChildren().add(archangel);
        support.getChildren().add(shieldman);
        support.getChildren().add(angel);

        return root;
    }
}
