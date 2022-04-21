package com.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "logs")
public class Log {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String project;

    @NotNull
    private int build;

    @ManyToOne
	@JoinColumn(name = "compile_id")
	private CompilePhase compile;

    @ManyToOne
	@JoinColumn(name = "test_id")
	private TestPhase test;

    @ManyToOne
	@JoinColumn(name = "package_id")
	private PackagePhase packag;

    public Log(int build) {
        this.build = build;
    }

    public Log(String project, int build, CompilePhase compile, TestPhase test, PackagePhase packag) {
        this.project = project;
        this.build = build;
        this.compile = compile;
        this.test = test;
        this.packag = packag;
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

    public CompilePhase getCompile() {
        return compile;
    }

    public void setCompile(CompilePhase compile) {
        this.compile = compile;
    }

    public TestPhase getTest() {
        return test;
    }

    public void setTest(TestPhase test) {
        this.test = test;
    }

    public PackagePhase getPackag() {
        return packag;
    }

    public void setPackag(PackagePhase packag) {
        this.packag = packag;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

}
