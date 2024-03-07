package com.bogoliubova.training_service.entity;

import com.bogoliubova.training_service.entity.enums.AllRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id", columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID roleId;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private AllRoles roleName;

    @ManyToMany(mappedBy = "customerRoles")
    private Set<Customer> customers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authorities",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authoritySet;
}