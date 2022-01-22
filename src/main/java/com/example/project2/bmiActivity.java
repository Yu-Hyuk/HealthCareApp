package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class bmiActivity extends AppCompatActivity {
    private EditText et_kg,et_m;
    private Button btn_add;
    private TextView textresult,textresult2;
    private ImageView img_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        setTitle("BMI 측정");

        et_kg = (EditText)findViewById(R.id.et_kg);
        et_m = (EditText)findViewById(R.id.et_m);

        btn_add = (Button)findViewById(R.id.btn_add);

        textresult = (TextView)findViewById(R.id.textresult);
        textresult2 = (TextView)findViewById(R.id.textresult2);

        img_bmi = (ImageView)findViewById(R.id.img_bmi);

        img_bmi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(getApplicationContext(),bmi_infoActivity.class);
                startActivity(intent);
                return false;
            }
        });

        btn_add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               String num1 = et_kg.getText().toString();
               String num2 = et_m.getText().toString();
               Float result = Float.parseFloat(num1) / (Float.parseFloat(num2) * Float.parseFloat(num2));
               textresult.setText("결과 : " + result.toString());
               if(result <= 18.5){
                   textresult2.setText("저체중입니다");
               }
               else if(result > 18.5 && result <=23){
                   textresult2.setText("정상입니다");
               }
               else if(result > 23 && result <=25){
                   textresult2.setText("과체중입니다");
               }
               else if(result > 25 && result <=30){
                   textresult2.setText("비만입니다");
               }
               else if(result > 30){
                   textresult2.setText("고도비만입니다");
               }

                return false;
            }
        });



    }
}