package org.apache.struts.example.jasperreports.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person {
  private Long id;

  private String name;

  private String lastName;

  private String registerDate;

  public Person() {}

  public Person(Long id, String name, String lastName, LocalDateTime registerDate) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.registerDate = registerDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(String registerDate) {
    this.registerDate = registerDate;
  }
}
