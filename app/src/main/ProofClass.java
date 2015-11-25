package main;

import collection.tree.ExpresionTree;
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
        String expresion = "34 25 + 3 2 234 ^ - *";
        ExpresionTree expresionTree = new ExpressionPostFix(expresion);
        ExpresionTree.putVar("abeto", expresionTree);

        System.out.println(expresionTree.infix());
        System.out.println(expresionTree.operate());



        String expresion2 = "abeto 34 25 + 3 2 234 ^ - * -";
        ExpresionTree expresionTree2 = new ExpressionPostFix(expresion2);

        System.out.println(expresionTree2.infix());

        try {
            System.out.println(expresionTree2.operate());
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }



        String expresion3 = "casa";
        ExpresionTree expresionTree3 = new ExpressionPostFix(expresion3);

        System.out.println(expresionTree3.infix());

        try {
            System.out.println(expresionTree3.operate());
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
