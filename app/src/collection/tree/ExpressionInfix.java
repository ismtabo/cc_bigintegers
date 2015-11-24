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
     * @param varsMap
     */
    public ExpressionInfix(String expression, HashMap<Character, ExpresionTree> varsMap) {
        super(expression, varsMap);
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
