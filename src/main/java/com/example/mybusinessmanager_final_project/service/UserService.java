package com.example.mybusinessmanager_final_project.service;

import com.example.mybusinessmanager_final_project.model.binding.ChangeUserAccountSettingsBindingModel;
import com.example.mybusinessmanager_final_project.model.service.UserRegistrationServiceModel;
import com.example.mybusinessmanager_final_project.model.service.UserServiceModel;
import com.example.mybusinessmanager_final_project.model.view.user.UserDetailsView;
import com.example.mybusinessmanager_final_project.model.view.user.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isUserNameFree(String username);

    Optional<UserViewModel> findByUsername(String username);

    void changePassword(String newPassword, String username);

    void changeUserRole (String role, Long id);

    List<UserServiceModel> getAllUsers();

    void editAccount(ChangeUserAccountSettingsBindingModel userSettingsModel);

    UserDetailsView findUsername(String username);

    Optional<UserViewModel> findById(Long id);
}