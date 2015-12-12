package collection.tree.node;

/**
 * Created by garciparedes on 27/11/15.
 */

import java.math.BigInteger;

/**
 * Class that represents literal numbers in the tree.
 */
public class NodeNumber extends NodeExpression {



    private BigInteger value;


    /**
     * Initialize instance of class.
     */
    public NodeNumber(){
        this(BigInteger.ZERO);
    }


    /**
     * Initialize instance of class given literal number value.
     * 
     * @param value {@link BigInteger} node literal.
     */
    public NodeNumber(BigInteger value){
        super(null, null, null);
        this.value = value;
    }


    /**
     * Initialize instance of class given literal number expression.
     * 
     * @param value {@link String} node expression.
     */
    public NodeNumber(String value){
        this(new BigInteger(value));
    }


    /**
     * Evaluate node value.
     * 
     * Evalutate node expression giving back the proper value.
     * 
     * @return literal number value.
     * @throws IllegalArgumentException when variable does not exist in local storage {@link ExpressionTree#vars}.
     */
    @Override
    public BigInteger operate() {
        return value;
    }


    /**
     * @return {@link String} expression of node.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
