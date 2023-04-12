package io.awiya.order.services;


import io.awiya.order.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory-service")
@Service
public interface InventoryRestClientService {

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable Long id);

    @GetMapping("/api/products")
    public List<Product> allProducts();
}
