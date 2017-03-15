package ie.gmit.sw;

import java.util.*;

/**
 * Created by Ross Byrne on 14/03/17.
 * A singleton factory for getting a data source
 */
public class DataFactory {

    private static DataFactory fact = new DataFactory();
    private List<String> dataSourceNames = new ArrayList<>();

    private DataFactory(){

        // set the names of available data sources
        dataSourceNames.add("Test Data");
        dataSourceNames.add("SQL - Production");
    }

    // static method to get the instance of singleton factory
    public static DataFactory getInstance(){

        return fact;

    } // getInstance()

    public DataSourceable getDataSource(String name){

        DataSourceable dataSource = null;

        switch (name) {
            case "Test Data":

            // create the data source
            dataSource = new TestDataSourceImp();

            break;

            default:

                // create the data source
                dataSource = new TestDataSourceImp();
                break;
        } // switch

        // return the data source
        return dataSource;

    } // getDataSource()

    public List<String> getDataSourceNames(){

        return dataSourceNames;

    } // getDataSourceNames()

} // class
