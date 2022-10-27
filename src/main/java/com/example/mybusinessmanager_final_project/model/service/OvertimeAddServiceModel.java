package com.example.mybusinessmanager_final_project.model.service;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class OvertimeAddServiceModel {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private OvertimeStatusEnum status;
    private String creator;

    public Long getId() {
        return id;
    }

    public OvertimeAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeAddServiceModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public OvertimeAddServiceModel setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public OvertimeAddServiceModel setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getStatus() {
        return status;
    }

    public OvertimeAddServiceModel setStatus(OvertimeStatusEnum status) {
        this.status = status;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public OvertimeAddServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
