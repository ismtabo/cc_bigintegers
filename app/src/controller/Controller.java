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
import java.util.ArrayList;
import view.View;

/**
 *
 * @author garciparedes
 */
public class Controller {

    private View view;

    private ArrayList<String> cache = new ArrayList<>();
    private int indexCache = 0;

    private ExpressionController expressionController;

    /**
     * Initialize instance of class given a {@link View}.
     *
     * @param view View of application
     */
    public Controller(View view) {
        this.view = view;
        this.expressionController = new ExpressionController();
    }

    /**
     * Evaluate function.
     *
     * Evaluate user expression of view with {@link ExpressionController},
     * giving back the result, and show it at program user interface. In case it
     * ocurrs an error during evaluation, controller makes the view to show
     * errors.
     */
    public void evaluate() {
        view.clearErrors();
        String expression = view.getJTextFieldInputText();
        String result;
        ExpressionTree expressiontree;
        try {
            expressionController.readExpression(expression);
            result = expressionController.result();
            view.appendJTextAreaResult(expression, result);
            view.setJTextFieldInputText("");

            cache.add(expression);
            indexCache = cache.size();

        } catch (Exception e) {
            view.showError(e.getMessage());
            //e.printStackTrace();
        }
    }

    /**
     * nextCache() function.
     * 
     * Change cache index to next cached result.
     */
    public void nextCache() {
        if (indexCache > 0) {
            indexCache--;
            view.setJTextFieldInputText(cache.get(indexCache));
        }
    }

    /**
     * previousCache() function.
     * 
     * Change cache index to previous cached result.
     */
    public void previousCache() {
        if (indexCache + 1 < cache.size()) {
            indexCache++;
            view.setJTextFieldInputText(cache.get(indexCache));
        }

    }

}
