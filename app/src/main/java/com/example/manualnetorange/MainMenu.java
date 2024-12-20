package com.example.manualnetorange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mainLayout;
    TextView logLabel;
    Button standardUserButton;
    Button btnSettings;
    Button btnAdmin;
    Button btnExit;
    Button btngroup;
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

        standardUserButton = findViewById(R.id.btnSection1);
        standardUserButton.setId(View.generateViewId());
        standardUserButton.setOnClickListener(this);

        btnSettings = findViewById(R.id.btnSection4);
        btnSettings.setId(View.generateViewId());
        btnSettings.setOnClickListener(this);

        btnExit = findViewById(R.id.btnSection5);
        btnExit.setId(View.generateViewId());
        btnExit.setOnClickListener(this);

        btnAdmin =findViewById(R.id.btnSection3);
        btnAdmin.setId(View.generateViewId());
        btnAdmin.setOnClickListener(this);

        btngroup=findViewById(R.id.btnSection2);
        btngroup.setId(View.generateViewId());
        btngroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == standardUserButton.getId()){
            OpenStandarUserView();
        }
        else if(id == btnSettings.getId()){
            OpenSettingView();
        }else if(id==btnAdmin.getId()){
            OpenAdminView();
        }
        else if (id== btnExit.getId()){
            GoBack();
        }
        else if (id== btngroup.getId()){
            OpenGroupview();
        }
    }

    private void OpenGroupview() {
        Intent intent = new Intent(MainMenu.this, ViewGroupUser.class);
        startActivity(intent);
        finish();
    }

    private void OpenAdminView() {
        Intent intent = new Intent(MainMenu.this, ViewAdmin.class);
        startActivity(intent);
        finish();
    }


    private void OpenStandarUserView() {
        Intent intent = new Intent(MainMenu.this, ViewStandardUser.class);
        startActivity(intent);
        finish();
    }

    private void OpenSettingView() {
        Intent intent = new Intent(MainMenu.this, Settings.class);
        startActivity(intent);
        finish();
    }

    private void GoBack(){
        Intent intent = new Intent(MainMenu.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
