package com.example.manualnetorange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAdmin extends AppCompatActivity implements View.OnClickListener  {
    RelativeLayout mainLayout;
    Button btnFtpServer;
    Button btnEmail;
    Button btnAdmin;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_user);

        mainLayout = findViewById(R.id.mainStandarUserLayout);

        btnFtpServer = findViewById(R.id.btnFtpServer);
        btnFtpServer.setId(View.generateViewId());
        btnFtpServer.setOnClickListener(this);

        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setId(View.generateViewId());
        btnEmail.setOnClickListener(this);

        btnAdmin = findViewById(R.id.btngestion);
        btnAdmin.setId(View.generateViewId());
        btnAdmin.setOnClickListener(this);

        btnback = findViewById(R.id.btnBack);
        btnback.setId(View.generateViewId());
        btnback.setOnClickListener(this);

        Intent intent = getIntent();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == btnFtpServer.getId()) {
            OpenFtpServerView();
        } else if (id == btnEmail.getId()) {
            OpenMailView();
        }else if(id == btnAdmin.getId()){
            OpenAdminView();
        }else if(id == btnback.getId()){
            Goback();
        }
    }
    private void Goback() {
        Intent intent =new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    private void OpenAdminView() {
        Intent intent = new Intent(ViewAdmin.this, ViewGestion.class);
        startActivity(intent);
        finish();
    }

    public void OpenFtpServerView() {
        Intent intent = new Intent(ViewAdmin.this, ViewFtpServer.class);
        startActivity(intent);
        finish();

    }

    public void OpenMailView() {
        Intent intent = new Intent(ViewAdmin.this, ViewEmail.class);
        startActivity(intent);
        finish();
    }
}
