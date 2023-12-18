package com.bogoliubova.training_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "direction_id")
    private Direction direction;

    @Column(name = "location_id")
    private Location location;

    @Column(name = "c_email")
    private String cusEmail;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(cusEmail, customer.cusEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, cusEmail);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", directionId=" + direction +
                ", locationId=" + location +
                ", email='" + cusEmail + '\'' +
                '}';
    }
}
