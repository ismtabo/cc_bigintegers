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


    private static final String ERROR_UNRECOGNIZABLE_OP = "Operación irreconocible: ";


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

    public Operation getOp() {
        return op;
    }

    private BigInteger getOperatedNodeLeft(){
        return getNodeLeft().operate();
    }



    private BigInteger getOperatedNodeRight(){
        return getNodeRight().operate();
    }



    @Override
    public String toString() {
        return op.toString();
    }



    public BigInteger operate() {
        Operation op = getOp();
        if(op == Operation.ADD){
            return getOperatedNodeRight().add(getOperatedNodeLeft());

        } if(op == Operation.SUBTRACT){
            return getOperatedNodeRight().subtract(getOperatedNodeLeft());

        } if(op == Operation.MULTIPLY){
            return getOperatedNodeRight().multiply(getOperatedNodeLeft());

        } if(op == Operation.DIVIDE){
            return getOperatedNodeRight().divide(getOperatedNodeLeft());

        } if(op == Operation.MODULE){
            return getOperatedNodeRight().mod(getOperatedNodeLeft());

        } if(op == Operation.POW){
                /*
                 * TODO find a better way to cast BigInteger to int.
                 */
            return getOperatedNodeRight().pow(Integer.valueOf(getOperatedNodeLeft().toString()));
        }
        throw new IllegalArgumentException(ERROR_UNRECOGNIZABLE_OP + this);
    }

}
