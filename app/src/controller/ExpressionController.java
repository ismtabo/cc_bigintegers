package controller;
/**
 * Created by ismtabo, in cc_bigintegers.
 */

import collection.tree.ExpressionInfix;
import collection.tree.ExpressionTree;
import collection.tree.Operation;
import collection.tree.node.NodeExpression;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static collection.tree.Operation.*;
import static collection.tree.Operation.ISPROBABLEPRIME;
import static collection.tree.Operation.MODINVERSE;
import static collection.tree.Operation.MODPOW;

/**
 * Expressions controller.
 * <p/>
 * This controller filter the users' expression making differences between operations and assignments.
 */
public class ExpressionController {

    private static final String ALPHA = "(?<left>[a-zA-Z]+)";

    private static final String RIGHT = "(?<right>([^\\(\\)]*)*?(\\(.*\\))*?([^\\(\\)]*?))";

    private static final String ASSIGN = ALPHA + "." + RESULT.getRegex() + "." + RIGHT;

    private static final List<Operation> complex_operations_regexp =
            Arrays.asList(MODINVERSE, MODPOW,
                    ISPROBABLEPRIME, NEXTPROBABLEPRIME);

    private ExpressionTree expressionTree;
    private String cached_result;

    /**
     * ExpressionController constructor.
     */
    public ExpressionController() {
        expressionTree = null;
        cached_result = "";
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

        expressionTree = null;
        cached_result = "";

        Pattern assign_pattern = Pattern.compile(ASSIGN);
        Matcher assign_matcher = assign_pattern.matcher(expression);
        if (assign_matcher.matches()) {

            String var_name = assign_matcher.group("left");
            String right = assign_matcher.group("right");

            expressionTree = new ExpressionInfix(right);
            ExpressionTree.putVar(var_name, expressionTree);

        } else {
            cached_result = testNSetComplex(expression);
            if (cached_result == null)
                expressionTree = new ExpressionInfix(expression);
        }
    }

    /**
     * result() function.
     * <p/>
     * Expression controller evaluates cached ExpressionTree.
     *
     * @return BigInteger result
     * @throws IllegalStateException - In case {@expressionTree} can not be evaluated.
     */
    public String result() throws IllegalStateException, IllegalArgumentException {
        if (cached_result == null) {
            String result;
            result = expressionTree.operate().toString();
            return result;
        } else
            return cached_result;
    }

    /*
     * Test and set result from expression.
     *
     * Method check if expression is a complex operation(not basic).
     * If false, expression is not a complex operation, return result = null
     * Else, check type of operation, and evaluate it, giving back the result.
     *
     * For more information about Operation, check Enumerate Operation.
     */
    private String testNSetComplex(String expression) throws IllegalArgumentException {
        Operation complex_operation = null;
        Pattern pattern;
        Matcher matcher = null;

        // Check all types of not basic operators
        for (Operation operation : complex_operations_regexp) {
            pattern = Pattern.compile(operation.getRegex());
            matcher = pattern.matcher(expression);
            if (matcher.matches()) {
                complex_operation = operation;
                break;
            }
        }


        if (complex_operation == null)
            return null;

        // String expression operands
        String left;
        String right;
        String result = "";

        // Numeric operands
        BigInteger x;
        BigInteger y;
        int integer;

        switch (complex_operation) {
            case MODINVERSE://BigInteger: x.modInverse(y) operation
                left = matcher.group("left");
                x = toBigInteger(left);
                right = matcher.group("right");
                y = toBigInteger(right);
                result = x.modInverse(y).toString();
                break;
            case MODPOW://BigInteger: x.modPow(exp,n) operation
                left = matcher.group("left");
                x = toBigInteger(left);
                right = matcher.group("right1");
                y = toBigInteger(right);
                String right2 = matcher.group("right2");
                BigInteger z = toBigInteger(right2);
                result = x.modPow(y, z).toString();
                break;
            case ISPROBABLEPRIME://boolean: x.isProbablePrime(n) operation
                left = matcher.group("left");
                x = toBigInteger(left);
                right = matcher.group("right");
                try {
                    integer = Integer.parseInt(right);
                } catch (Exception ex) {// Exception if n can't be cast as integer
                    ex.printStackTrace();
                    throw new IllegalArgumentException("Variable modular demasiado grande.");
                }
                result = "" + x.isProbablePrime(integer);
                break;
            case NEXTPROBABLEPRIME://BigInteger: x.nextProbablePrime() operation
                left = matcher.group("left");
                x = toBigInteger(left);
                result = x.nextProbablePrime().toString();
                break;

        }

        return result;
    }

    /*
     * String expression to BigInteger.
     *
     * Evaluate expression as Expression tree. With two possible cases:
     * - Variable saved at vars map
     * - Numeric literal
     *
     * Both can be translate into Expression tree, and result can be obtained by operating them.
     *
     * return BigInteger result
     */
    private BigInteger toBigInteger(String expression) throws IllegalArgumentException {
        /*BigInteger biginteger;
        if (expression.matches(ALPHA)) {
            try {
                biginteger = ExpressionTree.getVar(expression).operate();
            } catch (Exception ex) {
                throw new IllegalArgumentException("Variable no definida");
            }
        } else {
            biginteger = new BigInteger(expression);
        }*/
        ExpressionTree expressionTree = new ExpressionInfix(expression);
        return expressionTree.operate();
    }
}
