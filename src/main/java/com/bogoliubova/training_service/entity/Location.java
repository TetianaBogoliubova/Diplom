package com.bogoliubova.training_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class Location {
    @Getter
    @Setter
    private UUID id;
    private String country;
    private String city;
    private int postalCode;

    public Location() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return postalCode == location.postalCode && Objects.equals(id, location.id) && Objects.equals(country, location.country) && Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, postalCode);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
