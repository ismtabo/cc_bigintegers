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


    public Controller(View view) {
        this.view = view;
        this.expressionController = new ExpressionController();
    }

    public void evaluate() {
        view.clearErrors();
        String expression = view.getJTextFieldInputText();
        String result;
        ExpressionTree expressiontree;
        try {
            expressionController.readExpression(expression);
            result = expressionController.result();
            view.appendJTextAreaResult(expression, result);
        } catch (Exception e) {
            view.showError(e.getMessage());
            e.printStackTrace();
        }
    }
}
