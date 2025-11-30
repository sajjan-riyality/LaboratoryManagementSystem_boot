package com.lab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Role;
import com.lab.entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

	Optional<Role> findByName(RoleType rolePatient);
}
