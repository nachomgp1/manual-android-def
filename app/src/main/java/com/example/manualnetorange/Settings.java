package com.example.manualnetorange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout mainLayout;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        mainLayout = findViewById(R.id.mainLayoutSettings);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setId(View.generateViewId());
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == btnBack.getId()){
            GoBack();
        }
    }

    private void GoBack() {
        Intent intent = new Intent(Settings.this, MainMenu.class);
        startActivity(intent);
        finish();
    }
}
