package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.entity.OverTimeEntity;
import com.example.mybusinessmanager_final_project.model.service.OvertimeAddServiceModel;
import com.example.mybusinessmanager_final_project.model.view.OvertimeViewModel;
import com.example.mybusinessmanager_final_project.repository.OvertimeRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.OvertimeService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class OvertimeServiceImpl implements OvertimeService {

    private final UserRepository userRepository;
    private final OvertimeRepository overtimeRepository;

    public OvertimeServiceImpl(UserRepository userRepository, OvertimeRepository overtimeRepository) {
        this.userRepository = userRepository;
        this.overtimeRepository = overtimeRepository;
    }

    @Override
    public OvertimeViewModel createOvertime(OvertimeAddServiceModel overtimeAddServiceModel) {
        Objects.requireNonNull(overtimeAddServiceModel.getCreator());

        var overtime = overtimeRepository.
                findById(overtimeAddServiceModel.getId()).
                orElseThrow(() -> new ObjectNotFoundException("report with id " + overtimeAddServiceModel.getId() + " not found!"));

        var author = userRepository.
                findByUsername(overtimeAddServiceModel.getCreator()).
                orElseThrow(() -> new ObjectNotFoundException("User with username " + overtimeAddServiceModel.getCreator() + " not found!"));

        OverTimeEntity newOverTimeEntity = new OverTimeEntity();

//        newOverTimeEntity.setId()

        newOverTimeEntity.
                setAuthor(author).
                setName(overtimeAddServiceModel.getName()).
                setDate(overtimeAddServiceModel.getDate()).
                setTimeTo(overtimeAddServiceModel.getTimeTo()).
                setTimeFrom(overtimeAddServiceModel.getTimeFrom()).
                setStatus(overtimeAddServiceModel.getStatus()).
                setCreated(Instant.now());

        OverTimeEntity savedOvertime = overtimeRepository.save(newOverTimeEntity);


        return mapAsOvertime(savedOvertime);
    }

    @Override
    public List<OvertimeViewModel> getAllOvertimes(Long userId) {


        return null;
    }


    private OvertimeViewModel mapAsOvertime(OverTimeEntity overTimeEntity) {
        OvertimeViewModel overtimeViewModel = new OvertimeViewModel();

        overtimeViewModel.setId(overTimeEntity.getId()).
                setDate(overTimeEntity.getDate()).
                setName(overTimeEntity.getName()).
                setTimeFrom(overTimeEntity.getTimeFrom()).
                setTimeTo(overTimeEntity.getTimeTo()).
                setStatus(overTimeEntity.getStatus())
        ;

        return overtimeViewModel;
    }
}
