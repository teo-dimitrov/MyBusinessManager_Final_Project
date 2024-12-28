package com.example.mybusinessmanager_final_project.model.view.overtime;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.LocalDate;

public class OvertimeViewModel {

    private Long id;
    private String name;
    private LocalDate date;
    private String timeFrom;
    private String timeTo;
    private String duration;
    private OvertimeStatusEnum overtimeStatusEnum;
    private String authorUsername;
    private String creator;

    public String getCreator() {
        return creator;
    }

    public OvertimeViewModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public OvertimeViewModel() {
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public OvertimeViewModel setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
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

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeViewModel setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeViewModel setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public OvertimeViewModel setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeViewModel setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
        return this;
    }
}
