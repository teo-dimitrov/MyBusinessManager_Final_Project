package com.example.mybusinessmanager_final_project.web.user;

import com.example.mybusinessmanager_final_project.model.binding.UserRegistrationBindingModel;
import com.example.mybusinessmanager_final_project.model.service.UserRegistrationServiceModel;
import com.example.mybusinessmanager_final_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService,
                                      ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/users/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/users/register")
    public String register(
            @Valid UserRegistrationBindingModel userModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userModel.getPassword().equals(userModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:register";
        }

        UserRegistrationServiceModel serviceModel =
                modelMapper.map(userModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }
}

