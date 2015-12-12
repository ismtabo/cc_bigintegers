package collection.tree;

import collection.tree.node.NodeExpression;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public abstract class ExpressionTree {

    private static HashMap<String, ExpressionTree> vars = new HashMap<>();

    private NodeExpression top;
    private String expression;

    /**
     *
     * Constructor of ExpressionTree.
     *
     * @param expression expression.
     */
    public ExpressionTree(String expression) throws IllegalArgumentException {
        this.expression = expression;
        try {
            this.top = generateFromExpression();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(expression
                    + "\n"
                    + e.getMessage()
            );
        }
    }

    public static void putVar(String varName, ExpressionTree expresion) {
        vars.put(varName.toUpperCase(), expresion);
    }

    public static ExpressionTree getVar(String varName) {
        return vars.get(varName.toUpperCase());
    }

    /**
     * generateFromExpression method. Abstract method.
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
    public BigInteger operate() throws IllegalArgumentException {
        try {
            return top.operate();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(getExpression()
                    + '\n'
                    + e.getMessage()
            );
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return getExpression();
    }
}
