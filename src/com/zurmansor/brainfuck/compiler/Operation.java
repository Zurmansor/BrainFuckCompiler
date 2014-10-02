package com.zurmansor.brainfuck.compiler;

public class Operation {
    public enum Command{
        STEP,
        ADD,
        CYCLE,
        PRINT
    }

    public Command command = null;
    public int weight;

    public Operation() {
        this.weight = 0;
    }

    public Operation(Command command) {
        this();
        this.command = command;
    }

    public Operation(Command command, int weight) {
        this();
        this.command = command;
        this.weight = weight;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public int getWeight() {
        return weight;
    }

    public int getWeightAbs() {
        return Math.abs(weight);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void gainWeight () {
        gainWeight(1);
    }

    public void gainWeight (int gain) {
        this.weight += gain;
    }

    public void loseWeight () {
        gainWeight(-1);
    }

    public void loseWeight (int lose) {
        this.weight -= lose;
    }
}
