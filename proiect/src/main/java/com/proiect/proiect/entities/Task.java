package com.proiect.proiect.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;

    @OneToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "statusId")
    private Status status;

    @OneToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "userId")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
