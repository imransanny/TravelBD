package edu.ewubd.travelbd119;


public class Event {

    public String key="", user_name="", email="",phone="",password="", re_enter_pass="", Simage="";

    public Event(){

    }
    public Event(String key, String user_name, String email, String phone, String password,String re_enter_pass, String Simage){
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.re_enter_pass = re_enter_pass;
        this.Simage = Simage;
    }


}
