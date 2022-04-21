package com.api.repository;

import com.api.model.PackagePhase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackagePhase, Long> {

}
