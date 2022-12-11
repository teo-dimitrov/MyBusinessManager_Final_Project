package com.example.mybusinessmanager_final_project.model.service;

import java.time.LocalDateTime;

public class OrderEditServiceModel {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadLine;
    private String Creator;

    public Long getId() {
        return id;
    }

    public OrderEditServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderEditServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderEditServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderEditServiceModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public String getCreator() {
        return Creator;
    }

    public OrderEditServiceModel setCreator(String creator) {
        Creator = creator;
        return this;
    }
}
