/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import collection.tree.ExpressionInfix;
import collection.tree.ExpressionTree;
import collection.tree.Operation;
import java.math.BigInteger;
import view.View;

/**
 *
 * @author garciparedes
 */
public class Controller {

    private View view;

    private ExpressionController expressionController;

    private BigInteger cacheNumber;

    public Controller(View view) {
        this.view = view;
        this.expressionController = new ExpressionController();
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

    public void reset() {
        setCacheNumber(BigInteger.ZERO);
        update();
    }

    public void update() {
        view.setJTextFieldInputText(getCacheNumber().toString());
    }

    public void evaluate() {
        view.clearErrors();
        String expression = view.getJTextFieldInputText();
        String result = "--no result--";
        ExpressionTree expressiontree;
        try {
            expressionController.readExpression(expression);
            cacheNumber = expressionController.result();
            result = cacheNumber.toString();
            view.appendJTextAreaResult(expression, result);
        } catch (Exception e) {
            view.showError("Operación no válida.");
            e.printStackTrace();
        }
        
    }
}
