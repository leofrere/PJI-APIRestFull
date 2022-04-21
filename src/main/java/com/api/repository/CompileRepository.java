package com.api.repository;

import org.springframework.stereotype.Repository;

import com.api.model.CompilePhase;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompileRepository extends JpaRepository<CompilePhase, Long> {

}
