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
        String expressionA = "3+34+234fsas342ffgs";
        String expressionB = "34+45";
        String expressionC = "aed+45";
        String expressionD = "(aed*4)+45";
        String expressionE = "(aed*4)+(45^54-hola)";
        String expressionF = "((aed*4)*(45^54-hola)%(85^7))/34542+(AED*5)";

        ExpresionTree expresionTree = new ExpressionInfix(expressionF);
        System.exit(0);
    }
}
