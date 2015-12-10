package controller;
/**
 * Created by ismtabo, in cc_bigintegers.
 */

import collection.tree.ExpressionInfix;
import collection.tree.ExpressionTree;
import collection.tree.Operation;

import javax.management.ValueExp;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.IllegalFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Expressions controller.
 * <p/>
 * This controller filter the users' expression making differences between operations and assignments.
 */
public class ExpressionController {

    private static final String ALPHA = "(?<var>[a-zA-Z]+).";

    private static final String RIGHT = ".(?<right>([^\\(\\)]*)*?(\\(.*\\))*?([^\\(\\)]*?))";

    private static final String ASSIGN = ALPHA + Operation.RESULT.getRegex() + RIGHT;

    private ExpressionTree expressionTree;

    /**
     * ExpressionController constructor.
     */
    public ExpressionController() {
        expressionTree = null;
    }

    /**
     * readExression() function.
     * <p/>
     * Controller read {@code expression} and filter assignments expressions from operations.
     * If {@code expression} is an assignment {@code ExpressionController}
     * will record a variable at {@link ExpressionTree#vars}.
     *
     * @param expression - user input expression
     */
    public void readExpression(String expression) {
        expression.replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile(ASSIGN);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.matches()) {

            String var_name = matcher.group("var");
            String right = matcher.group("right");

            expressionTree = new ExpressionInfix(right);
            ExpressionTree.putVar(var_name, expressionTree);
        } else {
            expressionTree = new ExpressionInfix(expression);
        }
    }

    /**
     * result() function.
     * <p/>
     * Expression controller evaluates cached ExpressionTree.
     *
     * @return BigInteger result
     * @throws IllegalStateException - In case {@expressiontree} can not be evaluated.
     */
    public BigInteger result() throws IllegalStateException, IllegalArgumentException {
        BigInteger result;
        result = expressionTree.operate();
        return result;
    }
}
