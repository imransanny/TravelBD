package edu.ewubd.travelbd119;


public class Upload {
    private String image_Name;
    private String imageUrl;
    private String image_des;

    public Upload(){

    }

    public Upload (String imageName, String imageUrl, String image_des){
        this.image_Name = imageName;
        this.imageUrl = imageUrl;
        this.image_des = image_des;
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



}

