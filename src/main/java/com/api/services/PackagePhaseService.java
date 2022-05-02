package com.api.services;

import java.io.BufferedReader;

import com.api.model.PackagePhase;
import com.api.repository.PackageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackagePhaseService {
    
    @Autowired
    private PackageRepository packageRepository;

    public PackagePhase getPackagePhaseById(long id) {
        return packageRepository.findById(id).get();
    }

    public PackagePhase addPackagePhase(BufferedReader reader, String projectName, int build) {
        PackagePhase packagePhase = new PackagePhase(reader);
        packagePhase.setProject(projectName);
        packagePhase.setBuild(build);
        return packageRepository.save(packagePhase);
    }

}
