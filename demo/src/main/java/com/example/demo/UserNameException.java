package com.example.demo;

public class UserNameException extends Exception{
    public UserNameException() {
        super();
    }
    //Overloading yapılmıştır
    public UserNameException(String message) {
        super(message);
    }
}
