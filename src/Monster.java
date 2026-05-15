public class Monster extends GameCharacter {
    private String idMonster;
    private String triviaPenyakit;

    public Monster() {
        super();
    }


    public String getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(String idMonster) {
        this.idMonster = idMonster;
    }

    public String getTriviaPenyakit() {
        return triviaPenyakit;
    }

    public void setTriviaPenyakit(String triviaPenyakit) {
        this.triviaPenyakit = triviaPenyakit;
    }
}

