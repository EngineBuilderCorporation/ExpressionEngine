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

    // recursive method to evaluate the expression
    public boolean evaluate(){

        boolean result = false;
        Expressionable ex1 = null;
        Expressionable ex2 = null;

        switch((String)operator.getParameter()){

            case "AND":

                // get the expressions from the parameters
                ex1 = (Expressionable) paramX.getParameter();
                ex2 = (Expressionable) paramY.getParameter();

                // evaluate those expresssions and then check if AND is true or false
                result = (ex1.evaluate() && ex2.evaluate());

                break;
            case "OR":

                // get the expressions from the parameters
                ex1 = (Expressionable) paramX.getParameter();
                ex2 = (Expressionable) paramY.getParameter();

                // evaluate expressions and then check if OR is true or false
                result = (ex1.evaluate() || ex2.evaluate());

                break;
            case ">":

                // parse the number values from parameters and evaluate if one is greater then the other
                result = (Double.parseDouble(paramX.getParameter().toString()) > Double.parseDouble(paramY.getParameter().toString()));
                break;
            case "<":

                // parse the number values from parameters and evaluate if one is less then the other
                result = (Double.parseDouble(paramX.getParameter().toString()) < Double.parseDouble(paramY.getParameter().toString()));
                break;
            case "==":

                // check of parameter type is a number or expression
                if(paramX.getParameterType() == ParameterType.NUMBER) { // if number

                    // parse number values and evaluate if numbers are equal
                    result = (Double.parseDouble(paramX.getParameter().toString()) == Double.parseDouble(paramY.getParameter().toString()));

                } else { // if an expression

                    // get the expressions from the parameters
                    ex1 = (Expressionable) paramX.getParameter();
                    ex2 = (Expressionable) paramY.getParameter();

                    // evaluate expressions and check if they are equal
                    result = (ex1.evaluate() == ex2.evaluate());
                } // if

                break;
        } // switch

        System.out.println("Expression with: " + operator.getParameter().toString() + " is: " + result);

        return result;
    }

    public boolean setExpression(Parameterable x, Parameterable y, Parameterable operator){

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
