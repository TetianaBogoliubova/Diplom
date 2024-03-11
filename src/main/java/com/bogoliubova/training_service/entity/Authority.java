package com.bogoliubova.training_service.entity;


import com.bogoliubova.training_service.entity.enums.AllAuthorities;
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
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "authority_id", columnDefinition = "UUID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID authorityId;

    @Column(name = "authority_name")
    @Enumerated(EnumType.STRING)
    private AllAuthorities authorityName;

    @ManyToMany(mappedBy = "authoritySet")
    private Set<Role> roleSet;

}

