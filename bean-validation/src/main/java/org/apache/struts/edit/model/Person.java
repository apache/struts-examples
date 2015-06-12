package org.apache.struts.edit.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;


/**
 * Models a Person who registers.
 * @author bruce phillips
 *
 */
public class Person
{
	
	@NotBlank(message="firstName.required")
    private String firstName;
	
	@NotBlank(message="lastName.required")
    private String lastName;
    private String sport;
    private String gender;
    private String residency;
    private boolean over21;
    
    @NotEmpty(message="carModels.required")
    private String [] carModels;
    
    @Size(min=1, message="email.required")
    @Email(message="email.format")
    private String email;
    
    @Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="phoneNumber.required")
    private String phoneNumber;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
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

	public String getSport() {
		return sport;
	}

	public void setResidency(String residency) {
		this.residency = residency;
	}

	public String getResidency() {
		return residency;
	}

	public void setOver21(boolean over21) {
		this.over21 = over21;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setCarModels(String [] carModels) {
		this.carModels = carModels;
	}

	public String [] getCarModels() {
		return carModels;
	}

	public String toString()
    {
        return "First Name: " + getFirstName() + " | " +
        " Last Name:  " + getLastName() + " | " +
        " Favorite Sport: " + getSport() + " | " +
        " Gender: " + getGender() + " | " +  
        " Residency: " + getResidency() + " | " +
        " Over 21: " + isOver21()  + " | " +
        " Car models: " + Arrays.asList( getCarModels() ) + " | " +
        " Email: " + getEmail() + " | " +
        " Phone: " + getPhoneNumber();
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
}
