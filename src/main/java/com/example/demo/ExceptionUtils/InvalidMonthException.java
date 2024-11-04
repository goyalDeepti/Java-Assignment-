package com.example.demo.ExceptionUtils;

public class InvalidMonthException extends RuntimeException{
   public InvalidMonthException(String msg){
       super(msg);
    }
    public InvalidMonthException(){}

}
