package com.example.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentOperator;
    private double firstValue;
    private boolean operatorPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        currentOperator = "";
        firstValue = 0;
        operatorPressed = false;

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDot
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                String currentText = display.getText().toString();

                if (operatorPressed) {
                    currentText = "0";
                    operatorPressed = false;
                }

                if (currentText.equals("0") && !buttonText.equals(".")) {
                    display.setText(buttonText);
                } else {
                    display.setText(currentText + buttonText);
                }
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {
                R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply,
                R.id.buttonDivide, R.id.buttonEquals
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();

                if (!buttonText.equals("=")) {
                    currentOperator = buttonText;
                    firstValue = Double.parseDouble(display.getText().toString());
                    operatorPressed = true;
                } else {
                    calculateResult();
                }
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void calculateResult() {
        double secondValue = Double.parseDouble(display.getText().toString());
        double result = 0;

        switch (currentOperator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                result = firstValue / secondValue;
                break;
        }

        display.setText(String.valueOf(result));
        firstValue = result;
        operatorPressed = true;
    }
}
