package ie.gmit.sw;

import java.util.*;

/**
 * Created by Ross Byrne on 23/01/17.
 */
public class RuleImpl implements Ruleable {

    private Queue<Parameterable> ruleSections = new LinkedList<>();

    public void addParameter(Parameterable param){

        // add parameter to rule
        ruleSections.add(param);

    } // addParameter()

    public Queue<Parameterable> getRuleParameters(){

        return ruleSections;

    } // getRuleParameters()

    // computes if the rule equals true or false
    public boolean computeRule(){

        return false;
    }

} // class
