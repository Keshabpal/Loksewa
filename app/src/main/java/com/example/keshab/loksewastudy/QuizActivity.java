package com.example.keshab.loksewastudy;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
private TextView textViewQuestion;
private TextView textViewScore;
private TextView textViewQuestionCount;
private TextView textViewCountDown;
private RadioGroup rbGroup;
private RadioButton rb1;
private RadioButton rb2;
private RadioButton rb3;
private Button buttonConfirmNext;

private ColorStateList textColorDefaultRb;

private List<Question> questionList;
private int questionCounter;
private int questionCounterTotal;
private Question currentQuestion;

private int score;
private boolean answered;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.txt_question);
        textViewScore = findViewById(R.id.txt_score);
        textViewQuestionCount = findViewById(R.id.txt_question_count);
        textViewCountDown = findViewById(R.id.txt_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.btn_option1);
        rb2 = findViewById(R.id.btn_option2);
        rb3 = findViewById(R.id.btn_option3);
        buttonConfirmNext = findViewById(R.id.btn_confirm);
        textColorDefaultRb = rb1.getTextColors();

        QuizDpHelper dpHelper = new QuizDpHelper(this);
        questionList = dpHelper.getAllQuestions();
        questionCounterTotal = questionList.size();
        Collections.shuffle(questionList);


        showNextQuestion();
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(QuizActivity.this, "please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCounterTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question:" + questionCounter + "/" + questionCounterTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

        }else {
            finishQuiz();
        }
    }

    private void checkAnswer(){
        answered = true;

        RadioButton rbSelected  = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr =rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score++;
            textViewScore.setText("Score : " + score);
        }
        showSolution();
    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is Correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is Correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is Correct");
                break;

        }
        if (questionCounter < questionCounterTotal){
            buttonConfirmNext.setText("Next");
        }else {
            buttonConfirmNext.setText("Finish");

        }
    }
     private void finishQuiz(){
        finish();
     }
}