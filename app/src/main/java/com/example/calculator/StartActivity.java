package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

import static androidx.appcompat.app.AppCompatDelegate.*;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class StartActivity extends AppCompatActivity {
    MaterialButton startButton;
    CheckBox switchTheme;
    public static final String CHECK_STATE_THEME = "CHECK_STATE_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_theme);
        if (savedInstanceState == null){
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            switchTheme = findViewById(R.id.switch_theme);
            switchTheme.setChecked(sharedPref.getBoolean(CHECK_STATE_THEME, FALSE));
            if (switchTheme.isChecked()) {
                setDefaultNightMode(MODE_NIGHT_YES);
            } else setDefaultNightMode(MODE_NIGHT_NO);
        }
        initView();
    }

    private void initView() {

        initSwitchButton(findViewById(R.id.switch_theme));
        initStartButton(findViewById(R.id.button_start));
    }

    private void initSwitchButton(CheckBox viewById) {
        viewById.setOnClickListener(view -> {
            if (viewById.isChecked()) {
                setDefaultNightMode(MODE_NIGHT_YES);
            } else setDefaultNightMode(MODE_NIGHT_NO);
        });
    }

    private void initStartButton(MaterialButton viewById) {
        viewById.setOnClickListener(view -> startActivity(new Intent(StartActivity.this, MainActivity.class)));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        switchTheme = findViewById(R.id.switch_theme);
        instanceState.putBoolean(CHECK_STATE_THEME, switchTheme.isChecked());
        super.onSaveInstanceState(instanceState);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        switchTheme = findViewById(R.id.switch_theme);
        switchTheme.setChecked(instanceState.getBoolean(CHECK_STATE_THEME));
        super.onRestoreInstanceState(instanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        switchTheme = findViewById(R.id.switch_theme);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(CHECK_STATE_THEME, switchTheme.isChecked());
        editor.commit();
    }
}
