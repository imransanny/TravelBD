package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Hotel_Details_Information extends AppCompatActivity {

    ImageView cover_image,contact_img;
    TextView coverTitle, place_description, price, star,location, contact;
    String place_contact;


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

       place_contact = extras.getString("PLACE_CONTACT").trim();







        cover_image = findViewById(R.id.coverimg_Place_detais);
        coverTitle = findViewById(R.id.Tv_title);
        place_description = findViewById(R.id.Tv_desctiop);
        star = findViewById(R.id.Tv_Star);
        location = findViewById(R.id.Tv_loacation);
        price = findViewById(R.id.Tv_price);
        contact = findViewById(R.id.Tv_contactnumber_id);
        contact_img = findViewById(R.id.call_btn_id);


        coverTitle.setText(place_name);
        place_description.setText(place_des);
        star.setText(place_Star);
        location.setText(place_Location);
        price.setText(place_Price);
        contact.setText(place_contact);

        Picasso.get()
                .load(place_image)
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(cover_image);







        contact.setOnClickListener(v-> contact_manager());
        contact_img.setOnClickListener(v-> contact_manager());

    }

    private void contact_manager() {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:"+place_contact));
        startActivity(i);
    }
}