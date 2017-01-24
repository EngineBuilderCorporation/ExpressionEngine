package ie.gmit.sw;

/**
 * Created by Ross Byrne on 23/01/17.
 * Interface for any of the parameters that make up a rule
 */
public interface Parameterable {

    public Object getParameter();

    public boolean setParameter(Object parameter, ParameterType type);

    public ParameterType getParameterType();

} // interface
