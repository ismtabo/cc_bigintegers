package main;

import collection.tree.ExpresionTree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ProofClass {

    public static void main(String[] args) {
        ExpresionTree expresionTree = new ExpresionTree("21 7 % ", ExpresionTree.POSTFIX);
        System.out.println(expresionTree.infix());
        System.out.println(expresionTree.operate());
        System.exit(0);
    }
}
