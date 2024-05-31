package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerFrom, spinnerTo;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//dois sppiners com o valor de entrada e saida
        editTextValue = findViewById(R.id.editTextValue);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }
//funcao para converter as unidaddes
    private void convertUnits() {
        String valueStr = editTextValue.getText().toString();
        if (!valueStr.isEmpty()) {
            double value = Double.parseDouble(valueStr);
            String unitFrom = spinnerFrom.getSelectedItem().toString();
            String unitTo = spinnerTo.getSelectedItem().toString();
            double result = 0.0;

            // utilizando switch case, definindo a entrada e saida
            switch (unitFrom) {
                case "km":
                    result = convertFromKm(value, unitTo);
                    break;
                case "m":
                    result = convertFromMeters(value, unitTo);
                    break;
                case "cm":
                    result = convertFromCm(value, unitTo);
                    break;
                case "mi":
                    result = convertFromMi(value, unitTo);
                    break;
            }

            textViewResult.setText(String.valueOf(result));
        }
    }
//conversao de km
    private double convertFromKm(double value, String unitTo) {
        switch (unitTo) {
            case "km":
                return value;
            case "m":
                return value * 1000;
            case "cm":
                return value * 100000;
            case "mi":
                return value / 1.60934;
            default:
                return 0.0;
        }
    }
//convertendo metros para a unidade de saida
    private double convertFromMeters(double value, String unitTo) {
        switch (unitTo) {
            case "km":
                return value / 1000;
            case "m":
                return value;
            case "cm":
                return value * 100;
            case "mi":
                return value / 1609.34;
            default:
                return 0.0;
        }
    }
//conversao de cm
    private double convertFromCm(double value, String unitTo) {
        switch (unitTo) {
            case "km":
                return value / 100000;
            case "m":
                return value / 100;
            case "cm":
                return value;
            case "mi":
                return value / 160934;
            default:
                return 0.0;
        }
    }
//conversao de Milhas
    private double convertFromMi(double value, String unitTo) {
        switch (unitTo) {
            case "km":
                return value * 1.60934;
            case "m":
                return value * 1609.34;
            case "cm":
                return value * 160934;
            case "mi":
                return value;
            default:
                return 0.0;
        }
    }
}
