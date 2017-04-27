package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ross Byrne on 15/03/17.
 *
 * Simulates a data source. Information could be pulled from a SQL database
 * and placed in the data Map.
 */
public class SQLProductionImp implements DataSourceable {

    private Map<String, Integer> data = new HashMap<>();

    public SQLProductionImp(){

        // add test data to map
        data.put("Country Code", 13);    // represents country code
        data.put("Ireland", 13);            // represents a selected country
        data.put("Threshold 1", 183);         // the tax threshold for that country
        data.put("Days Worked", 170);       // number of days worked in country
        data.put("Threshold 6", 280);
        data.put("PAYE", 20);
        data.put("Minimum Threshold", 30);
        data.put("Tax Year", 2017);
        data.put("Current Year", 2017);
        data.put("Residency", 300);


    } // constructor

    // Set generic to be String and integer
    public Map<String, Integer> getData() {

        return this.data;

    } // getData()

} // class
