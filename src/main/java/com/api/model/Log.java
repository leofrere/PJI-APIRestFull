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
	private Compile compile;

    @ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;

    @ManyToOne
	@JoinColumn(name = "package_id")
	private Package packag;

    public Log() {
    }

    public Log(String project, int build, Compile compile, Test test, Package packag) {
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

    public Compile getCompile() {
        return compile;
    }

    public void setCompile(Compile compile) {
        this.compile = compile;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Package getPackag() {
        return packag;
    }

    public void setPackag(Package packag) {
        this.packag = packag;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

}
