package io.awiya.order.services;


import io.awiya.order.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
@Service
public interface CustomerRestClientService {

    @GetMapping("/api/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id);

    @GetMapping("/api/customers")
    public List<Customer> allCustomers();
}
