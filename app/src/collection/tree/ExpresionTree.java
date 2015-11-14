package collection.tree;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ExpresionTree {

    public static final int INFIX = 0;
    public static final int POSTFIX = 1;


    private NodeExpression top;



    /**
     * Constructor of ExpressionTree.
     *
     * @param expression String of expression.
     * @param format integer containing format of expression
     */
    public ExpresionTree(String expression, int format){
        if(format == INFIX){
            expression = infixToPostFix(expression);
        }
        this.top = generateTree(expression);
    }



    /**
     * generateTree function.
     *
     * It generates tree from expression.
     *
     * @param postFixExpression String of expression.
     * @return List of nodes.
     */
    private NodeExpression generateTree(String postFixExpression) {
        int i = 0, temp, len = postFixExpression.length();

        final Stack<NodeExpression> nodeStack = new Stack<NodeExpression>();
        Operation op;
        NodeExpression nodeExpression1;
        NodeExpression nodeExpression2;
        char c;

        while(i < len){
            c = postFixExpression.charAt(i);

            if((op = Operation.isOP(c)) !=  null){
                try{
                    nodeExpression1 = nodeStack.pop();
                    try {
                        nodeExpression2 = nodeStack.pop();
                    } catch (EmptyStackException e){
                        nodeExpression2 = new NodeNumber();
                    }
                }catch (EmptyStackException e){
                    nodeExpression1 = new NodeNumber();
                    nodeExpression2 = new NodeNumber();
                }

                nodeStack.push(new NodeExpression(nodeExpression1, op, nodeExpression2));
                i++;
            } else if (isDigit(postFixExpression.charAt(i))) {
                temp = i + 1;
                while ((temp < len) && isDigit(postFixExpression.charAt(temp))) {
                    temp++;
                }

                nodeStack.add(
                        new NodeNumber(postFixExpression.substring(i, temp))
                );
                i = temp;
            } else {
                i++;
            }
        }
        return nodeStack.pop();
    }



    /**
     * isDigit function.
     *
     * It calculates if char is numeric digit.
     *
     * @param value char to check
     * @return boolean containing if value is digit or not.
     */
    private boolean isDigit(char value) {
        return value >= '0' && value <= '9';
    }



    /**
     * infixToPostFix function.
     *
     * It transforms Infix expression to Postfix expression.
     *
     * @param expression Infix expression
     * @return Postfix expression
     */
    private String infixToPostFix(String expression) {
        //TODO Implement method.
        return null;
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
     * ************************************************************************
     *                              Nodos
     * ************************************************************************
     *
     */



    /**




    /**
     *
     * Class that represents operations in the tree.
     *
     */
    private class NodeExpression {



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
    private class NodeNumber extends NodeExpression {



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
}
