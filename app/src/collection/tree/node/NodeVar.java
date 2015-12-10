package collection.tree.node;

/**
 * Created by garciparedes on 27/11/15.
 */

import collection.tree.ExpressionTree;

import java.math.BigInteger;

/**
 * Class that represents numbers in the tree.
 */
public class NodeVar extends NodeExpression {

    private static final String ERROR_UNDEFINED_VAR = "Variable no definida: ";


    String varName;



    public NodeVar(String value){
        super(null, null, null);
        this.varName = value.toUpperCase();
    }



    @Override
    public BigInteger operate() throws IllegalArgumentException {
        if (getVar(varName) == null){
            throw new IllegalArgumentException (ERROR_UNDEFINED_VAR + varName);
        }
        return getVar(varName).operate();
    }



    private ExpressionTree getVar(String varName) {
        return ExpressionTree.getVar(varName);
    }



    @Override
    public String toString() {
        if (getVar(varName) == null){
            return varName;
        }

        return getVar(varName).toString();
    }
}
