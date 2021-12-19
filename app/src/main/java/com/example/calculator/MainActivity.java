package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{
        private TextView textEdit;
        private static final String TEXT_STATE = "TEXT_STATE";

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.calculator);
            initView();
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle instanceState) {
            textEdit = findViewById(R.id.editTextNumber);
            instanceState.putCharSequence(TEXT_STATE, textEdit.getText());
            super.onSaveInstanceState(instanceState);
        }


        @Override
        protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
            textEdit = findViewById(R.id.editTextNumber);
            textEdit.setText(instanceState.getCharSequence(TEXT_STATE));
            super.onRestoreInstanceState(instanceState);
        }

        private void initView() {
            textEdit = findViewById(R.id.editTextNumber);

            initNonEqualsButton(findViewById(R.id.rightBracketButton));
            initNonEqualsButton(findViewById(R.id.leftBracketButton));
            initNonEqualsButton(findViewById(R.id.oneButton));
            initNonEqualsButton(findViewById(R.id.twoButton));
            initNonEqualsButton(findViewById(R.id.threeButton));
            initNonEqualsButton(findViewById(R.id.fourButton));
            initNonEqualsButton(findViewById(R.id.fiveButton));
            initNonEqualsButton(findViewById(R.id.sixButton));
            initNonEqualsButton(findViewById(R.id.sevenButton));
            initNonEqualsButton(findViewById(R.id.eightButton));
            initNonEqualsButton(findViewById(R.id.nineButton));
            initNonEqualsButton(findViewById(R.id.buttonZero));
            initNonEqualsButton(findViewById(R.id.plusButton));
            initNonEqualsButton(findViewById(R.id.minusButton));
            initNonEqualsButton(findViewById(R.id.multButton));
            initNonEqualsButton(findViewById(R.id.expButton));
            initNonEqualsButton(findViewById(R.id.divButton));
            initNonEqualsButton(findViewById(R.id.buttonComa));
            initClearButton(findViewById(R.id.clearButton));
            initEqualsButton(findViewById(R.id.buttonEquals));
        }

        private void initNonEqualsButton(Button button) {
            button.setOnClickListener(v -> {

                StringBuilder text = new StringBuilder(textEdit.getText());
                textEdit.setText(String.format(Locale.getDefault(), "%s", text.append(button.getText())));
            });
        }
        private void initEqualsButton(Button button) {
            button.setOnClickListener(v -> {

                Calculator.calculate(String.valueOf(textEdit.getText()));
                textEdit.setText(String.format(Locale.getDefault(), "%s", Calculator.valuesStack.pop()));
            });
        }
        private void initClearButton(Button button) {
            button.setOnClickListener(v -> textEdit.setText(String.format(Locale.getDefault(), "%s", "")));
        }
}