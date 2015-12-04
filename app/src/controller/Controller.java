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

    public void reset() {
        setCacheNumber(BigInteger.ZERO);
        update();
    }

    public void update() {
        view.setJTextFieldInputText(getCacheNumber().toString());
    }

    public void evaluate() {
        String expression = view.getJTextFieldInputText();
        String result = "--no result--";
        ExpressionTree expressiontree;
        try {
            expressiontree = new ExpressionInfix(expression);
            cacheNumber = expressiontree.operate();
            result = cacheNumber.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            view.appendJTextAreaResult(expression,result);
        }
        
    }
}
