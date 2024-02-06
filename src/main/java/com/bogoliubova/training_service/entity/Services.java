package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllServices;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@UuidGenerator
    @Column(name = "service_id")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID serviceId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AllServices type;

    @Column(name = "s_price")
    private BigDecimal servicePrice;

    @OneToMany(mappedBy = "services", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Direction> directions;

    @OneToMany(mappedBy = "services", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return Objects.equals(serviceId, services.serviceId) && type == services.type && Objects.equals(servicePrice, services.servicePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, type, servicePrice);
    }

    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", type=" + type +
                ", servicePrice=" + servicePrice +
                ", directions=" + directions +
                ", books=" + books +
                '}';
    }
}
