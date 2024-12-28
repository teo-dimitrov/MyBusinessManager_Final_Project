package com.example.mybusinessmanager_final_project.model.binding;

import javax.validation.constraints.NotBlank;

public class ReportsEditCommentBindingModel {

    @NotBlank
    private String message;

    public String getMessage() {
        return message;
    }

    public ReportsEditCommentBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
