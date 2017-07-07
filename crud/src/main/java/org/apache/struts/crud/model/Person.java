package org.apache.struts.crud.model;

import java.util.Arrays;

/**
 * Models a Person who registers.
 * 
 * Person is Cloneable just an implemention technique for in-memory daos.
 *
 * @author bruce phillips
 * @author antonio sanchez
 */
public class Person implements Cloneable {
    private Integer personId;
    private String firstName;
    private String lastName;
    private String sport;
    private String gender;
    private Country country = new Country("", "");
    private boolean over21;
    private String[] carModels;
    private String email;
    private String phoneNumber;
    
    public Person()  {
    
    }
    
    public Person(Integer id, String firstName, String lastName, String sport, 
                String gender, Country country, boolean over21, String[] carModels, 
                String email, String phoneNumber) {
        this.personId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.gender = gender;
        this.country = country;
        this.over21 = over21;
        this.carModels = carModels;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setOver21(boolean over21) {
        this.over21 = over21;
    }

    public boolean isOver21() {
        return over21;
    }

    public void setCarModels(String[] carModels) {
        this.carModels = carModels;
    }

    public String[] getCarModels() {
        return carModels;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Id: " + getPersonId() + " | "
                + "First Name: " + getFirstName() + " | "
                + " Last Name:  " + getLastName() + " | "
                + " Favorite Sport: " + getSport() + " | "
                + " Gender: " + getGender() + " | "
                + " Country: " + getCountry() + " | "
                + " Over 21: " + isOver21() + " | "
                + " Car models: " + Arrays.asList(getCarModels()) + " | "
                + " Email: " + getEmail() + " | "
                + " Phone: " + getPhoneNumber();
    }
}
