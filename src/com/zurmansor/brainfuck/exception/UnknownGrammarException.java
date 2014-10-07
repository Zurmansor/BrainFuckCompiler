package com.zurmansor.brainfuck.exception;

// ошибки грамматики
public class UnknownGrammarException extends BrainFuckException{
    public UnknownGrammarException() {
        super("unknown grammar");
    }

    public UnknownGrammarException(String message) {
        super(message);
    }
}
