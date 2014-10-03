package com.zurmansor.brainfuck.compiler;

import com.zurmansor.brainfuck.exception.BracketException;
import com.zurmansor.brainfuck.exception.BrainFuckException;
import com.zurmansor.brainfuck.exception.NegativeStringException;
import com.zurmansor.brainfuck.exception.UnknownGrammarException;

import java.util.ArrayList;

public class Compiler {
    private String REGEX = "[+-.\\[\\]<>]*";

    private String code;
    private int operationIndex = 0; // индекс операции
    private int cell = 0;           // индекс ячейки
    private ArrayList<Operation> operationList = new ArrayList<Operation>();    // массив операций
    private ArrayList<Integer> chain = new ArrayList<Integer>();                // массив ячеек
    private ArrayList<Integer> result = new ArrayList<Integer>();               // массив вывода

    public Compiler() {

    }

    public Compiler(String code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void start() throws BrainFuckException {
        lexical();
        compress();
        semantic();
        print();
    }

    private String compress() throws BracketException {
        String codeRes = "";
        int temp = 0;
        int brackets = 0;

        for (int i = 0; i < code.length(); i++) {
            switch (code.charAt(i)) {
                case '+':
                    operationList.add(operationIndex, new Operation(Operation.Command.ADD));
                    i = i + collect('+', i) - 1;
                    break;

                case '-':
                    operationList.add(operationIndex, new Operation(Operation.Command.ADD));
                    i = i + collect('-', i) - 1;
                    break;

                case '>':
                    operationList.add(operationIndex, new Operation(Operation.Command.STEP));
                    i = i + collect('>', i) - 1;
                    break;

                case '<':
                    operationList.add(operationIndex, new Operation(Operation.Command.STEP));
                    i = i + collect('<', i) - 1;
                    break;

                case '[':
                    operationList.add(operationIndex, new Operation(Operation.Command.CYCLE, 1));
                    brackets++;
//                    i = i + collect('[', i) - 1;
                    break;

                case ']':
                    operationList.add(operationIndex, new Operation(Operation.Command.CYCLE, -1));
                    brackets--;
//                    i = i + collect(']', i) - 1;
                    break;

                case '.':
                    operationList.add(operationIndex, new Operation(Operation.Command.PRINT));
                    i = i + collect('.', i) - 1;
                    break;

                default:
                    operationIndex--;
                    break;

            }
            operationIndex++;
        }

        if (brackets != 0) {
            throw new BracketException();
        }
        return codeRes;
    }

    private void addOperation(Operation.Command type) {
        operationList.add(operationIndex, new Operation(type));
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

    private void lexical() throws UnknownGrammarException {
//        remove spaces
        code = code.replaceAll("[\\s\n]+","");

        if (!code.matches(REGEX)) {
            throw new UnknownGrammarException();
        }
    }

    private void semantic() throws NegativeStringException {
        chain.add(0);
        for (operationIndex = 0; operationIndex < operationList.size(); operationIndex++) {
            Operation operation = operationList.get(operationIndex);

            switch (operation.getCommand()){
                case ADD:
                    // прибавляем вес текущей ячейке
                    chain.set(cell, chain.get(cell) + operation.getWeight());
                    break;

                case STEP:
                    makeStep(operation.getWeight());
                    break;

                case CYCLE:
//                    semantic();
                    if (operation.getWeight() > 0) {
//                        opening bracket
                        if (chain.get(cell) <= 0) {
                            // если текущая ячейка 0, перейти на операцию последующую за операцией закрывания текущего цикла
                            int bracket = 1;
                            while (bracket > 0) {
                                operationIndex++;
                                operation = operationList.get(operationIndex);
                                if (operation.getCommand() == Operation.Command.CYCLE) {
                                    bracket += operation.getWeight();
                                }
                            }

                        }
                    } else {
//                        closing bracket
                        if (chain.get(cell) > 0) {
                            // если текущая ячейка не равно 0, вернуться к началу соответствующего цикла
                            int bracket = -1;
                            while (bracket < 0) {
                                operationIndex--;
                                operation = operationList.get(operationIndex);
                                if (operation.getCommand() == Operation.Command.CYCLE) {
                                    bracket += operation.getWeight();
                                }
                            }
//                            operationIndex++;
                        }
                    }

                    break;

                case PRINT:
//                    System.out.print((char)((int)chain.get(cell)));
                    // вывести несколько раз, если нужно
                    int c = operation.getWeight();
                    while (c > 0) {
                        result.add(chain.get(cell));
                        c--;
                    }
                    break;

                default:
                    break;
            }
        }
    }

//    private void cycle () {
//        while (chain.get(cell) > 0) {
//            int tmpOperationIndex = operationIndex + 1;
//            Operation tmpOperation = operationList.get(tmpOperationIndex);
//
//            while (tmpOperation.getCommand() != Operation.Command.CYCLE || tmpOperation.getWeight() > 0) {
//
//                switch (tmpOperation.getCommand()) {
//                    case ADD:
//                        // прибавляем вес текущей ячейке
//                        chain.set(cell, chain.get(cell) + tmpOperation.getWeight());
//                        break;
//
//                    case STEP:
//                        makeStep(tmpOperation.getWeight());
//                        break;
//
//                    case CYCLE:
//                        cycle();
//                        break;
//
//                    default:
//                        break;
//                }
//
//                tmpOperationIndex++;
//                tmpOperation = operationList.get(tmpOperationIndex);
//            }
//        }
//
//    }

    private void makeStep (int weight) throws NegativeStringException {
        // указываем на новую ячейку (смещение)
        cell += weight;

        if (cell < 0) {
            throw new NegativeStringException();
        }

        // create new and set 0 to missed
        // задаем 0 новой ячейке и всем ячейкам до нее, если их не было

        if (cell >= chain.size()) {
            int tmpCell = chain.size();
            while (tmpCell <= cell) {
                chain.add(tmpCell, 0);
                tmpCell++;
            }
        }
    }

    private void addCellIfEmpty () {
        if (cell >= chain.size()) {
            chain.add(cell, 1);
        }
    }

    private void printChain () {
        for (int ch : chain ) {
            System.out.println(ch);
        }
    }

    private void printOperations () {
        for (Operation operation : operationList ) {
            System.out.println(operation.getCommand() + "." + operation.getWeight());
        }
    }

    private void print () {
        for (int ch : result) {
            System.out.print((char)ch);
        }
    }
}
