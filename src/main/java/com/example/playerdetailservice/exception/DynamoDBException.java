package com.example.playerdetailservice.exception;

public class DynamoDBException extends RuntimeException{
    public DynamoDBException(String errorMessage){
        super(errorMessage);
    }
}
