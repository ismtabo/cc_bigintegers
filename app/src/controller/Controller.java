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
            view.setJTextFieldInputText("");
            
            cache.add(expression);
            indexCache = cache.size();
            
        } catch (Exception e) {
            view.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    
    public void nextCache() {
        if (indexCache > 0 ){
            indexCache--;
            view.setJTextFieldInputText(cache.get(indexCache));
        }
    }
        
    public void previousCache() {
        if (indexCache+1 < cache.size()){
            indexCache++;
            view.setJTextFieldInputText(cache.get(indexCache));
        }

    }


}
