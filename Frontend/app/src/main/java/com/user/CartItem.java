package com.user;

public class CartItem {
    public String itemName;

    public Double itemPrice;

    public String locationName;

    public Double total;

    CartItem(String itmName, String itmPrice, String locName){
        itemPrice = Double.parseDouble(itmPrice);
        itemName = itmName;
        locationName = locName;
    }

    @Override
    public String toString(){
        return itemName + " " + itemPrice + " " + locationName;
    }

    public Double getTotal(){
        return total;
    }

    public void setTotal(Double tot){
        tot = total;
    }


}
