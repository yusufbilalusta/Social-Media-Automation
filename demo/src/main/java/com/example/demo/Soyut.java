package com.example.demo;

import java.util.ArrayList;

public abstract class Soyut {
    public static ArrayList<Kullanici> kullanicilarArrayList = new ArrayList<>();
    public abstract void kullaniciEkle(Kullanici kullanici)throws PasswordException, UserNameException;
    public abstract void kullaniciSil(String kullaniciadi, String sifre);
    public abstract void kullaniciGir(String kullaniciAdi, String sifre) throws PasswordException, UserNameException;
}
