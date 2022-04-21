package com.api.services;

import java.io.BufferedReader;

import com.api.model.PackagePhase;
import com.api.repository.PackageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    
    @Autowired
    private PackageRepository packageRepository;

    public PackagePhase getPackageById(long id) {
        return packageRepository.findById(id).get();
    }

    public PackagePhase addPackage(BufferedReader reader) {
        return packageRepository.save(new PackagePhase(reader));
    }

    public void deletePackage(long id) {
        packageRepository.deleteById(id);
    }

}
