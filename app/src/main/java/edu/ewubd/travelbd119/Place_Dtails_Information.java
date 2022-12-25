package edu.ewubd.travelbd119;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Place_Dtails_Information extends AppCompatActivity {

    ImageView cover_image;
    TextView coverTitle, place_description;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_information);

        Bundle extras = getIntent().getExtras();

        String place_image = extras.getString("PLACE_IMAGE").trim();
        System.out.println("place_image" + place_image + "==");

        String place_name = extras.getString("IMAGE_NAME").trim();
        System.out.println("place_name" + place_name + "==");

        String place_des = extras.getString("IMAGE_DESCRIPTION").trim();
        System.out.println("place_des" + place_des + "==");

        cover_image = findViewById(R.id.coverimg_Place_detais);
        coverTitle = findViewById(R.id.Tv_title);
        place_description = findViewById(R.id.Tv_desctiop);


        coverTitle.setText(place_name);
        place_description.setText(place_des);

        Picasso.get()
                .load(place_image)
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(cover_image);


    }
}
