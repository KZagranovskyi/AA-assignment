package com.abnamro.customer;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String dateOfBirth;
    private String idDocument;
    private String username;
    private String iban;
    private String password;

    // Constructor
    public Customer(String name, String address, String dateOfBirth, String idDocument, String username, String iban, String password) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.idDocument = idDocument;
        this.username = username;
        this.iban = iban;
        this.password=password;
    }

    public Customer() {

    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public String getUsername() {
        return username;
    }

    public String getIban() {
        return iban;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                name.equals(customer.name) &&
                address.equals(customer.address) &&
                dateOfBirth.equals(customer.dateOfBirth) &&
                idDocument.equals(customer.idDocument) &&
                username.equals(customer.username) &&
                iban.equals(customer.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, dateOfBirth, idDocument, username, iban);
    }
}