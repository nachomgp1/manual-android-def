package com.example.manualnetorange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ViewStandardUser extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mainLayout;
    Button btnFtpServer;
    Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_standard_user);

        mainLayout = findViewById(R.id.mainStandarUserLayout);

        btnFtpServer = findViewById(R.id.btnFtpServer);
        btnFtpServer.setId(View.generateViewId());
        btnFtpServer.setOnClickListener(this);

        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setId(View.generateViewId());
        btnEmail.setOnClickListener(this);

        Intent intent = getIntent();


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnFtpServer.getId()) {
            OpenFtpServerView();
        } else if (id == btnEmail.getId()) {
            OpenMailView();
        }
    }

    public void OpenFtpServerView() {
        Intent intent = new Intent(ViewStandardUser.this, ViewFtpServer.class);
        startActivity(intent);
        finish();

    }

    public void OpenMailView() {
        Intent intent = new Intent(ViewStandardUser.this, ViewEmail.class);
        startActivity(intent);
        finish();
    }
}
