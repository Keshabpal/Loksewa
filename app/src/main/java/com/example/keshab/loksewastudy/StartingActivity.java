package com.example.keshab.loksewastudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        Button buttonStartQuiz = findViewById(R.id.btn_start);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQUIZ();
            }
        });
    }
      private void startQUIZ(){
          Intent intent = new Intent(StartingActivity.this, QuizActivity.class);
          startActivity(intent);
          }
}
