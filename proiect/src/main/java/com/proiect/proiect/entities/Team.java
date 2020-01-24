package com.proiect.proiect.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @OneToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "leaderId")
    private User leader;

    @OneToMany(mappedBy = "team")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Project> projects;

    @OneToMany(mappedBy = "team")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> members;

    public Team(){

    }

    public Team(String name, Optional<User> leader) {
        this.leader = leader.get();
        this.name = name;
    }

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

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
