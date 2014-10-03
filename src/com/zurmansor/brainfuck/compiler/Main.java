package com.zurmansor.brainfuck.compiler;

import com.zurmansor.brainfuck.exception.BrainFuckException;

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

    static final String ABC = "++++++++[>+>++>+++>++++>+++++>++++++>+++++++>++++++++>+++++++++>++++++++++>+++++++++++>++++++++++++>+++++++++++++>++++++++++++++>+++++++++++++++>++++++++++++++++<<<<<<<<<<<<<<<<-]>>>>>>>>>>>>+.-<<<<<<<<<<<<>>>>>>>>>>>>++.--<<<<<<<<<<<<>>>>>>>>>>>>+++.---<<<<<<<<<<<<.";
    static final String HELLO = "+++++ +++++ [->++ +++++ +++<] >++++ .---. +++++ ++..+ ++.<";

    static final String CODE_2 = "+++.>>>.+[+-<<--]]";
    static final String CODE_3 = "+++.>++.>++++><<---";
    static final String CODE_4 = "+++>++[>+++<-]>>+++++++";
    static final String CODE_5 = "++>++[>+++[>+<-]<-]>>>++++";
    static final String CODE_6 = "++++++>+++[<++>-]";
    static final String CODE_7 = "+++++ ++++[ ->+++ +++++ +<]>+ +++++ +++++ +++++ ...+. ..+.+ +.++. <+++[->+++ <]>++ .++.< +++[- >---< ]>--- .<+++ ++++[ ->--- ----< ]>--- -.++.<++++ [->-- --<]> ----- .<+++ +[->+ +++<] >++++ +++.. <";
    static final String CODE_8 = ">+>+>+>+>++<[>[<+++>->>>>>>+>+>+>+>++<[>[<+++>->>>>>>+>+>+>+>++<[>[<+++>->>>>>>+>+>+>+>++<[>[<+++>->>>>>+++[->+++++<]>[-]<<<<<<]<<]>[-]<<<<<]<<]>[-]<<<<<]<<]>[-]<<<<<]<<]>.";

    static final String CODE_9 = "erfer";


    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<Integer>();

        Compiler compiler = new Compiler(HELLO);
        try {
            compiler.start();
        } catch (BrainFuckException e) {
            e.printStackTrace();
        }

//        String data = "23343453";
//        String data2 = "+++-[]].";
//        String regex = "[\\D]+";
//        String regex2 = "[+-.\\[\\]]*";
//
//        String data3 = "acbg";
//        String regex3 = "[abc]*";
//
//        System.out.println(data2.matches(regex2));
            }




}
