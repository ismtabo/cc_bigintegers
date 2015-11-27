package collection.tree;

/**
 * Created by garciparedes on 27/11/15.
 */

import java.math.BigInteger;

/**
 * Class that represents numbers in the tree.
 */
public class NodeVar extends NodeExpression {



    String varName;



    public NodeVar(String value){
        super(null, null, null);
        this.varName = value.toUpperCase();
    }



    @Override
    public BigInteger operate() throws IllegalStateException{
        if (getVar(varName) == null){
            throw new IllegalStateException("Variable '" + varName + "' no definida");
        }
        return getVar(varName).operate();
    }

    private ExpresionTree getVar(String varName) {

        return ExpresionTree.getVar(varName);
    }


    @Override
    public String toString() {
        if (getVar(varName)== null){
            return varName;
        }

        return getVar(varName).toString();
    }
}
