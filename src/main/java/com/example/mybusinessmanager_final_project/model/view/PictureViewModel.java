package com.example.mybusinessmanager_final_project.model.view;

import java.time.Instant;

public class PictureViewModel {

    private Long pictureId;
    private String user;
    private Instant created;
    private String title;
    private String publicId;
    private String url;
    private boolean canApprove;
    private boolean canDelete;

    public String getTitle() {
        return title;
    }

    public PictureViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureViewModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public PictureViewModel setPictureId(Long pictureId) {
        this.pictureId = pictureId;
        return this;
    }

    public String getUser() {
        return user;
    }

    public PictureViewModel setUser(String user) {
        this.user = user;
        return this;

    }

    public Instant getCreated() {
        return created;
    }

    public PictureViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public PictureViewModel setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public PictureViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
