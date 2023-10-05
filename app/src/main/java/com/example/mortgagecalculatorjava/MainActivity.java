package com.example.mortgagecalculatorjava;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noteme.R;


public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private EditText editTextInterestRate;
    private EditText editTextTenure;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextTenure = findViewById(R.id.editTextTenure);
        textViewResult = findViewById(R.id.textViewResult);
    }

    public void calculateEMI(View view) {
        String amountString = editTextAmount.getText().toString();
        String interestRateString = editTextInterestRate.getText().toString();
        String tenureString = editTextTenure.getText().toString();

        if (TextUtils.isEmpty(amountString) || TextUtils.isEmpty(interestRateString) || TextUtils.isEmpty(tenureString)) {
            // Handle empty input fields
            textViewResult.setText("Please enter all values");
            return;
        }

        double principal = Double.parseDouble(amountString);
        double interestRate = Double.parseDouble(interestRateString);
        double tenure = Double.parseDouble(tenureString);

        // Monthly interest rate
        double monthlyRate = interestRate / 100 / 12;

        // Total number of payments
        double totalPayments = tenure * 12;

        // Calculate EMI using the formula
        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, totalPayments)) /
                (Math.pow(1 + monthlyRate, totalPayments) - 1);

        // Display the result
        textViewResult.setText("Monthly EMI: $" + String.format("%.2f", emi));
    }
}
