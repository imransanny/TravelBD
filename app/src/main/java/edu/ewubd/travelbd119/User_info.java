package edu.ewubd.travelbd119;

public class User_info {

    public String user_name, email,phone,password, re_enter_pass;

    public User_info(){

    }
    public User_info(String user_name, String email, String phone, String password,String re_enter_pass){
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.re_enter_pass = re_enter_pass;
    }


}
