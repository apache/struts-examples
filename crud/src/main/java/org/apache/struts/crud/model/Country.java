package org.apache.struts.crud.model;

/**
 * Model a country.
 * 
 * @author antonio sánchez
 */
public class Country {
    private String countryAbbr;
    private String countryName;

    public Country(String countryAbbr, String countryName) {
        this.countryAbbr = countryAbbr;
        this.countryName = countryName;
    }

    public void setCountryAbbr(String countryAbbr) {
        this.countryAbbr = countryAbbr;
    }

    public String getCountryAbbr() {
        return countryAbbr;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return getCountryAbbr();
    }
}
