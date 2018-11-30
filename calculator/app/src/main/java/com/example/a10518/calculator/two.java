package com.example.a10518.calculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class two extends AppCompatActivity  implements View.OnClickListener{

    public class clu {
        BigDecimal number1;
        BigDecimal number2;

        //sub
        BigDecimal clulatesub(String h1,String h2){
            number1 = new BigDecimal(h1);
            number2 = new BigDecimal(h2);
            return number1.subtract(number2);
        }
        //add
        BigDecimal clulateadd(String h1,String h2){
            number1 = new BigDecimal(h1);
            number2 = new BigDecimal(h2);
            return number1.add(number2);
        }
        //multiply
        BigDecimal clulatemultiply(String h1,String h2){
            number1 = new BigDecimal(h1);
            number2 = new BigDecimal(h2);
            return number1.multiply(number2);
        }
        //divde
        BigDecimal clulatedivde(String h1,String h2){
            number1 = new BigDecimal(h1);
            number2 = new BigDecimal(h2);
            return number1.divide(number2);
        }
        //平方
        BigDecimal clulatem(String h1){
            number1 = new BigDecimal(h1);
            return number1.multiply(number1);
        }
        //阶乘
        double clulatex(String h1){
            int i = Integer.valueOf(h1).intValue();
            double result=1;
            while(i>=1){
                result = i*result;
                i--;
            }
            return result;
        }
        //sin
        double clulatesin(String h1){
            double i = Integer.valueOf(h1).intValue();
            double result=0;
            result = Math.sin(i);
            return result;
        }
        //cos
        double clulatecos(String h1){
            double i = Integer.valueOf(h1).intValue();
            double result=0;
            result = Math.cos(i);
            return result;
        }
        //tan
        double clulatetan(String h1){
            double i = Integer.valueOf(h1).intValue();
            double result=0;
            result = Math.tan(i);
            return result;
        }
    }


    TextView rsText = null;  //显示器
    String  num1, num2;
    BigDecimal Result;  //计算结果
    int op = 0; //判断操作数，
    boolean isClickEqu = false; //判断是否按了“=”按钮
    clu haha = new clu();//定义类实例
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);

        //功能按钮
        rsText = (TextView) findViewById(R.id.show);
        Button b_clear = (Button) findViewById(R.id.clear);//不能够清除
        Button b_back = (Button) findViewById(R.id.back);
        Button b_m = (Button) findViewById(R.id.m);
        Button b_x = (Button) findViewById(R.id.jiechen);
        Button b_sin = (Button) findViewById(R.id.sin);
        Button b_cos = (Button) findViewById(R.id.cos);
        Button b_tan = (Button) findViewById(R.id.tan);
        Button b_dian = (Button) findViewById(R.id.dian);
        Button b_dengyu = (Button) findViewById(R.id.dengyu);

        //返回按钮
        Button b_re = (Button) findViewById(R.id.re);

        //返回按钮功能
        b_re.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(two.this,two.class);
                two.this.startActivity(intent);
            }
        });

        //数字按钮
        Button b0 = (Button) findViewById(R.id.b0);
        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);
        Button b3 = (Button) findViewById(R.id.b3);
        Button b4 = (Button) findViewById(R.id.b4);
        Button b5 = (Button) findViewById(R.id.b5);
        Button b6 = (Button) findViewById(R.id.b6);
        Button b7 = (Button) findViewById(R.id.b7);
        Button b8 = (Button) findViewById(R.id.b8);
        Button b9 = (Button) findViewById(R.id.b9);

        //listener
        rsText.setOnClickListener(this);
        b_m.setOnClickListener(this);
        b_back.setOnClickListener(this);
        b_sin.setOnClickListener(this);
        b_cos.setOnClickListener(this);
        b_tan.setOnClickListener(this);
        b_x.setOnClickListener(this);
        b_dengyu.setOnClickListener(this);
        b_dian.setOnClickListener(this);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //AC和back
            case R.id.back:
                String myStr = rsText.getText().toString();
                try {
                    rsText.setText(myStr.substring(0, myStr.length() - 1));
                } catch (Exception e) {
                    rsText.setText("");
                }
                break;
            case R.id.clear:
                rsText.setText(null);
                break;
            //b0--9
            case R.id.b0:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString = rsText.getText().toString();
                myString += "0";
                rsText.setText(myString);
                break;
            case R.id.b1:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString1 = rsText.getText().toString();
                myString1 += "1";
                rsText.setText(myString1);
                break;
            case R.id.b2:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString2 = rsText.getText().toString();
                myString2 += "2";
                rsText.setText(myString2);
                break;
            case R.id.b3:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString3 = rsText.getText().toString();
                myString3 += "3";
                rsText.setText(myString3);
                break;
            case R.id.b4:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString4 = rsText.getText().toString();
                myString4 += "4";
                rsText.setText(myString4);
                break;
            case R.id.b5:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString5 = rsText.getText().toString();
                myString5 += "5";
                rsText.setText(myString5);
                break;
            case R.id.b6:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString6 = rsText.getText().toString();
                myString6 += "6";
                rsText.setText(myString6);
                break;
            case R.id.b7:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString7 = rsText.getText().toString();
                myString7 += "7";
                rsText.setText(myString7);
                break;
            case R.id.b8:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString8 = rsText.getText().toString();
                myString8 += "8";
                rsText.setText(myString8);
                break;
            case R.id.b9:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString9 = rsText.getText().toString();
                myString9 += "9";
                rsText.setText(myString9);
                break;
            case R.id.dian:
                if (isClickEqu) {
                    rsText.setText(null);
                    isClickEqu = false;
                }
                String myString0 = rsText.getText().toString();
                myString0 += ".";
                rsText.setText(myString0);
                break;
            case R.id.m:
                String myStringm = rsText.getText().toString();
                if (myStringm.equals(null)) {
                    return;
                }
                num1 = myStringm;
                rsText.setText(myStringm + "^2");
                op = 1;
                isClickEqu = false;
                break;
            case R.id.jiechen:
                String myStringx = rsText.getText().toString();
                if (myStringx.equals(null)) {
                    return;
                }
                num1 = myStringx;
                rsText.setText(myStringx + "!");
                op = 2;
                isClickEqu = false;
                break;
            case R.id.sin:
                String myStringsin = rsText.getText().toString();
                if (myStringsin.equals(null)) {
                    return;
                }
                num1 = myStringsin;
                rsText.setText("sin("+myStringsin + ")");
                op = 3;
                isClickEqu = false;
                break;
            case R.id.cos:
                String myStringcos = rsText.getText().toString();
                if (myStringcos.equals(null)) {
                    return;
                }
                num1 = myStringcos;
                rsText.setText("cos("+myStringcos + ")");
                op = 4;
                isClickEqu = false;
                break;
            case R.id.tan:
                String myStringtan = rsText.getText().toString();
                if (myStringtan.equals(null)) {
                    return;
                }
                num1 = myStringtan;
                rsText.setText("cos("+myStringtan + ")");
                op = 5;
                isClickEqu = false;
                break;
            case R.id.dengyu:
                String myStringEqu = rsText.getText().toString();
                if (myStringEqu.equals(null)) {
                    return;
                }
                num2 = myStringEqu;
                rsText.setText(null);
                switch (op) {
                    case 0://本身
                        double i = Integer.valueOf(num2).intValue();
                        Result = BigDecimal.valueOf(i);
                        break;
                    case 1://平方
                        Result = haha.clulatem(num1);
                        break;
                    case 2://阶乘
                        rsText.setText(String.valueOf(haha.clulatex(num1)));
                        return;
                    case 3:			   			             	rsText.setText(String.valueOf(haha.clulatesin(num1)));;
                        return;
                    case 4:
                        rsText.setText(String.valueOf(haha.clulatecos(num1)));
                        return;
                    case 5:
                        rsText.setText(String.valueOf(haha.clulatetan(num1)));
                        return;
                    default:
                        Result = BigDecimal.valueOf('0');
                        break;
                }
                rsText.setText(String.valueOf(Result));
                isClickEqu = true;
                break;
            default:
                break;
        }
    }
}