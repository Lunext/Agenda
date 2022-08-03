package com.example.agenda.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="Agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agendaId", unique = true )
    private Integer id;

    @Column(name = "name", nullable = true, length = 50 )
    private String name;

    @Column (name="lastName", nullable= true, length = 80)

    private String lastName;

    @Column(name="phoneNumber", nullable= true)

    private String phoneNumber;

    @Column(name="email", nullable= true)
    @Email
    private String email;

    @Column(name="country", nullable= true)

    private String country;

    @Column(name="city", nullable= true)

    private String city;

    @Column(name="Identification", nullable= true)
    private String identification;

    public Agenda(Integer id, String name, String lastName, String phoneNumber, String email, String country, String city, String identification) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.country = country;
        this.city = city;
        this.identification = identification;
    }

    public Agenda() {
    }

    public Agenda(String name, String lastName, String phoneNumber, String email, String country, String city, String identification) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.country = country;
        this.city = city;
        this.identification = identification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
