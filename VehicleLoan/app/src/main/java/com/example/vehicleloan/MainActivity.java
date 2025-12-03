package com.example.vehicleloan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText vehiclePrice, downPayment, loanPeriod, interestRate;
    private TextView loanAmount, totalInterest, totalPayment, monthlyPayment;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        vehiclePrice = findViewById(R.id.price);
        downPayment = findViewById(R.id.down_payment);
        loanPeriod = findViewById(R.id.loan_period);
        interestRate = findViewById(R.id.rate);
        loanAmount = findViewById(R.id.amount);
        totalInterest = findViewById(R.id.interest);
        totalPayment = findViewById(R.id.total);
        monthlyPayment = findViewById(R.id.monthly);
        calculateButton = findViewById(R.id.calc);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoan();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculateLoan() {
        try {
            double price = Double.parseDouble(vehiclePrice.getText().toString());
            double down = Double.parseDouble(downPayment.getText().toString());
            int period = Integer.parseInt(loanPeriod.getText().toString());
            double rate = Double.parseDouble(interestRate.getText().toString());

            double loan = price - down;
            double interest = loan * (rate / 100) * period;
            double total = loan + interest;
            double monthly = total / (period * 12);

            loanAmount.setText(String.format("Loan amount: RM%.2f", loan));
            totalInterest.setText(String.format("Total interest : RM%.2f", interest));
            totalPayment.setText(String.format("Total payment: RM%.2f", total));
            monthlyPayment.setText(String.format("Monthly payment: RM%.2f", monthly));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent = new Intent(this, about.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
