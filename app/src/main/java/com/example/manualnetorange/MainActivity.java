package com.example.manualnetorange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mainLayout;
    LinearLayout credentialsLayout;
    LinearLayout buttonsLayout;
    public EditText edit_txt_username;
    public EditText edit_txt_password;
    Button login_btn;
    Button exit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Layouts
        mainLayout = findViewById(R.id.mainLayot);
        credentialsLayout = findViewById(R.id.credentialsLayout);
        buttonsLayout = findViewById(R.id.buttonsLayout);

        //EditText
        edit_txt_username = findViewById(R.id.edittxt_username);
        edit_txt_password = findViewById(R.id.edittxt_password);

        //Buttons
        login_btn = findViewById(R.id.login_btn);
        exit_btn = findViewById(R.id.exit_btn);

        login_btn.setId(View.generateViewId());
        exit_btn.setId(View.generateViewId());

        //Events
        login_btn.setOnClickListener(this);
        exit_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Obtaining id of the pressed button
        int id = v.getId();
        String username = edit_txt_username.getText().toString();
        String password = edit_txt_password.getText().toString();

        if (id == login_btn.getId()) {
            try {
                ValidateUser(username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void ValidateUser(String username, String password) throws SQLException {

        try {
            boolean isValid = Users.ValidateCredentials(username, password);

            if (isValid) {
                OpenMainMenu(username);
            } else {
                Toast.makeText(MainActivity.this, "Invalid user or password", Toast.LENGTH_SHORT).show();
            }
        }
        catch (SQLException e){
            Toast.makeText(MainActivity.this, "Error with database", Toast.LENGTH_SHORT).show();
        }



    }

    public void OpenMainMenu(String username) {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}