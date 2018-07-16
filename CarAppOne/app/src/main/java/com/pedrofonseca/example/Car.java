package com.pedrofonseca.example;

/**
 * Created by Pedro Fonseca on 10/02/2016.
 */
public class Car {

    //VARIAVEIS
    private String model;
    private String brand;
    private int photo;

    //CONSTRUTORES
    public Car(){}
    public Car(String m, String b, int p){
        model = m;
        brand = b;
        photo = p;
    }

    //GETERS AND SETERS
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

}
