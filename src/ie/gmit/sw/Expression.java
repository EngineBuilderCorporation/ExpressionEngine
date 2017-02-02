package ie.gmit.sw;

/**
 * Created by Ross Byrne on 27/01/17.
 * An expression is made up of two parameters and an operator.
 * A parameter can also be an expression for nested expressions.
 */
public class Expression implements Expressionable {

    private Parameterable paramX;
    private Parameterable paramY;
    private Parameterable operator;

    public boolean setExpression(Parameterable x, Parameterable y, Parameterable operator){

        if(x.getParameterType() == ParameterType.EXPRESSION || y.getParameterType() == ParameterType.EXPRESSION){

           // if(operator.getParameter().toString())
        }

        return false;
    }

    public Parameterable getParamX() {

        return paramX;
    }

    public void setParamX(Parameterable paramX) {

        this.paramX = paramX;
    }

    public Parameterable getParamY() {

        return paramY;
    }

    public void setParamY(Parameterable paramY) {

        this.paramY = paramY;
    }

    public Parameterable getOperator() {
        return operator;
    }

    public void setOperator(Parameterable operator) {
        this.operator = operator;
    }

} // class
