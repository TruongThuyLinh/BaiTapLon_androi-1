package com.example.baitaplon.drawerLayout.model;

import java.io.Serializable;

public class giohangmodel implements Serializable {
    private  String name;
    private  String price;
    private  int image;
    private int quantity; // Số lượng sản phẩm
    private boolean isSelected;
    public  giohangmodel(int imageResource, String name, String price, int quantity){
        this.image=imageResource;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.isSelected=isSelected;
    }
    public  String getName(){
        return  name;
    }
    public  String getPrice(){
        return  price;
    }
    public  int getImage(){
        return  image;
    }
    // Getter cho số lượng sản phẩm
    public int getQuantity() {
        return quantity;
    }

    // Setter để cập nhật số lượng sản phẩm
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

}