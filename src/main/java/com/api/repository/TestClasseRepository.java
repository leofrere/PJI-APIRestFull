package com.api.repository;

import com.api.model.TestClasse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestClasseRepository extends JpaRepository<TestClasse, Long> {

}
