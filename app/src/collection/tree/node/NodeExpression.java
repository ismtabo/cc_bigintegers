package collection.tree.node;

/**
 * Created by garciparedes on 27/11/15.
 */

import collection.tree.Operation;

import java.math.BigInteger;

/**
 *
 * Class that represents operations in the tree.
 *
 */
public class NodeExpression {



    private Operation op;

    private NodeExpression nodeLeft;
    private NodeExpression nodeRight;



    public NodeExpression(Operation op){
        this.op = op;
    }



    public NodeExpression(NodeExpression nodeLeft, Operation op, NodeExpression nodeRight){
        this(op);
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }




    public NodeExpression getNodeLeft() {
        return nodeLeft;
    }

    public NodeExpression getNodeRight() {
        return nodeRight;
    }

    @Override
    public String toString() {
        return op.toString();
    }



    public BigInteger operate() throws IllegalStateException {
        if(op == Operation.ADD){
            return getNodeRight().operate().add(getNodeLeft().operate());
        } if(op == Operation.SUBTRACT){
            return getNodeRight().operate().subtract(getNodeLeft().operate());
        } if(op == Operation.MULTIPLY){
            return getNodeRight().operate().multiply(getNodeLeft().operate());
        } if(op == Operation.DIVIDE){
            return getNodeRight().operate().divide(getNodeLeft().operate());
        } if(op == Operation.MODULE){
            return getNodeRight().operate().mod(getNodeLeft().operate());
        } if(op == Operation.POW){
                /*
                 * TODO find a better way to cast BigInteger to int.
                 */
            return getNodeRight().operate().pow(Integer.valueOf(getNodeLeft().operate().toString()));
        }
        return null;
    }

}
