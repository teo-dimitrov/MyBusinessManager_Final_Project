package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.ChangePasswordBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.ChangeUserAccountSettingsBindingModel;
import com.example.mybusinessmanager_final_project.model.view.UserDetailsView;
import com.example.mybusinessmanager_final_project.model.view.UserViewModel;
import com.example.mybusinessmanager_final_project.service.UserService;
import com.example.mybusinessmanager_final_project.service.impl.MBMUser;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserAccountController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserAccountController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("users/user-details")
    public String showAccount(Model model,
                              @AuthenticationPrincipal UserDetails principal) {

        UserViewModel viewModel = userService
                .findByUsername(principal.getUsername())
                .orElseThrow(() ->
                        new ObjectNotFoundException("user"));

        model.addAttribute("user", viewModel);

        return "user-details";
    }

    @GetMapping("users/user-details/changePassword")
    private String getChanePassword() {
        return "changePassword";
    }

    @PostMapping("users/user-details/changePassword")
    public String changePassword(ChangePasswordBindingModel changePasswordBindingModel, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, @AuthenticationPrincipal User user) {


        if (bindingResult.hasErrors() || !changePasswordBindingModel.getNewPassword()
                .equals(changePasswordBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("changePasswordBindingModel", changePasswordBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.changePasswordBindingModel",
                            bindingResult)
                    .addFlashAttribute("notMatch", true);
            return "changePassword";
        }

        userService.changePassword(changePasswordBindingModel.getNewPassword(), user.getUsername());

        return "redirect:/users/user-details";
    }

    @GetMapping("users/all")
    public String getAllUsers(Model model, Principal principal) {
        List<UserViewModel> users = userService.getAllUsers().stream()
                .map(userModel -> modelMapper.map(userModel, UserViewModel.class)).collect(Collectors.toList());
        model.addAttribute("users", users);
        return "users-all";
    }

    @GetMapping("/users/user-details/user-settings")
    private String getChangeSettings(Model model,
                                     ChangeUserAccountSettingsBindingModel userSettingsModel,
                                     @AuthenticationPrincipal MBMUser currentUser) {

        UserDetailsView userDetailsView = userService.findUsername(currentUser.getUserIdentifier());
        ChangeUserAccountSettingsBindingModel changeSettings =
                modelMapper.map(userDetailsView, ChangeUserAccountSettingsBindingModel.class);

        model.addAttribute("firstName", changeSettings.getFirstName());
        model.addAttribute("lastName", changeSettings.getLastName());
        model.addAttribute("email", changeSettings.getEmail());
        model.addAttribute("settingsModel", changeSettings);

        return "user-settings";
    }

    @PatchMapping("/users/user-details/user-settings")
    private String editAccount(
            @Valid ChangeUserAccountSettingsBindingModel userSettingsModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal MBMUser currentUser) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userSettingsModel", userSettingsModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userSettingsModel", bindingResult);

            return "redirect:user-settings";
        }
        ChangeUserAccountSettingsBindingModel userSettingModel = modelMapper.map(userSettingsModel,
                ChangeUserAccountSettingsBindingModel.class);
        userSettingModel
                .setUsername(currentUser.getUserIdentifier())
                .setFirstName(userSettingModel.getFirstName())
                .setLastName(userSettingModel.getLastName())
                .setEmail(userSettingModel.getEmail());

        userService.editAccount(userSettingModel);

        return "redirect:/users/user-details";
    }

    @ModelAttribute
    public ChangePasswordBindingModel changePasswordBindingModel() {
        return new ChangePasswordBindingModel();
    }

}
