package ie.gmit.sw;

import java.util.*;

/**
 * Created by Ross Byrne on 23/01/17.
 * Interface for any type of rule object
 */
public interface Ruleable {

    public void addParameter(Parameterable param);

    public Queue<Parameterable> getRuleParameters();

    public boolean computeRule();

} // interface
