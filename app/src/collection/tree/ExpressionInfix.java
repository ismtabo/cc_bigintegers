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

    private static final String NUMBER_A = "(?<numA>\\d*)";
    private static final String NUMBER_B = "(?<numB>\\d*)";

    private static final String VAR_A = "(?<varA>\\D*)";
    private static final String VAR_B = "(?<varB>\\D*)";

    private static final String BRACKETS_A = "(?<bracketsA>\\(.*\\))";
    private static final String BRACKETS_B = "(?<bracketsB>\\(.*\\))";

    private static final String A = "(?<A>" + NUMBER_A + "|" + VAR_A + "|" + BRACKETS_A + ")";
    private static final String B = "(?<B>" + NUMBER_B + "|" + VAR_B + "|" + BRACKETS_B + ")";

    private static final String otherLeft = "(?<otherLeft>[^\\d]*)";
    private static final String otherRight = "(?<otherRight>.*)";



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

        String regex = (""
                //+ otherLeft
                + A
                + operators
                + B
                //+ otherRight
        );

        System.out.println(getExpression());
        System.out.println(regex);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(getExpression());

        System.out.println(matcher.matches());
        //System.out.println(matcher.group("otherLeft"));
        System.out.println(matcher.group("A"));
        System.out.println(matcher.group("op"));
        System.out.println(matcher.group("B"));
        //System.out.println(matcher.group("otherRight"));

        //TODO
        return null;
    }
}
