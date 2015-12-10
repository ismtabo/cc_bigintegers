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
public class ExpressionInfix extends ExpressionTree {


    private static final String ERROR_EMPTY_EX = "Expresión vacía";
    private static final String ERROR_UNRECOGNIZABLE_EX = "Expresión Irreconocible: ";

    private static final String LEFT_OPERAND = "left";
    private static final String RIGHT_OPERAND = "right";
    private static final String OPERAND = "op";


    private static final String OP_START = "(?<" + OPERAND +">[";
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



    private static final String NUMBER = "(\\d+)";
    private static final String VAR = "(\\w+)";


    private static final String LEFT = "(?<" + LEFT_OPERAND + ">([^\\(\\)]*)*?(\\(.*\\))*?([^\\(\\)]*?))";
    private static final String RIGHT = "(?<" + RIGHT_OPERAND + ">([^\\(\\)]*)*?(\\(.*\\))*?([^\\(\\)]*?))";



    /**
     * Constructor of ExpressionTree.
     *
     * @param expression
     */
    public ExpressionInfix(String expression) {
        super(expression.replaceAll("\\s+", "").toUpperCase());
    }



    /**
     * generateFromExpression() function.
     *
     * It is necessary to implements ExpressionTree class.
     *
     * @return NodeExpression that is the top of tree.
     */
    @Override
    protected NodeExpression generateFromExpression() throws IllegalArgumentException  {
        if(getExpression().length() == 0) {
            throw new IllegalArgumentException(ERROR_EMPTY_EX);
        }
        return reduce(getExpression());

    }



    /**
     * reduce() function.
     *
     * Recursive function that check if input expression is number or variable
     * and if it isn't try to divide it in 'a operator b'.
     *
     * @param expression expression to check
     *
     * @return NodeExpression parent of subtree.
     */
    private static NodeExpression reduce(String expression) throws IllegalArgumentException {

        if (isNumeric(expression)) {
            return new NodeNumber(expression);
        } else if (isVariable(expression)) {
            return new NodeVar(expression);
        } else {
            try {
                return extract(OPS_1, expression);
            } catch (IllegalArgumentException e0) {
                try {
                    return extract(OPS_2, expression);
                } catch (IllegalArgumentException e1) {
                    try {
                        return extract(OPS_3, expression);
                    } catch (IllegalArgumentException e2) {
                        try {
                            return extract(OPS_4, expression);
                        } catch (IllegalArgumentException e3) {
                            if(expression.charAt(0)== '('
                                    && expression.charAt(expression.length()-1)== ')') {
                                return reduce(expression.substring(1, expression.length() - 1));
                            }else{
                                throw new IllegalArgumentException(e3.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }



    /**
     * extract() function.
     *
     * Recursive function that try to divide the input expression in 'a operator
     * b'.
     *
     * @param op operators to search.
     * @param expression expression to divide
     *
     * @exception IllegalStateException if operator is not found.
     *
     * @return NodeExpression parent of subtree.
     */
    private static NodeExpression extract(String op, String expression) throws IllegalArgumentException {
        NodeExpression nodeLeft;
        NodeExpression nodeRight;

        String regex = (LEFT + op + RIGHT);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        if (matcher.matches()) {

            String left = matcher.group(LEFT_OPERAND);
            String right = matcher.group(RIGHT_OPERAND);


            if ((countChar(left, '(')+countChar(left, ')'))%2 !=0){
                throw new IllegalArgumentException(ERROR_UNRECOGNIZABLE_EX + expression);
            }

            if (left.length() != 0) {

                if (isCorrectOP(left.charAt(left.length()-1)+"")){
                    throw new IllegalStateException(ERROR_UNRECOGNIZABLE_EX + expression);
                }

                nodeLeft = reduce(left);
            } else {
                nodeLeft = new NodeNumber();
            }

            if (right.length() != 0) {

                if(isCorrectOP(right.charAt(0)+"")) {
                    throw new IllegalStateException(ERROR_UNRECOGNIZABLE_EX + expression);
                }

                nodeRight = reduce(right);
            } else {
                nodeRight = new NodeNumber();
            }

        } else {
            nodeLeft = new NodeNumber();
            nodeRight = new NodeNumber();
        }
        try {
            return new NodeExpression(nodeRight, Operation.isOP(matcher.group(OPERAND).charAt(0)+""), nodeLeft);
        } catch (IllegalStateException e){
            throw new IllegalArgumentException(ERROR_UNRECOGNIZABLE_EX + expression);
        }
    }



    private static int countChar(String string, char c){
        int counter = 0;
        for( int i=0; i<string.length(); i++ ) {
            if( string.charAt(i) == c ) {
                counter++;
            }
        }
        return counter;
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
     * Function isVariable.
     *
     * It analizes if an expression is a variable.
     *
     * @param value expression.
     * @return boolean value.
     */
    private static boolean isCorrectOP(String value) {
        return (value.matches(OPS_1)
                || value.matches(OPS_3)
                || value.matches(OPS_4));
    }
}
