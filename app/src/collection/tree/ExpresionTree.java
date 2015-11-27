package collection.tree;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public abstract class ExpresionTree {



    private static HashMap<String, ExpresionTree> vars = new HashMap<>();


    private NodeExpression top;
    private String expression;



    /**
     *
     * Constructor of ExpressionTree.
     *
     * @param expression expression.
     */
    public ExpresionTree(String expression){
        this.expression = expression;
        this.top = generateFromExpression();
    }



    public static void putVar(String varName, ExpresionTree expresion){
        vars.put(varName.toUpperCase(), expresion);
    }



    public static ExpresionTree getVar(String varName){
        return vars.get(varName);
    }



    /**
     * generateFromExpression method.
     * Abstract method.
     *
     * @return top node of ExpressionTree
     */
    protected abstract NodeExpression generateFromExpression();



    /**
     * Getter of Expression
     *
     * @return NodeExpression top
     */
    protected NodeExpression getTop() {
        return top;
    }



    /**
     * Getter of Expression
     *
     * @return String expression
     */
    protected String getExpression() {
        return expression;
    }







    /**
     * operate function.
     *
     * Do operations calling operate function on top node.
     *
     * @return ExpressionTree value.
     */
    public BigInteger operate() throws IllegalStateException{
        return top.operate();
    }



    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return getExpression();
    }


    /**
     *
     * ************************************************************************
     *                              Nodos
     * ************************************************************************
     *
     */












}
