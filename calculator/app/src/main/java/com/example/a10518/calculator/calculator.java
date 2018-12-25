package com.example.a10518.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class calculator extends Activity implements View.OnClickListener {

    private TextView textview;
    private double result;
    private String number1,number2,string;
    private boolean addNum = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.putong);

        // 隐藏标题栏
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
//            actionBar.hide();
//        }

        // 隐藏通知栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        // 定义控件
        Button button0 = (Button) findViewById(R.id.zero);
        Button button1 = (Button) findViewById(R.id.one);
        Button button2 = (Button) findViewById(R.id.two);
        Button button3 = (Button) findViewById(R.id.three);
        Button button4 = (Button) findViewById(R.id.four);
        Button button5 = (Button) findViewById(R.id.five);
        Button button6 = (Button) findViewById(R.id.six);
        Button button7 = (Button) findViewById(R.id.seven);
        Button button8 = (Button) findViewById(R.id.eight);
        Button button9 = (Button) findViewById(R.id.nine);
        Button buttonCls = (Button) findViewById(R.id.cls);
        Button buttonPoint =  (Button) findViewById(R.id.point);
        Button buttonEqual =  (Button) findViewById(R.id.equal);
        Button buttonAdd =  (Button) findViewById(R.id.add);
        Button buttonSub =  (Button) findViewById(R.id.sub);
        Button buttonMul =  (Button) findViewById(R.id.mul);
        Button buttonDiv =  (Button) findViewById(R.id.div);
        Button buttonBackpace = (Button) findViewById(R.id.Backspace);
        textview = (TextView) findViewById(R.id.text);
        addNum = true;

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonCls.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonBackpace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        string = (String)textview.getText();
        switch (view.getId()){
            case R.id.cls:
                textview.setText("");
                break;
            case R.id.Backspace:
                if(!string.equals("") && string != null){
                    textview.setText(string.substring(0,string.length()-1));
                }
                break;
            case R.id.equal:
                if(string.contains("+")){
                    calculate(number1,number2, "+");
                }
                else if(string.contains("-")){
                    calculate(number1,number2,"-");
                }
                else if (string.contains("*")){
                    calculate(number1,number2,"*");
                }
                else if (string.contains("/")){
                    calculate(number1,number2,"/");
                }
                else {
                    return;
                }
                break;
            case R.id.add:
            case R.id.sub:
            case R.id.mul:
            case R.id.div:
                if (string.contains("+")|| string.contains("-") || string.contains("*") || string.contains("/"))
                    return;
                else
                    textview.setText(string+((Button)view).getText());
                if(!addNum)
                    addNum = true;
                break;
            default:
                if (addNum){
                    textview.setText(string + ((Button)view).getText());
                }
                else{
                    textview.setText(((Button)view).getText());
                    addNum = true;
                }
                break;
        }
    }


    private void calculate(String number1, String number2, String  operate){
        number1 = string.substring(0, string.indexOf(operate));
        number2 = string.substring(string.indexOf(operate)+1);
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);

        if(operate.equals("+")){
            result = num1 + num2;
        }
        else if (operate.equals("-")){
            result = num1 - num2;
        }
        else if (operate.equals("*")){
            result = num1 * num2;
        }
        else if (operate.equals("*")){
            result = num1 / num2;
        }

        String Endresult = result + "";
        if (Endresult.contains(".") && Endresult.substring(Endresult.length()-1).equals("0")){
            Endresult = Endresult.substring(0, Endresult.indexOf("."));
        }

        textview.setText(Endresult);
        addNum = false;
    }
}

