package com.example.calculatorapp;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';
    private static final int MAX_INPUT_LENGTH = 30;

    private char currentSymbol;
    private boolean lastPressWasOperation = false;
    private boolean hasDecimalPoint = false;

    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonPercent, buttonClear, buttonOFF, buttonEqual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);
        decimalFormat = new DecimalFormat("#.##########");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        resetCalculator();

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);

        buttonAdd = findViewById(R.id.add);
        buttonSub = findViewById(R.id.subtract);
        buttonDivide = findViewById(R.id.division);
        buttonDot = findViewById(R.id.btnPoint);
        buttonMultiply = findViewById(R.id.multiply);
        buttonClear = findViewById(R.id.clear_text);
        buttonOFF = findViewById(R.id.off);
        buttonEqual = findViewById(R.id.equal);
        buttonPercent = findViewById(R.id.percent);


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber("9");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation(ADDITION, "+");
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation(SUBTRACTION, "-");
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation(MULTIPLICATION, "×");
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation(DIVISION, "÷");
            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperation(PERCENT, "%");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasDecimalPoint && inputDisplay.length() < MAX_INPUT_LENGTH) {
                    inputDisplay.setText(inputDisplay.getText() + ".");
                    hasDecimalPoint = true;
                    lastPressWasOperation = false;
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputDisplay.getText().length() > 0) {
                    CharSequence currentText = inputDisplay.getText();
                    inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
                    if (!currentText.toString().contains(".")) {
                        hasDecimalPoint = false;
                    }
                } else {
                    resetCalculator();
                }
            }
        });

        buttonOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!lastPressWasOperation && inputDisplay.getText().length() > 0) {
                    calculateRunningTotal();
                    inputDisplay.setText("");
                    lastPressWasOperation = true;
                    hasDecimalPoint = false;
                }
            }
        });
    }

    private void appendNumber(String number) {
        if (inputDisplay.length() < MAX_INPUT_LENGTH) {
            String currentInput = inputDisplay.getText().toString();
            
            // Handle first digit
            if (currentInput.equals("0") && !number.equals(".")) {
                inputDisplay.setText(number);
                return;
            }
            
            // Append digit
            String newInput = currentInput + number;
            inputDisplay.setText(newInput);
            
            // Adjust text size based on input length
            adjustInputTextSize(newInput);
            
            // If we have an operator in the expression, calculate running total
            if (currentInput.matches(".*[+\\-×÷%].*")) {
                calculateRunningTotal();
            }
        }
    }

    private void adjustInputTextSize(String input) {
        int length = input.length();
        if (length >= 15) { // x+4 and longer
            inputDisplay.setMaxLines(2);
        } else if (length >= 12) { // x+3
            inputDisplay.setMaxLines(1);
            inputDisplay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24); // Reduced text size
        } else {
            inputDisplay.setMaxLines(1);
            inputDisplay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32); // Regular text size
        }
    }

    private void handleOperation(char operation, String symbol) {
        try {
            String currentInput = inputDisplay.getText().toString();
            
            // Handle negative numbers at start
            if (currentInput.isEmpty() && operation == SUBTRACTION) {
                inputDisplay.setText("-");
                return;
            }
            
            // Don't add operator if input is empty (except minus) or ends with decimal point
            if (currentInput.isEmpty() || currentInput.endsWith(".")) {
                return;
            }

            // If last char was an operator, replace it
            if (isOperator(currentInput.charAt(currentInput.length() - 1))) {
                inputDisplay.setText(currentInput.substring(0, currentInput.length() - 1) + symbol);
                return;
            }

            // Append the operator to input display
            inputDisplay.setText(currentInput + symbol);
            
            // Calculate result if we have more than one number
            if (currentInput.matches(".*[+\\-×÷%].*")) {
                calculateRunningTotal();
            } else {
                // First number entered
                try {
                    double value = Double.parseDouble(currentInput);
                    outputDisplay.setText(decimalFormat.format(value));
                } catch (NumberFormatException e) {
                    // Ignore parse errors
                }
            }
            // Adjust text size based on new input
            adjustInputTextSize(currentInput + symbol);
        } catch (Exception e) {
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '%';
    }

    private void calculateRunningTotal() {
        String expression = inputDisplay.getText().toString();
        if (expression.isEmpty()) return;

        // If expression ends with an operator, remove it for calculation
        if (isOperator(expression.charAt(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1);
        }

        String[] numbers = expression.split("[+\\-×÷%]");
        String[] operators = expression.split("[0-9.]+");
        
        try {
            if (numbers.length == 0) {
                showError("Invalid expression");
                return;
            }

            double result = Double.parseDouble(numbers[0]);
            int operatorIndex = 1;
            
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i].isEmpty()) continue;
                double num = Double.parseDouble(numbers[i]);
                char operator = operators[operatorIndex].charAt(0);
                operatorIndex++;
                
                switch (operator) {
                    case '+':
                        result += num;
                        break;
                    case '-':
                        result -= num;
                        break;
                    case '×':
                        result *= num;
                        break;
                    case '÷':
                        if (num == 0) {
                            showError("Cannot divide by zero");
                            return;
                        }
                        result /= num;
                        break;
                    case '%':
                        if (num == 0) {
                            showError("Cannot calculate modulus with zero");
                            return;
                        }
                        result %= num;
                        break;
                }
            }
            
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                showError("Invalid Result");
                return;
            }
            
            outputDisplay.setText(decimalFormat.format(result));
        } catch (Exception e) {
            showError("Invalid Result");
        }
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        outputDisplay.setText("Error");
    }

    private void resetCalculator() {
        firstValue = Double.NaN;
        secondValue = Double.NaN;
        currentSymbol = '0';
        inputDisplay.setText("");
        outputDisplay.setText("");
        lastPressWasOperation = false;
        hasDecimalPoint = false;
    }

    public void onEqualsClick(View view) {
        String expression = inputDisplay.getText().toString();
        if (expression.isEmpty()) return;

        // Remove trailing operator if present
        if (isOperator(expression.charAt(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1);
            inputDisplay.setText(expression);
        }

        calculateRunningTotal();
    }
}