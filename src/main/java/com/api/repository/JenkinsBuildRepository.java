package com.api.repository;

import com.api.model.JenkinsBuild;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenkinsBuildRepository extends JpaRepository<JenkinsBuild, Long> {
    
}
