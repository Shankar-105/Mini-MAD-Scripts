package com.example.myrepo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvExpression; // shows "previous operator" (small)
    private TextView tvDisplay;    // main large display

    // current shown value as string
    private String current = "0";
    // stored first operand (when an operator is set)
    private String previous = null;
    // pending operator (+, -, ×, ÷)
    private String operator = null;
    // whether user is currently typing the active number (affects how new digits are appended)
    private boolean isTypingNumber = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvExpression = findViewById(R.id.tvExpression);
        tvDisplay = findViewById(R.id.tvDisplay);

        // Back button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Numbers (including decimal)
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDecimal};
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(v -> appendNumber(((Button) v).getText().toString()));
        }

        // Operators
        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("÷"));

        // Equals
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculate());

        // AC (Clear All)
        findViewById(R.id.btnAC).setOnClickListener(v -> clearAll());

        // Delete (←)
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteLast());

        // %
        findViewById(R.id.btnPercent).setOnClickListener(v -> percent());

        // +/-
        findViewById(R.id.btnPlusMinus).setOnClickListener(v -> plusMinus());

        updateDisplay();
    }

    private void appendNumber(String input) {
        // Prevent more than one decimal point
        if (input.equals(".") && current.contains(".")) {
            return;
        }

        // If an operator is set and user hasn't started typing the second number, start fresh for the second operand
        if (operator != null && !isTypingNumber && previous != null) {
            // start typing the second operand
            if (input.equals(".")) {
                current = "0.";
            } else {
                current = input;
            }
            isTypingNumber = true;
        } else {
            if (!isTypingNumber) {
                // start fresh number (this happens on first digit entry or after equals/AC)
                if (input.equals(".")) {
                    current = "0.";
                } else {
                    current = input;
                }
                isTypingNumber = true;
            } else {
                // append digit or decimal
                current = current.equals("0") && !current.contains(".") && !input.equals(".")
                        ? input
                        : current + input;
            }
        }
        updateDisplay();
    }

    private void setOperator(String op) {
        // If a pending operator exists and user is typing a second number, compute the intermediate result
        if (operator != null && isTypingNumber && previous != null) {
            calculate();
        }

        // Move current display into previous operand and set the operator
        previous = current;
        operator = op;
        isTypingNumber = false;

        updateDisplay();
    }

    private void calculate() {
        if (operator == null || previous == null) {
            // nothing to compute
            return;
        }

        try {
            double prev = Double.parseDouble(previous);
            double curr = Double.parseDouble(current);
            double result = 0;

            switch (operator) {
                case "+":
                    result = prev + curr;
                    break;
                case "-":
                    result = prev - curr;
                    break;
                case "×":
                    result = prev * curr;
                    break;
                case "÷":
                    if (curr == 0) {
                        // simple handling for divide-by-zero
                        current = "Error";
                        previous = null;
                        operator = null;
                        isTypingNumber = false;
                        updateDisplay();
                        return;
                    } else {
                        result = prev / curr;
                    }
                    break;
            }

            current = formatResult(result);
            // clear pending operator (result becomes the new current)
            previous = null;
            operator = null;
            isTypingNumber = false;
            updateDisplay();
        } catch (NumberFormatException e) {
            // parse error — reset safely
            clearAll();
        }
    }

    private void clearAll() {
        current = "0";
        previous = null;
        operator = null;
        isTypingNumber = false;
        updateDisplay();
    }

    private void deleteLast() {
        if (isTypingNumber) {
            // remove last character from the active number
            if (current.length() > 1) {
                current = current.substring(0, current.length() - 1);
                // if we end up with a lone "-" or empty, reset to 0
                if (current.equals("-") || current.equals("") || current.equals("-0")) {
                    current = "0";
                    isTypingNumber = false;
                }
            } else {
                current = "0";
                isTypingNumber = false;
            }
        } else {
            // If user hasn't started typing and an operator is set, clear the operator (go back to editing previous)
            if (operator != null) {
                operator = null;
            } else {
                // otherwise reset the current value
                current = "0";
            }
        }
        updateDisplay();
    }

    private void percent() {
        try {
            double val = Double.parseDouble(current) / 100.0;
            current = formatResult(val);
            // percent is considered a completed entry
            isTypingNumber = false;
            updateDisplay();
        } catch (NumberFormatException e) {
            // ignore parse error
        }
    }

    private void plusMinus() {
        if (current.equals("0") || current.equals("0.0") || current.equals("Error")) return;

        if (current.startsWith("-")) {
            current = current.substring(1);
        } else {
            current = "-" + current;
        }
        updateDisplay();
    }

    private void updateDisplay() {
        // Expression line shows previous and operator when there is a pending operation
        if (previous != null && operator != null) {
            tvExpression.setText(previous + " " + operator);
            tvExpression.setVisibility(View.VISIBLE);
        } else {
            tvExpression.setText("");
            tvExpression.setVisibility(View.GONE);
        }

        tvDisplay.setText(current);
    }

    /**
     * Format result to remove unnecessary trailing zeros and avoid scientific notation for typical values.
     */
    private String formatResult(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        String out = bd.stripTrailingZeros().toPlainString();
        if (out == null || out.isEmpty()) return "0";
        return out;
    }
}