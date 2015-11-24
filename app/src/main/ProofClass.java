package main;

import collection.tree.ExpresionTree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ProofClass {

    public static void main(String[] args) {
        //Operacion: (34 + 25) * (3 - (2 ^234))
        ExpresionTree expresionTree = new ExpresionTree("34 25 + 3 2 234 ^ - *", ExpresionTree.POSTFIX);
        System.out.println(expresionTree.infix());
        System.out.println(expresionTree.operate());
        System.exit(0);
    }
}
