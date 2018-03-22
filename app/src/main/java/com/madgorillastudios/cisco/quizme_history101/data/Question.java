package com.madgorillastudios.cisco.quizme_history101.data;

public class Question {
    private int questionId;
    private String question;
    private String firstChoice;
    private String secondChoice;
    private String thirdChoice;
    private String fourthChoice;
    private String correctAnswer;

    public Question() {
        questionId = 0;
        question = "";
        firstChoice = "";
        secondChoice = "";
        thirdChoice = "";
        fourthChoice = "";
        correctAnswer = "";
    }

    public Question(String newQuestion, String newFirstChoice, String newSecondChoice,
                    String newThirdChoice, String newFourthChoice, String newCorrectAnswer) {
        question = newQuestion;
        firstChoice = newFirstChoice;
        secondChoice = newSecondChoice;
        thirdChoice = newThirdChoice;
        fourthChoice = newFourthChoice;
        correctAnswer = newCorrectAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int newQuestionId) {
        questionId = newQuestionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String newQuestion) {
        question = newQuestion;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String newFirstChoice) {
        firstChoice = newFirstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String newSecondChoice) {
        secondChoice = newSecondChoice;
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(String newThirdChoice) {
        thirdChoice = newThirdChoice;
    }

    public String getFourthChoice() {
        return fourthChoice;
    }

    public void setFourthChoice(String newFourthChoice) {
        fourthChoice = newFourthChoice;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String newCorrectAnswer) {
        correctAnswer = newCorrectAnswer;
    }
}