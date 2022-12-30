package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home extends AppCompatActivity {

    TextView place, hotel, air, car, bike, review;
    ImageView discout, place_logo, Hotel_logo, Air_logo, Car_logo, Bike_logo, Review_logo, profile,ic_menu;
    RecyclerView recyclerView;
    private List<Upload> uploadList;
    FirebaseAuth mAuth;
DatabaseReference databaseReference;
    String Current_USER_1;
    private Hotel_img_Adapter myAdapter;
    private List<Hotels_Upload> uploadList_hotels;
    private ProgressBar progressBar;






        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home);

         //   recyclerView.setNestedScrollingEnabled(false);
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

//====================================recycaler view

            recyclerView = findViewById(R.id.recyclerview_id);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            uploadList_hotels = new ArrayList<>();
            databaseReference = FirebaseDatabase.getInstance().getReference("Upload_HOTEL_Image");
            databaseReference.addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//database ttheke data anbo

                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Hotels_Upload upload = dataSnapshot1.getValue(Hotels_Upload.class);
                        uploadList_hotels.add(upload);
                    }
                    myAdapter = new Hotel_img_Adapter(Home.this, uploadList_hotels);
                    recyclerView.setAdapter(myAdapter);
                    //progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),"Error : "+error.getMessage(),Toast.LENGTH_LONG).show();
                  //  progressBar.setVisibility(View.INVISIBLE);
                }
            });
//===========================================
        }
//logout
    private void ic_menu() {
FirebaseAuth.getInstance().signOut();
SharedPreferences sp = this.getSharedPreferences("Remember_login_Sharedpref", MODE_PRIVATE);
sp.edit().clear().apply();

        SharedPreferences sppp = this.getSharedPreferences("CURRENT_USER_INFO", MODE_PRIVATE);
        sppp.edit().clear().apply();

Intent i = new Intent(Home.this, MainActivity.class);
//i.putExtra("LOGOUT","YES");
startActivity(i);


//finish();

    }


    //================================funciton for home page
    private void review() {
        //Intent i = new Intent(Home.this, Best_visit_place_suggestion.class);
        Intent i = new Intent(Home.this, Rview_test.class);
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
        SharedPreferences users = this.getSharedPreferences("TRAVELER", MODE_PRIVATE);
        String curr_user = users.getString("TRAVELER", "");
 if(curr_user.equals("TRA")){
     Intent i = new Intent(Home.this, Display_HOTEL_Image.class);
     startActivity(i);
 }else if(curr_user.equals("MAN")) {
     Intent i = new Intent(Home.this, Hotels.class);
     startActivity(i);
 }else{
     System.out.println("Somethig Wrong");
     System.out.println(curr_user);
 }

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
