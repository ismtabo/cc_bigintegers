package collection.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by garciparedes on 24/11/15.
 */
public class ExpressionInfix extends ExpresionTree {


    private static final String asignOperator = "=";// Asing operator

    private static final String sumOperator = "+\\-";// Main operators group by priority
    private static final String mulOperator = "*/%";
    private static final String powOperator = "^";
    private static final List<String> operators = Arrays.asList(asignOperator, sumOperator, mulOperator, powOperator);


    private static final String variable = "\(\w*\)|\w*";// Alphabetical value (inside parenthesis)
    private static final String xVariable = "(?P<x>"+variable+")";
    private static final String yVariable = "(?P<y>"+variable+")";

    private Pattern rg;// Actual regexp pattern(can change by priority)

    private Matcher matches;


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

        String pattern = "";
        for (operator: operators) {
            pattern = xVariable+regexOp(operator)+yVariable;
            rg.compile(pattern);
            matches = rg.matcher(expr);
            if(!matches.find()) // Expression match with actual operator
                continue;
            else{
                // TODO: regexp recursivo a traves de la expresion
                ;
            }
        }

        return null;
    }


    // Create Regexp String from decided operator
    private String regexOp(String operator){
        return "(?P<op>["+operator+"])";
    }
}
