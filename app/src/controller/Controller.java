/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigInteger;
import view.View;

/**
 *
 * @author garciparedes
 */
public class Controller {
    
    
    private static final String EQUAL = "=";
    private static final String ADD = " + ";
    private static final String SUBTRACT = " - ";
    private static final String MULTIPLY = " x ";
    private static final String DIVIDE = " / ";
    private static final String MOD = " % ";
    private static final String POW = " ^ ";

    private View view;
    
    private BigInteger cacheNumber;
    
    
    public Controller(View view){
        this.view = view;
        reset();
    }
    
    
    public void setCacheNumber(BigInteger number){
        cacheNumber = number;
    }
    
    
    
    public BigInteger getCacheNumber(){
        return cacheNumber;
    }
    
    
    public void addOp(){
        //setCacheNumber(cacheNumber.add(getCacheNumberOp()));
        System.out.println(cacheNumber);
    }
    
    private void multiplyOp() {
        //setCacheNumber(cacheNumber.multiply(getCacheNumberOp()));
        System.out.println(cacheNumber);
    }
    
    public void add(){
        view.appendJTextFieldInputText(ADD);
    }
    
    public void subtract(){
        view.appendJTextFieldInputText(SUBTRACT);

    }
    
    public void multiply() {
        view.appendJTextFieldInputText(MULTIPLY);
    }
    
    public void divide() {
        view.appendJTextFieldInputText(DIVIDE);
    }
    
    public void mod() {
        view.appendJTextFieldInputText(MOD);
    }
    
    public void pow() {
        view.appendJTextFieldInputText(POW);
    }
    
    public void reset(){
        setCacheNumber(BigInteger.ZERO);
        update();
    }
    
   
    public void update(){
        view.setJTextFieldInputText(getCacheNumber().toString());
    }
    

    public void equal() {

    }

    public void clean() {
        if (view.getJTextFieldInputText().equals("0")){
            view.setJTextFieldInputText("");
        }
    }
}
