package com.zurmansor.brainfuck.exception;

// ошибки логических скобок
public class BracketException extends BrainFuckException{
    public BracketException() {
        super("some brackets has no pair");
    }

    public BracketException(String message) {
        super(message);
    }
}
