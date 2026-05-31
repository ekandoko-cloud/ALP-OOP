package minigames;


public class Quiz {
    private String question;
    private String[] answerChoices;
    private String correctAnswer;
    private String explanation;

    public Quiz(String question, String[] answerChoices, String correctAnswer, String explanation) {
        this.question = question;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public boolean cekJawaban(String choice){
        if (choice == null) {
            return false;
        }
        return choice.trim().equalsIgnoreCase(correctAnswer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
