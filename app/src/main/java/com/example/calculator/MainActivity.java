package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TextView tx ;

    String res="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tx=findViewById(R.id.tv);

    }
    public double evaluateExpression(String expression) {
        // Step 1: Initialize lists to store numbers and operators
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();


        // Step 2: Parse the expression character by character
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the character is a digit or a decimal point, it's part of a number
            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else {
                // If it's an operator, store the current number and the operator
                numbers.add(Double.parseDouble(currentNumber.toString()));
                operators.add(c);
                currentNumber.setLength(0);  // Reset the currentNumber for the next one
            }
        }

        // Add the last number after the loop finishes
        numbers.add(Double.parseDouble(currentNumber.toString()));

        // Step 3: Handle multiplication and division first
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '*' || operators.get(i) == '/') {
                double num1 = numbers.get(i);
                double num2 = numbers.get(i + 1);
                double resul = 0;

                if (operators.get(i) == '*') {
                    resul = num1 * num2;
                } else if (operators.get(i) == '/') {
                    resul = num1 / num2;
                }

                // Replace the two numbers by their result
                numbers.set(i, resul);
                numbers.remove(i + 1);  // Remove the second number
                operators.remove(i);    // Remove the operator
                i--;  // Adjust index since the lists are now shorter
            }
        }

        // Step 4: Handle addition and subtraction
        double resul = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '+') {
                resul += numbers.get(i + 1);
            } else if (operators.get(i) == '-') {
                resul -= numbers.get(i + 1);
            }
        }

        return resul;
    }
    public void zero(View view) {
        res+=0;
        tx.setText(res);
    }
    public void one(View view) {
        res+=1;
        tx.setText(res);
    }
    public void two(View view) {
        res+=2;
        tx.setText(res);
    }
    public void three(View view) {
        res+=3;
        tx.setText(res);
    }
    public void foor(View view) {
        res+=4;
        tx.setText(res);
    }
    public void five(View view) {
        res+=5;
        tx.setText(res);
    }
    public void six(View view) {
        res+=6;
        tx.setText(res);
    }
    public void seven(View view) {
        res+=7;
        tx.setText(res);
    }
    public void eight(View view) {
        res+=8;
        tx.setText(res);
    }
    public void nine(View view) {
        res+=9;
        tx.setText(res);
    }
    public void point(View view) {
        res+=".";
        tx.setText(res);
    }
    public void add(View view) {
        res+="+";
        tx.setText(res);
    }
    public void mul(View view) {
        res+="*";
        tx.setText(res);
    }
    public void min(View view) {
        res+="-";
        tx.setText(res);
    }
    public void equals(View view) {
        double d = evaluateExpression(res) ;
        res = String.valueOf(d);
        tx.setText(res);

    }
    public void div(View view) {
        res+="/";
        tx.setText(res);
    }
    public void clear(View view) {
        res="";
        tx.setText(res);
    }
}