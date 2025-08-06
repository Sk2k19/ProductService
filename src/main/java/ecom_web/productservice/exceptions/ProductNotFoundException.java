package ecom_web.productservice.exceptions;

public class ProductNotFoundException extends Exception{
    private Long productId;
    public ProductNotFoundException(Long id, String message) {
        super(message);
        this.productId = id;
    }
}
