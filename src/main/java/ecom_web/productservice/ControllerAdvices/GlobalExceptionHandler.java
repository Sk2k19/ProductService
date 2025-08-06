package ecom_web.productservice.ControllerAdvices;

import ecom_web.productservice.exceptionDto.ExceptionHnadlerDto;
import ecom_web.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionHnadlerDto> handleProductNotFoundException() {
        ExceptionHnadlerDto exceptionHnadlerDto = new ExceptionHnadlerDto();
        exceptionHnadlerDto.setMessage("Product not found");
        exceptionHnadlerDto.setResolution("Try with a valid id");

        return new ResponseEntity<>(
                exceptionHnadlerDto,
                HttpStatus.NOT_FOUND
        );
    }
}
