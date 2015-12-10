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
    private static final String ERROR_HIGH_EXPONENT = "Exponente demasiado grande: ";
    private static final String ERROR_DIVIDE_BY_ZERO = "División entre 0: " ;
    private static final String ERROR_NEGATIVE_EXPONENT = "Exponente negativo: ";


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


    private BigInteger getOperatedNodeLeft() throws IllegalArgumentException {
        return getNodeLeft().operate();
    }



    private BigInteger getOperatedNodeRight() throws IllegalArgumentException {
        return getNodeRight().operate();
    }



    @Override
    public String toString() {
        return op.toString();
    }



    public BigInteger operate() throws IllegalArgumentException {
        Operation op = getOp();
        if(op == Operation.ADD){
            return getOperatedNodeRight().add(getOperatedNodeLeft());

        } if(op == Operation.SUBTRACT){
            return getOperatedNodeRight().subtract(getOperatedNodeLeft());

        } if(op == Operation.MULTIPLY){
            return getOperatedNodeRight().multiply(getOperatedNodeLeft());

        } if(op == Operation.DIVIDE){

            try {
                return getOperatedNodeRight().divide(getOperatedNodeLeft());
            } catch (ArithmeticException e){
                throw new IllegalArgumentException(ERROR_DIVIDE_BY_ZERO + getOperatedNodeRight() +"/0");
            }

        } if(op == Operation.MODULE){
            return getOperatedNodeRight().mod(getOperatedNodeLeft());

        } if(op == Operation.POW){
                /*
                 * TODO find a better way to cast BigInteger to int.
                 */
            try {
                return getOperatedNodeRight().pow(Integer.valueOf(getOperatedNodeLeft().toString()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ERROR_HIGH_EXPONENT + getOperatedNodeLeft().toString());
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException(ERROR_NEGATIVE_EXPONENT +  getOperatedNodeLeft().toString());

            }
        }
        throw new IllegalArgumentException(ERROR_UNRECOGNIZABLE_OP + this);
    }

}
