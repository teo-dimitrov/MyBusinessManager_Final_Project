package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class OvertimeAddBindingModel {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime timeFrom;
    @NotNull
    private LocalTime timeTo;
    @NotNull
    private OvertimeStatusEnum status;


    public Long getId() {
        return id;
    }

    public OvertimeAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeAddBindingModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public OvertimeAddBindingModel setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public OvertimeAddBindingModel setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getStatus() {
        return status;
    }

    public OvertimeAddBindingModel setStatus(OvertimeStatusEnum status) {
        this.status = status;
        return this;
    }
}
