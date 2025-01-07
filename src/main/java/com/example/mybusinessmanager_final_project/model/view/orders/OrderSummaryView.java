package com.example.mybusinessmanager_final_project.model.view.orders;
import java.time.Instant;
import java.time.LocalDateTime;

public class OrderSummaryView {
    private long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private Instant created;
    private Instant modified;

    private String instantCreatedStr;
    private String instantModifiedStr;

    private String authorId;
    private String authorFullName;

    public long getId() {
        return id;
    }

    public OrderSummaryView setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderSummaryView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public OrderSummaryView setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OrderSummaryView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OrderSummaryView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public String getInstantCreatedStr() {
        return instantCreatedStr;
    }

    public OrderSummaryView setInstantCreatedStr(String instantCreatedStr) {
        this.instantCreatedStr = instantCreatedStr;
        return this;
    }

    public String getInstantModifiedStr() {
        return instantModifiedStr;
    }

    public OrderSummaryView setInstantModifiedStr(String instantModifiedStr) {
        this.instantModifiedStr = instantModifiedStr;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public OrderSummaryView setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public OrderSummaryView setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }
}
