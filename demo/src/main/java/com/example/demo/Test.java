package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Test extends Application {
    private Users kullaniciUsers= new Users();
    private ArrayList<String> paylasimlar = new ArrayList<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Kullanici Giriş Ekranı için Izgara şeklinde olan GridPane en uygunudur
        primaryStage.setTitle("Kullanıcı Giriş Ekranı");
        GridPane grid = new GridPane();

        // Yazıların iç içe girmemesi için setPadding metodu ile düzenleme yapıldı
        grid.setPadding(new Insets(10,10,10,10));


        //Eklenen Node ların yatay da ve Dikeyde bitişik olmaması için 10 piksel boşluk bırakılmıştır
        grid.setHgap(10);
        grid.setVgap(10);


        //Kullanıcı Ekranı:

        //Kullanıcı Adı Labelı
        Label kullaniciadiLabel = new Label("Kullaıcı Adı:");
        GridPane.setConstraints(kullaniciadiLabel,0,0); //setConstraints metodu GridPane in içerisinde
        grid.getChildren().add(kullaniciadiLabel);            //konumu ayarlar

        //Şifre Labelı
        Label sifreLabel = new Label("Şifre");
        GridPane.setConstraints(sifreLabel,0,1);
        grid.getChildren().add(sifreLabel);

        //Kullanıcı Adı Textfieldı
        TextField kullaciadiTextField = new TextField();
        GridPane.setConstraints(kullaciadiTextField,1,0);
        grid.getChildren().add(kullaciadiTextField);

        //Şifre TextFieldı
        PasswordField sifrePasswordField = new PasswordField();
        GridPane.setConstraints(sifrePasswordField,1,1);
        grid.getChildren().add(sifrePasswordField);

        //Hata Labelı
        //Default olarak boştur ancak hata meydana geldiğinde bu labela yansır
        Label hataLabel = new Label("");
        GridPane.setConstraints(hataLabel,1,3);
        grid.getChildren().add(hataLabel);

        //Kayıt Butonu
        Button kayitButton = new Button("Kayıt");
        GridPane.setConstraints(kayitButton,0,2);
        grid.getChildren().add(kayitButton);


        //Giriş Butonu
        Button girisButton = new Button("Giriş");
        GridPane.setConstraints(girisButton,1,2);
        grid.getChildren().add(girisButton);

        //Sil Butonu
        Button silButton = new Button("Kullanici Kaldir");
        GridPane.setConstraints(silButton,2,2);
        grid.getChildren().add(silButton);


        //Kayıt Butonu Action
        //kullaniciadiString final yapılmıştır.Çünkü daha sonra paylasim yapılırken cagirilacaktir.
        kayitButton.setOnAction(actionEvent -> {
            final String kullaniciadiString = kullaciadiTextField.getText();
            String sifreString = sifrePasswordField.getText();
            Kullanici yeniKullanici = new Kullanici(kullaniciadiString,sifreString);
            try {
                kullaniciUsers.kullaniciEkle(yeniKullanici);
                hataLabel.setText("Kayıt Başarı ile Gerçekleştirildi");
            } catch (PasswordException e) {
                hataLabel.setText(e.getMessage());
            } catch (UserNameException e) {
                hataLabel.setText(e.getMessage());
            }

            kullaciadiTextField.clear();
            sifrePasswordField.clear();
        });

        //Giriş Butonu Action
        girisButton.setOnAction(actionEvent -> {
            String kullaniciadiString = kullaciadiTextField.getText();
            String sifreString = sifrePasswordField.getText();
            try {
                kullaniciUsers.kullaniciGir(kullaniciadiString,sifreString);
                hataLabel.setText("Giriş Başarılı...");
                kullaciadiTextField.clear();
                sifrePasswordField.clear();
                //Kullancı Sayfasına Girer
                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("Social Media");
                BorderPane borderPane = new BorderPane();
                Scene scene1 = new Scene(borderPane, 400, 400);

                // Ana sayfa ekranı
                VBox anaSayfa = new VBox(10);
                anaSayfa.setPadding(new Insets(10, 10, 10, 10));
                ListView<String> paylasimListesi = new ListView<>();
                paylasimListesi.getItems().addAll(paylasimlar);

                anaSayfa.getChildren().addAll(new Label("Ana Sayfa"), paylasimListesi);

                // Paylaşım ekranı
                VBox paylasimEkran = new VBox(10);
                paylasimEkran.setPadding(new Insets(10, 10, 10, 10));
                TextArea paylasimTextArea = new TextArea();
                Button paylasButton = new Button("Paylaş");
                paylasButton.setOnAction(e -> {
                    String paylasim = paylasimTextArea.getText();
                    if (!paylasim.isEmpty()) {
                        paylasim +=" ["+ kullaniciadiString+"]";
                        paylasimlar.add(paylasim);
                        paylasimListesi.getItems().add(0, paylasim); // En üste eklemek için
                        paylasimTextArea.clear();
                    }
                });

                paylasimEkran.getChildren().addAll(new Label("Paylaşım Ekranı"), paylasimTextArea, paylasButton);

                // Menü
                MenuBar menuBar = new MenuBar();
                Menu anaSayfaMenu = new Menu("Ana Sayfa");
                Menu paylasimMenu = new Menu("Paylaşım");
                menuBar.getMenus().addAll(anaSayfaMenu, paylasimMenu);

                // Ana sayfa ve paylaşım menüleri için içerik
                MenuItem anaSayfaItem = new MenuItem("Ana Sayfa");
                MenuItem paylasimItem = new MenuItem("Paylaşım");

                anaSayfaItem.setOnAction(e -> borderPane.setCenter(anaSayfa));
                paylasimItem.setOnAction(e -> borderPane.setCenter(paylasimEkran));

                anaSayfaMenu.getItems().add(anaSayfaItem);
                paylasimMenu.getItems().add(paylasimItem);

                borderPane.setTop(menuBar);
                borderPane.setCenter(anaSayfa);

                secondaryStage.setScene(scene1);
                secondaryStage.show();
            }
            catch (PasswordException e){
                hataLabel.setText(e.getMessage());
            }
            catch (UserNameException e){
                hataLabel.setText(e.getMessage());
            }



        });

        //Kullanıcı Kaldır Butonu Action
        silButton.setOnAction(actionEvent -> {
            String kullaniciadiString = kullaciadiTextField.getText();
            String sifreString = sifrePasswordField.getText();
            kullaniciUsers.kullaniciSil(kullaniciadiString,sifreString);
            hataLabel.setText("Kayıt Başarı ile Silindi");
            kullaciadiTextField.clear();
            sifrePasswordField.clear();
        });



        Scene scene = new Scene(grid,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
