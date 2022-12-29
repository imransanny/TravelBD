package edu.ewubd.travelbd119;


public class Event {

    public String key="";
    public String user_name="";
    public String email="";
    public String phone="";
    public String password="";
    public String re_enter_pass="";
    public String Simage="";

    public Event(){

    }
    public Event(String key, String user_name, String email, String phone, String password,String re_enter_pass, String Simage){
      this.key = key;
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.re_enter_pass = re_enter_pass;
        this.Simage = Simage;
    }


}
