package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatDelegate.NightMode;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

import static androidx.appcompat.app.AppCompatDelegate.*;

public class MainActivity extends AppCompatActivity{
        private TextView textEdit;
        private static final String TEXT_STATE = "TEXT_STATE";
        static {
            setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.calculator);
            initView();
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle instanceState) {
            textEdit = findViewById(R.id.edit_text_number);
            instanceState.putCharSequence(TEXT_STATE, textEdit.getText());
            super.onSaveInstanceState(instanceState);
        }


        @Override
        protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
            textEdit = findViewById(R.id.edit_text_number);
            textEdit.setText(instanceState.getCharSequence(TEXT_STATE));
            super.onRestoreInstanceState(instanceState);
        }

        private void initView() {
            textEdit = findViewById(R.id.edit_text_number);

            initNonEqualsButton(findViewById(R.id.right_bracket_button));
            initNonEqualsButton(findViewById(R.id.left_bracket_button));
            initNonEqualsButton(findViewById(R.id.one_button));
            initNonEqualsButton(findViewById(R.id.two_button));
            initNonEqualsButton(findViewById(R.id.three_button));
            initNonEqualsButton(findViewById(R.id.four_button));
            initNonEqualsButton(findViewById(R.id.five_button));
            initNonEqualsButton(findViewById(R.id.six_button));
            initNonEqualsButton(findViewById(R.id.seven_button));
            initNonEqualsButton(findViewById(R.id.eight_button));
            initNonEqualsButton(findViewById(R.id.nine_button));
            initNonEqualsButton(findViewById(R.id.button_zero));
            initNonEqualsButton(findViewById(R.id.plus_button));
            initNonEqualsButton(findViewById(R.id.minus_button));
            initNonEqualsButton(findViewById(R.id.mult_button));
            initNonEqualsButton(findViewById(R.id.exp_button));
            initNonEqualsButton(findViewById(R.id.div_button));
            initNonEqualsButton(findViewById(R.id.button_coma));
            initClearButton(findViewById(R.id.clear_button));
            initEqualsButton(findViewById(R.id.button_equals));
        }

        private void initNonEqualsButton(MaterialButton button) {
            button.setOnClickListener(v -> {

                StringBuilder text = new StringBuilder(textEdit.getText());
                textEdit.setText(String.format(Locale.getDefault(), "%s", text.append(button.getText())));
            });
        }
        private void initEqualsButton(MaterialButton button) {
            button.setOnClickListener(v -> {

                Calculator.calculate(String.valueOf(textEdit.getText()));
                textEdit.setText(String.format(Locale.getDefault(), "%s", Calculator.valuesStack.pop()));
            });
        }
        private void initClearButton(MaterialButton button) {
            button.setOnClickListener(v -> textEdit.setText(String.format(Locale.getDefault(), "%s", "")));
        }
}