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
    
    
    private static final int RESET = -1;
    private static final int EQUAL = 0;
    private static final int ADD = 1;
    private static final int SUBTRACT = 2;
    private static final int MULTIPLY = 3;
    private static final int DIVIDE = 4;
    private static final int MOD = 5;
    private static final int POW = 6;

    private View view;
    private BigInteger cacheNumber;
    private int lastOp;
    
    public Controller(View view){
        this.view = view;
        reset();
    }
    
    
    public void setCacheNumber(BigInteger number){
        cacheNumber = number;
    }
    
    
    public void setOp(int op){
        lastOp = op;
    }
    
    
    public BigInteger getCacheNumber(){
        return cacheNumber;
    }
    
    
    public void add(){
        
        BigInteger numA = new BigInteger(view.getJTextFieldInputText());
        
    }
    
    public void reset(){
        setCacheNumber(BigInteger.ZERO);
        setOp(RESET);
        update();
    }
    
    public void update(){
        view.setJTextFieldInputText(getCacheNumber().toString());
    }
}
