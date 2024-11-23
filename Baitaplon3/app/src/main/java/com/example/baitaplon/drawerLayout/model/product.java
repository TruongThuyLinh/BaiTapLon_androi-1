package com.example.baitaplon.drawerLayout.model;

public class product {
    private String name;
    private String price;
    private int image;
    public  product(String name,String price,int image){
        this.image=image;
        this.name=name;
        this.price=price;
    }
    public  String getName(){
        return  name;
    }
    public  String getPrice(){
        return  price;
    }
    public int getImage(){
        return  image;
    }
}