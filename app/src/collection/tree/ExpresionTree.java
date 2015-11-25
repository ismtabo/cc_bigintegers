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
     * @return NodeExpression top
     */
    protected NodeExpression getTop() {
        return top;
    }



    /**
     * Getter of Expression
     *
     * @return String expression
     */
    protected String getExpression() {
        return expression;
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
     *
     * @return
     */
    @Override
    public String toString() {
        return getExpression();
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
