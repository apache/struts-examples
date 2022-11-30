package org.apache.struts.register.model;

import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private List<Integer> options;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public String toString() {
        return "First Name: " + getFirstName() +
                " Last Name: " + getLastName() +
                " Email: " + getEmail() +
                " Age: " + getAge() +
                " Options: " + getOptions();
    }
}
