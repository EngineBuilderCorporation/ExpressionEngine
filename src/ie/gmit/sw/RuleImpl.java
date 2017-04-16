package ie.gmit.sw;

/**
 * Created by Ross Byrne on 23/01/17.
 */
public class RuleImpl implements Ruleable {

    private Expressionable expression; // this will be the root expression in expression tree.

    public void setExpression(Expressionable expression){

        this.expression = expression;
    }

    // computes if the rule equals true or false
    public boolean computeRule(){

        return expression.evaluate();
    }

} // class
