package com.example.mybusinessmanager_final_project.model.view;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class OrderViewModel {
    private Long id;
    private String name;
    private Instant created;
    private LocalDateTime deadLine;
    private String instantStr;

    private String deadlineStr;


    public String getInstantStr() {
        instantStr = created.truncatedTo(ChronoUnit.SECONDS).toString().replaceAll("[TZ]", " ");
        return instantStr;
    }

    public OrderViewModel setInstantStr(String instantStr) {
        this.instantStr = instantStr;
        return this;
    }


    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderViewModel setName(String name) {
        this.name = name;
        return this;
    }


    public Instant getCreated() {
        return created;
    }

    public OrderViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public OrderViewModel setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
        return this;
    }

//    public String getDeadlineStr() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        deadlineStr = deadLine.format(formatter);
//        return deadlineStr;
//    }
//
//    public OrderViewModel setDeadlineStr(String deadlineStr) {
//        this.deadlineStr = deadlineStr;
//        return this;
//    }
}
