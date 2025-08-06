package ecom_web.productservice.exceptions;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException( String message) {
        super(message);
    }
}
