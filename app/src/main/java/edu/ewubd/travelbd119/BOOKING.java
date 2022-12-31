package edu.ewubd.travelbd119;

public class BOOKING {
   private String Hotel_name ;
    private  String name_b ;
    private String phone_b ;
    private String email_b ;
    private  String address_b ;
    private  String date_b ;

    public BOOKING(){

    }


    public BOOKING(String Hotel_name, String name_b, String phone_b, String email_b, String address_b,String date_b){
        this.Hotel_name = Hotel_name;
        this.name_b = name_b;
        this.phone_b = phone_b;
        this.email_b = email_b;
        this.address_b = address_b;
        this.date_b = date_b;

    }


    public String getHotel_name() {
        return Hotel_name;
    }

    public void setHotel_name(String hotel_name) {
       this.Hotel_name = hotel_name;
    }

    public String getName_b() {
        return name_b;
    }

    public void setName_b(String name_b) {
        this.name_b = name_b;
    }

    public String getPhone_b() {
        return phone_b;
    }

    public void setPhone_b(String phone_b) {
        this.phone_b = phone_b;
    }

    public String getEmail_b() {
        return email_b;
    }

    public void setEmail_b(String email_b) {
        this.email_b = email_b;
    }

    public String getAddress_b() {
        return address_b;
    }

    public void setAddress_b(String address_b) {
        this.address_b = address_b;
    }

    public String getDate_b() {
        return date_b;
    }

    public void setDate_b(String date_b) {
        this.date_b = date_b;
    }
}
