package com.adkr38.app.models;
import jakarta.persistence.*;
import java.util.*;

import static jakarta.persistence.FetchType.EAGER;

/**
 * Entity class for representing a User in the database
 */
@Entity
public class User {
    /**
     * The unique identifier for the user
     */
    @Id
    /**
     * The id field is generated automatically by the database
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user (To be used as username)
     */
    private String name;

    /**
     * The email of the user
     */
    private String email;


    /**
     * The password used to log in
     */
    private String password;

    /**
     * The phone number of the user
     */
    @Column(name="phone_number")
    private String phoneNumber;

    /**
     * The roles that the user has
     */
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(fetch = EAGER, mappedBy = "user")
    List<Appointment> appointments;


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

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }
  
    public void setPassword(String password) {
      this.password = password;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public Collection<Role> getRoles() {
      return roles;
    }

    public void setRoles(Collection<Role> roles) {
      this.roles = roles;
    }

    public List<Appointment> getAppointments() {
      return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
      this.appointments = appointments;
    }

  public User(String name, String password, String email, String phoneNumber){
    setName(name);
    setPassword(password);
    setEmail(email);
    setPhoneNumber(phoneNumber);
  }

}
