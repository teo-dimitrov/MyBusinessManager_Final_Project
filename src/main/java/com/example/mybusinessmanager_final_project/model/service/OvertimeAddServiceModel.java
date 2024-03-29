package com.example.mybusinessmanager_final_project.model.service;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.LocalDate;


public class OvertimeAddServiceModel {

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

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeAddServiceModel setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeAddServiceModel setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeAddServiceModel setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
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
