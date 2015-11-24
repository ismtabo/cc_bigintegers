package collection.tree;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public abstract class ExpresionTree {



    private NodeExpression top;
    private HashMap<Character, ExpresionTree> varsMap;
    private String expression;



    /**
     *
     * Constructor of ExpressionTree.
     *
     * @param varsMap
     */
    public ExpresionTree(String expression, HashMap<Character, ExpresionTree> varsMap){
        this.expression = expression;
        this.varsMap = varsMap;
        this.top = generateFromExpression();
    }



    /**
     * generateFromExpression method.
     * Abstract method.
     *
     * @return top node of ExpressionTree
     */
    protected abstract NodeExpression generateFromExpression();



    /**
     * Getter of Expression
     *
     * @return String expression
     */
    public String getExpression() {
        return expression;
    }



    /**
     * Returns the infix expression
     *
     * @return the string of infix.
     */
    public String infix() {
        final StringBuilder infix = new StringBuilder();
        inOrder(top, infix);
        return infix.toString();
    }



    /**
     * operate function.
     *
     * Do operations calling operate function on top node.
     *
     * @return ExpressionTree value.
     */
    public BigInteger operate(){
        return top.operate();
    }



    /**
     * inOrder method.
     *
     * Transforms ops to infix notation.
     *
     * @param node node to transform.
     * @param infix StringBuilder where results is allocated.
     */
    private void inOrder(NodeExpression node, StringBuilder infix) {
        if (node != null) {
            inOrder(node.nodeRight, infix);
            infix.append(node);
            infix.append(" ");
            inOrder(node.nodeLeft, infix);
        }
    }



    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return infix();
    }


    /**
     *
     * ************************************************************************
     *                              Nodos
     * ************************************************************************
     *
     */



     /**
     *
     * Class that represents operations in the tree.
     *
     */
    protected class NodeExpression {



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



        @Override
        public String toString() {
            return op.toString();
        }



        public BigInteger operate(){
            if(op == Operation.ADD){
                return nodeRight.operate().add(nodeLeft.operate());
            } if(op == Operation.SUBTRACT){
                return nodeRight.operate().subtract(nodeLeft.operate());
            } if(op == Operation.MULTIPLY){
                return nodeRight.operate().multiply(nodeLeft.operate());
            } if(op == Operation.DIVIDE){
                return nodeRight.operate().divide(nodeLeft.operate());
            } if(op == Operation.MODULE){
                return nodeRight.operate().mod(nodeLeft.operate());
            } if(op == Operation.POW){
                /*
                 * TODO find a better way to cast BigInteger to int.
                 */
                return nodeRight.operate().pow(Integer.valueOf(nodeLeft.operate().toString()));
            }
            return null;
        }
    }



    /**
     * Class that represents numbers in the tree.
     */
    protected class NodeNumber extends NodeExpression {



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



    /**
     * Class that represents numbers in the tree.
     */
    protected class NodeVar extends NodeExpression {



        Character varId;



        public NodeVar(Character value){
            super(null, null, null);
            this.varId = value;
        }



        @Override
        public BigInteger operate() {
            return varsMap.get(varId).operate();
        }



        @Override
        public String toString() {
            return varsMap.get(varId).toString();
        }
    }
}
