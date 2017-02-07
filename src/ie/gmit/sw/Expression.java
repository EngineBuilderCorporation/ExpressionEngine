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

    public boolean evaluate(){

        boolean result = false;
        Expressionable ex1 = null;
        Expressionable ex2 = null;

        switch(operator.getParameter().toString()){

            case "&&":

                ex1 = (Expressionable) paramX.getParameter();
                ex2 = (Expressionable) paramY.getParameter();

                result = (ex1.evaluate() && ex2.evaluate());

                break;
            case "||":

                 ex1 = (Expressionable) paramX.getParameter();
                 ex2 = (Expressionable) paramY.getParameter();

                result = (ex1.evaluate() || ex2.evaluate());

                break;
            case ">":

                result = (Double.parseDouble(paramX.getParameter().toString()) > Double.parseDouble(paramY.getParameter().toString()));
                break;
            case "<":

                result = (Double.parseDouble(paramX.getParameter().toString()) < Double.parseDouble(paramY.getParameter().toString()));
                break;
            case "==":

                if(paramX.getParameterType() == ParameterType.NUMBER) {

                    result = (Double.parseDouble(paramX.getParameter().toString()) == Double.parseDouble(paramY.getParameter().toString()));

                } else {

                    ex1 = (Expressionable) paramX.getParameter();
                    ex2 = (Expressionable) paramY.getParameter();

                    result = (ex1.evaluate() == ex2.evaluate());
                } // if

                break;
        } // switch


        return result;
    }

    public boolean setExpression(Parameterable x, Parameterable y, Parameterable operator){

//        if(x.getParameterType() == ParameterType.EXPRESSION || y.getParameterType() == ParameterType.EXPRESSION){
//
//           // if(operator.getParameter().toString())
//        }

        this.paramX = x;
        this.paramY = y;
        this.operator = operator;

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
