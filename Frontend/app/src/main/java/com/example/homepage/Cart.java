package com.example.homepage;

public class Cart {

    public String itemName;

    public int itemPrice;

    public int quantity;


    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int q){
        quantity = q;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String name){
        itemName = name;
    }

    public void setItemPrice(String itmName){
        itemPrice = Integer.parseInt(itmName);
    }
    public int getItemPrice(){
        return itemPrice;
    }






}
