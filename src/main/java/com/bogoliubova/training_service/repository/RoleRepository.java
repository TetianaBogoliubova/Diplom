package com.bogoliubova.training_service.repository;

import com.bogoliubova.training_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
