package collection.tree;

/**
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */


import java.util.Stack;

/**
 * Infix2Postfix class.
 *
 * Code extracted from http://www.java.achchuthan.org/2012/03/convert-infix-to-postfix-using-stack-in.html
 *
 * We have adapted the code to our needs.
 *
 * @see <a href="http://www.java.achchuthan.org/2012/03/convert-infix-to-postfix-using-stack-in.html</a>
 *
 * @author ACHCHUTHAN
 *
 * @author ismtabo
 * @author garciparedes
 */
public class Infix2Postfix extends Stack<Character> {

    public Infix2Postfix() {
        super();
    }

    /**
     *@return postfixString value
     */

    public String InToPost(String infixString) {

        String postfixString = " ";

        for (int index = 0; index < infixString.length(); ++index) {

            char chValue = infixString.charAt(index);

            if (chValue == '(') {
                push('(');

            } else if (chValue == ')') {

                Character oper = peek();

                while (!(oper.equals('(')) && !(isEmpty())) {
                    postfixString += oper;
                    pop();
                    oper = peek();
                }

                pop();

            } else if (chValue == '+' || chValue == '-') {
                //Stack is empty
                if (isEmpty()) {
                    push(chValue);
                    //current Stack is not empty

                } else {

                    Character oper = peek();

                    while (!(isEmpty() || oper.equals('(') || oper.equals(')'))) {
                        pop();
                        postfixString += oper;
                    }
                    push(chValue);
                }

            } else if (chValue == '*' || chValue == '/' || chValue == '%' || chValue == '^') {

                if (isEmpty()) {

                    push(chValue);

                } else {

                    Character oper = peek();

                    while (!oper.equals('+') && !oper.equals('-') && !isEmpty()) {

                        pop();

                        postfixString += oper;

                    }

                    push(chValue);

                }

            } else {
                postfixString += chValue;
            }
        }

        while (!isEmpty()) {
            Character oper = peek();

            if (!oper.equals('(')) {
                pop();
                postfixString += oper;
            }
        }

        return postfixString;
    }
}
