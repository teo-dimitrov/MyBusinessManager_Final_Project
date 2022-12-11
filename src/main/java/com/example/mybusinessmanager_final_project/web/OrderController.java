package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.OrderAddBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.OrderEditBindingModel;
import com.example.mybusinessmanager_final_project.model.binding.ReportEditBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;
import com.example.mybusinessmanager_final_project.model.service.OrderAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.OrderEditServiceModel;
import com.example.mybusinessmanager_final_project.model.service.ReportEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.OrderDetailsView;
import com.example.mybusinessmanager_final_project.model.view.ReportDetailsView;
import com.example.mybusinessmanager_final_project.service.OrderService;
import com.example.mybusinessmanager_final_project.service.impl.MBMUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/orders/all")
    public String allReports(Model model) {
        model.addAttribute("orders",
                orderService.getAllOrders());
        return "orders-all";
    }
    @GetMapping("/orders/{id}/order-details")
    public String showOrder(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("order", this.orderService.findById(id, principal.getName()));
        return "order-details";
    }
    @GetMapping("/orders/add")
    public String getAddOrderPage(Model model) {

        if (!model.containsAttribute("orderAddBindingModel")) {
            model.
                    addAttribute("orderAddBindingModel", new OrderAddBindingModel());
        }
        return "orders-add";
    }
    @PostMapping("/orders/add")
    public String addReport(@Valid OrderAddBindingModel orderAddBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal MBMUser user) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }
        OrderAddServiceModel savedOrderAddServiceModel = orderService
                .addOrder(orderAddBindingModel, user.getUserIdentifier());

        return "redirect:/orders/all";
    }
    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable Long id,
                               Principal principal) {
        orderService.deleteOrder(id);
        return "redirect:/orders/all";
    }
    @GetMapping("/orders/{id}/edit")
    public String editReport(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal MBMUser currentUser) {

        OrderDetailsView orderDetailsView = orderService.findById(id, currentUser.getUserIdentifier());
        OrderEditBindingModel orderEditBindingModel = modelMapper.map(
                orderDetailsView,
                OrderEditBindingModel.class
        );
        model.addAttribute("type", ReportTypeEnum.values());
        model.addAttribute("status", ReportStatusEnum.values());
        model.addAttribute("orderModel", orderEditBindingModel);

        return "order-edit";

    }

    @GetMapping("/orders/{id}/edit/error")
    public String editOrderErrors(@PathVariable Long id, Model model) {


        return "order-edit";
    }

    @PatchMapping("/orders/{id}/edit")
    public String editOrder(
            @PathVariable Long id,
            @Valid OrderEditBindingModel orderModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("reportModel", orderModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderModel", bindingResult);

            return "redirect:/orders/" + id + "/edit/error";
        }

        OrderEditServiceModel serviceModel = modelMapper.map(orderModel,
                OrderEditServiceModel.class);
        serviceModel.setId(id);

        orderService.editOrder(serviceModel);

        return "redirect:/orders/" + id + "/order-details";
    }
}
