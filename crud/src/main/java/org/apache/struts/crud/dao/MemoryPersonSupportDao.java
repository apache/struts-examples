package org.apache.struts.crud.dao;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts.crud.model.Country;

/**
 * @author bruce phillips
 * @author antonio sanchez
 */
public class MemoryPersonSupportDao implements PersonSupportDao {
    private static final Country[] countries;
    private static final Map<String, Country> countriesMap;
    private static final String[] genders = {"male", "female"};
    private static final String[] sports = {"football", "baseball", "basketball", "mtb" };
    private static final String [] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan", "Seat"};

    static {
        countries = new Country[] {new Country("ES", "Spain"),
                    new Country("IE", "Ireland"),
                    new Country("IT", "Italy"),
                    new Country("PL", "Poland"),
                    new Country("US", "Usa") };
        
        countriesMap = new HashMap<>();
        for (Country c : countries) {
            countriesMap.put(c.getCountryId(), c);
        }
    }



    @Override
    public String[] getCarModels() {
        return carModelsAvailable;
    }

    @Override
    public String[] getSports() {
        return sports;
    }

    @Override
    public String[] getGenders() {
        return genders;
    }

    @Override
    public Country[] getCountries() {
        return countries;
    }
    
    static Country getCountry(String countryId) {
        return countriesMap.get(countryId);
    }
}
