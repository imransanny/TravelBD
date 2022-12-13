package edu.ewubd.travelbd119;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class Home extends AppCompatActivity {

    TextView place, hotel, air, car, bike, review;
    ImageView discout, place_logo, Hotel_logo, Air_logo, Car_logo, Bike_logo, Review_logo;

    RecyclerView recyclerView;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home);

            review = findViewById(R.id.review_text_id);
            discout = findViewById(R.id.discount_image_id);


            review.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Home.this, Best_visit_place_suggestion.class);
                    startActivity(i);
                }
            });

            discout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Home.this, Discount.class);
                    startActivity(i);
                }
            });


        }


    }
