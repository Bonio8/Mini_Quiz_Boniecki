package com.example.mini_quiz_boniecki;

public class Question {
    String question;
    String a;
    String b;
    String c;
    String correct;

    public Question(String question, String a, String b, String c, String correct) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.correct = correct;
    }
}
