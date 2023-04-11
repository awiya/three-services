package io.awiya.inventory.web;

import io.awiya.inventory.entities.Product;
import io.awiya.inventory.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;


    @GetMapping
    public List<Product> allProducts(){
        return repository.findAll();
    }
}
