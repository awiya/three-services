package io.awiya.order.web;


import io.awiya.order.entities.Order;
import io.awiya.order.model.Customer;
import io.awiya.order.model.Product;
import io.awiya.order.repository.OrderRepository;
import io.awiya.order.repository.ProductItemRepository;
import io.awiya.order.services.CustomerRestClientService;
import io.awiya.order.services.InventoryRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OderRestController {

    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClientService customerRestClientService;
    private final InventoryRestClientService inventoryRestClientService;



    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}
