package com.api.repository;

import com.api.model.TestPhase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestPhase, Long> {
    
}
