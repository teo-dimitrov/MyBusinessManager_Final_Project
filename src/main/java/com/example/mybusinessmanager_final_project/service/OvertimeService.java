package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.service.OvertimeAddServiceModel;
import com.example.mybusinessmanager_final_project.model.view.OvertimeViewModel;

import java.util.List;

public interface OvertimeService {

    OvertimeViewModel createOvertime(OvertimeAddServiceModel overtimeAddServiceModel);

    List<OvertimeViewModel> getAllOvertimes(Long userId);
}
