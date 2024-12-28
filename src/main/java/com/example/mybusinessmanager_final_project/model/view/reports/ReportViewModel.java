package com.example.mybusinessmanager_final_project.model.view.reports;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;


public class ReportViewModel {

    private Long id;
    private String name;
    private ReportTypeEnum reportTypeEnum;
    private ReportStatusEnum reportStatusEnum;
    private String user;
    private Instant created;


    public ReportViewModel() {

    }

    public Long getId() {
        return id;
    }

    public ReportViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportViewModel setName(String name) {
        this.name = name;
        return this;
    }


    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportViewModel setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportViewModel setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ReportViewModel setUser(String user) {
        this.user = user;
        return this;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Sofia")
    public Instant getCreated() {
        return created;
    }

    public ReportViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
