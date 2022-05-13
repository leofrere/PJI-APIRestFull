package com.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.api.model.abstracts.Model;

@Entity
@Table(name = "orders")
public class Order extends Model {

    @NotNull
    private String name;

    private float timeOfBuild;

    @ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "compile_id")
	private CompilePhase compilePhase;

    @ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "test_id")
	private TestPhase testPhase;

    @ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "package_id")
	private PackagePhase packagePhase;

    public Order() {
    }

    public Order(CompilePhase compile, TestPhase test, PackagePhase packagePhase, String name, String project, int build) {
        this.compilePhase = compile;
        this.testPhase = test;
        this.packagePhase = packagePhase;
        this.name = name;
        this.project = project;
        this.build = build;
    }

    public CompilePhase getCompilePhase() {
        return compilePhase;
    }

    public void setCompilePhase(CompilePhase compile) {
        this.compilePhase = compile;
    }

    public TestPhase getTestPhase() {
        return testPhase;
    }

    public void setTestPhase(TestPhase test) {
        this.testPhase = test;
    }

    public PackagePhase getPackagePhase() {
        return packagePhase;
    }

    public void setPackagePhase(PackagePhase packag) {
        this.packagePhase = packag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTimeOfBuild() {
        return timeOfBuild;
    }

    public void setTimeOfBuild(float timeOfBuild) {
        this.timeOfBuild = timeOfBuild;
    }

}
