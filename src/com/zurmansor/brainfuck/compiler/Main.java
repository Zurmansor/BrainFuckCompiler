package com.zurmansor.brainfuck.compiler;

import com.zurmansor.brainfuck.exception.BrainFuckException;
import com.zurmansor.brainfuck.interpreter.Interpreter;

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
    static final String HELLO = "+++++\n+++++ [->++ +++++ +++<] >++++ .---. +++++ ++..+ ++.<";

    static final String CODE_2 = "+++.>>>.+[+-<<--]]";
    static final String CODE_3 = "+++.>++.>++++><<---";
    static final String CODE_4 = "+++>++[>+++<-]>>+++++++";
    static final String CODE_5 = "++>++[>+++[>+<-]<-]>>>++++";
    static final String CODE_6 = "++++++>+++[<++>-]";
    static final String CODE_7 = "+++++ ++++[ ->+++ +++++ +<]>+ +++++ +++++ +++++ ...+. ..+.+ +.++. <+++[->+++ <]>++ .++.< +++[- >---< ]>--- .<+++ ++++[ ->--- ----< ]>--- -.++.<++++ [->-- --<]> ----- .<+++ +[->+ +++<] >++++ +++.. <";
    static final String CODE_8 = ">+>+>+>+>++<[>[<+++>->>>>>>+>+>+>+>++<[>[<+++>->>>>>>+>+>+>+>++<[>[<+++>->>>>>>+>+>+>+>++<[>[<+++>->>>>>+++[->+++++<]>[-]<<<<<<]<<]>[-]<<<<<]<<]>[-]<<<<<]<<]>[-]<<<<<]<<]>.";
    static final String CODE_9 = "[-]j>[-]<>+++++++++[<+++++++++>-]<++++++++.>+++++[<+++++>-]<---.>++[<++>-]<++.---.>+++++++++[<--------->-]<-.>+++++++++[<+++++++++>-]<+++.>++++[<---->-]<+.>++++[<++++>-]<+++.----.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<++++++++.---.>++++[<++++>-]<---.>++++[<---->-]<+++.>+++++++[<------->-]<------...";
    static final String CODE_10 = "++>+<<<<<+";

    static final String CODE_11 = "++[-]+[";

    static final String LONG = "[-]>[-]<>+++++++++[<+++++++++>-]<++++++.>++++[<++++>-]<+.---.>+++[<+++>-]<.>+++++++++[<--------->-]<+++.>++++++[<++++++>-]<+++++.>++++++[<------>-]<-----.>++++++++[<++++++++>-]<++++.>++[<++>-]<+.>+++[<+++>-]<+.>++++[<---->-]<.>+++[<+++>-]<+++.>+++[<+++>-]<--.>++++[<---->-]<-.>++++[<++++>-]<---.>++++[<---->-]<+++.-.>++++++++[<-------->-]<----.>++++++[<++++++>-]<--.>+++++++[<+++++++>-]<-.>++++[<---->-]<-.>+++[<+++>-]<-.>++[<++>-]<+.>+++[<--->-]<+.>++++[<++++>-]<-.>++++[<---->-]<--.>+++[<+++>-]<-.>+++++++++[<--------->-]<++++++.>+++++++++[<+++++++++>-]<++++++++.>++++[<---->-]<----.>++++[<++++>-]<--.+.>++++[<---->-]<+.>++++[<++++>-]<---.>++++[<---->-]<++.---.>+++++[<+++++>-]<-.>+++++++++[<--------->-]<--------.>++++++++[<++++++++>-]<+.>++[<++>-]<+.>++++[<++++>-]<--.>++++[<---->-]<+.>++++[<++++>-]<---.----.+..-.>++++++++[<-------->-]<--.>+++[<--->-]<---.>++++++[<++++++>-]<+++++.>++++++[<------>-]<-----.>+++++++++[<+++++++++>-]<--------.++++..>+++[<--->-]<+.-.>++[<++>-]<+.>+++[<--->-]<+.>++++[<++++>-]<+++.>++++[<---->-]<+.>+++[<+++>-]<--.>++++[<++++>-]<---.>+++++++++[<--------->-]<--------.>++++++++[<++++++++>-]<++++++.-.>+++[<+++>-]<--..>+++++++++[<--------->-]<+++++.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++++++++[<--------->-]<+++.>+++++++++[<+++++++++>-]<-----.+++.>+++[<+++>-]<--.>++++[<---->-]<-.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<++.>+++[<+++>-]<++.>+++[<--->-]<---.>++++++++[<-------->-]<--------.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++[<----->-]<+.>+++++++[<------->-]<----.>+++++++++[<+++++++++>-]<-----.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<++.++.>++++[<---->-]<---.>++++[<++++>-]<++.>+++[<--->-]<+.>+++[<--->-]<++.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<++.>+++[<+++>-]<+.>+++[<--->-]<++.>+++[<+++>-]<.>+++[<--->-]<-.>++++++++[<-------->-]<----.>+++++++++[<+++++++++>-]<--.>+++[<--->-]<.>++++++++[<-------->-]<------.>+++++++++[<+++++++++>-]<++.>+++[<--->-]<-.++++.+++.----.---.>++[<-->-]<--.>++[<++>-]<++.>+++[<+++>-]<++.>++[<++>-]<+.>+++++++++[<--------->-]<--------.>++++++++[<++++++++>-]<+.>++++[<++++>-]<---.>+++[<--->-]<-.>++++++++[<-------->-]<----.>++++++++[<++++++++>-]<++++++.+++.----.>+++[<+++>-]<.>+++[<--->-]<-.>++[<++>-]<+.>+++[<+++>-]<+.>+++[<--->-]<--.>++[<++>-]<++.>+++[<--->-]<.>++++[<++++>-]<--..>++++++++[<-------->-]<-----.>++++[<---->-]<++.>++++++[<++++++>-]<+++++.>+++++++[<+++++++>-]<------.>+++++[<----->-]<+.>+++++++[<------->-]<----.>+++++++++[<+++++++++>-]<-----.>+++++++++[<--------->-]<--.>++++++++[<++++++++>-]<+.>++++++++[<-------->-]<-.>+++++++++[<+++++++++>-]<+++++.>++++[<---->-]<-.>++++[<++++>-]<---.>+++[<--->-]<.>+++[<+++>-]<++.>++++[<---->-]<---.+.>+++[<+++>-]<+.>+++[<--->-]<++.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<+++.>++[<++>-]<+.>+++[<--->-]<++.>+++[<+++>-]<++..>+++[<--->-]<++.>+++[<+++>-]<.>+++[<--->-]<++.--.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>+++++++++[<--------->-]<++.>++++++++[<++++++++>-]<+++++++.--.>++++[<++++>-]<-.>+++++++++[<--------->-]<---.>++++++[<++++++>-]<--.>+++++++[<+++++++>-]<-.>++++[<---->-]<-.>+++[<+++>-]<-.>++[<++>-]<+.>+++[<--->-]<+.>++++[<++++>-]<-.>++++[<---->-]<--.>+++[<+++>-]<-.>+++++++++[<--------->-]<++++++.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>+++++++++[<--------->-]<++.>++++++++[<++++++++>-]<++++.>+++[<+++>-]<++.>+++++++++[<--------->-]<++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<---.>+++[<+++>-]<++.>++[<-->-]<-.>+++[<--->-]<---.+.>++[<++>-]<+.>+++[<--->-]<++.>++++++++[<-------->-]<-------.>+++++++++[<+++++++++>-]<++++.--.>++++[<---->-]<++.+.>++++[<++++>-]<-.>+++[<--->-]<.>++++++++[<-------->-]<.>+++[<--->-]<---.>++++++++[<++++++++>-]<++.>++++[<++++>-]<+++.-.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--.>+++[<--->-]<---..>++++[<++++>-]<++.---.>++++[<---->-]<+++.-.>++++++++[<-------->-]<----.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>+++++++++[<--------->-]<++.>+++++++++[<+++++++++>-]<----.>+++[<--->-]<+.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.>+++[<--->-]<++.>++++[<++++>-]<+++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<----.----.--.+.>+++[<+++>-]<+++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<++.+++.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<+++++.>++++[<---->-]<-.>++++[<++++>-]<---.>+++[<+++>-]<--.>+++++++++[<--------->-]<--------.>++++++++[<++++++++>-]<+++++++.>+++[<+++>-]<-..>+++[<--->-]<--.>++++++++[<-------->-]<----.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<++++++++.+.>++[<-->-]<-.>++[<++>-]<+.>++[<++>-]<+.>+++[<--->-]<++.>++++++++[<-------->-]<-------.>+++++++++[<+++++++++>-]<+++.>++++[<---->-]<+.>++++[<++++>-]<+++.----.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<++.>++++[<---->-]<.>++[<++>-]<++..>+++++++++[<--------->-]<++++++++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+.++.>++++++++[<-------->-]<------...>++++[<---->-]<++.>+++++++[<+++++++>-]<++++++.>++++[<++++>-]<+.>+++[<--->-]<++.>++++[<++++>-]<+++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<++++++.>+++[<+++>-]<.---..+++.>+++[<+++>-]<-.----.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<+.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<----.>+++[<+++>-]<+++.>+++++++++[<--------->-]<--------.>++++++++[<++++++++>-]<++++++.+++.>+++[<+++>-]<.+.+.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+++..>++++[<---->-]<+.>+++[<+++>-]<-.+++.++++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<+.>++++++++[<-------->-]<-.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-..---.>+++++++++[<--------->-]<+++++.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<+.+.>++[<-->-]<--.>++[<++>-]<+.>++++++++[<-------->-]<--------.>++++++++[<++++++++>-]<+++++.>+++[<+++>-]<.>+++[<--->-]<--.>+++[<+++>-]<+++.>+++[<--->-]<--.+.>++++[<++++>-]<--.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<+++.>++++[<---->-]<+.>++++[<++++>-]<+++.----.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++++++++[<--------->-]<+++.>++++++[<++++++>-]<--.>+++++++[<+++++++>-]<-.>++++[<---->-]<-.>+++[<+++>-]<-.>++[<++>-]<+.>+++[<--->-]<+.>++++[<++++>-]<-.>++++[<---->-]<--.>+++[<+++>-]<-.>+++++++++[<--------->-]<++++++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<---.>+++[<--->-]<-.>++++++++[<-------->-]<----.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>+++[<+++>-]<.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<++++++++.+.>++[<-->-]<-.+.>++++[<++++>-]<--.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<---.>+++++++++[<--------->-]<+++.>++++++[<++++++>-]<---.>++++[<++++>-]<++.>++++[<---->-]<.>++[<++>-]<++..>++++++[<------>-]<-----.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+.++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.++++.>+++[<--->-]<---.>++[<++>-]<++.--.>+++++++[<------->-]<------.>++++[<---->-]<++..>++++++[<++++++>-]<---.>++++++[<------>-]<+++.>++++++++[<++++++++>-]<++.>+++[<+++>-]<--.--.>++++++++[<-------->-]<-------.>+++++++++[<+++++++++>-]<---.+.>+++[<--->-]<--.>++++++++[<-------->-]<----.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>+++++++++[<--------->-]<++.>++++++[<++++++>-]<++++++.>++++++[<++++++>-]<+.-.>++++[<---->-]<+++.>++++[<++++>-]<+++.>+++[<--->-]<---.>+++[<--->-]<++.>++++[<++++>-]<---.>+++++++++[<--------->-]<+++.>++++++[<++++++>-]<++.>++++++[<++++++>-]<+++++.+++.>++++[<---->-]<++.>++++++++[<-------->-]<----.>++++++++[<++++++++>-]<++++++.>+++[<+++>-]<.+++.>+++++++++[<--------->-]<-.>++++++++[<++++++++>-]<++++++++.+.>+++[<+++>-]<+.>+++++++++[<--------->-]<--.>++++++[<++++++>-]<---.>++++[<++++>-]<++.>++++[<---->-]<.>++[<++>-]<++..>++++++[<------>-]<-----.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+.++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<+++++++.--.>+++[<+++>-]<.>+++[<--->-]<.>++++[<++++>-]<---.>++++[<---->-]<-.>++++[<++++>-]<+++.>++[<-->-]<-.+++.>+++++++++[<--------->-]<-.>++++++++[<++++++++>-]<+++.>+++[<+++>-]<.>+++[<--->-]<--.>++++[<++++>-]<++..>++++++++[<-------->-]<-------.>+++[<--->-]<---.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<+.+.>++[<-->-]<--.>++[<++>-]<+.>++++++++[<-------->-]<--------.>++++++[<++++++>-]<+++++.>++++++[<------>-]<-----.>+++++++++[<+++++++++>-]<++++.--.>++++[<---->-]<++.-.>++++++++[<-------->-]<----.>++++++++[<++++++++>-]<+.>++++[<++++>-]<++.>+++++++++[<--------->-]<--.>++++++++[<++++++++>-]<+.>++++++++[<-------->-]<-.>++++++++[<++++++++>-]<++.-.>++++[<++++>-]<++.>++++[<---->-]<++.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<++++++.>+++[<+++>-]<.+++.>+++++++++[<--------->-]<-.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<++.>++++[<---->-]<++.--.>+++[<+++>-]<+++.-.>+++[<--->-]<-.>++++++++[<-------->-]<----.>++++++++[<++++++++>-]<++++++++.>+++[<--->-]<++.>+++[<+++>-]<++.>++[<-->-]<--.>++++++++[<-------->-]<------.>+++++++++[<+++++++++>-]<--.>+++[<--->-]<.>++++++++[<-------->-]<------.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.+.>+++[<+++>-]<+.>+++++++++[<--------->-]<--.>++++++++[<++++++++>-]<+.>++++[<++++>-]<-..>++++++++[<-------->-]<--.>++++[<---->-]<++.>+++++++[<+++++++>-]<++++++.>++++[<++++>-]<+.---.>+++[<+++>-]<.>+++++++++[<--------->-]<+++.>++++++[<++++++>-]<+++++.>++++++[<------>-]<-----.>++++++++[<++++++++>-]<+++++++.--.>++++[<++++>-]<-.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<+.>++++[<++++>-]<+.---.>++[<++>-]<++.>+++[<--->-]<++.>+++[<--->-]<-.>++++++++[<-------->-]<----.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>+++++++++[<--------->-]<++.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>++++++++[<-------->-]<--------.>+++[<--->-]<---.>++++++[<++++++>-]<+++++.>++++[<++++>-]<+++.>+++++++[<------->-]<----.>++++++++[<++++++++>-]<+++++..>+++++++++[<--------->-]<+++++.>+++++++++[<+++++++++>-]<-.>++++[<---->-]<+.++.>+++[<+++>-]<-.>+++++++++[<--------->-]<++++++.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.+.>+++[<+++>-]<+.>+++++++++[<--------->-]<--.>++++++++[<++++++++>-]<+.>+++[<+++>-]<++..>+++++++++[<--------->-]<+++++.>+++++++++[<+++++++++>-]<++++.>++[<-->-]<-.>+++++++++[<--------->-]<+.>++++++++[<++++++++>-]<++++++.>+++[<+++>-]<.+++.>+++++++++[<--------->-]<-.>++++++++[<++++++++>-]<++++.>+++[<+++>-]<++.>+++[<+++>-]<-.>+++[<--->-]<.--.+++.>++++[<---->-]<++.+++.>++++++++[<-------->-]<----.>++++++++[<++++++++>-]<+.>++++[<++++>-]<++.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<+++.>+++[<+++>-]<.>+++[<--->-]<--.>++++[<++++>-]<++..>+++++++++[<--------->-]<--.>++++++[<++++++>-]<+++++.>++++++[<------>-]<-----.>+++++++++[<+++++++++>-]<++++++.>++[<-->-]<-.---.>++[<++>-]<+.>++++[<---->-]<+.>++++++++[<-------->-]<-----.>+++[<+++>-]<-.>++++++++[<++++++++>-]<------.++++.>+++[<--->-]<++.>+++++[<+++++>-]<----.>++++[<---->-]<+.>++++[<++++>-]<+++.----.>++++++++[<-------->-]<------.>+++++++[<+++++++>-]<++++.>+++[<+++>-]<.>+++[<--->-]<--.>++++[<++++>-]<++..>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<++.>+++[<--->-]<+.>+++[<+++>-]<-.>++++++++[<-------->-]<-------.>+++[<--->-]<.>++++++++[<++++++++>-]<+++.>+++[<+++>-]<+++.-.>++[<++>-]<++.>++++[<---->-]<---.>+++[<+++>-]<-.>++[<++>-]<+.>++[<++>-]<+.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<++.----.--.>+++[<--->-]<+.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<++++++.>++++[<++++>-]<-.>+++[<--->-]<++.>+++[<--->-]<--.>++++[<++++>-]<+.>+++[<--->-]<--.>++[<++>-]<++.-.>++[<++>-]<+.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<+.+.>++[<-->-]<--.>++[<++>-]<+.>++++++++[<-------->-]<--------.>+++++++++[<+++++++++>-]<----.>+++[<--->-]<---.>+++++[<+++++>-]<-.>+++++++++[<--------->-]<--------.>++++++++[<++++++++>-]<++.+++.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<--.>+++[<--->-]<.>++++++++[<-------->-]<------.>+++++++++[<+++++++++>-]<++++.--.>++++[<---->-]<++.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>+++++++++[<--------->-]<++.>+++++++++[<+++++++++>-]<++.----.--.>+++[<--->-]<+.>+++[<+++>-]<+.-.>+++[<--->-]<.>+++++++[<------->-]<------.>++++[<---->-]<++..>++++++[<++++++>-]<++++++.>+++++++[<+++++++>-]<------.--.+.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<++.+.>+++[<--->-]<--.>++[<-->-]<--.>+++[<+++>-]<-.>+++++++++[<--------->-]<++++++.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++++++++[<--------->-]<+++.>+++++++++[<+++++++++>-]<++.----.--.>+++[<--->-]<+.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<+++.>++++[<---->-]<+.>++++[<++++>-]<+++.----.>++++++++[<-------->-]<--------.>+++[<--->-]<---.>+++++++++[<+++++++++>-]<--.+.++++.>+++[<--->-]<--.>++[<++>-]<++.-.>++++[<---->-]<+++.>+++[<+++>-]<++..>++++[<++++>-]<---.>+++++++++[<--------->-]<--------.>+++++++++[<+++++++++>-]<++.---.>+++[<--->-]<--.--.>++[<++>-]<++.---.>++++[<++++>-]<+++.>+++++++++[<--------->-]<--------.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<++++.---.>++[<-->-]<--.>+++++++++[<--------->-]<+++++.>+++++++++[<+++++++++>-]<--.>+++[<--->-]<.>++++++++[<-------->-]<------.>++++++++[<++++++++>-]<+.>++++[<++++>-]<---.>+++++++++[<--------->-]<+++.>+++++++++[<+++++++++>-]<--------.++++.>+++[<--->-]<---.>++[<++>-]<++.--.>++++++++[<-------->-]<+++++++.>+++[<--->-]<---.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>+++[<+++>-]<.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<++++++++.+.>+++[<+++>-]<++.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<----.>+++++++[<------->-]<----.>++++++[<++++++>-]<----.>++++++[<++++++>-]<++++.>+++++++++[<--------->-]<+++.>++++[<++++>-]<---.>++++[<---->-]<++.>++++++[<++++++>-]<---.>++++++[<++++++>-]<--.>++++[<++++>-]<+.>+++[<--->-]<--.>++++[<++++>-]<---.>+++++[<----->-]<++++.>++++[<++++>-]<+++.>+++[<--->-]<--.>++[<++>-]<+.>+++[<--->-]<++.>++++++++[<-------->-]<-------.>++++++++[<++++++++>-]<++++.+.---.>++++[<++++>-]<+++.>++++[<---->-]<++.>++++++++[<-------->-]<-------.>+++++++++[<+++++++++>-]<----.++.>+++[<--->-]<--.+.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<++.>+++[<--->-]<--.>+++[<+++>-]<--.>+++[<+++>-]<-.----.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<--.+++.>+++[<--->-]<.--.++.>++[<++>-]<+.>++++[<---->-]<+++.>+++[<+++>-]<++.>+++++++++[<--------->-]<+++++.>+++++++++[<+++++++++>-]<-----.>+++[<--->-]<++.>++++[<++++>-]<-..>++++[<---->-]<+.>++++[<++++>-]<---.+.>+++++++++[<--------->-]<--.>+++[<+++>-]<-.>++++++++[<++++++++>-]<------.>++++[<++++>-]<+++.-.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<----.>+++[<--->-]<---.>+++[<+++>-]<-.>++[<++>-]<+.>++[<++>-]<++.>++++[<---->-]<---.>+++[<+++>-]<-.>++[<++>-]<+.>++[<++>-]<+.>+++++++++[<--------->-]<--.>++++++[<++++++>-]<--.++++.>++++++[<------>-]<--.>+++++++++[<+++++++++>-]<++.>++++[<---->-]<--.>++[<++>-]<+.-.>++++[<++++>-]<-.>++[<++>-]<+.>+++++++++[<--------->-]<+.>+++[<--->-]<.>++++++++[<++++++++>-]<+.>++++[<++++>-]<++.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<--.>+++[<+++>-]<--..>+++++++++[<--------->-]<+++++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<++.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<++.----.--.>+++[<--->-]<+.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++[<--->-]<+.>+++[<+++>-]<.+++.>++[<-->-]<-.>+++[<--->-]<---.>++++[<++++>-]<+++.>+++[<--->-]<--.>++[<++>-]<++.-.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<+.+.>++++[<++++>-]<---.>++[<++>-]<++.-.>+++++++++[<--------->-]<---.>++++++++[<++++++++>-]<++++++++.>+++[<+++>-]<--.>+++[<+++>-]<-.>+++++++++[<--------->-]<------.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++[<----->-]<+.>+++++++[<------->-]<----.>+++++++++[<+++++++++>-]<-----.>+++++++++[<--------->-]<--.>++++++++[<++++++++>-]<++++.>+++[<+++>-]<++.>++[<-->-]<--.>++[<++>-]<+.>+++[<--->-]<++.>++++++++[<-------->-]<-------.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>++++++++[<-------->-]<------.>++++[<---->-]<++.>+++++++[<+++++++>-]<++++++.>++++[<++++>-]<+.---.>+++[<+++>-]<.>+++++++++[<--------->-]<+++.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<++++.+.---.>++++[<++++>-]<+++.>++++[<---->-]<++.>++++++++[<-------->-]<-------.>++++++++[<++++++++>-]<++++++.>++++[<++++>-]<-.>+++[<--->-]<++.>+++[<--->-]<--.>++++[<++++>-]<+.>+++[<--->-]<--.>++[<++>-]<++.-.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<+++++.>+++[<+++>-]<.>+++[<--->-]<--.>+++[<+++>-]<+++.>++[<++>-]<++.>+++[<--->-]<++.>++[<++>-]<++.>++++[<---->-]<+.>++++[<++++>-]<---.+.>+++++++++[<--------->-]<--.>++++++++[<++++++++>-]<+.>++++++++[<-------->-]<-.>++++++[<++++++>-]<--.++++.>++++++[<------>-]<--.>+++++++++[<+++++++++>-]<--.+.>+++[<--->-]<--.>++++[<++++>-]<---.>++++[<---->-]<-.>++++[<++++>-]<+++.>++[<-->-]<-.+++.>+++++++++[<--------->-]<-.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.>+++++++++[<--------->-]<+++.>+++++++++[<+++++++++>-]<++++++++.>+++[<--->-]<-.>++[<++>-]<++.---.>+++++++++[<--------->-]<-.>+++++++++[<+++++++++>-]<--------.>++[<++>-]<+.++.>++[<++>-]<+.-.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<+.>++++[<---->-]<+++.>+++[<+++>-]<++.----.>+++[<--->-]<--.++.++.>++++[<++++>-]<--.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<++.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<++.>+++[<+++>-]<++.>+++[<--->-]<---.>++++++++[<-------->-]<--------.>++++++++[<++++++++>-]<+.>++++[<++++>-]<---.>+++++++++[<--------->-]<+++.>++++++++[<++++++++>-]<+.>++++[<++++>-]<++.+.>++++[<---->-]<+.>++++[<++++>-]<---.>+++[<--->-]<.>++++[<++++>-]<-.>+++++++++[<--------->-]<+++++.>+++[<--->-]<---.>++++++++[<++++++++>-]<++.>++++[<++++>-]<+++.-.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<--.>++[<++>-]<++.-.----.>++[<++>-]<+.-.>+++++++++[<--------->-]<---.>+++++++++[<+++++++++>-]<++++++.>++++[<---->-]<++.+++..>+++++++++[<--------->-]<+++++.>++++++++[<++++++++>-]<++.+++.>++++++++[<-------->-]<-----.>++++++++[<++++++++>-]<++++++.>+++[<+++>-]<.+++.>+++++++++[<--------->-]<-.>+++++++++[<+++++++++>-]<+++.>+++[<--->-]<---.---.>++++++++[<-------->-]<-----.>+++++++++[<+++++++++>-]<--.+++.>+++[<--->-]<.--.++.>++[<++>-]<+.>++++[<---->-]<+++.>+++[<+++>-]<++.>+++++++++[<--------->-]<+++++.>++++++++[<++++++++>-]<+++.>++[<++>-]<+.>+++[<--->-]<++.>++++[<++++>-]<+.>++++[<---->-]<-.++.>++++[<++++>-]<+.>++++[<---->-]<+.>++++[<++++>-]<---.>++++++++[<-------->-]<----.";

    static final String INT_1 = "++>+.";
    static final String INT_2 = "+++>++[>+++<-]>+++++++";


//    static final String CODE_9 = "erfer";


    public static void main(String[] args) {
        Compiler compiler = new Compiler(CODE_9);
        try {
            compiler.start();
        } catch (BrainFuckException e) {
            e.printStackTrace();
        }

        System.out.println("");
        Interpreter interpreter = new Interpreter(CODE_9);
        try {
            interpreter.start();
        } catch (BrainFuckException e) {
            e.printStackTrace();
        }
    }
}