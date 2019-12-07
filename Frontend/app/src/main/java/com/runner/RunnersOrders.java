package com.runner;

public class RunnersOrders {


    public String firstname;

    public String lastname;

    public String LocationName;

    public String item_name;

    public String Finaltotal;

    public String userNetID;

    public RunnersOrders(String firstname, String lastname, String locationName, String item_name, String finalTotal, String netid) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.LocationName = locationName;
        this.item_name = item_name;
        this.Finaltotal = finalTotal;
        this.userNetID = netid;

    }

}
