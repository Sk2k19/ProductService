package ecom_web.productservice.controllers;

import ecom_web.productservice.exceptions.ProductNotFoundException;
import ecom_web.productservice.models.Category;
import ecom_web.productservice.models.Product;

import java.util.HashMap;

public class ProductTestStub {
    HashMap<Long, Product> productMap = new HashMap<>();
    HashMap<Long,Category> categoryMap = new HashMap<>();
    Long lastProductId = 0L;
    Long lastCategoryId = 0L;
    public Product createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setId(lastProductId++);
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setImage(product.getImage());


        // check catefory exists or not
        if(categoryMap.containsKey(product.getCategory().getId())){
            newProduct.setCategory(categoryMap.get(product.getCategory().getId()));
        }else {
            Category newCategory = new Category();
            newCategory.setId(lastCategoryId++);
            newCategory.setName(product.getCategory().getName());
            newProduct.setCategory(newCategory);
            categoryMap.put(lastCategoryId, newCategory);
        }
        productMap.put(newProduct.getId(), newProduct);
        return newProduct;
    }
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        if(productMap.containsKey(productId)){
            productMap.remove(productId);
            lastProductId--;

        }else{
            throw new ProductNotFoundException(productId,"Product with id " + productId + " not found");
        }
    }
}
