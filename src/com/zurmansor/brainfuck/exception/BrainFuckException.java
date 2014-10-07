package com.zurmansor.brainfuck.exception;

// общий класс исключений нашего компилятора
public class BrainFuckException  extends Exception{
    public BrainFuckException() {
    }

    public BrainFuckException(String message) {
        super(message);
    }
}
