package com.example.mybusinessmanager_final_project.model.service;

public class ReportsEditCommentServiceModel {

    private Long reportId;
    private String message;
    private String creator;

    public Long getReportId() {
        return reportId;
    }

    public ReportsEditCommentServiceModel setReportId(Long reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReportsEditCommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public ReportsEditCommentServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
