package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ross Byrne on 14/03/17.
 * A test class to demonstate how the data source interface
 * can be implemented with a set of data.
 * Data can be taken from external sources such as a database.
 */
public class TestDataSourceImp implements DataSourceable {

    private Map<String, Integer> data = new HashMap<>();

    public TestDataSourceImp(){

        // add test data to map
        data.put("Days in Country", 100);
        data.put("Days Worked", 90);
        data.put("Projects Completed", 20);
        data.put("Sick Days", 1);
        data.put("Phone Calls", 345);
        data.put("Days Late", 3);
        data.put("Books Used", 10);
        data.put("Family Visits", 5);

    }

    // Set generic to be String and integer
    public Map<String, Integer> getData() {

        return this.data;

    } // getData()

} // class
