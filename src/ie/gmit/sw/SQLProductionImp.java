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
        data.put("Cars Owned", 40);
        data.put("Boats Owned", 10);
        data.put("Tractors Owned", 20);
        data.put("Motorcycles Owned", 15);
        data.put("Bicycles Owned", 22);

    } // constructor

    // Set generic to be String and integer
    public Map<String, Integer> getData() {

        return this.data;

    } // getData()

} // class
