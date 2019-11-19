package com.example.homepage;

public class CartItem {
    public String itemName;

    public Double itemPrice;



    CartItem(String itmName, String itmPrice){
        itemPrice = Double.parseDouble(itmPrice);
        itemName = itmName;
    }

    @Override
    public String toString(){
        return itemName + " " + itemPrice;
    }


}
