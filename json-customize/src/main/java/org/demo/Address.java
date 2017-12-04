package org.demo;

import java.util.List;

public class Address {
    private String name, street, city, state;
    private List<Zipcode> zipcodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Zipcode> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(List<Zipcode> zipcodes) {
        this.zipcodes = zipcodes;
    }
}
