package com.madgorillastudios.cisco.quizme_history101.data;

public class Question {
    private int mQuestionId;
    private String mQuestion;
    private String mFirstChoice;
    private String mSecondChoice;
    private String mThirdChoice;
    private String mFourthChoice;
    private String mCorrectAnswer;

    public Question() {
        mQuestionId = 0;
        mQuestion = "";
        mFirstChoice = "";
        mSecondChoice = "";
        mThirdChoice = "";
        mFourthChoice = "";
        mCorrectAnswer = "";
    }

    public Question(String newQuestion, String newFirstChoice, String newSecondChoice,
                    String newThirdChoice, String newFourthChoice, String newCorrectAnswer) {
        mQuestion = newQuestion;
        mFirstChoice = newFirstChoice;
        mSecondChoice = newSecondChoice;
        mThirdChoice = newThirdChoice;
        mFourthChoice = newFourthChoice;
        mCorrectAnswer = newCorrectAnswer;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int newQuestionId) {
        mQuestionId = newQuestionId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String newQuestion) {
        mQuestion = newQuestion;
    }

    public String getFirstChoice() {
        return mFirstChoice;
    }

    public void setFirstChoice(String newFirstChoice) {
        mFirstChoice = newFirstChoice;
    }

    public String getSecondChoice() {
        return mSecondChoice;
    }

    public void setSecondChoice(String newSecondChoice) {
        mSecondChoice = newSecondChoice;
    }

    public String getThirdChoice() {
        return mThirdChoice;
    }

    public void setThirdChoice(String newThirdChoice) {
        mThirdChoice = newThirdChoice;
    }

    public String getFourthChoice() {
        return mFourthChoice;
    }

    public void setFourthChoice(String newFourthChoice) {
        mFourthChoice = newFourthChoice;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(String newCorrectAnswer) {
        mCorrectAnswer = newCorrectAnswer;
    }
}