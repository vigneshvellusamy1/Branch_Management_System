package com.branch.dao;

import com.branch.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<Login, Integer> {
    // Custom query methods can be added here
    Login findByManagerEmail(String managerEmail);
    boolean existsByManagerEmail(String managerEmail);
}