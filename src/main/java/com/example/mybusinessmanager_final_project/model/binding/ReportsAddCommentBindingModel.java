package com.example.mybusinessmanager_final_project.model.binding;

import javax.validation.constraints.NotBlank;

public class ReportsAddCommentBindingModel {

  @NotBlank
  private String message;

  public String getMessage() {
    return message;
  }

  public ReportsAddCommentBindingModel setMessage(String message) {
    this.message = message;
    return this;
  }
}
