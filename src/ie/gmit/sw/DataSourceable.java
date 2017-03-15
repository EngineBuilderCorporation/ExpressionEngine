package ie.gmit.sw;

import java.util.Map;

/**
 * Created by Ross Byrne on 14/03/17.
 * Interface for getting data from a data source, returned in a map.
 * Method uses generics so any values can be used in map.
 */
public interface DataSourceable {

    // use generics so anything can be returned
    public <K, V> Map<K, V> getData();

    // methods for setting and getting the name of the data source
    public String getName();
    public void setName(String name);

} // interface
