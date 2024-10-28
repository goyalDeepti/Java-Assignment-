package com.example.demo.ExceptionUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(value = CustomerNotFoundException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public @ResponseBody String handleCustomerNotFoundException(CustomerNotFoundException ex) {
         return "Customer does not exist";
        }

    @ExceptionHandler(value = InvalidMonthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidMonthException(InvalidMonthException ex) {
        return "Month should be between Jan to Dec, please check";
    }
}
