package ecom_web.productservice.controllers;

import ecom_web.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RestController("/products")
public class ProductController {

    // localhost:8080/products
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return new Product();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @PostMapping
    public Product createProduct(Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        return;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, Product product) {
        return new Product();

    }
    @PatchMapping("/{id}")
    public Product patialUpdateProduct(@PathVariable("id") Long productId, Product product) {
        return new Product();

    }
}