package ecom_web.productservice.services;

import ecom_web.productservice.exceptions.CategoryNotFoundException;
import ecom_web.productservice.models.Category;
import ecom_web.productservice.models.Product;
import ecom_web.productservice.repositories.CategoryRepository;
import ecom_web.productservice.repositories.PorductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("productServiceDBImplementation")
@Primary
public class ProductServiceDBImplementation implements ProductService {

    CategoryRepository categoryRepository;
    PorductRepository productRepository;
    public ProductServiceDBImplementation(PorductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProduct(Long productId) {
        Optional<Product> OpProduct = productRepository.findById(productId);
        if(OpProduct.isEmpty()){
            throw new RuntimeException("Product with id " + productId + " not found");
        }
        return OpProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        if(product.getCategory() != null) {
            if(product.getCategory().getId() == null){
                Category category = product.getCategory();
                String name  = category.getName();
                Optional<Category> OpCategory = categoryRepository.findByName(name);
                if(OpCategory.isEmpty()){
                    Category newCategory = categoryRepository.save(category);
                    product.setCategory(newCategory);
                }else{
                    product.setCategory(OpCategory.get());
                }

            }else{
                Optional<Category> OpCategory = categoryRepository.findById(product.getCategory().getId());
                if(OpCategory.isEmpty()){
                    throw new CategoryNotFoundException("Category with id " + product.getCategory().getId() + " not found");
                }else{
                    product.setCategory(OpCategory.get());
                }
            }
        }else{
            throw new CategoryNotFoundException("Category not found");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optProduct = productRepository.findById(productId); // check if product exists
        if(optProduct.isEmpty()){
            throw new RuntimeException("Product with id " + productId + " not found");
        }
        productRepository.deleteById(productId);

    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> OpProduct = productRepository.findById(productId);
        if(OpProduct.isEmpty()) {
            throw new RuntimeException("Product with id " + productId + " not found");
        }
        Product toBeUpdatedProduct = OpProduct.get();
        toBeUpdatedProduct.setTitle(product.getTitle());
        toBeUpdatedProduct.setDescription(product.getDescription());
        toBeUpdatedProduct.setPrice(product.getPrice());

        // step for updating category;

        // checking before update the category of the product.
        // if category is null then we are not updating the category
        // if category is not null null then we check from db is that category exists or not
        // if category exists then we are updating the category
        // else we create new ctegory as per the product and then update that.

        Category category = product.getCategory();
        String name = category.getName();
        Optional<Category> OptCategory = categoryRepository.findByName(name);

        // if category is null then we are not updating the category
        if(!OptCategory.isEmpty()){
            toBeUpdatedProduct.setCategory(category);
        }else{
            Category newCategory = new Category();
            newCategory.setName(name);
             categoryRepository.save(newCategory);
            toBeUpdatedProduct.setCategory(newCategory);
        }

        return productRepository.save(toBeUpdatedProduct);
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
        return null;
    }
}
