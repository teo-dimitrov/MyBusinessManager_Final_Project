package com.example.mybusinessmanager_final_project.model.service;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.LocalDate;

public class OvertimeEditServiceModel {
    private Long id;
    private String name;
    private LocalDate date;
    private String timeFrom;
    private String timeTo;
    private OvertimeStatusEnum overtimeStatusEnum;
    private String creator;

    public Long getId() {
        return id;
    }

    public OvertimeEditServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeEditServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeEditServiceModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeEditServiceModel setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeEditServiceModel setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeEditServiceModel setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public OvertimeEditServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
