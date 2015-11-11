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
    RESULT('=');

    private final char symbol;

    /**
     * Constructor of Operation.
     *
     * @param symbol char who represents symbol.
     */
    Operation(char symbol){
        this.symbol = symbol;
    }

    /**
     * isOp funtion.
     *
     * Static Mehod.
     *
     * It returns Operation Object or null if no exist.
     *
     * @param c Character to generate Operation
     * @return Operation that represents the c.
     */
    public static Operation isOP(char c){
        if (c == ADD.symbol)
            return ADD;

        if (c == SUBTRACT.symbol)
            return SUBTRACT;

        if (c == MULTIPLY.symbol)
            return MULTIPLY;

        if (c == DIVIDE.symbol)
            return DIVIDE;

        if (c == MODULE.symbol)
            return MODULE;

        if (c == POW.symbol)
            return POW;

        if (c == RESULT.symbol)
            return RESULT;

        return null;
    }

}

