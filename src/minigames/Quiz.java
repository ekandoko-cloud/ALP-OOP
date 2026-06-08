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

    public String getQuestion() {
        return question;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
