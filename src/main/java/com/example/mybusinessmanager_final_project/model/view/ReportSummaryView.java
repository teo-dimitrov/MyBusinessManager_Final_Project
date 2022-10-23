package com.example.mybusinessmanager_final_project.model.view;

import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ReportSummaryView {

    private long id;
    private String name;
    private String description;
    private ReportTypeEnum reportTypeEnum;
    private ReportStatusEnum reportStatusEnum;
    private Instant created;
    private Instant modified;

    private String instantCreatedStr;
    private String instantModifiedStr;

    private String authorId;

    public String getAuthorId() {
        return authorId;
    }

    public ReportSummaryView setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getInstantCreatedStr() {
        instantCreatedStr = created.truncatedTo(ChronoUnit.SECONDS).toString().replaceAll("[TZ]", " ");
        return instantCreatedStr;
    }

    public ReportSummaryView setInstantCreatedStr(String instantStr) {
        this.instantCreatedStr = instantStr;
        return this;
    }
    public String getInstantModifiedStr() {
        instantModifiedStr = modified.truncatedTo(ChronoUnit.SECONDS).toString().replaceAll("[TZ]", " ");
        return instantModifiedStr;
    }

    public ReportSummaryView setInstantModifiedStr(String instantModifiedStr) {
        this.instantModifiedStr = instantModifiedStr;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public ReportSummaryView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public ReportSummaryView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }



    public long getId() {
        return id;
    }

    public ReportSummaryView setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ReportSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReportTypeEnum getReportTypeEnum() {
        return reportTypeEnum;
    }

    public ReportSummaryView setReportTypeEnum(ReportTypeEnum reportTypeEnum) {
        this.reportTypeEnum = reportTypeEnum;
        return this;
    }

    public ReportStatusEnum getReportStatusEnum() {
        return reportStatusEnum;
    }

    public ReportSummaryView setReportStatusEnum(ReportStatusEnum reportStatusEnum) {
        this.reportStatusEnum = reportStatusEnum;
        return this;
    }
}
