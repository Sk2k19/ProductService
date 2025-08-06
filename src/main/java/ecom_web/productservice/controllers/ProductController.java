package ecom_web.productservice.controllers;

import ecom_web.productservice.exceptions.CategoryNotFoundException;
import ecom_web.productservice.exceptions.ProductNotFoundException;
import ecom_web.productservice.models.Product;
import ecom_web.productservice.services.ProductService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(@Qualifier("productServiceDBImplementation")ProductService productService) {
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
    public Product createProduct(@RequestBody Product product) throws CategoryNotFoundException {

        return productService.createProduct(product);
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
    public Product partialUpdateProduct(@PathVariable("id") Long productId,@RequestBody Product product) {
        return productService.partialUpdateProduct(productId,product);

    }
}