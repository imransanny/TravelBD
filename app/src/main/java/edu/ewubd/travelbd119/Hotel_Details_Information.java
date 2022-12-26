package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Hotel_Details_Information extends AppCompatActivity {

    ImageView cover_image;
    TextView coverTitle, place_description, price, star,location;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_details_information);

        Bundle extras = getIntent().getExtras();

        String place_image = extras.getString("PLACE_IMAGE").trim();
        System.out.println("place_image" + place_image + "==");

        String place_name = extras.getString("IMAGE_NAME").trim();
        System.out.println("place_name" + place_name + "==");

        String place_des = extras.getString("IMAGE_DESCRIPTION").trim();
        System.out.println("place_des" + place_des + "==");

        String place_Star = extras.getString("PLACE_STAR").trim();

        String place_Location = extras.getString("PLACE_LOCATION").trim();

        String place_Price = extras.getString("PLACE_PRICE").trim();







        cover_image = findViewById(R.id.coverimg_Place_detais);
        coverTitle = findViewById(R.id.Tv_title);
        place_description = findViewById(R.id.Tv_desctiop);
        star = findViewById(R.id.Tv_Star);
        location = findViewById(R.id.Tv_loacation);
        price = findViewById(R.id.Tv_price);


        coverTitle.setText(place_name);
        place_description.setText(place_des);
        star.setText(place_Star);
        location.setText(place_Location);
        price.setText(place_Price);

        Picasso.get()
                .load(place_image)
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(cover_image);


    }
}