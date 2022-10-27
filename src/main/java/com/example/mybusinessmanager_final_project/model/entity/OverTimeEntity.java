package com.example.mybusinessmanager_final_project.model.entity;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "overtime")
public class OverTimeEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime timeFrom;
    @Column(nullable = false)
    private LocalTime timeTo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OvertimeStatusEnum status;
    @ManyToOne
    private UserEntity author;

    public String getName() {
        return name;
    }

    public OverTimeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OverTimeEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public OverTimeEntity setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public OverTimeEntity setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getStatus() {
        return status;
    }

    public OverTimeEntity setStatus(OvertimeStatusEnum status) {
        this.status = status;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public OverTimeEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
