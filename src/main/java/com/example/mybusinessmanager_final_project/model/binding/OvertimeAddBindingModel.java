package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class OvertimeAddBindingModel {

    private Long id;
    @NotBlank(message = "this field is required")
    private String name;
    @NotNull LocalDate date;
    @NotNull(message = "this field is required")
    private String timeFrom;
    @NotNull(message = "this field is required")
    private String timeTo;
    @NotNull
    private OvertimeStatusEnum overtimeStatusEnum;


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

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeAddBindingModel setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeAddBindingModel setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeAddBindingModel setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
        return this;
    }
}
