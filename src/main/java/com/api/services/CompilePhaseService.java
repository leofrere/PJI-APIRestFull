package com.api.services;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.api.model.CompilePhase;
import com.api.repository.CompileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class CompilePhaseService {
    
    @Autowired
    private CompileRepository compileRepository;

    public CompilePhase getCompilePhaseById(long id) {
        return compileRepository.findById(id).get();
    }

    public CompilePhase addCompilePhase(BufferedReader reader, String projectName, int build, String module, boolean timeIsSet) {
        CompilePhase compilePhase = new CompilePhase(reader, timeIsSet);
        compilePhase.setProject(projectName);
        compilePhase.setBuild(build);
        compilePhase.setModule(module);
        return compileRepository.save(compilePhase);
    }

    public void deleteCompilePhase(long id) {
        compileRepository.deleteById(id);
    }

    @SuppressWarnings("unchecked")
    @GraphQLQuery(name = "compilePhasesTimeByCompiledClass")
    public Map<String,Float>[] getCompilePhasesTimeByCompiledClass(@GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<CompilePhase> compilePhases = getCompilePhaseByProject(projectName, module);
        Map<String,Float>[] map = new HashMap[compilePhases.size()];

        for(int i = 0; i < compilePhases.size(); i++) {
            map[i] = new HashMap<String,Float>();
            map[i].put("build", (float) compilePhases.get(i).getBuild());
            map[i].put("time", (float) compilePhases.get(i).getTimeFloat() / compilePhases.get(i).getCompiledClasses());
        }
        return map;
    }

    @GraphQLQuery(name = "compilePhases")
    public List<CompilePhase> getAllCompilePhases() {
        return compileRepository.findAll();
    }

    @GraphQLQuery(name = "compilePhasesByProject")
    public List<CompilePhase> getCompilePhaseByProject(@GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<CompilePhase> compilePhases = compileRepository.findAll();
        List<CompilePhase> list = new LinkedList<CompilePhase>();
        for (CompilePhase compilePhase : compilePhases) {
            if (compilePhase.getProject().equals(projectName) && (compilePhase.getModule().equals(module) || module.equals(""))) {
                list.add(compilePhase);
            }
        }
        return list;
    }

    @GraphQLQuery(name = "compilePhaseByTime")
    public List<CompilePhase> getCompilePhaseByTime(@GraphQLArgument(name = "time") String time, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<CompilePhase> compilePhases = compileRepository.findAll();
        List<CompilePhase> list = new LinkedList<CompilePhase>();
        for (CompilePhase compilePhase : compilePhases) {
            if (compilePhase.getProject().equals(projectName) && compilePhase.getModule().equals(module)) {
                cmpCompile(Float.parseFloat(time), compilePhase.getTimeFloat(), op, list, compilePhase);
            }
        }
        return list;
    }

    @GraphQLQuery(name = "compilePhaseByCompiled")
    public List<CompilePhase> getCompilePhaseByCompiled(@GraphQLArgument(name = "compiled") int compiled, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<CompilePhase> compilePhases = compileRepository.findAll();
        List<CompilePhase> list = new LinkedList<CompilePhase>();
        for (CompilePhase compilePhase : compilePhases) {
            if (compilePhase.getProject().equals(projectName) && compilePhase.getModule().equals(module)) {
                cmpCompile((float) compiled,(float) compilePhase.getCompiledClasses(), op, list, compilePhase);
            }
        }
        return list;
    }

    private void cmpCompile(Float a, Float b, String op, List<CompilePhase> compilePhases, CompilePhase compilePhase) {
        switch(op) {
            case ">":
                if (a > b) {
                    compilePhases.add(compilePhase);
                }
                break;
            case "<":
                if (a < b) {
                    compilePhases.add(compilePhase);
                }
                break;
            case "=":
                if (a == b) {
                    compilePhases.add(compilePhase);
                }
        }
    }

}
