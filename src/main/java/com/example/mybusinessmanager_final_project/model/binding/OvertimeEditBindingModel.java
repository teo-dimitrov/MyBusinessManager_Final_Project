package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class OvertimeEditBindingModel {

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

    public OvertimeEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OvertimeEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public OvertimeEditBindingModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public OvertimeEditBindingModel setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public OvertimeEditBindingModel setTimeTo(String timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public OvertimeStatusEnum getOvertimeStatusEnum() {
        return overtimeStatusEnum;
    }

    public OvertimeEditBindingModel setOvertimeStatusEnum(OvertimeStatusEnum overtimeStatusEnum) {
        this.overtimeStatusEnum = overtimeStatusEnum;
        return this;
    }
}
