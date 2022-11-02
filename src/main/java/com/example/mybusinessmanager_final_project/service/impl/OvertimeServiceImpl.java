package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.binding.OvertimeAddBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.*;
import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;
import com.example.mybusinessmanager_final_project.model.service.OvertimeAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.OvertimeEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.OvertimeDetailsView;
import com.example.mybusinessmanager_final_project.model.view.OvertimeSummaryView;
import com.example.mybusinessmanager_final_project.model.view.OvertimeViewModel;
import com.example.mybusinessmanager_final_project.repository.OvertimeRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.OvertimeService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OvertimeServiceImpl implements OvertimeService {

    private final UserRepository userRepository;
    private final OvertimeRepository overtimeRepository;
    private final ModelMapper modelMapper;

    public OvertimeServiceImpl(UserRepository userRepository, OvertimeRepository overtimeRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.overtimeRepository = overtimeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeOvertimes() {
        if (overtimeRepository.count() == 0) {
            OvertimeEntity overtime1 = new OvertimeEntity();
            overtime1.
                    setAuthorUsername("admin").
                    setName("overtime 1").
                    setDate(LocalDate.now()).
                    setTimeFrom("09:00").
                    setTimeTo("17:00").
                    setStatus(OvertimeStatusEnum.PAID).
                    setAuthor(userRepository.findByUsername("admin").orElse(null));

            OvertimeEntity overtime2 = new OvertimeEntity();
            overtime2.
                    setAuthorUsername("member").
                    setName("overtime 2").
                    setDate(LocalDate.now()).
                    setTimeFrom("09:00").
                    setTimeTo("17:00").
                    setStatus(OvertimeStatusEnum.PAID).
                    setAuthor(userRepository.findByUsername("member").orElse(null));



            overtimeRepository.saveAll(List.of(overtime1, overtime2));
        }
    }
//
//    @Override
//    public OvertimeViewModel createOvertime(OvertimeAddServiceModel overtimeAddServiceModel) {
//        Objects.requireNonNull(overtimeAddServiceModel.getCreator());
//
//        var overtime = overtimeRepository.
//                findById(overtimeAddServiceModel.getId()).
//                orElseThrow(() -> new ObjectNotFoundException("report with id " + overtimeAddServiceModel.getId() + " not found!"));
//
//        var author = userRepository.
//                findByUsername(overtimeAddServiceModel.getCreator()).
//                orElseThrow(() -> new ObjectNotFoundException("User with username " + overtimeAddServiceModel.getCreator() + " not found!"));
//
//        OvertimeEntity newOverTimeEntity = new OvertimeEntity();
//
////        newOverTimeEntity.setId()
//
//        newOverTimeEntity.
//                setAuthorUsername(overtimeAddServiceModel.getCreator()).
//                setAuthor(author).
//                setName(overtimeAddServiceModel.getName()).
//                setDate(overtimeAddServiceModel.getDate()).
//                setTimeTo(overtimeAddServiceModel.getTimeTo()).
//                setTimeFrom(overtimeAddServiceModel.getTimeFrom()).
//                setStatus(overtimeAddServiceModel.getOvertimeStatusEnum()).
//                setCreated(Instant.now());
//
//        OvertimeEntity savedOvertime = overtimeRepository.save(newOverTimeEntity);
//
//
//        return mapAsOvertime(savedOvertime);
//    }

    @Override
    public List<OvertimeSummaryView> getAllOvertimes() {


        return overtimeRepository.findAll().stream().sorted(Comparator.comparing(BaseEntity::getCreated).reversed())
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public List<OvertimeSummaryView> getMyOvertimes(Long userId) {
        return overtimeRepository.findAll().stream().filter(u -> Objects.equals(u.getAuthor().getId(), userId))
                .sorted(Comparator.comparing(BaseEntity::getModified).reversed())
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public OvertimeAddServiceModel addOvertime(OvertimeAddBindingModel overtimeAddBindingModel, String creator) {
        UserEntity userEntity = userRepository.findByUsername(creator).orElseThrow();
        OvertimeAddServiceModel overtimeAddServiceModel = modelMapper.map(overtimeAddBindingModel, OvertimeAddServiceModel.class);
        OvertimeEntity newOvertime = modelMapper.map(overtimeAddServiceModel, OvertimeEntity.class);
        newOvertime.setAuthor(userEntity).setAuthorUsername(userEntity.getUsername());

        OvertimeEntity savedOvertime = overtimeRepository.save(newOvertime);

        return modelMapper.map(savedOvertime, OvertimeAddServiceModel.class);
    }

    @Override
    public OvertimeDetailsView findById(Long id, String currentUser) {
        return this.overtimeRepository
                .findById(id).map(r -> mapDetailsView(currentUser, r)).get();
    }


    @Override
    public boolean isOwner(String username, Long id) {
        Optional<OvertimeEntity> overtimeOptional = overtimeRepository.findById(id);
        Optional<UserEntity> caller = userRepository.findByUsername(username);

        if (overtimeOptional.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            OvertimeEntity overTimeEntity = overtimeOptional.get();
            return isAdmin(caller.get()) || overTimeEntity.getAuthor().getUsername().equals(username);
        }
    }

    @Override
    public void deleteOvertime(Long id) {
        overtimeRepository.deleteById(id);
    }

    @Override
    public void editOvertime(OvertimeEditServiceModel overtimeModel) {
        OvertimeEntity overtimeEntity = overtimeRepository
                .findById(overtimeModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Report with id " + overtimeModel.getId() + " not found!"));

        overtimeEntity
                .setName(overtimeModel.getName())
                .setDate(overtimeModel.getDate())
                .setTimeFrom(overtimeModel.getTimeFrom())
                .setTimeTo(overtimeModel.getTimeTo())
                .setStatus(overtimeModel.getOvertimeStatusEnum())
                .setModified(Instant.now());

        overtimeRepository.save(overtimeEntity);
    }


    private OvertimeViewModel mapAsOvertime(OvertimeEntity overTimeEntity) {
        OvertimeViewModel overtimeViewModel = new OvertimeViewModel();

        overtimeViewModel.setId(overTimeEntity.getId()).
                setDate(overTimeEntity.getDate()).
                setName(overTimeEntity.getName()).
                setTimeFrom(overTimeEntity.getTimeFrom()).
                setTimeTo(overTimeEntity.getTimeTo()).
                setOvertimeStatusEnum(overTimeEntity.getStatus())
//                .setAuthorUsername(overTimeEntity.getAuthorUsername())
        ;

        return overtimeViewModel;
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    private OvertimeDetailsView mapDetailsView(String currentUser, OvertimeEntity overTimeEntity) {

        OvertimeDetailsView overtimeDetailsView = this.modelMapper.map(overTimeEntity, OvertimeDetailsView.class);

        overtimeDetailsView.setCanDelete(isOwner(currentUser, overTimeEntity.getId()));
        overtimeDetailsView.setOwner(isOwner(currentUser, overTimeEntity.getId()));


        return overtimeDetailsView;
    }
    private OvertimeSummaryView map(OvertimeEntity overtimeEntity) {
        return this.modelMapper.map(overtimeEntity, OvertimeSummaryView.class);
    }
}
