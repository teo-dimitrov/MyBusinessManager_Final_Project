package com.example.mybusinessmanager_final_project.model.binding;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReportAddBindingModel {

    private Long id;
//    @NotNull
//    @Size(min = 1, max = 60, message = "Name must be between 3 and 40 characters")
    @NotBlank(message = "Моля попълнете полето")
    private String name;
    @NotBlank(message = "Моля попълнете полето")
    private String description;
    @NotNull
    private ReportTypeEnum reportTypeEnum;
    @NotNull
    private ReportStatusEnum reportStatusEnum;

    public Long getId() {
        return id;
    }

    public ReportAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportAddBindingModel setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportAddBindingModel setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }
}
