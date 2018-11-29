package com.example.a10518.calculator;

import android.service.autofill.FieldClassification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class calculator extends AppCompatActivity {
    private int[] number = {R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine};
    private int[] Operator = {R.id.add, R.id.sub, R.id.mul, R.id.div, R.id.point};
    private Button[] bNumber = new Button[number.length];     //按钮
    private Button[] bOperator = new Button[Operator.length];  //计算
    private Button bCls;   //AC
    private Button bBackspace;
    private Button bEqual;  //=
    private EditText input;
    private EditText result;
    private static String Text;

    @Override
    //在EditText进行输入和输出结果
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        input = (EditText) findViewById(R.id.result);
        input.setText("");
        input.setEnabled(false);
        result = (EditText) findViewById(R.id.result);
        result.setText("");
        bEqual = (Button) findViewById(R.id.equal);
        bEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(new Calculate(input.getText().toString()).str);
            }
        });

        bCls = (Button) findViewById(R.id.cls);
        bCls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                result.setText("");
            }
        });

        bBackspace = (Button) findViewById(R.id.Backspace);
        bBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.getText().toString().isEmpty()) {
                    Text = input.getText().toString();
                    Text = Text.substring(0,Text.length()-1);
                    input.setText(Text);
                }
            }
        });

        //注册单击事件
        for (int operator = 0; operator < Operator.length; operator++) {
            bOperator[operator] = (Button) findViewById(Operator[operator]);
            bOperator[operator].setOnClickListener(new OperatorOnClick(bNumber[operator].getText().toString()));
        }
        for (int i = 0; i < number.length; i++) {
            bNumber[i] = (Button) findViewById(number[i]);
            bNumber[i].setOnClickListener(new NumberOnClick(bNumber[i].getText().toString()));
        }

    }

    //继承OnClick接口
    class NumberOnClick implements View.OnClickListener {
        String Msg;

        //@param  msg 点击按钮传入字符
        public NumberOnClick(String msg) {
            Msg = msg;
        }

        @Override
        public void onClick(View v) {
            if (!result.getText().toString().equals("")) {
                input.setText("");
                result.setText("");
            }
            input.append(Msg);
        }
    }

    class OperatorOnClick implements View.OnClickListener {
        String Msg;
        String[] OperatorSymbol = {"+", "-", "*", "/", "."};

        public OperatorOnClick(String msg) {
            Msg = msg;
        }

        @Override
        public void onClick(View v) {
            if (!result.getText().toString().equals("")) {
                input.setText("");
                result.setText("");
            }

            //检查运算符是否多次输入
            for (int i = 0; i < OperatorSymbol.length; i++) {
                if (Msg.equals(OperatorSymbol[i])) {
                    if (input.getText().toString().split("")[input.getText().toString().split().length - 1].equals(OperatorSymbol[i])) {
                        Msg = "";
                    }
                }
            }
            input.append(Msg);
        }
    }



    //运算，返回一个String结果
    public class Calculate{
        public String s1;
        StringBuilder str;

        public Calculate(String m){
            this.s1 = m;
            try {
                eval();
        }catch(Exception e){
                str.delete(0,str.length());
                str.append("ERROR");
            }
    }

        /**
         *中缀表达式转后缀表达式
         *
         *遍历中缀的list
         *1、数字时，加入后缀list
         *2、“(”时，压栈
         *3、 若为 ')'，则依次弹栈,把弹出的运算符加入后缀表达式中，直到出现'('；
         *4、若为运算符，对做如下处置
         *   1、如果栈为空，则压栈
         *   2、如果栈不为空:
         *     1、stack.peek().equals("(")  则压栈
         *     2、比较str和stack.peek()的优先级
         *        1、如果>,则运算符压栈
         *        2、<=的情况：当栈不为空时:
         *           1、stack.peek()是左括号，压栈
         *           2、<=,把peek加入后缀表达式，弹栈
         *           3、>，把运算符压栈，停止对栈的操作
         *    执行完栈的操作之后，还得判断:如果栈为空,运算符压栈
         */

        public List<String> midToAfter(List<String> midList)throws EmptyStackException{
            List<String> afterList = new ArrayList<String>();
            Stack<String > stack = new Stack<String>();
            for (String str:midList){
                int flag = this.matchWitch(str);
                switch (flag){
                    case 7:
                        afterList.add(str);
                        break;
                    case 1:
                        stack.push(str);
                        break;
                    case 2:
                        String pop = stack.pop();
                        while (!pop.equals("(")){
                            afterList.add(pop);
                            pop = stack.pop();
                        }
                        break;
                        default:
                            if (stack.isEmpty()){
                                stack.push(str);
                                break;
                            }
                            else {
                                if (stack.peek().equals("(")){
                                    stack.push(str);
                                    break;
                                }
                                else {
                                    int ji1 = this.youxianji(str);
                                    int ji2 = this.youxianji(stack.peek());
                                    if(ji1>ji2){
                                        stack.push(str);
                                    }
                                    else{
                                        while(!stack.isEmpty()){
                                            String f=stack.peek();
                                            if(f.equals("(")){
                                                stack.push(str);
                                                break;
                                            }
                                            else {
                                                if (this.youxianji(str) <= this.youxianji(f)){
                                                    afterList.add(f);
                                                    stack.pop();
                                                }
                                                else{
                                                    stack.push(str);
                                                    break;
                                                }
                                            }
                                        }
                                        if(stack.isEmpty()){
                                            stack.push(str);
                                        }
                                    }
                                    break;
                                }
                            }
                }
            }
            while(!stack.isEmpty()){
                afterList.add(stack.pop());
            }
            StringBuffer sb = new StringBuffer();
            for (String s:afterList){
                sb.append(s+" ");
            }

            return afterList;
        }


        //判断运算的优先级
        public int youxianji(String str){
            int result = 0;
            if (str.equals("+")|| str.equals("-")){
                result=1;
            }
            else {
                result = 2;
            }
            return result;
        }


        //判断字符串属于操作数还是操作符
        public int matchWitch(String s){
            if (s.equals("+")){
                return 1;
            }
            else if (s.equals("-")){
                return 2;
            }
            else if (s.equals("*")){
                return 3;
            }
            else if (s.equals("/")){
                return 4
            }
            else {
                return 5;
            }
        }


        //计算的简单方法
        public Double singleEval(Double pop2,Double pop1, String str){
            Double value = 0.0;
            if (str.equals("+")){
                value = pop2 + pop1;
            }
            else if (str.equals("-")){
                value = pop2 - pop1;
            }
            else if (str.equals("*")){
                value = pop2 * pop1;
            }
            else{
                value = pop2 / pop1;
            }
            return value;
        }
        private double result;
        public double getResult(){
            return result;
        }
        public void setResult(double result){
            this.result = result;
        }

        private int state;
        public int getState(){
            return state;
        }
        public void setState(int state){
            this.state = state;
        }


        public void countHouzhui(List<String> list){
            str = new StringBuilder("");
            state = 0;
            result = 0;
            Stack<Double> stack = new Stack<Double>();
            for (String str:list){
                int flag = this.matchWitch(str);
                switch (flag){
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        Double pop1 = stack.pop();
                        Double pop2 = stack.pop();
                        Double value = this.singleEval(pop2, pop1,str);
                        stack.push(value);
                        break;
                    default:
                        Double push = Double.parseDouble(str);
                        stack.push(push);
                        break;
                }
            }
            if (stack.isEmpty()){
                state = 1;
            }
            else {
                result = stack.peek();
                str.append(stack.pop());
            }
        }

        public void eval()throws  Exception{
            List<String> list = new ArrayList<String>();
            //
            Pattern p = Pattern.compile("[+\\-/\\*] | \\d+\\.?\\d*");
            Matcher m = p.matcher(s1);
            while(m.find()){
                list.add(m.group());
            }
            List<String> afterList = this.midToAfter(list);
            this.countHouzhui(afterList);
        }
    }
}