package collection.tree.node;

/**
 * Created by garciparedes on 27/11/15.
 */

import collection.tree.ExpressionTree;

import java.math.BigInteger;

/**
 * Class that represents literal variables in the tree.
 */
public class NodeVar extends NodeExpression {

    private static final String ERROR_UNDEFINED_VAR = "Variable no definida: ";


    String varName;


    /**
     * Initialize instance of class.
     * 
     * @param value name of variable
     */
    public NodeVar(String value){
        super(null, null, null);
        this.varName = value.toUpperCase();
    }


    /**
     * Evaluate node value.
     * 
     * Evalutate node expression giving back the proper value.
     * 
     * @return variable value.
     * @throws IllegalArgumentException when variable does not exist in local storage {@link ExpressionTree#vars}.
     */
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


    /**
     * @return {@link String} expression of node.
     */
    @Override
    public String toString() {
        if (getVar(varName) == null){
            return varName;
        }

        return getVar(varName).toString();
    }
}
