package com.bogoliubova.training_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    @Getter
    @Setter

    private UUID id;
    private String firstName;
    private String lastName;
    private Direction directionId;
    private Location locationId;
    private String email;

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", directionId=" + directionId +
                ", locationId=" + locationId +
                ", email='" + email + '\'' +
                '}';
    }
}
