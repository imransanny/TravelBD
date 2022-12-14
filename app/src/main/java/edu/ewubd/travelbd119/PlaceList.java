package edu.ewubd.travelbd119;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlaceList extends AppCompatActivity {

    TextView place1, place2, place3, place4, place5;
    ImageView place_logo1, place_logo2, place_logo3, place_logo4, place_logo5;
    SearchView places;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placelist);

        places = findViewById(R.id.place_search);

        place1 = findViewById(R.id.place_kuyakata);
        place2 = findViewById(R.id.place_bagerhat);
        place3 = findViewById(R.id.place_sreemangal);
        place4 = findViewById(R.id.place_shah_amanat_shetu);
        place5 = findViewById(R.id.place_rangamati);

        place_logo1 = findViewById(R.id.place_01);
        place_logo2 = findViewById(R.id.place_02);
        place_logo3 = findViewById(R.id.place_03);
        place_logo4 = findViewById(R.id.place_04);
        place_logo5 = findViewById(R.id.place_05);

        place_logo1.setOnClickListener(v -> select_place_01());
        place_logo2.setOnClickListener(v -> select_place_02());
        place_logo3.setOnClickListener(v -> select_place_03());
        place_logo4.setOnClickListener(v -> select_place_04());
        place_logo5.setOnClickListener(v -> select_place_05());

    }

    private void select_place_01() {
        Intent i = new Intent(PlaceList.this, Home.class);
        startActivity(i);
    }
    private void select_place_02() {
        Intent i = new Intent(PlaceList.this, Home.class);
        startActivity(i);
    }
    private void select_place_03() {
        Intent i = new Intent(PlaceList.this, Home.class);
        startActivity(i);
    }
    private void select_place_04() {
        Intent i = new Intent(PlaceList.this, Home.class);
        startActivity(i);
    }
    private void select_place_05() {
        Intent i = new Intent(PlaceList.this, Home.class);
        startActivity(i);
    }


}
