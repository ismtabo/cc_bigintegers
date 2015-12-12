package collection.tree.node;

/**
 * Created by garciparedes on 27/11/15.
 */

import java.math.BigInteger;

/**
 * Class that represents numbers in the tree.
 */
public class NodeNumber extends NodeExpression {



    private BigInteger value;



    public NodeNumber(){
        this(BigInteger.ZERO);
    }



    public NodeNumber(BigInteger value){
        super(null, null, null);
        this.value = value;
    }



    public NodeNumber(String value){
        this(new BigInteger(value));
    }



    @Override
    public BigInteger operate() {
        return value;
    }



    @Override
    public String toString() {
        return value.toString();
    }
}
