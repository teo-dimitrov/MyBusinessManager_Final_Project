package com.example.mybusinessmanager_final_project.web;


import com.example.mybusinessmanager_final_project.model.binding.OvertimeAddBindingModel;
import com.example.mybusinessmanager_final_project.model.service.OvertimeAddServiceModel;
import com.example.mybusinessmanager_final_project.model.validator.ApiError;
import com.example.mybusinessmanager_final_project.model.view.OvertimeViewModel;
import com.example.mybusinessmanager_final_project.service.OvertimeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

//@RestController
public class OvertimeRestController {

//    private final ModelMapper modelMapper;
//    private final OvertimeService overtimeService;
//
//    public OvertimeRestController(ModelMapper modelMapper, OvertimeService overtimeService) {
//        this.modelMapper = modelMapper;
//        this.overtimeService = overtimeService;
//    }
//
//    @PostMapping("/api/users/profile")
//    public ResponseEntity<OvertimeViewModel> createOvertime(
//            @AuthenticationPrincipal UserDetails principal,
//
//            @RequestBody @Valid OvertimeAddBindingModel overtimeAddBindingModel
//    ) {
//        OvertimeAddServiceModel serviceModel =
//                modelMapper.map(overtimeAddBindingModel, OvertimeAddServiceModel.class);
//        serviceModel.setCreator(principal.getUsername());
//
//
//        OvertimeViewModel createOvertime =
//                overtimeService.createOvertime(serviceModel);
//
//        URI locationOfNewComment =
//                URI.create(String.format("/api/users/profile/%s", createOvertime.getId()));
//
//        return ResponseEntity.
//                created(locationOfNewComment).
//                body(createOvertime);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
//        exc.getFieldErrors().forEach(fe ->
//                apiError.addFieldWithError(fe.getField()));
//
//        return ResponseEntity.badRequest().body(apiError);
//    }
}
