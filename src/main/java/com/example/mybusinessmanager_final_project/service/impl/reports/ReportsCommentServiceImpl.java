package com.example.mybusinessmanager_final_project.service.impl.reports;

import com.example.mybusinessmanager_final_project.model.entity.CommentEntity;
import com.example.mybusinessmanager_final_project.model.service.ReportAddCommentServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportsEditCommentServiceModel;
import com.example.mybusinessmanager_final_project.model.view.reports.CommentViewModel;
import com.example.mybusinessmanager_final_project.repository.CommentRepository;
import com.example.mybusinessmanager_final_project.repository.ReportRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.reports.ReportsCommentService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportsCommentServiceImpl implements ReportsCommentService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ReportsCommentServiceImpl(ReportRepository reportRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(ReportAddCommentServiceModel reportAddCommentServiceModel) {
        Objects.requireNonNull(reportAddCommentServiceModel.getCreator());

        var report = reportRepository.
                findById(reportAddCommentServiceModel.getReportId()).
                orElseThrow(() -> new ObjectNotFoundException("report with id " + reportAddCommentServiceModel.getReportId() + " not found!"));

        var author = userRepository.
                findByUsername(reportAddCommentServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with username " + reportAddCommentServiceModel.getCreator() + " not found!"));

        CommentEntity newComment = new CommentEntity();
        newComment.setApproved(false);
        newComment.setTextContent(reportAddCommentServiceModel.getMessage());
        newComment.setCreated(Instant.now());
        newComment.setReportEntity(report);
        newComment.setAuthor(author);

        CommentEntity savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);
    }

    @Override
    public CommentViewModel editComment(ReportsEditCommentServiceModel reportsEditCommentServiceModel) {
        CommentEntity commentEntity = commentRepository
                .findById(reportsEditCommentServiceModel.getReportId())
                .orElseThrow(() -> new ObjectNotFoundException("Report with id " + reportsEditCommentServiceModel.getReportId() + " not found!"));

        commentEntity
                .setApproved(false)
                .setTextContent(reportsEditCommentServiceModel.getMessage())
                .setModified(Instant.now());


        commentRepository.save(commentEntity);
        return null;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long reportId) {
        var reportOpt = reportRepository.
                findById(reportId);

        if (reportOpt.isEmpty()) {
            throw new ObjectNotFoundException("Report with id " + reportId + " was not found!");
        }

        return reportOpt.
                get().
                getComments().
                stream().
                map(this::mapAsComment).
                collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanApprove(true).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;
    }
}
