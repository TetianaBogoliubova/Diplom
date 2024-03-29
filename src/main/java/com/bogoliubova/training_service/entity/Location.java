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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID locationId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private int postalCode;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Teacher> teachers;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return postalCode == location.postalCode &&
                Objects.equals(locationId, location.locationId) &&
                Objects.equals(country, location.country) &&
                Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, country, city, postalCode);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", customers=" + customers +
                ", teachers=" + teachers +
                '}';
    }
}


