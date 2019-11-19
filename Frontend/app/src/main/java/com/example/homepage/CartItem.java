package com.example.homepage;

public class CartItem {
    public String itemName;

    public Double itemPrice;

    public String locationName;

    CartItem(String itmName, String itmPrice, String locName){
        itemPrice = Double.parseDouble(itmPrice);
        itemName = itmName;
        locationName = locName;
    }

    @Override
    public String toString(){
        return itemName + " " + itemPrice + " " + locationName;
    }


}
