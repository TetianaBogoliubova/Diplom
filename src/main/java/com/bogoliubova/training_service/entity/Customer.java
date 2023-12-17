package com.bogoliubova.training_service.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Customer {

    @Id
    private UUID customerId;
    private String firstName;
    private String lastName;
    private Direction directionId;
    private Location locationId;
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
                ", directionId=" + directionId +
                ", locationId=" + locationId +
                ", email='" + cusEmail + '\'' +
                '}';
    }
}
