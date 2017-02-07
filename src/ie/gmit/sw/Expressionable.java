package ie.gmit.sw;

/**
 * Created by rossbyrne on 27/01/17.
 */
public interface Expressionable {

    public boolean evaluate();

    public boolean setExpression(Parameterable x, Parameterable y, Parameterable operator);
}
