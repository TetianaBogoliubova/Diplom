package com.bogoliubova.training_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    //@UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "c_email")
    private String cusEmail;

    @OneToOne()
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @JsonIgnore
    private Location location;

//    @OneToOne
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private Direction direction;

//    @OneToMany//(fetch = FetchType.LAZY, orphanRemoval = true, cascade = {MERGE, PERSIST, REFRESH})
//    @JoinColumn(name = "direction_id", referencedColumnName = "direction_id")
//    private List<Direction> directions;

    @OneToMany
    @JoinColumn(name = "direction_id")
    @JsonIgnore
    private List<Direction> directions;

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
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cusEmail='" + cusEmail + '\'' +
                ", location=" + location +
                ", directions=" + directions +
                '}';
    }
}
