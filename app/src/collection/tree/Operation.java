package collection.tree;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public enum Operation {

    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    MODULE('%'),
    POW('^'),
    RESULT('='),
    LBRACKET('('),
    RBRACKET(')');

    private final char symbol;

    /**
     * Constructor of Operation.
     *
     * @param symbol char who represents symbol.
     */
    Operation(char symbol) {
        this.symbol = symbol;
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
    public char getSymbol() {
        return symbol;
    }

    /**
     * Getter of Regex expression.
     *
     * @return symbol regex expression to find it char.
     */
    public String getRegex() {
        return "\\" + getSymbol();
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
    public static Operation isOP(char c) {
        if (c == ADD.getSymbol()) {
            return ADD;
        }

        if (c == SUBTRACT.getSymbol()) {
            return SUBTRACT;
        }

        if (c == MULTIPLY.getSymbol()) {
            return MULTIPLY;
        }

        if (c == DIVIDE.getSymbol()) {
            return DIVIDE;
        }

        if (c == MODULE.getSymbol()) {
            return MODULE;
        }

        if (c == POW.getSymbol()) {
            return POW;
        }

        if (c == RESULT.getSymbol()) {
            return RESULT;
        }

        throw new IllegalArgumentException("Operación irreconocible");
    }

}
