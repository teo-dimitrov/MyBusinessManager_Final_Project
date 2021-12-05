package com.example.mybusinessmanager_final_project.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class PictureBindingModel {

    private String pictureId;
    private String title;
    private MultipartFile picture;
    private String report;
    private String author;
    private String public_Id;


    public String getPictureId() {
        return pictureId;
    }

    public PictureBindingModel setPictureId(String pictureId) {
        this.pictureId = pictureId;
        return this;
    }

    public String getReport() {
        return report;
    }

    public PictureBindingModel setReport(String report) {
        this.report = report;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public PictureBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublic_Id() {
        return public_Id;
    }

    public PictureBindingModel setPublic_Id(String public_Id) {
        this.public_Id = public_Id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public PictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
