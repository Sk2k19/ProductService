package ecom_web.productservice.controllers;

import ecom_web.productservice.exceptions.ProductNotFoundException;
import ecom_web.productservice.models.Product;
import ecom_web.productservice.services.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }
    // localhost:8080/products
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId)throws ProductNotFoundException {

            return new ResponseEntity<Product>(
                    productService.getProduct(productId),
                    HttpStatus.OK
            );

    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        return;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return new Product();

    }
    @PatchMapping("/{id}")
    public Product patialUpdateProduct(@PathVariable("id") Long productId,@RequestBody Product product) {
        return new Product();

    }
}