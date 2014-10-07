package com.zurmansor.brainfuck.exception;

// ошибки выхода за пределы кода
public class NegativeStringException extends BrainFuckException{
    public NegativeStringException() {
        super("negative position in the string");
    }

    public NegativeStringException(String message) {
        super(message);
    }
}
