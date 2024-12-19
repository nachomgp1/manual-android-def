package com.example.manualnetorange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Settings extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    RelativeLayout mainLayout;
    Button btnBack;
    Switch languageSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_settings);

        mainLayout = findViewById(R.id.mainLayoutSettings);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setId(View.generateViewId());
        btnBack.setOnClickListener(this);

        languageSwitch = findViewById(R.id.languageSwitch);
        languageSwitch.setId(View.generateViewId());

        String currentLanguage = getSavedLanguageCode();

        languageSwitch.setChecked(currentLanguage.equals("en"));

        languageSwitch.setOnCheckedChangeListener(this);

        //themeSwitch = findViewById(R.id.modeSwitch);
        //themeSwitch.setId(View.generateViewId());

        //themeSwitch.setChecked(isDarkModeEnabled());
        //themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> toggleDarkMode(isChecked));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == btnBack.getId()) {
            GoBack();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {

            saveLanguageCode("en");
            SetLocale("en");
        } else {
            //Default
            saveLanguageCode("es");
            SetLocale("es");
        }
    }

    private void GoBack() {
        Intent intent = new Intent(Settings.this, MainMenu.class);
        startActivity(intent);
        finish();
    }

    private void SetLocale(String languageApp) {
        Locale locale = new Locale(languageApp);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    private void saveLanguageCode(String languageApp) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("language", languageApp).apply();
    }

    public String getSavedLanguageCode() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("language", "es");
    }

    public boolean isDarkModeEnabled() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getBoolean("dark_mode", false);
    }

    private void toggleDarkMode(boolean isEnbaled) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("dark_mode", isEnbaled).apply();

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
}
