package ecom_web.productservice.controllers;

import ecom_web.productservice.exceptions.CategoryNotFoundException;
import ecom_web.productservice.exceptions.ProductNotFoundException;
import ecom_web.productservice.models.Category;
import ecom_web.productservice.models.Product;
import ecom_web.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    ProductController productController ;
    @MockBean
    @Qualifier("productServiceDBImplementation")
    ProductService productService;
    @Test
    void getSingleProductTest() throws ProductNotFoundException {
        // Arrange
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Nokia1");
        expectedProduct.setDescription("Strongest phone on earth.");
        expectedProduct.setPrice(19990.99);

        when(productService.getProduct(1L))
                .thenReturn(expectedProduct);

        // Act
        Product actualProduct = productController.getProduct(1L);
        // Assert
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getAllProductsTest() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setTitle("Nokia1");
        p1.setDescription("Strongest phone on earth.");
        p1.setPrice(19990.99);

        Product p2 = new Product();
        p2.setId(2L);
        p2.setTitle("Samsung");
        p2.setDescription("best phone on earth.");
        p2.setPrice(15990.99);
        Product p3 = new Product();
        p3.setId(2L);
        p3.setTitle("Oppo");
        p3.setDescription("best camera phone on earth.");
        p3.setPrice(90990.99);

        List<Product> expectedProducts  =new ArrayList<>();
        expectedProducts.add(p1);
        expectedProducts.add(p2);
        expectedProducts.add(p3);

        when(productService.getAllProducts())
                .thenReturn(expectedProducts);

        List<Product> actualProductts = productController.getAllProducts();

         assertEquals(expectedProducts.size(), actualProductts.size());
         for(int i=0; i<expectedProducts.size(); i++) {
             assertEquals(expectedProducts.get(i).getId(), actualProductts.get(i).getId());
         }
    }

    @Test
    void createProduct() throws CategoryNotFoundException {
        Product product = new Product();

        product.setTitle("Italian Tiles");
        product.setDescription("Strongest tiles on earth.");
        product.setPrice(190.99);
        Category category = new Category();
        category.setName("Furniture");
        product.setCategory(category);

        ProductTestStub productTestStub = new ProductTestStub();
        Product expectedProduct = productTestStub.createProduct(product);



        when(productService.createProduct(product))
                .thenReturn(expectedProduct);

        Product actualProduct = productController.createProduct(product);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void deleteProduct() throws ProductNotFoundException {
//        Long productId = 1L;
//        ProductTestStub productTestStub = new ProductTestStub();
//
//        assertThrows(ProductNotFoundException.class, () -> productController.deleteProduct(productId));
//
    }

    @Test
    void updateProduct() {
    }

    @Test
    void partialUpdateProduct() {
    }
}