package com.example.mybusinessmanager_final_project.model.view.reports;

import java.time.Instant;

public class ReportsEditCommentViewModel {

    private Long commentId;
    private String message;
    private String user;
    private Instant created;
    private boolean canApprove;
    private boolean canDelete;

    public Long getCommentId() {
        return commentId;
    }

    public ReportsEditCommentViewModel setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReportsEditCommentViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUser() {
        return user;
    }

    public ReportsEditCommentViewModel setUser(String user) {
        this.user = user;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public ReportsEditCommentViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public ReportsEditCommentViewModel setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ReportsEditCommentViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
