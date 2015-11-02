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
    private BigInteger cacheNumberOp;

    private int lastOp;
    
    
    public Controller(View view){
        this.view = view;
        reset();
    }
    
    
    public void setCacheNumberOp(BigInteger number){
        cacheNumberOp = number;
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
    
    public BigInteger getCacheNumberOp(){
        return cacheNumberOp;
    }
    
    public int getLastOp(){
        return lastOp;
    }
    
    
    public void addOp(){
        setCacheNumber(cacheNumber.add(getCacheNumberOp()));
        System.out.println(cacheNumber);
    }
    
    private void multiplyOp() {
        setCacheNumber(cacheNumber.multiply(getCacheNumberOp()));
        System.out.println(cacheNumber);
    }
    
    public void add(){
        
        setOp(ADD);
        BigInteger bi = new BigInteger(view.getJTextFieldInputText());
        if ((getCacheNumberOp() == null) || !getCacheNumberOp().equals(bi)){
            setCacheNumberOp(bi);
            update();
        }

    }
    
    public void multiply() {
        setOp(MULTIPLY);

        BigInteger bi = new BigInteger(view.getJTextFieldInputText());  
        if ((getCacheNumberOp() == null) || !getCacheNumberOp().equals(bi)){
            setCacheNumberOp(bi);
            if (!getCacheNumber().equals(BigInteger.ZERO)){
                update();
            }
        }

        
    }
    public void reset(){
        setOp(RESET);
        setCacheNumber(BigInteger.ZERO);

        update();
    }
    
   
    public void update(){
        doLastOp();
        view.setJTextFieldInputText(getCacheNumber().toString());
    }

    private void doLastOp() {
        switch(getLastOp()){
            case ADD:
                addOp();
                break;
                
            case MULTIPLY:
                multiplyOp();
                break;
        }
    }

    public void equal() {
        BigInteger bi = new BigInteger(view.getJTextFieldInputText());
        if ((getCacheNumber() == null) || getCacheNumber().equals(BigInteger.ZERO)){
            setCacheNumber(bi);
        }
        update();
    }
}
