package org.apache.struts.crud.dao;

//import java.util.Map;
import org.apache.struts.crud.model.Country;

/**
 * Methods a PersonSupportDao class must implement to provide
 * additional information related to a Person.
 * 
 * @author bruce phillips
 * @author antonio sánchez
 */
public interface PersonSupportDao {

    Country[] getCountries();
    
    String[] getCarModels();
    
    String[] getSports();
    
    String[] getGenders();
    
}
