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
        //ExpresionTree expresionTree = new ExpresionTree("34 25 + 3 2 234 ^ - *", ExpresionTree.POSTFIX);

        HashMap<Character, ExpresionTree> vars = new HashMap<>();

        String expresion = "34 25 + 3 2 234 ^ - *";
        char var = 'A';
        ExpresionTree expresionTree = new ExpressionPostFix(expresion, vars);
        vars.put(var, expresionTree);

        System.out.println(expresionTree.infix());
        System.out.println(expresionTree.operate());

        String expresion2 = "A 34 25 + 3 2 234 ^ - * -";
        ExpresionTree expresionTree2 = new ExpressionPostFix(expresion2, vars);

        System.out.println(expresionTree2.infix());
        System.out.println(expresionTree2.operate());

        System.exit(0);
    }
}
