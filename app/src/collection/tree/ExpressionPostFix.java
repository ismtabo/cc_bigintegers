package collection.tree;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by garciparedes on 24/11/15.
 */
public class ExpressionPostFix  extends ExpresionTree{



    /**
     * Constructor of ExpressionTree.
     *
     * @param expression String of expression.
     */
    public ExpressionPostFix(String expression) {
        super(expression);
    }




    /**
     * generateFromPostfix function.
     *
     * It generates tree from expression.
     *
     * @return List of nodes.
     */
    @Override
    protected NodeExpression generateFromExpression() {
        int i = 0, temp, len = getExpression().length();

        final Stack<NodeExpression> nodeStack = new Stack<NodeExpression>();
        Operation op;
        NodeExpression nodeExpression1;
        NodeExpression nodeExpression2;
        char c;

        while(i < len){
            c = getExpression().charAt(i);

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

            } else if (Character.isDigit(getExpression().charAt(i))) {
                temp = i + 1;
                while ((temp < len) && Character.isDigit(getExpression().charAt(temp))) {
                    temp++;
                }

                nodeStack.add(
                        new NodeNumber(getExpression().substring(i, temp))
                );
                i = temp;

            } else if (Character.isLetter(getExpression().charAt(i))) {
                temp = i + 1;
                while ((temp < len) && Character.isLetter(getExpression().charAt(temp))) {
                    temp++;
                }

                nodeStack.add(
                        new NodeVar(getExpression().substring(i, temp))
                );
                i = temp;
            } else {
                i++;
            }
        }
        return nodeStack.pop();
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
            inOrder(node.getNodeRight(), infix);
            infix.append(node);
            infix.append(" ");
            inOrder(node.getNodeLeft(), infix);
        }
    }

    /**
     * Returns the infix expression
     *
     * @return the string of infix.
     */
    public String infix() {
        final StringBuilder infix = new StringBuilder();
        try {
            inOrder(getTop(), infix);
            return infix.toString();

        } catch (IllegalStateException e){
            return e.getMessage();
        }
    }

    @Override
    public String toString() {
        return infix();
    }
}
