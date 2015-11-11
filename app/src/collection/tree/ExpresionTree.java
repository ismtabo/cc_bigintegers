package collection.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ismtabo
 * @author garciparedes
 */
public class ExpresionTree {

    public static final int INFIX = 0;
    public static final int POSTFIX = 1;

    private NodeOp top;

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
     * generateTree funtion.
     *
     * Function to generate tree.
     *
     * @param expression String of expression.
     * @return NodeOp top node of tree.
     */
    private NodeOp generateTree(String expression) {
        List<Node> nodes = stringToNodes(expression);

        /**
         * TODO Implement method.
         * Ya se ha extraido todos los nodos de la cadena de caracteres
         * Ahora hay que colocar esa lista en forma de arbol.
         */
        return null;
    }

    /**
     * stringToNodes function.
     *
     * It generate list of nodes from expression.
     *
     * @param expression String of expression.
     * @return List of nodes.
     */
    private List<Node> stringToNodes(String expression) {
        int i = 0, temp, len = expression.length();

        List<Node> list = new ArrayList<>();
        Node node;
        char c;

        while(i < len){
            c = expression.charAt(i);
            if((node = createNodeOp(c)) !=  null){
                list.add(node);
                i++;
            } else if (isDigit(expression.charAt(i))) {
                temp = i + 1;
                while ((temp < len) && isDigit(expression.charAt(temp))) {
                    temp++;
                }
                node = new NodeNumber(expression.substring(i, temp));
                list.add(node);
                i = temp;
            } else {
                i++;
            }
        }

        for (int t = 0; t < list.size(); t++){
            System.out.println(list.get(t));
        }

        return null;
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
     * createNodeOp function.
     *
     * It creates NodeOp object if is possible,
     * if not returns null.
     *
     * @param c char of operation.
     * @return NodeOp
     */
    private NodeOp createNodeOp(char c) {
        Operation op;
        if ((op = Operation.isOP(c))!= null) {
            return new NodeOp(op);
        }
        return null;
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
     *
     * ************************************************************************
     *                              Nodos
     * ************************************************************************
     *
     */



    /**
     *
     * Interface that encapsulates Node implementations.
     *
     */
    private interface Node{
    }



    /**
     *
     * Class that represents operations in the tree.
     *
     */
    private  class NodeOp implements Node{
        private Operation op;
        private Node nodeLeft;
        private Node nodeRight;

        public NodeOp(Operation op){
            this.op = op;
        }

        public NodeOp(Operation op, Node nodeLeft, Node nodeRight){
            this(op);
            this.nodeLeft = nodeLeft;
            this.nodeRight = nodeRight;
        }

        @Override
        public String toString() {
            return op.name();
        }
    }



    /**
     * Class that represents numbers in the tree.
     */
    private class NodeNumber implements Node{
        private int value;

        public NodeNumber(int value){
            this.value = value;
        }
        
        public NodeNumber(String value){
            this(Integer.valueOf(value));
        }

        @Override
        public String toString() {
            return value + "";
        }
    }
}
