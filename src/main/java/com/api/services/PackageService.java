package com.api.services;

import java.io.BufferedReader;

import com.api.model.Package;
import com.api.repository.PackageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    
    @Autowired
    private PackageRepository packageRepository;

    public Package getPackageById(long id) {
        return packageRepository.findById(id).get();
    }

    public Package addPackage(BufferedReader reader) {
        return packageRepository.save(new Package(reader));
    }

}