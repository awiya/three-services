package io.awiya.order.web;


import io.awiya.order.entities.Order;
import io.awiya.order.exception.OrderNotFoundException;
import io.awiya.order.model.Customer;
import io.awiya.order.model.Product;
import io.awiya.order.repository.OrderRepository;
import io.awiya.order.repository.ProductItemRepository;
import io.awiya.order.services.CustomerRestClientService;
import io.awiya.order.services.InventoryRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OderRestController {

    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClientService customerRestClientService;
    private final InventoryRestClientService inventoryRestClientService;


    @GetMapping
    public List<Order> allOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/fullOrder/{orderId}")
    public Order getOrder(@PathVariable Long orderId){

        Order order=orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(String.format("[orderId: %s]This Order is not found")));

        Customer customer=customerRestClientService.getCustomerById(order.getCustomerId());

        order.setCustomer(customer);

        order.getProductItems().forEach(prodItem->{
            Product product=inventoryRestClientService.getProductById(prodItem.getProductId());
            prodItem.setProduct(product);
        });
        return order;
    }
}
