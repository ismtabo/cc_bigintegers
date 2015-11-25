package collection.tree;

import java.util.HashMap;

/**
 * Created by garciparedes on 24/11/15.
 */
public class ExpressionInfix extends ExpresionTree {



    /**
     * Constructor of ExpressionTree.
     *
     * @param expression
     */
    public ExpressionInfix(String expression) {
        super(expression);
    }



    /**
     *
     * @return
     */
    @Override
    protected NodeExpression generateFromExpression() {
        return null;
    }
}
