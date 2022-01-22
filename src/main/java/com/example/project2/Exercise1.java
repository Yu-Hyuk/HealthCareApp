package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Exercise1 extends AppCompatActivity {
    Button button;
    TextView timervalue;
    private static final long START_TIME_IN_MILLIS = 800000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        timervalue = (TextView)findViewById(R.id.timervalue);

        button = (Button)findViewById(R.id.button);

        startTimer();
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(mTimeLeftInMillis,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"종료!",Toast.LENGTH_SHORT).show();
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 13000) / 600;
        int seconds = (int) (mTimeLeftInMillis / 13000) % 600;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d" , minutes, seconds);
        timervalue.setText(timeLeft);
    }
}