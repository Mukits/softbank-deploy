package edu.dss.softbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.dss.softbank.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
