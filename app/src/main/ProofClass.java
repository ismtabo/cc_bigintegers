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

    /**
     * ProofClass main function.
     * 
     * Proof class run local test about program software. 
     * Testing {@link collection.tree} classes are running in the proper way.
     * 
     * @param args internal system arguments
     */
    public static void main(String[] args) {


        ExpressionTree.putVar("aed", new ExpressionInfix("(34+25)*(3-(2^234))^999"));
        ExpressionTree.putVar("hola", new ExpressionInfix("aed*2"));
        //System.out.println(ExpressionInfix.getVar("hola").operate());
        String expression [] = {
                 "3+34+234-34"                                          //0
                , "34+45"                                               //1
                , "aed+45"
                , "(aed*4)+45"
                , "(aed*4)+(45^54-hola)"
                , "((aed*4)*(45^54-hola)%(85^7))/34542+(AED*5)"         //5
                , "(a+b)+(c*d)+((c+f)+453543*344)"
                , "(34+25)*(3-(2^234))"
                , "34 25 + 3 2 234 ^ - *"
                , "3+34+234-34"
                , "(3+34)+(234)-(34)"                                   //10
                , "(3+2)-(4+2)"
                , "(3+(2-4)+2)"
                , "-1+(3+(2-4)+2)+1"
                , "((3+2)-4+2)"
                , "(3+2-(4+2))"                                         //15
                , "(34 + 25) * (3 - (2 ^234)) % 2^32 - 3 % 55"
                , "74+3213-(434-4&)"
                , ")("
                , "gfssdgdsgsd"
                , "gsfsd-(434-4&)"
                 , "434/0"
                 , "2^13134"
                 ,"3475*%3424"
                 ,"3475+^3424"
                , "2^131345678934567893456789"
                , "2433^-331"
                , "-(34234532523-(3*3)/(4*2))"

        };

        String expression1 [] = {
                "1+1"
                //,"(1+2)-(2*3)"
                //,"((1+2)*3)-(2*3)"
                ,"(34 + 25) * (3 - (2 ^234)) % 2^32 - 3 % 55"
                , "((aed*4)*(45^54-hola)%(85^7))/34542+(AED*5)"

        };

        //System.out.println(new ExpressionInfix(expression[5]).operate());

        for (String anExpression : expression) {
            System.out.println(anExpression);
            try {
                System.out.println(new ExpressionInfix(anExpression).operate());
            } catch (IllegalArgumentException | IllegalStateException e){
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        System.exit(0);
    }
}
