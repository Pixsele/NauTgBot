package ru.vorobyev.NauJava_TgBotDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vorobyev.NauJava_TgBotDelivery.entity.OrderEntity;
import ru.vorobyev.NauJava_TgBotDelivery.repository.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRESTController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRESTController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/getByStatusAndAddress")
    public List<OrderEntity> findByStatusAndAddress(@RequestParam String status, @RequestParam String address) {
        return orderRepository.findByStatusAndAddress(status, address);
    }

    @GetMapping("/getDeliveredOrdersByCourierId")
    public List<OrderEntity> findDeliveredOrdersByCourierId(@RequestParam Long courierId) {
        return orderRepository.findDeliveredOrdersByCourierId(courierId);
    }
}
