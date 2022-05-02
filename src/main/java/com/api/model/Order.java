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
    private String name;

    @NotNull
    private String project;

    @NotNull
    private int build;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

}
