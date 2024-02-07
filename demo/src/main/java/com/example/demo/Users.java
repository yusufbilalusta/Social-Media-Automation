package com.example.demo;

//Users class ında kullanicilarArrayList üzerinde işlemler yapılacaktır
//Kullanici ekle / sil metotları ile arraylist üzerinde oynama yapılabilir
//Kullanicigir metodu ile uygulamaya giriş yapılabilir veya hatalı bilgi girilmesi durumunda kullanıcıya mesaj gonderilebilir

public class Users extends Soyut {

    //Boş kullanıcı ekleyemesin diye bir bakıver
    @Override
    public void kullaniciEkle(Kullanici kullanici)throws PasswordException, UserNameException {

        if (kullanici.getSifre().equals("") || kullanici.getKullaniciadi().equals("")){
            throw new PasswordException("Lutfen kullanıcı adı ve şifre giriniz...");
        }

        kullanicilarArrayList.add(kullanici);

    }

    @Override
    public void kullaniciSil(String kullaniciadi, String sifre) {
        //Sadece kullanıcı adı ve şifre alabildiğimiz için ve arraylistte Kullanıcı Objesi depoladığımız için
        //Bu objeyi foreach dongusuyle arayarak bir tane null değer atıyoruz.
        Kullanici kaldirilacakKullanici = null;

        for (Kullanici kullanici : kullanicilarArrayList) {
            if (kullanici.getKullaniciadi().equals(kullaniciadi) && kullanici.getSifre().equals(sifre)) {
                kaldirilacakKullanici = kullanici;
                break;
            }
        }

        if (kaldirilacakKullanici != null) {
            kullanicilarArrayList.remove(kaldirilacakKullanici);
        }
    }

    @Override
    public void kullaniciGir(String kullaniciAdi, String sifre) throws PasswordException, UserNameException {

        for (Kullanici a : kullanicilarArrayList) {

            if (a.getKullaniciadi().equals(kullaniciAdi) && a.getSifre().equals(sifre)) {
                return;
            }
            else if (a.getKullaniciadi().equals(kullaniciAdi) && !a.getSifre().equals(sifre)) {
                throw new PasswordException("Şifre Hatalıdır....");

            } else if (!a.getKullaniciadi().equals(kullaniciAdi) && a.getSifre().equals(sifre)) {
                throw new UserNameException("Kullanıcı Adı Hatalıdır....");
            }
        }
        throw new UserNameException("Hem Kullanıcıadı Hem Şifre Yanlış....");
    }
}
