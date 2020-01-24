package com.proiect.proiect.models;

public class TeamModel {
    public Integer id;
    public String name;
    public Integer leaderId;

    public TeamModel(Integer id, String name, Integer leaderId) {
        this.id = id;
        this.name = name;
        this.leaderId = leaderId;
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

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }
}
