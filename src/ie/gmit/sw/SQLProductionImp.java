package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rossbyrne on 15/03/17.
 */
public class SQLProductionImp implements DataSourceable {

    private Map<String, Integer> data = new HashMap<>();

    public SQLProductionImp(){

        // add test data to map
        data.put("Current Country", 13);    // represents country code
        data.put("Ireland", 13);            // represents a selected country
        data.put("Threshold", 183);         // the tax threshold for that country
        data.put("Days Worked", 170);       // number of days worked in country
        data.put("Notification Limit", 146);// 80% of threshold days for country

    } // constructor

    // Set generic to be String and integer
    public Map<String, Integer> getData() {

        return this.data;

    } // getData()

} // class
