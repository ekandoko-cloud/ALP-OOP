package systems.classSystem;
import models.character.PlayerCharacter;
public class ClassTree {
    private ClassNode root;

    public ClassTree(ClassNode root) {
        this.root = root;
    }


    public ClassNode getRoot() {
        return root;
    }

    public void setRoot(ClassNode root) {
        this.root = root;
    }
}


