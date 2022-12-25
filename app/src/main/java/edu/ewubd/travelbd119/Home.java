package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {

    TextView place, hotel, air, car, bike, review;
    ImageView discout, place_logo, Hotel_logo, Air_logo, Car_logo, Bike_logo, Review_logo, profile,ic_menu;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;




        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home);
            mAuth = FirebaseAuth.getInstance();



            place = findViewById(R.id.place_text_id);
            hotel = findViewById(R.id.hotel_text_id);
            air = findViewById(R.id.air_text_id);
            car = findViewById(R.id.car_text_id);
            bike = findViewById(R.id.bike_text_id);
            discout = findViewById(R.id.discount_image_id);
            place_logo = findViewById(R.id.place_icon_home_id);
            Hotel_logo = findViewById(R.id.hotel_icon_id);
            Air_logo = findViewById(R.id.air_icon_home_id);
            Car_logo = findViewById(R.id.car_image_id);
            Bike_logo = findViewById(R.id.Bike_image_id);
            review = findViewById(R.id.review_text_id);
            Review_logo = findViewById(R.id.review_image_id);
            profile = findViewById(R.id.profile_home_id);


            place.setOnClickListener(v->place());
            place_logo.setOnClickListener(v->place());
            Hotel_logo.setOnClickListener(v->hotel());
            hotel.setOnClickListener(v->hotel());
            air.setOnClickListener(v->air());
            Air_logo.setOnClickListener(v->air());

            car.setOnClickListener(v->car());
            Car_logo.setOnClickListener(v->car());
            bike.setOnClickListener(v->bike());
            Bike_logo.setOnClickListener(v->bike());
            review.setOnClickListener(v->review());
            Review_logo.setOnClickListener(v->review());
            profile.setOnClickListener(v->profile());
            discout.setOnClickListener(v->discount());
            ic_menu = findViewById(R.id.Hamburger_menu_home_id);
            ic_menu.setOnClickListener(v-> ic_menu());




        }

    private void ic_menu() {
FirebaseAuth.getInstance().signOut();

SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);
sp.edit().clear().commit();
Intent i = new Intent(Home.this, MainActivity.class);
startActivity(i);


//finish();

    }


    //================================funciton for home page
    private void review() {
        Intent i = new Intent(Home.this, Best_visit_place_suggestion.class);
        startActivity(i);
    }
    private void discount() {
        Intent i = new Intent(Home.this, Discount.class);
        startActivity(i);
    }
    private void place() {


        Intent i = new Intent(Home.this, Display_Place_Image.class);
        startActivity(i);
    }


    private void hotel() {

        Intent i = new Intent(Home.this, Hotels.class);
        startActivity(i);
    } private void air() {
        Intent i = new Intent(Home.this, Airline.class);
        startActivity(i);
    } private void car() {
        Intent i = new Intent(Home.this, Cars.class);
        startActivity(i);
    } private void bike() {
        Intent i = new Intent(Home.this, Bike.class);
        startActivity(i);
    }
    private void profile() {
        Intent i = new Intent(Home.this, Profile.class);
        startActivity(i);
    }



}
