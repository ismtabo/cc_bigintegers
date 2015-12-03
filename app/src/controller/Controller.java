/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import collection.tree.ExpressionInfix;
import collection.tree.ExpressionTree;
import collection.tree.node.NodeExpression;
import java.math.BigInteger;
import view.View;

/**
 *
 * @author garciparedes
 */
public class Controller {

    private static final String EQUAL = "=";
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String MULTIPLY = "x";
    private static final String DIVIDE = "/";
    private static final String MOD = "%";
    private static final String POW = "^";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";

    private View view;

    private BigInteger cacheNumber;

    public Controller(View view) {
        this.view = view;
        reset();
    }

    public void setCacheNumber(BigInteger number) {
        cacheNumber = number;
    }

    public BigInteger getCacheNumber() {
        return cacheNumber;
    }

    public void addOp() {
        //setCacheNumber(cacheNumber.add(getCacheNumberOp()));
        System.out.println(cacheNumber);
    }

    private void multiplyOp() {
        //setCacheNumber(cacheNumber.multiply(getCacheNumberOp()));
        System.out.println(cacheNumber);
    }

    public void add() {
        view.appendJTextAreaResult(ADD);
    }

    public void subtract() {
        view.appendJTextAreaResult(SUBTRACT);

    }

    public void multiply() {
        view.appendJTextAreaResult(MULTIPLY);
    }

    public void divide() {
        view.appendJTextAreaResult(DIVIDE);
    }

    public void mod() {
        view.appendJTextAreaResult(MOD);
    }

    public void pow() {
        view.appendJTextAreaResult(POW);
    }

    public void reset() {
        setCacheNumber(BigInteger.ZERO);
        update();
    }

    public void update() {
        view.setJTextFieldInputText(getCacheNumber().toString());
    }

    public void openBracket() {
        view.appendJTextAreaResult(OPEN_BRACKET);
        closeBracket();
    }

    public void closeBracket() {
        view.appendJTextAreaResult(CLOSE_BRACKET);
    }

    public void execute() {
        String expression = view.getJTextFieldInputText();
        view.appendJTextAreaResult(expression);
        ExpressionTree expressiontree;
        try {
            expressiontree = new ExpressionInfix(expression);
            cacheNumber = expressiontree.operate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
