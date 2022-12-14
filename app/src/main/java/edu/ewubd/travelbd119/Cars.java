package edu.ewubd.travelbd119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Cars extends AppCompatActivity {

    TextView detail01, detail02, detail03, detail04, detail05;
    EditText pickup, picktime, dropoff, droptime;
    Button search_car;
    ImageView car01, car02, car03, car04, car05;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cars);

        detail01 = findViewById(R.id.detail01);
        detail02 = findViewById(R.id.detail02);
        detail03 = findViewById(R.id.detail03);
        detail04 = findViewById(R.id.detail04);
        detail05 = findViewById(R.id.detail05);

        pickup = findViewById(R.id.car_pickup);
        picktime = findViewById(R.id.car_pickuptime);
        dropoff = findViewById(R.id.car_drpoff);
        droptime = findViewById(R.id.car_dropofftime);

        search_car = findViewById(R.id.car_search);

        car01 = findViewById(R.id.car_noah);
        car02 = findViewById(R.id.car_02);
        car03 = findViewById(R.id.car_03);
        car04 = findViewById(R.id.car_04);
        car05 = findViewById(R.id.car_05);

        car01.setOnClickListener(v -> select_car_01());
        car02.setOnClickListener(v -> select_car_02());
        car03.setOnClickListener(v -> select_car_03());
        car04.setOnClickListener(v -> select_car_04());
        car05.setOnClickListener(v -> select_car_05());

    }

    private void select_car_01() {
        Intent i = new Intent(Cars.this, Home.class);
        startActivity(i);
    }
    private void select_car_02() {
        Intent i = new Intent(Cars.this, Home.class);
        startActivity(i);
    }
    private void select_car_03() {
        Intent i = new Intent(Cars.this, Home.class);
        startActivity(i);
    }
    private void select_car_04() {
        Intent i = new Intent(Cars.this, Home.class);
        startActivity(i);
    }
    private void select_car_05() {
        Intent i = new Intent(Cars.this, Home.class);
        startActivity(i);
    }

}
