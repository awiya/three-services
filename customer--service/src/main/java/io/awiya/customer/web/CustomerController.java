package io.awiya.customer.web;

import io.awiya.customer.entities.Customer;
import io.awiya.customer.exception.CustomerNotFoundException;
import io.awiya.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> allCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(String.format("Customer with the id: %s not found",id)));
    }

}
