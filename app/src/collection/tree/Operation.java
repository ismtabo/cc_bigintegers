package collection.tree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public enum Operation {

    /**
     * Adding operator.
     */
    ADD("+", true),
    /**
     * Substract operator.
     */
    SUBTRACT("-", true),
    /**
     * Multipy operator.
     */
    MULTIPLY("*", true),
    /**
     * Division operator.
     */
    DIVIDE("/", true),
    /**
     * Module operator.
     */
    MODULE("%", true),
    /**
     * Power operator.
     */
    POW("^", true),
    /**
     * Assignment/result operator.
     */
    RESULT("=", true),
    /**
     * Left bracket.
     */
    LBRACKET("(", true),
    /**
     * Right bracket.
     */
    RBRACKET(")", true),
    /**
     * Mod inverse operation.
     *
     * Infix operation xfy, in which given x we lookfor its invers number at
     * corpus (y)Z.
     */
    MODINVERSE(".modInverse( )", false),
    /**
     * Mod pow operation.
     *
     * Infix operation xf(y,n), in which given x and y we want x^y value at
     * corpus (y)Z.
     */
    MODPOW(".modPow( , )", false),
    /**
     * Is Probable Prime.
     *
     * Infix operation xfy, in which given x we want to know if it is prime at
     * corpus (y)Z.
     */
    ISPROBABLEPRIME(".ismodPrime( )", false),
    /**
     * Next Probable Prime.
     *
     * Given x, we want to know the next integer number which is probably prime.
     */
    NEXTPROBABLEPRIME(".nextProbablePrime()", false);

    private final String symbol;
    private final boolean basic;

    /**
     * Constructor of Operation.
     *
     * @param symbol {@link String} expression which represents symbol.
     * @param basic boolean which represent if operation is basic(common
     * operator) or complex.
     */
    Operation(String symbol, boolean basic) {
        this.symbol = symbol;
        this.basic = basic;
    }

    /**
     * toString function.
     *
     * @return op symbol.
     */
    @Override
    public String toString() {
        return String.valueOf(getSymbol());
    }

    /**
     * Getter of Symbol.
     *
     * @return symbol char
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getter of Regex expression.
     *
     * Difference between basic and complex operations.
     *
     * @return symbol regex expression to find it char.
     */
    public String getRegex() {
        if (this.basic) {
            return "\\" + getSymbol();
        }
        String regexp = "";
        switch (this) {
            case MODINVERSE:
                regexp = "(?<left>\\w*).modInverse\\((?<right>\\w*)\\)";
                break;
            case MODPOW:
                regexp = "(?<left>\\w*).modPow\\((?<right1>\\w*),(?<right2>\\w*)\\)";
                break;
            case ISPROBABLEPRIME:
                regexp = "(?<left>\\w*).ismodPrime\\((?<right>\\w*)\\)";
                break;
            case NEXTPROBABLEPRIME:
                regexp = "(?<left>\\w*).nextProbablePrime\\(\\)";
                break;
            default:
                break;
        }
        assert !regexp.equals("");
        return regexp;

    }

    /**
     * isOp function.
     *
     * Static Mehod.
     *
     * It returns Operation Object or null if no exist.
     *
     * @param c Character to generate Operation
     * @return Operation that represents the c.
     */
    public static Operation isOP(String c) {
        if (c.equals(ADD.getSymbol())) {
            return ADD;
        }

        if (c.equals(SUBTRACT.getSymbol())) {
            return SUBTRACT;
        }

        if (c.equals(MULTIPLY.getSymbol())) {
            return MULTIPLY;
        }

        if (c.equals(DIVIDE.getSymbol())) {
            return DIVIDE;
        }

        if (c.equals(MODULE.getSymbol())) {
            return MODULE;
        }

        if (c.equals(POW.getSymbol())) {
            return POW;
        }

        if (c.equals(RESULT.getSymbol())) {
            return RESULT;
        }

        throw new IllegalArgumentException("Operación irreconocible");
    }

}
