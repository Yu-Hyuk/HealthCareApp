package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class homeActivity extends AppCompatActivity {
    TextView fitonetitle,fittwotitle,fitthreetitle,firfourtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fitonetitle = (TextView)findViewById(R.id.fitonetitle);
        fittwotitle = (TextView)findViewById(R.id.fittwotitle);
        fitthreetitle = (TextView)findViewById(R.id.fitthreetitle);
        firfourtitle = (TextView)findViewById(R.id.fitfourtitle);

        fitonetitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Exercise1.class);
                startActivity(intent);
            }
        });

        fittwotitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),exercise2.class);
                startActivity(intent);
            }
        });

        fitthreetitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Exercise3.class);
                startActivity(intent);
            }
        });

        firfourtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Exercise4.class);
                startActivity(intent);
            }
        });

    }
}