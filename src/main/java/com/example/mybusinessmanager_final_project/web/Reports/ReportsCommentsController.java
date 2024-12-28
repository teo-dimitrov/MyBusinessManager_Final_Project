package com.example.mybusinessmanager_final_project.web.Reports;

import com.example.mybusinessmanager_final_project.service.reports.ReportsCommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ReportsCommentsController {


    private final ReportsCommentService reportsCommentService;
    private final ModelMapper modelMapper;

    public ReportsCommentsController(ReportsCommentService reportsCommentService, ModelMapper modelMapper) {
        this.reportsCommentService = reportsCommentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/reports/{reportId}/comments")
    public String allComments(@PathVariable Long reportId, Model model,
                              Principal principal) {
        model.addAttribute("comments",
                reportsCommentService.getComments(reportId));
        return "report-details";
    }
}
