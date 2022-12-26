package edu.ewubd.travelbd119;

public class Admin_info {
    public String user_name, email,phone,password, re_enter_pass,sImage;

    public Admin_info(){

    }
    public Admin_info(String user_name, String email, String phone, String password,String re_enter_pass,String sImage){
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.re_enter_pass = re_enter_pass;
        this.sImage = sImage;
    }


}

