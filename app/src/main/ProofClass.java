package main;

import collection.tree.ExpresionTree;
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
        //Operacion: (34 + 25) * (3 - (2 ^234))
        /*
        String expresion = "34 25 + 3 2 234 ^ - *";
        ExpresionTree expresionTree = new ExpressionPostFix(expresion);
        ExpresionTree.putVar("abeto", expresionTree);

        System.out.println(expresionTree);
        System.out.println(expresionTree.operate());



        String expresion2 = "abeto 34 25 + 3 2 234 ^ - * -";
        ExpresionTree expresionTree2 = new ExpressionPostFix(expresion2);

        System.out.println(expresionTree2);

        try {
            System.out.println(expresionTree2.operate());
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }



        String expresion3 = "casa";
        ExpresionTree expresionTree3 = new ExpressionPostFix(expresion3);

        System.out.println(expresionTree3);

        try {
            System.out.println(expresionTree3.operate());
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
        */
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

        };

        //System.out.println(new ExpressionInfix(expression[9]).operate());

        //System.out.println(new ExpressionInfix(expression[10]).operate());
        //System.out.println(new ExpressionInfix(expression[11]).operate());
        //System.out.println(new ExpressionInfix(expression[12]).operate());
        System.out.println(new ExpressionInfix(expression[13]).operate());
        //System.out.println(new ExpressionInfix(expression[14]).operate());
        //System.out.println(new ExpressionInfix(expression[15]).operate());

        //System.out.println(new ExpressionInfix(expression[7]).operate());

        //System.out.println(new ExpressionPostFix(expression[8]).operate());
        System.exit(0);
    }
}
