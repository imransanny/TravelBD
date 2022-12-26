
package edu.ewubd.travelbd119;


public class Hotels_Upload {
    private String image_Name;
    private String imageUrl;
    private String image_des;

    private  String image_Star;
    private  String image_Location;
    private String image_Price;
    private  String image_contact;

    public Hotels_Upload(){

    }

    public Hotels_Upload (String imageName, String imageUrl, String image_des, String image_Star,String image_Location,String image_Price, String image_contact){
        this.image_Name = imageName;
        this.imageUrl = imageUrl;
        this.image_des = image_des;
        this.image_Star = image_Star;
        this.image_Location = image_Location;
        this.image_Price = image_Price;
        this.image_contact = image_contact;


    }

    public  String getImageName(){
        return image_Name;
    }

    public void setImageName(String imageName){
        this.image_Name = imageName;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImage_des(){
        return image_des;
    }

    public void setImage_des(String image_des){

        this.image_des = image_des;
    }
    public String getImage_Star(){
        return image_Star;
    }
    public void setImage_Star(String image_Star){

        this.image_Star = image_Star;
    }
    public String getImage_Location(){
        return image_Location;
    }
    public void setImage_Location(String image_Location){

        this.image_Location = image_Location;
    }
    public String getImage_Price(){
        return image_Price;
    }
    public void setImage_Price(String image_Price){

        this.image_Price = image_Price;
    }
    public String getImage_contact(){
        return image_contact;
    }
    public void setImage_contact(String image_contact){

        this.image_contact = image_contact;
    }



}

