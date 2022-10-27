package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class OvertimeViewModel {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private LocalTime duration;
    private OvertimeStatusEnum status;

    public OvertimeViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OvertimeViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeViewModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public OvertimeViewModel setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public OvertimeViewModel setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public OvertimeViewModel setDuration(LocalTime duration) {
        this.duration = duration;
        return this;
    }

    public OvertimeStatusEnum getStatus() {
        return status;
    }

    public OvertimeViewModel setStatus(OvertimeStatusEnum status) {
        this.status = status;
        return this;
    }
}
