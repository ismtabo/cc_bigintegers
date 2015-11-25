package collection.tree;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public abstract class ExpresionTree {



    private static HashMap<String, ExpresionTree> vars = new HashMap<>();


    private NodeExpression top;
    private String expression;



    /**
     *
     * Constructor of ExpressionTree.
     *
     * @param expression expression.
     */
    public ExpresionTree(String expression){
        this.expression = expression;
        this.top = generateFromExpression();
    }



    public static void putVar(String varName, ExpresionTree expresion){
        vars.put(varName.toUpperCase(), expresion);
    }



    public static ExpresionTree getVar(String varName){
        return vars.get(varName);
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
        try {
            inOrder(top, infix);
            return infix.toString();

        } catch (IllegalStateException e){
            return e.getMessage();
        }
    }



    /**
     * operate function.
     *
     * Do operations calling operate function on top node.
     *
     * @return ExpressionTree value.
     */
    public BigInteger operate() throws IllegalStateException{
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



        public BigInteger operate() throws IllegalStateException {
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



        String varName;



        public NodeVar(String value){
            super(null, null, null);
            this.varName = value.toUpperCase();
        }



        @Override
        public BigInteger operate() throws IllegalStateException{
            if (getVar(varName) == null){
                throw new IllegalStateException("Variable '" + varName + "' no definida");
            }
            return getVar(varName).operate();
        }



        @Override
        public String toString() {
            if (getVar(varName)== null){
                return varName;
            }

            return getVar(varName).toString();
        }
    }
}
