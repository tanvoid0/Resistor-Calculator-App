package com.example.tanveer.resistorcalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] list = {"", "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Grey", "White"};
    private String[] multiplier = {"", "Silver", "Gold", "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue"};
    private String[] tolerance = {"None", "Violet", "Blue", "Green", "Silver", "Gold", "Brown", "Red"};
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    Button calculate;
    TextView result;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the spinner from the xml.
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
//        spinner5 = findViewById(R.id.spinner5);
        result = (TextView) findViewById(R.id.result);
        //create a list of items for the spinner.

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, multiplier);
        spinner3.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tolerance);
        spinner4.setAdapter(adapter);
        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Calculate();
            }
        });
    }

    public void Calculate(){
        int value1 = Band((String) spinner1.getSelectedItem().toString());
        int value2 = Band((String) spinner2.getSelectedItem().toString());

        double value3, value4, value5, value;
//        if(!toggle.isSelected()){
            value3 = Multiplier((String) spinner3.getSelectedItem().toString());
            value5 = Tolerance((String) spinner4.getSelectedItem().toString());

            value = (((10*value1)+value2) * value3);
//        }
//        else{
//            value3 = Band((String) spinner3.getSelectedItem());
//            value4 = Multiplier((String) spinner4.getSelectedItem());
//            value5 = Tolerance((String) spinner5.getSelectedItem());
//
//            value = (((100*value1)+(10*value2)+value3) * value4);
//        }
        result.setText("Result: "+prefix(value) + "Ω ±" + value5 + "%");
//        lblResult.setText(prefix(value) + "Ω ±" + value5 + "%");
//        Toast.makeText(this, prefix(value) + "Ω ±" + value5 + "%", Toast.LENGTH_LONG).show();


    }

    public static boolean isInt(double number){
        return Math.ceil(number) == Math.floor(number);
    }

    public String prefix(double value)
    {

        if(value >= 1000){
            value = value/1000;
            if(isInt(value))
                return String.valueOf((int)value)+" k";
            else
                return String.valueOf(value)+" k";

        }
        else if(value >= 1){
            if(isInt(value))
                return String.valueOf((int)value)+" ";
            else
                return String.valueOf(value)+" ";
        }
        else if(value >= .001){
            value = value/.001;
            if(isInt(value))
                return String.valueOf((int)value)+" m";
            else
                return String.valueOf(value)+" m";
        }
        else if(value >= .000001){
            value = value/.000001;
            if(isInt(value))
                return String.valueOf((int)value)+" µ";
            else
                return String.valueOf(value)+" µ";
        }
        else if(value >= .000000001){
            value =  value/.000000001;
            if(isInt(value))
                return String.valueOf((int)value)+" n";
            else
                return String.valueOf(value)+" n";
        }
        else{
            return "";
        }


    }

    public int Band(String color)
    {
        if(color == "Black")
            return 0;
        else if(color == "Brown")
            return 1;
        else if(color == "Red")
            return 2;
        else if(color == "Orange")
            return 3;
        else if(color == "Yellow")
            return 4;
        else if(color == "Green")
            return 5;
        else if(color == "Blue")
            return 6;
        else if(color == "Violet")
            return 7;
        else if(color == "Grey")
            return 8;
        else if(color == "White")
            return 9;
        else
            return -1;
    }

    public double Multiplier(String color)
    {
        if(color == "Black")
            return 1;
        else if(color == "Brown")
            return 10;
        else if(color == "Red")
            return 100;
        else if(color == "Orange")
            return 1000;
        else if(color == "Yellow")
            return 10000;
        else if(color == "Green")
            return 100000;
        else if(color == "Blue")
            return 1000000;
        else if(color == "Gold")
            return 0.1;
        else if(color == "Silver")
            return 0.01;
        else
            return -1;
    }

    public double Tolerance(String color)
    {
        if(color == "Violet")
            return 0.1;

        else if(color == "Blue")
            return 0.25;
        else if(color == "Green")
            return 0.5;
        else if(color == "Brown")
            return 1;
        else if(color == "Red")
            return 2;
        else if(color == "Gold")
            return 5;
        else if(color == "Silver")
            return 10;
        else
            return 20;
    }
}
