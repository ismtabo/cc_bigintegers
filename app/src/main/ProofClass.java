package main;

import collection.tree.ExpresionTree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ProofClass {

    public static void main(String[] args) {
        ExpresionTree expresionTree = new ExpresionTree("(21 - 23 ) % 4 ", ExpresionTree.INFIX);
        System.out.println(expresionTree.infix());
        System.out.println(expresionTree.operate());
        System.exit(0);
    }
}
