package com.example.mybusinessmanager_final_project.service.reports;

import com.example.mybusinessmanager_final_project.model.service.ReportAddCommentServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportsEditCommentServiceModel;
import com.example.mybusinessmanager_final_project.model.view.reports.CommentViewModel;

import java.util.List;

public interface ReportsCommentService {

    CommentViewModel createComment(ReportAddCommentServiceModel reportAddCommentServiceModel);
    CommentViewModel editComment(ReportsEditCommentServiceModel reportsEditCommentServiceModel);

    void deleteComment(Long id);


    List<CommentViewModel> getComments(Long reportId);


}
