package main;


import collection.tree.ExpressionTree;
import collection.tree.ExpressionInfix;
import collection.tree.ExpressionPostFix;

import java.util.HashMap;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ProofClass {

    public static void main(String[] args) {


        ExpressionTree.putVar("aed", new ExpressionInfix("(34+25)*(3-(2^234))^999"));
        ExpressionTree.putVar("hola", new ExpressionInfix("aed*2"));
        //System.out.println(ExpressionInfix.getVar("hola").operate());
        String expression [] = {
                 "3+34+234-34"                                          //0
                //, "34+45"                                               //1
                //, "aed+45"
                //, "(aed*4)+45"
                //, "(aed*4)+(45^54-hola)"
                //, "((aed*4)*(45^54-hola)%(85^7))/34542+(AED*5)"         //5
                //, "(a+b)+(c*d)+((c+f)+453543*344)"
                //, "(34+25)*(3-(2^234))"
                //, "34 25 + 3 2 234 ^ - *"
                //, "3+34+234-34"
                //, "(3+34)+(234)-(34)"                                   //10
                //, "(3+2)-(4+2)"
                //, "(3+(2-4)+2)"
                //, "-1+(3+(2-4)+2)+1"
                //, "((3+2)-4+2)"
                //, "(3+2-(4+2))"                                         //15
                , "(34 + 25) * (3 - (2 ^234)) % 2^32 - 3 % 55"
                //, "-(34234532523-(3*3)/(4*2))"

        };

        //System.out.println(new ExpressionInfix(expression[5]).operate());

        for (String anExpression : expression) {
            System.out.println(anExpression);
            System.out.println(new ExpressionInfix(anExpression).operate());
        }
        System.exit(0);
    }
}
