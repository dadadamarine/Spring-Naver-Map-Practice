package com.mappractice.demo.exception;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException(){
        super();
    }

    public ImageNotFoundException(String message){
        super(message);
    }
}
