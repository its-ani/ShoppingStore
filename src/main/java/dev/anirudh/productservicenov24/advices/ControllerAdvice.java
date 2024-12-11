package dev.anirudh.productservicenov24.advices;

import dev.anirudh.productservicenov24.dtos.ErrorDTO;
import dev.anirudh.productservicenov24.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

//used to through local exception through this Controller Advice. Once place for exceptions across your code.
@org.springframework.web.bind.annotation.RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
