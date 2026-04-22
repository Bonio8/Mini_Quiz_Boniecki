package com.example.mini_quiz_boniecki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class QActivity extends AppCompatActivity {

    TextView questionText, scoreText;
    Button aBtn, bBtn, cBtn, resetBtn;

    int current = 0;
    int score = 0;

    ArrayList<Question> selectedQuestions = new ArrayList<>();

    Question[] questionsArray = new Question[]{
            new Question("Stolica Włoch?", "Rzym", "Berlin", "Madryt", "Rzym"),
            new Question("2+2=?", "3", "4", "5", "4"),
            new Question("Kolor nieba?", "Zielony", "Niebieski", "Czerwony", "Niebieski"),
            new Question("Stolica Polski?", "Warszawa", "Praga", "Berlin", "Warszawa"),
            new Question("Java to?", "Gra", "Język", "Telefon", "Język"),
            new Question("5*2=?", "10", "8", "6", "10"),
            new Question("Największa planeta?", "Mars", "Jowisz", "Wenus", "Jowisz"),
            new Question("Ile dni ma tydzień?", "5", "7", "10", "7"),
            new Question("HTML to?", "Język znaczników", "Gra", "System", "Język znaczników"),
            new Question("CSS służy do?", "Stylowania", "Gier", "Bazy danych", "Stylowania"),
            new Question("CPU to?", "Procesor", "Monitor", "Myszka", "Procesor"),
            new Question("RAM to?", "Pamięć", "Dysk", "GPU", "Pamięć"),
            new Question("Który język Android?", "Java/Kotlin", "Python", "C#", "Java/Kotlin"),
            new Question("Internet powstał w?", "USA", "Polska", "Chiny", "USA"),
            new Question("Liczba pi ~", "3.14", "2.17", "1.11", "3.14")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);

        aBtn = findViewById(R.id.btnA);
        bBtn = findViewById(R.id.btnB);
        cBtn = findViewById(R.id.btnC);
        resetBtn = findViewById(R.id.btnReset);

        startQuiz();

        aBtn.setOnClickListener(v -> checkAnswer(aBtn.getText().toString()));
        bBtn.setOnClickListener(v -> checkAnswer(bBtn.getText().toString()));
        cBtn.setOnClickListener(v -> checkAnswer(cBtn.getText().toString()));

        resetBtn.setOnClickListener(v -> resetQuiz());
    }

    private void startQuiz() {
        score = 0;
        current = 0;
        selectedQuestions.clear();

        ArrayList<Question> temp = new ArrayList<>();
        Collections.addAll(temp, questionsArray);
        Collections.shuffle(temp);

        for (int i = 0; i < 5; i++) {
            selectedQuestions.add(temp.get(i));
        }

        scoreText.setText("Wynik: 0");
        showQuestion();
    }

    private void showQuestion() {

        if (current < 5) {
            Question q = selectedQuestions.get(current);

            questionText.setText(q.question);
            aBtn.setText(q.a);
            bBtn.setText(q.b);
            cBtn.setText(q.c);

        } else {

            Toast.makeText(this,
                    "Koniec quizu! Twój wynik: " + score + "/5",
                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(QActivity.this, MainActivity.class);
            i.putExtra("score", score);
            startActivity(i);
            finish();
        }
    }

    private void checkAnswer(String answer) {

        Question q = selectedQuestions.get(current);

        if (answer.trim().equals(q.correct.trim())) {
            score++;
        }

        current++;

        scoreText.setText("Wynik: " + score);

        showQuestion();
    }

    private void resetQuiz() {
        startQuiz();
    }
}