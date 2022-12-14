package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Airline extends AppCompatActivity {

    RadioButton oneway, round_trip, another_city;
    EditText from_city, to_city, departure_date, return_date;
    Button airline_search, airline1, airline2;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.airline);

        oneway = findViewById(R.id.btn_oneway);
        round_trip = findViewById(R.id.btn_roundtrip);
        another_city = findViewById(R.id.btn_anothercity);

        from_city = findViewById(R.id.airfrom);
        to_city = findViewById(R.id.airto);
        departure_date = findViewById(R.id.air_depart);
        return_date = findViewById(R.id.air_return);

        airline_search = findViewById(R.id.airline_search);
        airline1 = findViewById(R.id.select_airline1);
        airline2 = findViewById(R.id.select_airline2);

        oneway.setOnClickListener(v -> choose_oneway());
        round_trip.setOnClickListener(v -> choose_rountrip());
        another_city.setOnClickListener(v -> choose_anotherway());

        airline_search.setOnClickListener(v -> available_airlines());
        airline1.setOnClickListener(v -> select_airline_1());
        airline2.setOnClickListener(v -> select_airline_2());

    }

    private void choose_oneway() {
        Intent i = new Intent(Airline.this, Home.class);
        startActivity(i);
    }
    private void choose_rountrip() {
        Intent i = new Intent(Airline.this, Home.class);
        startActivity(i);
    }
    private void choose_anotherway() {
        Intent i = new Intent(Airline.this, Home.class);
        startActivity(i);
    }

    private void available_airlines() {
        Intent i = new Intent(Airline.this, Home.class);
        startActivity(i);
    }
    private void select_airline_1() {
        Intent i = new Intent(Airline.this, Home.class);
        startActivity(i);
    }
    private void select_airline_2() {
        Intent i = new Intent(Airline.this, Home.class);
        startActivity(i);
    }

}
