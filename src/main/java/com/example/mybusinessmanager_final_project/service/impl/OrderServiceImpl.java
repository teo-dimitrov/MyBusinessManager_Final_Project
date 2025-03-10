package com.example.mybusinessmanager_final_project.service.impl;

import com.example.mybusinessmanager_final_project.model.binding.OrderAddBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.*;
import com.example.mybusinessmanager_final_project.model.entity.enums.UserRoleEnum;
import com.example.mybusinessmanager_final_project.model.service.OrderAddServiceModel;
import com.example.mybusinessmanager_final_project.model.service.OrderEditServiceModel;
import com.example.mybusinessmanager_final_project.model.view.orders.OrderDetailsView;
import com.example.mybusinessmanager_final_project.model.view.orders.OrderSummaryView;
import com.example.mybusinessmanager_final_project.model.view.orders.OrderViewModel;
import com.example.mybusinessmanager_final_project.model.view.reports.ReportSummaryView;
import com.example.mybusinessmanager_final_project.repository.OrderRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.example.mybusinessmanager_final_project.service.OrderService;
import com.example.mybusinessmanager_final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderViewModel> getAllOrders() {
        return orderRepository.findAll().stream().sorted(Comparator.comparing(BaseEntity::getModified).reversed())
                .map(this::map).collect(Collectors.toList());
    }



    @Override
    public OrderDetailsView findById(Long id, String currentUser) {
        return this.orderRepository
                .findById(id).map(r -> mapDetailsView(currentUser, r)).get();
    }

    @Override
    public boolean isOwner(String username, Long id) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(id);
        Optional<UserEntity> caller = userRepository.findByUsername(username);

        if (orderOptional.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            OrderEntity orderEntity = orderOptional.get();
            return isAdmin(caller.get()) || orderEntity.getAuthor().getUsername().equals(username);
        }
    }

    @Override
    public OrderAddServiceModel addOrder(OrderAddBindingModel orderAddBindingModel, String ownerId) {
        UserEntity userEntity = userRepository.findByUsername(ownerId).orElseThrow();
        OrderAddServiceModel orderAddServiceModel = modelMapper.map(orderAddBindingModel, OrderAddServiceModel.class);
        OrderEntity newOrder = modelMapper.map(orderAddServiceModel, OrderEntity.class);
        newOrder.setAuthor(userEntity);

        OrderEntity savedOrder = orderRepository.save(newOrder);

        return modelMapper.map(savedOrder, OrderAddServiceModel.class);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void editOrder(OrderEditServiceModel orderModel) {
        OrderEntity orderEntity = orderRepository
                .findById(orderModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Order with id " + orderModel.getId() + " not found!"));

        orderEntity
                .setName(orderModel.getName())
                .setDescription(orderModel.getDescription())
                .setDeadLine(orderModel.getDeadLine())
                .setModified(Instant.now());

        orderRepository.save(orderEntity);
    }

    private ReportSummaryView map(ReportEntity reportEntity) {
        return this.modelMapper.map(reportEntity, ReportSummaryView.class);
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }


    private OrderViewModel map(OrderEntity orderEntity) {
        return this.modelMapper.map(orderEntity, OrderViewModel.class);
    }


    private OrderDetailsView mapDetailsView(String currentUser, OrderEntity order) {

        OrderDetailsView orderDetailsView = this.modelMapper.map(order, OrderDetailsView.class);

        orderDetailsView.setCanDelete(isOwner(currentUser, order.getId()));
        orderDetailsView.setOwner(isOwner(currentUser, order.getId()));

        return orderDetailsView;
    }
}
