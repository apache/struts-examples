package org.apache.struts.crud.dao;

//import java.util.Map;
import org.apache.struts.crud.model.Country;

/**
 * @author antonio sanchez
 */
public interface PersonSupportDao {

    Country[] getCountries();

//    Map<String, Country> getCountriesMap();
    
    String[] getCarModels();
    
    String[] getSports();
    
    String[] getGenders();
    
}
