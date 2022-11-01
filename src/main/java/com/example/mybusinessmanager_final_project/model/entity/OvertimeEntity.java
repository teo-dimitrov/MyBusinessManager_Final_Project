package com.example.mybusinessmanager_final_project.model.entity;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "overtime")
public class OvertimeEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String timeFrom;
    @Column(nullable = false)
    private String timeTo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OvertimeStatusEnum status;
    @ManyToOne
    private UserEntity author;

    @Column
    private String authorUsername;

    public String getAuthorUsername() {
        return authorUsername;
    }

    public OvertimeEntity setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeEntity setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeEntity setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getStatus() {
        return status;
    }

    public OvertimeEntity setStatus(OvertimeStatusEnum status) {
        this.status = status;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public OvertimeEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
