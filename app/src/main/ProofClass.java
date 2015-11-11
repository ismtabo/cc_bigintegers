package main;

import collection.tree.ExpresionTree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ProofClass {

    public static void main(String[] args) {
        new ExpresionTree("12345+-45*453hola", ExpresionTree.POSTFIX);
        System.exit(0);
    }
}
