package ie.gmit.sw;

/**
 * Created by Ross Byrne on 14/03/17.
 * A singleton factory for getting a data source
 */
public class DataFactory {

    private static DataFactory fact = new DataFactory();

    private DataFactory(){}

    // static method to get the instance of singleton factory
    public static DataFactory getInstance(){

        return fact;

    } // getInstance()



} // class
