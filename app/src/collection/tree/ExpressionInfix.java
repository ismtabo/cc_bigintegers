package collection.tree;

import collection.tree.node.NodeExpression;
import collection.tree.node.NodeNumber;
import collection.tree.node.NodeVar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ExpressionInfix extends ExpresionTree {


    private static final String OP_START = "(?<op>[";
    private static final String OP_END = "]{1})";


    private static final String OPS_1 = ""
            + OP_START
            + Operation.MODULE.getRegex()
            + OP_END;

    private static final String OPS_2 = ""
            + OP_START
            + Operation.ADD.getRegex()
            + Operation.SUBTRACT.getRegex()
            + OP_END;

    private static final String OPS_3 = ""
            + OP_START
            + Operation.MULTIPLY.getRegex()
            + Operation.DIVIDE.getRegex()
            + OP_END;

    private static final String OPS_4 = ""
            + OP_START
            + Operation.POW.getRegex()
            + OP_END;


    private static final String NUMBER = "(\\d*)";

    private static final String VAR = "(\\w*)";

    private static final String LEFT = "(?<left>([^\\(\\)]*)*?(\\(.*\\))*?([^\\(\\)]*?))";
    private static final String RIGHT = "(?<right>([^\\(\\)]*)*?(\\(.*\\))*?([^\\(\\)]*?))";



    /**
     * Constructor of ExpressionTree.
     *
     * @param expression
     */
    public ExpressionInfix(String expression) {
        super(expression.replaceAll("\\s+",""));
    }



    /**
     * generateFromExpression() function.
     *
     * It is necessary to implements ExpressionTree class.
     *
     * @return NodeExpression that is the top of tree.
     */
    @Override
    protected NodeExpression generateFromExpression() {
        return reduce(getExpression());
    }


    /**
     * reduce() function.
     *
     * Recursive function that check if input expression
     * is number or variable and if it isn't try to
     * divide it in 'a operator b'.
     *
     * @param expression expression to check
     *
     * @return NodeExpression parent of subtree.
     */
    private static NodeExpression reduce(String expression){

        if (!isBracketNeeded(expression)){
            expression = expression.substring(1, expression.length() - 1);
        }

        if (isNumeric(expression)) {
            return new NodeNumber(expression);
        } else if(isVariable(expression)) {
            return new NodeVar(expression);
        } else{
            try {
                return extract(OPS_1, expression);
            } catch (IllegalStateException e0) {
                try {
                    return extract(OPS_2, expression);
                } catch (IllegalStateException e) {
                    try {
                        return extract(OPS_3, expression);
                    } catch (IllegalStateException e1) {
                        try {
                            return extract(OPS_4, expression);
                        } catch (IllegalStateException e2) {
                            return null;
                        }
                    }
                }
            }
        }
    }



    /**
     * extract() function.
     *
     * Recursive function that try to divide the input expression
     * in 'a operator b'.
     *
     * @param op operators to search.
     * @param expression expression to divide
     *
     * @exception IllegalStateException if operator is not found.

     * @return NodeExpression parent of subtree.
     */
    private static NodeExpression extract(String op, String expression){
        NodeExpression nodeLeft;
        NodeExpression nodeRight;

        String regex = (LEFT + op + RIGHT );

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        if (matcher.matches()) {

            String left = matcher.group("left");
            String right = matcher.group("right");

            if (left.length() != 0) {
                nodeLeft = reduce(left);
            } else {
                nodeLeft = new NodeNumber();
            }

            if (right.length() != 0) {
                nodeRight = reduce(right);
            } else {
                nodeRight = new NodeNumber();
            }

        } else {
            nodeLeft = new NodeNumber();
            nodeRight = new NodeNumber();
        }

        return new NodeExpression(nodeRight, Operation.isOP(matcher.group("op").charAt(0)) ,nodeLeft);
    }



    /**
     * Function isNumeric.
     *
     * It analizes if an expression is a number.
     *
     * @param expression expression.
     * @return boolean value.
     */
    private static boolean isNumeric(String expression) {
        return expression.matches(NUMBER);
    }



    /**
     * Function isVariable.
     *
     * It analizes if an expression is a variable.
     *
     * @param expression expression.
     * @return boolean value.
     */
    private static boolean isVariable(String expression) {
        return expression.matches(VAR);
    }



    /**
     * Function isBracketNeeded.
     *
     * It analizes if an expression contains unnecessary brackets.
     *
     * @param expression expression.
     * @return boolean value.
     */
    private static boolean isBracketNeeded(String expression){

        if (expression.indexOf('(',1) > expression.indexOf(')',1)){
            return true;
        }

        if ((expression.length() != 0)
                && (expression.charAt(0) == '(')
                && (expression.charAt(expression.length()-1) ==')')){
            return false;
        }

        return true;
    }
}
