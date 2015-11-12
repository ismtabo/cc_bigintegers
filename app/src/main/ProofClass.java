package main;

import collection.tree.ExpresionTree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ProofClass {

    public static void main(String[] args) {
        ExpresionTree expresionTree = new ExpresionTree("12000 12345 2 + ^", ExpresionTree.POSTFIX);
        System.out.println(expresionTree.operate());
        System.exit(0);
    }
}
