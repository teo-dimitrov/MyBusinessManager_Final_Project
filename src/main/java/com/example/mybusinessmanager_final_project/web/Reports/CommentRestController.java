package com.example.mybusinessmanager_final_project.web.Reports;

import com.example.mybusinessmanager_final_project.model.binding.ReportsAddCommentBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.ReportsEditCommentBindingModel;
import com.example.mybusinessmanager_final_project.model.service.ReportAddCommentServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportsEditCommentServiceModel;
import com.example.mybusinessmanager_final_project.model.validator.ApiError;
import com.example.mybusinessmanager_final_project.model.view.reports.CommentViewModel;
import com.example.mybusinessmanager_final_project.service.reports.ReportsCommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

  private final ReportsCommentService reportsCommentService;
  private final ModelMapper modelMapper;

  public CommentRestController(ReportsCommentService reportsCommentService,
                               ModelMapper modelMapper) {
    this.reportsCommentService = reportsCommentService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/api/{reportId}/comments")
  public ResponseEntity<List<CommentViewModel>> getComments(
      @PathVariable Long reportId,
      Principal principal
  ) {
    return ResponseEntity.ok(
        reportsCommentService.getComments(reportId));
  }

  @PostMapping("/api/{reportId}/comments")
  public ResponseEntity<CommentViewModel> newComment(
      @AuthenticationPrincipal UserDetails principal,
      @PathVariable Long reportId,
      @RequestBody @Valid ReportsAddCommentBindingModel reportsAddCommentBindingModel
  ) {
    ReportAddCommentServiceModel serviceModel =
        modelMapper.map(reportsAddCommentBindingModel, ReportAddCommentServiceModel.class);
    serviceModel.setCreator(principal.getUsername());
    serviceModel.setReportId(reportId);

    CommentViewModel newComment =
        reportsCommentService.createComment(serviceModel);

    URI locationOfNewComment =
        URI.create(String.format("/api/%s/comments/%s", reportId, newComment.getCommentId()));

    return ResponseEntity.
        created(locationOfNewComment).
        body(newComment);
  }
  @PatchMapping("/api/{reportId}/comments/{commentId}/edit")
  public ResponseEntity<CommentViewModel> editComment(
          @AuthenticationPrincipal UserDetails principal,
          @PathVariable Long reportId,
          @RequestBody @Valid ReportsEditCommentBindingModel reportsEditCommentBindingModel,
          @PathVariable Long commentId) {
    ReportsEditCommentServiceModel serviceModel =
        modelMapper.map(reportsEditCommentBindingModel, ReportsEditCommentServiceModel.class);
    serviceModel.setMessage(reportsEditCommentBindingModel.getMessage());


    CommentViewModel newComment =
        reportsCommentService.editComment(serviceModel);

    URI locationOfNewComment =
        URI.create(String.format("/api/%s/comments/%s", reportId, newComment.getCommentId()));

    return ResponseEntity.
        created(locationOfNewComment).
        body(newComment);
  }

  @DeleteMapping("reports/{reportId}/comments/{id}")
  public String deleteComment(@PathVariable Long reportId,
                             Principal principal, @PathVariable Long id) {

    reportsCommentService.deleteComment(id);

    return "redirect:/reports/{reportId}/report-details";
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    exc.getFieldErrors().forEach(fe ->
        apiError.addFieldWithError(fe.getField()));

    return ResponseEntity.badRequest().body(apiError);
  }
}
