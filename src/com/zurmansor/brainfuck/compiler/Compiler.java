package com.zurmansor.brainfuck.compiler;

import java.util.ArrayList;

public class Compiler {
    private String code;
    private ArrayList<Operation> operationList = new ArrayList<Operation>();
    private int operationIndex = 0;

    public Compiler() {
    }

    public Compiler(String code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    static public void scan(String code){
//
//        ArrayList<Integer> operationList = new ArrayList<Integer>();
//
//        for (int i = 0; i < code.length(); i++) {
//
//        }
////        operationList.add();
//    }

    public String compress(){
        String codeRes = "";
        int temp = 0;

        for (int i = 0; i < code.length(); i++) {
            switch (code.charAt(i)) {
                case '+':
                    createCellIfEmpty(Operation.Command.ADD);
                    i = i + collect('+', i) - 1;
                    break;

                case '-':
                    createCellIfEmpty(Operation.Command.ADD);
                    i = i + collect('-', i) - 1;
                    break;

                case '>':
                    createCellIfEmpty(Operation.Command.STEP);
                    i = i + collect('>', i) - 1;
                    break;

                case '<':
                    createCellIfEmpty(Operation.Command.STEP);
                    i = i + collect('<', i) - 1;
                    break;

                case '[':
                    createCellIfEmpty(Operation.Command.CYCLE);
                    i = i + collect('[', i) - 1;
                    break;

                case ']':
                    createCellIfEmpty(Operation.Command.CYCLE);
                    i = i + collect(']', i) - 1;
                    break;

                case '.':
                    createCellIfEmpty(Operation.Command.PRINT);
                    i = i + collect('.', i) - 1;
                    break;

                default:
                    operationIndex--;
                    break;

            }
            operationIndex++;
//            if (code.charAt(i) == '+') {
//                int
//            }
        }
        return codeRes;
    }

    private void createCellIfEmpty(Operation.Command type) {
        if (operationIndex >= operationList.size()) {
            operationList.add(operationIndex, new Operation(type));
        }
    }

    private int collect (char operation, int i) {
        while (i < code.length() && code.charAt(i) == operation) {
            if (operation == '-' || operation == '<' || operation == ']') {
                operationList.get(operationIndex).loseWeight();
            } else {
                operationList.get(operationIndex).gainWeight();
            }
            i++;
        }
        return operationList.get(operationIndex).getWeightAbs();
    }

}
