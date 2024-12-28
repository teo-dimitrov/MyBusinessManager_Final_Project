package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.binding.OvertimeAddBindingModel;
import com.example.mybusinessmanager_final_project.model.service.OvertimeAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.OvertimeEditServiceModel;

import com.example.mybusinessmanager_final_project.model.view.overtime.OvertimeDetailsView;
import com.example.mybusinessmanager_final_project.model.view.overtime.OvertimeSummaryView;

import java.util.List;

public interface OvertimeService {

    void initializeOvertimes();

//    OvertimeViewModel createOvertime(OvertimeAddServiceModel overtimeAddServiceModel);

//    OvertimeViewModel createOvertime(OvertimeAddServiceModel overtimeAddServiceModel);

    List<OvertimeSummaryView> getAllOvertimes();
    List<OvertimeSummaryView> getAllNotPaidOvertimes();

    List<OvertimeSummaryView> getMyOvertimes(Long userId);


    OvertimeAddServiceModel addOvertime(OvertimeAddBindingModel overtimeAddBindingModel, String ownerId);
    OvertimeDetailsView findById(Long id, String currentUser);

    List<OvertimeSummaryView> findAllMyByStatus(String status, Long userId);
    OvertimeSummaryView getAll(Long id, String currentUser);

    boolean isOwner(String username, Long id);

    void deleteOvertime(Long id);

    void editOvertime(OvertimeEditServiceModel overtimeModel);
}
