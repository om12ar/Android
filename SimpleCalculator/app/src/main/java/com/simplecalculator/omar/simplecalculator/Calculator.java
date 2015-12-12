package com.simplecalculator.omar.simplecalculator;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by omar on 12/4/2015.
 */
public class Calculator {

    public static String calculate(String operationString) throws Exception {
        ArrayList<String> tokens = getTokens(operationString);
        System.err.println(operationString + tokens);
        Stack<String> operators = new Stack<>();
        Stack<String> numbers = new Stack<>();
        boolean willMultiply = false ;
        for(String s : tokens){
            if(isNum(s.charAt(0))){
                willMultiply = true ;
                numbers.push(s);
            }
            else if(isOperator(s.charAt(0))){
                willMultiply = false ;
                if(operators.isEmpty()){
                    operators.push(s);
                    continue;
                }
                while(!operators.isEmpty() && Priority(s.charAt(0)) <= Priority(operators.peek().charAt(0))){
                    Double value = operate(Double.parseDouble(numbers.pop()), Double.parseDouble(numbers.pop()), operators.pop());
                    numbers.push(value.toString());
                    System.err.println(value);
                }

                operators.push(s);

            }
            else if(s.charAt(0) == '('){

                if(willMultiply){
                    operators.push("*");
                }
                operators.push(s);
                willMultiply = false ;
            }
            else if(s.charAt(0) == ')') {
                willMultiply = true ;
                while (operators.peek().charAt(0) != '(') {
                    System.out.println(numbers.peek());

                    System.out.println(operators.peek());

                    Double value = operate(Double.parseDouble(numbers.pop()), Double.parseDouble(numbers.pop()), operators.pop());
                    numbers.push(value.toString());
                    System.err.println(value);
                }
                operators.pop();

            }
            System.out.println(operators);
            System.out.println(numbers);

        }
        while(!operators.isEmpty()){
            Double value = operate(Double.parseDouble(numbers.pop()), Double.parseDouble(numbers.pop()), operators.pop());
            numbers.push(value.toString());
            System.err.println(value);
        }
        if(numbers.isEmpty())
            return "0.0";
        return numbers.peek() ;
    }
    private static double operate(double num2 , double num1 , String op) throws Exception {
        double result = 0.0 ;
        switch (op) {
            case "+":
                result=num1+num2;
                break;
            case "-":
                result=num1-num2;
                break;
            case "*":
                result=num1*num2;
                break;
            case "/":
                result=num1/num2;
                break;
            case "%":
                result=num1%num2;
                break;
            case "^":
                result=Math.pow(num1,num2);
                break;
            case "√":
                    result=Math.pow(num2,1.0/num1);

                break;

        }
        return result;
    }
    private static ArrayList<String> getTokens (String s){
        ArrayList<String> tokens = new ArrayList<>();
        String token ="";

        for(int i =0 ;i < s.length() ;i++){
            if(isNum(s.charAt(i))){
                token+=s.charAt(i);
            }
            else{
                if(!token.isEmpty())
                    tokens.add(token);
                tokens.add(""+s.charAt(i));
                token="";
            }
        }
        if(!token.isEmpty())
            tokens.add(token);
        return tokens;
    }
    private static boolean isNum(char c){
        return ((c>='0' && c <='9')|| (c=='.') );
    }

    private static boolean isOperator(char c){
        return (c=='+' || c=='-' || c=='*' || c=='/' || c=='%' || c=='^' || c=='√');
    }
    private static int Priority(char c){
        switch(c){
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
            case '√':
                return 3;
        }
        return -1;
    }
}


