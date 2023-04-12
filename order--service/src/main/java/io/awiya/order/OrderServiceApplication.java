package io.awiya.order;

import io.awiya.order.entities.Order;
import io.awiya.order.entities.ProductItem;
import io.awiya.order.enums.OrderStatus;
import io.awiya.order.model.Customer;
import io.awiya.order.model.Product;
import io.awiya.order.repository.OrderRepository;
import io.awiya.order.repository.ProductItemRepository;
import io.awiya.order.services.CustomerRestClientService;
import io.awiya.order.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static io.awiya.order.enums.OrderStatus.CREATED;
import static io.awiya.order.enums.OrderStatus.PENDING;
import static java.lang.String.format;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClientService customerRestClientService,
            InventoryRestClientService inventoryRestClientService) {

        return args -> {

            List<Customer> allCustomers = customerRestClientService.allCustomers();
            List<Product> allProducts = inventoryRestClientService.allProducts();

            Long customerId = 1L;
            Random random = new Random();

            Customer customer = customerRestClientService.getCustomerById(customerId);
            System.out.println("\n");
            System.out.println(format("================= Customer with id: %s =================",customerId));
            System.out.println(customer);
            System.out.println();
            System.out.println(format("================= All Customers ========================",customerId));
            allCustomers.forEach(System.out::println);
            System.out.println("\n");


            for (int i = 0; i < 20; i++) {
                Long randIdCustomer = allCustomers.get(random.nextInt(allCustomers.size())).getId();
                Order order = Order.builder()
                        .customerId(randIdCustomer)
                        .status(Math.random() > 0.5 ? PENDING : CREATED)
                        .customer(customerRestClientService.getCustomerById(randIdCustomer))
                        .createdAt(new Date())
                        .build();
                Order savedOrder = orderRepository.save(order);
                for (int j = 0; j < allProducts.size(); j++) {
                    if (Math.random() > 0.70) {
                        ProductItem productItem = ProductItem.builder()
                                .order(savedOrder)
                                .productId(allProducts.get(j).getId())
                                .price(allProducts.get(j).getPrice())
                                .quantity(1 + random.nextInt(10))
                                .discount(Math.random())
                                .build();
                        productItemRepository.save(productItem);
                    }

                }
            }
        };
    }

}
