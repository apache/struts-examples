package org.apache.struts.crud.dao;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts.crud.model.Country;

/**
 * TODO I18e database text data
 * 
 * @author antonio sanchez
 */
public class MemoryPersonSupportDao implements PersonSupportDao {
    private static Country[] countries;
    private static Map<String, Country> countriesMap;
    private static String[] genders = {"male", "female"};
    private static String[] sports = {"football", "baseball", "basketball", "mtb" };
    private static String [] carModelsAvailable = {"Ford", "Chrysler", "Toyota", "Nissan", "Seat"};

    static {
        countries = new Country[] {new Country("ES", "Spain"),
                    new Country("IE", "Ireland"),
                    new Country("IT", "Italy"),
                    new Country("PL", "Poland"),
                    new Country("US", "Usa") };
        
        countriesMap = new HashMap<>();
        for (Country c : countries) {
            countriesMap.put(c.getCountryAbbr(), c);
        }
    }

//    @Override
//    public Map<String, Country> getCountriesMap() {
//        return countriesMap;
//    }

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
}
