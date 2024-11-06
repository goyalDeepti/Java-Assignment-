package com.example.demo.ExceptionUtils;

public class TransactionNotFound extends RuntimeException{

    public TransactionNotFound(String msg){
        super(msg);
    }
}
