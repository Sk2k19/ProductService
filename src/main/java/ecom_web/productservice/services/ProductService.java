package ecom_web.productservice.services;

import ecom_web.productservice.exceptions.CategoryNotFoundException;
import ecom_web.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public Product getProduct(Long productId);
    public List<Product> getAllProducts();
    public Product createProduct(Product product) throws CategoryNotFoundException;
    public void deleteProduct(Long productId);
    public Product updateProduct(Long productId, Product product);
    public Product partialUpdateProduct(Long productId, Product product);
}
