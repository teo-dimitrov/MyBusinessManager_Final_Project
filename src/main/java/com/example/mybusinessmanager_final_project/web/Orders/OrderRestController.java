package com.example.mybusinessmanager_final_project.web.Orders;
import com.example.mybusinessmanager_final_project.model.view.orders.OrderViewModel;
import com.example.mybusinessmanager_final_project.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public class OrderRestController {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderRestController(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }
    @GetMapping("orders/api")
    public ResponseEntity<List<OrderViewModel>> getAllOrders() {
        List<OrderViewModel> reportViewModels
                = orderRepository.
                findAll().
                stream().
                map(oe -> {
                    OrderViewModel orderViewModel = modelMapper.map(oe, OrderViewModel.class);
                    orderViewModel.setName(oe.getName());
                    return orderViewModel;
                }).
                collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(reportViewModels);
    }

}
