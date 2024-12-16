package com.example.manualnetorange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    RelativeLayout mainLayout;
    TextView logLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        mainLayout = findViewById(R.id.mainMenuLayout);

        //The intent is recived
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        logLabel = findViewById(R.id.logLabel);
        logLabel.setText(username);
    }
}
