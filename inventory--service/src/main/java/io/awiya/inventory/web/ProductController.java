package io.awiya.inventory.web;

import io.awiya.inventory.entities.Product;
import io.awiya.inventory.exception.ProductNotFoundException;
import io.awiya.inventory.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;


    @GetMapping
    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Product with the id: %s not found",id)));
    }
}
