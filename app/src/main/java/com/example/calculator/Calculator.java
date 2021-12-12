package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    static List<StringBuilder> listOperands;
    static Stack<Operation> operationStack;
    static Stack<StringBuilder> valuesStack;

    public static void init() {
        operationStack = new Stack<>();
        valuesStack = new Stack<>();
    }

    public static void execution() {

        double a2 = Double.parseDouble(valuesStack.pop().toString());
        double a1 = Double.parseDouble(valuesStack.pop().toString());
        Operation operation = operationStack.pop();
        valuesStack.push(new StringBuilder(Double.toString(operation.action(a1,a2))));

    }

    public static void calculate(String F) {
        Operation current;
        Operation top;

        init();
        parse(F);

        for (int i = 0; i <= listOperands.size() - 1; i++) {
            current = Operation.getOperation(listOperands.get(i).toString());
            if (current != null) {
                switch (current) {
                    case BRACKET_LEFT:
                        operationStack.push(current);
                        break;
                    case PLUS:
                    case MINUS:
                    case MULTIPLICATION:
                    case DIVISION:
                    case EXPONENTIAL:
                        if (operationStack.isEmpty()) {
                            operationStack.push(current);
                            break;
                        }
                        top = operationStack.peek();
                        if (current.getPriority() > top.getPriority()) {
                            operationStack.push(current);
                            break;
                        } else {
                            execution();
                            operationStack.push(current);
                            break;
                        }
                    case BRACKET_RIGHT:
                        while (true) {
                            top = operationStack.peek();
                            if (top.getTitle().equals("(")) {
                                top = operationStack.pop();
                                break;
                            }
                            execution();
                        }
                        break;
                    default:
                }
            } else
                valuesStack.push(listOperands.get(i));
        }

        while (!operationStack.isEmpty()) {
            execution();
        }
    }

    public static void parse(String formula) {
        StringBuilder formulaString = new StringBuilder(formula);

        StringBuilder Tmp = new StringBuilder();
        listOperands = new ArrayList<>();
        for (int i = 0; i < formulaString.length(); i++) {
            char s = formulaString.charAt(i);
            if (isOperation(s)) {
                if (s == '-' && (i == 0 || isOperation(formulaString.charAt(i - 1)))) {
                    Tmp.append(s);
                } else {
                    if (Tmp.length() > 0) {
                        listOperands.add(Tmp);
                        Tmp = new StringBuilder();
                    }
                    listOperands.add(new StringBuilder(Character.toString(s)));
                }
            } else Tmp.append(s);
        }
        if (Tmp.length() > 0) listOperands.add(Tmp);
    }

    private static boolean isOperation(char s) {
        switch (s) {
            case '+':
            case '-':
            case '*':
            case '^':
            case '/':
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

}
