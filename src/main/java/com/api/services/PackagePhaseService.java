package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.PackagePhase;
import com.api.repository.PackageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
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

    @GraphQLQuery(name = "packagePhases")
    public List<PackagePhase> getAllPackagePhase() {
        return packageRepository.findAll();
    }

    @GraphQLQuery(name = "packagePhasesByProject")
    public List<PackagePhase> getPackagePhaseByProject(@GraphQLArgument(name = "project") String projectName) {
        List<PackagePhase> packagePhases = packageRepository.findAll();
        List<PackagePhase> packagePhasesToReturn = new LinkedList<PackagePhase>();
        for (PackagePhase packagePhase : packagePhases) {
            if (packagePhase.getProject().equals(projectName)) {
                packagePhasesToReturn.add(packagePhase);
            }
        }
        return packagePhasesToReturn;
    }

    @GraphQLQuery(name = "packagePhasesOfTime")
    public List<PackagePhase> getPackagePhaseByTime(@GraphQLArgument(name = "time") String time, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName) {
        List<PackagePhase> packagePhases = packageRepository.findAll();
        List<PackagePhase> packagePhasesToReturn = new LinkedList<PackagePhase>();
        for (PackagePhase packagePhase : packagePhases) {
            if(packagePhase.getProject().equals(projectName)){
                cmpPackagePhase(Float.parseFloat(time), packagePhase.getTimeFloat(), op, packagePhasesToReturn, packagePhase);
            }
        }
        return packagePhasesToReturn;
    }

    private void cmpPackagePhase(Float a, Float b, String op, List<PackagePhase> list, PackagePhase testClasse) {
        switch(op) {
            case ">":
                if (a > b) {
                    list.add(testClasse);
                }
                break;
            case "<":
                if (a < b) {
                    list.add(testClasse);
                }
                break;
            case "=":
                if (a == b) {
                    list.add(testClasse);
                }
        }
    }

}
