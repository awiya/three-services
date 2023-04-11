package io.awiya.customer.web;

import io.awiya.customer.entities.Customer;
import io.awiya.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerConfigTestController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> allCustomers(){
        return customerRepository.findAll();
    }

}
