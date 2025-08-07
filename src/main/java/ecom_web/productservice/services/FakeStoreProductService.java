package ecom_web.productservice.services;

import ecom_web.productservice.dtos.FakeStoreProductDto;
import ecom_web.productservice.models.Category;
import ecom_web.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(Long productId) {
//        RestTemplate restTemplate = new RestTemplate();
// used to make calls to 3rd party APIs
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+ productId,
                FakeStoreProductDto.class);
        // convert from FakeStoreProductDto to Product

        return convertInToProduct(fakeStoreProductDto);
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        FakeStoreProductDto fakeStoreProductDto[] = restTemplate.getForObject(
                "https://fakestoreapi.com/products",FakeStoreProductDto[].class);
        ArrayList<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDto) {
            products.add(convertInToProduct(fakeStoreProductDto1));

        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        //convert from Product to FakeStoreProductDto
        // send to 3rd party API
        // get back from that API
        // convert from FakeStoreProductDto to Product
        // return Product
        FakeStoreProductDto fakeStoreProductDto = convertInToFakeStoreProductDto(product);
         FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject(
                 "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);

        return convertInToProduct(fakeStoreProductDto1);
    }

    @Override
    public void deleteProduct(Long productId) {
        // check if product exists
        // if exists, delete
        restTemplate.delete("https://fakestoreapi.com/products/" + productId);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        return null;
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {

        return null;
    }
    public Product convertInToProduct(FakeStoreProductDto fakeStoreProductDto) {

        Product product = new Product();
        product.setCategory(new Category());
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }
    public FakeStoreProductDto convertInToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImage());
        return fakeStoreProductDto;
    }
}
