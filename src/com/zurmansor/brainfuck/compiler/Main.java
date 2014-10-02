package com.zurmansor.brainfuck.compiler;

import java.util.ArrayList;

public class Main {

    static final String CODE_1 = "+++++++++++++++++++++++++++++++++++++++++++++"
            + "+++++++++++++++++++++++++++.+++++++++++++++++"
            + "++++++++++++.+++++++..+++.-------------------"
            + "---------------------------------------------"
            + "---------------.+++++++++++++++++++++++++++++"
            + "++++++++++++++++++++++++++.++++++++++++++++++"
            + "++++++.+++.------.--------.------------------"
            + "---------------------------------------------"
            + "----.-----------------------.";

    static final String CODE_2 = "+++.>>.+[+-<<<--]]";


    public static void main(String[] args) {
        System.out.println(CODE_2);

        ArrayList<Integer> al = new ArrayList<Integer>();

        Compiler compiler = new Compiler(CODE_2);
        compiler.compress();


    }




}
