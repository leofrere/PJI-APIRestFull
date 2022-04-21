package com.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String project;

    @NotNull
    private int build;

    @ManyToOne
	@JoinColumn(name = "compile_id")
	private CompilePhase compilePhase;

    @ManyToOne
	@JoinColumn(name = "test_id")
	private TestPhase testPhase;

    @ManyToOne
	@JoinColumn(name = "package_id")
	private PackagePhase packagePhase;

    public Order(int build) {
        this.build = build;
    }

    public Order(String project, int build, CompilePhase compile, TestPhase test, PackagePhase packag) {
        this.project = project;
        this.build = build;
        this.compilePhase = compile;
        this.testPhase = test;
        this.packagePhase = packag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

}
