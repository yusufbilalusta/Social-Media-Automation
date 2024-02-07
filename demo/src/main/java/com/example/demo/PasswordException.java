package com.example.demo;

//Kullanici giris yaparken herhangi bir hata yaparsa bu sınıftan hata fırlatılacaktır.

public class PasswordException extends Exception{

    public PasswordException() {
        super();
    }
    //Overloading yapılmıştır
    public PasswordException(String message) {
        super(message);
    }
}
