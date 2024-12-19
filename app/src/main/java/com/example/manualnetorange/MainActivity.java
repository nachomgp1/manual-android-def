package com.example.manualnetorange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout mainLayout;
    LinearLayout credentialsLayout;
    LinearLayout buttonsLayout;
    public EditText edit_txt_username;
    public EditText edit_txt_password;
    Button login_btn;
    Button exit_btn;
    Settings settings = new Settings();
    int contadorFallos = 0;

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

        if (id == login_btn.getId()){
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor ingresa un dato", Toast.LENGTH_SHORT).show();
                return;
            }
            verifyData(username, password);
        }else if (id == exit_btn.getId()){
            finishAffinity();
        }
    }
    private void verifyData(String name, String password) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Crear el DTO de la solicitud
        VerifyRequest request = new VerifyRequest(name, password);

        // Llamar al método verifyData
        Call<VerifyResponse> call = apiService.verifyData(request);

        // Manejar la respuesta
        call.enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        Toast.makeText(MainActivity.this, "Datos verificados correctamente", Toast.LENGTH_SHORT).show();
                        OpenMainMenu(name);
                    }
                } else {
                    contadorFallos ++;
                    if (contadorFallos == 3){
                        finishAffinity();
                        Toast.makeText(MainActivity.this, "Cerrando: numero de fallos alcanzado", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MainActivity.this, "Error: Usuario o Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OpenMainMenu(String username) {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}