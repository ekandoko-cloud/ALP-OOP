package DummyData;

import systems.skill.SkillNode;
import java.util.ArrayList;
import java.util.List;

public class skilltree {

    public static List<SkillNode> generateSkillTree() {
        List<SkillNode> allSkills = new ArrayList<>();

        // ROOT
        SkillNode root = new SkillNode("Inti Petualang", "Membuka Akses Skill Tree", 0, true, null, new ArrayList<>());

        // ATK
        SkillNode atk1 = new SkillNode("Aura Serang I", "Menambah ATK Party +5", 200, false, root, new ArrayList<>());
        root.getChildren().add(atk1);
        SkillNode atk2 = new SkillNode("Aura Serang II", "Menambah ATK Party +15", 500, false, atk1, new ArrayList<>());
        atk1.getChildren().add(atk2);
        SkillNode atk3 = new SkillNode("Aura Serang III", "Menambah ATK Party +30", 1000, false, atk2, new ArrayList<>());
        atk2.getChildren().add(atk3);
        SkillNode atk4 = new SkillNode("Amukan Bersama", "Menambah ATK Party +55", 1500, false, atk3, new ArrayList<>());
        atk3.getChildren().add(atk4);

        // MP
        SkillNode mp1 = new SkillNode("Aliran Mana I", "Menambah Max MP Party +20", 200, false, root, new ArrayList<>());
        root.getChildren().add(mp1);
        SkillNode mp2 = new SkillNode("Aliran Mana II", "Menambah Max MP Party +50", 500, false, mp1, new ArrayList<>());
        mp1.getChildren().add(mp2);
        SkillNode mp3 = new SkillNode("Aliran Mana III", "Menambah Max MP Party +90", 1000, false, mp2, new ArrayList<>());
        mp2.getChildren().add(mp3);
        SkillNode mp4 = new SkillNode("Transendensi Sihir", "Menambah Max MP Party +150", 1500, false, mp3, new ArrayList<>());
        mp3.getChildren().add(mp4);

        // DEF
        SkillNode def1 = new SkillNode("Perlindungan I", "Menambah DEF Party +5", 200, false, root, new ArrayList<>());
        root.getChildren().add(def1);
        SkillNode def2 = new SkillNode("Perlindungan II", "Menambah DEF Party +15", 500, false, def1, new ArrayList<>());
        def1.getChildren().add(def2);
        SkillNode def3 = new SkillNode("Perlindungan III", "Menambah DEF Party +30", 1000, false, def2, new ArrayList<>());
        def2.getChildren().add(def3);
        SkillNode def4 = new SkillNode("Perisai Benteng", "Menambah DEF Party +55", 1500, false, def3, new ArrayList<>());
        def3.getChildren().add(def4);

        // HP
        SkillNode hp1 = new SkillNode("Energi Kehidupan I", "Menambah Max HP Party +30", 200, false, root, new ArrayList<>());
        root.getChildren().add(hp1);
        SkillNode hp2 = new SkillNode("Energi Kehidupan II", "Menambah Max HP Party +75", 500, false, hp1, new ArrayList<>());
        hp1.getChildren().add(hp2);
        SkillNode hp3 = new SkillNode("Energi Kehidupan III", "Menambah Max HP Party +140", 1000, false, hp2, new ArrayList<>());
        hp2.getChildren().add(hp3);
        SkillNode hp4 = new SkillNode("Jantung Abadi", "Menambah Max HP Party +250", 1500, false, hp3, new ArrayList<>());
        hp3.getChildren().add(hp4);

        // TAS
        SkillNode tas1 = new SkillNode("Tas Petualang I", "Menambah Slot Inventory +10", 200, false, root, new ArrayList<>());
        root.getChildren().add(tas1);
        SkillNode tas2 = new SkillNode("Tas Petualang II", "Menambah Slot Inventory +25", 500, false, tas1, new ArrayList<>());
        tas1.getChildren().add(tas2);
        SkillNode tas3 = new SkillNode("Ruang Dimensi I", "Menambah Slot Inventory +45", 1000, false, tas2, new ArrayList<>());
        tas2.getChildren().add(tas3);
        SkillNode tas4 = new SkillNode("Ruang Dimensi II", "Menambah Slot Inventory +70", 1500, false, tas3, new ArrayList<>());
        tas3.getChildren().add(tas4);

        // Add to master list
        allSkills.add(root);
        allSkills.add(atk1); allSkills.add(atk2); allSkills.add(atk3); allSkills.add(atk4);
        allSkills.add(mp1); allSkills.add(mp2); allSkills.add(mp3); allSkills.add(mp4);
        allSkills.add(def1); allSkills.add(def2); allSkills.add(def3); allSkills.add(def4);
        allSkills.add(hp1); allSkills.add(hp2); allSkills.add(hp3); allSkills.add(hp4);
        allSkills.add(tas1); allSkills.add(tas2); allSkills.add(tas3); allSkills.add(tas4);

        return allSkills;
    }
}
