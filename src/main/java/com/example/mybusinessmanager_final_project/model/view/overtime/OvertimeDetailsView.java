package com.example.mybusinessmanager_final_project.model.view.overtime;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.LocalDate;

public class OvertimeDetailsView {

    private Long id;
    private String name;
    private LocalDate date;
    private String timeFrom;
    private String timeTo;
    private String duration;
    private OvertimeStatusEnum overtimeStatusEnum;
    private boolean canDelete;
    private boolean isOwner;
//    private String authorUsername;
    private String creator;

    public String getCreator() {
        return creator;
    }

    public OvertimeDetailsView setCreator(String creator) {
        this.creator = creator;
        return this;
    }

//    public String getAuthorUsername() {
//        return authorUsername;
//    }
//
//    public OvertimeDetailsView setAuthorUsername(String authorUsername) {
//        this.authorUsername = authorUsername;
//        return this;
//    }

    public OvertimeDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public OvertimeDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeDetailsView setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeDetailsView setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeDetailsView setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public OvertimeDetailsView setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeDetailsView setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public OvertimeDetailsView setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public OvertimeDetailsView setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }
}
