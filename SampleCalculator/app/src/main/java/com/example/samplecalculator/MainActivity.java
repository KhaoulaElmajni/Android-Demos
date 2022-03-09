package com.example.samplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ast.Scope;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Buttons
    Button one, two,three,four,five,six,seven,eighth,nine,zero,plus,sub,multiply,divide,equal,clear,dot,percent,parenthesis;
    //input & output
    TextView input, output;
    //expression to calculate
    String expression="";
    public boolean checkParenthesis=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAllViews();

        parenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkParenthesis){
                    expression+=")";
                    input.setText(expression);
                    checkParenthesis=false;
                }else {
                    expression+="(";
                    input.setText(expression);
                    checkParenthesis=true;
                }
            }
        });
    }

    public void addCharacter(View view) {
        //get the value of the button and parsing it
        String value = ((Button)view).getText().toString();
        switch (value){
            case "0" : expression+="0";break;
            case "1" : expression+="1";break;
            case "2" : expression+="2";break;
            case "3" : expression+="3";break;
            case "4" : expression+="4";break;
            case "5" : expression+="5";break;
            case "6" : expression+="6";break;
            case "7" : expression+="7";break;
            case "8" : expression+="8";break;
            case "9" : expression+="9";break;
            case "+" : expression+="+";break;
            case "-" : expression+="-";break;
            case "/" : expression+="/";break;
            case "x" : expression+="x";break;
            case "." : expression+=".";break;
            case "%" : expression+="%";break;
            default:break;
        }
        input.setText(expression);
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
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        dot = findViewById(R.id.dot);
        equal = findViewById(R.id.equal);
        parenthesis = findViewById(R.id.parenthesis);
        clear = findViewById(R.id.clear);
        percent = findViewById(R.id.percent);
    }

    public void clear(View view) {
        checkParenthesis = false;
        expression = "";
        input.setText("0");
        output.setText("0");
    }

    public void calculate(View view) {
        expression = expression.replaceAll("x","*");
        expression = expression.replaceAll("%","/100");
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String result = "";
        try {
            Scriptable scriptable = rhino.initStandardObjects();
            result = rhino.evaluateString(scriptable,expression,"javascript",1,null).toString();
        }catch (Exception e){
            result="0";
        }
        output.setText(result);
    }
}