package com.beijar.patrik.myapplication;


import java.io.Serializable;

public class Question implements Serializable {
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String correct;

    public Question(String mQuestion, String mAnswerOne, String mAnswerTwo, String mAnswerThree, String mCorrect) {
        this.question = mQuestion;
        this.answerOne = mAnswerOne;
        this.answerTwo = mAnswerTwo;
        this.answerThree = mAnswerThree;
        this.correct = mCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public String getCorrect() {
        return correct;
    }
}
