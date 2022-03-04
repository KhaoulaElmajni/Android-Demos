package com.example.intelligentcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //2 operands for the operation
    private double operand1 = 0.0, operand2 = 0.0;
    //boolean to test which is the courant operand
    private Boolean isOp1 = true;
    //operation's operator
    private String operator = null;
    //Buttons
    Button one, two,three,four,five,six,seven,eighth,nine,zero,plus,sub,multiply,divide,equal,clear;
    //operations screen
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAllViews();
    }

    public  void afficher(){
        double value=operand1;
        if(!isOp1) {
            value=operand2;
        }
        screen.setText(String.valueOf(value));
    }

    public void setOperator(View view){
        switch (view.getId()) {
            case R.id.plus : operator="+";  break;
            case R.id.sub: operator="-"; break;
            case R.id.multiply : operator="x";  break;
            case R.id.divide : operator="/";   break;
            default : return;
        }
        isOp1=false;
        afficher();
    }

    public void ajouterNbr(View view){
        //get the value of the button and parsing it
            double value = Double.parseDouble(((Button)view).getText().toString());
            /*in case of there is more than 1 digit in the number
                    multiply with 10 in both of the 2 operands
                */
            if (isOp1) {
                operand1 = operand1 * 10 + value;
                afficher();
            } else {
                operand2 = operand2 * 10 + value;
                afficher();
            }
    }

    //calculate the operation
    public void calculer(View view){
        //testing the courant operand in then calculate with appropriate operator
        if(!isOp1){
            switch(operator) {
                case "+" : operand1 = operand1 + operand2; break;
                case "-" : operand1 = operand1 - operand2; break;
                case "x" : operand1 = operand1 * operand2; break;
                case "/" : operand1 = operand1 / operand2; break;
                default : return;
            }
            operand2 = 0;
            isOp1 = true;
            afficher();
        }

    }

    //reset screen function
    public void clean(View view){
        operand1 = operand2 = 0;
        screen.setText("0");
    }

    //function for linking the buttons with the design
    public void setAllViews(){
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eighth = findViewById(R.id.eigth);
        nine = findViewById(R.id.nine);
        plus = findViewById(R.id.plus);
        sub = findViewById(R.id.sub);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        screen = findViewById(R.id.result);
        equal = findViewById(R.id.equal);
    }
}