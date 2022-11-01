package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import java.time.Instant;
import java.time.LocalDate;

public class OvertimeSummaryView {

    private long id;
    private String name;
    private LocalDate date;
    private String timeFrom;
    private String timeTo;
    private OvertimeStatusEnum overtimeStatusEnum;
    private Instant created;
    private Instant modified;
    private String authorId;
    private String creator;

    public String getCreator() {
        return creator;
    }

    public OvertimeSummaryView setCreator(String creator) {
        this.creator = creator;
        return this;
    }
    //    private String authorUsername;
//
//    public String getAuthorUsername() {
//        return authorUsername;
//    }
//
//    public OvertimeSummaryView setAuthorUsername(String authorUsername) {
//        this.authorUsername = authorUsername;
//        return this;
//    }

    public long getId() {
        return id;
    }

    public OvertimeSummaryView setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeSummaryView setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeSummaryView setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeSummaryView setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeSummaryView setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OvertimeSummaryView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OvertimeSummaryView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public OvertimeSummaryView setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }
}
