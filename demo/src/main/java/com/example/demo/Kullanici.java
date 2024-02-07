package com.example.demo;

//Arraylistte kullanicilar bu sınıftan türetilecektir.

public class Kullanici {
    private String kullaniciadi;
    private String sifre;

    //Constructor oluşturuldu.
    public Kullanici(String kullaniciadi, String sifre) {
        this.kullaniciadi = kullaniciadi;
        this.sifre = sifre;
    }

    //Değişkenlerin getter ve setter metodlarını üretildi.
    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public void setKullaniciadi(String kullaniciadi) {
        this.kullaniciadi = kullaniciadi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
