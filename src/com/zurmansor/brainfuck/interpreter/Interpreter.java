package com.zurmansor.brainfuck.interpreter;

import com.zurmansor.brainfuck.compiler.Operation;
import com.zurmansor.brainfuck.exception.BracketException;
import com.zurmansor.brainfuck.exception.BrainFuckException;
import com.zurmansor.brainfuck.exception.NegativeStringException;
import com.zurmansor.brainfuck.exception.UnknownGrammarException;

import java.util.ArrayList;

public class Interpreter {
    final char INC = '+';
    final char DEC = '-';
    final char GO = '>';
    final char BACK = '<';
    final char TWIST = '[';
    final char STAY = ']';
    final char SHOW = '.';

    private String code;
    private int cell = 0; // индекс ячейки
    private ArrayList<Integer> chain = new ArrayList<Integer>(); // массив ячеек

    public Interpreter() {

    }

    public Interpreter(String code) {
        this.code = code;
    }

    public void start () throws BrainFuckException {
        chain.add(0);
        for (int i = 0; i < code.length(); i++) {
            switch (code.charAt(i)) {
                case INC:
                    chain.set(cell, chain.get(cell) + 1);
                    break;

                case DEC:
                    chain.set(cell, chain.get(cell) - 1);
                    break;

                case GO:
                    cell++;
                    // если индекс указывает на новую ячейку, то сразу создаем ее и записываем 0
                    if (cell >= chain.size()) {
                        chain.add(0);
                    }
                    break;

                case BACK:
                    cell--;
                    if (cell < 0) {
                        throw new NegativeStringException();
                    }
                    break;

                case TWIST:
                    if (chain.get(cell) <= 0) {
                        // переходим к операции, которая идет после закрывающей скобки соответствующего массива
                        int bracket = 1;
                        while (bracket != 0) {
                            i++;
                            if (code.charAt(i) == TWIST) {
                                bracket++;
                            }
                            if (code.charAt(i) == STAY) {
                                bracket--;
                            }
                        }
                    }
                    break;

                case STAY:
                    if (chain.get(cell) > 0) {
                        // назад к началу соответствующего массива, на новый шаг
                        int bracket = -1;
                        while (bracket != 0) {
                            i--;
                            if (i < 0) {
                                throw new BracketException();
                            }
                            if (code.charAt(i) == TWIST) {
                                bracket++;
                            }
                            if (code.charAt(i) == STAY) {
                                bracket--;
                            }
                        }
                    }
                    break;

                case SHOW:
                    System.out.print((char)((int)chain.get(cell)));
                    break;

                case ' ':
                case '\n':
                    // пробел и перенос - ничего не делать
                    break;

                default:
                    // если какой-то другой символ, то исключение
                    throw new UnknownGrammarException();

            }
        }
    }
}
