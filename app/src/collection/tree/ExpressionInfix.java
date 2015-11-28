package collection.tree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ExpressionInfix extends ExpresionTree {


    private static final String operators = "(?<op>[\\+\\-\\*\\/\\^\\%]{1})";

    private static final String ops1 = "(?<op>[\\+\\-]{1})";
    private static final String ops2 = "(?<op>[\\*\\/]{1})";
    private static final String ops3 = "(?<op>[\\^\\%]{1})";

    private static final String NUMBER = "(\\d*)";
    private static final String NUMBER_B = "(?<numB>\\d*)";

    private static final String VAR = "(\\w*)";
    private static final String VAR_B = "(?<varB>\\w*)";

    private static final String BRACKETS_A = "(?<bracketsA>\\(.*\\))";
    private static final String BRACKETS_B = "(?<bracketsB>\\(.*\\))";

    private static final String SIGN_A = "(?<signA>\\-?)";
    private static final String SIGN_B = "(?<signB>\\-?)";

    //private static final String A = "(?<A>" + SIGN_A + "(" + NUMBER_A + "|" + VAR_A + "|" + BRACKETS_A + ")" + "{1})";
    //private static final String B = "(?<B>" + SIGN_B + "(" + NUMBER_B + "|" + VAR_B + "|" + BRACKETS_B + ")" + "{1})";

    private static final String otherLeft = "(?<otherLeft>([^\\(\\)]*)*(\\(.*\\))*([^\\(\\)]*))";
    private static final String otherRight = "(?<otherRight>([^\\(\\)]*)*(\\(.*\\))*([^\\(\\)]*))";



    /**
     * Constructor of ExpressionTree.
     *
     * @param expression
     */
    public ExpressionInfix(String expression) {
        super(expression.replaceAll("\\s+",""));
    }



    /**
     *
     * @return
     */
    @Override
    protected NodeExpression generateFromExpression() {

        System.out.println(getExpression());
        return reduce(getExpression());

    }



    private static NodeExpression extract(String op, String expression){
        String regexSum = (""
                + otherLeft
                + op
                + otherRight
        );

        Pattern pattern = Pattern.compile(regexSum);
        Matcher matcher = pattern.matcher(expression);

        matcher.matches();
        String left = matcher.group("otherLeft");
        String right = matcher.group("otherRight");

        System.out.println("grupo otherLeft: " + left);
        System.out.println("grupo o : " + matcher.group("op"));
        System.out.println("grupo otherRight: " + right);
        System.out.println();

        NodeExpression nodeLeft = new NodeNumber();
        NodeExpression nodeRight = new NodeNumber();

        if(left.length() != 0) {
            nodeLeft = reduce(left);
        }

        if (right.length() != 0) {
            nodeRight = reduce(right);
        }


        return new NodeExpression(nodeRight, Operation.isOP(matcher.group("op").charAt(0)) ,nodeLeft);
    }



    public static boolean isNumeric(String str) {
        return str.matches(NUMBER);  //match a number with optional '-' and decimal.
    }



    public static boolean isVariable(String str) {
        return str.matches(VAR);  //match a number with optional '-' and decimal.
    }



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
                return extract(ops1, expression);
            } catch (IllegalStateException e) {
                try {
                     return extract(ops2, expression);
                } catch (IllegalStateException e1) {
                    try {
                        return extract(ops3, expression);
                    } catch (IllegalStateException ignored) {}
                }
            }
        }
        return null;
    }



    public static boolean isBracketNeeded(String expression){

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
