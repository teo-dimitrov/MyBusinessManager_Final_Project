package com.example.mybusinessmanager_final_project.web.overtime;

import com.example.mybusinessmanager_final_project.model.binding.OvertimeAddBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.OvertimeEditBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.enums.OvertimeStatusEnum;
import com.example.mybusinessmanager_final_project.model.service.OvertimeAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.OvertimeEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.overtime.OvertimeDetailsView;
import com.example.mybusinessmanager_final_project.model.view.user.UserViewModel;
import com.example.mybusinessmanager_final_project.service.OvertimeService;
import com.example.mybusinessmanager_final_project.service.UserService;
import com.example.mybusinessmanager_final_project.service.impl.user.MBMUser;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OvertimeController {

    private final OvertimeService overtimeService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OvertimeController(OvertimeService overtimeService, ModelMapper modelMapper, UserService userService) {
        this.overtimeService = overtimeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/overtime")
    public String getAddOvertimePage(Model model, @AuthenticationPrincipal UserDetails principal) {

        UserViewModel viewModel = userService
                .findByUsername(principal.getUsername())
                .orElseThrow(() ->
                        new ObjectNotFoundException("user"));

        if (!model.containsAttribute("overtimeAddBindingModel")) {
            model.
                    addAttribute("overtimeAddBindingModel", new OvertimeAddBindingModel())
                    .addAttribute("user", viewModel);
        }
        return "overtime-add";
    }
    @GetMapping("/overtimes" )
    public String allOvertimes(Model model, @AuthenticationPrincipal UserDetails principal){

        UserViewModel viewModel = userService
                .findByUsername(principal.getUsername())
                .orElseThrow(() ->
                        new ObjectNotFoundException("user"));

        model
                .addAttribute("overtimes", overtimeService.getAllNotPaidOvertimes())
                .addAttribute("user", viewModel)
               ;

        return "overtime-all";
    }
    @GetMapping("/my-overtime" )
    public String myOvertimes(Model model, @AuthenticationPrincipal UserDetails principal){

        UserViewModel viewModel = userService
                .findByUsername(principal.getUsername())
                .orElseThrow(() ->
                        new ObjectNotFoundException("user"));

        model
                .addAttribute("overtimes", overtimeService.findAllMyByStatus( "NOT_PAID", viewModel.getId()))
                .addAttribute("user", viewModel)
        ;

        return "overtime-my";
    }
    @GetMapping("/overtime-archive" )
    public String myOvertimeArchive(Model model, @AuthenticationPrincipal UserDetails principal){

        UserViewModel viewModel = userService
                .findByUsername(principal.getUsername())
                .orElseThrow(() ->
                        new ObjectNotFoundException("user"));

        model
                .addAttribute("overtimes", overtimeService.findAllMyByStatus("PAID", viewModel.getId()))
                .addAttribute("user", viewModel)
        ;

        return "overtime-archive";
    }

    @PostMapping("/overtime")
    public String addOvertime(
//            @RequestParam("date")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
//            @RequestParam("timeFrom")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeFrom,
//            @RequestParam("timeTo")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime timeTo,
            @Valid OvertimeAddBindingModel overtimeAddBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal MBMUser user
            ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("overtimeAddBindingModel", overtimeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.overtimeAddBindingModel", bindingResult);

            return "overtime-add";
        }
        OvertimeAddServiceModel savedOvertimeAddServiceModel = overtimeService.addOvertime(overtimeAddBindingModel, user.getUserIdentifier());

        return "redirect:my-overtime";
    }
    @DeleteMapping("/overtimes/{id}")
    public String deleteOvertime(@PathVariable Long id,
                               Principal principal) {

        overtimeService.deleteOvertime(id);

        return "redirect:/overtime-archive";
    }

    @GetMapping("/overtimes/{id}/edit")
    public String editOvertime(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal MBMUser currentUser) {

        OvertimeDetailsView overtimeDetailsView = overtimeService.findById(id, currentUser.getUserIdentifier());
        OvertimeEditBindingModel overtimeEditBindingModel = modelMapper.map(
                overtimeDetailsView,
                OvertimeEditBindingModel.class
        );
        model.addAttribute("overtimeModel", overtimeEditBindingModel);

        return "overtime-edit";
    }

    @PatchMapping("/overtimes/{id}/edit")
    public String editReport(
            @PathVariable Long id,
            @Valid OvertimeEditBindingModel overtimeModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("overtimeModel", overtimeModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.overtimeModel", bindingResult);

            return "redirect:/overtimes/" + id + "/error";
        }

        OvertimeEditServiceModel serviceModel = modelMapper.map(overtimeModel,
                OvertimeEditServiceModel.class);
        serviceModel.setId(id);

        overtimeService.editOvertime(serviceModel);

        return "redirect:/my-overtime";
    }

    @GetMapping("/overtimes/{id}/error")
    public String editOvertimesErrors(@PathVariable Long id, Model model) {
        model.addAttribute("status", OvertimeStatusEnum.values());

        return "overtime-edit";
    }
}
